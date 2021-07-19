package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

import com.minecraftabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;

public class ShakeBundleGoal extends MoveToBlockGoal {
	private final ChimpanzeeEntity chimpanzee;
	private BlockPos bundlePos = BlockPos.ZERO;
	private int shakingTime;
	private int nextBananaTime;

	public ShakeBundleGoal(ChimpanzeeEntity chimpanzeeIn, double speed, int length, int yMax) {
		super(chimpanzeeIn, speed, length, yMax);
		this.chimpanzee = chimpanzeeIn;
		this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		if (this.chimpanzee.isDirty() || this.chimpanzee.needsSunlight()) {
			return false;
		} else if (!this.chimpanzee.needsFood()) {
			return false;
		} else if (this.chimpanzee.isChild()) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else {
			return super.shouldExecute();
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (this.shakingTime > 160) {
			return false;
		} else {
			return super.shouldContinueExecuting();
		}
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		this.shakingTime = 0;
		this.nextBananaTime = 30 + this.getNextBananaTime();
	}

	@Override
	public void resetTask() {
		super.resetTask();
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		super.tick();

		this.chimpanzee.getLookController().setLookPosition(this.bundlePos.getX() + 0.5D, this.bundlePos.getY() + 0.5D, this.bundlePos.getZ() + 0.5D, (float)(this.chimpanzee.getHorizontalFaceSpeed() + 20), (float)this.chimpanzee.getVerticalFaceSpeed());

		if (this.chimpanzee.getPosYHeight(1.0D) <= this.bundlePos.getY() && this.bundlePos.withinDistance(this.chimpanzee.getPositionVec().add(new Vector3d(0.0D, this.chimpanzee.getHeight(), 0.0D)), 1.0D)) {
			this.chimpanzee.setMotion(this.chimpanzee.getMotion().mul(0.4D, 0.0D, 0.4D).add(0.0D, 0.1D, 0.0D));

			if (this.shakingTime > 30) {
				this.chimpanzee.setAction(ChimpanzeeAction.SHAKING);

				if (this.shakingTime >= this.nextBananaTime) {
					double d0 = this.bundlePos.getX() + this.chimpanzee.getRNG().nextDouble() * 0.5D + 0.25D;
					double d1 = this.bundlePos.getZ() + this.chimpanzee.getRNG().nextDouble() * 0.5D + 0.25D;
							
					if (this.chimpanzee.getRNG().nextInt(4) == 0) {
						BananaPeelEntity bananapeel = NeapolitanEntities.BANANA_PEEL.get().create(this.chimpanzee.world);
						bananapeel.setLocationAndAngles(d0, this.bundlePos.getY() - 0.5D, d1, this.chimpanzee.rotationYaw, 0.0F);
						bananapeel.setMotion(this.chimpanzee.getRNG().nextDouble() * 0.4D - 0.2D, -0.1D, this.chimpanzee.getRNG().nextDouble() * 0.4D - 0.2D);
						this.chimpanzee.world.addEntity(bananapeel);
					} else {
						ItemEntity itementity = new ItemEntity(this.chimpanzee.world, d0, this.bundlePos.getY() - 0.25D, d1, new ItemStack(NeapolitanItems.BANANA_BUNCH.get()));
						itementity.setMotion(this.chimpanzee.getRNG().nextDouble() * 0.4D - 0.2D, -0.1D, this.chimpanzee.getRNG().nextDouble() * 0.4D - 0.2D);
						itementity.setDefaultPickupDelay();
						this.chimpanzee.world.addEntity(itementity);
					}

					this.nextBananaTime = this.shakingTime + this.getNextBananaTime();
				}
			} else {
				this.chimpanzee.setAction(ChimpanzeeAction.HANGING);
			}

			++this.shakingTime;
		} else {
			if (this.getIsAboveDestination() && this.chimpanzee.getAction().canBeInterrupted() && this.chimpanzee.isOnGround()) {
				this.chimpanzee.setJumping(true);
				double d0 = this.destinationBlock.getX() + 0.5D - this.chimpanzee.getPosX();
				double d1 = this.destinationBlock.getZ() + 0.5D - this.chimpanzee.getPosZ();
				double d2 = this.bundlePos.getY() - this.chimpanzee.getPosY() + 1.0D;
				double d3 = 0.8D - d2 * 0.1D;
				this.chimpanzee.setMotion(this.chimpanzee.getMotion().mul(0.9D, 1.0D, 0.9D).add(d0 * d3, 0.3D + d2 * 0.1D, d1 * d3));
			}

			this.chimpanzee.setDefaultAction();
			this.shakingTime = 0;
			this.nextBananaTime = 30 + this.getNextBananaTime();
		}
	}

	@Override
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).hasOpaqueCollisionShape(worldIn, pos)) {
			for (int i = 1; i < 7; ++i) {
				BlockPos blockpos = pos.up(i);
				if (i > 2 && worldIn.getBlockState(blockpos).getBlock() == NeapolitanBlocks.BANANA_BUNDLE.get()) {
					this.bundlePos = blockpos;
					return true;
				} else if (worldIn.getBlockState(blockpos).hasOpaqueCollisionShape(worldIn, pos.up(i))) {
					return false;
				}
			}
		}

		return false;
	}

	private int getNextBananaTime() {
		return 5 + this.chimpanzee.getRNG().nextInt(30);
	}
}
