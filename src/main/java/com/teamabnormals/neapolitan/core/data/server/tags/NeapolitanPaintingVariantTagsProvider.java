package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanPaintingVariants.*;

public class NeapolitanPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

	public NeapolitanPaintingVariantTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.tag(PaintingVariantTags.PLACEABLE).add(SCREAM, CONE, DAWN, NEAPOLEON);
	}
}