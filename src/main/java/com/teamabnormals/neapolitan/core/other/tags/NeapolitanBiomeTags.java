package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class NeapolitanBiomeTags {
	public static final TagKey<Biome> SPAWNS_RAINFOREST_VARIANT_CHIMPANZEES = biomeTag("spawns_rainforest_variant_chimpanzees");
	public static final TagKey<Biome> SPAWNS_BAMBOO_VARIANT_CHIMPANZEES = biomeTag("spawns_bamboo_variant_chimpanzees");

	public static final TagKey<Biome> HAS_CHIMPANZEE = biomeTag("has_animal/chimpanzee");
	public static final TagKey<Biome> HAS_PLANTAIN_SPIDER = biomeTag("has_monster/plantain_spider");

	public static final TagKey<Biome> HAS_STRAWBERRY_BUSH = biomeTag("has_feature/strawberry_bush");
	public static final TagKey<Biome> HAS_ADZUKI_SPROUTS = biomeTag("has_feature/adzuki_sprouts");
	public static final TagKey<Biome> HAS_VANILLA_VINE = biomeTag("has_feature/vanilla_vine");
	public static final TagKey<Biome> HAS_MINT_POND = biomeTag("has_feature/mint_pond");

	public static final TagKey<Biome> HAS_COMMON_BANANA_PLANT = biomeTag("has_feature/banana_plant/common");
	public static final TagKey<Biome> HAS_UNCOMMON_BANANA_PLANT = biomeTag("has_feature/banana_plant/uncommon");
	public static final TagKey<Biome> HAS_RARE_BANANA_PLANT = biomeTag("has_feature/banana_plant/rare");
	public static final TagKey<Biome> BANANA_PLANT_REQUIRES_SAND = biomeTag("banana_plant_requires_sand");

	private static TagKey<Biome> biomeTag(String name) {
		return TagUtil.biomeTag(Neapolitan.MOD_ID, name);
	}
}
