package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;

public class ChimpJumpOnBouncyGoal extends MoveToBlockGoal {
	private final ChimpanzeeEntity chimpanzee;
	private int jumps;

	public ChimpJumpOnBouncyGoal(ChimpanzeeEntity chimpanzeeIn, double speed, int length) {
		super(chimpanzeeIn, speed, length, 6);
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (this.chimpanzee.isBaby() && this.chimpanzee.getRandom().nextInt(150) == 0) {
			return super.canUse();
		} else if (!this.chimpanzee.isBaby() && this.chimpanzee.getRandom().nextInt(300) == 0) {
			return super.canUse();
		} else {
			return false;
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.jumps > 10 && this.chimpanzee.getRandom().nextInt(6) == 0) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else {
			return super.canContinueToUse();
		}
	}

	@Override
	public void start() {
		super.start();
		this.jumps = 0;
	}

	@Override
	public void stop() {
		super.stop();
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		super.tick();

		if (this.isReachedTarget() && this.chimpanzee.isOnGround() && this.chimpanzee.getAction().canBeInterrupted()) {
			this.mob.getJumpControl().jump();

			++this.jumps;
		}
	}

	@Override
	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		return worldIn.isEmptyBlock(pos.above()) && worldIn.isEmptyBlock(pos.above().above()) && worldIn.getBlockState(pos).getBlock().is(NeapolitanTags.Blocks.CHIMPANZEE_JUMPING_BLOCKS);
	}
}
