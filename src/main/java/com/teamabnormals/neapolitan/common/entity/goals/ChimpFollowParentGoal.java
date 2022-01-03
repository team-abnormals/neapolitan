package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ChimpFollowParentGoal extends FollowParentGoal {
	public ChimpFollowParentGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}
}
