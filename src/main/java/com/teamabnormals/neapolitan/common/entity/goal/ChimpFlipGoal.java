package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ChimpFlipGoal extends Goal {
	private final Chimpanzee chimpanzee;

	public ChimpFlipGoal(Chimpanzee chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		return this.chimpanzee.getApeModeTime() > 0 && !this.chimpanzee.isPassenger() && this.chimpanzee.onGround() && this.chimpanzee.isDoingAction(ChimpanzeeAction.DEFAULT) && this.chimpanzee.getRandom().nextInt(40) == 0;
	}

	@Override
	public void start() {
		this.chimpanzee.setAction(ChimpanzeeAction.JUMPING);
		this.chimpanzee.doFlip();
		this.chimpanzee.level().broadcastEntityEvent(this.chimpanzee, (byte) 9);
		this.chimpanzee.setSitting(false);

		this.chimpanzee.setJumping(true);
		Vec3 vec3 = this.chimpanzee.getLookAngle();
		this.chimpanzee.setDeltaMovement(this.chimpanzee.getDeltaMovement().multiply(0.1D, 1.0D, 0.1D).add(-vec3.x * 0.5D, 0.8D, -vec3.z * 0.5D));

		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public boolean canContinueToUse() {
		return !this.chimpanzee.isPassenger() && !this.chimpanzee.onGround() && !this.chimpanzee.isInWater();
	}

	@Override
	public void stop() {
		this.chimpanzee.setDefaultAction();
	}
}
