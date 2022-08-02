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
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeDyeLayer<E extends Chimpanzee, M extends ChimpanzeeModel<E>> extends RenderLayer<E, M> {
	public static final ResourceLocation DYED_HAND_LEFT = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_dyed_hand_left.png");
	public static final ResourceLocation DYED_HAND_RIGHT = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_dyed_hand_right.png");

	public ChimpanzeeDyeLayer(RenderLayerParent<E, M> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, E chimpanzee, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!chimpanzee.isInvisible()) {
			for (int i = 0; i < 2; ++i) {
				HumanoidArm handside = i == 0 ? HumanoidArm.LEFT : HumanoidArm.RIGHT;
				if (chimpanzee.getHandDyed(handside)) {
					float[] afloat = chimpanzee.getHandDyeColor(handside).getTextureDiffuseColors();
					VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(handside == HumanoidArm.LEFT ? DYED_HAND_LEFT : DYED_HAND_RIGHT));
					this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(chimpanzee, 0.0F), afloat[0], afloat[1], afloat[2], 1.0F);
				}
			}
		}
	}
}
