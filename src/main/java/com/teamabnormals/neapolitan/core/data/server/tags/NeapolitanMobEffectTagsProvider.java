package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanMobEffectTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class NeapolitanMobEffectTagsProvider extends ForgeRegistryTagsProvider<MobEffect> {

	public NeapolitanMobEffectTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ForgeRegistries.MOB_EFFECTS, Neapolitan.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(NeapolitanMobEffectTags.UNAFFECTED_BY_VANILLA_SCENT).add(NeapolitanMobEffects.VANILLA_SCENT.get()).addOptional(NeapolitanConstants.FOUL_TASTE);
	}
}