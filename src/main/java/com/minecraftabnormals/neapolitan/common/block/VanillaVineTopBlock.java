package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class VanillaVineTopBlock extends Block implements IGrowable {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D), // DOWN
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D), // UP
			Block.box(4.0D, 4.0D, 4.0D, 12.0D, 12.0D, 16.0D), // NORTH
			Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 12.0D), // SOUTH
			Block.box(4.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D), // WEST
			Block.box(0.0D, 4.0D, 4.0D, 12.0D, 12.0D, 12.0D), // EAST
	};

	public VanillaVineTopBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP));
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState otherState = worldIn.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
		Block block = otherState.getBlock();
		return facingSameDirection(state, otherState) || block.is(NeapolitanTags.Blocks.VANILLA_PLANTABLE_ON);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.getValue(FACING).get3DDataValue()];
	}

	public static boolean facingSameDirection(BlockState state, BlockState otherState) {
		Block block = otherState.getBlock();
		if (block == NeapolitanBlocks.VANILLA_VINE.get() || block == NeapolitanBlocks.VANILLA_VINE_PLANT.get()) {
			if (otherState.getValue(FACING) == state.getValue(FACING)) {
				return true;
			}
		}

		return false;
	}

	protected int getGrowthAmount(Random rand) {
		return rand.nextInt(2) + 1;
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.playerWillDestroy(world, pos, state, player);
		VanillaVineBlock.createPoisonCloud(world, pos, state, player);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!state.canSurvive(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (this.canGrowUp(state, worldIn, pos) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.relative(state.getValue(FACING)), worldIn.getBlockState(pos.relative(state.getValue(FACING))), random.nextDouble() < 0.1D)) {
			BlockPos blockpos = pos.relative(state.getValue(FACING));
			if (this.canGrowIn(worldIn.getBlockState(blockpos))) {
				worldIn.setBlockAndUpdate(blockpos, state);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, blockpos, worldIn.getBlockState(blockpos));
			}
		}
	}

	private boolean canGrowUp(BlockState state, ServerWorld world, BlockPos pos) {
		Direction facing = state.getValue(FACING);
		if (Direction.Plane.VERTICAL.test(facing)) {
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				if (world.getBlockState(pos.relative(direction)).canOcclude()) {
					return true;
				}
			}
		} else {
			for (Direction direction : Direction.Plane.VERTICAL) {
				if (world.getBlockState(pos.relative(direction)).canOcclude()) {
					return true;
				}
			}

			for (Direction direction : Direction.Plane.HORIZONTAL) {
				if (direction.getAxis() != Axis.Y && direction.getAxis() != facing.getAxis()) {
					if (world.getBlockState(pos.relative(direction)).canOcclude()) {
						return true;
					}
				}
			}
		}

		return world.getBlockState(pos.relative(facing.getOpposite())).is(NeapolitanTags.Blocks.VANILLA_PLANTABLE_ON);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		BlockState facingBlock = worldIn.getBlockState(currentPos.relative(stateIn.getValue(FACING)));
		if (facing == stateIn.getValue(FACING).getOpposite() && !stateIn.canSurvive(worldIn, currentPos)) {
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
		}
		if (facingBlock.hasProperty(FACING) && facingBlock.getValue(FACING) == stateIn.getValue(FACING) && facingState.is(this)) {
			return NeapolitanBlocks.VANILLA_VINE_PLANT.get().defaultBlockState().setValue(FACING, stateIn.getValue(FACING));
		} else {
			return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return this.canGrowIn(worldIn.getBlockState(pos.relative(state.getValue(FACING))));
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.relative(state.getValue(FACING));
		int j = this.getGrowthAmount(rand);

		for (int k = 0; k < j && this.canGrowIn(worldIn.getBlockState(blockpos)); ++k) {
			worldIn.setBlockAndUpdate(blockpos, state);
			blockpos = blockpos.relative(state.getValue(FACING));
		}
	}

	protected boolean canGrowIn(BlockState state) {
		return PlantBlockHelper.isValidGrowthState(state);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}
}