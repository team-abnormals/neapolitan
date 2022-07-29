package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBannerPatternTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBannerPatterns;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NeapolitanBannerPatternTagsProvider extends BannerPatternTagsProvider {

	public NeapolitanBannerPatternTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Neapolitan.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(NeapolitanBannerPatternTags.PATTERN_ITEM_CHIMPANZEE).add(NeapolitanBannerPatterns.CHIMPANZEE.get());
	}
}