package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.common.item.DrinkItem;
import com.minecraftabnormals.neapolitan.common.item.MilkshakeItem;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SoupItem;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanItems {
    public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;

    public static final RegistryObject<Item> CHOCOLATE_BAR          = HELPER.createItem("chocolate_bar", () -> new Item(new Item.Properties().food(Foods.CHOCOLATE_BAR).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> CHOCOLATE_ICE_CREAM    = HELPER.createItem("chocolate_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.CHOCOLATE_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> CHOCOLATE_CAKE         = HELPER.createItem("chocolate_cake", () -> new BlockItem(NeapolitanBlocks.CHOCOLATE_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
    public static final RegistryObject<Item> CHOCOLATE_MILKSHAKE    = HELPER.createItem("chocolate_milkshake", () -> new MilkshakeItem(EffectType.HARMFUL, new Item.Properties().food(Foods.CHOCOLATE_MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> STRAWBERRY_PIPS        = HELPER.createItem("strawberry_pips", () -> new BlockNamedItem(NeapolitanBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> STRAWBERRIES           = HELPER.createItem("strawberries", () -> new Item(new Item.Properties().food(Foods.STRAWBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_ICE_CREAM   = HELPER.createItem("strawberry_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.STRAWBERRY_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_CAKE        = HELPER.createItem("strawberry_cake", () -> new BlockItem(NeapolitanBlocks.STRAWBERRY_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
    public static final RegistryObject<Item> STRAWBERRY_SCONES      = HELPER.createItem("strawberry_scones", () -> new Item(new Item.Properties().food(Foods.STRAWBERRY_SCONES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_SMOOTHIE    = HELPER.createItem("strawberry_smoothie", () -> new DrinkItem(new Item.Properties().food(Foods.STRAWBERRY_SMOOTHIE).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> STRAWBERRY_MILKSHAKE   = HELPER.createItem("strawberry_milkshake", () -> new MilkshakeItem(EffectType.BENEFICIAL, new Item.Properties().food(Foods.STRAWBERRY_MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> VANILLA_BEANS          = HELPER.createItem("vanilla_beans", () -> new BlockNamedItem(NeapolitanBlocks.VANILLA_VINE.get(), new Item.Properties().food(Foods.VANILLA_BEANS).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> DRIED_VANILLA_BEANS    = HELPER.createItem("dried_vanilla_beans", () -> new Item(new Item.Properties().food(Foods.DRIED_VANILLA_BEANS).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_ICE_CREAM      = HELPER.createItem("vanilla_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.VANILLA_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_CAKE           = HELPER.createItem("vanilla_cake", () -> new BlockItem(NeapolitanBlocks.VANILLA_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));
    public static final RegistryObject<Item> VANILLA_PUDDING        = HELPER.createItem("vanilla_pudding", () -> new SoupItem(new Item.Properties().food(Foods.VANILLA_PUDDING).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_MILKSHAKE      = HELPER.createItem("vanilla_milkshake", () -> new MilkshakeItem(EffectType.NEUTRAL, new Item.Properties().food(Foods.VANILLA_MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> CHOCOLATE_STRAWBERRIES     = HELPER.createItem("chocolate_strawberries", () -> new Item(new Item.Properties().food(Foods.CHOCOLATE_STRAWBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> WHITE_STRAWBERRIES         = HELPER.createItem("white_strawberries", () -> new Item(new Item.Properties().food(Foods.WHITE_STRAWBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> VANILLA_CHOCOLATE_FINGERS  = HELPER.createItem("vanilla_chocolate_fingers", () -> new Item(new Item.Properties().food(Foods.VANILLA_CHOCOLATE_FINGERS).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> NEAPOLITAN_ICE_CREAM       = HELPER.createItem("neapolitan_ice_cream", () -> new SoupItem(new Item.Properties().food(Foods.NEAPOLITAN_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));

    static class Foods {
        public static final Food CHOCOLATE_BAR          = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food CHOCOLATE_ICE_CREAM    = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food CHOCOLATE_CAKE         = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food CHOCOLATE_MILKSHAKE    = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        
        public static final Food STRAWBERRIES           = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food STRAWBERRY_ICE_CREAM   = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food STRAWBERRY_CAKE        = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food STRAWBERRY_SCONES      = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food STRAWBERRY_SMOOTHIE    = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food STRAWBERRY_MILKSHAKE   = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        
        public static final Food VANILLA_BEANS          = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food DRIED_VANILLA_BEANS    = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food VANILLA_ICE_CREAM      = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food VANILLA_CAKE           = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food VANILLA_PUDDING        = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food VANILLA_MILKSHAKE      = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        
        public static final Food CHOCOLATE_STRAWBERRIES     = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food WHITE_STRAWBERRIES         = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food VANILLA_CHOCOLATE_FINGERS  = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        public static final Food NEAPOLITAN_ICE_CREAM       = (new Food.Builder()).hunger(2).saturation(0.1F).build();
    }
}
