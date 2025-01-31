package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanPoiTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiManager.Occupancy;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class ChimpShakeBundleGoal extends Goal {
	private static final Predicate<Holder<PoiType>> BUNDLE_PREDICATE = holder -> holder.is(NeapolitanPoiTypes.BANANA_BUNDLE.getKey());

	private final Chimpanzee chimpanzee;
	private final Level level;
	private final PoiManager poiManager;
	private final double moveSpeed;
	private BlockPos targetPos;
	private BlockPos bundlePos;
	private int nextStartTick;
	private int tryTicks;
	private int maxStayTicks;
	private int shakingTime;
	private int nextBananaTime;

	public ChimpShakeBundleGoal(Chimpanzee chimpanzee, double moveSpeed) {
		this.chimpanzee = chimpanzee;
		this.level = chimpanzee.level();
		this.poiManager = ((ServerLevel) this.level).getPoiManager();
		this.moveSpeed = moveSpeed;
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
			if (this.nextStartTick > 0) {
				--this.nextStartTick;
				return false;
			} else {
				this.nextStartTick = reducedTickDelay(200 + this.chimpanzee.getRandom().nextInt(200));
				return this.findNearestShakeableBundle();
			}
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.shakingTime > 160) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (!this.level.getBlockState(this.bundlePos).is(NeapolitanBlocks.BANANA_BUNDLE.get()) || this.findSafeGroundBelow(this.bundlePos) == null) {
			return false;
		} else if (this.isBundleOccupiedByOther(this.bundlePos)) {
			return false;
		} else {
			return this.tryTicks >= -this.maxStayTicks && this.tryTicks <= 1200;
		}
	}

	@Override
	public void start() {
		this.tryTicks = 0;
		this.maxStayTicks = this.chimpanzee.getRandom().nextInt(this.chimpanzee.getRandom().nextInt(1200) + 1200) + 1200;
		this.shakingTime = 0;
		this.nextBananaTime = 30 + this.getNextBananaTime();
		this.chimpanzee.setLookingForBundle(true);
		this.chimpanzee.getNavigation().moveTo(this.targetPos.getX() + 0.5D, this.targetPos.getY() + 1.0D, this.targetPos.getZ() + 0.5D, this.moveSpeed);
	}

	@Override
	public void stop() {
		this.chimpanzee.setDefaultAction();
		this.chimpanzee.setLookingForBundle(false);
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		this.chimpanzee.getLookControl().setLookAt(this.bundlePos.getX() + 0.5D, this.bundlePos.getY() + 0.5D, this.bundlePos.getZ() + 0.5D, (float) (this.chimpanzee.getMaxHeadYRot() + 20), (float) this.chimpanzee.getMaxHeadXRot());

		if (this.chimpanzee.getY(1.0D) <= this.bundlePos.getY() && this.bundlePos.closerToCenterThan(this.chimpanzee.position().add(0.0D, this.chimpanzee.getBbHeight(), 0.0D), 1.0D)) {
			this.chimpanzee.setDeltaMovement(new Vec3(0.0D, 0.1D, 0.0D));

			if (this.shakingTime > 30) {
				this.chimpanzee.setAction(ChimpanzeeAction.SHAKING);

				if (this.shakingTime >= this.nextBananaTime) {
					double d0 = this.bundlePos.getX() + this.chimpanzee.getRandom().nextDouble() * 0.5D + 0.25D;
					double d1 = this.bundlePos.getZ() + this.chimpanzee.getRandom().nextDouble() * 0.5D + 0.25D;

					if (this.chimpanzee.getRandom().nextInt(4) == 0) {
						BananaPeel bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(this.level);
						bananapeel.moveTo(d0, this.bundlePos.getY() - 0.5D, d1, this.chimpanzee.getYRot(), 0.0F);
						bananapeel.setDeltaMovement(this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D, -0.1D, this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D);
						this.level.addFreshEntity(bananapeel);
					} else {
						ItemEntity itementity = new ItemEntity(this.level, d0, this.bundlePos.getY() - 0.25D, d1, new ItemStack(NeapolitanItems.BANANA_BUNCH.get()));
						itementity.setDeltaMovement(this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D, -0.1D, this.chimpanzee.getRandom().nextDouble() * 0.4D - 0.2D);
						itementity.setDefaultPickUpDelay();
						this.level.addFreshEntity(itementity);
					}

					this.nextBananaTime = this.shakingTime + this.getNextBananaTime();
				}
			} else {
				this.chimpanzee.setAction(ChimpanzeeAction.HANGING);
			}

			++this.shakingTime;
		} else {
			if (this.targetPos.closerToCenterThan(this.chimpanzee.position(), 1.0D) && this.chimpanzee.getAction().canBeInterrupted() && this.chimpanzee.onGround()) {
				this.chimpanzee.setJumping(true);
				double dx = this.targetPos.getX() + 0.5D - this.chimpanzee.getX();
				double dy = this.bundlePos.getY() - this.chimpanzee.getY() + 1.0D;
				double dz = this.targetPos.getZ() + 0.5D - this.chimpanzee.getZ();
				double d3 = 0.8D - dy * 0.1D;
				this.chimpanzee.setDeltaMovement(this.chimpanzee.getDeltaMovement().multiply(0.5D, 1.0D, 0.5D).add(dx * d3, 0.3D + dy * 0.1D, dz * d3));
				--this.tryTicks;
			} else {
				if (this.tryTicks % 40 == 0) {
					this.chimpanzee.getNavigation().moveTo(this.targetPos.getX() + 0.5D, this.targetPos.getY() + 1.0D, this.targetPos.getZ() + 0.5D, this.moveSpeed);
				}
				++this.tryTicks;
			}

			this.chimpanzee.setDefaultAction();
			this.shakingTime = 0;
			this.nextBananaTime = 30 + this.getNextBananaTime();
		}
	}

	private boolean findNearestShakeableBundle() {
		BlockPos blockpos = this.chimpanzee.blockPosition();

		List<PoiRecord> list = this.poiManager.getInRange(BUNDLE_PREDICATE, blockpos, 48, Occupancy.HAS_SPACE).sorted(Comparator.comparingDouble(poi -> poi.getPos().distSqr(blockpos))).toList();
		for (PoiRecord poi : list) {
			if (!this.isBundleOccupiedByOther(poi.getPos())) {
				BlockPos blockpos1 = this.findSafeGroundBelow(poi.getPos());
				if (blockpos1 != null && this.chimpanzee.isWithinRestriction(blockpos1)) {
					this.targetPos = blockpos1;
					this.bundlePos = poi.getPos();
					return true;
				}
			}
		}

		return false;
	}

	private BlockPos findSafeGroundBelow(BlockPos pos) {
		MutableBlockPos mutable = pos.mutable();
		for (int i = 1; i < 7; ++i) {
			mutable.move(Direction.DOWN);
			if (i > 2 && !GoalUtils.isNotStable(this.chimpanzee.getNavigation(), mutable)) {
				return mutable.immutable();
			} else if (GoalUtils.isSolid(this.chimpanzee, mutable) || GoalUtils.hasMalus(this.chimpanzee, mutable)) {
				return null;
			}
		}
		return null;
	}

	private boolean isBundleOccupiedByOther(BlockPos pos) {
		return !this.level.getEntitiesOfClass(Chimpanzee.class, new AABB(pos.below()), (chimpanzee) -> chimpanzee != this.chimpanzee && chimpanzee.isDoingAction(ChimpanzeeAction.HANGING, ChimpanzeeAction.SHAKING)).isEmpty();
	}

	private int getNextBananaTime() {
		return 5 + this.chimpanzee.getRandom().nextInt(30);
	}
}