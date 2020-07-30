package com.minecraftabnormals.neapolitan.core.util;

import net.minecraft.util.IStringSerializable;

public enum StrawberryType implements IStringSerializable {
	NONE("none"), 
	RED("red"), 
	WHITE("white");

	private final String name;

	private StrawberryType(String name) {
		this.name = name;
	}

	@Override
	public String getString() {
		return this.name;
	}
}