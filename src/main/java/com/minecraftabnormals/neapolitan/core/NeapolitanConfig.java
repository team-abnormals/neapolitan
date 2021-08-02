package com.minecraftabnormals.neapolitan.core;

import com.minecraftabnormals.abnormals_core.core.annotations.ConfigKey;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class NeapolitanConfig {
	public static class Common {

		@ConfigKey("milk_cauldron_enabled")
		public final ConfigValue<Boolean> milkCauldron;

		@ConfigKey("milking_with_glass_bottles_enabled")
		public final ConfigValue<Boolean> milkingWithGlassBottles;


		@ConfigKey("strawberry_bush_generation_chance")
		public final ConfigValue<Integer> strawberryBushGenerationChance;

		@ConfigKey("vanilla_vine_generation_chance")
		public final ConfigValue<Integer> vanillaVineGenerationChance;

		@ConfigKey("adzuki_sprouts_generation_chance")
		public final ConfigValue<Integer> adzukiSproutsGenerationChance;

		@ConfigKey("mint_pond_generation_chance")
		public final ConfigValue<Integer> mintPondGenerationChance;


		@ConfigKey("banana_plants_generate_in_beaches")
		public final ConfigValue<Boolean> bananaPlantBeachGeneration;

		@ConfigKey("banana_plants_generate_in_jungles")
		public final ConfigValue<Boolean> bananaPlantJungleGeneration;

		@ConfigKey("plantain_spiders_spawn_in_jungle")
		public final ConfigValue<Boolean> plantainSpiderSpawning;

		@ConfigKey("plantain_spiders_spawn_from_bundles")
		public final ConfigValue<Boolean> plantainSpidersFromBundles;

		@ConfigKey("plantain_spiders_give_slipping")
		public final ConfigValue<Boolean> plantainSpidersGiveSlipping;


		@ConfigKey("chimpanzees_spawn_in_jungle")
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
			strawberryBushGenerationChance = builder.define("Strawberry Bush generation chance in Plains", 7);
			vanillaVineGenerationChance = builder.define("Vanilla Vine generation chance in Savannas", 6);
			adzukiSproutsGenerationChance = builder.define("Adzuki Sprouts generation chance in Forests", 6);
			mintPondGenerationChance = builder.define("Mint pond generation chance in Mountains", 5);
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
