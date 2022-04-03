package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class NeapolitanItemTags {
	public static final TagKey<Item> CHIMPANZEE_FOOD = itemTag("chimpanzee_food");
	public static final TagKey<Item> CHIMPANZEE_SNACKS = itemTag("chimpanzee_snacks");
	public static final TagKey<Item> CHIMPANZEE_APE_MODE_ITEMS = itemTag("chimpanzee_ape_mode_items");
	public static final TagKey<Item> CHIMPANZEE_FAVORITES = itemTag("chimpanzee_favorites");
	public static final TagKey<Item> HIDES_CHIMPANZEE_EARS = itemTag("hides_chimpanzee_ears");
	public static final TagKey<Item> ICE_CREAM = itemTag("ice_cream");

	public static final TagKey<Item> BOTTLES = TagUtil.itemTag("forge", "bottles");
	public static final TagKey<Item> BOTTLES_MILK = TagUtil.itemTag("forge", "bottles/milk");
	public static final TagKey<Item> FRUITS = TagUtil.itemTag("forge", "fruits");
	public static final TagKey<Item> FRUITS_BANANA = TagUtil.itemTag("forge", "fruits/banana");
	public static final TagKey<Item> FRUITS_STRAWBERRY = TagUtil.itemTag("forge", "fruits/strawberry");
	public static final TagKey<Item> SEEDS_STRAWBERRY = TagUtil.itemTag("forge", "seeds/strawberry");
	public static final TagKey<Item> ICE_CUBES = TagUtil.itemTag("forge", "ice_cubes");
	public static final TagKey<Item> MILK = TagUtil.itemTag("forge", "milk");
	public static final TagKey<Item> PUMPKINS = TagUtil.itemTag("forge", "pumpkins");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(Neapolitan.MOD_ID, name);
	}
}
