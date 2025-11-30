package com.shakyturd.ashtonsmagicmod.entity;

import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.entity.custom.MagicProjectileEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AshtonsMagicMod.MOD_ID);

    public static final Supplier<EntityType<MagicProjectileEntity>> MAGICPROJECTILE =
            ENTITY_TYPES.register("magic_projectile", () -> EntityType.Builder.<MagicProjectileEntity>of(MagicProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build("magic_projectile"));
}
