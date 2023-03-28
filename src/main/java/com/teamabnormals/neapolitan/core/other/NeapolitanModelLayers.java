package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class NeapolitanModelLayers {
	public static final ModelLayerLocation BANANA_PEEL = register("banana_peel");
	public static final ModelLayerLocation CHIMPANZEE = register("chimpanzee");
	public static final ModelLayerLocation CHIMPANZEE_HEAD = register("chimpanzee_head");
	public static final ModelLayerLocation CHIMPANZEE_INNER_ARMOR = register("chimpanzee", "inner_armor");
	public static final ModelLayerLocation CHIMPANZEE_OUTER_ARMOR = register("chimpanzee", "outer_armor");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(new ResourceLocation(Neapolitan.MOD_ID, name), layer);
	}
}
