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
	public boolean shouldExecute() {
		return this.chimpanzee.world.isRainingAt(this.chimpanzee.getPosition()) && super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.chimpanzee.world.isRaining() && !this.destinationBlock.withinDistance(this.chimpanzee.getPositionVec(), 1.0D) && super.shouldContinueExecuting();
	}

	@Override
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		return worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up(2)) && !worldIn.canSeeSky(pos.up()) && worldIn.getBlockState(pos).getMaterial().isSolid();
	}
}
