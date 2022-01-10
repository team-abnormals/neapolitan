package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NeapolitanItemTagsProvider extends ItemTagsProvider {

	public NeapolitanItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, ExistingFileHelper helper) {
		super(generator, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	public void addTags() {
		this.tag(ItemTags.FOX_FOOD).add(NeapolitanItems.STRAWBERRIES.get(), NeapolitanItems.WHITE_STRAWBERRIES.get());
	}
}