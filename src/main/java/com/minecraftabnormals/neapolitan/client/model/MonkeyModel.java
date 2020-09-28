package com.minecraftabnormals.neapolitan.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MonkeyModel extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer head;
	private final ModelRenderer rightear;
	private final ModelRenderer leftear;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;

	public MonkeyModel() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 17.0F, 2.0F);
		body.setTextureOffset(37, 0).addBox(-5.0F, -12.0F, -3.0F, 10.0F, 8.0F, 3.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -5.0F, 0.0F);
		body.addChild(tail);
		tail.setTextureOffset(53, 19).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -12.0F, -3.0F);
		body.addChild(head);
		head.setTextureOffset(1, 1).addBox(-4.0F, -8.0F, -2.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(30, 11).addBox(-2.0F, -5.0F, -3.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);

		rightear = new ModelRenderer(this);
		rightear.setRotationPoint(-4.0F, -3.0F, 1.0F);
		head.addChild(rightear);
		rightear.setTextureOffset(25, 1).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		leftear = new ModelRenderer(this);
		leftear.setRotationPoint(4.0F, -5.0F, 1.0F);
		head.addChild(leftear);
		leftear.setTextureOffset(25, 1).addBox(0.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, true);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-1.0F, -4.0F, -2.0F);
		body.addChild(rightleg);
		rightleg.setTextureOffset(27, 17).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(1.0F, -4.0F, -2.0F);
		body.addChild(leftleg);
		leftleg.setTextureOffset(40, 17).addBox(0.0F, 0.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, -11.0F, -2.0F);
		body.addChild(leftarm);
		leftarm.setTextureOffset(14, 17).addBox(0.0F, -1.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, -11.0F, -2.0F);
		body.addChild(rightarm);
		rightarm.setTextureOffset(1, 17).addBox(-3.0F, -1.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}