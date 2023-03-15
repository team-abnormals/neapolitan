package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.common.item.MilkBottleItem;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.other.NeapolitanCauldronInteractions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeMod;

public class MilkCauldronBlock extends MilkshakeCauldronBlock {

	public MilkCauldronBlock(BlockBehaviour.Properties properties) {
		super(NeapolitanCauldronInteractions.MILK.map());
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		int i = state.getValue(LEVEL);
		float f = (float) pos.getY() + (6.0F + (float) (3 * i)) / 16.0F;
		if (!level.isClientSide && entityIn instanceof LivingEntity living && i > 0 && entityIn.getY() <= (double) f) {
			if (!living.getActiveEffects().isEmpty()) {
				MilkBottleItem.clearRandomEffect(level, living);
				lowerFillLevel(state, level, pos);
			}
		}
	}

	@Override
	protected boolean canReceiveStalactiteDrip(Fluid fluid) {
		return fluid == ForgeMod.MILK.get() && NeapolitanConfig.COMMON.milkFromDripstones.get();
	}
}
