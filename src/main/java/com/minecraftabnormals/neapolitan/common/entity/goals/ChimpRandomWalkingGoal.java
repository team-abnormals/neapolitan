package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.util.math.vector.Vector3d;

public class ChimpRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpRandomWalkingGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return this.chimpanzee.isSitting() ? false : super.canUse();
	}

	@Nullable
	protected Vector3d getPosition() {
		if (!this.chimpanzee.isLeader() && !this.chimpanzee.isInWaterOrBubble()) {
			Predicate<ChimpanzeeEntity> predicate = (chimpanzeeentity) -> {
				return chimpanzeeentity != this.chimpanzee && chimpanzeeentity.getAge() >= 0;
			};
			List<ChimpanzeeEntity> list = this.chimpanzee.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().inflate(12.0D, 8.0D, 12.0D), predicate);

			if (!list.isEmpty()) {
				return this.chimpanzee.getRandom().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.mob, 6, 3) : RandomPositionGenerator.getPos(this.mob, 6, 3);
			}
		}

		return this.mob.getRandom().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.mob, 10, 7) : super.getPosition();
	}
}
