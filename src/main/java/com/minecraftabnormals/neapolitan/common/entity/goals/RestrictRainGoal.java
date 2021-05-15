package com.minecraftabnormals.neapolitan.common.entity.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.GroundPathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class RestrictRainGoal extends Goal {
	private final CreatureEntity entity;

	public RestrictRainGoal(CreatureEntity creature) {
		this.entity = creature;
	}

	public boolean shouldExecute() {
		if (!this.entity.world.isRaining()) {
			return false;
		} else {
			BlockPos position = this.entity.getPosition();
			Biome biome = this.entity.world.getBiome(position);
			return biome.getPrecipitation() == Biome.RainType.RAIN && biome.getTemperature(position) >= 0.15F && GroundPathHelper.isGroundNavigator(this.entity);
		}
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