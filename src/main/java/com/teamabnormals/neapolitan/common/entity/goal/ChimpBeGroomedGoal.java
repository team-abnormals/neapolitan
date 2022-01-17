package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ChimpBeGroomedGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpBeGroomedGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.getAction().canBeInterrupted()) {
			return false;
		} else if (this.chimpanzee.isDoingAction(ChimpanzeeAction.CRYING)) {
			return false;
		} else {
			ChimpanzeeEntity groomer = this.chimpanzee.getGroomer();
			return groomer != null && this.chimpanzee.distanceToSqr(groomer) < 16.0D && groomer.getGroomingTarget() == this.chimpanzee;
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
