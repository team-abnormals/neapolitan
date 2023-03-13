package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintBannerPatternItem;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.neapolitan.common.item.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBannerPatternTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanItems {
	public static final ItemSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> MILK_BOTTLE = HELPER.createItem("milk_bottle", () -> new MilkBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> ICE_CUBES = HELPER.createItem("ice_cubes", () -> new IceCubesItem(new Item.Properties().food(NeapolitanFoods.ICE_CUBES).tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> CHOCOLATE_BAR = HELPER.createItem("chocolate_bar", () -> new Item(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_BAR).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_SPIDER_EYE = HELPER.createItem("chocolate_spider_eye", () -> new Item(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_SPIDER_EYE).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM = HELPER.createItem("chocolate_ice_cream", () -> new IceCreamItem(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_CAKE = HELPER.createItem("chocolate_cake", () -> new BlockItem(NeapolitanBlocks.CHOCOLATE_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> CHOCOLATE_MILKSHAKE = HELPER.createItem("chocolate_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> STRAWBERRY_PIPS = HELPER.createItem("strawberry_pips", () -> new ItemNameBlockItem(NeapolitanBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> STRAWBERRIES = HELPER.createItem("strawberries", () -> new HealingItem(2.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> WHITE_STRAWBERRIES = HELPER.createItem("white_strawberries", () -> new HealingItem(4.0F, new Item.Properties().food(NeapolitanFoods.WHITE_STRAWBERRIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_SCONES = HELPER.createItem("strawberry_scones", () -> new HealingItem(1.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_SCONES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_ICE_CREAM = HELPER.createItem("strawberry_ice_cream", () -> new HealingIceCreamItem(3.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_CAKE = HELPER.createItem("strawberry_cake", () -> new BlockItem(NeapolitanBlocks.STRAWBERRY_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> STRAWBERRY_MILKSHAKE = HELPER.createItem("strawberry_milkshake", () -> new HealingMilkshakeItem(3.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> VANILLA_PODS = HELPER.createItem("vanilla_pods", () -> new ItemNameBlockItem(NeapolitanBlocks.VANILLA_VINE.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> DRIED_VANILLA_PODS = HELPER.createItem("dried_vanilla_pods", () -> new Item(new Item.Properties().food(NeapolitanFoods.DRIED_VANILLA_PODS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_FUDGE = HELPER.createItem("vanilla_fudge", () -> new Item(new Item.Properties().food(NeapolitanFoods.VANILLA_FUDGE).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_PUDDING = HELPER.createItem("vanilla_pudding", () -> new BowlFoodItem(new Item.Properties().food(NeapolitanFoods.VANILLA_PUDDING).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_ICE_CREAM = HELPER.createItem("vanilla_ice_cream", () -> new IceCreamItem(new Item.Properties().food(NeapolitanFoods.VANILLA_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_CAKE = HELPER.createItem("vanilla_cake", () -> new BlockItem(NeapolitanBlocks.VANILLA_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> VANILLA_MILKSHAKE = HELPER.createItem("vanilla_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.VANILLA_MILKSHAKE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> BANANA = HELPER.createItem("banana", () -> new Item(new Item.Properties().food(NeapolitanFoods.BANANA).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_BUNCH = HELPER.createItem("banana_bunch", () -> new BananaBunchItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> DRIED_BANANA = HELPER.createItem("dried_banana", () -> new Item(new Item.Properties().food(NeapolitanFoods.DRIED_BANANA).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_BREAD = HELPER.createItem("banana_bread", () -> new Item(new Item.Properties().food(NeapolitanFoods.BANANA_BREAD).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_ICE_CREAM = HELPER.createItem("banana_ice_cream", () -> new IceCreamItem(new Item.Properties().food(NeapolitanFoods.BANANA_ICE_CREAM).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANA_CAKE = HELPER.createItem("banana_cake", () -> new BlockItem(NeapolitanBlocks.BANANA_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> BANANA_MILKSHAKE = HELPER.createItem("banana_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.BANANA_MILKSHAKE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> BANANARROW = HELPER.createItem("bananarrow", () -> new BananarrowItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

	public static final RegistryObject<Item> MINT_SPROUT = HELPER.createItem("mint_sprout", () -> new ItemNameBlockItem(NeapolitanBlocks.MINT.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> MINT_LEAVES = HELPER.createItem("mint_leaves", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_LEAVES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CANDIES = HELPER.createItem("mint_candies", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_CANDIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CHOPS = HELPER.createItem("mint_chops", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_CHOPS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> COOKED_MINT_CHOPS = HELPER.createItem("cooked_mint_chops", () -> new Item(new Item.Properties().food(NeapolitanFoods.COOKED_MINT_CHOPS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_ICE_CREAM = HELPER.createItem("mint_ice_cream", () -> new IceCreamItem(new Item.Properties().food(NeapolitanFoods.MINT_ICE_CREAM).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CAKE = HELPER.createItem("mint_cake", () -> new BlockItem(NeapolitanBlocks.MINT_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> MINT_MILKSHAKE = HELPER.createItem("mint_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.MINT_MILKSHAKE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> ADZUKI_BEANS = HELPER.createItem("adzuki_beans", () -> new AdzukiBeansItem(false, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> ROASTED_ADZUKI_BEANS = HELPER.createItem("roasted_adzuki_beans", () -> new Item(new Item.Properties().food(NeapolitanFoods.ROASTED_ADZUKI_BEANS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_BUN = HELPER.createItem("adzuki_bun", () -> new Item(new Item.Properties().food(NeapolitanFoods.ADZUKI_BUN).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_STEW = HELPER.createItem("adzuki_stew", () -> new BowlFoodItem(new Item.Properties().food(NeapolitanFoods.ADZUKI_STEW).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_ICE_CREAM = HELPER.createItem("adzuki_ice_cream", () -> new IceCreamItem(new Item.Properties().food(NeapolitanFoods.ADZUKI_ICE_CREAM).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_CAKE = HELPER.createItem("adzuki_cake", () -> new BlockItem(NeapolitanBlocks.ADZUKI_CAKE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(1)));
	public static final RegistryObject<Item> ADZUKI_MILKSHAKE = HELPER.createItem("adzuki_milkshake", () -> new MilkshakeItem(new Item.Properties().food(NeapolitanFoods.ADZUKI_MILKSHAKE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MAGIC_BEANS = HELPER.createItem("magic_beans", () -> new AdzukiBeansItem(true, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> CHOCOLATE_STRAWBERRIES = HELPER.createItem("chocolate_strawberries", () -> new HealingItem(1.0F, new Item.Properties().food(NeapolitanFoods.CHOCOLATE_STRAWBERRIES).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> VANILLA_CHOCOLATE_FINGERS = HELPER.createItem("vanilla_chocolate_fingers", () -> new Item(new Item.Properties().food(NeapolitanFoods.VANILLA_CHOCOLATE_FINGERS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_BANANA_SMOOTHIE = HELPER.createItem("strawberry_banana_smoothie", () -> new HealingDrinkItem(2.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_BANANA_SMOOTHIE).stacksTo(16).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> MINT_CHOCOLATE = HELPER.createItem("mint_chocolate", () -> new Item(new Item.Properties().food(NeapolitanFoods.MINT_CHOCOLATE).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_BEAN_BONBONS = HELPER.createItem("strawberry_bean_bonbons", () -> new HealingItem(2.0F, new Item.Properties().food(NeapolitanFoods.STRAWBERRY_BEAN_BONBONS).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> ADZUKI_CURRY = HELPER.createItem("adzuki_curry", () -> new BowlFoodItem(new Item.Properties().food(NeapolitanFoods.ADZUKI_CURRY).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));
	public static final RegistryObject<Item> NEAPOLITAN_ICE_CREAM = HELPER.createItem("neapolitan_ice_cream", () -> new HealingIceCreamItem(2.0F, new Item.Properties().food(NeapolitanFoods.NEAPOLITAN_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(CreativeModeTab.TAB_FOOD)));

	public static final RegistryObject<Item> MUSIC_DISC_HULLABALOO = HELPER.createItem("music_disc_hullabaloo", () -> new BlueprintRecordItem(12, NeapolitanSoundEvents.HULLABALOO, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE), 132));
	public static final RegistryObject<Item> CHIMPANZEE_BANNER_PATTERN = HELPER.createItem("chimpanzee_banner_pattern", () -> new BlueprintBannerPatternItem(NeapolitanBannerPatternTags.PATTERN_ITEM_CHIMPANZEE, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

	public static final RegistryObject<ForgeSpawnEggItem> CHIMPANZEE_SPAWN_EGG = HELPER.createSpawnEggItem("chimpanzee", NeapolitanEntityTypes.CHIMPANZEE::get, 0x1F1626, 0xAD8064);
	public static final RegistryObject<ForgeSpawnEggItem> PLANTAIN_SPIDER_SPAWN_EGG = HELPER.createSpawnEggItem("plantain_spider", NeapolitanEntityTypes.PLANTAIN_SPIDER::get, 0xAD870A, 0x33202A);

	public static final class NeapolitanFoods {
		public static final FoodProperties ICE_CUBES = new FoodProperties.Builder().alwaysEat().build();

		public static final FoodProperties CHOCOLATE_BAR = createFood(4, 0.25F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).build();
		public static final FoodProperties CHOCOLATE_SPIDER_EYE = createFood(2, 0.35F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 800), 1.0F).effect(() -> new MobEffectInstance(MobEffects.POISON, 80), 1.0F).build();
		public static final FoodProperties CHOCOLATE_ICE_CREAM = createFood(6, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 600, 2), 1.0F).build();
		public static final FoodProperties CHOCOLATE_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 200), 1.0F).build();
		public static final FoodProperties CHOCOLATE_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 300, 2)).build();

		public static final FoodProperties STRAWBERRIES = createFood(3, 0.05F).build();
		public static final FoodProperties WHITE_STRAWBERRIES = createFood(5, 0.05F).build();
		public static final FoodProperties STRAWBERRY_SCONES = createFood(5, 0.05F).build();
		public static final FoodProperties STRAWBERRY_ICE_CREAM = createFood(6, 0.4F).build();
		public static final FoodProperties STRAWBERRY_CAKE = createFood(1, 0.1F).build();
		public static final FoodProperties STRAWBERRY_MILKSHAKE = createFood(2, 1.5F).alwaysEat().build();

		public static final FoodProperties DRIED_VANILLA_PODS = createFood(1, 0.15F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT.get(), 200), 1.0F).build();
		public static final FoodProperties VANILLA_FUDGE = createFood(3, 0.30F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
		public static final FoodProperties VANILLA_PUDDING = createFood(6, 1.2F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT.get(), 300), 1.0F).build();
		public static final FoodProperties VANILLA_ICE_CREAM = createFood(6, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT.get(), 400), 1.0F).build();
		public static final FoodProperties VANILLA_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
		public static final FoodProperties VANILLA_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 200)).build();

		public static final FoodProperties BANANA = createFood(2, 0.2F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 300), 1.0F).build();
		public static final FoodProperties BANANA_BREAD = createFood(5, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 600), 1.0F).build();
		public static final FoodProperties DRIED_BANANA = createFood(4, 0.3F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 200), 1.0F).build();
		public static final FoodProperties BANANA_ICE_CREAM = createFood(6, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 1200), 1.0F).build();
		public static final FoodProperties BANANA_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 200), 1.0F).build();
		public static final FoodProperties BANANA_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 600)).build();

		public static final FoodProperties MINT_LEAVES = createFood(2, 0.2F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 600), 1.0F).build();
		public static final FoodProperties MINT_CHOPS = createFood(3, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 900), 1.0F).meat().build();
		public static final FoodProperties COOKED_MINT_CHOPS = createFood(7, 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 1200), 1.0F).meat().build();
		public static final FoodProperties MINT_CANDIES = createFood(3, 0.5F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 2400), 1.0F).build();
		public static final FoodProperties MINT_ICE_CREAM = createFood(6, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 1600), 1.0F).build();
		public static final FoodProperties MINT_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 300), 1.0F).build();
		public static final FoodProperties MINT_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 800)).build();

		public static final FoodProperties ROASTED_ADZUKI_BEANS = createFood(3, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 100), 1.0F).build();
		public static final FoodProperties ADZUKI_BUN = createFood(5, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 300), 1.0F).build();
		public static final FoodProperties ADZUKI_STEW = createFood(8, 0.6F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 400), 1.0F).build();
		public static final FoodProperties ADZUKI_ICE_CREAM = createFood(6, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 600), 1.0F).build();
		public static final FoodProperties ADZUKI_CAKE = createFood(1, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 200), 1.0F).build();
		public static final FoodProperties ADZUKI_MILKSHAKE = createMilkshake(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 300)).build();

		public static final FoodProperties CHOCOLATE_STRAWBERRIES = createFood(4, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 200), 1.0F).build();
		public static final FoodProperties VANILLA_CHOCOLATE_FINGERS = createFood(6, 0.55F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 200), 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
		public static final FoodProperties STRAWBERRY_BANANA_SMOOTHIE = createFood(3, 0.05F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 600), 1.0F).build();
		public static final FoodProperties MINT_CHOCOLATE = createFood(6, 0.55F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 200), 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.BERSERKING.get(), 1200), 1.0F).build();
		public static final FoodProperties STRAWBERRY_BEAN_BONBONS = createFood(4, 0.1F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 100), 1.0F).build();
		public static final FoodProperties ADZUKI_CURRY = createFood(6, 1.2F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.HARMONY.get(), 300), 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.AGILITY.get(), 300), 1.0F).build();
		public static final FoodProperties NEAPOLITAN_ICE_CREAM = createFood(12, 0.4F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).effect(() -> new MobEffectInstance(NeapolitanMobEffects.VANILLA_SCENT.get(), 200), 1.0F).build();

		public static FoodProperties.Builder createFood(int nutrition, float saturation) {
			return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation);
		}

		public static FoodProperties.Builder createMilkshake(Supplier<MobEffectInstance> effect) {
			return createFood(2, 1.5F).alwaysEat().effect(effect, 1.0F);
		}
	}
}
