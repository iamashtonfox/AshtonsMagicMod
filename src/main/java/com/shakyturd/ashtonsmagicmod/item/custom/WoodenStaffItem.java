package com.shakyturd.ashtonsmagicmod.item.custom;

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
                SoundEvents.FIREWORK_ROCKET_SHOOT,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F/ (level.getRandom().nextFloat() *0.4F + 0.8F)
        );
        player.getCooldowns().addCooldown(this,15);
        if(!level.isClientSide){
//            MagicMissileEntity magicMissile = new MagicMissileEntity(level, player);
            Snowball magicMissile = new Snowball(level, player); //placeholder until can make entity working
            magicMissile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(magicMissile);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if(!player.getAbilities().instabuild){
            staff.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        }

        return InteractionResultHolder.sidedSuccess(staff, level.isClientSide());
    }
}
