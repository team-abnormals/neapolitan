package com.minecraftabnormals.neapolitan.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BananaPeelModel extends EntityModel<Entity> {
	private final ModelRenderer bananaPeel;

	public BananaPeelModel() {
		textureWidth = 16;
		textureHeight = 16;

		bananaPeel = new ModelRenderer(this);
		bananaPeel.setRotationPoint(0.0F, -0.1F, 0.0F);
		bananaPeel.setTextureOffset(0, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		bananaPeel.setTextureOffset(0, 12).addBox(1.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);
		bananaPeel.setTextureOffset(0, 14).addBox(-5.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);
		bananaPeel.setTextureOffset(0, 7).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 0.0F, 4.0F, 0.0F, false);
		bananaPeel.setTextureOffset(4, 7).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 0.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bananaPeel.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}