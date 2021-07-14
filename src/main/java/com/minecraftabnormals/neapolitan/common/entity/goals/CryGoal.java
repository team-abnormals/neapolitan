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
		this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		if (!this.chimpanzee.isChild()) {
			return false;
		} else if (!this.chimpanzee.getAction().canBeInterrupted()) {
			return false;
		} else if (!this.chimpanzee.getNavigator().noPath()) {
			return false;
		} else if (this.chimpanzee.getRNG().nextInt(120) == 0) {
			return !this.isParentCloseBy();
		} else {
			return false;
		}
	}

	@Override
	public void startExecuting() {
		this.cryTimer = 40 + this.chimpanzee.getRNG().nextInt(40);
		this.chimpanzee.setAction(ChimpanzeeAction.CRYING);
		this.chimpanzee.getNavigator().clearPath();
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.cryTimer > 0;
	}

	@Override
	public void resetTask() {
		this.cryTimer = 0;
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		--this.cryTimer;
	}

	private boolean isParentCloseBy() {
		List<ChimpanzeeEntity> list = this.chimpanzee.world.getEntitiesWithinAABB(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().grow(8.0D, 4.0D, 8.0D));

		for(ChimpanzeeEntity chimpanzeentity : list) {
			if (chimpanzeentity.getGrowingAge() >= 0) {
				return true;
			}
		}
		
		return false;
	}
}
