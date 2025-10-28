package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;

public class NeapolitanItemModelProvider extends BlueprintItemModelProvider {

	public NeapolitanItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Neapolitan.MOD_ID, helper);
		for (String flavor : new String[]{"vanilla", "chocolate", "strawberry", "banana", "mint", "adzuki"}) {
			for (String dish : new String[]{"bowl_", "cone_"}) {
				for (int i = 1; i <= 3; i++) {
					this.existingFileHelper.trackGenerated(Neapolitan.location("ice_cream/items/" + dish + "layer" + i + "_neapolitan_" + flavor), ModelProvider.TEXTURE);
				}
			}
		}
	}

	@Override
	protected void registerModels() {
		this.generatedItem(
				PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE, WAFFLE_CONE,
				REFLECTION_POTTERY_SHERD, SCREAM_POTTERY_SHERD, SPIDER_POTTERY_SHERD, SNACK_POTTERY_SHERD
		);

		for (String flavor : new String[]{"vanilla", "chocolate", "strawberry", "banana", "mint", "adzuki"}) {
			for (String dish : new String[]{"bowl_", "cone_"}) {
				this.withExistingParent("item/ice_cream/" + dish + flavor + "_topping", "item/generated").texture("layer0", Neapolitan.location("ice_cream/toppings/" + flavor));
				for (int i = 1; i <= 3; i++) {
					String name = dish + "layer" + i + "_neapolitan_" + flavor;
					this.withExistingParent("item/ice_cream/" + name, "item/generated").texture("layer0", Neapolitan.location("ice_cream/items/" + name));
				}
			}
		}
	}
}