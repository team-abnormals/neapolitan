package com.teamabnormals.neapolitan.core.registry;

import com.google.common.collect.ImmutableSet;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NeapolitanPoiTypes {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Neapolitan.MOD_ID);

	public static final RegistryObject<PoiType> BANANA_BUNDLE = POI_TYPES.register("banana_bundle", () -> new PoiType(ImmutableSet.copyOf(NeapolitanBlocks.BANANA_BUNDLE.get().getStateDefinition().getPossibleStates()), 1, 1));
}