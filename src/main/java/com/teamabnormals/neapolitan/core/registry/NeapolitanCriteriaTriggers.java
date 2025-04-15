package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.resources.ResourceLocation;

// TODO: Advancements
public class NeapolitanCriteriaTriggers {
	// public static final EmptyTrigger CREEPER_HEAL = CriteriaTriggers.register(new EmptyTrigger(prefix("creeper_heal")));
	// public static final EmptyTrigger VANILLA_POISON = CriteriaTriggers.register(new EmptyTrigger(prefix("vanilla_poison")));
	// public static final EmptyTrigger CHIMPANZEE_ATTACK = CriteriaTriggers.register(new EmptyTrigger(prefix("chimpanzee_attack")));

	private static ResourceLocation prefix(String name) {
		return Neapolitan.location(name);
	}
}
