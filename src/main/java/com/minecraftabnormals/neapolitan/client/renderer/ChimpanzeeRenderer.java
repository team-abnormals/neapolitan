package com.minecraftabnormals.neapolitan.client.renderer;

import java.util.Locale;

import com.minecraftabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.minecraftabnormals.neapolitan.client.renderer.layers.*;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeTypes;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeRenderer extends MobRenderer<ChimpanzeeEntity, ChimpanzeeModel<ChimpanzeeEntity>> {

	public ChimpanzeeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ChimpanzeeModel<>(), 0.4F);
		this.addLayer(new ChimpanzeePaleSkinLayer<>(this));
		this.addLayer(new ChimpanzeeDirtLayer<>(this));
		this.addLayer(new ChimpanzeeDyeLayer<>(this));
		this.addLayer(new HeadLayer<>(this));
		this.addLayer(new ChimpanzeeItemLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(ChimpanzeeEntity entity) {
		ChimpanzeeTypes type = ChimpanzeeTypes.byId(entity.getChimpanzeeType());
		String textureend = entity.isMouthOpen() ? "_chimpanzee_mouth_open.png" : "_chimpanzee.png";
		return new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/" + type.name().toLowerCase(Locale.ROOT) + textureend);
	}

	@Override
	public void render(ChimpanzeeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	protected boolean isShaking(ChimpanzeeEntity entityIn) {
		return entityIn.getApeModeTime() > 0;
	}
}