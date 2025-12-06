package com.shakyturd.ashtonsmagicmod.datagen;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AshtonsMagicMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ARCANE_ESSENCE.get());
        basicItem(ModItems.ARCANE_CRYSTAL.get());

        basicItem(ModItems.MANAPHITE.get());

        basicItem(ModItems.EMPTY_WOODEN_STAFF.get());
        basicItem(ModItems.WOODEN_STAFF.get());

    }
}
