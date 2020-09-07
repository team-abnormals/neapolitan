package com.minecraftabnormals.neapolitan.core.registry;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import com.minecraftabnormals.neapolitan.common.item.BannerRecipe;
import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IRegistryDelegate;

public final class NeapolitanBanners {
	public static final DeferredRegister<IRecipeSerializer<?>> BANNERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Neapolitan.MODID);
	public static final RegistryObject<IRecipeSerializer<?>> APPLY = BANNERS.register("banner_pattern_apply", () -> BannerRecipe.SERIALIZER);

	public static final Map<IRegistryDelegate<Item>, BannerPattern> PATTERNS = new LinkedHashMap<>();
	public static final BannerPattern MONKEY = createPattern("monkey", "mky");
	
	public static void registerBanners() {
		addPattern(MONKEY, NeapolitanItems.MONKEY_BANNNER_PATTERN.get());
	}
	
	private static BannerPattern createPattern(String name, String id) {
		name = "mca_" + name;
		id = "mca_" + id;
		return BannerPattern.create(name.toUpperCase(Locale.ROOT), name, id, false);
	}
	
	private static void addPattern(BannerPattern pattern, IItemProvider craftingItem) {
		PATTERNS.put(craftingItem.asItem().delegate, pattern);
	}
}
