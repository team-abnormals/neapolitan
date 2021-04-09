package com.minecraftabnormals.neapolitan.common.entity.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.pathfinding.Path;

import java.util.EnumSet;
import java.util.List;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

public class GrabBananaGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private ItemEntity itemEntity;
	private final double moveSpeed;
	private int delayCounter;

	public GrabBananaGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.moveSpeed = speed;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		if (!this.chimpanzee.getFood().isEmpty()) {
			return false;
		} else {
			List<ItemEntity> list = this.chimpanzee.world.getEntitiesWithinAABB(ItemEntity.class, this.chimpanzee.getBoundingBox().grow(12.0D, 4.0D, 12.0D));
			ItemEntity item = null;
			double d0 = Double.MAX_VALUE;

			for (ItemEntity item1 : list) {
				if (this.chimpanzee.isFood(item1.getItem())) {
					double d1 = this.chimpanzee.getDistanceSq(item1);
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
	public boolean shouldContinueExecuting() {
		return !this.chimpanzee.getFood().isEmpty() && this.itemEntity != null && this.itemEntity.isAlive();
	}

	@Override
	public void startExecuting() {
		this.delayCounter = 0;
	}

	@Override
	public void resetTask() {
		this.itemEntity = null;
	}

	@Override
	public void tick() {
		if (--this.delayCounter <= 0) {
			this.delayCounter = 10;
			this.chimpanzee.getLookController().setLookPositionWithEntity(this.itemEntity, 30.0F, 30.0F);
			Path path = this.chimpanzee.getNavigator().getPathToEntity(this.itemEntity, 0);
			if (path != null) {
				this.chimpanzee.getNavigator().setPath(path, this.moveSpeed);
			}
		}
	}
}