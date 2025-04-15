package com.teamabnormals.neapolitan.core.registry;

import com.google.common.collect.ImmutableSet;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeapolitanPoiTypes {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Neapolitan.MOD_ID);

	public static final DeferredHolder<PoiType, PoiType> BANANA_BUNDLE = POI_TYPES.register("banana_bundle", () -> new PoiType(ImmutableSet.copyOf(NeapolitanBlocks.BANANA_BUNDLE.get().getStateDefinition().getPossibleStates()), 1, 1));
}