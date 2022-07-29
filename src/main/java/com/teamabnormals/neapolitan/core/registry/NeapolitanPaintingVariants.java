package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class NeapolitanPaintingVariants {
	public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Neapolitan.MOD_ID);

	public static final RegistryObject<PaintingVariant> SCREAM = PAINTING_VARIANTS.register("scream", () -> new PaintingVariant(32, 32));
	public static final RegistryObject<PaintingVariant> CONE = PAINTING_VARIANTS.register("cone", () -> new PaintingVariant(16, 32));
}