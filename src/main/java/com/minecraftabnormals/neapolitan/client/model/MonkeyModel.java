package com.minecraftabnormals.neapolitan.client.model;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.neapolitan.common.entity.MonkeyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class MonkeyModel<E extends MonkeyEntity> extends AgeableModel<E> implements IHasArm, IHasHead {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer rightEar;
	private final ModelRenderer leftEar;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	public final ModelRenderer tail;

	public MonkeyModel() {
		super(false, 10.0F, 0.0F, 2.0F, 2.0F, 24);
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 9.0F, 2.0F);
		body.setTextureOffset(37, 0).addBox(-5.0F, -4.0F, -3.0F, 10.0F, 8.0F, 3.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 3.0F, 0.0F);
		body.addChild(tail);
		tail.setTextureOffset(53, 19).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 5.0F, 0.5F);
		head.setTextureOffset(1, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(30, 11).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);

		rightEar = new ModelRenderer(this);
		rightEar.setRotationPoint(-4.0F, -3.0F, 0.0F);
		head.addChild(rightEar);
		rightEar.setTextureOffset(25, 1).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		leftEar = new ModelRenderer(this);
		leftEar.setRotationPoint(4.0F, -5.0F, 0.0F);
		head.addChild(leftEar);
		leftEar.setTextureOffset(25, 1).addBox(0.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(5.0F, 6.5F, 0.5F);
		leftArm.setTextureOffset(14, 17).addBox(0.0F, -1.5F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-5.0F, 6.5F, 0.5F);
		rightArm.setTextureOffset(1, 17).addBox(-3.0F, -1.5F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(2.5F, 13.0F, 0.5F);
		leftLeg.setTextureOffset(40, 17).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-2.5F, 13.0F, 0.5F);
		rightLeg.setTextureOffset(27, 17).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

		float x = 1.0F;
		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / x;
		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / x;
		this.rightArm.rotateAngleZ = 0.0F;
		this.leftArm.rotateAngleZ = 0.0F;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / x;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / x;
		this.rightLeg.rotateAngleY = 0.0F;
		this.leftLeg.rotateAngleY = 0.0F;
		this.rightLeg.rotateAngleZ = 0.0F;
		this.leftLeg.rotateAngleZ = 0.0F;
		
		this.tail.rotateAngleX = (float) Math.toRadians(30);
	}

	protected HandSide getMainHand(E entityIn) {
		HandSide handside = entityIn.getPrimaryHand();
		return entityIn.swingingHand == Hand.MAIN_HAND ? handside : handside.opposite();
	}

	protected ModelRenderer getArmForSide(HandSide side) {
		return side == HandSide.LEFT ? this.leftArm : this.rightArm;
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(head);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(body, leftArm, rightArm, leftLeg, rightLeg);
	}

	@Override
	public ModelRenderer getModelHead() {
		return head;
	}

	@Override
	public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
		this.getArmForSide(sideIn).translateRotate(matrixStackIn);
	}
}