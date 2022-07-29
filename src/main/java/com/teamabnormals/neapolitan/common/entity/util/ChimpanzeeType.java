package com.teamabnormals.neapolitan.common.entity.util;

import java.util.Arrays;
import java.util.Comparator;

public enum ChimpanzeeType {
	JUNGLE(0),
	RAINFOREST(1),
	BAMBOO(2);

	private static final ChimpanzeeType[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(ChimpanzeeType::getId)).toArray(ChimpanzeeType[]::new);

	private final int id;

	ChimpanzeeType(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static ChimpanzeeType byId(int id) {
		if (id < 0 || id >= VALUES.length) {
			id = 0;
		}
		return VALUES[id];
	}
}