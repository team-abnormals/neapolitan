package com.teamabnormals.neapolitan.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeDirtLayer<E extends Chimpanzee, M extends ChimpanzeeModel<E>> extends RenderLayer<E, M> {
	public static final ResourceLocation DIRT = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_dirt.png");

	public ChimpanzeeDirtLayer(RenderLayerParent<E, M> entityRenderer) {
		super(entityRenderer);
	}

	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, E chimpanzee, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!chimpanzee.isInvisible() && chimpanzee.isDirty()) {
			VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(DIRT));
			this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(chimpanzee, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
