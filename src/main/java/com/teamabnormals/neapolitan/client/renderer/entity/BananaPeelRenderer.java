package com.teamabnormals.neapolitan.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.neapolitan.client.model.BananaPeelModel;
import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BananaPeelRenderer extends EntityRenderer<BananaPeel> {
	private static final ResourceLocation BANANA_PEEL_LOCATION = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/banana_peel.png");
	private final BananaPeelModel model;

	public BananaPeelRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new BananaPeelModel(context.bakeLayer(NeapolitanModelLayers.BANANA_PEEL));
	}

	@Override
	public void render(BananaPeel entityIn, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStack, bufferIn, packedLightIn);
		matrixStack.pushPose();
		matrixStack.scale(-1.0F, -1.0F, 1.0F);
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
		this.model.renderToBuffer(matrixStack, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(BananaPeel entity) {
		return BANANA_PEEL_LOCATION;
	}
}