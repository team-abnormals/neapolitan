package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBannerPatternTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBannerPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class NeapolitanBannerPatternTagsProvider extends BannerPatternTagsProvider {

	public NeapolitanBannerPatternTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.tag(NeapolitanBannerPatternTags.PATTERN_ITEM_CHIMPANZEE).add(NeapolitanBannerPatterns.CHIMPANZEE.getKey());
	}
}