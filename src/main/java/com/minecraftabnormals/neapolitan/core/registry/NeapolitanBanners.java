package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.abnormals_core.core.api.banner.BannerManager;
import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.entity.item.PaintingType;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class NeapolitanBanners {
	
    public static final DeferredRegister<PaintingType> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Neapolitan.MOD_ID);
    
	public static final RegistryObject<PaintingType> SCREAM	= PAINTINGS.register("scream", () -> new PaintingType(32, 32));
	public static final RegistryObject<PaintingType> CONE	= PAINTINGS.register("cone", () -> new PaintingType(16, 32));

	public static final BannerPattern MONKEY = BannerManager.createPattern("mca", "monkey", "mky");
	
	public static void registerBanners() {
		BannerManager.addPattern(MONKEY, NeapolitanItems.MONKEY_BANNNER_PATTERN.get());
	}
}
