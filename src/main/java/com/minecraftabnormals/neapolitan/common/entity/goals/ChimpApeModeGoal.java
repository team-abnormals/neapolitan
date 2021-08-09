package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class ChimpApeModeGoal extends RandomWalkingGoal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpApeModeGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed, 40);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return this.chimpanzee.getApeModeTime() > 0 ? super.canUse() : false;
	}

	@Nullable
	@Override
	protected Vector3d getPosition() {
		if (this.mob.isInWaterOrBubble()) {
			Vector3d vector3d = RandomPositionGenerator.getLandPos(this.mob, 15, 7);
			return vector3d == null ? super.getPosition() : vector3d;
		} else {
			return this.mob.getRandom().nextFloat() >= 0.001F ? RandomPositionGenerator.getLandPos(this.mob, 10, 7) : super.getPosition();
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
