package com.minecraftabnormals.neapolitan.core;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
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
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		NeapolitanEffects.EFFECTS.register(bus);
		NeapolitanFeatures.FEATURES.register(bus);
		NeapolitanBanners.PAINTINGS.register(bus);
		NeapolitanParticles.PARTICLES.register(bus);

		bus.addListener(this::commonSetup);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::clientSetup);
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NeapolitanConfig.COMMON_SPEC);
		DataUtil.registerConfigCondition(Neapolitan.MOD_ID, NeapolitanConfig.COMMON);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			NeapolitanCompat.transformCookies();
			NeapolitanCompat.registerCompat();
			NeapolitanEntities.registerEntitySpawns();
			NeapolitanFeatures.Configured.registerConfiguredFeatures();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		NeapolitanEntities.registerEntityRenderers();
		event.enqueueWork(() -> {
			NeapolitanCompat.registerRenderLayers();
			NeapolitanItems.registerItemProperties();
		});
	}
}
