package com.minecraftabnormals.neapolitan.core.registry;

import com.teamabnormals.abnormals_core.core.library.api.BannerManager;

import net.minecraft.tileentity.BannerPattern;

public final class NeapolitanBanners {

	public static final BannerPattern MONKEY = BannerManager.createPattern("mca", "monkey", "mky");

	public static void registerBanners() {
		BannerManager.addPattern(MONKEY, NeapolitanItems.MONKEY_BANNNER_PATTERN.get());
	}
}
