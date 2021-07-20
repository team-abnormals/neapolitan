package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;

public class EatBananaGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int eatTime;

	public EatBananaGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isHungry()) {
			ItemStack food = this.chimpanzee.getSnack();
			if (!food.isEmpty() && food.isEdible()) {
				if (this.chimpanzee.getAction().canBeInterrupted()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void start() {
		this.chimpanzee.setAction(ChimpanzeeAction.EATING);
		this.chimpanzee.getNavigation().stop();
		this.eatTime = 100;
	}

	@Override
	public void tick() {
		--this.eatTime;

		if (this.eatTime <= 0) {
			this.chimpanzee.eatSnack();
			this.chimpanzee.setDefaultAction();
		}
	}

	@Override
	public boolean canContinueToUse() {
		ItemStack food = this.chimpanzee.getSnack();
		return !food.isEmpty() && food.isEdible() && this.chimpanzee.getAction() == ChimpanzeeAction.EATING;
	}

	@Override
	public void stop() {
		this.chimpanzee.setDefaultAction();
	}
}