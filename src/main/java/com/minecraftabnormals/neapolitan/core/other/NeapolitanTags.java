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
        public static final ITag<Block> VANILLA_PLANTABLE_ON	= BlockTags.makeWrapperTag(id("vanilla_plantable_on"));
        public static final ITag<Block> CREEPER_REPELLENTS      = BlockTags.makeWrapperTag(id("creeper_repellents"));
    }
    
    public static class Items {
        public static final ITag<Item> MONKEY_BREEDING_ITEMS 	= ItemTags.makeWrapperTag(id("monkey_breeding_items"));
        public static final ITag<Item> MONKEY_TEMPTATION_ITEMS 	= ItemTags.makeWrapperTag(id("monkey_temptation_items"));
    }
    
    public static class EntityTypes {
        public static final ITag<EntityType<?>> MILKABLE = EntityTypeTags.getTagById(id("milkable"));
        public static final ITag<EntityType<?>> SLIPPING_PROOF = EntityTypeTags.getTagById(id("slipping_proof"));
    }
    
    private static String id(String string) {
    	return Neapolitan.MOD_ID + ":" + string;
    }
}
