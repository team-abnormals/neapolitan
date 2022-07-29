package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanPaintingVariants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NeapolitanPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

	public NeapolitanPaintingVariantTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Neapolitan.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(PaintingVariantTags.PLACEABLE).add(NeapolitanPaintingVariants.CONE.get(), NeapolitanPaintingVariants.SCREAM.get());
	}
}