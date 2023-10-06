package com.teamabnormals.neapolitan.integration;

import com.teamabnormals.neapolitan.core.Neapolitan;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

@JeiPlugin
public class NeapolitanPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Neapolitan.MOD_ID, Neapolitan.MOD_ID);
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(ForgeTypes.FLUID_STACK, List.of(new FluidStack(ForgeMod.MILK.get(), 1000)));
	}
}