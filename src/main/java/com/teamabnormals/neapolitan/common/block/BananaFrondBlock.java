package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BananaFrondBlock extends BushBlock implements BonemealableBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty MOIST = BooleanProperty.create("moist");

	public BananaFrondBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(MOIST, false));
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction facing = state.getValue(FACING);
		BlockPos blockpos = pos.relative(facing.getOpposite());
		BlockState blockState = level.getBlockState(blockpos);
		return (canSupportCenter(level, blockpos, facing) || this.mayPlaceOn(blockState, level, blockpos));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return level.getBlockState(pos).is(BlockTags.LEAVES);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, MOIST);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		Direction direction = context.getClickedFace();
		if (state.is(NeapolitanBlocks.SMALL_BANANA_FROND.get())) {
			return BlockUtil.transferAllBlockStates(state, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState());
		} else if (state.is(NeapolitanBlocks.BANANA_FROND.get())) {
			return BlockUtil.transferAllBlockStates(state, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState());
		} else {
			return this.defaultBlockState().setValue(FACING, direction).setValue(MOIST, direction == Direction.UP && canGrowOn(level.getBlockState(pos.below())));
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return (useContext.getItemInHand().is(NeapolitanItems.BANANA_FROND.get()) && !state.is(NeapolitanBlocks.LARGE_BANANA_FROND.get())) || super.canBeReplaced(state, useContext);
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.BANANA_FROND.get());
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
		if (state.is(NeapolitanBlocks.LARGE_BANANA_FROND.get())) {
			return state.getValue(FACING) == Direction.UP && level instanceof Level && ((Level) level).isRainingAt(pos);
		} else {
			return true;
		}
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
		if (state.is(NeapolitanBlocks.LARGE_BANANA_FROND.get()) && rand.nextInt(6) == 0) {
			attemptGrowBanana(getSizeForFrond(rand, this), level, rand, pos);
		} else if (state.is(NeapolitanBlocks.SMALL_BANANA_FROND.get())) {
			level.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(state, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState()));
		} else if (state.is(NeapolitanBlocks.BANANA_FROND.get())) {
			level.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(state, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState()));
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (state.getValue(MOIST) && level.isRainingAt(pos) && canGrowOn(level.getBlockState(pos.below()))) {
			if (ForgeHooks.onCropsGrowPre(level, pos, state, rand.nextInt(2) == 0)) {
				attemptGrowBanana(getSizeForFrond(rand, this), level, rand, pos);
				ForgeHooks.onCropsGrowPost(level, pos, state);
			}
		}
	}

	public static boolean attemptGrowBanana(int size, Level level, RandomSource rand, BlockPos pos) {
		BlockPos blockPos = pos;
		List<BlockPos> stalks = new ArrayList<>();
		BlockPos upFrond;
		BlockPos bundle = null;

		Map<BlockPos, Direction> smallFronds = new HashMap<>();
		Map<BlockPos, Direction> fronds = new HashMap<>();
		Map<BlockPos, Direction> largeFronds = new HashMap<>();

		for (int i = 0; i < size; i++) {
			stalks.add(blockPos);
			blockPos = blockPos.above();
		}
		upFrond = (blockPos);
		int i = 0;
		for (BlockPos stalk : stalks) {
			if (i >= size - 3) {
				for (Direction direction : Direction.values()) {
					if (direction.getAxis().isHorizontal()) {
						if (i == size - 1) {
							if (rand.nextInt(4) != 0) {
								largeFronds.put(stalk.relative(direction), direction);
							} else {
								fronds.put(stalk.relative(direction), direction);
							}
						} else if (i == size - 2) {
							if (rand.nextBoolean()) {
								fronds.put(stalk.relative(direction), direction);
							} else {
								if (rand.nextBoolean() && bundle == null) {
									bundle = stalk.relative(direction);
								} else {
									smallFronds.put(stalk.relative(direction), direction);
								}
							}
						} else if (i == size - 3) {
							if (rand.nextInt(3) != 0) {
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
				level.setBlock(blockPos2, NeapolitanBlocks.BANANA_STALK.get().defaultBlockState(), 2);
			}
			level.setBlock(upFrond, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState(), 2);
			if (bundle != null)
				level.setBlock(bundle, NeapolitanBlocks.BANANA_BUNDLE.get().defaultBlockState(), 2);
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.SMALL_BANANA_FROND.get().defaultBlockState().setValue(FACING, smallFronds.get(blockPos2)), 2);
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState().setValue(FACING, fronds.get(blockPos2)), 2);
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				level.setBlock(blockPos2, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState().setValue(FACING, largeFronds.get(blockPos2)), 2);
			}
			return true;
		}

		return false;
	}

	public static boolean canGrowOn(BlockState state) {
		return state.is(Tags.Blocks.GRAVEL) || state.is(Tags.Blocks.SAND);
	}

	private static boolean isAirAt(Level level, BlockPos pos, int size) {
		for (int i = 0; i < size + 1; i++) {
			if (i != 0 && !(level.isEmptyBlock(pos) || level.getBlockState(pos).getMaterial().isReplaceable()))
				return false;
			for (Direction direction : Direction.values()) {
				if (direction.getAxis().isHorizontal()) {
					if (!(level.isEmptyBlock(pos.relative(direction)) || level.getBlockState(pos.relative(direction)).getMaterial().isReplaceable()))
						return false;
				}
			}
			pos = pos.above();
		}
		return true;
	}

	private static int getSizeForFrond(RandomSource rand, Block frond) {
		int extra = 0;
		if (frond == NeapolitanBlocks.SMALL_BANANA_FROND.get())
			extra = rand.nextInt(2);
		if (frond == NeapolitanBlocks.BANANA_FROND.get())
			extra = 1 + rand.nextInt(2);
		if (frond == NeapolitanBlocks.LARGE_BANANA_FROND.get())
			extra = 1 + rand.nextInt(3);
		return 3 + extra;
	}
}
