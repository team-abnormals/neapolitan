package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

public class NeapolitanMobEffectTags {
	public static final TagKey<MobEffect> UNAFFECTED_BY_VANILLA_SCENT = mobEffectTag("unaffected_by_vanilla_scent");

	private static TagKey<MobEffect> mobEffectTag(String name) {
		return TagUtil.mobEffectTag(Neapolitan.MOD_ID, name);
	}
}
