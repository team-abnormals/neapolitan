package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public final class NeapolitanPaintingVariants {
	public static final ResourceKey<PaintingVariant> SCREAM = create("scream");
	public static final ResourceKey<PaintingVariant> CONE = create("cone");
	public static final ResourceKey<PaintingVariant> DAWN = create("dawn");
	public static final ResourceKey<PaintingVariant> NEAPOLEON = create("neapoleon");

	public static void bootstrap(BootstrapContext<PaintingVariant> context) {
		register(context, SCREAM, 2, 2);
		register(context, CONE, 1, 2);
		register(context, DAWN, 4, 4);
		register(context, NEAPOLEON, 4, 4);
	}

	private static ResourceKey<PaintingVariant> create(String name) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, Neapolitan.location(name));
	}

	private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
		context.register(key, new PaintingVariant(width, height, key.location()));
	}
}