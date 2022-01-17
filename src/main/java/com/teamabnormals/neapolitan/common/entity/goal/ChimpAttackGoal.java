package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class ChimpAttackGoal extends MeleeAttackGoal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpAttackGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed, false);
		this.chimpanzee = chimpanzeeIn;
	}

	public boolean canUse() {
		return !this.chimpanzee.isBaby() && super.canUse();
	}
}