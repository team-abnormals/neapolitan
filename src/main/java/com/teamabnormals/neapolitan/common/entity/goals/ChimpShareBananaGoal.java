package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class ChimpShareBananaGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private ChimpanzeeEntity buddy;
	private final double moveSpeed;
	private int delayCounter;
	private int throwTimer;
	private int lookTimer;

	public ChimpShareBananaGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.moveSpeed = speed;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isBaby() || this.chimpanzee.getSnack().isEmpty() || this.chimpanzee.isHungry()) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(100) == 0) {
			List<ChimpanzeeEntity> list = this.chimpanzee.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().inflate(8.0D, 4.0D, 8.0D));
			ChimpanzeeEntity chimpanzeeentity = null;
			double d0 = Double.MAX_VALUE;

			for (ChimpanzeeEntity chimpanzeeentity1 : list) {
				if (chimpanzeeentity1.isHungry() && chimpanzeeentity1.getSnack().isEmpty() && !chimpanzeeentity1.isDoingAction(ChimpanzeeAction.HANGING, ChimpanzeeAction.SHAKING)) {
					double d1 = this.chimpanzee.distanceToSqr(chimpanzeeentity1);
					if (!(d1 > d0)) {
						d0 = d1;
						chimpanzeeentity = chimpanzeeentity1;
					}
				}
			}

			if (chimpanzeeentity != null) {
				this.buddy = chimpanzeeentity;
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canContinueToUse() {
		if (this.lookTimer >= 3) {
			return false;
		} else if (!this.buddy.isAlive()) {
			return false;
		} else if (this.lookTimer < 0) {
			if (this.chimpanzee.getSnack().isEmpty() || !this.buddy.isHungry() || !this.buddy.getSnack().isEmpty() || this.buddy.isDoingAction(ChimpanzeeAction.HANGING, ChimpanzeeAction.SHAKING)) {
				return false;
			}
		}

		return this.chimpanzee.distanceToSqr(this.buddy) <= 256.0D;
	}

	@Override
	public void start() {
		this.delayCounter = 0;
		this.throwTimer = 4;
		this.lookTimer = -1;
	}

	@Override
	public void stop() {
		this.buddy = null;
	}

	@Override
	public void tick() {
		double d0 = this.chimpanzee.distanceToSqr(this.buddy);
		if (d0 < 9.0D) {
			this.chimpanzee.getNavigation().stop();
		}

		if (--this.delayCounter <= 0) {
			this.delayCounter = 10;

			if (this.lookTimer >= 0) {
				++this.lookTimer;
			}

			if (d0 < 9.0D) {
				if (--this.throwTimer <= 0) {
					InteractionHand hand = this.chimpanzee.getSnackHand();
					ItemStack itemstack = this.chimpanzee.getItemInHand(hand);
					int i = itemstack.getCount();

					ItemEntity itementity = new ItemEntity(this.chimpanzee.level, this.chimpanzee.getX(), this.chimpanzee.getEyeY() - (double) 0.3F, this.chimpanzee.getZ(), itemstack.split(1));
					Vec3 vector3d = this.buddy.position().subtract(this.chimpanzee.position());
					vector3d = vector3d.normalize().scale(0.3F);
					itementity.setDeltaMovement(vector3d);
					itementity.setDefaultPickUpDelay();
					this.chimpanzee.level.addFreshEntity(itementity);

					this.chimpanzee.setItemInHand(hand, itemstack.split(i - 1));
					this.throwTimer = 12;
					this.lookTimer = 0;
				}
			} else {
				this.chimpanzee.getNavigation().moveTo(this.buddy, this.moveSpeed);
			}
		}

		this.chimpanzee.getLookControl().setLookAt(this.buddy, 30.0F, 30.0F);
	}
}