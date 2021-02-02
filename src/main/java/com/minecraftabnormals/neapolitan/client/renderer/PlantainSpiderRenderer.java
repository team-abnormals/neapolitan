package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlantainSpiderRenderer extends SpiderRenderer<PlantainSpiderEntity> {
   private static final ResourceLocation PLANTAIN_SPIDER_TEXTURES = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/spider/plantain_spider.png");

   public PlantainSpiderRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize *= 0.6F;
   }

   @Override
   protected void preRenderCallback(PlantainSpiderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
      matrixStackIn.scale(0.6F, 0.6F, 0.6F);
   }

   @Override
   public ResourceLocation getEntityTexture(PlantainSpiderEntity entity) {
      return PLANTAIN_SPIDER_TEXTURES;
   }
}