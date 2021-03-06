package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.List;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;

import net.minecraft.entity.ai.goal.Goal;

public class CryGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int cryTimer;

	public CryGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.isBaby()) {
			return false;
		} else if (!this.chimpanzee.getAction().canBeInterrupted()) {
			return false;
		} else if (!this.chimpanzee.getNavigation().isDone()) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(120) == 0) {
			return !this.isParentCloseBy();
		} else {
			return false;
		}
	}

	@Override
	public void start() {
		this.cryTimer = 40 + this.chimpanzee.getRandom().nextInt(40);
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
		List<ChimpanzeeEntity> list = this.chimpanzee.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().inflate(8.0D, 4.0D, 8.0D));

		for(ChimpanzeeEntity chimpanzeentity : list) {
			if (chimpanzeentity.getAge() >= 0) {
				return true;
			}
		}
		
		return false;
	}
}
