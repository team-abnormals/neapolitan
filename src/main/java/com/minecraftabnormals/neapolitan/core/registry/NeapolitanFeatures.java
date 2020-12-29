package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.minecraftabnormals.neapolitan.common.world.gen.feature.BananaPlantFeature;
import com.minecraftabnormals.neapolitan.common.world.gen.feature.StrawberryPatchFeature;
import com.minecraftabnormals.neapolitan.common.world.gen.feature.VanillaPatchFeature;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NeapolitanFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Neapolitan.MOD_ID);
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> STRAWBERRY_PATCH = FEATURES.register("strawberry_patch", () -> new StrawberryPatchFeature(BlockClusterFeatureConfig.field_236587_a_));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> VANILLA_VINE_PATCH = FEATURES.register("vanilla_patch", () -> new VanillaPatchFeature(BlockClusterFeatureConfig.field_236587_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> BANANA_PLANT = FEATURES.register("banana_plant", () -> new BananaPlantFeature(NoFeatureConfig.field_236558_a_));

	public static final class Configs {
		public static final BlockClusterFeatureConfig STRAWBERRY_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NeapolitanBlocks.STRAWBERRY_BUSH.get().getDefaultState().with(StrawberryBushBlock.TYPE, StrawberryBushBlock.StrawberryType.RED)), SimpleBlockPlacer.PLACER)).tries(512).build();
		public static final BlockClusterFeatureConfig VANILLA_VINE_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NeapolitanBlocks.VANILLA_VINE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(64).build();
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> STRAWBERRY_PATCH = NeapolitanFeatures.STRAWBERRY_PATCH.get().withConfiguration(Configs.STRAWBERRY_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT.chance(22));
		public static final ConfiguredFeature<?, ?> VANILLA_VINE_PATCH = NeapolitanFeatures.VANILLA_VINE_PATCH.get().withConfiguration(Configs.VANILLA_VINE_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT.chance(26));

		public static final ConfiguredFeature<?, ?> BANANA_PLANT = NeapolitanFeatures.BANANA_PLANT.get().withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_BEACH = BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.075F, 1)));
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_JUNGLE = BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.30F, 3)));
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_RARE = BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.25F, 1)));
		public static final ConfiguredFeature<?, ?> BANANA_PLANT_VERY_RARE = BANANA_PLANT.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.15F, 1)));

		private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Neapolitan.MOD_ID, name), configuredFeature);
		}

		public static void registerConfiguredFeatures() {
			register("strawberry_patch", STRAWBERRY_PATCH);
			register("vanilla_vine_patch", VANILLA_VINE_PATCH);
			register("banana_plant", BANANA_PLANT);
			register("banana_plant_beach", BANANA_PLANT_BEACH);
			register("banana_plant_jungle", BANANA_PLANT_JUNGLE);
			register("banana_plant_rare", BANANA_PLANT_RARE);
			register("banana_plant_very_rare", BANANA_PLANT_VERY_RARE);
		}
	}
}
