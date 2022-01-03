package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class ChimpShakeBundleGoal extends MoveToBlockGoal {
	private final ChimpanzeeEntity chimpanzee;
	private BlockPos bundlePos = BlockPos.ZERO;
	private int shakingTime;
	private int nextBananaTime;

	public ChimpShakeBundleGoal(ChimpanzeeEntity chimpanzeeIn, double speed, int length, int yMax) {
		super(chimpanzeeIn, speed, length, yMax);
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isDirty() || this.chimpanzee.needsSunlight()) {
			return false;
		} else if (!this.chimpanzee.needsSnack()) {
			return false;
		} else if (this.chimpanzee.isBaby()) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else {
			return super.canUse();
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.shakingTime > 160) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else {
			return super.canContinueToUse();
		}
	}

	@Override
	public void start() {
		super.start();
		this.shakingTime = 0;
		this.nextBananaTime = 30 + this.getNextBananaTime();
		this.chimpanzee.setLookingForBundle(true);
	}

	@Override
	public void stop() {
		super.stop();
		this.chimpanzee.setDefaultAction();
		this.chimpanzee.setLookingForBundle(false);
	}

	@Override
	public void tick() {
		super.tick();

		this.chimpanzee.getLookControl().setLookAt(this.bundlePos.getX() + 0.5D, this.bundlePos.getY() + 0.5D, this.bundlePos.getZ() + 0.5D, (float) (this.chimpanzee.getMaxHeadYRot() + 20), (float) this.chimpanzee.getMaxHeadXRot());

		if (this.chimpanzee.getY(1.0D) <= this.bundlePos.getY() && this.bundlePos.closerThan(this.chimpanzee.position().add(new Vec3(0.0D, this.chimpanzee.getBbHeight(), 0.0D)), 1.0D)) {
			this.chimpanzee.setDeltaMovement(this.chimpanzee.getDeltaMovement().multiply(0.4D, 0.0D, 0.4D).add(0.0D, 0.1D, 0.0D));

			if (this.shakingTime > 30) {
				this.chimpanzee.setAction(ChimpanzeeAction.SHAKING);

				if (this.shakingTime >= this.nextBananaTime) {
					double d0 = this.bundlePos.getX() + this.chimpanzee.getRandom().nextDouble() * 0.5D + 0.25D;
					double d1 = this.bundlePos.getZ() + this.chimpanzee.getRandom().nextDouble() * 0.5D + 0.25D;

					if (this.chimpanzee.getRandom().nextInt(4) == 0) {
						BananaPeelEntity bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(this.chimpanzee.level);
						bananapeel.moveTo(d0, this.bundlePos.getY() - 0.5D, d1, this.chimpanzee.getYRot(), 0.0F);
						bananapeel.setDeltaMovement(this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D, -0.1D, this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D);
						this.chimpanzee.level.addFreshEntity(bananapeel);
					} else {
						ItemEntity itementity = new ItemEntity(this.chimpanzee.level, d0, this.bundlePos.getY() - 0.25D, d1, new ItemStack(NeapolitanItems.BANANA_BUNCH.get()));
						itementity.setDeltaMovement(this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D, -0.1D, this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D);
						itementity.setDefaultPickUpDelay();
						this.chimpanzee.level.addFreshEntity(itementity);
					}

					this.nextBananaTime = this.shakingTime + this.getNextBananaTime();
				}
			} else {
				this.chimpanzee.setAction(ChimpanzeeAction.HANGING);
			}

			++this.shakingTime;
		} else {
			if (this.isReachedTarget() && this.chimpanzee.getAction().canBeInterrupted() && this.chimpanzee.isOnGround()) {
				this.chimpanzee.setJumping(true);
				double d0 = this.blockPos.getX() + 0.5D - this.chimpanzee.getX();
				double d1 = this.blockPos.getZ() + 0.5D - this.chimpanzee.getZ();
				double d2 = this.bundlePos.getY() - this.chimpanzee.getY() + 1.0D;
				double d3 = 0.8D - d2 * 0.1D;
				this.chimpanzee.setDeltaMovement(this.chimpanzee.getDeltaMovement().multiply(0.9D, 1.0D, 0.9D).add(d0 * d3, 0.3D + d2 * 0.1D, d1 * d3));
			}

			this.chimpanzee.setDefaultAction();
			this.shakingTime = 0;
			this.nextBananaTime = 30 + this.getNextBananaTime();
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).isCollisionShapeFullBlock(worldIn, pos)) {
			for (int i = 1; i < 7; ++i) {
				BlockPos blockpos = pos.above(i);
				if (i > 2 && worldIn.getBlockState(blockpos).getBlock() == NeapolitanBlocks.BANANA_BUNDLE.get()) {
					if (this.getBlockBeingShaken((Level) worldIn, blockpos)) {
						return false;
					} else {
						this.bundlePos = blockpos;
						return true;
					}
				} else if (worldIn.getBlockState(blockpos).isCollisionShapeFullBlock(worldIn, pos.above(i))) {
					return false;
				}
			}
		}

		return false;
	}

	private int getNextBananaTime() {
		return 5 + this.chimpanzee.getRandom().nextInt(30);
	}

	private boolean getBlockBeingShaken(Level worldIn, BlockPos pos) {
		return !worldIn.getEntitiesOfClass(ChimpanzeeEntity.class, new AABB(pos.below()), (chimpanzee) -> {
			return chimpanzee != this.chimpanzee && chimpanzee.isDoingAction(ChimpanzeeAction.HANGING, ChimpanzeeAction.SHAKING);
		}).isEmpty();
	}
}
