package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.common.world.gen.feature.AdzukiSproutsFeature;
import com.teamabnormals.neapolitan.common.world.gen.feature.BananaPlantFeature;
import com.teamabnormals.neapolitan.common.world.gen.feature.MintPondFeature;
import com.teamabnormals.neapolitan.common.world.gen.feature.StrawberryBushFeature;
import com.teamabnormals.neapolitan.common.world.gen.feature.VanillaPatchFeature;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class NeapolitanFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Neapolitan.MOD_ID);
	public static final RegistryObject<Feature<SimpleBlockConfiguration>> STRAWBERRY_BUSH = FEATURES.register("strawberry_bush", () -> new StrawberryBushFeature(SimpleBlockConfiguration.CODEC));
	public static final RegistryObject<Feature<RandomPatchConfiguration>> VANILLA_VINE_PATCH = FEATURES.register("vanilla_patch", () -> new VanillaPatchFeature(RandomPatchConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> BANANA_PLANT = FEATURES.register("banana_plant", () -> new BananaPlantFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<SimpleBlockConfiguration>> ADZUKI_SPROUTS = FEATURES.register("adzuki_sprouts", () -> new AdzukiSproutsFeature(SimpleBlockConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> MINT_POND = FEATURES.register("mind_pond", () -> new MintPondFeature(NoneFeatureConfiguration.CODEC));

	public static final class NeapolitanConfiguredFeatures {

		public static final ConfiguredFeature<?, ?> PATCH_STRAWBERRY_BUSH = register("patch_strawberry_bush", Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(512, 5, 3, () -> STRAWBERRY_BUSH.get().configured(new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.STRAWBERRY_BUSH.get().defaultBlockState()))).filtered(simplePatchPredicate(List.of(Blocks.GRASS_BLOCK))))));
		public static final ConfiguredFeature<?, ?> PATCH_VANILLA_VINE = register("patch_vanilla_vine", NeapolitanFeatures.VANILLA_VINE_PATCH.get().configured(new RandomPatchConfiguration(64, 7, 3, () -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.VANILLA_VINE.get().defaultBlockState()))).filtered(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.ONLY_IN_AIR_PREDICATE)))));
		public static final ConfiguredFeature<?, ?> PATCH_ADZUKI_SPROUTS = register("patch_adzuki_sprouts", Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(256, 3, 2, () -> ADZUKI_SPROUTS.get().configured(new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState()))).filtered(simplePatchPredicate(List.of(Blocks.GRASS_BLOCK))))));
		public static final ConfiguredFeature<?, ?> MINT_POND = register("mint_pond", NeapolitanFeatures.MINT_POND.get().configured(FeatureConfiguration.NONE));
		public static final ConfiguredFeature<?, ?> BANANA_PLANT = register("banana_plant", NeapolitanFeatures.BANANA_PLANT.get().configured(FeatureConfiguration.NONE));

		private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Neapolitan.MOD_ID, name), configuredFeature);
		}

		private static BlockPredicate simplePatchPredicate(List<Block> matchBlocks) {
			BlockPredicate blockpredicate;
			if (!matchBlocks.isEmpty()) {
				blockpredicate = BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(matchBlocks, new BlockPos(0, -1, 0)));
			} else {
				blockpredicate = BlockPredicate.ONLY_IN_AIR_PREDICATE;
			}

			return blockpredicate;
		}
	}

	public static final class NeapolitanPlacedFeatures {
		public static final PlacedFeature PATCH_STRAWBERRY_BUSH = register("patch_strawberry_bush", NeapolitanConfiguredFeatures.PATCH_STRAWBERRY_BUSH.placed(RarityFilter.onAverageOnceEvery(NeapolitanConfig.COMMON.strawberryBushGenerationChance.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final PlacedFeature PATCH_VANILLA_VINE = register("patch_vanilla_vine", NeapolitanConfiguredFeatures.PATCH_VANILLA_VINE.placed(RarityFilter.onAverageOnceEvery(NeapolitanConfig.COMMON.vanillaVineGenerationChance.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final PlacedFeature PATCH_ADZUKI_SPROUTS = register("patch_adzuki_sprouts", NeapolitanConfiguredFeatures.PATCH_ADZUKI_SPROUTS.placed(RarityFilter.onAverageOnceEvery(NeapolitanConfig.COMMON.adzukiSproutsGenerationChance.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		public static final PlacedFeature MINT_POND = register("mint_pond", NeapolitanConfiguredFeatures.MINT_POND.placed(RarityFilter.onAverageOnceEvery(NeapolitanConfig.COMMON.mintPondGenerationChance.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

		public static final PlacedFeature BANANA_PLANT_BEACH = register("banana_plant_beach", NeapolitanConfiguredFeatures.BANANA_PLANT.placed(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final PlacedFeature BANANA_PLANT_JUNGLE = register("banana_plant_jungle", NeapolitanConfiguredFeatures.BANANA_PLANT.placed(PlacementUtils.countExtra(0, 0.25F, 3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final PlacedFeature BANANA_PLANT_RARE = register("banana_plant_rare", NeapolitanConfiguredFeatures.BANANA_PLANT.placed(PlacementUtils.countExtra(0, 0.25F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		public static final PlacedFeature BANANA_PLANT_VERY_RARE = register("banana_plant_very_rare", NeapolitanConfiguredFeatures.BANANA_PLANT.placed(PlacementUtils.countExtra(0, 0.125F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

		public static PlacedFeature register(String name, PlacedFeature placedFeature) {
			return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Neapolitan.MOD_ID, name), placedFeature);
		}
	}
}
