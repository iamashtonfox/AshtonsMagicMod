package com.shakyturd.ashtonsmagicmod.event;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = AshtonsMagicMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) { //whenever you hit any entity with a wand, instantly break the wand and refund the crystal
        if (event.getEntity() instanceof LivingEntity entity && event.getSource().getDirectEntity() instanceof Player player) {
            if (player.getMainHandItem().getItem() == ModItems.WOODEN_STAFF.get()) {
                Level level;
                player.playSound(SoundEvents.ITEM_BREAK);
                player.getMainHandItem().hurtAndBreak(1000, player, player.getEquipmentSlotForItem(player.getMainHandItem()));
//                player.playSound(SoundEvents.ITEM_PICKUP);
                player.addItem(ModItems.MAGIC_CRYSTAL.toStack());
                //TODO create an invisible entity that instantly dies and always drops a magic crystal to give the effect of the crystal "falling out" of the staff as it breaks
            }
        }
    }

}
