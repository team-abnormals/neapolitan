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
				MANGO, MANGO_MILKSHAKE, MANGO_CAKE,
				DRIED_MANGO, MANGO_FISH, COOKED_MANGO_FISH,
				CINNAMON_STICKS, CINNAMON_MILKSHAKE, CINNAMON_CAKE,
				CINNAMON_ROLL, CINNAMON_BAGEL, BERRY_STRUDEL,
				BUBBLEGUM, BUBBLEGUM_MILKSHAKE, BUBBLEGUM_CAKE,
				STRAWBERRY_BUBBLEGUM, BANANA_BUBBLEGUM, MINT_BUBBLEGUM, MANGO_BUBBLEGUM,
				PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE, WAFFLE_CONE, NeapolitanBlocks.CHILLBOX,
				REFLECTION_POTTERY_SHERD, SCREAM_POTTERY_SHERD, SPIDER_POTTERY_SHERD, SNACK_POTTERY_SHERD
		);

		for (ResourceKey<IceCreamFlavor> flavor : NeapolitanIceCreamFlavors.FLAVORS) {
			for (String dish : new String[]{"bowl_", "cone_"}) {
				for (int i = 1; i <= 3; i++) {
					String name = dish + "layer" + i + "_" + flavor.location().getNamespace() + "_" + flavor.location().getPath();
					this.withExistingParent("item/ice_cream/" + name, "item/generated").texture("layer0", Neapolitan.location("ice_cream/items/" + name));
				}
			}
		}
	}
}