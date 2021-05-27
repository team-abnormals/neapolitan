package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.Goal;

public class BeGroomedGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;

	public BeGroomedGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		if (!this.chimpanzee.getAction().canBeInterrupted())  {
			return false;
		} else {
			ChimpanzeeEntity groomer = this.chimpanzee.getGroomer();
			return groomer != null && groomer.getGroomingTarget() == this.chimpanzee;
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		ChimpanzeeEntity groomer = this.chimpanzee.getGroomer();
		return groomer != null && groomer.isAlive() && groomer.getGroomingTarget() == this.chimpanzee;
	}

	@Override
	public void startExecuting() {
		this.chimpanzee.getNavigator().clearPath();
	}

	@Override
	public void resetTask() {
		this.chimpanzee.setGroomer(null);
	}
}
