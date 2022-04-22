package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ChimpApeModeGoal extends RandomStrollGoal {
	private final Chimpanzee chimpanzee;

	public ChimpApeModeGoal(Chimpanzee chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed, 20);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return this.chimpanzee.getApeModeTime() > 0 && super.canUse();
	}

	@Nullable
	@Override
	protected Vec3 getPosition() {
		if (this.mob.isInWaterOrBubble()) {
			Vec3 vector3d = LandRandomPos.getPos(this.mob, 15, 7);
			return vector3d == null ? super.getPosition() : vector3d;
		} else {
			return this.mob.getRandom().nextFloat() >= 0.001F ? LandRandomPos.getPos(this.mob, 10, 7) : super.getPosition();
		}
	}

	@Override
	public void start() {
		super.start();
		if (this.mob.isOnGround()) {
			this.chimpanzee.getJumpControl().jump();
		}
	}
}
