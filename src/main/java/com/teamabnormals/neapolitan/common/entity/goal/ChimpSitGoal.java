package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class ChimpSitGoal extends Goal {
	private final Chimpanzee chimpanzee;
	private int sitTimer;
	private int cooldown;

	public ChimpSitGoal(Chimpanzee chimpanzeeIn) {
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
			if (!this.chimpanzee.isLeader() && this.chimpanzee.getRandom().nextInt(30) == 0) {
				Predicate<Chimpanzee> predicate = (chimpanzeeentity) -> {
					return chimpanzeeentity != this.chimpanzee && chimpanzeeentity.isLeader();
				};
				List<Chimpanzee> list = this.chimpanzee.level.getEntitiesOfClass(Chimpanzee.class, this.chimpanzee.getBoundingBox().inflate(6.0D, 4.0D, 6.0D), predicate);

				for (Chimpanzee chimpanzeeentity : list) {
					if (chimpanzeeentity.isSitting()) {
						return true;
					}
				}
			} else if (this.chimpanzee.level.isNight()) {
				return this.chimpanzee.getRandom().nextInt(150) == 0;
			} else return this.chimpanzee.getRandom().nextInt(1200) == 0;
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
		this.cooldown = this.adjustedTickDelay(100);
		this.chimpanzee.setSitting(false);
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.DRUMMING)) {
			--this.sitTimer;
		}
	}
}
