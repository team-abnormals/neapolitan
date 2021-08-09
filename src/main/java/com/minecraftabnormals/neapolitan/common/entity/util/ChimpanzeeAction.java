package com.minecraftabnormals.neapolitan.common.entity.util;

import java.util.Arrays;
import java.util.Comparator;

public enum ChimpanzeeAction {
	DEFAULT(0, true, false),
	CLIMBING(1, false, false),
	HANGING(2,  false, false),
	EATING(3, false, false),
	GROOMING(4, false, false),
	SHAKING(5,  false, false),
	HUNCHING(6,  true, true),
	CRYING(7,  true, false),
	LOOKING_AT_ITEM(8, true, false),
	PLAYING_WITH_ITEM(9, true, false),
	PLAYING_WITH_HELMET(10, true, false),
	DRUMMING(11, true, true);

	private static final ChimpanzeeAction[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(ChimpanzeeAction::getId)).toArray(ChimpanzeeAction[]::new);
	
	private final int id;
	private final boolean canBeInterrupted;
	private final boolean shouldSit;

	private ChimpanzeeAction(int idIn, boolean canBeInterruptedIn, boolean shouldSitIn) {
		this.id = idIn;
		this.canBeInterrupted = canBeInterruptedIn;
		this.shouldSit = shouldSitIn;
	}

	public int getId() {
		return this.id;
	}

	public static ChimpanzeeAction byId(int indexIn) {
		if (indexIn < 0 || indexIn >= VALUES.length) {
			indexIn = 0;
		}

		return VALUES[indexIn];
	}

	public boolean canBeInterrupted() {
		return this.canBeInterrupted;
	}

	public boolean shouldSit() {
		return this.shouldSit;
	}
}