package com.bagel.neapolitan.core.registry;

import com.bagel.neapolitan.common.item.EffectMilkBucketItem;
import com.bagel.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanItems {
	public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;
	public static final RegistryHelper OVERRIDES = Neapolitan.OVERRIDE_HELPER;
	
	public static final RegistryObject<Item> CHOCOLATE_MILK_BUCKET	= HELPER.createItem("chocolate_milk_bucket", () -> new EffectMilkBucketItem(EffectType.HARMFUL, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));
	public static final RegistryObject<Item> STRAWBERRY_MILK_BUCKET	= HELPER.createItem("strawberry_milk_bucket", () -> new EffectMilkBucketItem(EffectType.BENEFICIAL, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));
	
	public static final RegistryObject<Item> CHOCOLATE_BAR	 		= HELPER.createItem("chocolate_bar", () -> new Item(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_BAR).group(ItemGroup.FOOD)));
//	public static final RegistryObject<Item> STRAWBERRY 			= HELPER.createItem("strawberry", () -> new Item(new Item.Properties().food(NeapolitanFoods.STRAWBERRY).group(ItemGroup.FOOD)));
//	public static final RegistryObject<Item> CHOCOLATE_STRAWBERRY 	= HELPER.createItem("chocolate_strawberry", () -> new Item(new Item.Properties().food(NeapolitanFoods.CHOCOLATE_STRAWBERRY).group(ItemGroup.FOOD)));	
	public static final RegistryObject<Item> COOKIE 				= OVERRIDES.createItem("cookie", () -> new Item((new Item.Properties()).group(ItemGroup.FOOD).food(NeapolitanFoods.COOKIE)));
}
