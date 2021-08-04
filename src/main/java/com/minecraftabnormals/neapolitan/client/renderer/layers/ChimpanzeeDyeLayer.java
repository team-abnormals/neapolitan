package com.minecraftabnormals.neapolitan.client.renderer.layers;

import com.minecraftabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeDyeLayer<E extends ChimpanzeeEntity, M extends ChimpanzeeModel<E>> extends LayerRenderer<E, M> {
	public static final ResourceLocation DYED_HAND_LEFT = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_dyed_hand_left.png");
	public static final ResourceLocation DYED_HAND_RIGHT = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_dyed_hand_right.png");

	public ChimpanzeeDyeLayer(IEntityRenderer<E, M> entityRenderer) {
		super(entityRenderer);
	}

	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, E chimpanzee, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!chimpanzee.isInvisible()) {
			for (int i = 0; i < 2; ++i) {
				HandSide handside = i == 0 ? HandSide.LEFT : HandSide.RIGHT;
				if (chimpanzee.getHandDyed(handside)) {
					float[] afloat = chimpanzee.getHandDyeColor(handside).getTextureDiffuseColors();
					IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(handside == HandSide.LEFT ? DYED_HAND_LEFT : DYED_HAND_RIGHT));
					this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getOverlayCoords(chimpanzee, 0.0F), afloat[0], afloat[1], afloat[2], 1.0F);
				}
			}
		}
	}
}
