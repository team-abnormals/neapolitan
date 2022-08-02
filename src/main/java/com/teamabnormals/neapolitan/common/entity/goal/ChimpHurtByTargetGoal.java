package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class ChimpHurtByTargetGoal extends HurtByTargetGoal {

	private final Chimpanzee chimpanzee;

	public ChimpHurtByTargetGoal(Chimpanzee chimpanzeeIn) {
		super(chimpanzeeIn, Chimpanzee.class);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public void start() {
		super.start();
		if (this.chimpanzee.isBaby()) {
			this.stop();
		}
	}

	@Override
	protected void alertOther(Mob mobIn, LivingEntity targetIn) {
		if (mobIn instanceof Chimpanzee && !mobIn.isBaby()) {
			super.alertOther(mobIn, targetIn);
		}
	}
}
