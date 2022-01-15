package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBlockTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class VanillaVineTopBlock extends Block implements BonemealableBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D), // DOWN
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D), // UP
			Block.box(4.0D, 4.0D, 4.0D, 12.0D, 12.0D, 16.0D), // NORTH
			Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 12.0D), // SOUTH
			Block.box(4.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D), // WEST
			Block.box(0.0D, 4.0D, 4.0D, 12.0D, 12.0D, 12.0D), // EAST
	};

	public VanillaVineTopBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP));
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState otherState = worldIn.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
		return facingSameDirection(state, otherState) || otherState.is(NeapolitanBlockTags.VANILLA_PLANTABLE_ON);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get3DDataValue()];
	}

	public static boolean facingSameDirection(BlockState state, BlockState otherState) {
		Block block = otherState.getBlock();
		if (block == NeapolitanBlocks.VANILLA_VINE.get() || block == NeapolitanBlocks.VANILLA_VINE_PLANT.get()) {
			return otherState.getValue(FACING) == state.getValue(FACING);
		}

		return false;
	}

	protected int getGrowthAmount(Random rand) {
		return rand.nextInt(2) + 1;
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		super.playerWillDestroy(world, pos, state, player);
		VanillaVineBlock.createPoisonCloud(world, pos, state, player);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (!state.canSurvive(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		if (this.canGrowUp(state, worldIn, pos) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.relative(state.getValue(FACING)), worldIn.getBlockState(pos.relative(state.getValue(FACING))), random.nextDouble() < 0.1D)) {
			BlockPos blockpos = pos.relative(state.getValue(FACING));
			if (this.canGrowIn(worldIn.getBlockState(blockpos))) {
				worldIn.setBlockAndUpdate(blockpos, state);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, blockpos, worldIn.getBlockState(blockpos));
			}
		}
	}

	private boolean canGrowUp(BlockState state, ServerLevel world, BlockPos pos) {
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

		return world.getBlockState(pos.relative(facing.getOpposite())).is(NeapolitanBlockTags.VANILLA_PLANTABLE_ON);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		BlockState facingBlock = worldIn.getBlockState(currentPos.relative(stateIn.getValue(FACING)));
		if (facing == stateIn.getValue(FACING).getOpposite() && !stateIn.canSurvive(worldIn, currentPos)) {
			worldIn.scheduleTick(currentPos, this, 1);
		}
		if (facingBlock.hasProperty(FACING) && facingBlock.getValue(FACING) == stateIn.getValue(FACING) && facingState.is(this)) {
			return NeapolitanBlocks.VANILLA_VINE_PLANT.get().defaultBlockState().setValue(FACING, stateIn.getValue(FACING));
		} else {
			return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return this.canGrowIn(worldIn.getBlockState(pos.relative(state.getValue(FACING))));
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.relative(state.getValue(FACING));
		int j = this.getGrowthAmount(rand);

		for (int k = 0; k < j && this.canGrowIn(worldIn.getBlockState(blockpos)); ++k) {
			worldIn.setBlockAndUpdate(blockpos, state);
			blockpos = blockpos.relative(state.getValue(FACING));
		}
	}

	protected boolean canGrowIn(BlockState state) {
		return NetherVines.isValidGrowthState(state);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}
}