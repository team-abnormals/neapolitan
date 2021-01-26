package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import com.minecraftabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class StrawberryPatchFeature extends Feature<BlockClusterFeatureConfig> {
	public StrawberryPatchFeature(Codec<BlockClusterFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
		BlockState strawberryBush = config.stateProvider.getBlockState(random, pos);

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
			if ((world.isAirBlock(position) || config.isReplaceable && world.getBlockState(position).getMaterial().isReplaceable()) && strawberryBush.isValidPosition(world, position) && (config.whitelist.isEmpty() || config.whitelist.contains(downState.getBlock())) && !config.blacklist.contains(downState) && (!config.requiresWater || world.getFluidState(downPosition.west()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.east()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.north()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.south()).isTagged(FluidTags.WATER))) {
				if (random.nextInt(5) != 0) {
					int age = world.getBlockState(position.down()).isIn(Blocks.COARSE_DIRT) ? 2 : 6;
					config.blockPlacer.place(world, position, strawberryBush.with(StrawberryBushBlock.AGE, age), random);
				} else {
					config.blockPlacer.place(world, position, strawberryBush.with(StrawberryBushBlock.TYPE, StrawberryBushBlock.StrawberryType.NONE).with(StrawberryBushBlock.AGE, 2), random);
					config.blockPlacer.place(world, position.down(), Blocks.COARSE_DIRT.getDefaultState(), random);
				}

				for (Direction direction : Direction.Plane.HORIZONTAL) {
					BlockState offsetState = world.getBlockState(position.offset(direction));
					BlockState offsetDownState = world.getBlockState(downPosition.offset(direction));

					if (!(offsetState.isIn(NeapolitanBlocks.STRAWBERRY_BUSH.get()) && offsetState.get(StrawberryBushBlock.AGE) != 2) && (offsetDownState.isIn(Blocks.DIRT) || offsetDownState.isIn(Blocks.GRASS_BLOCK))) {
						if (random.nextBoolean()) {
							config.blockPlacer.place(world, downPosition.offset(direction), Blocks.COARSE_DIRT.getDefaultState(), random);
						}
					}
				}

				++i;
			}
		}

		return i > 0;
	}
}