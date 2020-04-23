package com.bagel.neopolitan.core.registry;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;

public class NeopolitanData {
	public static void registerBlockData() {
//		registerFlammable(NeopolitanBlocks.TEMPLATE_BLOCK.get(), 60, 30);
//		registerStrippable(NeopolitanBlocks.TEMPLATE_BLOCK.get(), NeopolitanBlocks.TEMPLATE_BLOCK.get());
//		registerCompostable(NeopolitanBlocks.TEMPLATE_BLOCK.get(), 0.65F);
	}
	
	public static void setRenderLayers()
	{
		//RenderTypeLookup.setRenderLayer(TemplateBlocks.TEMPLATE_BLOCK.get(),RenderType.getCutout());
	}

	public static void registerFlammable(Block block, int encouragement, int flammability) {
		FireBlock fire = (FireBlock) Blocks.FIRE;
		fire.setFireInfo(block, encouragement, flammability);
	}

	public static void registerCompostable(IItemProvider item, float chance) {
		ComposterBlock.CHANCES.put(item.asItem(), chance);
	}

	public static void registerStrippable(Block log, Block stripped) {
		AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
		AxeItem.BLOCK_STRIPPING_MAP.put(log, stripped);
	}
}
