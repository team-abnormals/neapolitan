package com.minecraftabnormals.neapolitan.common.item;

import com.minecraftabnormals.neapolitan.common.block.BeanstalkThornsBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdzukiBeansItem extends Item {
	private final boolean magic;

	public AdzukiBeansItem(boolean magic, Properties builder) {
		super(builder);
		this.magic = magic;
	}

	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = world.getBlockState(pos);
		Direction face = context.getClickedFace();
		PlayerEntity player = context.getPlayer();
		ItemStack stack = context.getItemInHand();
		Random random = world.getRandom();

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
				world.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (player != null && !player.abilities.instabuild) stack.shrink(1);
				return ActionResultType.sidedSuccess(world.isClientSide);
			}

			return ActionResultType.PASS;
		}

		List<BlockPos> beanstalkPositions = new ArrayList<>();
		BlockPos offsetPos = pos.relative(face);

		if (state.getMaterial().isReplaceable()) offsetPos = pos;
		world.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundCategory.BLOCKS, 1.0F, 1.0F);
		if (!world.isClientSide() && pos.getY() >= 0) {
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


			if (placed > 0 && player != null && !player.abilities.instabuild) stack.shrink(1);

			for (BlockPos blockPos : beanstalkPositions) {
				for (Direction direction : Direction.values()) {
					BlockPos thornPos = blockPos.relative(direction);
					BlockState beanState = NeapolitanBlocks.BEANSTALK_THORNS.get().defaultBlockState().setValue(BeanstalkThornsBlock.FACING, direction).setValue(BeanstalkThornsBlock.WATERLOGGED, world.getFluidState(thornPos).getType() == Fluids.WATER);
					if (world.getBlockState(blockPos).is(NeapolitanBlocks.BEANSTALK.get()) && world.getBlockState(thornPos).getMaterial().isReplaceable() && random.nextInt(4) == 0 && beanState.canSurvive(world, thornPos))
						world.setBlockAndUpdate(thornPos, beanState);
				}
			}
		}

		return ActionResultType.sidedSuccess(world.isClientSide);
	}

	private static boolean attemptPlaceBeanstalk(World world, BlockPos pos, Direction direction) {
		if (world.getBlockState(pos).getMaterial().isReplaceable())
			return world.setBlockAndUpdate(pos, NeapolitanBlocks.BEANSTALK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
		return false;
	}

	private static Direction rotate(Axis axis, Direction face) {
		if (axis == Axis.Y) {
			switch (face) {
				case NORTH:
					return Direction.EAST;
				case SOUTH:
					return Direction.WEST;
				case WEST:
					return Direction.NORTH;
				case EAST:
					return Direction.SOUTH;
				default:
					throw new IllegalStateException("Unable to get rotated facing of " + face);
			}
		} else if (axis == Axis.Z) {
			switch (face) {
				case UP:
					return Direction.EAST;
				case DOWN:
					return Direction.WEST;
				case WEST:
					return Direction.UP;
				case EAST:
					return Direction.DOWN;
				default:
					throw new IllegalStateException("Unable to get rotated facing of " + face);
			}
		} else if (axis == Axis.X) {
			switch (face) {
				case UP:
					return Direction.NORTH;
				case DOWN:
					return Direction.SOUTH;
				case SOUTH:
					return Direction.UP;
				case NORTH:
					return Direction.DOWN;
				default:
					throw new IllegalStateException("Unable to get rotated facing of " + face);
			}
		}

		return face;
	}
}
