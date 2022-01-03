package com.teamabnormals.neapolitan.client.renderer.layers;

import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeePaleSkinLayer<E extends ChimpanzeeEntity, M extends ChimpanzeeModel<E>> extends RenderLayer<E, M> {
	public static final ResourceLocation PALE_SKIN = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_skin_pale.png");
	public static final ResourceLocation PALE_SKIN_MOUTH_OPEN = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_skin_pale_mouth_open.png");

	public ChimpanzeePaleSkinLayer(RenderLayerParent<E, M> entityRenderer) {
		super(entityRenderer);
	}

	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, E chimpanzee, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = chimpanzee.getVisiblePaleness();
		if (!chimpanzee.isInvisible() && f > 0.0F) {
			ResourceLocation resourcelocation = chimpanzee.isMouthOpen() ? PALE_SKIN_MOUTH_OPEN : PALE_SKIN;
			VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(resourcelocation));
			this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(chimpanzee, 0.0F), 1.0F, 1.0F, 1.0F, f);
		}
	}
}
