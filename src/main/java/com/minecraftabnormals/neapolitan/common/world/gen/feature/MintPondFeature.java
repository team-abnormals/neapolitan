package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import com.minecraftabnormals.neapolitan.common.block.MintBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MintPondFeature extends Feature<NoFeatureConfig> {
	public MintPondFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig config) {
		BlockPos blockpos = world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);
		List<Direction> directions = Direction.Plane.HORIZONTAL.stream().collect(Collectors.toList());
		boolean spruce = false;

		int i = 0;
		BlockPos.Mutable position = new BlockPos.Mutable();
		List<BlockPos> waterPositions = new ArrayList<>();
		List<BlockPos> mintPositions = new ArrayList<>();

		position.setWithOffset(blockpos, random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
		if (isSafeSpotForWater(world, position)) {
			waterPositions.add(position);
			for (Direction direction : directions) {
				BlockPos offsetPos = position.relative(direction);
				if (isSafeSpotForWater(world, offsetPos)) {
					waterPositions.add(offsetPos);
					BlockPos cornerPos = offsetPos.relative(direction.getClockWise());
					if (isSafeSpotForWater(world, cornerPos) && random.nextInt(3) != 0) {
						waterPositions.add(cornerPos);
					}

					for (Direction direction2 : directions) {
						BlockPos offsetPos2 = offsetPos.relative(direction);
						if (isSafeSpotForWater(world, offsetPos2) && random.nextInt(3) == 0) {
							waterPositions.add(offsetPos2);
							BlockPos cornerPos2 = offsetPos2.relative(direction2.getClockWise());
							if (isSafeSpotForWater(world, cornerPos2) && random.nextInt(4) != 0) {
								waterPositions.add(cornerPos);
							}
						}
					}
				}
			}

			for (BlockPos waterPos : waterPositions) {
				for (Direction direction : directions) {
					mintPositions.add(waterPos.above().relative(direction));
				}
			}

			for (BlockPos blockPos : waterPositions) placeWater(world, blockPos);
			for (BlockPos blockPos : mintPositions) {
				if (!spruce && random.nextInt(3) == 0) {
					Features.SPRUCE.place(world, chunkGenerator, random, blockPos);
					spruce = true;
				} else placeMint(world, blockPos, random);
			}
			++i;

		}

		return i > 0;
	}

	private static void placeMint(ISeedReader world, BlockPos pos, Random random) {
		if (world.getBlockState(pos).isAir() && world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) && random.nextInt(4) == 0)
			world.setBlock(pos, NeapolitanBlocks.MINT.get().defaultBlockState().setValue(MintBlock.AGE, 4).setValue(MintBlock.SPROUTS, 1 + random.nextInt(3)), 2);
	}

	private static void placeWater(ISeedReader world, BlockPos pos) {
		world.setBlock(pos, Blocks.WATER.defaultBlockState(), 2);
		if (world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || world.getBlockState(pos.below()).isAir())
			world.setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), 2);
	}

	private static boolean isSafeSpotForWater(ISeedReader world, BlockPos pos) {
		return world.getBlockState(pos).is(Blocks.GRASS_BLOCK) && !world.getBlockState(pos.west()).isAir() && !world.getBlockState(pos.east()).isAir() && !world.getBlockState(pos.north()).isAir() && !world.getBlockState(pos.south()).isAir();
	}
}