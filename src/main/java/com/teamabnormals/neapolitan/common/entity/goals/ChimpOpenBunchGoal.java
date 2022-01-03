package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.InteractionHand;

import java.util.EnumSet;

public class ChimpOpenBunchGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int throwTimer;

	public ChimpOpenBunchGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isHungry() && this.chimpanzee.getAction().canBeInterrupted()) {
			return this.chimpanzee.getMainHandItem().getItem() == NeapolitanItems.BANANA_BUNCH.get() || this.chimpanzee.getOffhandItem().getItem() == NeapolitanItems.BANANA_BUNCH.get();
		}

		return false;
	}

	@Override
	public void start() {
		this.chimpanzee.setAction(ChimpanzeeAction.DEFAULT);
		this.chimpanzee.getNavigation().stop();
		this.throwTimer = 0;
	}

	@Override
	public void tick() {
		++this.throwTimer;

		if (this.throwTimer >= 40) {
			boolean flag = false;
			InteractionHand hand = InteractionHand.MAIN_HAND;
			if (this.chimpanzee.getMainHandItem().getItem() == NeapolitanItems.BANANA_BUNCH.get()) {
				flag = true;
			} else if (this.chimpanzee.getOffhandItem().getItem() == NeapolitanItems.BANANA_BUNCH.get()) {
				hand = InteractionHand.OFF_HAND;
				flag = true;
			}

			if (flag) {
				this.chimpanzee.openBunch(hand);
				this.chimpanzee.swingArms();
				this.chimpanzee.level.broadcastEntityEvent(this.chimpanzee, (byte) 4);
			}
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.chimpanzee.getMainHandItem().getItem() != NeapolitanItems.BANANA_BUNCH.get() && this.chimpanzee.getOffhandItem().getItem() != NeapolitanItems.BANANA_BUNCH.get()) {
			return false;
		} else {
			return this.chimpanzee.isDoingAction(ChimpanzeeAction.DEFAULT);
		}
	}
}