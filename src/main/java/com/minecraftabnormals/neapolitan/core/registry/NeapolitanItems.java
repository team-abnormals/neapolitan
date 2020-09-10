package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.common.item.BananaBunchItem;
import com.minecraftabnormals.neapolitan.common.item.BananarrowItem;
import com.minecraftabnormals.neapolitan.common.item.HealingDrinkItem;
import com.minecraftabnormals.neapolitan.common.item.HealingItem;
import com.minecraftabnormals.neapolitan.common.item.HealingSoupItem;
import com.minecraftabnormals.neapolitan.common.item.MilkBottleItem;
import com.minecraftabnormals.neapolitan.common.item.MilkshakeItem;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SoupItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanItems {
    public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;

    public static final RegistryObject<Item> MILK_BOTTLE    = HELPER.createItem("milk_bottle", () -> new MilkBottleItem(new Item.Properties().maxStackSize(16).containerItem(Items.GLASS_BOTTLE).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> ICE_CUBES		= HELPER.createItem("ice_cubes", () -> new Item(new Item.Properties().food(Foods.ICE_CUBES).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> CHOCOLATE_BAR          = HELPER.createItem("chocolate_bar", () -> new Item(new Item.Properties().food(Foods.CHOCOLATE_BAR).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM    = HELPER.createItem("chocolate_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.CHOCOLATE_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> CHOCOLATE_CAKE         = HELPER.createItem("chocolate_cake", () -> new BlockItem(NeapolitanBlocks.CHOCOLATE_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
    public static final RegistryObject<Item> CHOCOLATE_MILKSHAKE    = HELPER.createItem("chocolate_milkshake", () -> new MilkshakeItem(EffectType.HARMFUL, new Item.Properties().food(Foods.CHOCOLATE_MILKSHAKE).containerItem(Items.GLASS_BOTTLE).maxStackSize(16).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> STRAWBERRY_PIPS        = HELPER.createItem("strawberry_pips", () -> new BlockNamedItem(NeapolitanBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> STRAWBERRIES           = HELPER.createItem("strawberries", () -> new HealingItem(2.0F, new Item.Properties().food(Foods.STRAWBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_ICE_CREAM   = HELPER.createItem("strawberry_ice_cream", () -> new HealingSoupItem(3.0F, new Item.Properties().food(Foods.STRAWBERRY_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_CAKE        = HELPER.createItem("strawberry_cake", () -> new BlockItem(NeapolitanBlocks.STRAWBERRY_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
    public static final RegistryObject<Item> STRAWBERRY_SCONES      = HELPER.createItem("strawberry_scones", () -> new HealingItem(1.0F, new Item.Properties().food(Foods.STRAWBERRY_SCONES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_MILKSHAKE   = HELPER.createItem("strawberry_milkshake", () -> new MilkshakeItem(EffectType.BENEFICIAL, new Item.Properties().food(Foods.STRAWBERRY_MILKSHAKE).containerItem(Items.GLASS_BOTTLE).maxStackSize(16).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> VANILLA_PODS           = HELPER.createItem("vanilla_pods", () -> new BlockNamedItem(NeapolitanBlocks.VANILLA_VINE.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> DRIED_VANILLA_PODS     = HELPER.createItem("dried_vanilla_pods", () -> new Item(new Item.Properties().food(Foods.DRIED_VANILLA_PODS).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_ICE_CREAM      = HELPER.createItem("vanilla_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.VANILLA_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_CAKE           = HELPER.createItem("vanilla_cake", () -> new BlockItem(NeapolitanBlocks.VANILLA_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
    public static final RegistryObject<Item> VANILLA_PUDDING        = HELPER.createItem("vanilla_pudding", () -> new SoupItem(new Item.Properties().food(Foods.VANILLA_PUDDING).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_MILKSHAKE      = HELPER.createItem("vanilla_milkshake", () -> new MilkshakeItem(EffectType.NEUTRAL, new Item.Properties().food(Foods.VANILLA_MILKSHAKE).containerItem(Items.GLASS_BOTTLE).maxStackSize(16).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> BANANA				= HELPER.createItem("banana", () -> new Item(new Item.Properties().food(Foods.BANANA).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BANANA_BUNCH		= HELPER.createItem("banana_bunch", () -> new BananaBunchItem(new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> BANANA_ICE_CREAM	= HELPER.createItem("banana_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.BANANA_ICE_CREAM).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BANANA_CAKE		= HELPER.createItem("banana_cake", () -> new BlockItem(NeapolitanBlocks.BANANA_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
    public static final RegistryObject<Item> DRIED_BANANA		= HELPER.createItem("dried_banana", () -> new Item(new Item.Properties().food(Foods.DRIED_BANANA).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BANANA_MILKSHAKE	= HELPER.createItem("banana_milkshake", () -> new MilkshakeItem(null, new Item.Properties().food(Foods.BANANA_MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BANANA_BREAD		= HELPER.createItem("banana_bread", () -> new Item(new Item.Properties().food(Foods.BANANA_BREAD).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BANANARROW			= HELPER.createItem("bananarrow", () -> new BananarrowItem(new Item.Properties().group(ItemGroup.COMBAT)));

    public static final RegistryObject<Item> CHOCOLATE_STRAWBERRIES     = HELPER.createItem("chocolate_strawberries", () -> new HealingItem(1.0F, new Item.Properties().food(Foods.CHOCOLATE_STRAWBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> WHITE_STRAWBERRIES         = HELPER.createItem("white_strawberries", () -> new HealingItem(4.0F, new Item.Properties().food(Foods.WHITE_STRAWBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_CHOCOLATE_FINGERS  = HELPER.createItem("vanilla_chocolate_fingers", () -> new Item(new Item.Properties().food(Foods.VANILLA_CHOCOLATE_FINGERS).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_BANANA_SMOOTHIE = HELPER.createItem("strawberry_banana_smoothie", () -> new HealingDrinkItem(2.0F, new Item.Properties().food(Foods.STRAWBERRY_BANANA_SMOOTHIE).maxStackSize(16).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> NEAPOLITAN_ICE_CREAM       = HELPER.createItem("neapolitan_ice_cream", () -> new HealingSoupItem(2.0F, new Item.Properties().food(Foods.NEAPOLITAN_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    
    public static final RegistryObject<Item> MONKEY_BANNNER_PATTERN		= HELPER.createItem("monkey_banner_pattern", () -> new BannerPatternItem(NeapolitanBanners.MONKEY, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));

    static class Foods {
        public static final Food ICE_CUBES	= (new Food.Builder()).setAlwaysEdible().build();

        public static final Food CHOCOLATE_BAR          = (new Food.Builder()).hunger(4).saturation(0.25F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).build();
        public static final Food CHOCOLATE_ICE_CREAM    = (new Food.Builder()).hunger(6).saturation(0.42F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 600, 2), 1.0F).build();
        public static final Food CHOCOLATE_CAKE         = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).build();
        public static final Food CHOCOLATE_MILKSHAKE    = (new Food.Builder()).hunger(3).saturation(0.6F).setAlwaysEdible().build();
        
        public static final Food STRAWBERRIES           = (new Food.Builder()).hunger(3).saturation(0.03F).build();
        public static final Food STRAWBERRY_ICE_CREAM   = (new Food.Builder()).hunger(6).saturation(0.42F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).build();
        public static final Food STRAWBERRY_SCONES      = (new Food.Builder()).hunger(5).saturation(0.03F).build();
        public static final Food STRAWBERRY_CAKE        = (new Food.Builder()).hunger(1).saturation(0.1F).build();
        public static final Food STRAWBERRY_MILKSHAKE   = (new Food.Builder()).hunger(3).saturation(0.6F).setAlwaysEdible().build();
        
        public static final Food DRIED_VANILLA_PODS    	= (new Food.Builder()).hunger(1).saturation(0.15F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 200), 1.0F).build();
        public static final Food VANILLA_ICE_CREAM     	= (new Food.Builder()).hunger(6).saturation(0.42F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 400), 1.0F).build();
        public static final Food VANILLA_PUDDING        = (new Food.Builder()).hunger(6).saturation(1.2F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 300), 1.0F).build();
        public static final Food VANILLA_CAKE           = (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
        public static final Food VANILLA_MILKSHAKE      = (new Food.Builder()).hunger(3).saturation(0.6F).setAlwaysEdible().build();
        
        public static final Food BANANA				= (new Food.Builder()).hunger(2).saturation(0.2F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 300), 1.0F).build();
        public static final Food BANANA_ICE_CREAM	= (new Food.Builder()).hunger(6).saturation(0.42F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 1200), 1.0F).build();
        public static final Food BANANA_BREAD		= (new Food.Builder()).hunger(5).saturation(0.6F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 600), 1.0F).build();
        public static final Food DRIED_BANANA		= (new Food.Builder()).hunger(4).saturation(0.3F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 300), 1.0F).build();
        public static final Food BANANA_MILKSHAKE	= (new Food.Builder()).hunger(3).saturation(0.6F).build();
        public static final Food BANANA_CAKE		= (new Food.Builder()).hunger(1).saturation(0.1F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 300), 1.0F).build();

        public static final Food CHOCOLATE_STRAWBERRIES     = (new Food.Builder()).hunger(4).saturation(0.025F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).build();
        public static final Food WHITE_STRAWBERRIES         = (new Food.Builder()).hunger(5).saturation(0.04F).build();
        public static final Food VANILLA_CHOCOLATE_FINGERS  = (new Food.Builder()).hunger(6).saturation(0.55F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 200), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 100), 1.0F).build();
        public static final Food STRAWBERRY_BANANA_SMOOTHIE = (new Food.Builder()).hunger(3).saturation(0.04F).effect(() -> new EffectInstance(NeapolitanEffects.AGILITY.get(), 600), 1.0F).build();
        public static final Food NEAPOLITAN_ICE_CREAM       = (new Food.Builder()).hunger(12).saturation(0.42F).effect(() -> new EffectInstance(Effects.SLOWNESS, 100, 2), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.SUGAR_RUSH.get(), 400, 1), 1.0F).effect(() -> new EffectInstance(NeapolitanEffects.VANILLA_SCENT.get(), 200), 1.0F).build();
    }
}
