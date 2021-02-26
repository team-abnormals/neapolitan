package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.abnormals_core.common.items.AbnormalsBannerPatternItem;
import com.minecraftabnormals.abnormals_core.common.items.AbnormalsSpawnEggItem;
import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.minecraftabnormals.neapolitan.common.item.*;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanItems {
	public static final ItemSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> MILK_BOTTLE = HELPER.createItem("milk_bottle", () -> new MilkBottleItem(new Item.Properties().maxStackSize(16).containerItem(Items.GLASS_BOTTLE).group(ItemGroup.MISC)));
	public static final RegistryObject<Item> ICE_CUBES = HELPER.createItem("ice_cubes", () -> new IceCubesItem(new Item.Properties().food(Foods.ICE_CUBES).group(ItemGroup.MISC)));

	public static final RegistryObject<Item> CHOCOLATE_BAR = HELPER.createItem("chocolate_bar", () -> new Item(new Item.Properties().food(Foods.CHOCOLATE_BAR).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_SPIDER_EYE = HELPER.createItem("chocolate_spider_eye", () -> new Item(new Item.Properties().food(Foods.CHOCOLATE_SPIDER_EYE).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM = HELPER.createItem("chocolate_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.CHOCOLATE_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> CHOCOLATE_CAKE = HELPER.createItem("chocolate_cake", () -> new BlockItem(NeapolitanBlocks.CHOCOLATE_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
	public static final RegistryObject<Item> CHOCOLATE_MILKSHAKE = HELPER.createItem("chocolate_milkshake", () -> new MilkshakeItem(EffectType.BENEFICIAL, new Item.Properties().food(Foods.MILKSHAKE).containerItem(Items.GLASS_BOTTLE).maxStackSize(16).group(ItemGroup.FOOD)));

	public static final RegistryObject<Item> STRAWBERRY_PIPS = HELPER.createItem("strawberry_pips", () -> new BlockNamedItem(NeapolitanBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> STRAWBERRIES = HELPER.createItem("strawberries", () -> new HealingItem(2.0F, new Item.Properties().food(Foods.STRAWBERRIES).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> WHITE_STRAWBERRIES = HELPER.createItem("white_strawberries", () -> new HealingItem(4.0F, new Item.Properties().food(Foods.WHITE_STRAWBERRIES).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_ICE_CREAM = HELPER.createItem("strawberry_ice_cream", () -> new HealingSoupItem(3.0F, new Item.Properties().food(Foods.STRAWBERRY_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_CAKE = HELPER.createItem("strawberry_cake", () -> new BlockItem(NeapolitanBlocks.STRAWBERRY_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
	public static final RegistryObject<Item> STRAWBERRY_SCONES = HELPER.createItem("strawberry_scones", () -> new HealingItem(1.0F, new Item.Properties().food(Foods.STRAWBERRY_SCONES).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_MILKSHAKE = HELPER.createItem("strawberry_milkshake", () -> new MilkshakeItem(EffectType.HARMFUL, new Item.Properties().food(Foods.MILKSHAKE).containerItem(Items.GLASS_BOTTLE).maxStackSize(16).group(ItemGroup.FOOD)));

	public static final RegistryObject<Item> VANILLA_PODS = HELPER.createItem("vanilla_pods", () -> new BlockNamedItem(NeapolitanBlocks.VANILLA_VINE.get(), new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> DRIED_VANILLA_PODS = HELPER.createItem("dried_vanilla_pods", () -> new Item(new Item.Properties().food(Foods.DRIED_VANILLA_PODS).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> VANILLA_FUDGE = HELPER.createItem("vanilla_fudge", () -> new Item(new Item.Properties().food(Foods.VANILLA_FUDGE).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> VANILLA_ICE_CREAM = HELPER.createItem("vanilla_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.VANILLA_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> VANILLA_CAKE = HELPER.createItem("vanilla_cake", () -> new BlockItem(NeapolitanBlocks.VANILLA_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
	public static final RegistryObject<Item> VANILLA_PUDDING = HELPER.createItem("vanilla_pudding", () -> new SoupItem(new Item.Properties().food(Foods.VANILLA_PUDDING).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> VANILLA_MILKSHAKE = HELPER.createItem("vanilla_milkshake", () -> new MilkshakeItem(EffectType.NEUTRAL, new Item.Properties().food(Foods.MILKSHAKE).containerItem(Items.GLASS_BOTTLE).maxStackSize(16).group(ItemGroup.FOOD)));

	public static final RegistryObject<Item> BANANA = HELPER.createItem("banana", () -> new Item(new Item.Properties().food(Foods.BANANA).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> BANANA_BUNCH = HELPER.createItem("banana_bunch", () -> new BananaBunchItem(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BANANA_ICE_CREAM = HELPER.createItem("banana_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.BANANA_ICE_CREAM).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> BANANA_CAKE = HELPER.createItem("banana_cake", () -> new BlockItem(NeapolitanBlocks.BANANA_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
	public static final RegistryObject<Item> DRIED_BANANA = HELPER.createItem("dried_banana", () -> new Item(new Item.Properties().food(Foods.DRIED_BANANA).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> BANANA_MILKSHAKE = HELPER.createItem("banana_milkshake", () -> new MilkshakeItem(null, new Item.Properties().food(Foods.MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> BANANA_BREAD = HELPER.createItem("banana_bread", () -> new Item(new Item.Properties().food(Foods.BANANA_BREAD).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> BANANARROW = HELPER.createItem("bananarrow", () -> new BananarrowItem(new Item.Properties().group(ItemGroup.COMBAT)));

	public static final RegistryObject<Item> MINT_SPROUT = HELPER.createItem("mint_sprout", () -> new BlockNamedItem(NeapolitanBlocks.MINT.get(), new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> MINT_LEAVES = HELPER.createItem("mint_leaves", () -> new Item(new Item.Properties().food(Foods.MINT_LEAVES).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> MINT_CANDIES = HELPER.createItem("mint_candies", () -> new Item(new Item.Properties().food(Foods.MINT_CANDIES).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> MINT_ICE_CREAM = HELPER.createItem("mint_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.MINT_ICE_CREAM).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> MINT_CAKE = HELPER.createItem("mint_cake", () -> new BlockItem(NeapolitanBlocks.MINT_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
	public static final RegistryObject<Item> MINT_MILKSHAKE = HELPER.createItem("mint_milkshake", () -> new MilkshakeItem(null, new Item.Properties().food(Foods.MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> MINT_CHOPS = HELPER.createItem("mint_chops", () -> new Item(new Item.Properties().food(Foods.MINT_CHOPS).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> COOKED_MINT_CHOPS = HELPER.createItem("cooked_mint_chops", () -> new Item(new Item.Properties().food(Foods.COOKED_MINT_CHOPS).group(ItemGroup.FOOD)));

	public static final RegistryObject<Item> ADZUKI_BEANS = HELPER.createItem("adzuki_beans", () -> new AdzukiBeansItem(false, new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> ROASTED_ADZUKI_BEANS = HELPER.createItem("roasted_adzuki_beans", () -> new Item(new Item.Properties().food(Foods.ROASTED_ADZUKI_BEANS).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> ADZUKI_BUN = HELPER.createItem("adzuki_bun", () -> new Item(new Item.Properties().food(Foods.ADZUKI_BUN).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> ADZUKI_ICE_CREAM = HELPER.createItem("adzuki_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.ADZUKI_ICE_CREAM).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> ADZUKI_STEW = HELPER.createItem("adzuki_stew", () -> new SoupItem(new Item.Properties().food(Foods.ADZUKI_STEW).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> ADZUKI_CAKE = HELPER.createItem("adzuki_cake", () -> new BlockItem(NeapolitanBlocks.ADZUKI_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
	public static final RegistryObject<Item> ADZUKI_MILKSHAKE = HELPER.createItem("adzuki_milkshake", () -> new MilkshakeItem(null, new Item.Properties().food(Foods.MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> MAGIC_BEANS = HELPER.createItem("magic_beans", () -> new AdzukiBeansItem(true, new Item.Properties().group(ItemGroup.MISC)));

	public static final RegistryObject<Item> CHOCOLATE_STRAWBERRIES = HELPER.createItem("chocolate_strawberries", () -> new HealingItem(1.0F, new Item.Properties().food(Foods.CHOCOLATE_STRAWBERRIES).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> VANILLA_CHOCOLATE_FINGERS = HELPER.createItem("vanilla_chocolate_fingers", () -> new Item(new Item.Properties().food(Foods.VANILLA_CHOCOLATE_FINGERS).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_BANANA_SMOOTHIE = HELPER.createItem("strawberry_banana_smoothie", () -> new HealingDrinkItem(2.0F, new Item.Properties().food(Foods.STRAWBERRY_BANANA_SMOOTHIE).maxStackSize(16).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> MINT_CHOCOLATE = HELPER.createItem("mint_chocolate", () -> new Item(new Item.Properties().food(Foods.MINT_CHOCOLATE).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> STRAWBERRY_BEAN_BONBONS = HELPER.createItem("strawberry_bean_bonbons", () -> new HealingItem(2.0F, new Item.Properties().food(Foods.STRAWBERRY_BEAN_BONBONS).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> ADZUKI_CURRY = HELPER.createItem("adzuki_curry", () -> new SoupItem(new Item.Properties().food(Foods.ADZUKI_CURRY).maxStackSize(1).group(ItemGroup.FOOD)));
	public static final RegistryObject<Item> NEAPOLITAN_ICE_CREAM = HELPER.createItem("neapolitan_ice_cream", () -> new HealingSoupItem(2.0F, new Item.Properties().food(Foods.NEAPOLITAN_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));

	public static final RegistryObject<Item> CHIMPANZEE_BANNER_PATTERN = HELPER.createItem("chimpanzee_banner_pattern", () -> new AbnormalsBannerPatternItem(NeapolitanBanners.CHIMPANZEE, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));

	public static final RegistryObject<AbnormalsSpawnEggItem> CHIMPANZEE_SPAWN_EGG = HELPER.createSpawnEggItem("chimpanzee", NeapolitanEntities.CHIMPANZEE::get, 0x1F1626, 0xAD8064);
	public static final RegistryObject<AbnormalsSpawnEggItem> PLANTAIN_SPIDER_SPAWN_EGG = HELPER.createSpawnEggItem("plantain_spider", NeapolitanEntities.PLANTAIN_SPIDER::get, 0xAD870A, 0x33202A);

	static class Foods {
		public static final Food ICE_CUBES = (new Food.Builder()).setAlwaysEdible().build();
		public static final Food MILKSHAKE = (new Food.Builder()).hunger(3).saturation(0.6F).setAlwaysEdible().build();

		public static final Food CHOCOLATE_BAR = (new Food.Builder()).hunger(4).saturation(0.25F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).build();
		public static final Food CHOCOLATE_SPIDER_EYE = (new Food.Builder()).hunger(2).saturation(0.35F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 800), 1.0F).effect(() -> new EffectInstance(Effects.POISON, 80), 1.0F).build();
		public static final Food CHOCOLATE_ICE_CREAM = (new Food.Builder()).hunger(6).saturation(0.4F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 600, 2), 1.0F).build();
		public static final Food CHOCOLATE_CAKE = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).build();

		public static final Food STRAWBERRIES = (new Food.Builder()).hunger(3).saturation(0.05F).build();
		public static final Food WHITE_STRAWBERRIES = (new Food.Builder()).hunger(5).saturation(0.05F).build();
		public static final Food STRAWBERRY_SCONES = (new Food.Builder()).hunger(5).saturation(0.05F).build();
		public static final Food STRAWBERRY_ICE_CREAM = (new Food.Builder()).hunger(6).saturation(0.4F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).build();
		public static final Food STRAWBERRY_CAKE = (new Food.Builder()).hunger(1).saturation(0.1F).build();

		public static final Food DRIED_VANILLA_PODS = (new Food.Builder()).hunger(1).saturation(0.15F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 200), 1.0F).build();
		public static final Food VANILLA_FUDGE = (new Food.Builder()).hunger(3).saturation(0.30F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
		public static final Food VANILLA_PUDDING = (new Food.Builder()).hunger(6).saturation(1.2F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 300), 1.0F).build();
		public static final Food VANILLA_ICE_CREAM = (new Food.Builder()).hunger(6).saturation(0.4F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 400), 1.0F).build();
		public static final Food VANILLA_CAKE = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();

		public static final Food BANANA = (new Food.Builder()).hunger(2).saturation(0.2F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 300), 1.0F).build();
		public static final Food BANANA_BREAD = (new Food.Builder()).hunger(5).saturation(0.6F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 600), 1.0F).build();
		public static final Food DRIED_BANANA = (new Food.Builder()).hunger(4).saturation(0.3F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 200), 1.0F).build();
		public static final Food BANANA_ICE_CREAM = (new Food.Builder()).hunger(6).saturation(0.4F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 1200), 1.0F).build();
		public static final Food BANANA_CAKE = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 200), 1.0F).build();

		public static final Food MINT_LEAVES = (new Food.Builder()).hunger(2).saturation(0.2F).effect(() -> new EffectInstance(NeapolitanEffects.BERSERKING.get(), 600), 1.0F).build();
		public static final Food MINT_CHOPS = (new Food.Builder()).hunger(3).saturation(0.4F).effect(() -> new EffectInstance(NeapolitanEffects.BERSERKING.get(), 900), 1.0F).meat().build();
		public static final Food COOKED_MINT_CHOPS = (new Food.Builder()).hunger(7).saturation(1.0F).effect(() -> new EffectInstance(NeapolitanEffects.BERSERKING.get(), 1200), 1.0F).meat().build();
		public static final Food MINT_CANDIES = (new Food.Builder()).hunger(3).saturation(0.5F).effect(() -> new EffectInstance(NeapolitanEffects.BERSERKING.get(), 2400), 1.0F).build();
		public static final Food MINT_ICE_CREAM = (new Food.Builder()).hunger(6).saturation(0.4F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.BERSERKING.get(), 1600), 1.0F).build();
		public static final Food MINT_CAKE = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.BERSERKING.get(), 300), 1.0F).build();

		public static final Food ROASTED_ADZUKI_BEANS = (new Food.Builder()).hunger(3).saturation(0.4F).effect(() -> new EffectInstance(NeapolitanEffects.HARMONY.get(), 200), 1.0F).build();
		public static final Food ADZUKI_BUN = (new Food.Builder()).hunger(5).saturation(0.4F).effect(() -> new EffectInstance(NeapolitanEffects.HARMONY.get(), 600), 1.0F).build();
		public static final Food ADZUKI_STEW = (new Food.Builder()).hunger(8).saturation(0.6F).effect(() -> new EffectInstance(NeapolitanEffects.HARMONY.get(), 900), 1.0F).build();
		public static final Food ADZUKI_ICE_CREAM = (new Food.Builder()).hunger(6).saturation(0.4F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.HARMONY.get(), 1200), 1.0F).build();
		public static final Food ADZUKI_CAKE = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.HARMONY.get(), 200), 1.0F).build();

		public static final Food CHOCOLATE_STRAWBERRIES = (new Food.Builder()).hunger(4).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).build();
		public static final Food VANILLA_CHOCOLATE_FINGERS = (new Food.Builder()).hunger(6).saturation(0.55F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
		public static final Food STRAWBERRY_BANANA_SMOOTHIE = (new Food.Builder()).hunger(3).saturation(0.05F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 600), 1.0F).build();
		public static final Food MINT_CHOCOLATE = (new Food.Builder()).hunger(6).saturation(0.55F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.BERSERKING.get(), 1200), 1.0F).build();
		public static final Food STRAWBERRY_BEAN_BONBONS = (new Food.Builder()).hunger(4).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.HARMONY.get(), 400), 1.0F).build();
		public static final Food ADZUKI_CURRY = (new Food.Builder()).hunger(6).saturation(1.2F).effect(() -> new EffectInstance(NeapolitanEffects.HARMONY.get(), 300), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 300), 1.0F).build();
		public static final Food NEAPOLITAN_ICE_CREAM = (new Food.Builder()).hunger(12).saturation(0.4F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 200), 1.0F).build();
	}

	public static void registerItemProperties() {
		ItemModelsProperties.registerProperty(Items.CROSSBOW, new ResourceLocation(Neapolitan.MOD_ID, "bananarrow"), (stack, world, entity) -> {
			return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.hasChargedProjectile(stack, NeapolitanItems.BANANARROW.get()) ? 1.0F : 0.0F;
		});
	}
}
