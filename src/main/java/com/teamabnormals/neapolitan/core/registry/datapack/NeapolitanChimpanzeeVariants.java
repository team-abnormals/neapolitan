package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeVariant;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public final class NeapolitanChimpanzeeVariants {
	public static final ResourceKey<ChimpanzeeVariant> JUNGLE = create("jungle");
	public static final ResourceKey<ChimpanzeeVariant> BAMBOO = create("bamboo");
	public static final ResourceKey<ChimpanzeeVariant> RAINFOREST = create("rainforest");
	public static final ResourceKey<ChimpanzeeVariant> DEFAULT = JUNGLE;

	public static void bootstrap(BootstrapContext<ChimpanzeeVariant> context) {
		register(context, JUNGLE, "jungle_chimpanzee", NeapolitanBiomeTags.SPAWNS_JUNGLE_VARIANT_CHIMPANZEES);
		register(context, BAMBOO, "bamboo_chimpanzee", NeapolitanBiomeTags.SPAWNS_BAMBOO_VARIANT_CHIMPANZEES);
		register(context, RAINFOREST, "rainforest_chimpanzee", NeapolitanBiomeTags.SPAWNS_RAINFOREST_VARIANT_CHIMPANZEES);
	}

	private static ResourceKey<ChimpanzeeVariant> create(String name) {
		return ResourceKey.create(NeapolitanRegistries.CHIMPANZEE_VARIANT, Neapolitan.location(name));
	}

	public static void register(BootstrapContext<ChimpanzeeVariant> context, ResourceKey<ChimpanzeeVariant> key, String name, ResourceKey<Biome> spawnBiome) {
		register(context, key, name, HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(spawnBiome)));
	}

	public static void register(BootstrapContext<ChimpanzeeVariant> context, ResourceKey<ChimpanzeeVariant> key, String name, TagKey<Biome> spawnBiomes) {
		register(context, key, name, context.lookup(Registries.BIOME).getOrThrow(spawnBiomes));
	}

	public static void register(BootstrapContext<ChimpanzeeVariant> context, ResourceKey<ChimpanzeeVariant> key, String name, HolderSet<Biome> spawnBiomes) {
		ResourceLocation texture = key.location().withPath("entity/chimpanzee/" + name);
		ResourceLocation screamingTexture = key.location().withPath("entity/chimpanzee/" + name + "_mouth_open");
		context.register(key, new ChimpanzeeVariant(texture, screamingTexture, spawnBiomes));
	}

	public static Holder<ChimpanzeeVariant> getSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome) {
		Registry<ChimpanzeeVariant> registry = registryAccess.registryOrThrow(NeapolitanRegistries.CHIMPANZEE_VARIANT);
		return registry.holders()
				.filter(p_332674_ -> p_332674_.value().biomes().contains(biome))
				.findFirst()
				.or(() -> registry.getHolder(DEFAULT))
				.or(registry::getAny)
				.orElseThrow();
	}
}