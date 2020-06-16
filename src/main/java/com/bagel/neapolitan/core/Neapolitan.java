package com.bagel.neapolitan.core;

import com.bagel.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Foods;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
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

        MinecraftForge.EVENT_BUS.register(this);
        
        modEventBus.addListener(this::setupCommon);
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
        	modEventBus.addListener(this::setupClient);
        });
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
    	DeferredWorkQueue.runLater(() -> {
    		Foods.COOKIE.fastToEat = true;
        	Foods.COOKIE.saturation = 0.3F;
    	});
    	
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
    	DeferredWorkQueue.runLater(() -> {
    		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.getCutout());
    	});
    }
}
