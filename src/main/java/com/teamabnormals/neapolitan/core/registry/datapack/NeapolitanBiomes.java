package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanGeneration;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class NeapolitanBiomes {
	public static final ResourceKey<Biome> STRAWBERRY_FIELDS = createKey("strawberry_fields");

	public static void bootstrap(BootstrapContext<Biome> context) {
		HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

		context.register(STRAWBERRY_FIELDS, strawberryFields(features, carvers));
	}

	public static ResourceKey<Biome> createKey(String name) {
		return ResourceKey.create(Registries.BIOME, Neapolitan.location(name));
	}

	private static Biome strawberryFields(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
		NeapolitanGeneration.strawberryFields(generation);

		MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.farmAnimals(spawns);
		BiomeDefaultFeatures.commonSpawns(spawns);
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 5, 2, 6));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 3));
		spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 6, 2, 3));

		return (new Biome.BiomeBuilder()).hasPrecipitation(true).temperature(0.8F).downfall(0.4F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.8F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(OverworldBiomes.NORMAL_MUSIC).build()).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build();
	}

	private static int calculateSkyColor(float temperature) {
		float clampedTemp = Mth.clamp(temperature / 3.0F, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - clampedTemp * 0.05F, 0.5F + clampedTemp * 0.1F, 1.0F);
	}
}