package com.minecraftabnormals.neapolitan.client.model;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class ChimpanzeeModel<T extends ChimpanzeeEntity> extends AgeableModel<T> implements IHasArm, IHasHead {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer rightEar;
	private final ModelRenderer leftEar;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public ChimpanzeeModel() {
		super(false, 10.0F, 0.0F, 2.0F, 2.0F, 24);
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 9.0F, 2.0F);
		body.setTextureOffset(37, 0).addBox(-5.0F, -4.0F, -3.0F, 10.0F, 8.0F, 3.0F, 0.0F, false);

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
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		float partialtick = ageInTicks - (float)entity.ticksExisted;
		float climbscale = !this.isSitting ? entity.getClimbingAnimationScale(partialtick) : 0.0F;
		float climbanim = -climbscale * (float) Math.PI / 2F;
		int attackanim = entity.getAttackTimer();

		if (entity.getAction() != ChimpanzeeEntity.Action.EATING) {
			this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
			this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		}

		this.head.rotationPointX = 0.0F;
		this.head.rotationPointZ = 0.5F;
		this.rightArm.rotateAngleX = 0.0F;
		this.leftArm.rotateAngleX = 0.0F;
		this.rightArm.rotationPointX = -5.0F;
		this.leftArm.rotationPointX = 5.0F;
		this.rightArm.rotateAngleZ = 0.0F;
		this.leftArm.rotateAngleZ = 0.0F;
		this.rightArm.rotationPointZ = 0.5F;
		this.leftArm.rotationPointZ = 0.5F;
		this.rightArm.rotateAngleY = 0.0F;
		this.leftArm.rotateAngleY = 0.0F;
		this.body.rotationPointX = 0.0F;
		this.body.rotationPointZ = 2.0F;

		if (attackanim > 0) {
			this.rightArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) attackanim - partialtick, 10.0F);
			this.leftArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) attackanim - partialtick, 10.0F);
		} else {
			float swing1 = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / 1.0F;
			float swing2 = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / 1.0F;

			if (entity.getAction() == ChimpanzeeEntity.Action.EATING) {
				float eatanim = MathHelper.sin(ageInTicks * 0.7F);

				this.head.rotateAngleY = 0.0F;
				this.head.rotateAngleX = -eatanim * 0.2F;

				this.rightArm.rotateAngleX = eatanim * 0.4F - 1.7F;
				this.leftArm.rotateAngleX = eatanim * 0.4F - 1.7F;
				this.rightArm.rotateAngleY = -0.6F;
				this.leftArm.rotateAngleY = 0.6F;
			} else {
				if (entity.isPartying()) {
					this.rightArm.rotateAngleX = (1.0F - climbscale) * (float) -Math.PI;
					this.leftArm.rotateAngleX = (1.0F - climbscale) * (float) -Math.PI;
				} else {
					this.rightArm.rotateAngleX = swing1;
					this.leftArm.rotateAngleX = swing2;

					HandSide mainhand = entity.getPrimaryHand();

					if (!entity.getHeldItemMainhand().isEmpty()) {
						this.getArmForSide(mainhand).rotateAngleX -= (float)Math.PI / 10F;
					}
					if (!entity.getHeldItemOffhand().isEmpty()) {
						this.getArmForSide(mainhand.opposite()).rotateAngleX -= (float)Math.PI / 10F;
					}
				}

				if (!(this.swingProgress <= 0.0F)) {
					HandSide handside = this.getMainHand(entity);
					ModelRenderer modelrenderer = this.getArmForSide(handside);
					modelrenderer.rotateAngleX = MathHelper.sin(this.swingProgress * (float)Math.PI) * 2.0F;
				}
			}

			this.rightArm.rotateAngleX += climbanim * 1.4F;
			this.leftArm.rotateAngleX += climbanim * 1.4F;
			this.rightArm.rotateAngleY += -climbanim * 0.4F;
			this.leftArm.rotateAngleY += climbanim * 0.4F;
		}

		if (entity.isPartying()) {
			float partyx = MathHelper.cos(ageInTicks * 0.75F) * 0.5F;
			float partyz = MathHelper.sin(ageInTicks * 0.75F) * 0.5F;

			this.head.rotationPointX += partyx;
			this.body.rotationPointX += partyx;
			this.rightArm.rotationPointX += partyx;
			this.leftArm.rotationPointX += partyx;
			this.head.rotationPointZ += partyz;
			this.body.rotationPointZ += partyz;
			this.rightArm.rotationPointZ += partyz;
			this.leftArm.rotationPointZ += partyz;

			this.rightArm.rotateAngleX += partyx * 0.1F;
			this.leftArm.rotateAngleX += partyx * 0.1F;
			this.rightArm.rotateAngleZ += partyz * 0.2F;
			this.leftArm.rotateAngleZ += partyz * 0.2F;
		}

		if (this.isSitting) {
			if (entity.getAction() == ChimpanzeeEntity.Action.DEFAULT && !entity.isPartying()) {
				this.rightArm.rotateAngleX += (-(float) Math.PI / 5F);
				this.leftArm.rotateAngleX += (-(float) Math.PI / 5F);
			}
			this.rightLeg.rotateAngleX = -1.4137167F;
			this.rightLeg.rotateAngleY = ((float) Math.PI / 10F);
			this.rightLeg.rotateAngleZ = 0.07853982F;
			this.leftLeg.rotateAngleX = -1.4137167F;
			this.leftLeg.rotateAngleY = (-(float) Math.PI / 10F);
			this.leftLeg.rotateAngleZ = -0.07853982F;
		} else {
			this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 1.0F + climbanim * 0.5F;
			this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 1.0F + climbanim * 0.5F;
			this.rightLeg.rotateAngleY = 0.0F;
			this.leftLeg.rotateAngleY = 0.0F;
			this.rightLeg.rotateAngleZ = 0.0F;
			this.leftLeg.rotateAngleZ = 0.0F;
		}
	}

	protected HandSide getMainHand(T entityIn) {
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