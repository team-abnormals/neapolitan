package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class AdzukiSoilBlock extends Block {
	public AdzukiSoilBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (player.getHeldItem(handIn).getItem().getToolTypes(player.getHeldItem(handIn)).contains(ToolType.HOE))
			spawnAsEntity(worldIn, pos.up(), new ItemStack(NeapolitanItems.ADZUKI_BEANS.get()));
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (toolType == ToolType.HOE)
			return Blocks.DIRT.getDefaultState();
		return super.getToolModifiedState(state, world, pos, player, stack, toolType);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1)) return;
		if (worldIn.getLightSubtracted(pos.up(), 0) >= 9 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(4) == 0) && worldIn.getBlockState(pos.up()).isAir()) {
			worldIn.setBlockState(pos.up(), NeapolitanBlocks.ADZUKI_SPROUTS.get().getDefaultState());
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}
}
