package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;

public class NeapolitanItemModelProvider extends BlueprintItemModelProvider {

	public NeapolitanItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Neapolitan.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		this.generatedItem(
				PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE,
				REFLECTION_POTTERY_SHERD, SCREAM_POTTERY_SHERD, SPIDER_POTTERY_SHERD, SNACK_POTTERY_SHERD
		);
	}
}