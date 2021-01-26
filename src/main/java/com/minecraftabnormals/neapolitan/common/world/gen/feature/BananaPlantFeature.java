package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.minecraftabnormals.neapolitan.common.block.BananaFrondBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.*;

public class BananaPlantFeature extends Feature<NoFeatureConfig> {
	public BananaPlantFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig config) {
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

		if (!isValidGround(world, pos.down()))
			return false;

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
				TreeUtil.setForcedState(world, blockPos2, NeapolitanBlocks.BANANA_STALK.get().getDefaultState());
			}
			TreeUtil.setForcedState(world, upFrond, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState());
			if (bundle != null)
				TreeUtil.setForcedState(world, bundle, NeapolitanBlocks.BANANA_BUNDLE.get().getDefaultState());
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				TreeUtil.setForcedState(world, blockPos2, NeapolitanBlocks.SMALL_BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, smallFronds.get(blockPos2)));
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				TreeUtil.setForcedState(world, blockPos2, NeapolitanBlocks.BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, fronds.get(blockPos2)));
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				TreeUtil.setForcedState(world, blockPos2, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, largeFronds.get(blockPos2)));
			}
			if (isGrass(world, pos.down())) {
				TreeUtil.setForcedState(world, pos.down(), Blocks.GRAVEL.getDefaultState());
				for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.getX() - 3, pos.getY() - 2, pos.getZ() - 3, pos.getX() + 3, pos.getY() + 2, pos.getZ() + 3)) {
					if (isGrass(world, blockpos) && random.nextInt(4) == 0 && TreeUtil.isAir(world, blockpos.up()))
						TreeUtil.setForcedState(world, blockpos, Blocks.GRAVEL.getDefaultState());
				}
			}

			return true;
		}

		return false;
	}

	private static boolean isAirAt(IWorldGenerationBaseReader world, BlockPos pos, int size) {
		BlockPos position = pos.up();
		for (int i = 0; i < size + 1; i++) {
			if (!TreeUtil.isAir(world, position))
				return false;
			for (Direction direction : Direction.values()) {
				if (direction.getAxis().isHorizontal()) {
					if (!TreeUtil.isAir(world, position.offset(direction)))
						return false;
				}
			}
			position = position.up();
		}
		return true;
	}

	public static boolean isGrowable(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		return worldIn.hasBlockState(pos, (state) -> {
			return state.isIn(Blocks.GRAVEL) || state.isIn(Blocks.SAND);
		});
	}

	public static boolean isValidGround(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		return worldIn.hasBlockState(pos, (state) -> {
			return state.isIn(Blocks.GRAVEL) || state.isIn(Blocks.SAND) || state.isIn(Blocks.GRASS_BLOCK);
		});
	}

	public static boolean isGrass(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		return worldIn.hasBlockState(pos, (state) -> {
			return state.isIn(Blocks.GRASS_BLOCK);
		});
	}
}