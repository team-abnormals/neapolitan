package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;

public class NeapolitanPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

	public NeapolitanPaintingVariantTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		TagAppender<PaintingVariant> appender = this.tag(PaintingVariantTags.PLACEABLE);
		for (RegistryObject<PaintingVariant> variant : NeapolitanPaintingVariants.PAINTING_VARIANTS.getEntries()) {
			appender.add(variant.getKey());
		}
	}
}