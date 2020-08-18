package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;

public class NeapolitanTags {
    static class EntityTypes {
        public static final ITag<EntityType<?>> MILKABLE = EntityTypeTags.func_232896_a_(Neapolitan.MODID + ":milkable");
    }
}
