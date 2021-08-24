package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;

public class ChimpRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpRandomWalkingGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return this.chimpanzee.isSitting() ? false : super.canUse();
	}
}
