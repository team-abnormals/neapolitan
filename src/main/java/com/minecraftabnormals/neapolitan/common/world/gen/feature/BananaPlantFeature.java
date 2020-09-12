package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.minecraftabnormals.neapolitan.common.block.BananaFrondBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class BananaPlantFeature extends Feature<NoFeatureConfig> {
	public BananaPlantFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig config) {
		BlockPos blockPos = pos;
		List<BlockPos> stalks = new ArrayList<>();
		BlockPos upFrond = null;
		BlockPos bundle = null;
		int size = 3 + random.nextInt(4);

		Map<BlockPos, Direction> smallFronds = new HashMap<>();
		Map<BlockPos, Direction> fronds = new HashMap<>();
		Map<BlockPos, Direction> largeFronds = new HashMap<>();

		for (int i = 0; i < size; i++) {
			stalks.add(blockPos);
			blockPos = blockPos.up();
		}

		upFrond = (blockPos);
		int i = 0;

		BlockState down = world.getBlockState(pos.down());
		if (!(down.isIn(Blocks.SAND) || down.isIn(Blocks.GRAVEL) || down.isIn(Blocks.DIRT) || down.isIn(Blocks.GRASS_BLOCK) || down.isIn(Blocks.PODZOL)))
			return false;
		if (down.isIn(Blocks.GRASS_BLOCK) || down.isIn(Blocks.PODZOL))
			world.setBlockState(pos.down(), Blocks.GRAVEL.getDefaultState(), 18);

		for (BlockPos stalk : stalks) {
			if (i >= size - 3) {
				for (Direction direction : Direction.values()) {
					if (direction.getAxis().isHorizontal()) {
						if (i == size - 1) {
							if (random.nextInt(4) != 0) {
								largeFronds.put(stalk.offset(direction), direction);
							} else {
								fronds.put(stalk.offset(direction), direction);
							}
						} else if (i == size - 2) {
							if (random.nextBoolean()) {
								fronds.put(stalk.offset(direction), direction);
							} else {
								if (random.nextBoolean() && bundle == null) {
									bundle = stalk.offset(direction);
								} else {
									smallFronds.put(stalk.offset(direction), direction);
								}
							}
						} else if (i == size - 3) {
							if (random.nextInt(3) != 0) {
								smallFronds.put(stalk.offset(direction), direction);
							}
						}
					}
				}
			}
			i += 1;
		}

		if (isAirAt(world, pos, size) && pos.getY() < world.getHeight() - size) {
			for (BlockPos blockPos2 : stalks) {
				world.setBlockState(blockPos2, NeapolitanBlocks.BANANA_STALK.get().getDefaultState(), 18);
			}
			world.setBlockState(upFrond, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState(), 18);
			if (bundle != null)
				world.setBlockState(bundle, NeapolitanBlocks.BANANA_BUNDLE.get().getDefaultState(), 18);
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				world.setBlockState(blockPos2, NeapolitanBlocks.SMALL_BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, smallFronds.get(blockPos2)), 18);
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				world.setBlockState(blockPos2, NeapolitanBlocks.BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, fronds.get(blockPos2)), 18);
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				world.setBlockState(blockPos2, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, largeFronds.get(blockPos2)), 18);
			}
			return true;
		}

		return false;
	}

	private static boolean isAirAt(IWorldGenerationBaseReader world, BlockPos pos, int size) {
		BlockPos position = pos.up();
		for (int i = 0; i < size + 1; i++) {
			if (!isAir(world, position))
				return false;
			for (Direction direction : Direction.values()) {
				if (direction.getAxis().isHorizontal()) {
					if (!isAir(world, position.offset(direction)))
						return false;
				}
			}
			position = position.up();
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public static boolean isAir(IWorldGenerationBaseReader world, BlockPos pos) {
		if (!(world instanceof IBlockReader)) {
			return world.hasBlockState(pos, BlockState::isAir);
		} else {
			return world.hasBlockState(pos, state -> state.isAir((net.minecraft.world.IBlockReader) world, pos));
		}
	}
}