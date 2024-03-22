package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanBiomeModifiers;
import com.teamabnormals.neapolitan.core.other.NeapolitanDamageSources;
import com.teamabnormals.neapolitan.core.other.NeapolitanTrimPatterns;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanConfiguredFeatures;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanPlacedFeatures;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NeapolitanDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, NeapolitanConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, NeapolitanPlacedFeatures::bootstrap)
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, NeapolitanBiomeModifiers::bootstrap)
			.add(Registries.TRIM_PATTERN, NeapolitanTrimPatterns::bootstrap)
			.add(Registries.DAMAGE_TYPE, NeapolitanDamageSources::bootstrap);

	public NeapolitanDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, Set.of(Neapolitan.MOD_ID));
	}
}