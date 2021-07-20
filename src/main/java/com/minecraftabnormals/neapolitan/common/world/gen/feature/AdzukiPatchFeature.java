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
	public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
		BlockState sprouts = config.stateProvider.getState(random, pos);

		BlockPos blockpos;
		if (config.project) {
			blockpos = world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		int i = 0;
		BlockPos.Mutable position = new BlockPos.Mutable();

		for (int j = 0; j < config.tries; ++j) {
			position.setWithOffset(blockpos, random.nextInt(config.xspread + 1) - random.nextInt(config.xspread + 1), random.nextInt(config.yspread + 1) - random.nextInt(config.yspread + 1), random.nextInt(config.zspread + 1) - random.nextInt(config.zspread + 1));
			BlockPos downPosition = position.below();
			BlockState downState = world.getBlockState(downPosition);
			if ((world.isEmptyBlock(position) || config.canReplace && world.getBlockState(position).getMaterial().isReplaceable() && !world.getFluidState(position).is(FluidTags.WATER)) && sprouts.canSurvive(world, position) && (config.whitelist.isEmpty() || config.whitelist.contains(downState.getBlock())) && !config.blacklist.contains(downState) && (!config.needWater || world.getFluidState(downPosition.west()).is(FluidTags.WATER) || world.getFluidState(downPosition.east()).is(FluidTags.WATER) || world.getFluidState(downPosition.north()).is(FluidTags.WATER) || world.getFluidState(downPosition.south()).is(FluidTags.WATER))) {
				config.blockPlacer.place(world, position, sprouts.setValue(AdzukiSproutsBlock.AGE, 6), random);
				config.blockPlacer.place(world, position.below(), Blocks.DIRT.defaultBlockState(), random);
				++i;
			}
		}

		return i > 0;
	}
}