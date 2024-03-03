package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class NeapolitanBannerPatternTags {
	public static final TagKey<BannerPattern> PATTERN_ITEM_CHIMPANZEE = bannerPatternTag("pattern_item/chimpanzee");

	private static TagKey<BannerPattern> bannerPatternTag(String name) {
		return TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(Neapolitan.MOD_ID, name));
	}
}
