package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;

public class TemptBananaGoal extends Goal {
	private static final EntityPredicate ENTITY_PREDICATE = (new EntityPredicate()).setDistance(10.0D).allowInvulnerable().allowFriendlyFire().setSkipAttackChecks().setLineOfSiteRequired();
	protected final ChimpanzeeEntity chimpanzee;
	private final double speed;
	protected PlayerEntity closestPlayer;
	private int delayTemptCounter;
	private int forgetTimer;
	private int patience;
	private double playerDistance;

	public TemptBananaGoal(ChimpanzeeEntity chimpanzeeIn, double speedIn) {
		this.chimpanzee = chimpanzeeIn;
		this.speed = speedIn;

		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		if (this.delayTemptCounter > 0) {
			--this.delayTemptCounter;
			return false;
		} else if (!this.chimpanzee.isHungry()) {
			return false;
		} else if (!this.chimpanzee.getFood().isEmpty()) {
			return false;
		} else {
			this.closestPlayer = this.chimpanzee.world.getClosestPlayer(ENTITY_PREDICATE, this.chimpanzee);
			if (this.closestPlayer == null) {
				return false;
			} else {
				return this.isTempting(this.closestPlayer.getHeldItemMainhand()) || this.isTempting(this.closestPlayer.getHeldItemOffhand());
			}
		}
	}

	protected boolean isTempting(ItemStack stack) {
		return this.chimpanzee.isFood(stack);
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (this.forgetTimer <= 0) {
			return false;
		} else if (this.closestPlayer == null || !ENTITY_PREDICATE.canTarget(this.chimpanzee, this.closestPlayer)) {
			return false;
		} else {
			return this.chimpanzee.isHungry() && this.chimpanzee.getFood().isEmpty() && ENTITY_PREDICATE.canTarget(this.chimpanzee, this.closestPlayer);
		}
	}

	@Override
	public void startExecuting() {
		this.forgetTimer = 100;
		this.patience = 60;
		this.playerDistance = this.chimpanzee.getDistanceSq(this.closestPlayer);
		
		this.chimpanzee.setTempting(true);
	}

	@Override
	public void resetTask() {
		this.closestPlayer = null;
		this.chimpanzee.getNavigator().clearPath();
		this.delayTemptCounter = 100;
		this.chimpanzee.setTempting(false);
	}

	@Override
	public void tick() {
		this.chimpanzee.getLookController().setLookPositionWithEntity(this.closestPlayer, (float)(this.chimpanzee.getHorizontalFaceSpeed() + 20), (float)this.chimpanzee.getVerticalFaceSpeed());
		if (this.chimpanzee.getDistanceSq(this.closestPlayer) < 6.25D) {
			this.chimpanzee.getNavigator().clearPath();
		} else {
			this.chimpanzee.getNavigator().tryMoveToEntityLiving(this.closestPlayer, this.speed);
		}
		
		double d0 = this.chimpanzee.getDistanceSq(this.closestPlayer);
		
		if (this.playerDistance < d0 && this.closestPlayer.getMotion() != Vector3d.ZERO) {
			--this.patience;
		}
		
		this.playerDistance = d0;
		
		if (!this.chimpanzee.isChild() && this.patience <= 0 && !this.closestPlayer.abilities.isCreativeMode) {
			this.chimpanzee.setAttackTarget(this.closestPlayer);
		}
		
		if (!this.isTempting(this.closestPlayer.getHeldItemMainhand()) && !this.isTempting(this.closestPlayer.getHeldItemOffhand())) {
			--this.forgetTimer;
		} else {
			this.forgetTimer = 160;
		}
	}
}
