package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class ChimpSitGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private int sitTimer;
	private int cooldown;

	public ChimpSitGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		if (this.cooldown > 0) {
			--this.cooldown;
			return false;
		} else if (this.chimpanzee.isSitting()) {
			return true;
		} else if (this.chimpanzee.isPassenger() || !this.chimpanzee.isOnGround() || this.chimpanzee.isInWater()) {
			return false;
		} else if (this.chimpanzee.xxa != 0.0F || this.chimpanzee.yya != 0.0F || this.chimpanzee.zza != 0.0F) {
			return false;
		} else if (this.chimpanzee.getAction().canSit()) {
			if (!this.chimpanzee.isLeader() && this.chimpanzee.getRandom().nextInt(60) == 0) {
				Predicate<ChimpanzeeEntity> predicate = (chimpanzeeentity) -> {
					return chimpanzeeentity != this.chimpanzee && chimpanzeeentity.isLeader();
				};
				List<ChimpanzeeEntity> list = this.chimpanzee.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().inflate(6.0D, 4.0D, 6.0D), predicate);

				for (ChimpanzeeEntity chimpanzeeentity : list) {
					if (chimpanzeeentity.isSitting()) {
						return true;
					}
				}
			} else if (this.chimpanzee.level.isNight()) {
				return this.chimpanzee.getRandom().nextInt(300) == 0;
			} else return this.chimpanzee.getRandom().nextInt(2400) == 0;
		}

		return false;
	}

	@Override
	public void start() {
		this.sitTimer = 200 + this.chimpanzee.getRandom().nextInt(600);
		this.chimpanzee.setSitting(true);
	}

	@Override
	public boolean canContinueToUse() {
		if (!this.chimpanzee.isSitting() || !this.chimpanzee.getAction().canSit() || this.chimpanzee.isPassenger()) {
			return false;
		} else if (!this.chimpanzee.canStandUp()) {
			return true;
		} else if (this.chimpanzee.isInWater()) {
			return false;
		} else if (this.sitTimer <= 0) {
			return false;
		} else return this.chimpanzee.xxa == 0.0F && this.chimpanzee.yya == 0.0F && this.chimpanzee.zza == 0.0F;
	}

	@Override
	public void stop() {
		this.cooldown = 100;
		this.chimpanzee.setSitting(false);
	}

	@Override
	public void tick() {
		if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.DRUMMING)) {
			--this.sitTimer;
		}
	}
}
