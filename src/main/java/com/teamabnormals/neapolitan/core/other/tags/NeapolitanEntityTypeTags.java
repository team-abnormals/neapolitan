package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;

public class NeapolitanEntityTypeTags {
	public static final Tag.Named<EntityType<?>> UNAFFECTED_BY_SLIPPING = entityTypeTag("unaffected_by_slipping");
	public static final Tag.Named<EntityType<?>> UNAFFECTED_BY_HARMONY = entityTypeTag("unaffected_by_harmony");
	public static final Tag.Named<EntityType<?>> SCARES_CHIMPANZEES = entityTypeTag("scares_chimpanzees");
	public static final Tag.Named<EntityType<?>> CHIMPANZEE_DART_TARGETS = entityTypeTag("chimpanzee_dart_targets");

	private static Tag.Named<EntityType<?>> entityTypeTag(String name) {
		return TagUtil.entityTypeTag(Neapolitan.MOD_ID, name);
	}
}
