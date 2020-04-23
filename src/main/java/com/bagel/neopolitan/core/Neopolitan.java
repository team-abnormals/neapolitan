package com.bagel.neopolitan.core;

import com.bagel.neopolitan.core.registry.NeopolitanBlocks;
import com.bagel.neopolitan.core.registry.NeopolitanData;
import com.bagel.neopolitan.core.registry.NeopolitanItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("neopolitan")
@Mod.EventBusSubscriber(modid = "neopolitan")
public class Neopolitan {
	public static final String MODID = "neopolitan";

    public Neopolitan() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        NeopolitanBlocks.BLOCKS.register(modEventBus);
        NeopolitanItems.ITEMS.register(modEventBus);
        NeopolitanItems.ITEM_OVERRIDES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        
        modEventBus.addListener(this::setupCommon);
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
        	modEventBus.addListener(this::setupClient);
        });
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        NeopolitanData.registerBlockData();
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
    	NeopolitanData.setRenderLayers();
    }
}
