package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraftforge.registries.ForgeRegistries;

public class NeapolitanTrimPatterns {
	public static final ResourceKey<TrimPattern> PRIMAL = createKey("primal");

	public static void bootstrap(BootstapContext<TrimPattern> context) {
		register(context, PRIMAL, NeapolitanItems.PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.get());
	}

	public static ResourceKey<TrimPattern> createKey(String name) {
		return ResourceKey.create(Registries.TRIM_PATTERN, new ResourceLocation(Neapolitan.MOD_ID, name));
	}

	private static void register(BootstapContext<TrimPattern> context, ResourceKey<TrimPattern> key, Item item) {
		context.register(key, new TrimPattern(key.location(), ForgeRegistries.ITEMS.getHolder(item).get(), Component.translatable(Util.makeDescriptionId("trim_pattern", key.location()))));
	}
}
