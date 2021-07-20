package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.Goal;

public class FollowOthersGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private ChimpanzeeEntity buddy;
	private final double moveSpeed;
	private int delayCounter;

	public FollowOthersGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.moveSpeed = speed;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	public boolean canUse() {
		if (this.chimpanzee.isLeader()) {
			return false;
		} else if (this.chimpanzee.isBaby()) {
			return false;
		} else {
			Predicate<ChimpanzeeEntity> predicate = (chimpanzeeentity) -> {
				return chimpanzeeentity != this.chimpanzee && chimpanzeeentity.getAge() >= 0;
			};
			List<ChimpanzeeEntity> list = this.chimpanzee.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().inflate(12.0D, 8.0D, 12.0D), predicate);
			ChimpanzeeEntity chimpanzeeentity = null;
			double d0 = Double.MAX_VALUE;
			boolean flag = false;

			for(ChimpanzeeEntity chimpanzeeentity1 : list) {
				double d1 = this.chimpanzee.distanceToSqr(chimpanzeeentity1);
				
				if (!flag && chimpanzeeentity1.isLeader()) {
					flag = true;
					d0 = Double.MAX_VALUE;;
				}
				
				if (flag == chimpanzeeentity1.isLeader() && !(d1 > d0)) {
					d0 = d1;
					chimpanzeeentity = chimpanzeeentity1;
				}
			}

			if (chimpanzeeentity == null) {
				return false;
			} else if (d0 < this.getFollowDistance(chimpanzeeentity)) {
				return false;
			} else {
				this.buddy = chimpanzeeentity;
				return true;
			}
		}
	}

	public boolean canContinueToUse() {
		if (this.chimpanzee.isLeader()) {
			return false;
		} else if (!this.buddy.isAlive()) {
			return false;
		} else {
			double d0 = this.chimpanzee.distanceToSqr(this.buddy);
			return !(d0 < 16.0D) && !(d0 > 256.0D);
		}
	}

	public void start() {
		this.delayCounter = 0;
	}

	public void stop() {
		this.buddy = null;
		this.chimpanzee.getNavigation().stop();
	}

	public void tick() {
		if (--this.delayCounter <= 0) {
			this.delayCounter = 20;
			this.chimpanzee.getNavigation().moveTo(this.buddy, this.moveSpeed);
		}
	}
	
	private double getFollowDistance(ChimpanzeeEntity chimpanzeeentity) {
		return chimpanzeeentity.isLeader() && chimpanzeeentity.getNavigation().isInProgress() ? 16.0D : 64.0D;
	}
}
