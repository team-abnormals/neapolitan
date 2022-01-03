package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class ChimpRandomWalkingGoal extends WaterAvoidingRandomStrollGoal {
	private final ChimpanzeeEntity chimpanzee;

	public ChimpRandomWalkingGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.chimpanzee = chimpanzeeIn;
	}

	@Override
	public boolean canUse() {
		return !this.chimpanzee.isSitting() && super.canUse();
	}

	@Nullable
	protected Vec3 getPosition() {
		if (!this.chimpanzee.isLeader() && !this.chimpanzee.isInWaterOrBubble()) {
			Predicate<ChimpanzeeEntity> predicate = (chimpanzeeentity) -> {
				return chimpanzeeentity != this.chimpanzee && chimpanzeeentity.getAge() >= 0;
			};
			List<ChimpanzeeEntity> list = this.chimpanzee.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.chimpanzee.getBoundingBox().inflate(12.0D, 8.0D, 12.0D), predicate);

			if (!list.isEmpty()) {
				return this.chimpanzee.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.mob, 6, 3) : DefaultRandomPos.getPos(this.mob, 6, 3);
			}
		}

		return this.mob.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.mob, 10, 7) : super.getPosition();
	}
}
