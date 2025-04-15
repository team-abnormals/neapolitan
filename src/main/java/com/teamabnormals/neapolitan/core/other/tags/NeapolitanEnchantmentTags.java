package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class NeapolitanEnchantmentTags {
	public static final TagKey<Enchantment> PREVENTS_PLANTAIN_SPIDER_SPAWNS_WHEN_MINING = enchantmentTag("prevents_plantain_spider_spawns_when_mining");

	private static TagKey<Enchantment> enchantmentTag(String name) {
		return TagUtil.enchantmentTag(Neapolitan.MOD_ID, name);
	}
}
