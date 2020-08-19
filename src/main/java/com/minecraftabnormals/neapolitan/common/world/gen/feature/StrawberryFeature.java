package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import java.util.Random;

import com.minecraftabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class StrawberryFeature extends Feature<BlockClusterFeatureConfig> {
    public StrawberryFeature(Codec<BlockClusterFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
        BlockState blockstate = config.stateProvider.getBlockState(random, pos);

        BlockPos blockpos;
        if (config.field_227298_k_) {
            blockpos = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos);
        } else {
            blockpos = pos;
        }

        int i = 0;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (int j = 0; j < config.tryCount; ++j) {
            blockpos$mutable.func_239621_a_(blockpos, random.nextInt(config.xSpread + 1) - random.nextInt(config.xSpread + 1), random.nextInt(config.ySpread + 1) - random.nextInt(config.ySpread + 1), random.nextInt(config.zSpread + 1) - random.nextInt(config.zSpread + 1));
            BlockPos blockpos1 = blockpos$mutable.down();
            BlockState blockstate1 = world.getBlockState(blockpos1);
            if ((world.isAirBlock(blockpos$mutable) || config.isReplaceable && world.getBlockState(blockpos$mutable).getMaterial().isReplaceable()) && blockstate.isValidPosition(world, blockpos$mutable) && (config.whitelist.isEmpty() || config.whitelist.contains(blockstate1.getBlock())) && !config.blacklist.contains(blockstate1) && (!config.requiresWater || world.getFluidState(blockpos1.west()).isTagged(FluidTags.WATER) || world.getFluidState(blockpos1.east()).isTagged(FluidTags.WATER) || world.getFluidState(blockpos1.north()).isTagged(FluidTags.WATER) || world.getFluidState(blockpos1.south()).isTagged(FluidTags.WATER))) {
                if (random.nextInt(4) != 0) {
                    config.blockPlacer.func_225567_a_(world, blockpos$mutable, blockstate.with(StrawberryBushBlock.AGE, 6), random);
                } else {
                    config.blockPlacer.func_225567_a_(world, blockpos$mutable, blockstate.with(StrawberryBushBlock.TYPE, StrawberryBushBlock.StrawberryType.NONE).with(StrawberryBushBlock.AGE, 2), random);
                    config.blockPlacer.func_225567_a_(world, blockpos$mutable.down(), Blocks.COARSE_DIRT.getDefaultState(), random);
                }
                ++i;
            }
        }

        return i > 0;
    }
}