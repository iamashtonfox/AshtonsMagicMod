package com.shakyturd.ashtonsmagicmod.item.custom;

import com.shakyturd.ashtonsmagicmod.entity.custom.MagicProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class WoodenStaffItem extends Item {
    public WoodenStaffItem(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack staff = player.getItemInHand(usedHand);
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.FIRECHARGE_USE,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F/ (level.getRandom().nextFloat() *0.4F + 0.8F)
        );
        player.awardStat(Stats.ITEM_USED.get(this));
        if(!player.getAbilities().instabuild){
            staff.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        }
        player.getCooldowns().addCooldown(this,15);
        if(!level.isClientSide){
            MagicProjectileEntity magicProjectile = new MagicProjectileEntity(player, level);
//            Snowball magicProjectile = new Snowball(level, player); //placeholder until can make entity working // LOL IT WORKS NOW YAY keeping this here
            magicProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
            level.addFreshEntity(magicProjectile);
        }



        return InteractionResultHolder.sidedSuccess(staff, level.isClientSide());
    }
}
