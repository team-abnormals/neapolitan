package com.teamabnormals.neapolitan.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.common.network.particle.SpawnParticlesPayload;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MagicBeansBlock extends DirectionalBlock implements SimpleWaterloggedBlock, BonemealableBlock {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.box(0, 15, 0, 16, 16, 16),
			Block.box(0, 0, 0, 16, 1, 16),
			Block.box(0, 0, 15, 16, 16, 16),
			Block.box(0, 0, 0, 16, 16, 1),
			Block.box(15, 0, 0, 16, 16, 16),
			Block.box(0, 0, 0, 1, 16, 16)
	};

	public MagicBeansBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, false));
	}

	@Override
	protected MapCodec<? extends DirectionalBlock> codec() {
		return null;
	}

	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get3DDataValue()];
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		return level.getBlockState(blockpos).isFaceSturdy(level, blockpos, direction);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean moving) {
		level.scheduleTick(pos, this, 50);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!this.canSurvive(state, level, pos)) {
			level.destroyBlock(pos, true);
		} else {
			growBeanstalk(level, pos, state.getValue(FACING));
		}
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		if (state.getValue(WATERLOGGED))
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, offsetState, level, pos, offsetPos);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		LevelAccessor levelaccessor = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		return this.defaultBlockState().setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER).setValue(FACING, context.getClickedFace());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	private static void growBeanstalk(ServerLevel level, BlockPos origin, Direction direction) {
		RandomSource random = level.getRandom();

		List<BlockPos> beanstalkPositions = new ArrayList<>();

		List<Direction> offsetDirections = new ArrayList<>();
		for (Axis axis : Axis.values()) {
			if (axis != direction.getAxis()) {
				for (Direction direction1 : Direction.values()) {
					if (direction1.getAxis() == axis) {
						offsetDirections.add(direction1);
					}
				}
			}
		}

		for (Direction direction1 : offsetDirections) {
			BlockPos blockPos = origin.relative(direction1);
			beanstalkPositions.add(blockPos);
			BlockPos cornerPos = blockPos.relative(rotate(direction.getAxis(), direction1));
			if (random.nextInt(3) == 0)
				beanstalkPositions.add(cornerPos);
		}

		Direction startingDirection = offsetDirections.get(random.nextInt(4));
		BlockPos blockPos = origin;
		level.playSound(null, origin, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

		stem:
		for (int i = 0; i < 3 + random.nextInt(4); ++i) {
			for (int j = 0; j < 3 + random.nextInt(3); ++j) {
				if (j != 0)
					blockPos = blockPos.relative(direction);
				if (level.getBlockState(blockPos).canBeReplaced() || blockPos.equals(origin))
					beanstalkPositions.add(blockPos);
				else
					break stem;
			}
			startingDirection = rotate(direction.getAxis(), startingDirection);
			blockPos = blockPos.relative(startingDirection);
		}

		for (BlockPos blockPos1 : beanstalkPositions) {
			if (level.getBlockState(blockPos1).canBeReplaced() || blockPos1.equals(origin)) {
				level.setBlockAndUpdate(blockPos1, NeapolitanBlocks.BEANSTALK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
				for (int i = 0; i < 3; i++) {
					double d0 = random.nextGaussian() * 0.02D;
					double d1 = random.nextGaussian() * 0.02D;
					double d2 = random.nextGaussian() * 0.02D;
					NetworkUtil.spawnParticle(level, ParticleTypes.HAPPY_VILLAGER, List.of(new SpawnParticlesPayload.ParticleInstance(blockPos1.getX() - 0.1D + random.nextDouble() * 1.2D, blockPos1.getY() - 0.1D + random.nextDouble() * 1.2D, blockPos1.getZ() - 0.1D + random.nextDouble() * 1.2D, d0, d1, d2)));
				}
			}
		}

		for (BlockPos blockPos1 : beanstalkPositions) {
			for (Direction direction1 : Direction.values()) {
				BlockPos thornPos = blockPos1.relative(direction1);
				BlockState beanState = NeapolitanBlocks.BEANSTALK_THORNS.get().defaultBlockState().setValue(BeanstalkThornsBlock.FACING, direction1).setValue(BeanstalkThornsBlock.WATERLOGGED, level.getFluidState(thornPos).getType() == Fluids.WATER);
				if (level.getBlockState(blockPos1).is(NeapolitanBlocks.BEANSTALK.get()) && level.getBlockState(thornPos).canBeReplaced() && random.nextInt(4) == 0 && beanState.canSurvive(level, thornPos))
					level.setBlockAndUpdate(thornPos, beanState);
			}
		}
	}

	private static Direction rotate(Axis axis, Direction face) {
		if (axis == Axis.Y) {
			return switch (face) {
				case NORTH -> Direction.EAST;
				case SOUTH -> Direction.WEST;
				case WEST -> Direction.NORTH;
				case EAST -> Direction.SOUTH;
				default -> throw new IllegalStateException("Unable to get rotated facing of " + face);
			};
		} else if (axis == Axis.Z) {
			return switch (face) {
				case UP -> Direction.EAST;
				case DOWN -> Direction.WEST;
				case WEST -> Direction.UP;
				case EAST -> Direction.DOWN;
				default -> throw new IllegalStateException("Unable to get rotated facing of " + face);
			};
		} else if (axis == Axis.X) {
			return switch (face) {
				case UP -> Direction.NORTH;
				case DOWN -> Direction.SOUTH;
				case SOUTH -> Direction.UP;
				case NORTH -> Direction.DOWN;
				default -> throw new IllegalStateException("Unable to get rotated facing of " + face);
			};
		}

		return face;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		growBeanstalk(level, pos, state.getValue(FACING));
	}
}