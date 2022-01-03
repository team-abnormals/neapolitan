package com.teamabnormals.neapolitan.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlantainSpiderRenderer extends SpiderRenderer<PlantainSpiderEntity> {
	private static final ResourceLocation PLANTAIN_SPIDER_TEXTURES = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/spider/plantain_spider.png");

	public PlantainSpiderRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.shadowRadius *= 0.6F;
	}

	@Override
	protected void scale(PlantainSpiderEntity entity, PoseStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	public ResourceLocation getTextureLocation(PlantainSpiderEntity entity) {
		return PLANTAIN_SPIDER_TEXTURES;
	}
}