package com.teamabnormals.neapolitan.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class NeapolitanConfig {
	public static class Common {
		public final ConfigValue<Boolean> milkCauldron;
		public final ConfigValue<Boolean> milkingWithGlassBottles;

		public final ConfigValue<Integer> whiteStrawberryMinHeight;
		public final ConfigValue<Boolean> strawberryBushArthropodInvisibility;

		public final ConfigValue<Integer> strawberryBushGenerationChance;
		public final ConfigValue<Integer> vanillaVineGenerationChance;
		public final ConfigValue<Integer> adzukiSproutsGenerationChance;
		public final ConfigValue<Integer> mintPondGenerationChance;

		public final ConfigValue<Boolean> bananaPlantBeachGeneration;
		public final ConfigValue<Boolean> bananaPlantJungleGeneration;

		public final ConfigValue<Boolean> plantainSpiderSpawning;
		public final ConfigValue<Boolean> plantainSpidersFromBundles;
		public final ConfigValue<Boolean> plantainSpidersGiveSlipping;

		public final ConfigValue<Boolean> chimpanzeeSpawning;
		public final ConfigValue<Double> chimpanzeeGroupChance;
		public final ConfigValue<Integer> chimpanzeeMaxGroupSize;
		public final ConfigValue<Integer> chimpanzeeMinSpawnAttempts;
		public final ConfigValue<Integer> chimpanzeeMaxSpawnAttempts;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("items");
			builder.push("milk_bottles");
			milkCauldron = builder.comment("If milk can be inserted into cauldrons").define("Milk Cauldron", true);
			milkingWithGlassBottles = builder.comment("If milkable mobs can be milked with empty glass bottles").define("Milking with Glass Bottles", false);
			builder.pop();
			builder.pop();
			builder.push("blocks");
			builder.push("strawberry_bush");
			whiteStrawberryMinHeight = builder.comment("The minimum height required for Strawberry Bushes to grow White Strawberries").define("White Strawberry minimum height", 256);
			strawberryBushArthropodInvisibility = builder.comment("If arthropods that go through Strawberry Bushes are given Invisibility").define("Strawberry Bushes give arthropods Invisibility", true);
			builder.pop();
			builder.pop();
			builder.push("world");
			builder.push("generation");
			strawberryBushGenerationChance = builder.comment("The chance Strawberry Bushes have to generate in Plains biomes (larger = more common)").define("Strawberry Bush chance", 64);
			vanillaVineGenerationChance = builder.comment("The chance Vanilla Vines have to generate in Savanna biomes (larger = more common)").define("Vanilla Vine chance", 64);
			adzukiSproutsGenerationChance = builder.comment("The chance Adzuki Sprouts have to generate in Forest biomes (larger = more common)").define("Adzuki Sprouts chance", 64);
			mintPondGenerationChance = builder.comment("The chance ponds with Mint have to generate in Mountain biomes (smaller = more common)").define("Mint pond chance", 8);
			bananaPlantBeachGeneration = builder.define("Banana Plants generate in Beach biomes", true);
			bananaPlantJungleGeneration = builder.define("Banana Plants generate in Jungle biomes", true);
			builder.pop();
			builder.pop();
			builder.push("mobs");
			builder.push("plantain_spider");
			plantainSpiderSpawning = builder.define("Plantain Spider spawn in Jungles", true);
			plantainSpidersFromBundles = builder.define("Plantain Spider spawn from Banana Bundles", true);
			plantainSpidersGiveSlipping = builder.define("Plantain Spiders give Slipping", true);
			builder.pop();
			builder.push("chimpanzee");
			chimpanzeeSpawning = builder.define("Chimpanzee spawn in Jungles", true);
			chimpanzeeGroupChance = builder.comment("The percentage chance for a Banana Plant to generate with a group of Chimpanzees").define("Chimpanzee group chance", 0.25D);
			chimpanzeeMaxGroupSize = builder.comment("The maximum amount of Chimpanzees that can spawn with a Banana Plant").define("Chimpanzee maximum group size", 10);
			chimpanzeeMinSpawnAttempts = builder.comment("The minimum attempts for Chimpanzees to spawn with a Banana Plant").define("Chimpanzee minimum spawn attempts", 12);
			chimpanzeeMaxSpawnAttempts = builder.comment("The maximum attempts for Chimpanzees to spawn with a Banana Plant").define("Chimpanzee maximum spawn attempts", 24);
			builder.pop();
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
