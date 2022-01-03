package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.common.item.MilkBottleItem;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.*;

public class MilkCauldronBlock extends Block {
	public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), BooleanOp.ONLY_FIRST);

	public MilkCauldronBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return INSIDE;
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		int i = state.getValue(LEVEL);
		float f = (float) pos.getY() + (6.0F + (float) (3 * i)) / 16.0F;
		if (!worldIn.isClientSide && entityIn instanceof LivingEntity && i > 0 && entityIn.getY() <= (double) f) {
			LivingEntity living = (LivingEntity) entityIn;
			if (!living.getActiveEffects().isEmpty()) {
				MilkBottleItem.clearRandomEffect(worldIn, living);
				this.setMilkLevel(worldIn, pos, i - 1);
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack itemstack = player.getItemInHand(handIn);
		if (itemstack.isEmpty()) {
			return InteractionResult.PASS;
		} else {
			int i = state.getValue(LEVEL);
			Item item = itemstack.getItem();
			if (item == Items.MILK_BUCKET) {
				if (i < 3 && !worldIn.isClientSide) {
					if (!player.getAbilities().instabuild) {
						player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
					}

					player.awardStat(Stats.FILL_CAULDRON);
					this.setMilkLevel(worldIn, pos, 3);
					worldIn.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				}

				return InteractionResult.sidedSuccess(worldIn.isClientSide);
			} else if (item == Items.BUCKET) {
				if (i == 3 && !worldIn.isClientSide) {
					if (!player.getAbilities().instabuild) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.setItemInHand(handIn, new ItemStack(Items.MILK_BUCKET));
						} else if (!player.getInventory().add(new ItemStack(Items.MILK_BUCKET))) {
							player.drop(new ItemStack(Items.MILK_BUCKET), false);
						}
					}

					player.awardStat(Stats.USE_CAULDRON);
					this.setMilkLevel(worldIn, pos, 0);
					worldIn.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				}

				return InteractionResult.sidedSuccess(worldIn.isClientSide);
			} else if (item == Items.GLASS_BOTTLE) {
				if (i > 0 && !worldIn.isClientSide) {
					if (!player.getAbilities().instabuild) {
						ItemStack stack = new ItemStack(NeapolitanItems.MILK_BOTTLE.get());
						player.awardStat(Stats.USE_CAULDRON);
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.setItemInHand(handIn, stack);
						} else if (!player.getInventory().add(stack)) {
							player.drop(stack, false);
						}
					}

					worldIn.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
					this.setMilkLevel(worldIn, pos, i - 1);
				}

				return InteractionResult.sidedSuccess(worldIn.isClientSide);
			} else if (item == NeapolitanItems.MILK_BOTTLE.get()) {
				if (i < 3 && !worldIn.isClientSide) {
					if (!player.getAbilities().instabuild) {
						itemstack.shrink(1);
						ItemStack returnStack = new ItemStack(Items.GLASS_BOTTLE);
						if (!player.getInventory().add(returnStack)) {
							player.drop(returnStack, false);
						}

						player.awardStat(Stats.USE_CAULDRON);
					}

					worldIn.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
					this.setMilkLevel(worldIn, pos, i + 1);
				}

				return InteractionResult.sidedSuccess(worldIn.isClientSide);
			} else {
				return InteractionResult.PASS;
			}
		}
	}

	public void setMilkLevel(Level worldIn, BlockPos pos, int level) {
		int newLevel = Mth.clamp(level, 0, 3);
		BlockState state = newLevel != 0 ? NeapolitanBlocks.MILK_CAULDRON.get().defaultBlockState() : Blocks.CAULDRON.defaultBlockState();
		worldIn.setBlock(pos, state.setValue(LEVEL, newLevel), 2);
		worldIn.updateNeighbourForOutputSignal(pos, this);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
		return blockState.getValue(LEVEL);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LEVEL);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return false;
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.CAULDRON);
	}
}
