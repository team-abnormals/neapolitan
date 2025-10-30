package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanIceCreamFlavors;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;

public class NeapolitanItemModelProvider extends BlueprintItemModelProvider {

	public NeapolitanItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Neapolitan.MOD_ID, helper);
		for (ResourceKey<IceCreamFlavor> flavor : NeapolitanIceCreamFlavors.FLAVORS) {
			for (String dish : new String[]{"bowl_", "cone_"}) {
				for (int i = 1; i <= 3; i++) {
					this.existingFileHelper.trackGenerated(flavor.location().withPrefix("ice_cream/items/" + dish + "layer" + i + "_" + flavor.location().getNamespace() + "_"), ModelProvider.TEXTURE);
				}
			}
		}
	}

	@Override
	protected void registerModels() {
		this.generatedItem(
				PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE, WAFFLE_CONE, NeapolitanBlocks.CHILLBOX,
				REFLECTION_POTTERY_SHERD, SCREAM_POTTERY_SHERD, SPIDER_POTTERY_SHERD, SNACK_POTTERY_SHERD
		);

		for (ResourceKey<IceCreamFlavor> flavor : NeapolitanIceCreamFlavors.FLAVORS) {
			for (String dish : new String[]{"bowl_", "cone_"}) {
				if (NeapolitanIceCreamFlavors.TOPPINGS.contains(flavor)) {
					this.withExistingParent("item/ice_cream/" + dish + flavor.location().getPath() + "_topping", "item/generated").texture("layer0", flavor.location().withPrefix("ice_cream/toppings/"));
				}

				for (int i = 1; i <= 3; i++) {
					String name = dish + "layer" + i + "_" + flavor.location().getNamespace() + "_" + flavor.location().getPath();
					this.withExistingParent("item/ice_cream/" + name, "item/generated").texture("layer0", Neapolitan.location("ice_cream/items/" + name));
				}
			}
		}
	}
}