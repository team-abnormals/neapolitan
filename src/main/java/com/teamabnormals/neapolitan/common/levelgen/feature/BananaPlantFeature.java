package com.teamabnormals.neapolitan.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import com.teamabnormals.neapolitan.common.block.BananaFrondBlock;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BananaPlantFeature extends Feature<NoneFeatureConfiguration> {
	public BananaPlantFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();

		BlockPos blockPos = pos;
		List<BlockPos> stalks = new ArrayList<>();
		int size = 3 + random.nextInt(4);

		Map<BlockPos, Direction> smallFronds = new HashMap<>();
		Map<BlockPos, Direction> fronds = new HashMap<>();
		Map<BlockPos, Direction> largeFronds = new HashMap<>();

		for (int i = 0; i < size; i++) {
			stalks.add(blockPos);
			blockPos = blockPos.above();
		}

		BlockPos bundle = null;
		BlockPos upFrond = blockPos;
		int i = 0;

		if (!isValidGround(level, pos.below()))
			return false;

		for (BlockPos stalk : stalks) {
			if (i >= size - 3) {
				for (Direction direction : Direction.values()) {
					if (direction.getAxis().isHorizontal()) {
						if (i == size - 1) {
							if (random.nextInt(4) != 0) {
								largeFronds.put(stalk.relative(direction), direction);
							} else {
								fronds.put(stalk.relative(direction), direction);
							}
						} else if (i == size - 2) {
							if (random.nextBoolean()) {
								fronds.put(stalk.relative(direction), direction);
							} else {
								if (random.nextBoolean() && bundle == null) {
									bundle = stalk.relative(direction);
								} else {
									smallFronds.put(stalk.relative(direction), direction);
								}
							}
						} else if (i == size - 3) {
							if (random.nextInt(3) != 0) {
								smallFronds.put(stalk.relative(direction), direction);
							}
						}
					}
				}
			}
			i += 1;
		}

		if (isAirAt(level, pos, size) && pos.getY() < level.getMaxBuildHeight() - size) {
			for (BlockPos blockPos2 : stalks) {
				level.setBlock(blockPos2, NeapolitanBlocks.BANANA_STALK.get().defaultBlockState(), 19);
			}
			level.setBlock(upFrond, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState(), 19);
			if (bundle != null) {
				level.setBlock(bundle, NeapolitanBlocks.BANANA_BUNDLE.get().defaultBlockState(), 19);
				if (random.nextDouble() < NeapolitanConfig.COMMON.chimpanzeeGroupChance.get() && level.getBiome(pos).containsTag(NeapolitanBiomeTags.HAS_CHIMPANZEE)) {
					spawnChimps(level, pos);
				}
			}
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.SMALL_BANANA_FROND.get().defaultBlockState().setValue(BananaFrondBlock.FACING, smallFronds.get(blockPos2)), 19);
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState().setValue(BananaFrondBlock.FACING, fronds.get(blockPos2)), 19);
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState().setValue(BananaFrondBlock.FACING, largeFronds.get(blockPos2)), 19);
			}
			if (isGrass(level, pos.below())) {
				level.setBlock(pos.below(), Blocks.GRAVEL.defaultBlockState(), 19);
				for (BlockPos blockpos : BlockPos.betweenClosed(pos.getX() - 3, pos.getY() - 2, pos.getZ() - 3, pos.getX() + 3, pos.getY() + 2, pos.getZ() + 3)) {
					if (isGrass(level, blockpos) && level.isStateAtPosition(blockpos.above(), BlockStateBase::isAir) && random.nextInt(3) != 0)
						level.setBlock(blockpos, Blocks.GRAVEL.defaultBlockState(), 19);
				}
			}

			return true;
		}

		return false;
	}

	private static void spawnChimps(WorldGenLevel level, BlockPos pos) {
		RandomSource random = level.getRandom();
		int minSpawnAttempts = NeapolitanConfig.COMMON.chimpanzeeMinSpawnAttempts.get();
		int maxSpawnAttempts = NeapolitanConfig.COMMON.chimpanzeeMaxSpawnAttempts.get();
		if (maxSpawnAttempts < minSpawnAttempts || maxSpawnAttempts <= 0 || minSpawnAttempts < 0) return;
		int spawnCount = minSpawnAttempts + random.nextInt(maxSpawnAttempts - minSpawnAttempts);

		int spawnedChimps = 0;
		for (int i = 0; i < spawnCount; ++i) {
			int spawnRange = 4;

			double d0 = (double) pos.getX() + (random.nextDouble() - random.nextDouble()) * (double) spawnRange + 0.5D;
			double d1 = pos.getY() + random.nextInt(3) - 1;
			double d2 = (double) pos.getZ() + (random.nextDouble() - random.nextDouble()) * (double) spawnRange + 0.5D;
			if (level.noCollision(NeapolitanEntityTypes.CHIMPANZEE.get().getAABB(d0, d1, d2))) {
				if (spawnedChimps < NeapolitanConfig.COMMON.chimpanzeeMaxGroupSize.get() && SpawnPlacements.checkSpawnRules(NeapolitanEntityTypes.CHIMPANZEE.get(), level, MobSpawnType.STRUCTURE, new BlockPos(d0, d1, d2), level.getRandom())) {
					Chimpanzee chimp = NeapolitanEntityTypes.CHIMPANZEE.get().create(level.getLevel());
					if (chimp != null) {
						chimp.moveTo(d0, d1, d2, level.getRandom().nextFloat() * 360.0F, 0.0F);
						chimp.finalizeSpawn(level, level.getCurrentDifficultyAt(chimp.blockPosition()), MobSpawnType.STRUCTURE, null, null);
						chimp.setBaby(random.nextInt(4) == 0);
						level.addFreshEntity(chimp);
						chimp.spawnAnim();
						spawnedChimps++;
					}
				}
			}
		}
	}

	private static boolean isAirAt(LevelSimulatedReader level, BlockPos origin, int size) {
		BlockPos pos = origin.above();
		for (int i = 0; i < size + 1; i++) {
			if (!TreeFeature.isAirOrLeaves(level, pos))
				return false;
			for (Direction direction : Direction.values()) {
				if (direction.getAxis().isHorizontal()) {
					if (!level.isStateAtPosition(pos.relative(direction), BlockStateBase::isAir))
						return false;
				}
			}
			pos = pos.above();
		}
		return true;
	}

	public static boolean isValidGround(LevelSimulatedReader worldIn, BlockPos pos) {
		return worldIn.isStateAtPosition(pos, (state) -> state.is(Blocks.GRAVEL) || state.is(Blocks.SAND) || state.is(Blocks.GRASS_BLOCK));
	}

	public static boolean isGrass(LevelSimulatedReader worldIn, BlockPos pos) {
		return worldIn.isStateAtPosition(pos, (state) -> state.is(Blocks.GRASS_BLOCK));
	}
}