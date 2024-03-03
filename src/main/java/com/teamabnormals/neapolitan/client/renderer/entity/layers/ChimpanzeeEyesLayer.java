package com.teamabnormals.neapolitan.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.client.BlueprintRenderTypes;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeEyesLayer<T extends Chimpanzee> extends EyesLayer<T, ChimpanzeeModel<T>> {
	private static final RenderType RENDER_TYPE = BlueprintRenderTypes.getUnshadedCutoutEntity(new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_eyes.png"), true);

	public ChimpanzeeEyesLayer(RenderLayerParent<T, ChimpanzeeModel<T>> rendererIn) {
		super(rendererIn);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T chimpanzee, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (chimpanzee.level().getMaxLocalRawBrightness(chimpanzee.blockPosition()) > 5)
			return;
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.renderType());
		this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public RenderType renderType() {
		return RENDER_TYPE;
	}
}