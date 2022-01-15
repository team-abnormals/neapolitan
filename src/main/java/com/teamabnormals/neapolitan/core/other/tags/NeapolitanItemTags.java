package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class NeapolitanItemTags {
	public static final Tag<Item> CHIMPANZEE_FOOD = itemTag("chimpanzee_food");
	public static final Tag<Item> CHIMPANZEE_SNACKS = itemTag("chimpanzee_snacks");
	public static final Tag<Item> CHIMPANZEE_APE_MODE_ITEMS = itemTag("chimpanzee_ape_mode_items");
	public static final Tag<Item> CHIMPANZEE_SHAKEABLE_BUCKETS = itemTag("chimpanzee_shakeable_buckets");
	public static final Tag<Item> CHIMPANZEE_FAVORITES = itemTag("chimpanzee_favorites");
	public static final Tag<Item> HIDES_CHIMPANZEE_EARS = itemTag("hides_chimpanzee_ears");

	private static Tag<Item> itemTag(String name) {
		return TagUtil.itemTag(Neapolitan.MOD_ID, name);
	}
}
