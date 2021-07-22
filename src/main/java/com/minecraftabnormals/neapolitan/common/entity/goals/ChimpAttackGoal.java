package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class ChimpAttackGoal extends MeleeAttackGoal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpAttackGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed, false);
		this.chimpanzee = chimpanzeeIn;
	}

	public boolean canUse() {
		return !this.chimpanzee.isBaby() ? super.canUse() : false;
	}
}