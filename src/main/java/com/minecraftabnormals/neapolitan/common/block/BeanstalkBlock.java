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
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack stack = player.getItemInHand(handIn);
		Direction face = hit.getDirection();
		BlockPos offsetPos = pos.relative(face);
		System.out.println(worldIn.isClientSide());
		if (stack.getItem() instanceof BoneMealItem && worldIn.getBlockState(offsetPos).isAir()) {
			if (!player.abilities.instabuild) stack.shrink(1);
			worldIn.setBlockAndUpdate(offsetPos, NeapolitanBlocks.BEANSTALK_THORNS.get().defaultBlockState().setValue(BeanstalkThornsBlock.FACING, face));
			if (worldIn.isClientSide()) {
				BoneMealItem.addGrowthParticles(worldIn, offsetPos, 15);
			}
			return ActionResultType.sidedSuccess(worldIn.isClientSide);

		}

		return super.use(state, worldIn, pos, player, handIn, hit);
	}
}
