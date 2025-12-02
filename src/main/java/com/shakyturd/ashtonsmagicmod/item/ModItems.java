package com.shakyturd.ashtonsmagicmod.item;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.item.custom.WoodenStaffItem;
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

    public static final DeferredItem<Item> WOODEN_STAFF = ITEMS.register("wooden_staff",
            () -> new WoodenStaffItem(new Item.Properties().durability(128)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
