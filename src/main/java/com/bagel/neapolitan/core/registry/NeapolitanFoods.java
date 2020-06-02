package com.bagel.neapolitan.core.registry;

import net.minecraft.item.Food;

public class NeapolitanFoods {
	public static final Food COOKIE = (new Food.Builder()).hunger(2).saturation(0.3F).fastToEat().build();
	public static final Food CHOCOLATE_BAR = (new Food.Builder()).hunger(2).saturation(0.1F).build();
	public static final Food STRAWBERRY = (new Food.Builder()).hunger(2).saturation(0.1F).build();
	public static final Food CHOCOLATE_STRAWBERRY = (new Food.Builder()).hunger(2).saturation(0.1F).build();
}
