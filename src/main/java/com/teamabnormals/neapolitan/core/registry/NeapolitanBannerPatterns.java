package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class NeapolitanBannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, Neapolitan.MOD_ID);

	public static final RegistryObject<BannerPattern> CHIMPANZEE = BANNER_PATTERNS.register("chimpanzee", () -> new BannerPattern("mky"));
}