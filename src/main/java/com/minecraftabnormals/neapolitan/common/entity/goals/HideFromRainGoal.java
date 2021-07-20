package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class HideFromRainGoal extends MoveToBlockGoal {
	private final ChimpanzeeEntity chimpanzee;

	public HideFromRainGoal(ChimpanzeeEntity chimpanzeeIn, double speed, int length, int yMax) {
		super(chimpanzeeIn, speed, length, yMax);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return this.chimpanzee.level.isRainingAt(this.chimpanzee.blockPosition()) && super.canUse();
	}

	@Override
	public boolean canContinueToUse() {
		return this.chimpanzee.level.isRaining() && !this.blockPos.closerThan(this.chimpanzee.position(), 1.0D) && super.canContinueToUse();
	}

	@Override
	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		return worldIn.isEmptyBlock(pos.above()) && worldIn.isEmptyBlock(pos.above(2)) && !worldIn.canSeeSky(pos.above()) && worldIn.getBlockState(pos).getMaterial().isSolid();
	}
}
