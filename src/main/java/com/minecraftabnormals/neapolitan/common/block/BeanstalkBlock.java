package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BeanstalkBlock extends RotatedPillarBlock {
	public BeanstalkBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack stack = player.getHeldItem(handIn);
		Direction face = hit.getFace();
		BlockPos offsetPos = pos.offset(face);
		System.out.println(worldIn.isRemote());
		if (stack.getItem() instanceof BoneMealItem && worldIn.getBlockState(offsetPos).isAir()) {
			if (!player.abilities.isCreativeMode) stack.shrink(1);
			worldIn.setBlockState(offsetPos, NeapolitanBlocks.BEANSTALK_THORNS.get().getDefaultState().with(BeanstalkThornsBlock.FACING, face));
			if (worldIn.isRemote()) {
				BoneMealItem.spawnBonemealParticles(worldIn, offsetPos, 15);
			}
			return ActionResultType.func_233537_a_(worldIn.isRemote);

		}

		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
}
