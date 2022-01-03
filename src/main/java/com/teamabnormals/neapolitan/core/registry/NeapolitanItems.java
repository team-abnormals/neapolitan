package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.item.*;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.neapolitan.common.item.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanItems {
	public static final ItemSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> MILK_BOTTLE = HELPER.createItem("milk_bottle", () -> new MilkBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> ICE_CUBES = HELPER.createItem("ice_cubes", () -> new IceCubesItem(new Item.Properties().food(Foods.ICE_CUBES).tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> CHOCOLATE_BAR = HELPER.createItem("chocolate_bar", () -> new Item(new Item.Properties().food(Foods.CHOCOLATE_BAR).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_SPIDER_EYE = HELPER.createItem("chocolate_spider_eye", () -> new Item(new Item.Properties().food(Foods.CHOCOLATE_SPIDER_EYE).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM = HELPER.createItem("chocolate_ice_cream", () -> new BowlFoodItem(new Item.Properties().food(Foods.CHOCOLATE_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_CAKE = HELPER.createItem("chocolate_cake", () -> new BlockItem(NeapolitanBlocks.CHOCOLATE_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> CHOCOLATE_MILKSHAKE = HELPER.createItem("chocolate_milkshake", () -> new MilkshakeItem(MobEffectCategory.BENEFICIAL, new Item.Properties().food(Foods.MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> STRAWBERRY_PIPS = HELPER.createItem("strawberry_pips", () -> new ItemNameBlockItem(NeapolitanBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> STRAWBERRIES = HELPER.createItem("strawberries", () -> new HealingItem(2.0F, new Item.Properties().food(Foods.STRAWBERRIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> WHITE_STRAWBERRIES = HELPER.createItem("white_strawberries", () -> new HealingItem(4.0F, new Item.Properties().food(Foods.WHITE_STRAWBERRIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_ICE_CREAM = HELPER.createItem("strawberry_ice_cream", () -> new HealingSoupItem(3.0F, new Item.Properties().food(Foods.STRAWBERRY_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_CAKE = HELPER.createItem("strawberry_cake", () -> new BlockItem(NeapolitanBlocks.STRAWBERRY_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> STRAWBERRY_SCONES = HELPER.createItem("strawberry_scones", () -> new HealingItem(1.0F, new Item.Properties().food(Foods.STRAWBERRY_SCONES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_MILKSHAKE = HELPER.createItem("strawberry_milkshake", () -> new MilkshakeItem(MobEffectCategory.HARMFUL, new Item.Properties().food(Foods.MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> VANILLA_PODS = HELPER.createItem("vanilla_pods", () -> new ItemNameBlockItem(NeapolitanBlocks.VANILLA_VINE.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> DRIED_VANILLA_PODS = HELPER.createItem("dried_vanilla_pods", () -> new Item(new Item.Properties().food(Foods.DRIED_VANILLA_PODS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_FUDGE = HELPER.createItem("vanilla_fudge", () -> new Item(new Item.Properties().food(Foods.VANILLA_FUDGE).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_ICE_CREAM = HELPER.createItem("vanilla_ice_cream", () -> new BowlFoodItem(new Item.Properties().food(Foods.VANILLA_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_CAKE = HELPER.createItem("vanilla_cake", () -> new BlockItem(NeapolitanBlocks.VANILLA_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> VANILLA_PUDDING = HELPER.createItem("vanilla_pudding", () -> new BowlFoodItem(new Item.Properties().food(Foods.VANILLA_PUDDING).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_MILKSHAKE = HELPER.createItem("vanilla_milkshake", () -> new MilkshakeItem(MobEffectCategory.NEUTRAL, new Item.Properties().food(Foods.MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> BANANA = HELPER.createItem("banana", () -> new Item(new Item.Properties().food(Foods.BANANA).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_BUNCH = HELPER.createItem("banana_bunch", () -> new BananaBunchItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BANANA_ICE_CREAM = HELPER.createItem("banana_ice_cream", () -> new BowlFoodItem(new Item.Properties().food(Foods.BANANA_ICE_CREAM).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_CAKE = HELPER.createItem("banana_cake", () -> new BlockItem(NeapolitanBlocks.BANANA_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> DRIED_BANANA = HELPER.createItem("dried_banana", () -> new Item(new Item.Properties().food(Foods.DRIED_BANANA).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_MILKSHAKE = HELPER.createItem("banana_milkshake", () -> new MilkshakeItem(null, new Item.Properties().food(Foods.MILKSHAKE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_BREAD = HELPER.createItem("banana_bread", () -> new Item(new Item.Properties().food(Foods.BANANA_BREAD).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANARROW = HELPER.createItem("bananarrow", () -> new BananarrowItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

	public static final RegistryObject<Item> MINT_SPROUT = HELPER.createItem("mint_sprout", () -> new ItemNameBlockItem(NeapolitanBlocks.MINT.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> MINT_LEAVES = HELPER.createItem("mint_leaves", () -> new Item(new Item.Properties().food(Foods.MINT_LEAVES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CANDIES = HELPER.createItem("mint_candies", () -> new Item(new Item.Properties().food(Foods.MINT_CANDIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_ICE_CREAM = HELPER.createItem("mint_ice_cream", () -> new BowlFoodItem(new Item.Properties().food(Foods.MINT_ICE_CREAM).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CAKE = HELPER.createItem("mint_cake", () -> new BlockItem(NeapolitanBlocks.MINT_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> MINT_MILKSHAKE = HELPER.createItem("mint_milkshake", () -> new MilkshakeItem(null, new Item.Properties().food(Foods.MILKSHAKE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CHOPS = HELPER.createItem("mint_chops", () -> new Item(new Item.Properties().food(Foods.MINT_CHOPS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> COOKED_MINT_CHOPS = HELPER.createItem("cooked_mint_chops", () -> new Item(new Item.Properties().food(Foods.COOKED_MINT_CHOPS).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> ADZUKI_BEANS = HELPER.createItem("adzuki_beans", () -> new AdzukiBeansItem(false, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> ROASTED_ADZUKI_BEANS = HELPER.createItem("roasted_adzuki_beans", () -> new Item(new Item.Properties().food(Foods.ROASTED_ADZUKI_BEANS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_BUN = HELPER.createItem("adzuki_bun", () -> new Item(new Item.Properties().food(Foods.ADZUKI_BUN).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_ICE_CREAM = HELPER.createItem("adzuki_ice_cream", () -> new BowlFoodItem(new Item.Properties().food(Foods.ADZUKI_ICE_CREAM).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_STEW = HELPER.createItem("adzuki_stew", () -> new BowlFoodItem(new Item.Properties().food(Foods.ADZUKI_STEW).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_CAKE = HELPER.createItem("adzuki_cake", () -> new BlockItem(NeapolitanBlocks.ADZUKI_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> ADZUKI_MILKSHAKE = HELPER.createItem("adzuki_milkshake", () -> new MilkshakeItem(null, new Item.Properties().food(Foods.MILKSHAKE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MAGIC_BEANS = HELPER.createItem("magic_beans", () -> new AdzukiBeansItem(true, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> CHOCOLATE_STRAWBERRIES = HELPER.createItem("chocolate_strawberries", () -> new HealingItem(1.0F, new Item.Properties().food(Foods.CHOCOLATE_STRAWBERRIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_CHOCOLATE_FINGERS = HELPER.createItem("vanilla_chocolate_fingers", () -> new Item(new Item.Properties().food(Foods.VANILLA_CHOCOLATE_FINGERS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_BANANA_SMOOTHIE = HELPER.createItem("strawberry_banana_smoothie", () -> new HealingDrinkItem(2.0F, new Item.Properties().food(Foods.STRAWBERRY_BANANA_SMOOTHIE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CHOCOLATE = HELPER.createItem("mint_chocolate", () -> new Item(new Item.Properties().food(Foods.MINT_CHOCOLATE).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_BEAN_BONBONS = HELPER.createItem("strawberry_bean_bonbons", () -> new HealingItem(2.0F, new Item.Properties().food(Foods.STRAWBERRY_BEAN_BONBONS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_CURRY = HELPER.createItem("adzuki_curry", () -> new BowlFoodItem(new Item.Properties().food(Foods.ADZUKI_CURRY).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> NEAPOLITAN_ICE_CREAM = HELPER.createItem("neapolitan_ice_cream", () -> new HealingSoupItem(2.0F, new Item.Properties().food(Foods.NEAPOLITAN_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> MUSIC_DISC_HULLABALOO = HELPER.createItem("music_disc_hullabaloo", () -> new BlueprintRecordItem(12, NeapolitanSounds.HULLABALOO, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> CHIMPANZEE_BANNER_PATTERN = HELPER.createItem("chimpanzee_banner_pattern", () -> new BlueprintBannerPatternItem(NeapolitanBanners.CHIMPANZEE, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

	public static final RegistryObject<BlueprintSpawnEggItem> CHIMPANZEE_SPAWN_EGG = HELPER.createSpawnEggItem("chimpanzee", NeapolitanEntityTypes.CHIMPANZEE::get, 0x1F1626, 0xAD8064);
	public static final RegistryObject<BlueprintSpawnEggItem> PLANTAIN_SPIDER_SPAWN_EGG = HELPER.createSpawnEggItem("plantain_spider", NeapolitanEntityTypes.PLANTAIN_SPIDER::get, 0xAD870A, 0x33202A);

	static class Foods {
		public static final FoodProperties ICE_CUBES = (new FoodProperties.Builder()).alwaysEat().build();
		public static final FoodProperties MILKSHAKE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).alwaysEat().build();

		public static final FoodProperties CHOCOLATE_BAR = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.25F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).build();
		public static final FoodProperties CHOCOLATE_SPIDER_EYE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.35F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 800), 1.0F).effect(() -> new MobEffectInstance(MobEffects.POISON, 80), 1.0F).build();
		public static final FoodProperties CHOCOLATE_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 600, 2), 1.0F).build();
		public static final FoodProperties CHOCOLATE_CAKE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).build();

		public static final FoodProperties STRAWBERRIES = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.05F).build();
		public static final FoodProperties WHITE_STRAWBERRIES = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.05F).build();
		public static final FoodProperties STRAWBERRY_SCONES = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.05F).build();
		public static final FoodProperties STRAWBERRY_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).build();
		public static final FoodProperties STRAWBERRY_CAKE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();

		public static final FoodProperties DRIED_VANILLA_PODS = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.15F).effect(() -> new MobEffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 200), 1.0F).build();
		public static final FoodProperties VANILLA_FUDGE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.30F).effect(() -> new MobEffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
		public static final FoodProperties VANILLA_PUDDING = (new FoodProperties.Builder()).nutrition(6).saturationMod(1.2F).effect(() -> new MobEffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 300), 1.0F).build();
		public static final FoodProperties VANILLA_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 400), 1.0F).build();
		public static final FoodProperties VANILLA_CAKE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();

		public static final FoodProperties BANANA = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).effect(() -> new MobEffectInstance(NeapolitanEffects.AGILITY.get(), 300), 1.0F).build();
		public static final FoodProperties BANANA_BREAD = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).effect(() -> new MobEffectInstance(NeapolitanEffects.AGILITY.get(), 600), 1.0F).build();
		public static final FoodProperties DRIED_BANANA = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).effect(() -> new MobEffectInstance(NeapolitanEffects.AGILITY.get(), 200), 1.0F).build();
		public static final FoodProperties BANANA_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.AGILITY.get(), 1200), 1.0F).build();
		public static final FoodProperties BANANA_CAKE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(NeapolitanEffects.AGILITY.get(), 200), 1.0F).build();

		public static final FoodProperties MINT_LEAVES = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).effect(() -> new MobEffectInstance(NeapolitanEffects.BERSERKING.get(), 600), 1.0F).build();
		public static final FoodProperties MINT_CHOPS = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.4F).effect(() -> new MobEffectInstance(NeapolitanEffects.BERSERKING.get(), 900), 1.0F).meat().build();
		public static final FoodProperties COOKED_MINT_CHOPS = (new FoodProperties.Builder()).nutrition(7).saturationMod(1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.BERSERKING.get(), 1200), 1.0F).meat().build();
		public static final FoodProperties MINT_CANDIES = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).effect(() -> new MobEffectInstance(NeapolitanEffects.BERSERKING.get(), 2400), 1.0F).build();
		public static final FoodProperties MINT_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.BERSERKING.get(), 1600), 1.0F).build();
		public static final FoodProperties MINT_CAKE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(NeapolitanEffects.BERSERKING.get(), 300), 1.0F).build();

		public static final FoodProperties ROASTED_ADZUKI_BEANS = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.4F).effect(() -> new MobEffectInstance(NeapolitanEffects.HARMONY.get(), 100), 1.0F).build();
		public static final FoodProperties ADZUKI_BUN = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).effect(() -> new MobEffectInstance(NeapolitanEffects.HARMONY.get(), 300), 1.0F).build();
		public static final FoodProperties ADZUKI_STEW = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).effect(() -> new MobEffectInstance(NeapolitanEffects.HARMONY.get(), 400), 1.0F).build();
		public static final FoodProperties ADZUKI_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.HARMONY.get(), 600), 1.0F).build();
		public static final FoodProperties ADZUKI_CAKE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(NeapolitanEffects.HARMONY.get(), 200), 1.0F).build();

		public static final FoodProperties CHOCOLATE_STRAWBERRIES = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.1F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).build();
		public static final FoodProperties VANILLA_CHOCOLATE_FINGERS = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.55F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
		public static final FoodProperties STRAWBERRY_BANANA_SMOOTHIE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.05F).effect(() -> new MobEffectInstance(NeapolitanEffects.AGILITY.get(), 600), 1.0F).build();
		public static final FoodProperties MINT_CHOCOLATE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.55F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.BERSERKING.get(), 1200), 1.0F).build();
		public static final FoodProperties STRAWBERRY_BEAN_BONBONS = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.1F).effect(() -> new MobEffectInstance(NeapolitanEffects.HARMONY.get(), 100), 1.0F).build();
		public static final FoodProperties ADZUKI_CURRY = (new FoodProperties.Builder()).nutrition(6).saturationMod(1.2F).effect(() -> new MobEffectInstance(NeapolitanEffects.HARMONY.get(), 300), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.AGILITY.get(), 300), 1.0F).build();
		public static final FoodProperties NEAPOLITAN_ICE_CREAM = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).effect(() -> new MobEffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 200), 1.0F).build();
	}

	public static void registerItemProperties() {
		ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Neapolitan.MOD_ID, "bananarrow"), (stack, world, entity, i) -> {
			return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, NeapolitanItems.BANANARROW.get()) ? 1.0F : 0.0F;
		});
	}
}
