package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

public class ChimpHurtByTargetGoal extends HurtByTargetGoal {

	private final ChimpanzeeEntity chimpanzee;

	public ChimpHurtByTargetGoal(ChimpanzeeEntity chimpanzeeIn) {
		super(chimpanzeeIn, ChimpanzeeEntity.class);
		this.chimpanzee = chimpanzeeIn;
	}

	public void start() {
		super.start();
		if (this.chimpanzee.isBaby()) {
			this.stop();
		}
	}

	protected void alertOther(Mob mobIn, LivingEntity targetIn) {
		if (mobIn instanceof ChimpanzeeEntity && !mobIn.isBaby()) {
			super.alertOther(mobIn, targetIn);
		}
	}
}
