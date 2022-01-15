package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ChimpJumpOnBouncyGoal extends MoveToBlockGoal {
	private final ChimpanzeeEntity chimpanzee;
	private int jumps;

	public ChimpJumpOnBouncyGoal(ChimpanzeeEntity chimpanzeeIn, double speed, int length) {
		super(chimpanzeeIn, speed, length, 6);
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.DEFAULT, ChimpanzeeAction.CLIMBING)) {
			return false;
		} else if (this.chimpanzee.isBaby() && this.chimpanzee.getRandom().nextInt(400) != 0) {
			return false;
		} else if (!this.chimpanzee.isBaby() && this.chimpanzee.getRandom().nextInt(1200) != 0) {
			return false;
		} else {
			return this.findNearestBlock();
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.jumps > 10) {
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
	}

	@Override
	public void stop() {
		super.stop();
		this.jumps = 0;
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		super.tick();

		if (this.isReachedTarget() && this.chimpanzee.getAction().canBeInterrupted()) {
			this.chimpanzee.setAction(ChimpanzeeAction.JUMPING);
			if (this.chimpanzee.isOnGround()) {
				this.chimpanzee.getJumpControl().jump();
				Vec3 vector3d = this.chimpanzee.getDeltaMovement();
				this.chimpanzee.setDeltaMovement(new Vec3(vector3d.x * 0.1D, vector3d.y, vector3d.z * 0.1D));

				++this.jumps;
			}
		} else {
			this.chimpanzee.setDefaultAction();
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		return worldIn.isEmptyBlock(pos.above()) && worldIn.isEmptyBlock(pos.above().above()) && worldIn.getBlockState(pos).is(NeapolitanBlockTags.CHIMPANZEE_JUMPING_BLOCKS) && !this.isBlockBeingJumpedOn((Level) worldIn, pos);
	}

	private boolean isBlockBeingJumpedOn(Level worldIn, BlockPos pos) {
		return !worldIn.getEntitiesOfClass(ChimpanzeeEntity.class, new AABB(pos.above()), (chimpanzee) -> {
			return chimpanzee != this.chimpanzee && chimpanzee.isDoingAction(ChimpanzeeAction.JUMPING);
		}).isEmpty();
	}
}
