package com.minecraftabnormals.neapolitan.core;

import com.minecraftabnormals.neapolitan.common.world.gen.NeapolitanBiomeFeatures;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanCompat;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBanners;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanFeatures;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Neapolitan.MODID)
@Mod.EventBusSubscriber(modid = Neapolitan.MODID)
public class Neapolitan {
	public static final String MODID = "neapolitan";

	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public Neapolitan() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);
		
		REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredSoundRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredEntityRegister().register(modEventBus);
		
		NeapolitanEffects.EFFECTS.register(modEventBus);
		NeapolitanFeatures.FEATURES.register(modEventBus);
		NeapolitanBanners.PAINTINGS.register(modEventBus);
		
		modEventBus.addListener(this::setupCommon);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(this::setupClient);
			modEventBus.addListener(this::registerItemColors);
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NeapolitanConfig.COMMON_SPEC);
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			NeapolitanBanners.registerBanners();
			NeapolitanCompat.transformCookies();
			NeapolitanCompat.registerFlammables();
			NeapolitanCompat.registerCompostables();
			NeapolitanCompat.registerDispenserBehaviors();
			NeapolitanCompat.registerLootInjectors();
			NeapolitanBiomeFeatures.generateFeatures();
			NeapolitanEntities.registerEntityAttributes();
			NeapolitanEntities.registerEntitySpawns();
		});
	}

	private void setupClient(final FMLClientSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			NeapolitanCompat.registerRenderLayers();
			NeapolitanEntities.registerEntityRenderers();
			NeapolitanItems.registerItemProperties();
		});
	}

	@OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		REGISTRY_HELPER.processSpawnEggColors(event);
	}
}
