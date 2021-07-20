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

	@Override
	public boolean canUse() {
		if (!this.entity.level.isRaining()) {
			return false;
		} else {
			BlockPos position = this.entity.blockPosition();
			Biome biome = this.entity.level.getBiome(position);
			return biome.getPrecipitation() == Biome.RainType.RAIN && biome.getTemperature(position) >= 0.15F && GroundPathHelper.hasGroundPathNavigation(this.entity);
		}
	}

	@Override
	public void start() {
		((GroundPathNavigator)this.entity.getNavigation()).setAvoidSun(true);
	}

	@Override
	public void stop() {
		if (GroundPathHelper.hasGroundPathNavigation(this.entity)) {
			((GroundPathNavigator)this.entity.getNavigation()).setAvoidSun(false);
		}
	}
}