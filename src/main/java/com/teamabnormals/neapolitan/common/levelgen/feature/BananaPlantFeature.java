package com.teamabnormals.neapolitan.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.neapolitan.common.block.BananaFrondBlock;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.other.NeapolitanLootTables;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.Plane;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
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
								if (random.nextInt(4) == 0 && bundle == null) {
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
			boolean canSpawnChimps = level.getBiome(pos).is(NeapolitanBiomeTags.HAS_CHIMPANZEE);
			boolean suspicious = canSpawnChimps && isGrass(level, pos.below()) && NeapolitanConfig.COMMON.suspiciousBananaPlants.get() && random.nextFloat() < NeapolitanConfig.COMMON.suspiciousBananaPlantChance.get();

			for (BlockPos blockPos2 : stalks) {
				boolean carved = suspicious && random.nextBoolean();
				level.setBlock(blockPos2, (carved ? NeapolitanBlocks.CARVED_BANANA_STALK : NeapolitanBlocks.BANANA_STALK).get().defaultBlockState(), 19);
			}
			level.setBlock(upFrond, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState().setValue(BananaFrondBlock.SIZE, 3), 19);
			if (bundle != null) {
				level.setBlock(bundle, NeapolitanBlocks.BANANA_BUNDLE.get().defaultBlockState(), 19);
				if (random.nextDouble() < NeapolitanConfig.COMMON.chimpanzeeGroupChance.get() && canSpawnChimps) {
					spawnChimps(level, pos);
				}
			}
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState().setValue(BananaFrondBlock.SIZE, 1).setValue(BananaFrondBlock.FACING, smallFronds.get(blockPos2)), 19);
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState().setValue(BananaFrondBlock.SIZE, 2).setValue(BananaFrondBlock.FACING, fronds.get(blockPos2)), 19);
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState().setValue(BananaFrondBlock.SIZE, 3).setValue(BananaFrondBlock.FACING, largeFronds.get(blockPos2)), 19);
			}
			if (isGrass(level, pos.below())) {
				level.setBlock(pos.below(), Blocks.GRAVEL.defaultBlockState(), 19);

				boolean chimpHead = suspicious && random.nextFloat() < 0.25F;

				int horizontalRange = (suspicious ? 3 : 2) + random.nextInt(2);
				int verticalMin = suspicious ? -8 : -2;

				int rareSusGravel = 0;
				int commonSusGravel = 0;

				int rareSusGravelMax = NeapolitanConfig.COMMON.rareSuspiciousGravelMin.get() + random.nextInt(2);
				int susGravelAmount = 8 + random.nextInt(3) + random.nextInt(2);

				if (chimpHead) {
					int moreGravel = 2 + random.nextInt(3);
					rareSusGravelMax += moreGravel;
					susGravelAmount += moreGravel - 1;

					Direction facing = Plane.HORIZONTAL.getRandomDirection(random);
					generateChimpHead(level, pos
							.relative(facing, random.nextInt(2))
							.relative(facing.getClockWise(), 1 + random.nextInt(2))
							.below(3 + random.nextInt(3)), facing, random);
				}


				for (int x = -horizontalRange; x <= horizontalRange; x++) {
					for (int y = verticalMin; y < 2; y++) {
						for (int z = -horizontalRange; z <= horizontalRange; z++) {
							if (Mth.abs(x) == Math.abs(z) && Mth.abs(x) == horizontalRange) {
								continue;
							}

							if (y < -3 && (Math.abs(x) >= horizontalRange || Math.abs(z) >= horizontalRange)) {
								continue;
							}

							if (y < -6 && (Math.abs(x) >= horizontalRange - 1 || Math.abs(z) >= horizontalRange - 1)) {
								continue;
							}

							BlockPos offsetPos = pos.offset(x, y, z);
							int dist = (int) Mth.sqrt((float) offsetPos.distSqr(pos));
							int clamped = Math.max(1, Math.min((y >= -1 || (Math.abs(x) < 3 && Math.abs(z) < 3)) ? 3 : dist, dist)) + (suspicious ? 0 : random.nextInt(2));
							if (random.nextInt(clamped) == 0 && ((suspicious && isStone(level.getBlockState(offsetPos))) || isDirt(level, offsetPos))) {
								if (!suspicious) {
									level.setBlock(offsetPos, Blocks.GRAVEL.defaultBlockState(), 19);
								} else {
									if (commonSusGravel + rareSusGravel < susGravelAmount && random.nextFloat() < (0.05F * (Math.abs(y) + 1))) {
										level.setBlock(offsetPos, Blocks.SUSPICIOUS_GRAVEL.defaultBlockState(), 19);

										boolean rare = (rareSusGravel < rareSusGravelMax && random.nextInt(3) == 0) || commonSusGravel == susGravelAmount - rareSusGravelMax;
										if (rare) {
											rareSusGravel++;
										} else {
											commonSusGravel++;
										}

										level.getBlockEntity(offsetPos, BlockEntityType.BRUSHABLE_BLOCK).ifPresent((block) -> block.setLootTable(rare ? NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_RARE : NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_COMMON, offsetPos.asLong()));
									} else {
										level.setBlock(offsetPos, Blocks.GRAVEL.defaultBlockState(), 19);
									}
								}

								if (!level.isStateAtPosition(offsetPos.above(), state -> state.canSurvive(level, offsetPos.above()))) {
									level.setBlock(offsetPos.above(), Blocks.AIR.defaultBlockState(), 19);
								}
							}
						}
					}
				}
			}

			return true;
		}

		return false;
	}

	private static void generateChimpHead(WorldGenLevel level, BlockPos origin, Direction facing, RandomSource random) {
		BlockPos.betweenClosedStream(origin, origin.below(3).relative(facing.getOpposite(), 2).relative(facing.getCounterClockWise(), 3)).map(BlockPos::immutable).forEach(
				pos -> placeMossyBlock(level, random, null, pos, 0, 0, 0, Blocks.COBBLESTONE.defaultBlockState())
		);

		placeMossyBlock(level, random, facing, origin, -1, -1, -1, Blocks.COBBLESTONE.defaultBlockState());
		placeMossyBlock(level, random, facing, origin, 4, -1, -1, Blocks.COBBLESTONE.defaultBlockState());

		placeMossyBlock(level, random, facing, origin, -1, 0, -1, Blocks.COBBLESTONE_SLAB.defaultBlockState());
		placeMossyBlock(level, random, facing, origin, 4, 0, -1, Blocks.COBBLESTONE_SLAB.defaultBlockState());

		placeMossyBlock(level, random, facing, origin, 1, -1, -1, Blocks.EMERALD_BLOCK.defaultBlockState());
		placeMossyBlock(level, random, facing, origin, 2, -1, -1, Blocks.EMERALD_BLOCK.defaultBlockState());
		placeMossyBlock(level, random, facing, origin, 1, -1, 0, Blocks.COBBLESTONE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, facing.getCounterClockWise()));
		placeMossyBlock(level, random, facing, origin, 2, -1, 0, Blocks.COBBLESTONE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, facing.getClockWise()));

		placeMossyBlock(level, random, facing, origin, 1, -1, 1, Blocks.COBBLESTONE_SLAB.defaultBlockState());
		placeMossyBlock(level, random, facing, origin, 2, -1, 1, Blocks.COBBLESTONE_SLAB.defaultBlockState());
		placeMossyBlock(level, random, facing, origin, 1, -2, 1, Blocks.COBBLESTONE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, facing.getClockWise()).setValue(StairBlock.HALF, Half.TOP));
		placeMossyBlock(level, random, facing, origin, 2, -2, 1, Blocks.COBBLESTONE_STAIRS.defaultBlockState().setValue(StairBlock.FACING, facing.getCounterClockWise()).setValue(StairBlock.HALF, Half.TOP));
		placeMossyBlock(level, random, facing, origin, 1, -3, 1, Blocks.COBBLESTONE.defaultBlockState());
		placeMossyBlock(level, random, facing, origin, 2, -3, 1, Blocks.COBBLESTONE.defaultBlockState());
	}

	private static void placeMossyBlock(WorldGenLevel level, RandomSource random, Direction facing, BlockPos pos, int x, int y, int z, BlockState state) {
		if (state.is(Blocks.EMERALD_BLOCK)) {
			if (random.nextBoolean()) return;
		} else if (random.nextFloat() < 0.4F) {
			Block block = state.is(Blocks.COBBLESTONE) ? Blocks.MOSSY_COBBLESTONE : state.is(Blocks.COBBLESTONE_SLAB) ? Blocks.MOSSY_COBBLESTONE_SLAB : Blocks.MOSSY_COBBLESTONE_STAIRS;
			state = BlockUtil.transferAllBlockStates(state, block.defaultBlockState());
		}

		if (facing != null) {
			if (facing.getAxis() == Axis.X) {
				int temp = x;
				x = z;
				z = temp - 3;

				if (state.hasProperty(StairBlock.FACING)) {
					state = state.setValue(StairBlock.FACING, state.getValue(StairBlock.FACING).getOpposite());
				}
			}

			x *= facing.getAxisDirection().getStep();
			z *= facing.getAxisDirection().getStep();
		}

		level.setBlock(pos.offset(x, y, z), state, 19);
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
			if (level.noCollision(NeapolitanEntityTypes.CHIMPANZEE.get().getSpawnAABB(d0, d1, d2))) {
				if (spawnedChimps < NeapolitanConfig.COMMON.chimpanzeeMaxGroupSize.get()) {
					Chimpanzee chimp = NeapolitanEntityTypes.CHIMPANZEE.get().create(level.getLevel());
					if (chimp != null) {
						chimp.moveTo(d0, d1, d2, level.getRandom().nextFloat() * 360.0F, 0.0F);
						chimp.finalizeSpawn(level, level.getCurrentDifficultyAt(chimp.blockPosition()), MobSpawnType.STRUCTURE, null);
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

	public static boolean isValidGround(WorldGenLevel level, BlockPos pos) {
		return level.isStateAtPosition(pos, (state) ->
				(level.getBiome(pos).is(NeapolitanBiomeTags.BANANA_PLANT_REQUIRES_SAND) && state.is(Blocks.SAND)) ||
						(state.is(Blocks.GRAVEL) || state.is(Blocks.GRASS_BLOCK)));
	}

	public static boolean isGrass(LevelSimulatedReader worldIn, BlockPos pos) {
		return worldIn.isStateAtPosition(pos, (state) -> state.is(Blocks.GRASS_BLOCK));
	}

	public static boolean isDirt(LevelSimulatedReader worldIn, BlockPos pos) {
		return worldIn.isStateAtPosition(pos, (state) -> state.is(BlockTags.DIRT));
	}
}