package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class NeapolitanMaterials {
	public static final ResourceLocation CHILLBOX_ATLAS = Neapolitan.location("chillbox");
	public static final ResourceLocation CHILLBOX_SHEET = Neapolitan.location("textures/atlas/chillbox.png");

	public static final Material CHILLBOX_BASE = createChillboxMaterial(Neapolitan.location("chillbox_base"));

	public static Map<ResourceLocation, Material> CHILLBOX_MATERIALS = new HashMap<>();

	public static Material getChillboxMaterial(ResourceKey<IceCreamFlavor> flavor, int layer) {
		ResourceLocation location = Neapolitan.location("layer" + layer + "_" + flavor.location().getNamespace() + "_" + flavor.location().getPath());
		return CHILLBOX_MATERIALS.computeIfAbsent(location, key -> createChillboxMaterial(location));
	}

	private static Material createChillboxMaterial(ResourceLocation assetId) {
		return new Material(CHILLBOX_SHEET, assetId.withPrefix("entity/chillbox/"));
	}
}
