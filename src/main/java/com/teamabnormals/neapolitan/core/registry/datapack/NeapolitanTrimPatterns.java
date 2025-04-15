package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimPattern;

public class NeapolitanTrimPatterns {
	public static final ResourceKey<TrimPattern> PRIMAL = createKey("primal");

	public static void bootstrap(BootstrapContext<TrimPattern> context) {
		register(context, PRIMAL, NeapolitanItems.PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.get());
	}

	public static ResourceKey<TrimPattern> createKey(String name) {
		return ResourceKey.create(Registries.TRIM_PATTERN, Neapolitan.location(name));
	}

	private static void register(BootstrapContext<TrimPattern> context, ResourceKey<TrimPattern> key, Item item) {
		context.register(key, new TrimPattern(key.location(), BuiltInRegistries.ITEM.wrapAsHolder(item), Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false));
	}
}
