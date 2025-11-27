package com.shakyturd.ashtonsmagicmod.item;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AshtonsMagicMod.MOD_ID);

    public static final DeferredItem<Item> MAGIC_CRYSTAL = ITEMS.register("magic_crystal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> EMPTY_WOODEN_STAFF = ITEMS.register("dormant_wooden_staff",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
