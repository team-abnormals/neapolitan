package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.client.model.BananaPeelModel;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeHeadModel;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.client.renderer.entity.BananaPeelRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.BananarrowRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.ChimpanzeeRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.PlantainSpiderRenderer;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlockEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.NeapolitanSkullTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NeapolitanModelLayers {
	public static final ModelLayerLocation BANANA_PEEL = register("banana_peel");
	public static final ModelLayerLocation CHIMPANZEE = register("chimpanzee");
	public static final ModelLayerLocation CHIMPANZEE_HEAD = register("chimpanzee_head");
	public static final ModelLayerLocation CHIMPANZEE_INNER_ARMOR = register("chimpanzee", "inner_armor");
	public static final ModelLayerLocation CHIMPANZEE_OUTER_ARMOR = register("chimpanzee", "outer_armor");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(Neapolitan.location(name), layer);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(BANANA_PEEL, BananaPeelModel::createBodyLayer);
		event.registerLayerDefinition(CHIMPANZEE, () -> ChimpanzeeModel.createBodyLayer(0.0F, false, false));
		event.registerLayerDefinition(CHIMPANZEE_INNER_ARMOR, () -> ChimpanzeeModel.createBodyLayer(0.5F, true, true));
		event.registerLayerDefinition(CHIMPANZEE_OUTER_ARMOR, () -> ChimpanzeeModel.createBodyLayer(1.0F, true, false));
		event.registerLayerDefinition(CHIMPANZEE_HEAD, ChimpanzeeHeadModel::createHeadLayer);
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(NeapolitanEntityTypes.CHIMPANZEE.get(), ChimpanzeeRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.PLANTAIN_SPIDER.get(), PlantainSpiderRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.BANANA_PEEL.get(), BananaPeelRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.BANANARROW.get(), BananarrowRenderer::new);

		event.registerBlockEntityRenderer(NeapolitanBlockEntityTypes.SKULL.get(), SkullBlockRenderer::new);
	}

	@SubscribeEvent
	public static void createSkullModels(EntityRenderersEvent.CreateSkullModels event) {
		event.registerSkullModel(NeapolitanSkullTypes.CHIMPANZEE, new ChimpanzeeHeadModel(event.getEntityModelSet().bakeLayer(CHIMPANZEE_HEAD)));
	}
}
