package com.shakyturd.ashtonsmagicmod.util;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Blocks {

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AshtonsMagicMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> MAGIC_CYRYSTALS = createTag("magic_crystals");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AshtonsMagicMod.MOD_ID, name));
        }
    }
}
