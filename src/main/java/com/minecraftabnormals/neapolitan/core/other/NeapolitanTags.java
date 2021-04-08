package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class NeapolitanTags {
	public static class Blocks {
		public static final ITag<Block> VANILLA_PLANTABLE_ON = BlockTags.makeWrapperTag(id("vanilla_plantable_on"));
		public static final ITag<Block> UNAFFECTED_BY_MINT = BlockTags.makeWrapperTag(id("unaffected_by_mint"));
	}

	public static class Items {
		public static final ITag<Item> CHIMPANZEE_FOOD = ItemTags.makeWrapperTag(id("chimpanzee_food"));
		public static final ITag<Item> CHIMPANZEE_TEMPT_ITEMS = ItemTags.makeWrapperTag(id("chimpanzee_tempt_items"));
	}

	public static class EntityTypes {
		public static final ITag<EntityType<?>> MILKABLE = EntityTypeTags.getTagById(id("milkable"));
		public static final ITag<EntityType<?>> UNAFFECTED_BY_SLIPPING = EntityTypeTags.getTagById(id("unaffected_by_slipping"));
		public static final ITag<EntityType<?>> UNAFFECTED_BY_HARMONY = EntityTypeTags.getTagById(id("unaffected_by_harmony"));
	}

	private static String id(String string) {
		return Neapolitan.MOD_ID + ":" + string;
	}
}
