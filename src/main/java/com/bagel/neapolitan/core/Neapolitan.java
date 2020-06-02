package com.bagel.neapolitan.core;

import com.bagel.neapolitan.core.registry.NeapolitanBlocks;
import com.bagel.neapolitan.core.registry.NeapolitanData;
import com.bagel.neapolitan.core.registry.NeapolitanItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("neapolitan")
@Mod.EventBusSubscriber(modid = "neapolitan")
public class Neapolitan {
	public static final String MODID = "neapolitan";

    public Neapolitan() {
    	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        NeapolitanBlocks.BLOCKS.register(modEventBus);
        NeapolitanItems.ITEMS.register(modEventBus);
        NeapolitanItems.ITEM_OVERRIDES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        
        modEventBus.addListener(this::setupCommon);
    	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
        	modEventBus.addListener(this::setupClient);
        });
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        NeapolitanData.registerBlockData();
    }
    
    private void setupClient(final FMLClientSetupEvent event) {
    	NeapolitanData.setRenderLayers();
    }
}
