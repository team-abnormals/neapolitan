package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeVariant;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public final class NeapolitanRegistries {
	public static final ResourceKey<Registry<ChimpanzeeVariant>> CHIMPANZEE_VARIANT = create("chimpanzee_variant");

	public static void registerRegistries(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(CHIMPANZEE_VARIANT, ChimpanzeeVariant.DIRECT_CODEC, ChimpanzeeVariant.DIRECT_CODEC);
	}

	private static <T> ResourceKey<Registry<T>> create(String name) {
		return ResourceKey.createRegistryKey(Neapolitan.location(name));
	}
}