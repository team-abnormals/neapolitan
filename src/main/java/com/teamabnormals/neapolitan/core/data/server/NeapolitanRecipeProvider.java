package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanBlockFamilies;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;

import java.util.function.Consumer;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.*;
import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;
import static net.minecraft.world.item.Items.*;

public class NeapolitanRecipeProvider extends BlueprintRecipeProvider {
	public static final NotCondition ABNORMALS_DELIGHT_NOT_LOADED = new NotCondition(new ModLoadedCondition("abnormals_delight"));
	public static final ModLoadedCondition BERRY_GOOD_LOADED = new ModLoadedCondition("berry_good");

	public NeapolitanRecipeProvider(PackOutput output) {
		super(Neapolitan.MOD_ID, output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ADZUKI_BUN.get()).requires(ROASTED_ADZUKI_BEANS.get()).requires(WHEAT).requires(BlueprintItemTags.MILK).unlockedBy(getHasName(ADZUKI_BEANS.get()), has(ADZUKI_BEANS.get())).save(consumer);
		cakeRecipe(consumer, ROASTED_ADZUKI_BEANS.get(), NeapolitanItems.ADZUKI_CAKE.get());
		iceCreamRecipes(consumer, ROASTED_ADZUKI_BEANS.get(), ADZUKI_ICE_CREAM.get(), ADZUKI_ICE_CREAM_BLOCK.get());
		milkshake(consumer, ADZUKI_ICE_CREAM.get(), ADZUKI_MILKSHAKE.get());
		conditionalRecipe(consumer, ABNORMALS_DELIGHT_NOT_LOADED, RecipeCategory.FOOD, ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ADZUKI_STEW.get()).requires(ROASTED_ADZUKI_BEANS.get(), 2).requires(BEETROOT).requires(CARROT).requires(BROWN_MUSHROOM).requires(BOWL).unlockedBy(getHasName(ROASTED_ADZUKI_BEANS.get()), has(ROASTED_ADZUKI_BEANS.get())));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MAGIC_BEANS.get(), 3).requires(ADZUKI_BEANS.get(), 3).requires(FERMENTED_SPIDER_EYE).unlockedBy(getHasName(ADZUKI_BEANS.get()), has(ADZUKI_BEANS.get())).save(consumer);
		foodCookingRecipes(consumer, ADZUKI_BEANS.get(), ROASTED_ADZUKI_BEANS.get());

		twoByTwoPacker(consumer, RecipeCategory.DECORATIONS, FROND_THATCH.get(), NeapolitanItems.BANANA_FROND.get());
		generateRecipes(consumer, NeapolitanBlockFamilies.FROND_THATCH_FAMILY);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BANANA_BREAD.get()).requires(NeapolitanItemTags.FRUITS_BANANA).requires(WHEAT).requires(SUGAR).unlockedBy(getHasName(BANANA.get()), has(NeapolitanItemTags.FRUITS_BANANA)).save(consumer);
		threeByThreePacker(consumer, RecipeCategory.FOOD, BANANA_BUNDLE.get(), BANANA_BUNCH.get());
		cakeRecipe(consumer, NeapolitanItemTags.FRUITS_BANANA, NeapolitanItems.BANANA_CAKE.get(), getHasName(BANANA.get()));
		iceCreamRecipes(consumer, NeapolitanItemTags.FRUITS_BANANA, BANANA_ICE_CREAM.get(), BANANA_ICE_CREAM_BLOCK.get());
		milkshake(consumer, BANANA_ICE_CREAM.get(), BANANA_MILKSHAKE.get());
		foodCookingRecipes(consumer, NeapolitanItemTags.FRUITS_BANANA, DRIED_BANANA.get(), getHasName(BANANA.get()));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PAPER).requires(NeapolitanItems.BANANA_FROND.get()).requires(NeapolitanItems.BANANA_FROND.get()).requires(NeapolitanItems.BANANA_FROND.get()).unlockedBy(getHasName(NeapolitanItems.BANANA_FROND.get()), has(NeapolitanItems.BANANA_FROND.get())).save(consumer, new ResourceLocation(Neapolitan.MOD_ID, getSimpleRecipeName(PAPER)));
		trimRecipes(consumer, PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.get(), BANANA_STALK.get());
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CARVED_BANANA_STALK.get()).pattern("##").pattern("##").define('#', BANANA_STALK.get()).unlockedBy(getHasName(BANANA_STALK.get()), has(BANANA_STALK.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, BANANARROW.get()).requires(NeapolitanItemTags.FRUITS_BANANA).requires(ARROW).unlockedBy(getHasName(BANANA.get()), has(NeapolitanItemTags.FRUITS_BANANA)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CHIMPANZEE_BANNER_PATTERN.get()).requires(PAPER).requires(NeapolitanItems.CHIMPANZEE_HEAD.get()).unlockedBy(getHasName(NeapolitanItems.CHIMPANZEE_HEAD.get()), has(NeapolitanItems.CHIMPANZEE_HEAD.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHOCOLATE_BAR.get(), 4).requires(COCOA_BEANS, 2).requires(SUGAR).requires(BlueprintItemTags.MILK).unlockedBy(getHasName(COCOA_BEANS), has(COCOA_BEANS)).save(consumer);
		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BAR.get(), RecipeCategory.FOOD, CHOCOLATE_BLOCK.get(), getConversionRecipeName(CHOCOLATE_BAR.get(), CHOCOLATE_BLOCK.get()), null);
		cakeRecipe(consumer, CHOCOLATE_BAR.get(), NeapolitanItems.CHOCOLATE_CAKE.get());
		iceCreamRecipes(consumer, CHOCOLATE_BAR.get(), CHOCOLATE_ICE_CREAM.get(), CHOCOLATE_ICE_CREAM_BLOCK.get());
		milkshake(consumer, CHOCOLATE_ICE_CREAM.get(), CHOCOLATE_MILKSHAKE.get());
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHOCOLATE_SPIDER_EYE.get(), 2).requires(SPIDER_EYE).requires(SPIDER_EYE).requires(CHOCOLATE_BAR.get()).unlockedBy(getHasName(CHOCOLATE_BAR.get()), has(CHOCOLATE_BAR.get())).save(consumer);

		twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BRICKS.get(), CHOCOLATE_BAR.get());
		generateRecipes(consumer, NeapolitanBlockFamilies.CHOCOLATE_BRICK_FAMILY);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHISELED_CHOCOLATE_BRICKS.get(), CHOCOLATE_BRICKS.get());
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BRICK_SLAB.get(), CHOCOLATE_BRICKS.get(), 2);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_BRICK_STAIRS.get(), CHOCOLATE_BRICKS.get());
		stonecutterRecipe(consumer, RecipeCategory.DECORATIONS, CHOCOLATE_BRICK_WALL.get(), CHOCOLATE_BRICKS.get());

		generateRecipes(consumer, NeapolitanBlockFamilies.CHOCOLATE_TILE_FAMILY);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILES.get(), 4).pattern("##").pattern("##").define('#', CHOCOLATE_BRICKS.get()).unlockedBy(getHasName(CHOCOLATE_BRICKS.get()), has(CHOCOLATE_BRICKS.get())).save(consumer);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_SLAB.get(), CHOCOLATE_TILES.get(), 2);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_STAIRS.get(), CHOCOLATE_TILES.get());
		stonecutterRecipe(consumer, RecipeCategory.DECORATIONS, CHOCOLATE_TILE_WALL.get(), CHOCOLATE_TILES.get());
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILES.get(), CHOCOLATE_BRICKS.get());
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_SLAB.get(), CHOCOLATE_BRICKS.get(), 2);
		stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, CHOCOLATE_TILE_STAIRS.get(), CHOCOLATE_BRICKS.get());
		stonecutterRecipe(consumer, RecipeCategory.DECORATIONS, CHOCOLATE_TILE_WALL.get(), CHOCOLATE_BRICKS.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MILK_BUCKET).requires(Ingredient.of(NeapolitanItemTags.BOTTLES_MILK), 3).requires(BUCKET).unlockedBy(getHasName(MILK_BOTTLE.get()), has(NeapolitanItemTags.BOTTLES_MILK)).save(consumer, new ResourceLocation(Neapolitan.MOD_ID, getSimpleRecipeName(MILK_BUCKET)));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MILK_BOTTLE.get(), 3).requires(BlueprintItemTags.BUCKETS_MILK).requires(GLASS_BOTTLE, 3).unlockedBy(getHasName(MILK_BUCKET), has(BlueprintItemTags.BUCKETS_MILK)).save(consumer);

		foodCookingRecipes(consumer, MINT_CHOPS.get(), COOKED_MINT_CHOPS.get());

		conditionalStorageRecipes(consumer, BERRY_GOOD_LOADED, RecipeCategory.FOOD, MINT_LEAVES.get(), RecipeCategory.DECORATIONS, MINT_BASKET.get());
		cakeRecipe(consumer, MINT_LEAVES.get(), NeapolitanItems.MINT_CAKE.get());
		iceCreamRecipes(consumer, MINT_LEAVES.get(), MINT_ICE_CREAM.get(), MINT_ICE_CREAM_BLOCK.get());
		milkshake(consumer, MINT_ICE_CREAM.get(), MINT_MILKSHAKE.get());
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MINT_CANDIES.get()).requires(MINT_LEAVES.get()).requires(SUGAR, 2).unlockedBy(getHasName(MINT_LEAVES.get()), has(MINT_LEAVES.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MINT_CHOPS.get()).requires(MUTTON).requires(MINT_LEAVES.get()).unlockedBy(getHasName(MINT_LEAVES.get()), has(MINT_LEAVES.get())).save(consumer);
		conversionRecipe(consumer, MINT_SPROUT.get(), MINT_LEAVES.get(), getSimpleRecipeName(MINT_SPROUT.get()));

		conditionalStorageRecipes(consumer, BERRY_GOOD_LOADED, RecipeCategory.FOOD, STRAWBERRIES.get(), RecipeCategory.DECORATIONS, STRAWBERRY_BASKET.get());
		conditionalStorageRecipes(consumer, BERRY_GOOD_LOADED, RecipeCategory.FOOD, WHITE_STRAWBERRIES.get(), RecipeCategory.DECORATIONS, WHITE_STRAWBERRY_BASKET.get());
		cakeRecipe(consumer, STRAWBERRIES.get(), NeapolitanItems.STRAWBERRY_CAKE.get());
		iceCreamRecipes(consumer, STRAWBERRIES.get(), STRAWBERRY_ICE_CREAM.get(), STRAWBERRY_ICE_CREAM_BLOCK.get());
		milkshake(consumer, STRAWBERRY_ICE_CREAM.get(), STRAWBERRY_MILKSHAKE.get());
		conversionRecipe(consumer, STRAWBERRY_PIPS.get(), STRAWBERRIES.get(), getSimpleRecipeName(STRAWBERRY_PIPS.get()));
		conversionRecipe(consumer, STRAWBERRY_PIPS.get(), WHITE_STRAWBERRIES.get(), getConversionRecipeName(STRAWBERRY_PIPS.get(), WHITE_STRAWBERRIES.get()));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_SCONES.get(), 2).requires(WHEAT, 2).requires(NeapolitanItemTags.FRUITS_STRAWBERRY).requires(SUGAR).unlockedBy(getHasName(STRAWBERRIES.get()), has(NeapolitanItemTags.FRUITS_STRAWBERRY)).save(consumer);

		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, DRIED_VANILLA_PODS.get(), RecipeCategory.FOOD, DRIED_VANILLA_POD_BLOCK.get(), getConversionRecipeName(DRIED_VANILLA_PODS.get(), DRIED_VANILLA_POD_BLOCK.get()), null);
		storageRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, VANILLA_PODS.get(), RecipeCategory.FOOD, VANILLA_POD_BLOCK.get(), getConversionRecipeName(VANILLA_PODS.get(), VANILLA_POD_BLOCK.get()), null);
		foodCookingRecipes(consumer, VANILLA_PODS.get(), DRIED_VANILLA_PODS.get());
		cakeRecipe(consumer, DRIED_VANILLA_PODS.get(), NeapolitanItems.VANILLA_CAKE.get());
		iceCreamRecipes(consumer, DRIED_VANILLA_PODS.get(), VANILLA_ICE_CREAM.get(), VANILLA_ICE_CREAM_BLOCK.get());
		milkshake(consumer, DRIED_VANILLA_PODS.get(), VANILLA_MILKSHAKE.get());
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VANILLA_FUDGE.get(), 4).requires(DRIED_VANILLA_PODS.get(), 2).requires(SUGAR).requires(BlueprintItemTags.MILK).unlockedBy(getHasName(VANILLA_PODS.get()), has(VANILLA_PODS.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VANILLA_PUDDING.get()).requires(EGG).requires(SUGAR).requires(DRIED_VANILLA_PODS.get()).requires(BOWL).unlockedBy(getHasName(VANILLA_PODS.get()), has(VANILLA_PODS.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ADZUKI_CURRY.get()).requires(ROASTED_ADZUKI_BEANS.get()).requires(DRIED_BANANA.get()).requires(CARROT).requires(BlueprintItemTags.PUMPKINS).requires(BOWL).unlockedBy(getHasName(ROASTED_ADZUKI_BEANS.get()), has(ROASTED_ADZUKI_BEANS.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHOCOLATE_STRAWBERRIES.get()).requires(NeapolitanItemTags.FRUITS_STRAWBERRY).requires(CHOCOLATE_BAR.get()).unlockedBy(getHasName(CHOCOLATE_BAR.get()), has(CHOCOLATE_BAR.get())).unlockedBy(getHasName(STRAWBERRIES.get()), has(STRAWBERRIES.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MINT_CHOCOLATE.get()).requires(MINT_LEAVES.get()).requires(CHOCOLATE_BAR.get()).unlockedBy(getHasName(CHOCOLATE_BAR.get()), has(CHOCOLATE_BAR.get())).unlockedBy(getHasName(MINT_LEAVES.get()), has(MINT_LEAVES.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NEAPOLITAN_ICE_CREAM.get()).requires(CHOCOLATE_BAR.get()).requires(DRIED_VANILLA_PODS.get()).requires(NeapolitanItemTags.FRUITS_STRAWBERRY).requires(BlueprintItemTags.MILK).requires(SUGAR).requires(ICE_CUBES.get()).requires(BOWL).unlockedBy(getHasName(ICE_CUBES.get()), has(ICE_CUBES.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NEAPOLITAN_ICE_CREAM.get()).requires(VANILLA_ICE_CREAM.get()).requires(CHOCOLATE_BAR.get()).requires(NeapolitanItemTags.FRUITS_STRAWBERRY).unlockedBy(getHasName(ICE_CUBES.get()), has(ICE_CUBES.get())).save(consumer, getModConversionRecipeName(NEAPOLITAN_ICE_CREAM.get(), VANILLA_ICE_CREAM.get()));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_BANANA_SMOOTHIE.get()).requires(Ingredient.of(NeapolitanItemTags.FRUITS_STRAWBERRY), 2).requires(NeapolitanItemTags.FRUITS_BANANA).requires(GLASS_BOTTLE).unlockedBy(getHasName(BANANA.get()), has(NeapolitanItemTags.FRUITS_BANANA)).unlockedBy(getHasName(STRAWBERRIES.get()), has(NeapolitanItemTags.FRUITS_STRAWBERRY)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, STRAWBERRY_BEAN_BONBONS.get()).requires(NeapolitanItemTags.FRUITS_STRAWBERRY).requires(ROASTED_ADZUKI_BEANS.get()).requires(SUGAR).unlockedBy(getHasName(STRAWBERRIES.get()), has(STRAWBERRIES.get())).unlockedBy(getHasName(ROASTED_ADZUKI_BEANS.get()), has(ROASTED_ADZUKI_BEANS.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VANILLA_CHOCOLATE_FINGERS.get()).requires(CHOCOLATE_BAR.get()).requires(DRIED_VANILLA_PODS.get()).unlockedBy(getHasName(VANILLA_PODS.get()), has(VANILLA_PODS.get())).unlockedBy(getHasName(CHOCOLATE_BAR.get()), has(CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, CAKE).pattern("AAA").pattern("BEB").pattern("CCC").define('A', BlueprintItemTags.MILK).define('B', SUGAR).define('C', WHEAT).define('E', Tags.Items.EGGS).unlockedBy(getHasName(EGG), has(EGG)).save(consumer, new ResourceLocation(Neapolitan.MOD_ID, getSimpleRecipeName(CAKE)));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ICE).requires(ICE_CUBES.get(), 9).unlockedBy(getHasName(ICE_CUBES.get()), has(ICE_CUBES.get())).save(consumer, new ResourceLocation(Neapolitan.MOD_ID, getSimpleRecipeName(ICE)));
	}

	public static void cakeRecipe(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike output) {
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, output)
				.pattern("ADA")
				.pattern("BEB")
				.pattern("CDC")
				.define('A', BlueprintItemTags.MILK)
				.define('D', ingredient)
				.define('B', SUGAR)
				.define('C', WHEAT)
				.define('E', Tags.Items.EGGS)
				.unlockedBy(getHasName(ingredient), has(ingredient))
				.save(consumer);
	}

	public static void cakeRecipe(Consumer<FinishedRecipe> consumer, TagKey<Item> ingredient, ItemLike output, String hasName) {
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, output)
				.pattern("ADA")
				.pattern("BEB")
				.pattern("CDC")
				.define('A', BlueprintItemTags.MILK)
				.define('D', ingredient)
				.define('B', SUGAR)
				.define('C', WHEAT)
				.define('E', Tags.Items.EGGS)
				.unlockedBy(hasName, has(ingredient))
				.save(consumer);
	}

	public static void iceCreamRecipes(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike iceCream, ItemLike iceCreamBlock) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, iceCream)
				.requires(ingredient)
				.requires(ICE_CUBES.get())
				.requires(SUGAR)
				.requires(BlueprintItemTags.MILK)
				.requires(BOWL)
				.unlockedBy(getHasName(ICE_CUBES.get()), has(ICE_CUBES.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, iceCreamBlock, 8)
				.pattern("###")
				.pattern("#X#")
				.pattern("###")
				.define('X', iceCream)
				.define('#', SNOW_BLOCK)
				.unlockedBy(getHasName(iceCream), has(iceCream))
				.save(consumer);
	}

	public static void iceCreamRecipes(Consumer<FinishedRecipe> consumer, TagKey<Item> ingredient, ItemLike iceCream, ItemLike iceCreamBlock) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, iceCream)
				.requires(ingredient)
				.requires(ICE_CUBES.get())
				.requires(SUGAR)
				.requires(BlueprintItemTags.MILK)
				.requires(BOWL)
				.unlockedBy(getHasName(ICE_CUBES.get()), has(ICE_CUBES.get()))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, iceCreamBlock, 8)
				.pattern("###")
				.pattern("#X#")
				.pattern("###")
				.define('X', iceCream)
				.define('#', SNOW_BLOCK)
				.unlockedBy(getHasName(iceCream), has(iceCream))
				.save(consumer);
	}

	public static void milkshake(Consumer<FinishedRecipe> consumer, ItemLike iceCream, ItemLike milkshake) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, milkshake, 3)
				.requires(GLASS_BOTTLE, 3)
				.requires(iceCream)
				.requires(BlueprintItemTags.MILK)
				.unlockedBy(getHasName(iceCream), has(iceCream))
				.save(consumer);
	}

	public static void foodCookingRecipes(Consumer<FinishedRecipe> consumer, TagKey<Item> input, ItemLike output, String hasName) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 200).unlockedBy(hasName, has(input)).save(consumer);
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 100).unlockedBy(hasName, has(input)).save(consumer, RecipeBuilder.getDefaultRecipeId(output) + "_from_smoking");
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 600).unlockedBy(hasName, has(input)).save(consumer, RecipeBuilder.getDefaultRecipeId(output) + "_from_campfire_cooking");
	}
}