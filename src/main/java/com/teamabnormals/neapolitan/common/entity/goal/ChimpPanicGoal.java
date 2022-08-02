package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class ChimpPanicGoal extends PanicGoal {
	private final Chimpanzee chimpanzee;

	public ChimpPanicGoal(Chimpanzee chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return (this.chimpanzee.isBaby() || this.chimpanzee.isOnFire()) && super.canUse();
	}
}
