package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ChimpOpenBunchGoal extends Goal {
	private final Chimpanzee chimpanzee;
	private int throwTimer;

	public ChimpOpenBunchGoal(Chimpanzee chimpanzeeIn) {
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
		this.throwTimer = this.adjustedTickDelay(40);
	}

	@Override
	public void tick() {
		if (--this.throwTimer <= 0) {
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