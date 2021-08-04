package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ShakeHeadGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int headShakeTimer;

	public ShakeHeadGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.getAction() != ChimpanzeeAction.DEFAULT && this.chimpanzee.getAction() != ChimpanzeeAction.CLIMBING) {
			return false;
		} else if (this.chimpanzee.getNavigation().isDone() && this.chimpanzee.getRandom().nextInt(120) == 0) {
			if (this.chimpanzee.isDirty() && this.chimpanzee.getGroomer() == null) {
				this.chimpanzee.level.broadcastEntityEvent(this.chimpanzee, (byte) 7);
				return true;
			} else if (this.chimpanzee.needsSunlight() && !this.chimpanzee.isInSunlight()) {
				this.chimpanzee.level.broadcastEntityEvent(this.chimpanzee, (byte) 8);
				return true;
			} else if (this.chimpanzee.needsSnack()) {
				this.chimpanzee.level.broadcastEntityEvent(this.chimpanzee, (byte) 9);
				return true;
			}
		}

		return false;
	}

	@Override
	public void start() {
		this.headShakeTimer = 40;
		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public boolean canContinueToUse() {
		return this.headShakeTimer > 0;
	}

	@Override
	public void stop() {
		this.headShakeTimer = 0;
	}

	@Override
	public void tick() {
		--this.headShakeTimer;
	}
}
