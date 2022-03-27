package com.teamabnormals.neapolitan.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.neapolitan.common.block.MintBlock;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MintPondFeature extends Feature<NoneFeatureConfiguration> {
	public MintPondFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		Random random = context.random();
		BlockPos pos = context.origin();

		BlockPos blockpos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
		List<Direction> directions = Direction.Plane.HORIZONTAL.stream().collect(Collectors.toList());
		boolean spruce = false;

		int i = 0;
		BlockPos.MutableBlockPos position = new BlockPos.MutableBlockPos();
		List<BlockPos> waterPositions = new ArrayList<>();
		List<BlockPos> mintPositions = new ArrayList<>();

		position.setWithOffset(blockpos, random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
		if (isSafeSpotForWater(level, position)) {
			waterPositions.add(position);
			for (Direction direction : directions) {
				BlockPos offsetPos = position.relative(direction);
				if (isSafeSpotForWater(level, offsetPos)) {
					waterPositions.add(offsetPos);
					BlockPos cornerPos = offsetPos.relative(direction.getClockWise());
					if (isSafeSpotForWater(level, cornerPos) && random.nextInt(3) != 0) {
						waterPositions.add(cornerPos);
					}

					for (Direction direction2 : directions) {
						BlockPos offsetPos2 = offsetPos.relative(direction);
						if (isSafeSpotForWater(level, offsetPos2) && random.nextInt(3) == 0) {
							waterPositions.add(offsetPos2);
							BlockPos cornerPos2 = offsetPos2.relative(direction2.getClockWise());
							if (isSafeSpotForWater(level, cornerPos2) && random.nextInt(4) != 0) {
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

			for (BlockPos blockPos : waterPositions) placeWater(level, blockPos);
			for (BlockPos blockPos : mintPositions) {
				if (!spruce && random.nextInt(3) == 0) {
					TreeFeatures.SPRUCE.value().place(level, context.chunkGenerator(), random, blockPos);
					spruce = true;
				} else placeMint(level, blockPos, random);
			}
			++i;

		}

		return i > 0;
	}

	private static void placeMint(WorldGenLevel world, BlockPos pos, Random random) {
		if (world.getBlockState(pos).isAir() && world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) && random.nextInt(4) == 0)
			world.setBlock(pos, NeapolitanBlocks.MINT.get().defaultBlockState().setValue(MintBlock.AGE, 4).setValue(MintBlock.SPROUTS, 1 + random.nextInt(3)), 2);
	}

	private static void placeWater(WorldGenLevel world, BlockPos pos) {
		world.setBlock(pos, Blocks.WATER.defaultBlockState(), 2);
		if (world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || world.getBlockState(pos.below()).isAir())
			world.setBlock(pos.below(), Blocks.DIRT.defaultBlockState(), 2);
	}

	private static boolean isSafeSpotForWater(WorldGenLevel world, BlockPos pos) {
		return world.getBlockState(pos).is(Blocks.GRASS_BLOCK) && !world.getBlockState(pos.west()).isAir() && !world.getBlockState(pos.east()).isAir() && !world.getBlockState(pos.north()).isAir() && !world.getBlockState(pos.south()).isAir();
	}
}