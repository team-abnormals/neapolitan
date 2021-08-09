package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;

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

	protected void alertOther(MobEntity mobIn, LivingEntity targetIn) {
		if (mobIn instanceof ChimpanzeeEntity && !mobIn.isBaby()) {
			super.alertOther(mobIn, targetIn);
		}
	}
}
