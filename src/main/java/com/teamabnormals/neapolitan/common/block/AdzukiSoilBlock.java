package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class AdzukiSoilBlock extends Block implements BonemealableBlock {
	public AdzukiSoilBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (stack.canPerformAction(ItemAbilities.HOE_DIG)) {
			popResource(worldIn, pos.above(), new ItemStack(NeapolitanItems.ADZUKI_BEANS.get()));
		}
		return super.useItemOn(stack, state, worldIn, pos, player, handIn, hit);
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility action, boolean simulate) {
		if (action == ItemAbilities.SHOVEL_FLATTEN) {
			return Blocks.DIRT_PATH.defaultBlockState();
		}
		if (action == ItemAbilities.HOE_DIG) {
			return Blocks.DIRT.defaultBlockState();
		}
		return super.getToolModifiedState(state, context, action, simulate);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!worldIn.isAreaLoaded(pos, 1)) return;
		if (worldIn.getRawBrightness(pos.above(), 0) >= 9 && CommonHooks.canCropGrow(worldIn, pos, state, random.nextInt(4) == 0) && worldIn.getBlockState(pos.above()).isAir()) {
			worldIn.setBlockAndUpdate(pos.above(), NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState());
			worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			CommonHooks.fireCropGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		if (worldIn.getBlockState(pos.above()).isAir()) {
			worldIn.setBlockAndUpdate(pos.above(), NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState());
			worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
		}
	}
}
