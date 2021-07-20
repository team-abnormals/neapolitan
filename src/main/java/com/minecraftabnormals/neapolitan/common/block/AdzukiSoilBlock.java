package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolType;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class AdzukiSoilBlock extends Block implements IGrowable {
	public AdzukiSoilBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (player.getItemInHand(handIn).getItem().getToolTypes(player.getItemInHand(handIn)).contains(ToolType.HOE))
			popResource(worldIn, pos.above(), new ItemStack(NeapolitanItems.ADZUKI_BEANS.get()));
		return super.use(state, worldIn, pos, player, handIn, hit);
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (toolType == ToolType.HOE)
			return Blocks.DIRT.defaultBlockState();
		return super.getToolModifiedState(state, world, pos, player, stack, toolType);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1)) return;
		if (worldIn.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(4) == 0) && worldIn.getBlockState(pos.above()).isAir()) {
			worldIn.setBlockAndUpdate(pos.above(), NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState());
			worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		if (worldIn.getBlockState(pos.above()).isAir()) {
			worldIn.setBlockAndUpdate(pos.above(), NeapolitanBlocks.ADZUKI_SPROUTS.get().defaultBlockState());
			worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
		}
	}
}
