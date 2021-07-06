package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.minecraftabnormals.neapolitan.client.renderer.layer.ChimpanzeeDirtLayer;
import com.minecraftabnormals.neapolitan.client.renderer.layer.ChimpanzeeItemLayer;
import com.minecraftabnormals.neapolitan.client.renderer.layer.ChimpanzeePaleSkinLayer;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
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
	public static final ResourceLocation[] CHIMPANZEE_TEXTURES = new ResourceLocation[]{new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee.png"), new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/rainforest_chimpanzee.png"), new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/bamboo_chimpanzee.png")};
	public static final ResourceLocation[] CHIMPANZEE_MOUTH_OPEN_TEXTURES = new ResourceLocation[]{new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_mouth_open.png"), new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/rainforest_chimpanzee_mouth_open.png"), new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/bamboo_chimpanzee_mouth_open.png")};

	public ChimpanzeeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ChimpanzeeModel<>(), 0.4F);
		this.addLayer(new ChimpanzeePaleSkinLayer<>(this));
		this.addLayer(new ChimpanzeeDirtLayer<>(this));
		this.addLayer(new HeadLayer<>(this));
		this.addLayer(new ChimpanzeeItemLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ChimpanzeeEntity entity) {
		int type = entity.getChimpanzeeType();
		return entity.isMouthOpen() ? CHIMPANZEE_MOUTH_OPEN_TEXTURES[type] : CHIMPANZEE_TEXTURES[type];
	}

	@Override
	public void render(ChimpanzeeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
}