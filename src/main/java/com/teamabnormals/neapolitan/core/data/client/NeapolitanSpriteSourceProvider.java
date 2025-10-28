package com.teamabnormals.neapolitan.core.data.client;

import com.mojang.datafixers.util.Either;
import com.teamabnormals.blueprint.client.renderer.texture.atlas.BlueprintPalettedPermutations;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanMaterials;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanFlavors;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanTrimPatterns;
import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;
import net.minecraft.client.renderer.texture.atlas.sources.PalettedPermutations;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class NeapolitanSpriteSourceProvider extends SpriteSourceProvider {

	public NeapolitanSpriteSourceProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Neapolitan.MOD_ID, helper);
	}

	public static final ResourceLocation COLOR_PALETTE_KEY = Neapolitan.location("ice_cream/color_palettes/ice_cream_palette");

	@Override
	protected void gather() {
		this.atlas(NeapolitanMaterials.CHILLBOX_ATLAS).addSource(new DirectoryLister("entity/chillbox", "entity/chillbox/")).addSource(chillboxPermutations());
		this.atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(iceCreamPermutations());
		this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.patternPermutationsOfVanillaMaterials(NeapolitanTrimPatterns.PRIMAL));
	}

	public static BlueprintPalettedPermutations iceCreamPermutations() {
		return new BlueprintPalettedPermutations(Either.left(List.of(new DirectoryLister("ice_cream/items", "ice_cream/items/"))), COLOR_PALETTE_KEY, getFlavorPermutations());
	}

	public static PalettedPermutations chillboxPermutations() {
		return new PalettedPermutations(List.of(
				Neapolitan.location("entity/chillbox/layer1"),
				Neapolitan.location("entity/chillbox/layer2"),
				Neapolitan.location("entity/chillbox/layer3")
		), COLOR_PALETTE_KEY, getFlavorPermutations());
	}

	private static HashMap<String, ResourceLocation> getFlavorPermutations() {
		HashMap<String, ResourceLocation> permutations = new HashMap<>();
		for (ResourceKey<IceCreamFlavor> flavor : new ResourceKey[]{NeapolitanFlavors.VANILLA, NeapolitanFlavors.CHOCOLATE, NeapolitanFlavors.STRAWBERRY, NeapolitanFlavors.BANANA, NeapolitanFlavors.MINT, NeapolitanFlavors.ADZUKI}) {
			permutations.put(flavor.location().getNamespace() + "_" + flavor.location().getPath(), ResourceLocation.fromNamespaceAndPath(flavor.location().getNamespace(), "ice_cream/color_palettes/" + flavor.location().getPath()));
		}

		return permutations;
	}
}