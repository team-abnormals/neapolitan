package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.common.block.BeanstalkThornsBlock;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;

public class AdzukiBeansItem extends Item {
	private final boolean magic;

	public AdzukiBeansItem(boolean magic, Properties builder) {
		super(builder);
		this.magic = magic;
	}

	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = world.getBlockState(pos);
		Direction face = context.getClickedFace();
		Player player = context.getPlayer();
		ItemStack stack = context.getItemInHand();
		RandomSource random = world.getRandom();

		List<Direction> offsetDirections = new ArrayList<>();
		for (Axis axis : Axis.values()) {
			if (axis != face.getAxis()) {
				for (Direction direction : Direction.values()) {
					if (direction.getAxis() == axis) {
						offsetDirections.add(direction);
					}
				}
			}
		}

		if (!magic) {
			if ((state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK)) && world.getBlockState(pos.above()).isAir() && face == Direction.UP) {
				if (!world.isClientSide())
					world.setBlockAndUpdate(pos, NeapolitanBlocks.ADZUKI_SOIL.get().defaultBlockState());
				world.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (player instanceof ServerPlayer) {
					CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, pos, stack);
				}
				if (player != null && !player.getAbilities().instabuild) stack.shrink(1);
				return InteractionResult.sidedSuccess(world.isClientSide);
			}

			return InteractionResult.PASS;
		}

		List<BlockPos> beanstalkPositions = new ArrayList<>();
		BlockPos offsetPos = pos.relative(face);

		if (state.getMaterial().isReplaceable()) offsetPos = pos;
		world.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.0F);
		if (!world.isClientSide() && pos.getY() >= world.getMinBuildHeight()) {
			for (Direction direction : offsetDirections) {
				beanstalkPositions.add(offsetPos.relative(direction));
				BlockPos cornerPos = offsetPos.relative(direction).relative(rotate(face.getAxis(), direction));
				if (random.nextInt(3) == 0) beanstalkPositions.add(cornerPos);
			}

			Direction startingDirection = offsetDirections.get(random.nextInt(4));
			stem:
			for (int i = 0; i < 3 + random.nextInt(4); ++i) {
				for (int j = 0; j < 3 + random.nextInt(3); ++j) {
					if (j != 0) offsetPos = offsetPos.relative(face);
					if (world.getBlockState(offsetPos).getMaterial().isReplaceable())
						beanstalkPositions.add(offsetPos);
					else break stem;
				}
				startingDirection = rotate(face.getAxis(), startingDirection);
				offsetPos = offsetPos.relative(startingDirection);
			}

			int placed = 0;
			for (BlockPos blockPos : beanstalkPositions) {
				if (attemptPlaceBeanstalk(world, blockPos, face)) placed++;
			}

			if (placed > 0 && player != null && !player.getAbilities().instabuild) stack.shrink(1);

			for (BlockPos blockPos : beanstalkPositions) {
				for (Direction direction : Direction.values()) {
					BlockPos thornPos = blockPos.relative(direction);
					BlockState beanState = NeapolitanBlocks.BEANSTALK_THORNS.get().defaultBlockState().setValue(BeanstalkThornsBlock.FACING, direction).setValue(BeanstalkThornsBlock.WATERLOGGED, world.getFluidState(thornPos).getType() == Fluids.WATER);
					if (world.getBlockState(blockPos).is(NeapolitanBlocks.BEANSTALK.get()) && world.getBlockState(thornPos).getMaterial().isReplaceable() && random.nextInt(4) == 0 && beanState.canSurvive(world, thornPos))
						world.setBlockAndUpdate(thornPos, beanState);
				}
			}
		}

		return InteractionResult.sidedSuccess(world.isClientSide);
	}

	private static boolean attemptPlaceBeanstalk(Level world, BlockPos pos, Direction direction) {
		if (world.getBlockState(pos).getMaterial().isReplaceable())
			return world.setBlockAndUpdate(pos, NeapolitanBlocks.BEANSTALK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
		return false;
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
}
