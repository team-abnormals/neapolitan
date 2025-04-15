package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanMobEffectTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class NeapolitanMobEffectTagsProvider extends IntrinsicHolderTagsProvider<MobEffect> {

	public NeapolitanMobEffectTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, Registries.MOB_EFFECT, provider, effect -> BuiltInRegistries.MOB_EFFECT.getResourceKey(effect).get(), Neapolitan.MOD_ID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(NeapolitanMobEffectTags.UNAFFECTED_BY_VANILLA_SCENT)
				.add(MobEffects.DARKNESS.getKey(), NeapolitanMobEffects.VANILLA_SCENT.getKey())
				.addOptional(NeapolitanConstants.FOUL_TASTE);
	}
}