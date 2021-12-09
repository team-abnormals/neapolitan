package com.minecraftabnormals.neapolitan.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class NeapolitanConfig {
	public static class Common {
		public final ConfigValue<Boolean> milkCauldron;
		public final ConfigValue<Boolean> milkingWithGlassBottles;

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

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("items");
			builder.push("milk_bottles");
			milkCauldron = builder.comment("If milk can be inserted into cauldrons").define("Milk Cauldron", true);
			milkingWithGlassBottles = builder.comment("If milkable mobs can be milked with empty glass bottles").define("Milking with Glass Bottles", false);
			builder.pop();
			builder.pop();
			builder.push("world");
			builder.push("generation");
			strawberryBushGenerationChance = builder.define("Strawberry Bush generation chance in Plains", 12);
			vanillaVineGenerationChance = builder.define("Vanilla Vine generation chance in Savannas", 10);
			adzukiSproutsGenerationChance = builder.define("Adzuki Sprouts generation chance in Forests", 7);
			mintPondGenerationChance = builder.define("Mint pond generation chance in Mountains", 6);
			bananaPlantBeachGeneration = builder.define("Banana Plant generation in Beaches", true);
			bananaPlantJungleGeneration = builder.define("Banana Plant generation in Jungles", true);
			builder.pop();
			builder.pop();
			builder.push("mobs");
			builder.push("plantain_spider");
			plantainSpiderSpawning = builder.define("Plantain Spider spawning in Jungles", true);
			plantainSpidersFromBundles = builder.define("Plantain Spider spawn from Banana Bundles", true);
			plantainSpidersGiveSlipping = builder.define("Plantain Spiders give Slipping", true);
			builder.pop();
			builder.push("chimpanzee");
			chimpanzeeSpawning = builder.define("Chimpanzee spawning in Jungles", true);
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
