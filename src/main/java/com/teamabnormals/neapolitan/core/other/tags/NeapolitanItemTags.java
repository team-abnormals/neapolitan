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
	public static final TagKey<Item> CHIMPANZEE_WEAPONS = itemTag("chimpanzee_weapons");
	public static final TagKey<Item> HIDES_CHIMPANZEE_EARS = itemTag("hides_chimpanzee_ears");

	public static final TagKey<Item> FOODS_BANANA = TagUtil.itemTag("c", "foods/banana");
	public static final TagKey<Item> FOODS_STRAWBERRY = TagUtil.itemTag("c", "foods/strawberry");
	public static final TagKey<Item> SEEDS_STRAWBERRY = TagUtil.itemTag("c", "seeds/strawberry");
	public static final TagKey<Item> FOODS_CHOCOLATE_BAR = TagUtil.itemTag("c", "foods/chocolate_bar");
	public static final TagKey<Item> ICE_CUBES = TagUtil.itemTag("c", "ice_cubes");

	public static final TagKey<Item> FOODS_ICE_CREAM = TagUtil.itemTag("c", "foods/ice_cream");
	public static final TagKey<Item> FOODS_CAKE = TagUtil.itemTag("c", "foods/cake");
	public static final TagKey<Item> DRINKS_MILKSHAKE = TagUtil.itemTag("c", "drinks/milkshake");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(Neapolitan.MOD_ID, name);
	}
}
