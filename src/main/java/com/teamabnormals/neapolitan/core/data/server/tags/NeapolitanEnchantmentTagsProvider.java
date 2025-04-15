package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEnchantmentTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class NeapolitanEnchantmentTagsProvider extends EnchantmentTagsProvider {

	public NeapolitanEnchantmentTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(NeapolitanEnchantmentTags.PREVENTS_PLANTAIN_SPIDER_SPAWNS_WHEN_MINING).add(Enchantments.SILK_TOUCH);
	}
}