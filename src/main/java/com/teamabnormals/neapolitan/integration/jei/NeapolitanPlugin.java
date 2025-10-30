package com.teamabnormals.neapolitan.integration.jei;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class NeapolitanPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return Neapolitan.location(Neapolitan.MOD_ID);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		registration.registerSubtypeInterpreter(NeapolitanItems.ICE_CREAM.get(), IceCreamSubtypeInterpreter.INSTANCE);
		registration.registerSubtypeInterpreter(NeapolitanItems.ICE_CREAM_CONE.get(), IceCreamSubtypeInterpreter.INSTANCE);
	}
}
