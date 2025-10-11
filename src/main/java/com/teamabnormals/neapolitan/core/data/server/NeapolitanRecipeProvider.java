package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanBlockFamilies;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.*;
import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;
import static net.minecraft.world.item.Items.*;

public class NeapolitanRecipeProvider extends BlueprintRecipeProvider {
	public static final NotCondition ABNORMALS_DELIGHT_NOT_LOADED = new NotCondition(new ModLoadedCondition("abnormals_delight"));

	public NeapolitanRecipeProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(Neapolitan.MOD_ID, output, provider);
	}

	@Override
	public void buildRecipes(RecipeOutput consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ADZUKI_BUN).requires(ROASTED_ADZUKI_BEANS).requires(WHEAT).requires(BlueprintItemTags.MILK).unlockedBy(getHasName(ADZUKI_BEANS), has(ADZUKI_BEANS)).save(consumer);
		flavorRecipes(consumer, ROASTED_ADZUKI_BEANS, ADZUKI_ICE_CREAM, ADZUKI_MILKSHAKE, NeapolitanItems.ADZUKI_CAKE, ADZUKI_ICE_CREAM_BLOCK);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ADZUKI_STEW).requires(ROASTED_ADZUKI_BEANS, 2).requires(BEETROOT).requires(CARROT).requires(BROWN_MUSHROOM).requires(BOWL).unlockedBy(getHasName(ROASTED_ADZUKI_BEANS), has(ROASTED_ADZUKI_BEANS)).save(consumer.withConditions(ABNORMALS_DELIGHT_NOT_LOADED));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MAGIC_BEANS, 3).requires(ADZUKI_BEANS, 3).requires(FERMENTED_SPIDER_EYE).unlockedBy(getHasName(ADZUKI_BEANS), has(ADZUKI_BEANS)).save(consumer);
		foodCookingRecipes(consumer, ADZUKI_BEANS, ROASTED_ADZUKI_BEANS);

		twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, FROND_THATCH, BANANA_FROND);
		generateRecipes(consumer, NeapolitanBlockFamilies.FROND_THATCH_FAMILY, FeatureFlags.VANILLA_SET);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BANANA_BREAD).requires(NeapolitanItemTags.FOODS_BANANA).requires(WHEAT).requires(SUGAR).unlockedBy(getHasName(BANANA), has(NeapolitanItemTags.FOODS_BANANA)).save(consumer);
		threeByThreePacker(consumer, RecipeCategory.BUILDING_BLOCKS, BANANA_BUNDLE, BANANA_BUNCH);
		flavorRecipes(consumer, NeapolitanItemTags.FOODS_BANANA, getHasName(BANANA), BANANA_ICE_CREAM, BANANA_MILKSHAKE, NeapolitanItems.BANANA_CAKE, BANANA_ICE_CREAM_BLOCK);
		foodCookingRecipes(consumer, NeapolitanItemTags.FOODS_BANANA, DRIED_BANANA, getHasName(BANANA));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PAPER).requires(BANANA_FROND, 3).unlockedBy(getHasName(BANANA_FROND), has(BANANA_FROND)).save(consumer, getModConversionRecipeName(PAPER, BANANA_FROND));
		trimRecipes(consumer, PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE, BANANA_STALK);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CARVED_BANANA_STALK).pattern("##").pattern("##").define('#', BANANA_STALK).unlockedBy(getHasName(BANANA_STALK), has(BANANA_STALK)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, BANANARROW).requires(NeapolitanItemTags.FOODS_BANANA).requires(ARROW).unlockedBy(getHasName(BANANA), has(NeapolitanItemTags.FOODS_BANANA)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CHIMPANZEE_BANNER_PATTERN).requires(PAPER).requires(NeapolitanItems.CHIMPANZEE_HEAD).unlockedBy(getHasName(NeapolitanItems.CHIMPANZEE_HEAD), has(NeapolitanItems.CHIMPANZEE_HEAD)).save(consumer);
		threeByThreePacker(consumer, RecipeCategory.BUILDING_BLOCKS, BANANA_STALK, BANANA_FROND);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHOCOLATE_BAR, 4).requires(COCOA_BEANS, 2).requires(SUGAR).requires(BlueprintItemTags.MILK).unlockedBy(getHasName(COCOA_BEANS), has(COCOA_BEANS)).save(consumer);
		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BAR, RecipeCategory.FOOD, CHOCOLATE_BLOCK, getConversionRecipeName(CHOCOLATE_BAR, CHOCOLATE_BLOCK), null);
		flavorRecipes(consumer, NeapolitanItemTags.FOODS_CHOCOLATE_BAR, getHasName(CHOCOLATE_BAR), CHOCOLATE_ICE_CREAM, CHOCOLATE_MILKSHAKE, NeapolitanItems.CHOCOLATE_CAKE, CHOCOLATE_ICE_CREAM_BLOCK);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHOCOLATE_SPIDER_EYE, 2).requires(SPIDER_EYE).requires(SPIDER_EYE).requires(NeapolitanItemTags.FOODS_CHOCOLATE_BAR).unlockedBy(getHasName(CHOCOLATE_BAR), has(NeapolitanItemTags.FOODS_CHOCOLATE_BAR)).save(consumer);

		twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BRICKS, CHOCOLATE_BAR);
		generateRecipes(consumer, NeapolitanBlockFamilies.CHOCOLATE_BRICK_FAMILY, FeatureFlags.VANILLA_SET);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHISELED_CHOCOLATE_BRICKS, CHOCOLATE_BRICKS);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BRICK_SLAB, CHOCOLATE_BRICKS, 2);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BRICK_STAIRS, CHOCOLATE_BRICKS);
		stonecutterRecipe(consumer, RecipeCategory.DECORATIONS, CHOCOLATE_BRICK_WALL, CHOCOLATE_BRICKS);

		generateRecipes(consumer, NeapolitanBlockFamilies.CHOCOLATE_TILE_FAMILY, FeatureFlags.VANILLA_SET);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILES, 4).pattern("##").pattern("##").define('#', CHOCOLATE_BRICKS).unlockedBy(getHasName(CHOCOLATE_BRICKS), has(CHOCOLATE_BRICKS)).save(consumer);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_SLAB, CHOCOLATE_TILES, 2);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_STAIRS, CHOCOLATE_TILES);
		stonecutterRecipe(consumer, RecipeCategory.DECORATIONS, CHOCOLATE_TILE_WALL, CHOCOLATE_TILES);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILES, CHOCOLATE_BRICKS);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_SLAB, CHOCOLATE_BRICKS, 2);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_STAIRS, CHOCOLATE_BRICKS);
		stonecutterRecipe(consumer, RecipeCategory.DECORATIONS, CHOCOLATE_TILE_WALL, CHOCOLATE_BRICKS);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MILK_BUCKET).requires(Ingredient.of(MILK_BOTTLE), 3).requires(BUCKET).unlockedBy(getHasName(MILK_BOTTLE), has(MILK_BOTTLE)).save(consumer, Neapolitan.location(getSimpleRecipeName(MILK_BUCKET)));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MILK_BOTTLE, 3).requires(Tags.Items.BUCKETS_MILK).requires(GLASS_BOTTLE, 3).unlockedBy(getHasName(MILK_BUCKET), has(Tags.Items.BUCKETS_MILK)).save(consumer);

		foodCookingRecipes(consumer, MINT_CHOPS, COOKED_MINT_CHOPS);

		storageRecipes(consumer, RecipeCategory.FOOD, MINT_LEAVES, RecipeCategory.DECORATIONS, MINT_BASKET);
		flavorRecipes(consumer, MINT_LEAVES, MINT_ICE_CREAM, MINT_MILKSHAKE, NeapolitanItems.MINT_CAKE, MINT_ICE_CREAM_BLOCK);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MINT_CANDIES).requires(MINT_LEAVES).requires(SUGAR, 2).unlockedBy(getHasName(MINT_LEAVES), has(MINT_LEAVES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MINT_CHOPS).requires(MUTTON).requires(MINT_LEAVES).unlockedBy(getHasName(MINT_LEAVES), has(MINT_LEAVES)).save(consumer);
		conversionRecipe(consumer, MINT_SPROUT, MINT_LEAVES, getSimpleRecipeName(MINT_SPROUT));

		storageRecipes(consumer, RecipeCategory.FOOD, STRAWBERRIES, RecipeCategory.DECORATIONS, STRAWBERRY_BASKET);
		storageRecipes(consumer, RecipeCategory.FOOD, WHITE_STRAWBERRIES, RecipeCategory.DECORATIONS, WHITE_STRAWBERRY_BASKET);
		flavorRecipes(consumer, NeapolitanItemTags.FOODS_STRAWBERRY, getHasName(STRAWBERRIES), STRAWBERRY_ICE_CREAM, STRAWBERRY_MILKSHAKE, NeapolitanItems.STRAWBERRY_CAKE, STRAWBERRY_ICE_CREAM_BLOCK);
		conversionRecipe(consumer, STRAWBERRY_PIPS, STRAWBERRIES, getSimpleRecipeName(STRAWBERRY_PIPS));
		conversionRecipe(consumer, STRAWBERRY_PIPS, WHITE_STRAWBERRIES, getConversionRecipeName(STRAWBERRY_PIPS, WHITE_STRAWBERRIES));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_SCONES, 2).requires(WHEAT, 2).requires(NeapolitanItemTags.FOODS_STRAWBERRY).requires(SUGAR).unlockedBy(getHasName(STRAWBERRIES), has(NeapolitanItemTags.FOODS_STRAWBERRY)).save(consumer);

		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.FOOD, ADZUKI_BEANS, RecipeCategory.DECORATIONS, ADZUKI_CRATE, getConversionRecipeName(ADZUKI_BEANS, ADZUKI_CRATE), null);
		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.FOOD, ROASTED_ADZUKI_BEANS, RecipeCategory.DECORATIONS, ROASTED_ADZUKI_CRATE, getConversionRecipeName(ROASTED_ADZUKI_BEANS, ROASTED_ADZUKI_CRATE), null);
		storageRecipes(consumer, RecipeCategory.FOOD, BANANA, RecipeCategory.DECORATIONS, BANANA_CRATE);

		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, DRIED_VANILLA_PODS, RecipeCategory.FOOD, DRIED_VANILLA_POD_BLOCK, getConversionRecipeName(DRIED_VANILLA_PODS, DRIED_VANILLA_POD_BLOCK), null);
		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, VANILLA_PODS, RecipeCategory.FOOD, VANILLA_POD_BLOCK, getConversionRecipeName(VANILLA_PODS, VANILLA_POD_BLOCK), null);
		foodCookingRecipes(consumer, VANILLA_PODS, DRIED_VANILLA_PODS);
		flavorRecipes(consumer, DRIED_VANILLA_PODS, VANILLA_ICE_CREAM, VANILLA_MILKSHAKE, NeapolitanItems.VANILLA_CAKE, VANILLA_ICE_CREAM_BLOCK);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VANILLA_FUDGE, 4).requires(DRIED_VANILLA_PODS, 2).requires(SUGAR).requires(BlueprintItemTags.MILK).unlockedBy(getHasName(VANILLA_PODS), has(VANILLA_PODS)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VANILLA_PUDDING).requires(EGG).requires(SUGAR).requires(DRIED_VANILLA_PODS).requires(BOWL).unlockedBy(getHasName(VANILLA_PODS), has(VANILLA_PODS)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ADZUKI_CURRY).requires(ROASTED_ADZUKI_BEANS).requires(DRIED_BANANA).requires(CARROT).requires(BlueprintItemTags.PUMPKINS).requires(BOWL).unlockedBy(getHasName(ROASTED_ADZUKI_BEANS), has(ROASTED_ADZUKI_BEANS)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHOCOLATE_STRAWBERRIES).requires(NeapolitanItemTags.FOODS_STRAWBERRY).requires(NeapolitanItemTags.FOODS_CHOCOLATE_BAR).unlockedBy(getHasName(CHOCOLATE_BAR), has(NeapolitanItemTags.FOODS_CHOCOLATE_BAR)).unlockedBy(getHasName(STRAWBERRIES), has(STRAWBERRIES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MINT_CHOCOLATE).requires(MINT_LEAVES).requires(NeapolitanItemTags.FOODS_CHOCOLATE_BAR).unlockedBy(getHasName(CHOCOLATE_BAR), has(NeapolitanItemTags.FOODS_CHOCOLATE_BAR)).unlockedBy(getHasName(MINT_LEAVES), has(MINT_LEAVES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NEAPOLITAN_ICE_CREAM).requires(NeapolitanItemTags.FOODS_CHOCOLATE_BAR).requires(DRIED_VANILLA_PODS).requires(NeapolitanItemTags.FOODS_STRAWBERRY).requires(BlueprintItemTags.MILK).requires(SUGAR).requires(ICE_CUBES).requires(BOWL).unlockedBy(getHasName(ICE_CUBES), has(ICE_CUBES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NEAPOLITAN_ICE_CREAM).requires(VANILLA_ICE_CREAM).requires(NeapolitanItemTags.FOODS_CHOCOLATE_BAR).requires(NeapolitanItemTags.FOODS_STRAWBERRY).unlockedBy(getHasName(ICE_CUBES), has(ICE_CUBES)).save(consumer, getModConversionRecipeName(NEAPOLITAN_ICE_CREAM, VANILLA_ICE_CREAM));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_BANANA_SMOOTHIE).requires(Ingredient.of(NeapolitanItemTags.FOODS_STRAWBERRY), 2).requires(NeapolitanItemTags.FOODS_BANANA).requires(GLASS_BOTTLE).unlockedBy(getHasName(BANANA), has(NeapolitanItemTags.FOODS_BANANA)).unlockedBy(getHasName(STRAWBERRIES), has(NeapolitanItemTags.FOODS_STRAWBERRY)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_BEAN_BONBONS).requires(NeapolitanItemTags.FOODS_STRAWBERRY).requires(ROASTED_ADZUKI_BEANS).requires(SUGAR).unlockedBy(getHasName(STRAWBERRIES), has(STRAWBERRIES)).unlockedBy(getHasName(ROASTED_ADZUKI_BEANS), has(ROASTED_ADZUKI_BEANS)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VANILLA_CHOCOLATE_FINGERS).requires(NeapolitanItemTags.FOODS_CHOCOLATE_BAR).requires(DRIED_VANILLA_PODS).unlockedBy(getHasName(VANILLA_PODS), has(VANILLA_PODS)).unlockedBy(getHasName(CHOCOLATE_BAR), has(NeapolitanItemTags.FOODS_CHOCOLATE_BAR)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, CAKE).pattern("AAA").pattern("BEB").pattern("CCC").define('A', BlueprintItemTags.MILK).define('B', SUGAR).define('C', WHEAT).define('E', Tags.Items.EGGS).unlockedBy(getHasName(EGG), has(EGG)).save(consumer, Neapolitan.location(getSimpleRecipeName(CAKE)));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ICE).requires(ICE_CUBES, 9).unlockedBy(getHasName(ICE_CUBES), has(ICE_CUBES)).save(consumer, Neapolitan.location(getSimpleRecipeName(ICE)));
	}

	public static void flavorRecipes(RecipeOutput consumer, ItemLike item, ItemLike iceCream, ItemLike milkshake, ItemLike cake, ItemLike iceCreamBlock) {
		flavorRecipes(consumer, Ingredient.of(item), iceCream, milkshake, cake, iceCreamBlock, getHasName(item), has(item));
	}

	public static void flavorRecipes(RecipeOutput consumer, TagKey<Item> tag, String hasName, ItemLike iceCream, ItemLike milkshake, ItemLike cake, ItemLike iceCreamBlock) {
		flavorRecipes(consumer, Ingredient.of(tag), iceCream, milkshake, cake, iceCreamBlock, hasName, has(tag));
	}

	public static void flavorRecipes(RecipeOutput consumer, Ingredient ingredient, ItemLike iceCream, ItemLike milkshake, ItemLike cake, ItemLike iceCreamBlock, String hasName, Criterion<?> has) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, iceCream).requires(ingredient).requires(ICE_CUBES).requires(SUGAR).requires(BlueprintItemTags.MILK).requires(BOWL).unlockedBy(hasName, has).unlockedBy(getHasName(ICE_CUBES), has(ICE_CUBES)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, cake).pattern("ADA").pattern("BEB").pattern("CDC").define('A', BlueprintItemTags.MILK).define('D', ingredient).define('B', SUGAR).define('C', WHEAT).define('E', Tags.Items.EGGS).unlockedBy(hasName, has).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, milkshake, 3).requires(GLASS_BOTTLE, 3).requires(iceCream).requires(BlueprintItemTags.MILK).unlockedBy(getHasName(iceCream), has(iceCream)).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, iceCreamBlock, 8).pattern("###").pattern("#X#").pattern("###").define('X', iceCream).define('#', SNOW_BLOCK).unlockedBy(getHasName(iceCream), has(iceCream)).save(consumer);
	}

	public static void foodCookingRecipes(RecipeOutput consumer, TagKey<Item> input, ItemLike output, String hasName) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 200).unlockedBy(hasName, has(input)).save(consumer);
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 100).unlockedBy(hasName, has(input)).save(consumer, RecipeBuilder.getDefaultRecipeId(output) + "_from_smoking");
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 600).unlockedBy(hasName, has(input)).save(consumer, RecipeBuilder.getDefaultRecipeId(output) + "_from_campfire_cooking");
	}
}