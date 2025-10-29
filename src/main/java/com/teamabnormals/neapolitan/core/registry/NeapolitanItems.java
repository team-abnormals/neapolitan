package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.neapolitan.common.item.*;
import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBannerPatternTags;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanIceCreamFlavors;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanJukeboxSongs;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanTrimPatterns;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class NeapolitanItems {
	public static final ItemSubRegistryHelper ITEMS = Neapolitan.REGISTRY_HELPER.getItemSubHelper();

	public static final DeferredItem<Item> MILK_BOTTLE = ITEMS.createItem("milk_bottle", () -> new MilkBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredItem<Item> ICE_CUBES = ITEMS.createItem("ice_cubes", () -> new IceCubesItem(new Item.Properties().food(NeapolitanFoods.ICE_CUBES)));
	public static final DeferredItem<Item> WAFFLE_CONE = ITEMS.createItem("waffle_cone", () -> new Item(new Item.Properties().food(NeapolitanFoods.ICE_CUBES)));

	public static final DeferredItem<Item> ICE_CREAM = ITEMS.createItem("ice_cream", () -> new IceCreamItem(new Item.Properties().food(NeapolitanFoods.ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).component(NeapolitanDataComponents.ICE_CREAM.get(), new IceCream(NeapolitanIceCreamFlavors.VANILLA, NeapolitanIceCreamFlavors.CHOCOLATE, NeapolitanIceCreamFlavors.STRAWBERRY))));
	public static final DeferredItem<Item> ICE_CREAM_CONE = ITEMS.createItem("ice_cream_cone", () -> new IceCreamItem(new Item.Properties().food(NeapolitanFoods.ICE_CREAM_CONE).component(NeapolitanDataComponents.ICE_CREAM.get(), new IceCream(NeapolitanIceCreamFlavors.VANILLA, NeapolitanIceCreamFlavors.CHOCOLATE, NeapolitanIceCreamFlavors.STRAWBERRY))));

	public static final DeferredItem<Item> VANILLA_PODS = ITEMS.createItem("vanilla_pods", () -> new ItemNameBlockItem(NeapolitanBlocks.VANILLA_VINE.get(), new Item.Properties()));
	public static final DeferredItem<Item> DRIED_VANILLA_PODS = ITEMS.createItem("dried_vanilla_pods", () -> new Item(new Item.Properties().food(NeapolitanFoods.DRIED_VANILLA_PODS)));
	public static final DeferredItem<Item> VANILLA_FUDGE = ITEMS.createItem("vanilla_fudge", () -> new Item(new Item.Properties().food(NeapolitanFoods.VANILLA_FUDGE)));
	public static final DeferredItem<Item> VANILLA_PUDDING = ITEMS.createItem("vanilla_pudding", () -> new Item(new Item.Properties().food(NeapolitanFoods.VANILLA_PUDDING).craftRemainder(Items.BOWL).stacksTo(1)));
	public static final DeferredItem<Item> VANILLA_CAKE = ITEMS.createItem("vanilla_cake", () -> new BlockItem(NeapolitanBlocks.VANILLA_CAKE.get(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> VANILLA_MILKSHAKE = ITEMS.createItem("vanilla_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.VANILLA_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

	public static final DeferredItem<Item> CHOCOLATE_BAR = ITEMS.createItem("chocolate_bar", () -> new Item(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_BAR)));
	public static final DeferredItem<Item> CHOCOLATE_SPIDER_EYE = ITEMS.createItem("chocolate_spider_eye", () -> new Item(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_SPIDER_EYE)));
	public static final DeferredItem<Item> CHOCOLATE_CAKE = ITEMS.createItem("chocolate_cake", () -> new BlockItem(NeapolitanBlocks.CHOCOLATE_CAKE.get(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> CHOCOLATE_MILKSHAKE = ITEMS.createItem("chocolate_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

	public static final DeferredItem<Item> STRAWBERRY_PIPS = ITEMS.createItem("strawberry_pips", () -> new ItemNameBlockItem(NeapolitanBlocks.STRAWBERRY_BUSH.get(), new Item.Properties()));
	public static final DeferredItem<Item> STRAWBERRIES = ITEMS.createItem("strawberries", () -> new HealingItem(2.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRIES)));
	public static final DeferredItem<Item> WHITE_STRAWBERRIES = ITEMS.createItem("white_strawberries", () -> new HealingItem(4.0F, new Item.Properties().food(NeapolitanFoods.WHITE_STRAWBERRIES)));
	public static final DeferredItem<Item> STRAWBERRY_SCONES = ITEMS.createItem("strawberry_scones", () -> new HealingItem(1.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_SCONES)));
	public static final DeferredItem<Item> STRAWBERRY_CAKE = ITEMS.createItem("strawberry_cake", () -> new BlockItem(NeapolitanBlocks.STRAWBERRY_CAKE.get(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> STRAWBERRY_MILKSHAKE = ITEMS.createItem("strawberry_milkshake", () -> new HealingMilkshakeItem(3.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

	public static final DeferredItem<Item> BANANA = ITEMS.createItem("banana", () -> new Item(new Item.Properties().food(NeapolitanFoods.BANANA)));
	public static final DeferredItem<Item> BANANA_BUNCH = ITEMS.createItem("banana_bunch", () -> new BananaBunchItem(new Item.Properties()));
	public static final DeferredItem<Item> DRIED_BANANA = ITEMS.createItem("dried_banana", () -> new Item(new Item.Properties().food(NeapolitanFoods.DRIED_BANANA)));
	public static final DeferredItem<Item> BANANA_BREAD = ITEMS.createItem("banana_bread", () -> new Item(new Item.Properties().food(NeapolitanFoods.BANANA_BREAD)));
	public static final DeferredItem<Item> BANANA_CAKE = ITEMS.createItem("banana_cake", () -> new BlockItem(NeapolitanBlocks.BANANA_CAKE.get(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> BANANA_MILKSHAKE = ITEMS.createItem("banana_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.BANANA_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
	public static final DeferredItem<Item> BANANARROW = ITEMS.createItem("bananarrow", () -> new BananarrowItem(new Item.Properties()));

	public static final DeferredItem<Item> MINT_SPROUT = ITEMS.createItem("mint_sprout", () -> new ItemNameBlockItem(NeapolitanBlocks.MINT.get(), new Item.Properties()));
	public static final DeferredItem<Item> MINT_LEAVES = ITEMS.createItem("mint_leaves", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_LEAVES)));
	public static final DeferredItem<Item> MINT_CANDIES = ITEMS.createItem("mint_candies", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_CANDIES)));
	public static final DeferredItem<Item> MINT_CHOPS = ITEMS.createItem("mint_chops", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_CHOPS)));
	public static final DeferredItem<Item> COOKED_MINT_CHOPS = ITEMS.createItem("cooked_mint_chops", () -> new Item(new Item.Properties().food(NeapolitanFoods.COOKED_MINT_CHOPS)));
	public static final DeferredItem<Item> MINT_CAKE = ITEMS.createItem("mint_cake", () -> new BlockItem(NeapolitanBlocks.MINT_CAKE.get(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> MINT_MILKSHAKE = ITEMS.createItem("mint_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.MINT_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

	public static final DeferredItem<Item> ADZUKI_BEANS = ITEMS.createItem("adzuki_beans", () -> new AdzukiBeansItem(new Item.Properties()));
	public static final DeferredItem<Item> ROASTED_ADZUKI_BEANS = ITEMS.createItem("roasted_adzuki_beans", () -> new Item(new Item.Properties().food(NeapolitanFoods.ROASTED_ADZUKI_BEANS)));
	public static final DeferredItem<Item> ADZUKI_BUN = ITEMS.createItem("adzuki_bun", () -> new Item(new Item.Properties().food(NeapolitanFoods.ADZUKI_BUN)));
	public static final DeferredItem<Item> ADZUKI_STEW = ITEMS.createItem("adzuki_stew", () -> new Item(new Item.Properties().food(NeapolitanFoods.ADZUKI_STEW).stacksTo(1)));
	public static final DeferredItem<Item> ADZUKI_CAKE = ITEMS.createItem("adzuki_cake", () -> new BlockItem(NeapolitanBlocks.ADZUKI_CAKE.get(), new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> ADZUKI_MILKSHAKE = ITEMS.createItem("adzuki_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.ADZUKI_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

	public static final DeferredItem<Item> VANILLA_CHOCOLATE_FINGERS = ITEMS.createItem("vanilla_chocolate_fingers", () -> new Item(new Item.Properties().food(NeapolitanFoods.VANILLA_CHOCOLATE_FINGERS)));
	public static final DeferredItem<Item> CHOCOLATE_STRAWBERRIES = ITEMS.createItem("chocolate_strawberries", () -> new HealingItem(1.0F, new Item.Properties().food(NeapolitanFoods.CHOCOLATE_STRAWBERRIES)));
	public static final DeferredItem<Item> STRAWBERRY_BANANA_SMOOTHIE = ITEMS.createItem("strawberry_banana_smoothie", () -> new HealingDrinkItem(2.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_BANANA_SMOOTHIE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
	public static final DeferredItem<Item> MINT_CHOCOLATE = ITEMS.createItem("mint_chocolate", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_CHOCOLATE)));
	public static final DeferredItem<Item> STRAWBERRY_BEAN_BONBONS = ITEMS.createItem("strawberry_bean_bonbons", () -> new HealingItem(2.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_BEAN_BONBONS)));
	public static final DeferredItem<Item> ADZUKI_CURRY = ITEMS.createItem("adzuki_curry", () -> new Item(new Item.Properties().food(NeapolitanFoods.ADZUKI_CURRY).stacksTo(1)));

	public static final DeferredItem<Item> REFLECTION_POTTERY_SHERD = ITEMS.createItem("reflection_pottery_sherd", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> SCREAM_POTTERY_SHERD = ITEMS.createItem("scream_pottery_sherd", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> SNACK_POTTERY_SHERD = ITEMS.createItem("snack_pottery_sherd", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> SPIDER_POTTERY_SHERD = ITEMS.createItem("spider_pottery_sherd", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> CHIMPANZEE_HEAD = ITEMS.createItem("chimpanzee_head", () -> new StandingAndWallBlockItem(NeapolitanBlocks.CHIMPANZEE_HEAD.get(), NeapolitanBlocks.CHIMPANZEE_WALL_HEAD.get(), new Item.Properties().rarity(Rarity.UNCOMMON), Direction.DOWN));

	public static final DeferredItem<Item> MUSIC_DISC_HULLABALOO = ITEMS.createItem("music_disc_hullabaloo", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(NeapolitanJukeboxSongs.HULLABALOO)));
	public static final DeferredItem<Item> CHIMPANZEE_BANNER_PATTERN = ITEMS.createItem("chimpanzee_banner_pattern", () -> new BannerPatternItem(NeapolitanBannerPatternTags.PATTERN_ITEM_CHIMPANZEE, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.createItem("primal_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(NeapolitanTrimPatterns.PRIMAL));

	public static final DeferredItem<DeferredSpawnEggItem> CHIMPANZEE_SPAWN_EGG = ITEMS.createSpawnEggItem("chimpanzee", NeapolitanEntityTypes.CHIMPANZEE::get, 0x1F1626, 0xAD8064);
	public static final DeferredItem<DeferredSpawnEggItem> PLANTAIN_SPIDER_SPAWN_EGG = ITEMS.createSpawnEggItem("plantain_spider", NeapolitanEntityTypes.PLANTAIN_SPIDER::get, 0xAD870A, 0x33202A);

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(Neapolitan.MOD_ID)
				.tab(FOOD_AND_DRINKS)
				.addItemsAfter(of(Items.MELON_SLICE), STRAWBERRIES, CHOCOLATE_STRAWBERRIES, WHITE_STRAWBERRIES, BANANA, DRIED_BANANA)
				.addItemsBefore(of(Items.DRIED_KELP), DRIED_VANILLA_PODS, VANILLA_CHOCOLATE_FINGERS, MINT_LEAVES, ROASTED_ADZUKI_BEANS)
				.addItemsAfter(of(Items.COOKED_MUTTON), MINT_CHOPS, COOKED_MINT_CHOPS)
				.addItemsAfter(of(Items.RABBIT_STEW), ADZUKI_STEW, ADZUKI_CURRY)
				.addItemsAfter(of(Items.BREAD), BANANA_BREAD, ADZUKI_BUN)
				.addItemsBefore(of(Items.COOKIE), VANILLA_FUDGE, CHOCOLATE_BAR, MINT_CHOCOLATE)
				.addItemsAfter(of(Items.COOKIE), STRAWBERRY_SCONES, WAFFLE_CONE)
				.addItemsAfter(of(Items.CAKE), VANILLA_CAKE, CHOCOLATE_CAKE, STRAWBERRY_CAKE, BANANA_CAKE, MINT_CAKE, ADZUKI_CAKE)
				.addItemsAfter(of(Items.PUMPKIN_PIE), MINT_CANDIES, STRAWBERRY_BEAN_BONBONS)
				.addItemsAfter(of(Items.SPIDER_EYE), CHOCOLATE_SPIDER_EYE)
				.addItemsBefore(of(Items.MILK_BUCKET), VANILLA_PUDDING)
				.addStacksAfter(of(Items.MILK_BUCKET),
						() -> IceCream.setFlavor(ICE_CREAM, NeapolitanIceCreamFlavors.VANILLA),
						() -> IceCream.setFlavor(ICE_CREAM, NeapolitanIceCreamFlavors.CHOCOLATE),
						() -> IceCream.setFlavor(ICE_CREAM, NeapolitanIceCreamFlavors.STRAWBERRY),
						() -> IceCream.setFlavor(ICE_CREAM, NeapolitanIceCreamFlavors.BANANA),
						() -> IceCream.setFlavor(ICE_CREAM, NeapolitanIceCreamFlavors.MINT),
						() -> IceCream.setFlavor(ICE_CREAM, NeapolitanIceCreamFlavors.ADZUKI),
						() -> new ItemStack(ICE_CREAM.get()),
						() -> IceCream.setFlavor(ICE_CREAM_CONE, NeapolitanIceCreamFlavors.VANILLA),
						() -> IceCream.setFlavor(ICE_CREAM_CONE, NeapolitanIceCreamFlavors.CHOCOLATE),
						() -> IceCream.setFlavor(ICE_CREAM_CONE, NeapolitanIceCreamFlavors.STRAWBERRY),
						() -> IceCream.setFlavor(ICE_CREAM_CONE, NeapolitanIceCreamFlavors.BANANA),
						() -> IceCream.setFlavor(ICE_CREAM_CONE, NeapolitanIceCreamFlavors.MINT),
						() -> IceCream.setFlavor(ICE_CREAM_CONE, NeapolitanIceCreamFlavors.ADZUKI),
						() -> new ItemStack(ICE_CREAM_CONE.get())
				)
				.addItemsAfter(of(Items.MILK_BUCKET), MILK_BOTTLE, VANILLA_MILKSHAKE, CHOCOLATE_MILKSHAKE, STRAWBERRY_MILKSHAKE, BANANA_MILKSHAKE, MINT_MILKSHAKE, ADZUKI_MILKSHAKE)
				.addItemsAfter(of(Items.HONEY_BOTTLE), STRAWBERRY_BANANA_SMOOTHIE)
				.addItemsBefore(of(Items.POTION), ICE_CUBES)
				.tab(COMBAT)
				.addItemsAfter(of(Items.ARROW), BANANARROW)
				.tab(INGREDIENTS)
				.addItemsAfter(of(Items.HONEYCOMB), BANANA_BUNCH)
				.addPotterySherdsAlphabetically(SCREAM_POTTERY_SHERD, REFLECTION_POTTERY_SHERD, SNACK_POTTERY_SHERD, SPIDER_POTTERY_SHERD)
				.addItemsAfter(of(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE), PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE)
				.addItemsAfter(of(Items.GLOBE_BANNER_PATTERN), CHIMPANZEE_BANNER_PATTERN)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsAfter(of(Items.MUSIC_DISC_WAIT), MUSIC_DISC_HULLABALOO)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsAfter(of(Items.CREEPER_HEAD), CHIMPANZEE_HEAD)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Items.SUGAR_CANE), VANILLA_PODS)
				.addItemsAfter(of(Items.COCOA_BEANS), ADZUKI_BEANS)
				.addItemsAfter(of(Items.BEETROOT_SEEDS), STRAWBERRY_PIPS, MINT_SPROUT)
				.tab(SPAWN_EGGS)
				.addSpawnEggsAlphabetically(CHIMPANZEE_SPAWN_EGG, PLANTAIN_SPIDER_SPAWN_EGG);
	}

	public static final class NeapolitanFoods {
		public static final FoodProperties ICE_CUBES = new FoodProperties.Builder().alwaysEdible().build();
		public static final FoodProperties ICE_CREAM = createFood(12, 0.3F).usingConvertsTo(Items.BOWL).build();
		public static final FoodProperties ICE_CREAM_CONE = createFood(6, 0.3F).build();

		public static final FoodProperties CHOCOLATE_BAR = createFood(4, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH, 400, 1), 1.0F).build();
		public static final FoodProperties CHOCOLATE_SPIDER_EYE = createFood(2, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH, 800), 1.0F).effect(() -> new MobEffectInstance(MobEffects.POISON, 80), 1.0F).build();
		public static final FoodProperties CHOCOLATE_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH, 200), 1.0F).build();
		public static final FoodProperties CHOCOLATE_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH, 300, 2)).build();

		public static final FoodProperties STRAWBERRIES = createFood(3, 0.1F).build();
		public static final FoodProperties WHITE_STRAWBERRIES = createFood(5, 0.1F).build();
		public static final FoodProperties STRAWBERRY_SCONES = createFood(5, 0.1F).build();
		public static final FoodProperties STRAWBERRY_CAKE = createFood(1, 0.1F).build();
		public static final FoodProperties STRAWBERRY_MILKSHAKE = createFood(2, 1.2F).alwaysEdible().build();

		public static final FoodProperties DRIED_VANILLA_PODS = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT, 200), 1.0F).build();
		public static final FoodProperties VANILLA_FUDGE = createFood(3, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT, 100), 1.0F).build();
		public static final FoodProperties VANILLA_PUDDING = createFood(6, 1.2F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT, 300), 1.0F).usingConvertsTo(Items.BOWL).build();
		public static final FoodProperties VANILLA_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT, 100), 1.0F).build();
		public static final FoodProperties VANILLA_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT, 200)).build();

		public static final FoodProperties BANANA = createFood(2, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY, 300), 1.0F).build();
		public static final FoodProperties BANANA_BREAD = createFood(5, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY, 600), 1.0F).build();
		public static final FoodProperties DRIED_BANANA = createFood(4, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY, 200), 1.0F).build();
		public static final FoodProperties BANANA_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY, 200), 1.0F).build();
		public static final FoodProperties BANANA_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY, 600)).build();

		public static final FoodProperties MINT_LEAVES = createFood(2, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING, 600), 1.0F).build();
		public static final FoodProperties MINT_CHOPS = createFood(3, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING, 900), 1.0F).build();
		public static final FoodProperties COOKED_MINT_CHOPS = createFood(7, 1.2F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING, 1200), 1.0F).build();
		public static final FoodProperties MINT_CANDIES = createFood(3, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING, 2400), 1.0F).build();
		public static final FoodProperties MINT_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING, 300), 1.0F).build();
		public static final FoodProperties MINT_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING, 800)).build();

		public static final FoodProperties ROASTED_ADZUKI_BEANS = createFood(3, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY, 100), 1.0F).build();
		public static final FoodProperties ADZUKI_BUN = createFood(5, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY, 300), 1.0F).build();
		public static final FoodProperties ADZUKI_STEW = createFood(8, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY, 400), 1.0F).usingConvertsTo(Items.BOWL).build();
		public static final FoodProperties ADZUKI_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY, 200), 1.0F).build();
		public static final FoodProperties ADZUKI_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY, 300)).build();

		public static final FoodProperties CHOCOLATE_STRAWBERRIES = createFood(4, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH, 200), 1.0F).build();
		public static final FoodProperties VANILLA_CHOCOLATE_FINGERS = createFood(6, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH, 200), 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT, 100), 1.0F).build();
		public static final FoodProperties STRAWBERRY_BANANA_SMOOTHIE = createFood(3, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY, 600), 1.0F).build();
		public static final FoodProperties MINT_CHOCOLATE = createFood(6, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH, 200), 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING, 1200), 1.0F).build();
		public static final FoodProperties STRAWBERRY_BEAN_BONBONS = createFood(4, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY, 100), 1.0F).build();
		public static final FoodProperties ADZUKI_CURRY = createFood(6, 1.2F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY, 300), 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY, 300), 1.0F).build();

		public static FoodProperties.Builder createFood(int nutrition, float saturation) {
			return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
		}

		public static FoodProperties.Builder createMilkshake(Supplier<MobEffectInstance> effect) {
			return createFood(2, 1.5F).alwaysEdible().effect(effect, 1.0F);
		}
	}
}
