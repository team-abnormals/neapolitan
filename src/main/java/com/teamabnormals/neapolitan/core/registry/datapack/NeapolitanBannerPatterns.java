package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class NeapolitanBannerPatterns {
	public static final ResourceKey<BannerPattern> CHIMPANZEE = create("chimpanzee");

	public static void bootstrap(BootstrapContext<BannerPattern> context) {
		register(context, CHIMPANZEE);
	}

	private static ResourceKey<BannerPattern> create(String name) {
		return ResourceKey.create(Registries.BANNER_PATTERN, Neapolitan.location(name));
	}

	public static void register(BootstrapContext<BannerPattern> context, ResourceKey<BannerPattern> resourceKey) {
		context.register(resourceKey, new BannerPattern(resourceKey.location(), "block.minecraft.banner." + resourceKey.location().toShortLanguageKey()));
	}
}