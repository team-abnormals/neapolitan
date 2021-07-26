package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class LookAtItemGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private Hand itemHand;
	private ItemStack item;
	private int lookTimer;

	public LookAtItemGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.getAction().canBeInterrupted()) {
			return false;
		} else if (!this.chimpanzee.getNavigation().isDone()) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(120) > 0) {
			return false;
		} else {
			for (int i = 0; i < 2; ++i) {
				Hand hand = i == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
				ItemStack itemstack = this.chimpanzee.getItemInHand(hand);

				if (!itemstack.isEmpty() && !this.chimpanzee.isSnack(itemstack) && !itemstack.getItem().is(NeapolitanTags.Items.CHIMPANZEE_FAVORITES)) {
					this.itemHand = hand;
					this.item = itemstack;
					return true;
				}
			}

			return false;
		}
	}

	@Override
	public void start() {
		this.lookTimer = 60 + this.chimpanzee.getRandom().nextInt(60);
		this.chimpanzee.setAction(this.getHandAction(this.itemHand));
		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public boolean canContinueToUse() {
		if (this.chimpanzee.getItemInHand(this.itemHand) != this.item) {
			return false;
		} else {
			return this.lookTimer > 0;
		}
	}

	@Override
	public void stop() {
		this.lookTimer = 0;
		this.chimpanzee.setDefaultAction();
		
		if (this.chimpanzee.isApeModeItem(this.item) && this.chimpanzee.getApeModeTime() <= 0) {
			this.chimpanzee.setApeModeTime(1200 + this.chimpanzee.getRandom().nextInt(1200));
		}
		
		if (this.chimpanzee.getItemInHand(this.itemHand) == this.item) {
			this.chimpanzee.throwHeldItem(this.itemHand);
			
			if (this.item.getItem().isEdible() && !this.chimpanzee.isSnack(this.item)) {
				this.chimpanzee.level.broadcastEntityEvent(this.chimpanzee, (byte) 6);
			}
		}
	}

	@Override
	public void tick() {
		--this.lookTimer;
		
		if (this.lookTimer == 20 && this.chimpanzee.isApeModeItem(this.item) && this.chimpanzee.getApeModeTime() <= 0) {
			this.lookTimer = 40;
			this.chimpanzee.setApeModeTime(1200 + this.chimpanzee.getRandom().nextInt(1200));
		}
	}

	private ChimpanzeeAction getHandAction(Hand hand) {
		return hand == Hand.MAIN_HAND ? ChimpanzeeAction.LOOKING_AT_MAIN_HAND_ITEM : ChimpanzeeAction.LOOKING_AT_OFF_HAND_ITEM;
	}
}
