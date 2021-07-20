package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;

import net.minecraft.entity.ai.goal.Goal;

public class BeGroomedGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;

	public BeGroomedGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.getAction().canBeInterrupted())  {
			return false;
		} else if (this.chimpanzee.getAction() == ChimpanzeeAction.CRYING || this.chimpanzee.getAction() == ChimpanzeeAction.HUNCHING)  {
			return false;
		} else {
			ChimpanzeeEntity groomer = this.chimpanzee.getGroomer();
			return groomer != null && groomer.getGroomingTarget() == this.chimpanzee;
		}
	}

	@Override
	public boolean canContinueToUse() {
		ChimpanzeeEntity groomer = this.chimpanzee.getGroomer();
		return groomer != null && groomer.isAlive() && groomer.getGroomingTarget() == this.chimpanzee;
	}

	@Override
	public void start() {
		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public void stop() {
		this.chimpanzee.setGroomer(null);
	}
}
