package com.shakyturd.ashtonsmagicmod.entity.custom;

import com.shakyturd.ashtonsmagicmod.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MagicProjectileEntity extends Projectile { //TODO known issue: places the entity in the middle of the block the player is standing on, instead of the exact player position

    public enum MagicProjectileType {
        BASE,
        POISON
    }

    private static final EntityDataAccessor<Integer> TYPE =
            SynchedEntityData.defineId(MagicProjectileEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HIT =
            SynchedEntityData.defineId(MagicProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> COUNT =
            SynchedEntityData.defineId(MagicProjectileEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DAMAGE =
            SynchedEntityData.defineId(MagicProjectileEntity.class, EntityDataSerializers.FLOAT);


    public MagicProjectileEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }
    //ok next figure out how to stop projectile inertia on cast

    public MagicProjectileEntity(LivingEntity shooter, Level level) {
        this(ModEntities.MAGICPROJECTILE.get(), level);
        this.setOwner(shooter);
        BlockPos pos = shooter.blockPosition();
        double d0 = shooter.getX();
        double d1 = shooter.getEyeY() -0.3F;
        double d2 = shooter.getZ();
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }



    public void setType(MagicProjectileType type){
        this.entityData.set(TYPE, type.ordinal());
    }

    public int getProjectileType() {
        return this.entityData.get(TYPE);
    }

    @Override //trying to fix the projectile displacement when shooting with x/y-axis movement
    public Vec3 getMovementToShoot(double x, double y, double z, float velocity, float inaccuracy) {
        return new Vec3(x, y, z).normalize()
                .add(
                        this.random.triangle(0.0,0.0),
                        this.random.triangle(0.0,0.0),
                        this.random.triangle(0.0,0.0)
                ).scale((double)velocity);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.entityData.get(HIT)){ //this is not working properly for some reason :/
            this.entityData.set(COUNT, this.entityData.get(COUNT) + 1);
            this.entityData.set(DAMAGE, this.entityData.get(DAMAGE) - 2);
            if(this.entityData.get(COUNT) >= 2){
                this.destroy();
            }
            this.entityData.set(HIT, false);
        }
        if(this.tickCount >= 15) {
            this.remove(RemovalReason.DISCARDED);
        }
        Vec3 vec3 = this.getDeltaMovement();

        HitResult res = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if(res.getType() != HitResult.Type.MISS) {
            this.onHit(res);
        }
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;


        //TODO be able to shoot through leaves? (maybe even other semi-permeable blocks?)
        if(this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir) && !this.isInWaterOrBubble()){
            this.discard();
        }else if(this.isInWaterOrBubble()) {
            this.setDeltaMovement(vec3.scale(1.0F));
            this.setPos(d0, d1, d2);
        } else{
            this.setDeltaMovement(vec3.scale(1.0F));
            this.setPos(d0, d1, d2);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity hitEntity = result.getEntity();
        Entity owner = this.getOwner();

        if(hitEntity == owner && this.level().isClientSide()) {
            return;
        }

        //TODO: make this sound not play when hitting an enderman
        this.getOwner().playSound(SoundEvents.ARMOR_STAND_BREAK, 2F, 1F);

        LivingEntity livent = owner instanceof LivingEntity ? (LivingEntity)owner : null;
        boolean hurt = hitEntity.hurt(this.damageSources().mobProjectile(this, livent), this.entityData.get(DAMAGE));

        if(hurt) {
            if(this.entityData.get(TYPE) == 1) {
                if(hitEntity instanceof LivingEntity livingHitEntity) {
                    livingHitEntity.addEffect(new MobEffectInstance(MobEffects.POISON,3), owner);
                }
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);


        if(this.level().isClientSide()) {
            return;
        }

        if(result.getType() == HitResult.Type.ENTITY && result instanceof EntityHitResult entityHitResult) {
            Entity hit = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            if(owner != hit) {
                this.entityData.set(HIT, true);
            }
        }else{
            this.entityData.set(HIT, false);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(TYPE, 0);
        builder.define(HIT, false);
        builder.define(COUNT, 0);
        builder.define(DAMAGE, 8f);
    }
    public void destroy() {
        this.discard();
        this.level().gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }
}
