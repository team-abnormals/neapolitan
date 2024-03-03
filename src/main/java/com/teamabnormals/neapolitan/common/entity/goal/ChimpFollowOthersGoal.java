package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class ChimpFollowOthersGoal extends Goal {
	private final Chimpanzee chimpanzee;
	private Chimpanzee leader;
	private final double moveSpeed;
	private int delayCounter;

	public ChimpFollowOthersGoal(Chimpanzee chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.moveSpeed = speed;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		Predicate<Chimpanzee> predicate = (chimpanzeeentity) -> {
			return chimpanzeeentity != this.chimpanzee && chimpanzeeentity.getAge() >= 0;
		};
		List<Chimpanzee> list = this.chimpanzee.level().getEntitiesOfClass(Chimpanzee.class, this.chimpanzee.getBoundingBox().inflate(12.0D, 8.0D, 12.0D), predicate);

		if (list.isEmpty()) {
			return false;
		} else if (this.chimpanzee.isBaby()) {
			return false;
		} else {
			Chimpanzee chimpanzeeentity = null;
			double d0 = Double.MAX_VALUE;
			boolean flag = false;

			for (Chimpanzee chimpanzeeentity1 : list) {
				double d1 = this.chimpanzee.distanceToSqr(chimpanzeeentity1);

				if (this.chimpanzee.isLeader() && d1 < 64.0D) {
					return false;
				}

				if (!flag && chimpanzeeentity1.isLeader()) {
					flag = true;
					d0 = Double.MAX_VALUE;
				}

				if (flag == chimpanzeeentity1.isLeader() && !(d1 > d0)) {
					d0 = d1;
					chimpanzeeentity = chimpanzeeentity1;
				}
			}

			if (chimpanzeeentity != null && d0 >= this.getFollowDistance(chimpanzeeentity)) {
				this.leader = chimpanzeeentity;
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.chimpanzee.isLeader()) {
			return false;
		} else if (!this.leader.isAlive()) {
			return false;
		} else {
			double d0 = this.chimpanzee.distanceToSqr(this.leader);
			return !(d0 < 16.0D) && !(d0 > 256.0D);
		}
	}

	@Override
	public void start() {
		this.delayCounter = 0;
	}

	@Override
	public void stop() {
		this.leader = null;
		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public void tick() {
		if (--this.delayCounter <= 0) {
			this.delayCounter = this.adjustedTickDelay(20);
			this.chimpanzee.getNavigation().moveTo(this.leader, this.moveSpeed);
		}
	}

	private double getFollowDistance(Chimpanzee chimpanzeeentity) {
		return chimpanzeeentity.isLeader() && chimpanzeeentity.getNavigation().isInProgress() ? 16.0D : 64.0D;
	}
}
