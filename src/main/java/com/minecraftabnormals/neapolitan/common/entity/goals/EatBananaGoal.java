package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;

public class EatBananaGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int eatTime;

	public EatBananaGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		if (this.chimpanzee.isHungry() && !this.chimpanzee.isInWater()) {
			ItemStack food = this.chimpanzee.getFood();
			if (!food.isEmpty() && food.isFood() && !this.chimpanzee.isInWater()) {
				if (this.chimpanzee.getAction() == ChimpanzeeEntity.Action.EATING) {
					return true;
				} else if (this.chimpanzee.canDoAction() && this.chimpanzee.getAction().canBeChanged()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void startExecuting() {
		this.chimpanzee.setAction(ChimpanzeeEntity.Action.EATING);
		this.chimpanzee.getNavigator().clearPath();
		this.eatTime = 80;
	}

	@Override
	public void tick() {
		--this.eatTime;

		if (this.eatTime <= 0) {
			this.chimpanzee.eatFood();
			this.chimpanzee.setActionCooldown(80);
			this.chimpanzee.setAction(ChimpanzeeEntity.Action.DEFAULT);
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		ItemStack food = this.chimpanzee.getFood();
		return !food.isEmpty() && food.isFood() && !this.chimpanzee.isInWater() && this.chimpanzee.getAction() == ChimpanzeeEntity.Action.EATING;
	}

	@Override
	public void resetTask() {
		this.chimpanzee.setActionCooldown(80);
		this.chimpanzee.setAction(ChimpanzeeEntity.Action.DEFAULT);
	}
}