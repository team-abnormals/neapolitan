package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.client.model.MonkeyModel;
import com.minecraftabnormals.neapolitan.common.entity.MonkeyEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonkeyRenderer extends BipedRenderer<MonkeyEntity, MonkeyModel<MonkeyEntity>> {
   private static final ResourceLocation MONKEY_TEXTURES = new ResourceLocation(Neapolitan.MODID, "textures/entity/monkey/monkey.png");

   public MonkeyRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new MonkeyModel<>(1.0F), 0.4F);
      this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(1.0F), new BipedModel<>(1.0F)));
   }
   
   @Override
   public ResourceLocation getEntityTexture(MonkeyEntity entity) {
      return MONKEY_TEXTURES;
   }
}