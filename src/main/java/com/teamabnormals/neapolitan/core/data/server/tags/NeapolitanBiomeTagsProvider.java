package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class NeapolitanBiomeTagsProvider extends BiomeTagsProvider {

	public NeapolitanBiomeTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(NeapolitanBiomes.STRAWBERRY_FIELDS,
				BiomeTags.IS_OVERWORLD,
				BiomeTags.HAS_MINESHAFT, BiomeTags.STRONGHOLD_BIASED_TO, BiomeTags.HAS_RUINED_PORTAL_STANDARD,
				Tags.Biomes.IS_PLAINS, Tags.Biomes.IS_RARE
		);

		this.tag(NeapolitanBiomeTags.SPAWNS_JUNGLE_VARIANT_CHIMPANZEES).add(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE);
		this.tag(NeapolitanBiomeTags.SPAWNS_RAINFOREST_VARIANT_CHIMPANZEES).addOptionalTag(NeapolitanConstants.IS_RAINFOREST);
		this.tag(NeapolitanBiomeTags.SPAWNS_BAMBOO_VARIANT_CHIMPANZEES).add(Biomes.BAMBOO_JUNGLE);

		this.tag(NeapolitanBiomeTags.SPAWNS_STRAWBERRY_RABBITS).add(NeapolitanBiomes.STRAWBERRY_FIELDS);

		this.tag(NeapolitanBiomeTags.HAS_CHIMPANZEE).addTag(NeapolitanBiomeTags.HAS_COMMON_BANANA_PLANT).addTag(NeapolitanBiomeTags.HAS_UNCOMMON_BANANA_PLANT);

		this.tag(NeapolitanBiomeTags.HAS_STRAWBERRY_BUSH).add(Biomes.PLAINS);
		this.tag(NeapolitanBiomeTags.HAS_ADZUKI_SPROUTS).add(Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.DARK_FOREST);
		this.tag(NeapolitanBiomeTags.HAS_VANILLA_VINE).addTag(BiomeTags.IS_SAVANNA);
		this.tag(NeapolitanBiomeTags.HAS_MINT_POND).addTag(BiomeTags.IS_HILL);

		this.tag(NeapolitanBiomeTags.HAS_COMMON_BANANA_PLANT).add(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE).addOptional(NeapolitanConstants.RAINFOREST).addOptional(NeapolitanConstants.RAINFOREST_BASIN);
		this.tag(NeapolitanBiomeTags.HAS_UNCOMMON_BANANA_PLANT).add(Biomes.SPARSE_JUNGLE).addOptional(NeapolitanConstants.SPARSE_RAINFOREST).addOptional(NeapolitanConstants.SPARSE_RAINFOREST_BASIN);
		this.tag(NeapolitanBiomeTags.HAS_RARE_BANANA_PLANT).add(Biomes.BEACH, Biomes.STONY_SHORE);
		this.tag(NeapolitanBiomeTags.BANANA_PLANT_REQUIRES_SAND).add(Biomes.BEACH);
	}

	@SafeVarargs
	private void tag(ResourceKey<Biome> biome, TagKey<Biome>... tags) {
		for (TagKey<Biome> key : tags) {
			tag(key).add(biome);
		}
	}
}