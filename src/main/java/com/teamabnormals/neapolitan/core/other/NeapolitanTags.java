package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NeapolitanTags {
	public static class Blocks {
		public static final Tag<Block> VANILLA_PLANTABLE_ON = blockTag("vanilla_plantable_on");
		public static final Tag<Block> UNAFFECTED_BY_MINT = blockTag("unaffected_by_mint");
		public static final Tag<Block> CHIMPANZEE_JUMPING_BLOCKS = blockTag("chimpanzee_jumping_blocks");

		private static Tag<Block> blockTag(String name) {
			return TagUtil.blockTag(Neapolitan.MOD_ID, name);
		}
	}

	public static class Items {
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

	public static class EntityTypes {
		public static final Tag<EntityType<?>> MILKABLE = entityTypeTag("milkable");
		public static final Tag<EntityType<?>> UNAFFECTED_BY_SLIPPING = entityTypeTag("unaffected_by_slipping");
		public static final Tag<EntityType<?>> UNAFFECTED_BY_HARMONY = entityTypeTag("unaffected_by_harmony");
		public static final Tag<EntityType<?>> SCARES_CHIMPANZEES = entityTypeTag("scares_chimpanzees");
		public static final Tag<EntityType<?>> CHIMPANZEE_DART_TARGETS = entityTypeTag("chimpanzee_dart_targets");

		private static Tag<EntityType<?>> entityTypeTag(String name) {
			return TagUtil.entityTypeTag(Neapolitan.MOD_ID, name);
		}
	}
}
