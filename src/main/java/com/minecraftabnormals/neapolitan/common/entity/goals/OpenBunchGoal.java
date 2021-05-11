package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Hand;

public class OpenBunchGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int throwTimer;

	public OpenBunchGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		if (this.chimpanzee.isHungry() && this.chimpanzee.getAction().canBeChanged()) {
			if (this.chimpanzee.getHeldItemMainhand().getItem() == NeapolitanItems.BANANA_BUNCH.get() || this.chimpanzee.getHeldItemOffhand().getItem() == NeapolitanItems.BANANA_BUNCH.get()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void startExecuting() {
		this.chimpanzee.setAction(ChimpanzeeEntity.Action.DEFAULT);
		this.chimpanzee.getNavigator().clearPath();
		this.throwTimer = 0;
	}

	@Override
	public void tick() {
		++this.throwTimer;

		if (this.throwTimer >= 40) {
			boolean flag = false;
			Hand hand = Hand.MAIN_HAND;
			if (this.chimpanzee.getHeldItemMainhand().getItem() == NeapolitanItems.BANANA_BUNCH.get()) {
				flag = true;
			} else if (this.chimpanzee.getHeldItemOffhand().getItem() == NeapolitanItems.BANANA_BUNCH.get()) {
				hand = Hand.OFF_HAND;
				flag = true;
			}

			if (flag) {
				this.chimpanzee.openBunch(hand);
				this.chimpanzee.setActionCooldown(40);
				this.chimpanzee.swingArms();
				this.chimpanzee.world.setEntityState(this.chimpanzee, (byte)4);
			}
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (this.throwTimer < 40 && this.chimpanzee.getHeldItemMainhand().getItem() != NeapolitanItems.BANANA_BUNCH.get() && this.chimpanzee.getHeldItemOffhand().getItem() != NeapolitanItems.BANANA_BUNCH.get()) {
			return false;
		} else if (this.throwTimer >= 60) {
			return false;
		} else {
			return this.chimpanzee.getAction() == ChimpanzeeEntity.Action.DEFAULT;
		}
	}
}