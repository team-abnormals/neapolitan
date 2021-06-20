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
		if (this.chimpanzee.isHungry()) {
			ItemStack food = this.chimpanzee.getFood();
			if (!food.isEmpty() && food.isFood()) {
				if (this.chimpanzee.getAction().canBeInterrupted()) {
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
		this.eatTime = 100;
	}

	@Override
	public void tick() {
		--this.eatTime;

		if (this.eatTime <= 0) {
			this.chimpanzee.eatFood();
			this.chimpanzee.setDefaultAction();
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		ItemStack food = this.chimpanzee.getFood();
		return !food.isEmpty() && food.isFood() && this.chimpanzee.getAction() == ChimpanzeeEntity.Action.EATING;
	}

	@Override
	public void resetTask() {
		this.chimpanzee.setDefaultAction();
	}
}