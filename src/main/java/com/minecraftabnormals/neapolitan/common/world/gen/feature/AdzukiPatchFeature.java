package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import com.minecraftabnormals.neapolitan.common.block.AdzukiSproutsBlock;
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

import java.util.Random;

public class AdzukiPatchFeature extends Feature<BlockClusterFeatureConfig> {
	public AdzukiPatchFeature(Codec<BlockClusterFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
		BlockState sprouts = config.stateProvider.getBlockState(random, pos);

		BlockPos blockpos;
		if (config.field_227298_k_) {
			blockpos = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		int i = 0;
		BlockPos.Mutable position = new BlockPos.Mutable();

		for (int j = 0; j < config.tryCount; ++j) {
			position.setAndOffset(blockpos, random.nextInt(config.xSpread + 1) - random.nextInt(config.xSpread + 1), random.nextInt(config.ySpread + 1) - random.nextInt(config.ySpread + 1), random.nextInt(config.zSpread + 1) - random.nextInt(config.zSpread + 1));
			BlockPos downPosition = position.down();
			BlockState downState = world.getBlockState(downPosition);
			if ((world.isAirBlock(position) || config.isReplaceable && world.getBlockState(position).getMaterial().isReplaceable() && !world.getFluidState(position).isTagged(FluidTags.WATER)) && sprouts.isValidPosition(world, position) && (config.whitelist.isEmpty() || config.whitelist.contains(downState.getBlock())) && !config.blacklist.contains(downState) && (!config.requiresWater || world.getFluidState(downPosition.west()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.east()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.north()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.south()).isTagged(FluidTags.WATER))) {
				config.blockPlacer.place(world, position, sprouts.with(AdzukiSproutsBlock.AGE, 6), random);
				config.blockPlacer.place(world, position.down(), Blocks.DIRT.getDefaultState(), random);
				++i;
			}
		}

		return i > 0;
	}
}