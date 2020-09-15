package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.minecraftabnormals.neapolitan.common.block.BananaFrondBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.serialization.Codec;
import com.teamabnormals.abnormals_core.core.utils.TreeUtils;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraftforge.common.Tags;

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

		if (!(isGrass(world, pos.down())) || isGrowable(world, pos.down()))
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
				TreeUtils.setForcedState(world, blockPos2, NeapolitanBlocks.BANANA_STALK.get().getDefaultState());
			}
			TreeUtils.setForcedState(world, upFrond, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState());
			if (bundle != null)
				TreeUtils.setForcedState(world, bundle, NeapolitanBlocks.BANANA_BUNDLE.get().getDefaultState());
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				TreeUtils.setForcedState(world, blockPos2, NeapolitanBlocks.SMALL_BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, smallFronds.get(blockPos2)));
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				TreeUtils.setForcedState(world, blockPos2, NeapolitanBlocks.BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, fronds.get(blockPos2)));
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				TreeUtils.setForcedState(world, blockPos2, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState().with(BananaFrondBlock.FACING, largeFronds.get(blockPos2)));
			}
			if (isGrass(world, pos.down())) {
				TreeUtils.setForcedState(world, pos.down(), Blocks.GRAVEL.getDefaultState());
				for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.getX() - 3, pos.getY() - 2, pos.getZ() - 3, pos.getX() + 3, pos.getY() + 2, pos.getZ() + 3)) {
	                if (isGrass(world, blockpos) && random.nextBoolean() && TreeUtils.isAir(world, blockpos.up()))
	                	TreeUtils.setForcedState(world, blockpos, Blocks.GRAVEL.getDefaultState());
	            }
			}
			
			return true;
		}

		return false;
	}

	private static boolean isAirAt(IWorldGenerationBaseReader world, BlockPos pos, int size) {
		BlockPos position = pos.up();
		for (int i = 0; i < size + 1; i++) {
			if (!TreeUtils.isAir(world, position))
				return false;
			for (Direction direction : Direction.values()) {
				if (direction.getAxis().isHorizontal()) {
					if (!TreeUtils.isAir(world, position.offset(direction)))
						return false;
				}
			}
			position = position.up();
		}
		return true;
	}
	
	public static boolean isGrowable(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        return worldIn.hasBlockState(pos, (state) -> {
            return state.isIn(Tags.Blocks.GRAVEL) || state.isIn(Tags.Blocks.SAND);
        });
    }
	
	public static boolean isGrass(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        return worldIn.hasBlockState(pos, (state) -> {
            return state.isIn(Blocks.GRASS_BLOCK);
        });
    }
}