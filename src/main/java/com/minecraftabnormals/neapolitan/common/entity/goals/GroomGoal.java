package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.List;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.Goal;

public class GroomGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private final double moveSpeed;
	private int delayCounter;
	private int groomTime;

	public GroomGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.moveSpeed = speed;
		this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		if (this.chimpanzee.isChild()) {
			return false;
		} else if (this.chimpanzee.getRNG().nextInt(80) == 0) {
			List<ChimpanzeeEntity> list = this.chimpanzee.world.getEntitiesWithinAABB(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
			ChimpanzeeEntity chimpanzeeentity = null;
			double d0 = Double.MAX_VALUE;

			for(ChimpanzeeEntity chimpanzeeentity1 : list) {
				if (chimpanzeeentity1 != this.chimpanzee && chimpanzeeentity1.needsGrooming() && chimpanzeeentity1.getGroomer() == null) {
					double d1 = this.chimpanzee.getDistanceSq(chimpanzeeentity1);
					if (!(d1 > d0)) {
						d0 = d1;
						chimpanzeeentity = chimpanzeeentity1;
					}
				}
			}

			if (chimpanzeeentity != null) {
				this.chimpanzee.setGroomingTarget(chimpanzeeentity);
				chimpanzeeentity.setGroomer(this.chimpanzee);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean shouldContinueExecuting() {
		ChimpanzeeEntity target = this.chimpanzee.getGroomingTarget();

		if (!target.isAlive()) {
			return false;
		} else if (!target.needsGrooming()) {
			return false;
		} else {
			return this.chimpanzee.getDistanceSq(target) <= 256.0D;
		}
	}

	@Override
	public void startExecuting() {
		this.delayCounter = 0;
		this.groomTime = 0;
	}

	@Override
	public void resetTask() {
		this.chimpanzee.setDefaultAction();
		this.chimpanzee.getGroomingTarget().setGroomer(null);
		this.chimpanzee.setGroomingTarget(null);
	}

	@Override
	public void tick() {
		ChimpanzeeEntity target = this.chimpanzee.getGroomingTarget();

		if (--this.delayCounter <= 0) {
			this.delayCounter = 10;

			double d0 = this.chimpanzee.getDistanceSq(target);

			if (d0 <= 2.0D && this.chimpanzee.canEntityBeSeen(target)) {
				if (this.chimpanzee.getAction().canBeInterrupted()) {
					this.chimpanzee.setAction(ChimpanzeeEntity.Action.GROOMING);

					if (++this.groomTime >= 16) {
						target.setDirtiness(0);
					}
				}
			} else {
				this.chimpanzee.getNavigator().tryMoveToEntityLiving(target, this.moveSpeed);
				this.chimpanzee.setDefaultAction();
			}
		}

		this.chimpanzee.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
	}
}
