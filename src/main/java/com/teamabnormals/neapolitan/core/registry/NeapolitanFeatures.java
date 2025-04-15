package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.common.levelgen.feature.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class NeapolitanFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Neapolitan.MOD_ID);
	public static final DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> STRAWBERRY_BUSH = FEATURES.register("strawberry_bush", () -> new StrawberryBushFeature(SimpleBlockConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<RandomPatchConfiguration>> VANILLA_VINE_PATCH = FEATURES.register("vanilla_patch", () -> new VanillaPatchFeature(RandomPatchConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BANANA_PLANT = FEATURES.register("banana_plant", () -> new BananaPlantFeature(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> ADZUKI_SPROUTS = FEATURES.register("adzuki_sprouts", () -> new AdzukiSproutsFeature(SimpleBlockConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MINT_POND = FEATURES.register("mint_pond", () -> new MintPondFeature(NoneFeatureConfiguration.CODEC));

	public static final class NeapolitanConfiguredFeatures {
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_STRAWBERRY_BUSH = createKey("patch_strawberry_bush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_STRAWBERRY_BUSH_SMALL = createKey("patch_strawberry_bush_small");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_VANILLA_VINE = createKey("patch_vanilla_vine");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ADZUKI_SPROUTS = createKey("patch_adzuki_sprouts");
		public static final ResourceKey<ConfiguredFeature<?, ?>> MINT_POND = createKey("mint_pond");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_PLANT = createKey("banana_plant");
		public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_CORNFLOWER = createKey("single_cornflower");
		public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_STRAWBERRY_FIELDS = createKey("trees_strawberry_fields");

		public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
			HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

			register(context, PATCH_STRAWBERRY_BUSH, Feature.RANDOM_PATCH, new RandomPatchConfiguration(512, 5, 3, PlacementUtils.filtered(STRAWBERRY_BUSH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.STRAWBERRY_BUSH.get().defaultBlockState())), simplePatchPredicate(List.of(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT)))));
			register(context, PATCH_STRAWBERRY_BUSH_SMALL, Feature.RANDOM_PATCH, new RandomPatchConfiguration(256, 2, 2, PlacementUtils.filtered(STRAWBERRY_BUSH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.STRAWBERRY_BUSH.get().defaultBlockState())), simplePatchPredicate(List.of(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT)))));
			register(context, PATCH_VANILLA_VINE, NeapolitanFeatures.VANILLA_VINE_PATCH.get(), new RandomPatchConfiguration(64, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.VANILLA_VINE.get().defaultBlockState())), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.ONLY_IN_AIR_PREDICATE))));
			register(context, PATCH_ADZUKI_SPROUTS, Feature.RANDOM_PATCH, new RandomPatchConfiguration(256, 3, 2, PlacementUtils.filtered(ADZUKI_SPROUTS.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState())), simplePatchPredicate(List.of(Blocks.GRASS_BLOCK)))));
			register(context, MINT_POND, NeapolitanFeatures.MINT_POND.get(), FeatureConfiguration.NONE);
			register(context, BANANA_PLANT, NeapolitanFeatures.BANANA_PLANT.get(), FeatureConfiguration.NONE);
			register(context, SINGLE_CORNFLOWER, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.CORNFLOWER.defaultBlockState())));
			register(context, TREES_STRAWBERRY_FIELDS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.FANCY_OAK_BEES_002), 0.1F)), placedFeatures.getOrThrow(TreePlacements.OAK_BEES_002)));
		}

		private static BlockPredicate simplePatchPredicate(List<Block> matchBlocks) {
			BlockPredicate blockpredicate;
			if (!matchBlocks.isEmpty()) {
				blockpredicate = BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), matchBlocks));
			} else {
				blockpredicate = BlockPredicate.ONLY_IN_AIR_PREDICATE;
			}

			return blockpredicate;
		}

		public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
			return ResourceKey.create(Registries.CONFIGURED_FEATURE, Neapolitan.location(name));
		}

		public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
			context.register(key, new ConfiguredFeature<>(feature, config));
		}
	}

	public static final class NeapolitanPlacedFeatures {
		public static final ResourceKey<PlacedFeature> PATCH_STRAWBERRY_BUSH = createKey("patch_strawberry_bush");
		public static final ResourceKey<PlacedFeature> PATCH_STRAWBERRY_BUSH_COMMON = createKey("patch_strawberry_bush_common");
		public static final ResourceKey<PlacedFeature> PATCH_STRAWBERRY_BUSH_SMALL = createKey("patch_strawberry_bush_small");
		public static final ResourceKey<PlacedFeature> PATCH_VANILLA_VINE = createKey("patch_vanilla_vine");
		public static final ResourceKey<PlacedFeature> PATCH_ADZUKI_SPROUTS = createKey("patch_adzuki_sprouts");
		public static final ResourceKey<PlacedFeature> MINT_POND = createKey("mint_pond");
		public static final ResourceKey<PlacedFeature> BANANA_PLANT_COMMON = createKey("banana_plant_common");
		public static final ResourceKey<PlacedFeature> BANANA_PLANT_UNCOMMON = createKey("banana_plant_uncommon");
		public static final ResourceKey<PlacedFeature> BANANA_PLANT_RARE = createKey("banana_plant_rare");
		public static final ResourceKey<PlacedFeature> SINGLE_CORNFLOWER = createKey("single_cornflower");
		public static final ResourceKey<PlacedFeature> TREES_STRAWBERRY_FIELDS = createKey("trees_strawberry_fields");

		public static void bootstrap(BootstrapContext<PlacedFeature> context) {
			register(context, PATCH_STRAWBERRY_BUSH, NeapolitanConfiguredFeatures.PATCH_STRAWBERRY_BUSH, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_STRAWBERRY_BUSH_COMMON, NeapolitanConfiguredFeatures.PATCH_STRAWBERRY_BUSH, RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_STRAWBERRY_BUSH_SMALL, NeapolitanConfiguredFeatures.PATCH_STRAWBERRY_BUSH_SMALL, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_VANILLA_VINE, NeapolitanConfiguredFeatures.PATCH_VANILLA_VINE, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_ADZUKI_SPROUTS, NeapolitanConfiguredFeatures.PATCH_ADZUKI_SPROUTS, RarityFilter.onAverageOnceEvery(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, MINT_POND, NeapolitanConfiguredFeatures.MINT_POND, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, BANANA_PLANT_COMMON, NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.25F, 3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
			register(context, BANANA_PLANT_UNCOMMON, NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
			register(context, BANANA_PLANT_RARE, NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
			register(context, SINGLE_CORNFLOWER, NeapolitanConfiguredFeatures.SINGLE_CORNFLOWER, NoiseThresholdCountPlacement.of(-0.8D, 15, 3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CORNFLOWER.defaultBlockState(), BlockPos.ZERO))));
			register(context, TREES_STRAWBERRY_FIELDS, NeapolitanConfiguredFeatures.TREES_STRAWBERRY_FIELDS, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
		}

		public static ResourceKey<PlacedFeature> createKey(String name) {
			return ResourceKey.create(Registries.PLACED_FEATURE, Neapolitan.location(name));
		}

		public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
			context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature), modifiers));
		}

		public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureHolder, PlacementModifier... modifiers) {
			context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(configuredFeatureHolder), List.of(modifiers)));
		}
	}
}
