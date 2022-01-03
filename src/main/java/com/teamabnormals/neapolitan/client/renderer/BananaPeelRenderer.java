package com.teamabnormals.neapolitan.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.neapolitan.client.model.BananaPeelModel;
import com.teamabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BananaPeelRenderer extends EntityRenderer<BananaPeelEntity> {
	private final BananaPeelModel bananaPeel = new BananaPeelModel(BananaPeelModel.createLayerDefinition().bakeRoot());

	public BananaPeelRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(BananaPeelEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStack, bufferIn, packedLightIn);
		matrixStack.pushPose();
		matrixStack.scale(-1.0F, -1.0F, 1.0F);
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.bananaPeel.renderType(this.getTextureLocation(entityIn)));
		this.bananaPeel.renderToBuffer(matrixStack, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.popPose();
	}

	public ResourceLocation getTextureLocation(BananaPeelEntity entity) {
		return new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/banana_peel.png");
	}
}