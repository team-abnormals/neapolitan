package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;

public class ChimpFollowParentGoal extends FollowParentGoal {
	public ChimpFollowParentGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}
}
