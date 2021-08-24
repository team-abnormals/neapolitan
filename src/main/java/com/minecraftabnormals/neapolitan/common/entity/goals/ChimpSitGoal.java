package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.List;
import java.util.function.Predicate;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;

import net.minecraft.entity.ai.goal.Goal;

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
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (!this.chimpanzee.isOnGround()) {
			return false;
		} else if (this.chimpanzee.xxa != 0.0F || this.chimpanzee.yya != 0.0F || this.chimpanzee.zza != 0.0F) {
			return false;
		} else if (this.chimpanzee.getAction().canSit() && !this.chimpanzee.isPassenger()) {
			if (!this.chimpanzee.isLeader() && this.chimpanzee.getRandom().nextInt(60) == 0) {
				Predicate<ChimpanzeeEntity> predicate = (chimpanzeeentity) -> {
					return chimpanzeeentity != this.chimpanzee && chimpanzeeentity.isLeader();
				};
				List<ChimpanzeeEntity> list = this.chimpanzee.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().inflate(6.0D, 4.0D, 6.0D), predicate);

				for(ChimpanzeeEntity chimpanzeeentity : list) {
					if (chimpanzeeentity.isSitting()) {
						return true;
					}
				}
			} else if (this.chimpanzee.level.isNight()) {
				if (this.chimpanzee.getRandom().nextInt(300) == 0) {
					return true;
				}
			} else if (this.chimpanzee.getRandom().nextInt(2400) == 0) {
				return true;
			}
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
		if (!this.chimpanzee.isSitting()) {
			return false;
		} else if (this.sitTimer <= 0) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (this.chimpanzee.xxa != 0.0F || this.chimpanzee.yya != 0.0F || this.chimpanzee.zza != 0.0F) {
			return false;
		} else if (!this.chimpanzee.getAction().canSit()) {
			return false;
		} else {
			return true;
		}
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
