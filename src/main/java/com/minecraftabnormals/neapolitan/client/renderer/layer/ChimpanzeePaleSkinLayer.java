package com.minecraftabnormals.neapolitan.client.renderer.layer;

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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeePaleSkinLayer<E extends ChimpanzeeEntity, M extends ChimpanzeeModel<E>> extends LayerRenderer<E, M> {
	public static final ResourceLocation PALE_SKIN = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_skin_pale.png");
	public static final ResourceLocation PALE_SKIN_MOUTH_OPEN = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_skin_pale_mouth_open.png");

	public ChimpanzeePaleSkinLayer(IEntityRenderer<E, M> entityRenderer) {
		super(entityRenderer);
	}

	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, E chimpanzee, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = chimpanzee.getVisiblePaleness();
		if (!chimpanzee.isInvisible() && f > 0.0F) {
			ResourceLocation resourcelocation = chimpanzee.isMouthOpen() ? PALE_SKIN_MOUTH_OPEN : PALE_SKIN;
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityTranslucent(resourcelocation));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getPackedOverlay(chimpanzee, 0.0F), 1.0F, 1.0F, 1.0F, f);
		}
	}
}
