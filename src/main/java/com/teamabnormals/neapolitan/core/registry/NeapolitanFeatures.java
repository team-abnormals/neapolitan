package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.common.levelgen.feature.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
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
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> MINT_POND = FEATURES.register("mint_pond", () -> new MintPondFeature(NoneFeatureConfiguration.CODEC));

	public static final class NeapolitanConfiguredFeatures {
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_STRAWBERRY_BUSH = createKey("patch_strawberry_bush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_VANILLA_VINE = createKey("patch_vanilla_vine");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ADZUKI_SPROUTS = createKey("patch_adzuki_sprouts");
		public static final ResourceKey<ConfiguredFeature<?, ?>> MINT_POND = createKey("mint_pond");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_PLANT = createKey("banana_plant");

		public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
			register(context, PATCH_STRAWBERRY_BUSH, () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(512, 5, 3, PlacementUtils.filtered(STRAWBERRY_BUSH.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.STRAWBERRY_BUSH.get().defaultBlockState())), simplePatchPredicate(List.of(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT))))));
			register(context, PATCH_VANILLA_VINE, () -> new ConfiguredFeature<>(NeapolitanFeatures.VANILLA_VINE_PATCH.get(), new RandomPatchConfiguration(64, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.VANILLA_VINE.get().defaultBlockState())), BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.ONLY_IN_AIR_PREDICATE)))));
			register(context, PATCH_ADZUKI_SPROUTS, () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(256, 3, 2, PlacementUtils.filtered(ADZUKI_SPROUTS.get(), new SimpleBlockConfiguration(BlockStateProvider.simple(NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState())), simplePatchPredicate(List.of(Blocks.GRASS_BLOCK))))));
			register(context, MINT_POND, () -> new ConfiguredFeature<>(NeapolitanFeatures.MINT_POND.get(), FeatureConfiguration.NONE));
			register(context, BANANA_PLANT, () -> new ConfiguredFeature<>(NeapolitanFeatures.BANANA_PLANT.get(), FeatureConfiguration.NONE));
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
			return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Neapolitan.MOD_ID, name));
		}

		public static void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Supplier<? extends ConfiguredFeature<?, ?>> configuredFeature) {
			context.register(key, configuredFeature.get());
		}
	}

	public static final class NeapolitanPlacedFeatures {
		public static final ResourceKey<PlacedFeature> PATCH_STRAWBERRY_BUSH = createKey("patch_strawberry_bush");
		public static final ResourceKey<PlacedFeature> PATCH_VANILLA_VINE = createKey("patch_vanilla_vine");
		public static final ResourceKey<PlacedFeature> PATCH_ADZUKI_SPROUTS = createKey("patch_adzuki_sprouts");
		public static final ResourceKey<PlacedFeature> MINT_POND = createKey("mint_pond");
		public static final ResourceKey<PlacedFeature> BANANA_PLANT_COMMON = createKey("banana_plant_common");
		public static final ResourceKey<PlacedFeature> BANANA_PLANT_UNCOMMON = createKey("banana_plant_uncommon");
		public static final ResourceKey<PlacedFeature> BANANA_PLANT_RARE = createKey("banana_plant_rare");

		public static void bootstrap(BootstapContext<PlacedFeature> context) {
			register(context, PATCH_STRAWBERRY_BUSH, NeapolitanConfiguredFeatures.PATCH_STRAWBERRY_BUSH, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_VANILLA_VINE, NeapolitanConfiguredFeatures.PATCH_VANILLA_VINE, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_ADZUKI_SPROUTS, NeapolitanConfiguredFeatures.PATCH_ADZUKI_SPROUTS, RarityFilter.onAverageOnceEvery(128), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, MINT_POND, NeapolitanConfiguredFeatures.MINT_POND, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, BANANA_PLANT_COMMON, NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.25F, 3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
			register(context, BANANA_PLANT_UNCOMMON, NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
			register(context, BANANA_PLANT_RARE, NeapolitanConfiguredFeatures.BANANA_PLANT, PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		}

		public static ResourceKey<PlacedFeature> createKey(String name) {
			return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Neapolitan.MOD_ID, name));
		}

		public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureHolder, PlacementModifier... modifiers) {
			context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(configuredFeatureHolder), List.of(modifiers)));
		}
	}
}
