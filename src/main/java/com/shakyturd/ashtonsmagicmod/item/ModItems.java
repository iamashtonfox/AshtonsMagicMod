package com.shakyturd.ashtonsmagicmod.item;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.item.custom.WoodenStaffItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AshtonsMagicMod.MOD_ID);

    public static final DeferredItem<Item> MAGIC_CRYSTAL = ITEMS.register("magic_crystal", //TODO if the crystal catches fire, destroy the item and release some experience orbs
            () -> new Item(new Item.Properties()                                                 //TODO probably need to make the magic crystal a custom item instead of just a deffered item
                    .fireResistant()
                    .rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> EMPTY_WOODEN_STAFF = ITEMS.register("dormant_wooden_staff",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MANAPHITE = ITEMS.register("manaphite",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredItem<Item> WOODEN_STAFF = ITEMS.register("wooden_staff",
            () -> new WoodenStaffItem(new Item.Properties()
                    .durability(128)
                    .rarity(Rarity.RARE)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
