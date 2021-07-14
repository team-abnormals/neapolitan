package com.minecraftabnormals.neapolitan.common.entity.util;

import java.util.Arrays;
import java.util.Comparator;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

public enum ChimpanzeeAction {
	DEFAULT(0, true, false),
	CLIMBING(1, false, false),
	HANGING(2,  false, false),
	EATING(3, false, false),
	GROOMING(4, false, false),
	SHAKING(5,  false, false),
	HUNCHING(6,  true, true),
	CRYING(7,  true, false),
	DRUMMING(8, true, true);

	private static final ChimpanzeeAction[] ACTIONS = Arrays.stream(values()).sorted(Comparator.comparingInt(ChimpanzeeAction::getIndex)).toArray((p_221102_0_) -> {
		return new ChimpanzeeAction[p_221102_0_];
	});
	private final int index;
	private final boolean canBeInterrupted;
	private final boolean shouldSit;

	private ChimpanzeeAction(int indexIn, boolean canBeInterruptedIn, boolean shouldSitIn) {
		this.index = indexIn;
		this.canBeInterrupted = canBeInterruptedIn;
		this.shouldSit = shouldSitIn;
	}

	public int getIndex() {
		return this.index;
	}

	public static ChimpanzeeAction byIndex(int indexIn) {
		if (indexIn < 0 || indexIn >= ACTIONS.length) {
			indexIn = 0;
		}

		return ACTIONS[indexIn];
	}

	public boolean canBeInterrupted() {
		return this.canBeInterrupted;
	}

	public boolean shouldSit() {
		return this.shouldSit;
	}
}