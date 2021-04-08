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
	public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig config) {
		BlockPos blockpos = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos);
		List<Direction> directions = Direction.Plane.HORIZONTAL.getDirectionValues().collect(Collectors.toList());
		boolean spruce = false;

		int i = 0;
		BlockPos.Mutable position = new BlockPos.Mutable();
		List<BlockPos> waterPositions = new ArrayList<>();
		List<BlockPos> mintPositions = new ArrayList<>();

		position.setAndOffset(blockpos, random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
		if (isSafeSpotForWater(world, position)) {
			waterPositions.add(position);
			for (Direction direction : directions) {
				BlockPos offsetPos = position.offset(direction);
				if (isSafeSpotForWater(world, offsetPos)) {
					waterPositions.add(offsetPos);
					BlockPos cornerPos = offsetPos.offset(direction.rotateY());
					if (isSafeSpotForWater(world, cornerPos) && random.nextInt(3) != 0) {
						waterPositions.add(cornerPos);
					}

					for (Direction direction2 : directions) {
						BlockPos offsetPos2 = offsetPos.offset(direction);
						if (isSafeSpotForWater(world, offsetPos2) && random.nextInt(3) == 0) {
							waterPositions.add(offsetPos2);
							BlockPos cornerPos2 = offsetPos2.offset(direction2.rotateY());
							if (isSafeSpotForWater(world, cornerPos2) && random.nextInt(4) != 0) {
								waterPositions.add(cornerPos);
							}
						}
					}
				}
			}

			for (BlockPos waterPos : waterPositions) {
				for (Direction direction : directions) {
					mintPositions.add(waterPos.up().offset(direction));
				}
			}

			for (BlockPos blockPos : waterPositions) placeWater(world, blockPos);
			for (BlockPos blockPos : mintPositions) {
				if (!spruce && random.nextInt(3) == 0) {
					Features.SPRUCE.generate(world, chunkGenerator, random, blockPos);
					spruce = true;
				} else placeMint(world, blockPos, random);
			}
			++i;

		}

		return i > 0;
	}

	private static void placeMint(ISeedReader world, BlockPos pos, Random random) {
		if (world.getBlockState(pos).isAir() && world.getBlockState(pos.down()).isIn(Blocks.GRASS_BLOCK) && random.nextInt(4) == 0)
			world.setBlockState(pos, NeapolitanBlocks.MINT.get().getDefaultState().with(MintBlock.AGE, 4).with(MintBlock.SPROUTS, 1 + random.nextInt(3)), 2);
	}

	private static void placeWater(ISeedReader world, BlockPos pos) {
		world.setBlockState(pos, Blocks.WATER.getDefaultState(), 2);
		if (world.getBlockState(pos.down()).isIn(Blocks.GRASS_BLOCK) || world.getBlockState(pos.down()).isAir())
			world.setBlockState(pos.down(), Blocks.DIRT.getDefaultState(), 2);
	}

	private static boolean isSafeSpotForWater(ISeedReader world, BlockPos pos) {
		return world.getBlockState(pos).isIn(Blocks.GRASS_BLOCK) && !world.getBlockState(pos.west()).isAir() && !world.getBlockState(pos.east()).isAir() && !world.getBlockState(pos.north()).isAir() && !world.getBlockState(pos.south()).isAir();
	}
}