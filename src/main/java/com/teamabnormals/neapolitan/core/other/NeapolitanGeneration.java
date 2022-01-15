package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanPlacedFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanGeneration {

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();
		Biome.BiomeCategory category = event.getCategory();
		if (biome == null) return;
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, biome);
		MobSpawnSettingsBuilder spawns = event.getSpawns();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		if ((category.equals(Biome.BiomeCategory.JUNGLE) || biome.getPath().contains("rainforest"))) {
			if (NeapolitanConfig.COMMON.plantainSpiderSpawning.get())
				spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(NeapolitanEntityTypes.PLANTAIN_SPIDER.get(), 120, 3, 5));
		}

		if (category.equals(Biome.BiomeCategory.PLAINS) && NeapolitanConfig.COMMON.strawberryBushGenerationChance.get() > 0)
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_STRAWBERRY_BUSH);

		if (category.equals(Biome.BiomeCategory.FOREST) && NeapolitanConfig.COMMON.adzukiSproutsGenerationChance.get() > 0)
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_ADZUKI_SPROUTS);

		if (category.equals(Biome.BiomeCategory.SAVANNA) && NeapolitanConfig.COMMON.vanillaVineGenerationChance.get() > 0)
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_VANILLA_VINE);

		if (category.equals(Biome.BiomeCategory.EXTREME_HILLS) && NeapolitanConfig.COMMON.mintPondGenerationChance.get() > 0)
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.MINT_POND);

		if (event.getClimate().precipitation.equals(Precipitation.RAIN)) {
			if (category.equals(Biome.BiomeCategory.BEACH) && NeapolitanConfig.COMMON.bananaPlantBeachGeneration.get())
				generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_BEACH);

			if (category.equals(Biome.BiomeCategory.JUNGLE) && NeapolitanConfig.COMMON.bananaPlantJungleGeneration.get()) {
				if (biome.getPath().contains("rainforest")) {
					if (BiomeDictionary.hasType(key, BiomeDictionary.Type.FOREST))
						generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_RARE);
					else
						generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_VERY_RARE);
				} else if (!DataUtil.matchesKeys(biome, Biomes.SPARSE_JUNGLE))
					generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_JUNGLE);
				else
					generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_RARE);
			}
		}
	}
}
