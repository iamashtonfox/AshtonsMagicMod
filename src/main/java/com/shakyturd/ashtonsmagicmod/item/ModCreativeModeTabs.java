package com.shakyturd.ashtonsmagicmod.item;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AshtonsMagicMod.MOD_ID);

    public static final Supplier<CreativeModeTab> MAGIC_MOD_TAB = CREATIVE_MODE_TAB.register("magic_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ARCANE_ESSENCE.get()))
                    .title(Component.translatable("creativetab.ashtonsmagicmod.magic_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ARCANE_ESSENCE);
                        output.accept(ModItems.ARCANE_CRYSTAL);
                        output.accept(ModItems.EMPTY_WOODEN_STAFF);
                        output.accept(ModItems.WOODEN_STAFF);
                        output.accept(ModItems.MANAPHITE);
                        output.accept(ModBlocks.MANAPHITE_ORE);
                        output.accept(ModBlocks.MANAPHITE_DEEPSLATE_ORE);
                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
