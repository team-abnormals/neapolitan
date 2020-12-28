package com.minecraftabnormals.neapolitan.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.neapolitan.common.world.gen.NeapolitanBiomeFeatures;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanCompat;
import com.minecraftabnormals.neapolitan.core.registry.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Neapolitan.MOD_ID)
@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class Neapolitan {
	public static final String MOD_ID = "neapolitan";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public Neapolitan() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(modEventBus);
		NeapolitanEffects.EFFECTS.register(modEventBus);
		NeapolitanFeatures.FEATURES.register(modEventBus);
		NeapolitanBanners.PAINTINGS.register(modEventBus);

		modEventBus.addListener(this::setupCommon);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(this::setupClient);
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NeapolitanConfig.COMMON_SPEC);
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			NeapolitanBanners.registerBanners();
			NeapolitanCompat.transformCookies();
			NeapolitanCompat.registerCompat();
			NeapolitanEntities.registerEntityAttributes();
			NeapolitanEntities.registerEntitySpawns();
			NeapolitanBiomeFeatures.Configs.registerConfiguredFeatures();
		});
	}

	private void setupClient(final FMLClientSetupEvent event) {
		NeapolitanEntities.registerEntityRenderers();
		event.enqueueWork(() -> {
			NeapolitanCompat.registerRenderLayers();
			NeapolitanItems.registerItemProperties();
		});
	}
}
