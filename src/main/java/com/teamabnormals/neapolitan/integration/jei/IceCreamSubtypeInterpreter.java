package com.teamabnormals.neapolitan.integration.jei;

import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDataComponents;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class IceCreamSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
	public static final IceCreamSubtypeInterpreter INSTANCE = new IceCreamSubtypeInterpreter();

	private IceCreamSubtypeInterpreter() {

	}

	@Override
	public @Nullable Object getSubtypeData(ItemStack ingredient, UidContext context) {
		return ingredient.get(NeapolitanDataComponents.ICE_CREAM);
	}

	@Override
	public String getLegacyStringSubtypeInfo(ItemStack itemStack, UidContext context) {
		IceCream iceCream = itemStack.get(NeapolitanDataComponents.ICE_CREAM);
		if (iceCream == null) {
			return "";
		}
		return iceCream.primaryFlavor().key().location() + "." + iceCream.secondaryFlavor().key().location() + "." + iceCream.tertiaryFlavor().key().location();
	}
}