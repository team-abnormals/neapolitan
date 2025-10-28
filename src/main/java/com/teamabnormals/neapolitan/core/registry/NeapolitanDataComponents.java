package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponentType.Builder;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class NeapolitanDataComponents {
	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Neapolitan.MOD_ID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<IceCream>> ICE_CREAM = register("ice_cream", builder -> builder.persistent(IceCream.CODEC).networkSynchronized(IceCream.STREAM_CODEC));

	private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<Builder<T>> builder) {
		return DATA_COMPONENTS.register(name, () -> builder.apply(DataComponentType.builder()).build());
	}
}