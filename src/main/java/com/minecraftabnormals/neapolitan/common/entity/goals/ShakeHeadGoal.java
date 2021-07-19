package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;

import net.minecraft.entity.ai.goal.Goal;

public class ShakeHeadGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int headShakeTimer;

	public ShakeHeadGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		if (this.chimpanzee.getAction() != ChimpanzeeAction.DEFAULT && this.chimpanzee.getAction() != ChimpanzeeAction.CLIMBING) {
			return false;
		} else if (this.chimpanzee.getNavigator().noPath() && this.chimpanzee.getRNG().nextInt(120) == 0) {
			if (this.chimpanzee.isDirty() && this.chimpanzee.getGroomer() == null) {
				this.chimpanzee.world.setEntityState(this.chimpanzee, (byte) 6);
				return true;
			} else if (this.chimpanzee.needsSunlight() && !this.chimpanzee.isInSunlight()) {
				this.chimpanzee.world.setEntityState(this.chimpanzee, (byte) 7);
				return true;
			} else if (this.chimpanzee.needsFood()) {
				this.chimpanzee.world.setEntityState(this.chimpanzee, (byte) 8);
				return true;
			}
		}

		return false;
	}

	@Override
	public void startExecuting() {
		this.headShakeTimer = 40;
		this.chimpanzee.getNavigator().clearPath();
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.headShakeTimer > 0;
	}

	@Override
	public void resetTask() {
		this.headShakeTimer = 0;
	}

	@Override
	public void tick() {
		--this.headShakeTimer;
	}
}
