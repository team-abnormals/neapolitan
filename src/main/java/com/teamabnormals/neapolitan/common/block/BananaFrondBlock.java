package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.Tags;

import java.util.*;

public class BananaFrondBlock extends BushBlock implements BonemealableBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public BananaFrondBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		Direction facing = state.getValue(FACING);
		BlockPos blockpos = pos.relative(facing.getOpposite());
		BlockState blockState = worldIn.getBlockState(blockpos);
		return (canSupportCenter(worldIn, blockpos, facing) || this.mayPlaceOn(blockState, worldIn, blockpos));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).is(BlockTags.LEAVES);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(FACING) == Direction.UP && worldIn instanceof Level && ((Level) worldIn).isRainingAt(pos);
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {
		if (rand.nextInt(6) == 0) {
			attemptGrowBanana(getSizeForFrond(rand, this), world, rand, pos);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (this.isValidBonemealTarget(worldIn, pos, state, worldIn.isClientSide()) && canGrowOn(worldIn.getBlockState(pos.below()))) {
			if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(2) == 0)) {
				attemptGrowBanana(getSizeForFrond(rand, this), worldIn, rand, pos);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	public static boolean attemptGrowBanana(int size, Level world, Random rand, BlockPos pos) {
		BlockPos blockPos = pos;
		List<BlockPos> stalks = new ArrayList<>();
		BlockPos upFrond = null;
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

		if (isAirAt(world, pos, size) && pos.getY() < world.getMaxBuildHeight() - size) {
			for (BlockPos blockPos2 : stalks) {
				world.setBlock(blockPos2, NeapolitanBlocks.BANANA_STALK.get().defaultBlockState(), 2);
			}
			world.setBlock(upFrond, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState(), 2);
			if (bundle != null)
				world.setBlock(bundle, NeapolitanBlocks.BANANA_BUNDLE.get().defaultBlockState(), 2);
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				world.setBlock(blockPos2, NeapolitanBlocks.SMALL_BANANA_FROND.get().defaultBlockState().setValue(FACING, smallFronds.get(blockPos2)), 2);
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				world.setBlock(blockPos2, NeapolitanBlocks.BANANA_FROND.get().defaultBlockState().setValue(FACING, fronds.get(blockPos2)), 2);
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				world.setBlock(blockPos2, NeapolitanBlocks.LARGE_BANANA_FROND.get().defaultBlockState().setValue(FACING, largeFronds.get(blockPos2)), 2);
			}
			return true;
		}

		return false;
	}

	public static boolean canGrowOn(BlockState state) {
		return state.is(Tags.Blocks.GRAVEL) || state.is(Tags.Blocks.SAND);
	}

	private static boolean isAirAt(Level world, BlockPos pos, int size) {
		for (int i = 0; i < size + 1; i++) {
			if (i != 0 && !(world.isEmptyBlock(pos) || world.getBlockState(pos).getMaterial().isReplaceable()))
				return false;
			for (Direction direction : Direction.values()) {
				if (direction.getAxis().isHorizontal()) {
					if (!(world.isEmptyBlock(pos.relative(direction)) || world.getBlockState(pos.relative(direction)).getMaterial().isReplaceable()))
						return false;
				}
			}
			pos = pos.above();
		}
		return true;
	}

	private static int getSizeForFrond(Random rand, Block frond) {
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
