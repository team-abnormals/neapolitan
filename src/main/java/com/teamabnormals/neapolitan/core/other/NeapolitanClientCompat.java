package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.client.renderer.item.IceCreamRenderer.IceCreamClientExtension;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.NeapolitanSkullTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;


@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NeapolitanClientCompat {

	public static void register() {
		NeapolitanBlocks.setupTabEditors();
		NeapolitanItems.setupTabEditors();
		registerRenderLayers();
		registerItemProperties();
		SkullBlockRenderer.SKIN_BY_TYPE.put(NeapolitanSkullTypes.CHIMPANZEE, Neapolitan.location("textures/entity/chimpanzee/jungle_chimpanzee.png"));
	}

	private static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.ADZUKI_SPROUTS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.MAGIC_BEANS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BEANSTALK_THORNS.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_BANANA_FROND.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_SLAB.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.KOA_DOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.KOA_TRAPDOOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.KOA_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_KOA_SAPLING.get(), RenderType.cutout());
	}

	public static void registerItemProperties() {
		ItemProperties.register(Items.CROSSBOW, Neapolitan.location("bananarrow"), (stack, world, entity, i) -> {
			ChargedProjectiles projectiles = stack.get(DataComponents.CHARGED_PROJECTILES);
			return projectiles != null && CrossbowItem.isCharged(stack) && projectiles.contains(NeapolitanItems.BANANARROW.get()) ? 1.0F : 0.0F;
		});
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IceCreamClientExtension(), NeapolitanItems.ICE_CREAM);
		event.registerItem(new IceCreamClientExtension(), NeapolitanItems.ICE_CREAM_CONE);
	}
}
