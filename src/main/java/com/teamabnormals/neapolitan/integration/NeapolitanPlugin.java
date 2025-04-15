package com.teamabnormals.neapolitan.integration;

import com.teamabnormals.neapolitan.core.Neapolitan;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.List;

@JeiPlugin
public class NeapolitanPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return Neapolitan.location(Neapolitan.MOD_ID);
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(NeoForgeTypes.FLUID_STACK, List.of(new FluidStack(NeoForgeMod.MILK.get(), 1000)));
	}
}