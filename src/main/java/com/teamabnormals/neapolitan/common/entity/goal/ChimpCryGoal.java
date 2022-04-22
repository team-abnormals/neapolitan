package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;

public class ChimpCryGoal extends Goal {
	private final Chimpanzee chimpanzee;
	private int cryTimer;

	public ChimpCryGoal(Chimpanzee chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.isBaby()) {
			return false;
		} else if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.DEFAULT)) {
			return false;
		} else if (!this.chimpanzee.getNavigation().isDone()) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(60) == 0) {
			return !this.isParentCloseBy();
		} else {
			return false;
		}
	}

	@Override
	public void start() {
		this.cryTimer = this.adjustedTickDelay(40 + this.chimpanzee.getRandom().nextInt(40));
		this.chimpanzee.setAction(ChimpanzeeAction.CRYING);
		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public boolean canContinueToUse() {
		return this.cryTimer > 0;
	}

	@Override
	public void stop() {
		this.cryTimer = 0;
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		--this.cryTimer;
	}

	private boolean isParentCloseBy() {
		List<Chimpanzee> list = this.chimpanzee.level.getEntitiesOfClass(Chimpanzee.class, this.chimpanzee.getBoundingBox().inflate(8.0D, 4.0D, 8.0D));

		for (Chimpanzee chimpanzeentity : list) {
			if (chimpanzeentity.getAge() >= 0) {
				return true;
			}
		}

		return false;
	}
}
