package com.teamabnormals.neapolitan.common.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public record ChimpanzeeVariant(ResourceLocation texture, ResourceLocation textureFull, ResourceLocation screamingTexture, ResourceLocation screamingTextureFull, HolderSet<Biome> biomes) {
	public static final Codec<ChimpanzeeVariant> DIRECT_CODEC = RecordCodecBuilder.create(
			p_332779_ -> p_332779_.group(
							ResourceLocation.CODEC.fieldOf("texture").forGetter(p_335261_ -> p_335261_.texture),
							ResourceLocation.CODEC.fieldOf("screaming_texture").forGetter(p_335263_ -> p_335263_.screamingTexture),
							RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(ChimpanzeeVariant::biomes)
					)
					.apply(p_332779_, ChimpanzeeVariant::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, ChimpanzeeVariant> DIRECT_STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC,
			ChimpanzeeVariant::texture,
			ResourceLocation.STREAM_CODEC,
			ChimpanzeeVariant::screamingTexture,
			ByteBufCodecs.holderSet(Registries.BIOME),
			ChimpanzeeVariant::biomes,
			ChimpanzeeVariant::new
	);
	public static final Codec<Holder<ChimpanzeeVariant>> CODEC = RegistryFileCodec.create(NeapolitanRegistries.CHIMPANZEE_VARIANT, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<ChimpanzeeVariant>> STREAM_CODEC = ByteBufCodecs.holder(NeapolitanRegistries.CHIMPANZEE_VARIANT, DIRECT_STREAM_CODEC);

	public ChimpanzeeVariant(ResourceLocation texture, ResourceLocation screamingTexture, HolderSet<Biome> biomes) {
		this(texture, fullTextureId(texture), screamingTexture, fullTextureId(screamingTexture), biomes);
	}

	private static ResourceLocation fullTextureId(ResourceLocation texture) {
		return texture.withPath(s -> "textures/" + s + ".png");
	}
}