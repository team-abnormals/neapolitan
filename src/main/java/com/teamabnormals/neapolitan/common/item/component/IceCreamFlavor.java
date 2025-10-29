package com.teamabnormals.neapolitan.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.common.item.component.effect.IceCreamFlavorEffect;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Optional;

public record IceCreamFlavor(Holder<Item> ingredient, Component description, List<IceCreamFlavorEffect> effects, Optional<Holder<Block>> milkshakeCauldron) {
	public static final Codec<IceCreamFlavor> DIRECT_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							RegistryFixedCodec.create(Registries.ITEM).fieldOf("ingredient").forGetter(IceCreamFlavor::ingredient),
							ComponentSerialization.CODEC.fieldOf("description").forGetter(IceCreamFlavor::description),
							IceCreamFlavorEffect.CODEC.listOf().optionalFieldOf("effects", List.of()).forGetter(IceCreamFlavor::effects),
							RegistryFixedCodec.create(Registries.BLOCK).optionalFieldOf("milkshake_cauldron").forGetter(IceCreamFlavor::milkshakeCauldron)
					)
					.apply(instance, IceCreamFlavor::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, IceCreamFlavor> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.holderRegistry(Registries.ITEM), IceCreamFlavor::ingredient,
			ComponentSerialization.STREAM_CODEC, IceCreamFlavor::description,
			ByteBufCodecs.fromCodec(IceCreamFlavorEffect.CODEC.listOf()), IceCreamFlavor::effects,
			ByteBufCodecs.optional(ByteBufCodecs.holderRegistry(Registries.BLOCK)), IceCreamFlavor::milkshakeCauldron,
			IceCreamFlavor::new
	);

	public static final Codec<Holder<IceCreamFlavor>> CODEC = RegistryFileCodec.create(NeapolitanRegistries.ICE_CREAM_FLAVOR, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<IceCreamFlavor>> STREAM_CODEC = ByteBufCodecs.holder(NeapolitanRegistries.ICE_CREAM_FLAVOR, DIRECT_STREAM_CODEC);

	public static IceCreamFlavor create(Item ingredient, Component description, Block cauldron, IceCreamFlavorEffect... effects) {
		return new IceCreamFlavor(BuiltInRegistries.ITEM.wrapAsHolder(ingredient), description, List.of(effects), Optional.of(BuiltInRegistries.BLOCK.wrapAsHolder(cauldron)));
	}

	public static Optional<Reference<IceCreamFlavor>> getFromIngredient(HolderLookup.Provider registries, ItemStack ingredient) {
		return registries.lookupOrThrow(NeapolitanRegistries.ICE_CREAM_FLAVOR).listElements().filter(flavor -> ingredient.is(flavor.value().ingredient())).findFirst();
	}
}