package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;
import java.util.List;

public class ChimpGrabBananaGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private ItemEntity itemEntity;
	private final double moveSpeed;
	private int delayCounter;

	public ChimpGrabBananaGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.moveSpeed = speed;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.needsSnack()) {
			return false;
		} else if (this.chimpanzee.isDoingAction(ChimpanzeeAction.SHAKING)) {
			return false;
		} else {
			List<ItemEntity> list = this.chimpanzee.level.getEntitiesOfClass(ItemEntity.class, this.chimpanzee.getBoundingBox().inflate(12.0D, 4.0D, 12.0D));
			ItemEntity item = null;
			double d0 = Double.MAX_VALUE;

			for (ItemEntity item1 : list) {
				if (this.chimpanzee.isSnack(item1.getItem())) {
					double d1 = this.chimpanzee.distanceToSqr(item1);
					if (d1 < d0) {
						d0 = d1;
						item = item1;
					}
				}
			}

			if (item == null) {
				return false;
			} else {
				this.itemEntity = item;
				return true;
			}
		}
	}

	@Override
	public boolean canContinueToUse() {
		return this.chimpanzee.getSnack().isEmpty() && this.itemEntity != null && this.itemEntity.isAlive();
	}

	@Override
	public void start() {
		this.delayCounter = 0;
	}

	@Override
	public void stop() {
		this.itemEntity = null;
	}

	@Override
	public void tick() {
		if (--this.delayCounter <= 0) {
			this.delayCounter = this.adjustedTickDelay(10);
			this.chimpanzee.getLookControl().setLookAt(this.itemEntity, 30.0F, 30.0F);
			Path path = this.chimpanzee.getNavigation().createPath(this.itemEntity, 0);
			if (path != null) {
				this.chimpanzee.getNavigation().moveTo(path, this.moveSpeed);
			}
		}
	}
}