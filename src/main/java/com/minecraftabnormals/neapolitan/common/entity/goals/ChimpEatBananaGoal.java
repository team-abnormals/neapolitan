package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;

import java.util.EnumSet;

public class ChimpEatBananaGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int eatTime;

	public ChimpEatBananaGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isHungry()) {
			ItemStack snack = this.chimpanzee.getSnack();
			if (!snack.isEmpty() && snack.getItem() != NeapolitanItems.BANANA_BUNCH.get()) {
				return this.chimpanzee.getAction().canBeInterrupted();
			}
		}

		return false;
	}

	@Override
	public void start() {
		this.chimpanzee.setAction(ChimpanzeeAction.DEFAULT);
		this.chimpanzee.getNavigation().stop();
		this.eatTime = 140;
	}

	@Override
	public void tick() {
		--this.eatTime;

		if (this.eatTime <= 0) {
			this.chimpanzee.eatSnack();
			this.chimpanzee.setDefaultAction();
		} else if (this.eatTime < 100) {
			this.chimpanzee.setAction(ChimpanzeeAction.EATING);
		}
	}

	@Override
	public boolean canContinueToUse() {
		ItemStack snack = this.chimpanzee.getSnack();
		return !snack.isEmpty() && snack.getItem() != NeapolitanItems.BANANA_BUNCH.get();
	}

	@Override
	public void stop() {
		this.chimpanzee.setDefaultAction();
	}
}