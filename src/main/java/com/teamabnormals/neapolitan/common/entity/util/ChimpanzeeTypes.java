package com.teamabnormals.neapolitan.common.entity.util;

import java.util.Arrays;
import java.util.Comparator;

public enum ChimpanzeeTypes {
	JUNGLE(0),
	RAINFOREST(1),
	BAMBOO(2);

	private static final ChimpanzeeTypes[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(ChimpanzeeTypes::getId)).toArray(ChimpanzeeTypes[]::new);

	private final int id;

	ChimpanzeeTypes(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static ChimpanzeeTypes byId(int id) {
		if (id < 0 || id >= VALUES.length) {
			id = 0;
		}
		return VALUES[id];
	}
}