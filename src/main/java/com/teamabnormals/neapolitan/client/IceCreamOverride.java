package com.teamabnormals.neapolitan.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.common.item.component.IceCream;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public record IceCreamOverride(IceCream iceCream, boolean strict, Optional<Component> bowlDescription, Optional<ResourceLocation> bowlItemModel, Optional<Component> coneDescription, Optional<ResourceLocation> coneItemModel) {
	public static final Codec<IceCreamOverride> CODEC = RecordCodecBuilder.create(
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

	public static final StreamCodec<RegistryFriendlyByteBuf, IceCreamOverride> STREAM_CODEC = StreamCodec.composite(
			IceCream.STREAM_CODEC, IceCreamOverride::iceCream,
			ByteBufCodecs.BOOL, IceCreamOverride::strict,
			ByteBufCodecs.optional(ComponentSerialization.STREAM_CODEC), IceCreamOverride::bowlDescription,
			ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC), IceCreamOverride::bowlItemModel,
			ByteBufCodecs.optional(ComponentSerialization.STREAM_CODEC), IceCreamOverride::coneDescription,
			ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC), IceCreamOverride::coneItemModel,
			IceCreamOverride::new
	);
}