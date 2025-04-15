package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanTrimPatterns;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.concurrent.CompletableFuture;

public final class NeapolitanSpriteSourceProvider extends SpriteSourceProvider {

	public NeapolitanSpriteSourceProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	protected void gather() {
		this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.patternPermutationsOfVanillaMaterials(NeapolitanTrimPatterns.PRIMAL));
	}
}