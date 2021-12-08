package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanGeneration {

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();
		Biome.Category category = event.getCategory();
		if (biome == null) return;
		RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, biome);
		MobSpawnInfoBuilder spawns = event.getSpawns();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		if ((category.equals(Biome.Category.JUNGLE) || biome.getPath().contains("rainforest"))) {
			if (NeapolitanConfig.COMMON.plantainSpiderSpawning.get())
				spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(NeapolitanEntities.PLANTAIN_SPIDER.get(), 120, 3, 5));
		}

		if (category.equals(Biome.Category.PLAINS) && NeapolitanConfig.COMMON.strawberryBushGenerationChance.get() > 0)
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.PATCH_STRAWBERRY_BUSH);

		if (category.equals(Biome.Category.FOREST) && NeapolitanConfig.COMMON.adzukiSproutsGenerationChance.get() > 0)
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.PATCH_ADZUKI_SPROUTS);

		if (category.equals(Biome.Category.SAVANNA) && NeapolitanConfig.COMMON.vanillaVineGenerationChance.get() > 0)
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.PATCH_VANILLA_VINE);

		if (category.equals(Biome.Category.EXTREME_HILLS) && NeapolitanConfig.COMMON.mintPondGenerationChance.get() > 0)
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.MINT_POND);

		if (event.getClimate().precipitation.equals(RainType.RAIN)) {
			if (category.equals(Biome.Category.BEACH) && NeapolitanConfig.COMMON.bananaPlantBeachGeneration.get())
				generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.BANANA_PLANT_BEACH);

			if (category.equals(Biome.Category.JUNGLE) && NeapolitanConfig.COMMON.bananaPlantJungleGeneration.get()) {
				if (biome.getPath().contains("rainforest")) {
					if (BiomeDictionary.hasType(key, BiomeDictionary.Type.FOREST))
						generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.BANANA_PLANT_RARE);
					else
						generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.BANANA_PLANT_VERY_RARE);
				} else if (!DataUtil.matchesKeys(biome, Biomes.BAMBOO_JUNGLE) && !DataUtil.matchesKeys(biome, Biomes.BAMBOO_JUNGLE_HILLS)) {
					if (!DataUtil.matchesKeys(biome, Biomes.JUNGLE_EDGE) && !DataUtil.matchesKeys(biome, Biomes.MODIFIED_JUNGLE_EDGE))
						generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.BANANA_PLANT_JUNGLE);
					else
						generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.Configured.BANANA_PLANT_RARE);
				}
			}


		}
	}
}
