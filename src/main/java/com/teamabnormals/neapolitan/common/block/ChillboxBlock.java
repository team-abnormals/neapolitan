package com.teamabnormals.neapolitan.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.neapolitan.common.block.entity.ChillboxBlockEntity;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ChillboxBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<ChillboxBlock> CODEC = simpleCodec(ChillboxBlock::new);
	private static final VoxelShape BOUNDING_BOX = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	@Override
	public MapCodec<ChillboxBlock> codec() {
		return CODEC;
	}

	public ChillboxBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	protected BlockState updateShape(BlockState p_276307_, Direction p_276322_, BlockState p_276280_, LevelAccessor p_276320_, BlockPos p_276270_, BlockPos p_276312_) {
		if (p_276307_.getValue(WATERLOGGED)) {
			p_276320_.scheduleTick(p_276270_, Fluids.WATER, Fluids.WATER.getTickDelay(p_276320_));
		}

		return super.updateShape(p_276307_, p_276322_, p_276280_, p_276320_, p_276270_, p_276312_);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			BlockEntity blockentity = level.getBlockEntity(pos);
			if (blockentity instanceof ChillboxBlockEntity chillbox) {
				player.openMenu(chillbox);
			}
			return InteractionResult.CONSUME;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_272711_) {
		FluidState fluidstate = p_272711_.getLevel().getFluidState(p_272711_.getClickedPos());
		return this.defaultBlockState().setValue(FACING, p_272711_.getHorizontalDirection()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}

	@Override
	protected boolean isPathfindable(BlockState p_276295_, PathComputationType p_276303_) {
		return false;
	}

	@Override
	protected VoxelShape getShape(BlockState p_273112_, BlockGetter p_273055_, BlockPos p_273137_, CollisionContext p_273151_) {
		return BOUNDING_BOX;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_273169_) {
		p_273169_.add(FACING, WATERLOGGED);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_273396_, BlockState p_272674_) {
		return new ChillboxBlockEntity(p_273396_, p_272674_);
	}


	@Override
	protected FluidState getFluidState(BlockState p_272593_) {
		return p_272593_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_272593_);
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState p_305995_) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState p_306206_, Level p_306113_, BlockPos p_306305_) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_306113_.getBlockEntity(p_306305_));
	}

	@Override
	protected BlockState rotate(BlockState p_333895_, Rotation p_333806_) {
		return p_333895_.setValue(FACING, p_333806_.rotate(p_333895_.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState p_334078_, Mirror p_333905_) {
		return p_334078_.rotate(p_333905_.getRotation(p_334078_.getValue(FACING)));
	}

	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof ChillboxBlockEntity chillbox) {
				if (level instanceof ServerLevel) {
					Containers.dropContents(level, pos, chillbox);
					chillbox.getRecipesToAwardAndPopExperience((ServerLevel) level, Vec3.atCenterOf(pos));
				}

				super.onRemove(state, level, pos, newState, isMoving);
				level.updateNeighbourForOutputSignal(pos, this);
			} else {
				super.onRemove(state, level, pos, newState, isMoving);
			}
		}
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
		return level.isClientSide ? null : createTickerHelper(blockEntity, NeapolitanBlockEntityTypes.CHILLBOX.get(), ChillboxBlockEntity::serverTick);
	}
}
