package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class NeapolitanItemTags {
	public static final Tag.Named<Item> CHIMPANZEE_FOOD = itemTag("chimpanzee_food");
	public static final Tag.Named<Item> CHIMPANZEE_SNACKS = itemTag("chimpanzee_snacks");
	public static final Tag.Named<Item> CHIMPANZEE_APE_MODE_ITEMS = itemTag("chimpanzee_ape_mode_items");
	public static final Tag.Named<Item> CHIMPANZEE_FAVORITES = itemTag("chimpanzee_favorites");
	public static final Tag.Named<Item> HIDES_CHIMPANZEE_EARS = itemTag("hides_chimpanzee_ears");
	public static final Tag.Named<Item> ICE_CREAM = itemTag("ice_cream");

	public static final Tag.Named<Item> BOTTLES = TagUtil.forgeItemTag("bottles");
	public static final Tag.Named<Item> BOTTLES_MILK = TagUtil.forgeItemTag("bottles/milk");
	public static final Tag.Named<Item> FRUITS = TagUtil.forgeItemTag("fruits");
	public static final Tag.Named<Item> FRUITS_BANANA = TagUtil.forgeItemTag("fruits/banana");
	public static final Tag.Named<Item> FRUITS_STRAWBERRY = TagUtil.forgeItemTag("fruits/strawberry");
	public static final Tag.Named<Item> SEEDS_STRAWBERRY = TagUtil.forgeItemTag("seeds/strawberry");
	public static final Tag.Named<Item> ICE_CUBES = TagUtil.forgeItemTag("ice_cubes");
	public static final Tag.Named<Item> MILK = TagUtil.forgeItemTag("milk");
	public static final Tag.Named<Item> PUMPKINS = TagUtil.forgeItemTag("pumpkins");

	private static Tag.Named<Item> itemTag(String name) {
		return TagUtil.itemTag(Neapolitan.MOD_ID, name);
	}
}
