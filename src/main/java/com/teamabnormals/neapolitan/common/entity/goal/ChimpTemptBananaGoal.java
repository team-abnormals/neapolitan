package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ChimpTemptBananaGoal extends Goal {
	private static final TargetingConditions ENTITY_PREDICATE = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();
	protected final Chimpanzee chimpanzee;
	private final double speed;
	protected Player closestPlayer;
	private int delayTemptCounter;
	private int forgetTimer;
	private int patience;
	private double playerDistance;

	public ChimpTemptBananaGoal(Chimpanzee chimpanzeeIn, double speedIn) {
		this.chimpanzee = chimpanzeeIn;
		this.speed = speedIn;

		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.delayTemptCounter > 0) {
			--this.delayTemptCounter;
			return false;
		} else if (!this.chimpanzee.needsSnack()) {
			return false;
		} else {
			this.closestPlayer = this.chimpanzee.level().getNearestPlayer(ENTITY_PREDICATE, this.chimpanzee);
			if (this.closestPlayer == null) {
				return false;
			} else {
				return this.isTempting(this.closestPlayer.getMainHandItem()) || this.isTempting(this.closestPlayer.getOffhandItem());
			}
		}
	}

	protected boolean isTempting(ItemStack stack) {
		return this.chimpanzee.isSnack(stack);
	}

	@Override
	public boolean canContinueToUse() {
		if (this.forgetTimer <= 0) {
			return false;
		} else if (this.closestPlayer == null || !ENTITY_PREDICATE.test(this.chimpanzee, this.closestPlayer)) {
			return false;
		} else {
			return this.chimpanzee.needsSnack() && ENTITY_PREDICATE.test(this.chimpanzee, this.closestPlayer);
		}
	}

	@Override
	public void start() {
		this.forgetTimer = this.adjustedTickDelay(100);
		this.patience = this.adjustedTickDelay(100);
		this.playerDistance = this.chimpanzee.distanceToSqr(this.closestPlayer);
	}

	@Override
	public void stop() {
		this.closestPlayer = null;
		this.chimpanzee.getNavigation().stop();
		this.delayTemptCounter = this.adjustedTickDelay(100);
	}

	@Override
	public void tick() {
		this.chimpanzee.getLookControl().setLookAt(this.closestPlayer, (float) (this.chimpanzee.getMaxHeadYRot() + 20), (float) this.chimpanzee.getMaxHeadXRot());
		if (this.chimpanzee.distanceToSqr(this.closestPlayer) < 6.25D) {
			this.chimpanzee.getNavigation().stop();
		} else {
			this.chimpanzee.getNavigation().moveTo(this.closestPlayer, this.speed);
		}

		double d0 = this.chimpanzee.distanceToSqr(this.closestPlayer);

		if (this.playerDistance < d0 && this.closestPlayer.getDeltaMovement() != Vec3.ZERO) {
			--this.patience;
		}

		this.playerDistance = d0;

		if (!this.chimpanzee.isBaby() && this.patience <= 0 && !this.closestPlayer.getAbilities().instabuild) {
			this.chimpanzee.setTarget(this.closestPlayer);
		}

		if (!this.isTempting(this.closestPlayer.getMainHandItem()) && !this.isTempting(this.closestPlayer.getOffhandItem())) {
			--this.forgetTimer;
		} else {
			this.forgetTimer = this.adjustedTickDelay(200);
		}

		System.out.println(this.patience);
	}
}
