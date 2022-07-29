package com.teamabnormals.neapolitan.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.neapolitan.common.levelgen.feature.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class NeapolitanFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Neapolitan.MOD_ID);
	public static final RegistryObject<Feature<SimpleBlockConfiguration>> STRAWBERRY_BUSH = FEATURES.register("strawberry_bush", () -> new StrawberryBushFeature(SimpleBlockConfiguration.CODEC));
	public static final RegistryObject<Feature<RandomPatchConfiguration>> VANILLA_VINE_PATCH = FEATURES.register("vanilla_patch", () -> new VanillaPatchFeature(RandomPatchConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> BANANA_PLANT = FEATURES.register("banana_plant", () -> new BananaPlantFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<SimpleBlockConfiguration>> ADZUKI_SPROUTS = FEATURES.register("adzuki_sprouts", () -> new AdzukiSproutsFeature(SimpleBlockConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> MINT_POND = FEATURES.register("mind_pond", () -> new MintPondFeature(NoneFeatureConfiguration.CODEC));

	public static final class NeapolitanConfiguredFeatures {
		public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Neapolitan.MOD_ID);

		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_STRAWBERRY_BUSH = register("patch_strawberry_bush", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(512, 5, 3, PlacementUtils.filtered(STRAWBERRY_BUSH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.STRAWBERRY_BUSH.get().defaultBlockState())), simplePatchPredicate(List.of(Blocks.GRASS_BLOCK))))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_VANILLA_VINE = register("patch_vanilla_vine", () -> new ConfiguredFeature<>(NeapolitanFeatures.VANILLA_VINE_PATCH.get(), new RandomPatchConfiguration(64, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.VANILLA_VINE.get().defaultBlockState())), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.ONLY_IN_AIR_PREDICATE)))));
		public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_ADZUKI_SPROUTS = register("patch_adzuki_sprouts", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(256, 3, 2, PlacementUtils.filtered(ADZUKI_SPROUTS.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState())), simplePatchPredicate(List.of(Blocks.GRASS_BLOCK))))));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> MINT_POND = register("mint_pond", () -> new ConfiguredFeature<>(NeapolitanFeatures.MINT_POND.get(), FeatureConfiguration.NONE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> BANANA_PLANT = register("banana_plant", () -> new ConfiguredFeature<>(NeapolitanFeatures.BANANA_PLANT.get(), FeatureConfiguration.NONE));

		private static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String name, Supplier<ConfiguredFeature<FC, F>> feature) {
			return CONFIGURED_FEATURES.register(name, feature);
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
	}

	public static final class NeapolitanPlacedFeatures {
		public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Neapolitan.MOD_ID);

		public static final RegistryObject<PlacedFeature> PATCH_STRAWBERRY_BUSH = register("patch_strawberry_bush", NeapolitanConfiguredFeatures.PATCH_STRAWBERRY_BUSH, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> PATCH_VANILLA_VINE = register("patch_vanilla_vine", NeapolitanConfiguredFeatures.PATCH_VANILLA_VINE, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> PATCH_ADZUKI_SPROUTS = register("patch_adzuki_sprouts", NeapolitanConfiguredFeatures.PATCH_ADZUKI_SPROUTS, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> MINT_POND = register("mint_pond", NeapolitanConfiguredFeatures.MINT_POND, RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		public static final RegistryObject<PlacedFeature> BANANA_PLANT_BEACH = register("banana_plant_beach", NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> BANANA_PLANT_JUNGLE = register("banana_plant_jungle", NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.25F, 3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> BANANA_PLANT_RARE = register("banana_plant_rare", NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.25F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> BANANA_PLANT_VERY_RARE = register("banana_plant_very_rare", NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.125F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		@SuppressWarnings("unchecked")
		private static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
			return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) feature.getHolder().get(), ImmutableList.copyOf(placementModifiers)));
		}
	}
}
