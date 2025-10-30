package com.teamabnormals.neapolitan.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public record IceCreamOverride(IceCream iceCream, boolean strict, Optional<Component> bowlDescription, Optional<ResourceLocation> bowlItemModel, Optional<Component> coneDescription, Optional<ResourceLocation> coneItemModel) {
	public static final Codec<IceCreamOverride> DIRECT_CODEC = RecordCodecBuilder.create(
			p_332779_ -> p_332779_.group(
							IceCream.CODEC.fieldOf("ice_cream").forGetter(IceCreamOverride::iceCream),
							Codec.BOOL.optionalFieldOf("strict", false).forGetter(IceCreamOverride::strict),
							ComponentSerialization.CODEC.optionalFieldOf("bowl_description").forGetter(IceCreamOverride::bowlDescription),
							ResourceLocation.CODEC.optionalFieldOf("bowl_item_model").forGetter(IceCreamOverride::bowlItemModel),
							ComponentSerialization.CODEC.optionalFieldOf("cone_description").forGetter(IceCreamOverride::coneDescription),
							ResourceLocation.CODEC.optionalFieldOf("cone_item_model").forGetter(IceCreamOverride::coneItemModel)
					)
					.apply(p_332779_, IceCreamOverride::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, IceCreamOverride> DIRECT_STREAM_CODEC = StreamCodec.composite(
			IceCream.STREAM_CODEC, IceCreamOverride::iceCream,
			ByteBufCodecs.BOOL, IceCreamOverride::strict,
			ByteBufCodecs.optional(ComponentSerialization.STREAM_CODEC), IceCreamOverride::bowlDescription,
			ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC), IceCreamOverride::bowlItemModel,
			ByteBufCodecs.optional(ComponentSerialization.STREAM_CODEC), IceCreamOverride::coneDescription,
			ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC), IceCreamOverride::coneItemModel,
			IceCreamOverride::new
	);

	public static final Codec<Holder<IceCreamOverride>> CODEC = RegistryFileCodec.create(NeapolitanRegistries.ICE_CREAM_OVERRIDE, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<IceCreamOverride>> STREAM_CODEC = ByteBufCodecs.holder(NeapolitanRegistries.ICE_CREAM_OVERRIDE, DIRECT_STREAM_CODEC);

	public static Optional<Reference<IceCreamOverride>> getFromIceCream(RegistryLookup<IceCreamOverride> lookup, IceCream iceCream) {
		return lookup.listElements().filter(ref -> ref.value().strict() ? ref.value().iceCream().is(iceCream) : ref.value().iceCream().matches(iceCream)).findFirst();
	}
}