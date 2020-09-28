package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;

public class NeapolitanTags {
    public static class Blocks {
        public static final ITag<Block> VANILLA_PLANTABLE_ON    	= BlockTags.makeWrapperTag(Neapolitan.MODID + ":vanilla_plantable_on");
        public static final ITag<Block> CREEPER_REPELLENTS      	= BlockTags.makeWrapperTag(Neapolitan.MODID + ":creeper_repellents");
    }
    
    public static class EntityTypes {
        public static final ITag<EntityType<?>> MILKABLE = EntityTypeTags.func_232896_a_(Neapolitan.MODID + ":milkable");
    }
}
