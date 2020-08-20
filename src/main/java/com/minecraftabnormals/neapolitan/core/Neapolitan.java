package com.minecraftabnormals.neapolitan.core;

import com.minecraftabnormals.neapolitan.common.world.gen.NeapolitanBiomeFeatures;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanCompat;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanFeatures;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Foods;
import net.minecraftforge.api.distmarker.Dist;
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

@SuppressWarnings("deprecation")
@Mod(Neapolitan.MODID)
@Mod.EventBusSubscriber(modid = Neapolitan.MODID)
public class Neapolitan {
	public static final String MODID = "neapolitan";

	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);
	
    public Neapolitan() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
    	REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
    	NeapolitanEffects.EFFECTS.register(modEventBus);
    	NeapolitanFeatures.FEATURES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        
        modEventBus.addListener(this::setupCommon);
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
        	modEventBus.addListener(this::setupClient);
        });
    	
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NeapolitanConfig.COMMON_SPEC);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
    	DeferredWorkQueue.runLater(() -> {
    	    NeapolitanCompat.registerFlammables();
    	    NeapolitanCompat.registerCompostables();
    	    NeapolitanBiomeFeatures.generateFeatures();
    		Foods.COOKIE.fastToEat = true;
        	Foods.COOKIE.saturation = 0.3F;
    	});
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
    	DeferredWorkQueue.runLater(() -> {
    		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.getCutout());
    		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.VANILLA_VINE.get(), RenderType.getCutout());
    		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), RenderType.getCutout());
    	});
    }
}
