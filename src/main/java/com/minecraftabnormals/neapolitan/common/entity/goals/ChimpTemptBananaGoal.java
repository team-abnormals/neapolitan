package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;

public class ChimpTemptBananaGoal extends Goal {
	private static final EntityPredicate ENTITY_PREDICATE = (new EntityPredicate()).range(10.0D).allowInvulnerable().allowSameTeam().allowNonAttackable().allowUnseeable();
	protected final ChimpanzeeEntity chimpanzee;
	private final double speed;
	protected PlayerEntity closestPlayer;
	private int delayTemptCounter;
	private int forgetTimer;
	private int patience;
	private double playerDistance;

	public ChimpTemptBananaGoal(ChimpanzeeEntity chimpanzeeIn, double speedIn) {
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
			this.closestPlayer = this.chimpanzee.level.getNearestPlayer(ENTITY_PREDICATE, this.chimpanzee);
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
		this.forgetTimer = 100;
		this.patience = 60;
		this.playerDistance = this.chimpanzee.distanceToSqr(this.closestPlayer);
	}

	@Override
	public void stop() {
		this.closestPlayer = null;
		this.chimpanzee.getNavigation().stop();
		this.delayTemptCounter = 100;
	}

	@Override
	public void tick() {
		this.chimpanzee.getLookControl().setLookAt(this.closestPlayer, (float)(this.chimpanzee.getMaxHeadYRot() + 20), (float)this.chimpanzee.getMaxHeadXRot());
		if (this.chimpanzee.distanceToSqr(this.closestPlayer) < 6.25D) {
			this.chimpanzee.getNavigation().stop();
		} else {
			this.chimpanzee.getNavigation().moveTo(this.closestPlayer, this.speed);
		}
		
		double d0 = this.chimpanzee.distanceToSqr(this.closestPlayer);
		
		if (this.playerDistance < d0 && this.closestPlayer.getDeltaMovement() != Vector3d.ZERO) {
			--this.patience;
		}
		
		this.playerDistance = d0;
		
		if (!this.chimpanzee.isBaby() && this.patience <= 0 && !this.closestPlayer.abilities.instabuild) {
			this.chimpanzee.setTarget(this.closestPlayer);
		}
		
		if (!this.isTempting(this.closestPlayer.getMainHandItem()) && !this.isTempting(this.closestPlayer.getOffhandItem())) {
			--this.forgetTimer;
		} else {
			this.forgetTimer = 160;
		}
	}
}
