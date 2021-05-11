package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.List;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;

public class ShareBananaGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private ChimpanzeeEntity buddy;
	private final double moveSpeed;
	private int delayCounter;
	private int throwTimer;
	private int lookTimer;

	public ShareBananaGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.moveSpeed = speed;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		if (this.chimpanzee.isChild() || this.chimpanzee.getFood().isEmpty() || this.chimpanzee.isHungry()) {
			return false;
		} else if (this.chimpanzee.getRNG().nextInt(80) == 0) {
			List<ChimpanzeeEntity> list = this.chimpanzee.world.getEntitiesWithinAABB(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
			ChimpanzeeEntity chimpanzeeentity = null;
			double d0 = Double.MAX_VALUE;

			for(ChimpanzeeEntity chimpanzeeentity1 : list) {
				if (chimpanzeeentity1.isHungry() && chimpanzeeentity1.getFood().isEmpty()) {
					double d1 = this.chimpanzee.getDistanceSq(chimpanzeeentity1);
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
	public boolean shouldContinueExecuting() {
		if (this.lookTimer >= 3) {
			return false;
		} else if (!this.buddy.isAlive()) {
			return false;
		} else if (this.lookTimer < 0) {
			if (this.chimpanzee.getFood().isEmpty() || !this.buddy.isHungry() || !this.buddy.getFood().isEmpty()) {
				return false;
			} 
		}

		return true;
	}

	@Override
	public void startExecuting() {
		this.delayCounter = 0;
		this.throwTimer = 4;
		this.lookTimer = -1;
	}

	@Override
	public void resetTask() {
		this.buddy = null;
	}

	@Override
	public void tick() {
		double d0 = this.chimpanzee.getDistanceSq(this.buddy);
		if (d0 < 9.0D) {
			this.chimpanzee.getNavigator().clearPath();
		}

		if (--this.delayCounter <= 0) {
			this.delayCounter = 10;
			
			if (this.lookTimer >= 0) {
				++this.lookTimer;
			}

			if (d0 < 9.0D) {
				if (--this.throwTimer <= 0) {
					Hand hand = this.chimpanzee.getFoodHand();
					ItemStack itemstack = this.chimpanzee.getHeldItem(hand);
					int i = itemstack.getCount();

					ItemEntity itementity = new ItemEntity(this.chimpanzee.world, this.chimpanzee.getPosX(), this.chimpanzee.getPosYEye() - (double)0.3F, this.chimpanzee.getPosZ(), itemstack.split(1));
					Vector3d vector3d = this.buddy.getPositionVec().subtract(this.chimpanzee.getPositionVec());
					vector3d = vector3d.normalize().scale((double)0.3F);
					itementity.setMotion(vector3d);
					itementity.setDefaultPickupDelay();
					this.chimpanzee.world.addEntity(itementity);

					this.chimpanzee.setHeldItem(hand, itemstack.split(i - 1));
					this.chimpanzee.setPickUpTimer(80);
					this.throwTimer = 12;
					this.lookTimer = 0;
				}
			} else {
				this.chimpanzee.getNavigator().tryMoveToEntityLiving(this.buddy, this.moveSpeed);
			}
		}

		this.chimpanzee.getLookController().setLookPositionWithEntity(this.buddy, 30.0F, 30.0F);
	}
}