package com.minecraftabnormals.neapolitan.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class NeapolitanConfig {
	public static class Common {
		public final ConfigValue<Boolean> milkingWithGlassBottles;
		public final ConfigValue<Integer> milkBucketUseTime;

		public final ConfigValue<Integer> strawberryBushGenerationChance;
		public final ConfigValue<Integer> vanillaVineGenerationChance;
		public final ConfigValue<Boolean> bananaPlantBeachGeneration;
		public final ConfigValue<Boolean> bananaPlantJungleGeneration;

		public final ConfigValue<Boolean> plantainSpiderSpawning;
		public final ConfigValue<Boolean> plantainSpidersFromBundles;
		public final ConfigValue<Boolean> plantainSpidersGiveSlipping;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("items");
			milkingWithGlassBottles = builder.comment("If milkable mobs can be milked with empty glass bottles").define("Milking with Glass Bottles", false);
			milkBucketUseTime = builder.comment("The use time for Milk Buckets, in ticks").define("Milk Bucket drink time", 8);
			builder.pop();
			builder.push("worldgen");
			builder.push("features");
			strawberryBushGenerationChance = builder.defineInRange("Strawberry Bush generation chance in Plains", 9, 0, 128);
			vanillaVineGenerationChance = builder.defineInRange("Vanilla Vine generation chance in Savannas", 6, 0, 128);
			bananaPlantBeachGeneration = builder.define("Banana Plant generation in Beaches", true);
			bananaPlantJungleGeneration = builder.define("Banana Plant generation in Jungles", true);
			builder.pop();
			builder.pop();
			builder.push("entities");
			builder.push("plantain_spider");
			plantainSpiderSpawning = builder.define("Plantain Spider spawning in Jungles", true);
			plantainSpidersFromBundles = builder.define("Plantain Spider spawn from Banana Bundles", true);
			plantainSpidersGiveSlipping = builder.define("Plantain Spiders give Slipping", true);
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
