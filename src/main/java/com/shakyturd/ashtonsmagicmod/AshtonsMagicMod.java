package com.shakyturd.ashtonsmagicmod;

import com.shakyturd.ashtonsmagicmod.block.ModBlocks;
import com.shakyturd.ashtonsmagicmod.entity.ModEntities;
import com.shakyturd.ashtonsmagicmod.entity.client.MagicProjectileRenderer;
import com.shakyturd.ashtonsmagicmod.item.ModCreativeModeTabs;
import com.shakyturd.ashtonsmagicmod.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

//TODO manaphite refinery block entity which creates refined manaphite (essentially ingots) to create manaphite armor
//TODO manaphite armor set bonus: extra cast speed on magic weapons

//TODO manaphite ore gen in deep dark?

//TODO manaphite-magicEssence purifier block entity which turns magic essence into magic crystals using manaphite
//TODO magic crystals able to be slotted onto staves

//TODO add biome-speicific magic crystals

//TODO make blanket magic projectile entity to act as foundational entity

//TODO make equipped staves render in-hand the same way swords are

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AshtonsMagicMod.MOD_ID)
public class AshtonsMagicMod {
    public static final String MOD_ID = "ashtonsmagicmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AshtonsMagicMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);


        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);


        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = AshtonsMagicMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ClientModEvents {
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.MAGICPROJECTILE.get(), MagicProjectileRenderer::new);
        }
    }
}
