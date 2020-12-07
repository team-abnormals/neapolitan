package com.minecraftabnormals.neapolitan.common.world.gen;

import com.minecraftabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanBiomeFeatures {

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		ResourceLocation biome = event.getName();
		Biome.Category category = event.getCategory();
		RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, biome);

		if (category.equals(Biome.Category.JUNGLE) || biome.getPath().contains("rainforest"))
			event.getSpawns().withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(NeapolitanEntities.PLANTAIN_SPIDER.get(), 120, 3, 5));

		if (category.equals(Biome.Category.PLAINS))
			event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.STRAWBERRY_PATCH);

		if (category.equals(Biome.Category.SAVANNA))
			event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.VANILLA_VINE_PATCH);

		if (event.getClimate().precipitation.equals(RainType.RAIN)) {
			if (category.equals(Biome.Category.BEACH))
				event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.BANANA_PLANT_BEACH);

			if (category.equals(Biome.Category.JUNGLE)) {
				if (!isBiome(biome, Biomes.BAMBOO_JUNGLE) && !isBiome(biome, Biomes.BAMBOO_JUNGLE_HILLS)) {
					if (!isBiome(biome, Biomes.JUNGLE_EDGE) && !isBiome(biome, Biomes.MODIFIED_JUNGLE_EDGE))
						event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.BANANA_PLANT_JUNGLE);
					else
						event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.BANANA_PLANT_RARE);
				}
			}

			if (biome.getPath().contains("rainforest")) {
				if (BiomeDictionary.hasType(key, BiomeDictionary.Type.FOREST))
					event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.BANANA_PLANT_RARE);
				else
					event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configs.BANANA_PLANT_VERY_RARE);
			}
		}
	}

	public static boolean isBiome(ResourceLocation biome, RegistryKey<Biome>... biomes) {
		for (RegistryKey<Biome> key : biomes) if (key.getLocation().equals(biome)) return true;
		return false;
	}

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Neapolitan.MOD_ID, name), configuredFeature);
	}

	public static final class Configs {
		public static final BlockClusterFeatureConfig STRAWBERRY_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NeapolitanBlocks.STRAWBERRY_BUSH.get().getDefaultState().with(StrawberryBushBlock.TYPE, StrawberryBushBlock.StrawberryType.RED)), SimpleBlockPlacer.PLACER)).tries(512).build();
		public static final BlockClusterFeatureConfig VANILLA_VINE_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NeapolitanBlocks.VANILLA_VINE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(64).build();

		public static final ConfiguredFeature<?, ?> STRAWBERRY_PATCH = register("banana_plant", NeapolitanFeatures.STRAWBERRY_PATCH.get().withConfiguration(STRAWBERRY_PATCH_CONFIG).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30))));
		public static final ConfiguredFeature<?, ?> VANILLA_VINE_PATCH = register("banana_plant", NeapolitanFeatures.VANILLA_VINE_PATCH.get().withConfiguration(VANILLA_VINE_PATCH_CONFIG).withPlacement(Placement.CHANCE.configure(new ChanceConfig(28))));

		public static final ConfiguredFeature<?, ?> BANANA_PLANT = register("banana_plant", NeapolitanFeatures.BANANA_PLANT.get().withConfiguration(NoFeatureConfig.field_236559_b_)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_BEACH = register("banana_plant_beach", BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.075F, 1))));
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_JUNGLE = register("banana_plant_jungle", BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.30F, 3))));
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_RARE = register("banana_plant_rare", BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.25F, 1))));
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_VERY_RARE = register("banana_plant_very_rare", BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.15F, 1))));
	}
}
