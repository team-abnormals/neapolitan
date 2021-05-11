package com.minecraftabnormals.neapolitan.common.entity.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.GroundPathHelper;

public class RestrictRainGoal extends Goal {
	private final CreatureEntity entity;

	public RestrictRainGoal(CreatureEntity creature) {
		this.entity = creature;
	}

	public boolean shouldExecute() {
		return this.entity.world.isRaining() && GroundPathHelper.isGroundNavigator(this.entity);
	}

	public void startExecuting() {
		((GroundPathNavigator)this.entity.getNavigator()).setAvoidSun(true);
	}

	public void resetTask() {
		if (GroundPathHelper.isGroundNavigator(this.entity)) {
			((GroundPathNavigator)this.entity.getNavigator()).setAvoidSun(false);
		}
	}
}