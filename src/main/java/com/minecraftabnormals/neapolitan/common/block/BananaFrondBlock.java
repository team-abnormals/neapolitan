package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import java.util.*;

public class BananaFrondBlock extends BushBlock implements IGrowable {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public BananaFrondBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction facing = state.get(FACING);
		BlockPos blockpos = pos.offset(facing.getOpposite());
		BlockState blockState = worldIn.getBlockState(blockpos);
		return (hasEnoughSolidSide(worldIn, blockpos, facing) || this.isValidGround(blockState, worldIn, blockpos));
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).isIn(BlockTags.LEAVES);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getFace());
	}

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.get(FACING) == Direction.UP && worldIn instanceof World && ((World) worldIn).isRainingAt(pos);
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		if (rand.nextInt(6) == 0) {
			attemptGrowBanana(getSizeForFrond(rand, this), world, rand, pos);
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (this.canGrow(worldIn, pos, state, worldIn.isRemote()) && canGrowOn(worldIn.getBlockState(pos.down()))) {
			if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(2) == 0)) {
				attemptGrowBanana(getSizeForFrond(rand, this), worldIn, rand, pos);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	public static boolean attemptGrowBanana(int size, World world, Random rand, BlockPos pos) {
		BlockPos blockPos = pos;
		List<BlockPos> stalks = new ArrayList<>();
		BlockPos upFrond = null;
		BlockPos bundle = null;

		Map<BlockPos, Direction> smallFronds = new HashMap<>();
		Map<BlockPos, Direction> fronds = new HashMap<>();
		Map<BlockPos, Direction> largeFronds = new HashMap<>();

		for (int i = 0; i < size; i++) {
			stalks.add(blockPos);
			blockPos = blockPos.up();
		}
		upFrond = (blockPos);
		int i = 0;
		for (BlockPos stalk : stalks) {
			if (i >= size - 3) {
				for (Direction direction : Direction.values()) {
					if (direction.getAxis().isHorizontal()) {
						if (i == size - 1) {
							if (rand.nextInt(4) != 0) {
								largeFronds.put(stalk.offset(direction), direction);
							} else {
								fronds.put(stalk.offset(direction), direction);
							}
						} else if (i == size - 2) {
							if (rand.nextBoolean()) {
								fronds.put(stalk.offset(direction), direction);
							} else {
								if (rand.nextBoolean() && bundle == null) {
									bundle = stalk.offset(direction);
								} else {
									smallFronds.put(stalk.offset(direction), direction);
								}
							}
						} else if (i == size - 3) {
							if (rand.nextInt(3) != 0) {
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
				world.setBlockState(blockPos2, NeapolitanBlocks.BANANA_STALK.get().getDefaultState(), 2);
			}
			world.setBlockState(upFrond, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState(), 2);
			if (bundle != null)
				world.setBlockState(bundle, NeapolitanBlocks.BANANA_BUNDLE.get().getDefaultState(), 2);
			for (BlockPos blockPos2 : smallFronds.keySet()) {
				world.setBlockState(blockPos2, NeapolitanBlocks.SMALL_BANANA_FROND.get().getDefaultState().with(FACING, smallFronds.get(blockPos2)), 2);
			}
			for (BlockPos blockPos2 : fronds.keySet()) {
				world.setBlockState(blockPos2, NeapolitanBlocks.BANANA_FROND.get().getDefaultState().with(FACING, fronds.get(blockPos2)), 2);
			}
			for (BlockPos blockPos2 : largeFronds.keySet()) {
				world.setBlockState(blockPos2, NeapolitanBlocks.LARGE_BANANA_FROND.get().getDefaultState().with(FACING, largeFronds.get(blockPos2)), 2);
			}
			return true;
		}

		return false;
	}

	public static boolean canGrowOn(BlockState state) {
		return state.isIn(Tags.Blocks.GRAVEL) || state.isIn(Tags.Blocks.SAND);
	}

	private static boolean isAirAt(World world, BlockPos pos, int size) {
		for (int i = 0; i < size + 1; i++) {
			if (i != 0 && !(world.isAirBlock(pos) || world.getBlockState(pos).getMaterial().isReplaceable()))
				return false;
			for (Direction direction : Direction.values()) {
				if (direction.getAxis().isHorizontal()) {
					if (!(world.isAirBlock(pos.offset(direction)) || world.getBlockState(pos.offset(direction)).getMaterial().isReplaceable()))
						return false;
				}
			}
			pos = pos.up();
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
