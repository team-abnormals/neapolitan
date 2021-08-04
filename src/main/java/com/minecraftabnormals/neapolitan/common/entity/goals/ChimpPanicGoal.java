package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import net.minecraft.entity.ai.goal.PanicGoal;

public class ChimpPanicGoal extends PanicGoal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpPanicGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.chimpanzee = chimpanzeeIn;
	}

	public boolean canUse() {
		return !this.chimpanzee.isBaby() && !this.chimpanzee.isOnFire() ? false : super.canUse();
	}
}
