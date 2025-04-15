package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeapolitanDecoratedPotPatterns {
	public static final DeferredRegister<DecoratedPotPattern> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, Neapolitan.MOD_ID);

	public static final DeferredHolder<DecoratedPotPattern, ?> REFLECTION = register("reflection_pottery_pattern");
	public static final DeferredHolder<DecoratedPotPattern, ?> SCREAM = register("scream_pottery_pattern");
	public static final DeferredHolder<DecoratedPotPattern, ?> SNACK = register("snack_pottery_pattern");
	public static final DeferredHolder<DecoratedPotPattern, ?> SPIDER = register("spider_pottery_pattern");

	public static DeferredHolder<DecoratedPotPattern, ?> register(String assetId) {
		return DECORATED_POT_PATTERNS.register(assetId, () -> new DecoratedPotPattern(Neapolitan.location(assetId)));
	}

	public static void registerDecoratedPotPatterns() {
		DataUtil.registerDecoratedPotPattern(NeapolitanItems.REFLECTION_POTTERY_SHERD.get(), REFLECTION);
		DataUtil.registerDecoratedPotPattern(NeapolitanItems.SCREAM_POTTERY_SHERD.get(), SCREAM);
		DataUtil.registerDecoratedPotPattern(NeapolitanItems.SNACK_POTTERY_SHERD.get(), SNACK);
		DataUtil.registerDecoratedPotPattern(NeapolitanItems.SPIDER_POTTERY_SHERD.get(), SPIDER);
	}
}
