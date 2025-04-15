package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeVariant;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NeapolitanEntityDataSerializers {
	public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, Neapolitan.MOD_ID);

	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<ChimpanzeeVariant>>> CHIMPANZEE_VARIANT = ENTITY_DATA_SERIALIZERS.register("chimpanzee_variant", () -> EntityDataSerializer.forValueType(ChimpanzeeVariant.STREAM_CODEC));
}
