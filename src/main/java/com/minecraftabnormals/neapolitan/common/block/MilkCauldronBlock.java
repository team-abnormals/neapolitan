package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.common.item.MilkBottleItem;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MilkCauldronBlock extends Block {
	public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = VoxelShapes.join(VoxelShapes.block(), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), IBooleanFunction.ONLY_FIRST);

	public MilkCauldronBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getInteractionShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return INSIDE;
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
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
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack itemstack = player.getItemInHand(handIn);
		if (itemstack.isEmpty()) {
			return ActionResultType.PASS;
		} else {
			int i = state.getValue(LEVEL);
			Item item = itemstack.getItem();
			if (item == Items.MILK_BUCKET) {
				if (i < 3 && !worldIn.isClientSide) {
					if (!player.abilities.instabuild) {
						player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
					}

					player.awardStat(Stats.FILL_CAULDRON);
					this.setMilkLevel(worldIn, pos, 3);
					worldIn.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				return ActionResultType.sidedSuccess(worldIn.isClientSide);
			} else if (item == Items.BUCKET) {
				if (i == 3 && !worldIn.isClientSide) {
					if (!player.abilities.instabuild) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.setItemInHand(handIn, new ItemStack(Items.MILK_BUCKET));
						} else if (!player.inventory.add(new ItemStack(Items.MILK_BUCKET))) {
							player.drop(new ItemStack(Items.MILK_BUCKET), false);
						}
					}

					player.awardStat(Stats.USE_CAULDRON);
					this.setMilkLevel(worldIn, pos, 0);
					worldIn.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				return ActionResultType.sidedSuccess(worldIn.isClientSide);
			} else if (item == Items.GLASS_BOTTLE) {
				if (i > 0 && !worldIn.isClientSide) {
					if (!player.abilities.instabuild) {
						ItemStack stack = new ItemStack(NeapolitanItems.MILK_BOTTLE.get());
						player.awardStat(Stats.USE_CAULDRON);
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.setItemInHand(handIn, stack);
						} else if (!player.inventory.add(stack)) {
							player.drop(stack, false);
						} else if (player instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) player).refreshContainer(player.inventoryMenu);
						}
					}

					worldIn.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					this.setMilkLevel(worldIn, pos, i - 1);
				}

				return ActionResultType.sidedSuccess(worldIn.isClientSide);
			} else if (item == NeapolitanItems.MILK_BOTTLE.get()) {
				if (i < 3 && !worldIn.isClientSide) {
					if (!player.abilities.instabuild) {
						itemstack.shrink(1);
						ItemStack returnStack = new ItemStack(Items.GLASS_BOTTLE);
						if (!player.inventory.add(returnStack)) {
							player.drop(returnStack, false);
						}

						player.awardStat(Stats.USE_CAULDRON);
						if (player instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) player).refreshContainer(player.inventoryMenu);
						}
					}

					worldIn.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					this.setMilkLevel(worldIn, pos, i + 1);
				}

				return ActionResultType.sidedSuccess(worldIn.isClientSide);
			} else {
				return ActionResultType.PASS;
			}
		}
	}

	public void setMilkLevel(World worldIn, BlockPos pos, int level) {
		int newLevel = MathHelper.clamp(level, 0, 3);
		BlockState state = newLevel != 0 ? NeapolitanBlocks.MILK_CAULDRON.get().defaultBlockState() : Blocks.CAULDRON.defaultBlockState();
		worldIn.setBlock(pos, state.setValue(LEVEL, newLevel), 2);
		worldIn.updateNeighbourForOutputSignal(pos, this);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
		return blockState.getValue(LEVEL);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(LEVEL);
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}

	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.CAULDRON);
	}
}
