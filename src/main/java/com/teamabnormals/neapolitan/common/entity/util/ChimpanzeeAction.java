package com.teamabnormals.neapolitan.common.entity.util;

import java.util.Arrays;
import java.util.Comparator;

public enum ChimpanzeeAction {
	DEFAULT(0, true, true),
	CLIMBING(1, false, false),
	HANGING(2, false, false),
	EATING(3, false, true),
	GROOMING(4, false, true),
	SHAKING(5, false, false),
	CRYING(6, true, true),
	LOOKING_AT_ITEM(7, true, true),
	PLAYING_WITH_ITEM(8, true, true),
	PLAYING_WITH_HELMET(9, true, true),
	JUMPING(10, true, false),
	DRUMMING(11, true, true);

	private static final ChimpanzeeAction[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(ChimpanzeeAction::getId)).toArray(ChimpanzeeAction[]::new);

	private final int id;
	private final boolean canBeInterrupted;
	private final boolean canSit;

	ChimpanzeeAction(int idIn, boolean canBeInterruptedIn, boolean canSitIn) {
		this.id = idIn;
		this.canBeInterrupted = canBeInterruptedIn;
		this.canSit = canSitIn;
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

	public boolean canSit() {
		return this.canSit;
	}
}