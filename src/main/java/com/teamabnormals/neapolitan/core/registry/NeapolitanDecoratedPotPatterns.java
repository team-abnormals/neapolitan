package com.teamabnormals.neapolitan.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeapolitanDecoratedPotPatterns {
	public static final DeferredRegister<DecoratedPotPattern> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, Neapolitan.MOD_ID);

	public static final DeferredHolder<DecoratedPotPattern, ?> REFLECTION = register("reflection");
	public static final DeferredHolder<DecoratedPotPattern, ?> SCREAM = register("scream");
	public static final DeferredHolder<DecoratedPotPattern, ?> SNACK = register("snack");
	public static final DeferredHolder<DecoratedPotPattern, ?> SPIDER = register("spider");

	public static DeferredHolder<DecoratedPotPattern, ?> register(String name) {
		return DECORATED_POT_PATTERNS.register(name, () -> new DecoratedPotPattern(Neapolitan.location(name + "_pottery_pattern")));
	}

	public static void registerDecoratedPotPatterns() {
		DataUtil.registerDecoratedPotPattern(
				Pair.of(NeapolitanItems.REFLECTION_POTTERY_SHERD.get(), REFLECTION),
				Pair.of(NeapolitanItems.SCREAM_POTTERY_SHERD.get(), SCREAM),
				Pair.of(NeapolitanItems.SNACK_POTTERY_SHERD.get(), SNACK),
				Pair.of(NeapolitanItems.SPIDER_POTTERY_SHERD.get(), SPIDER)
		);
	}
}
