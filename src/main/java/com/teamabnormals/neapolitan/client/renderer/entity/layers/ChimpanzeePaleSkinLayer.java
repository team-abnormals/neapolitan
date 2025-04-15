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
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeePaleSkinLayer<E extends Chimpanzee, M extends ChimpanzeeModel<E>> extends RenderLayer<E, M> {
	public static final ResourceLocation PALE_SKIN = Neapolitan.location("textures/entity/chimpanzee/chimpanzee_skin_pale.png");
	public static final ResourceLocation PALE_SKIN_MOUTH_OPEN = Neapolitan.location("textures/entity/chimpanzee/chimpanzee_skin_pale_mouth_open.png");

	public ChimpanzeePaleSkinLayer(RenderLayerParent<E, M> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, E chimpanzee, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = chimpanzee.getVisiblePaleness();
		if (!chimpanzee.isInvisible() && f > 0.0F) {
			ResourceLocation texture = chimpanzee.isMouthOpen() ? PALE_SKIN_MOUTH_OPEN : PALE_SKIN;
			VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(texture));
			int i = FastColor.ARGB32.color(Mth.floor(f * 255.0F), 255, 255, 255);
			this.getParentModel().renderToBuffer(poseStack, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(chimpanzee, 0.0F), i);
		}
	}
}
