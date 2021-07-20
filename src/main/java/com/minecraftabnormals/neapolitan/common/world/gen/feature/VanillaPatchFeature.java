package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import com.minecraftabnormals.neapolitan.common.block.VanillaVineBlock;
import com.minecraftabnormals.neapolitan.common.block.VanillaVineTopBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class VanillaPatchFeature extends Feature<BlockClusterFeatureConfig> {
	public VanillaPatchFeature(Codec<BlockClusterFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
		BlockPos blockpos;
		if (config.project) {
			blockpos = world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		int i = 0;
		BlockPos.Mutable position = new BlockPos.Mutable();

		for (Direction direction : Direction.values()) {
			for (int j = 0; j < config.tries; ++j) {
				position.setWithOffset(blockpos, random.nextInt(config.xspread + 1) - random.nextInt(config.xspread + 1), random.nextInt(config.yspread + 1) - random.nextInt(config.yspread + 1), random.nextInt(config.zspread + 1) - random.nextInt(config.zspread + 1));
				BlockPos downPosition = position.relative(direction.getOpposite());
				BlockState downState = world.getBlockState(downPosition);

				BlockState vanillaVine = config.stateProvider.getState(random, pos).setValue(VanillaVineTopBlock.FACING, direction);
				BlockState vanillaVinePlant = NeapolitanBlocks.VANILLA_VINE_PLANT.get().defaultBlockState().setValue(VanillaVineBlock.FACING, direction);

				if ((world.isEmptyBlock(position) || config.canReplace && world.getBlockState(position).getMaterial().isReplaceable()) && vanillaVine.canSurvive(world, position) && (config.whitelist.isEmpty() || config.whitelist.contains(downState.getBlock())) && !config.blacklist.contains(downState) && (!config.needWater || world.getFluidState(downPosition.west()).is(FluidTags.WATER) || world.getFluidState(downPosition.east()).is(FluidTags.WATER) || world.getFluidState(downPosition.north()).is(FluidTags.WATER) || world.getFluidState(downPosition.south()).is(FluidTags.WATER))) {
					if (!downState.is(config.stateProvider.getState(random, pos).getBlock())) {
						config.blockPlacer.place(world, position, vanillaVine, random);
						if (world.getBlockState(position.relative(direction)).isAir(world, position.relative(direction))) {
							config.blockPlacer.place(world, position, vanillaVinePlant, random);
							config.blockPlacer.place(world, position.relative(direction), vanillaVine, random);
						}
					}
					++i;
				}
			}
		}

		return i > 0;
	}
}