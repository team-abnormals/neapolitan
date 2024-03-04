package com.teamabnormals.neapolitan.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class NeapolitanDecoratedPotPatterns {
	public static final DeferredRegister<String> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERNS, Neapolitan.MOD_ID);
	
	public static final RegistryObject<String> REFLECTION = register("reflection_pottery_pattern");
	public static final RegistryObject<String> ANGER = register("anger_pottery_pattern");
	public static final RegistryObject<String> SPIDER = register("spider_pottery_pattern");
	public static final RegistryObject<String> BANANA = register("banana_pottery_pattern");

	public static RegistryObject<String> register(String name) {
		return DECORATED_POT_PATTERNS.register(name, () -> name);
	}

	public static void registerDecoratedPotPatterns() {
		DataUtil.registerDecoratedPotPattern(Pair.of(NeapolitanItems.REFLECTION_POTTERY_SHERD.get(), REFLECTION));
		DataUtil.registerDecoratedPotPattern(Pair.of(NeapolitanItems.ANGER_POTTERY_SHERD.get(), ANGER));
		DataUtil.registerDecoratedPotPattern(Pair.of(NeapolitanItems.SPIDER_POTTERY_SHERD.get(), SPIDER));
		DataUtil.registerDecoratedPotPattern(Pair.of(NeapolitanItems.BANANA_POTTERY_SHERD.get(), BANANA));
	}
}
