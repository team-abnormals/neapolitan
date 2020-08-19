package com.minecraftabnormals.neapolitan.common.world.gen;

import com.minecraftabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class NeapolitanBiomeFeatures {
    
    public static final BlockClusterFeatureConfig STRAWBERRY_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NeapolitanBlocks.STRAWBERRY_BUSH.get().getDefaultState().with(StrawberryBushBlock.TYPE, StrawberryBushBlock.StrawberryType.RED)), SimpleBlockPlacer.field_236447_c_)).tries(512).build();
    public static final BlockClusterFeatureConfig VANILLA_VINE_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NeapolitanBlocks.VANILLA_VINE.get().getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(48).build();

    public static void generateFeatures() {
        ForgeRegistries.BIOMES.getValues().forEach(NeapolitanBiomeFeatures::generate);
    }

    public static void generate(Biome biome) {
        if (biome.getCategory() == Biome.Category.PLAINS) {
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NeapolitanFeatures.STRAWBERRY_PATCH.get().withConfiguration(STRAWBERRY_PATCH_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(31))));
        }
        
        if (biome.getCategory() == Biome.Category.SAVANNA) {
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(VANILLA_VINE_PATCH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
        }
    }
}
