package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeEntity;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ChimpFollowParentGoal extends FollowParentGoal {
	public ChimpFollowParentGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		super(chimpanzeeIn, speed);
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}
}
