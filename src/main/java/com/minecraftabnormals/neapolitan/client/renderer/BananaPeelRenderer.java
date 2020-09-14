package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.client.model.BananaPeelModel;
import com.minecraftabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class BananaPeelRenderer extends EntityRenderer<BananaPeelEntity> {
	private final BananaPeelModel bananaPeel = new BananaPeelModel();

	public BananaPeelRenderer(EntityRendererManager manager) {
		super(manager);
	}

	@Override
	public void render(BananaPeelEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStack, bufferIn, packedLightIn);
		matrixStack.push();
		matrixStack.scale(-1.0F, -1.0F, 1.0F);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.bananaPeel.getRenderType(this.getEntityTexture(entityIn)));
		this.bananaPeel.render(matrixStack, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.pop();
	}

	public ResourceLocation getEntityTexture(BananaPeelEntity entity) {
		return new ResourceLocation(Neapolitan.MODID, "textures/entity/banana_peel.png");
	}
}