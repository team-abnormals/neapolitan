package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanConfiguredFeatures;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanPlacedFeatures;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import com.teamabnormals.neapolitan.core.registry.datapack.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NeapolitanDatapackProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(NeapolitanRegistries.ICE_CREAM_FLAVOR, NeapolitanIceCreamFlavors::bootstrap)
			.add(NeapolitanRegistries.ICE_CREAM_OVERRIDE, NeapolitanIceCreamOverrides::bootstrap)
			.add(NeapolitanRegistries.CHIMPANZEE_VARIANT, NeapolitanChimpanzeeVariants::bootstrap)
			.add(Registries.JUKEBOX_SONG, NeapolitanJukeboxSongs::bootstrap)
			.add(Registries.PAINTING_VARIANT, NeapolitanPaintingVariants::bootstrap)
			.add(Registries.BANNER_PATTERN, NeapolitanBannerPatterns::bootstrap)
			.add(Registries.CONFIGURED_FEATURE, NeapolitanConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, NeapolitanPlacedFeatures::bootstrap)
			.add(Registries.BIOME, NeapolitanBiomes::bootstrap)
			.add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, NeapolitanBiomeSlices::bootstrap)
			.add(Keys.BIOME_MODIFIERS, NeapolitanBiomeModifiers::bootstrap)
			.add(Registries.TRIM_PATTERN, NeapolitanTrimPatterns::bootstrap)
			.add(Registries.DAMAGE_TYPE, NeapolitanDamageSources::bootstrap);

	public NeapolitanDatapackProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, Set.of(Neapolitan.MOD_ID));
	}
}