package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class ChimpAttackGoal extends MeleeAttackGoal {
	private final Chimpanzee chimpanzee;

	public ChimpAttackGoal(Chimpanzee chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed, false);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return !this.chimpanzee.isBaby() && super.canUse();
	}
}