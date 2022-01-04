package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BeanstalkBlock extends RotatedPillarBlock {
	public BeanstalkBlock(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(handIn);
		Direction face = hit.getDirection();
		BlockPos offsetPos = pos.relative(face);
		System.out.println(worldIn.isClientSide());
		if (stack.getItem() instanceof BoneMealItem && worldIn.getBlockState(offsetPos).isAir()) {
			if (!player.getAbilities().instabuild) stack.shrink(1);
			worldIn.setBlockAndUpdate(offsetPos, NeapolitanBlocks.BEANSTALK_THORNS.get().defaultBlockState().setValue(BeanstalkThornsBlock.FACING, face));
			if (worldIn.isClientSide()) {
				BoneMealItem.addGrowthParticles(worldIn, offsetPos, 15);
			}
			return InteractionResult.sidedSuccess(worldIn.isClientSide);

		}

		return super.use(state, worldIn, pos, player, handIn, hit);
	}
}
