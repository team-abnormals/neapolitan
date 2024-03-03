package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;

public class NeapolitanClientCompat {

	public static void registerClientCompat() {
		registerRenderLayers();
		registerItemProperties();
	}

	private static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.ADZUKI_SPROUTS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BEANSTALK_THORNS.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.SMALL_BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.LARGE_BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_BANANA_FROND.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_SLAB.get(), RenderType.cutout());
	}

	public static void registerItemProperties() {
		ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Neapolitan.MOD_ID, "bananarrow"), (stack, world, entity, i) -> {
			return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, NeapolitanItems.BANANARROW.get()) ? 1.0F : 0.0F;
		});
	}
}
