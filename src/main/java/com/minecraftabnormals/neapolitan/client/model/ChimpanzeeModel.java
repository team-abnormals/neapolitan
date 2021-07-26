package com.minecraftabnormals.neapolitan.client.model;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
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

	private final ModelRenderer bodyDefault;
	private final ModelRenderer headDefault;
	private final ModelRenderer leftArmDefault;
	private final ModelRenderer rightArmDefault;
	private final ModelRenderer leftLegDefault;
	private final ModelRenderer rightLegDefault;

	public ChimpanzeeModel() {
		super(false, 10.0F, 0.0F, 2.0F, 2.0F, 24);
		texWidth = 64;
		texHeight = 32;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 9.0F, 2.0F);
		body.texOffs(37, 0).addBox(-5.0F, -4.0F, -3.0F, 10.0F, 8.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 5.0F, 0.5F);
		head.texOffs(1, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);
		head.texOffs(30, 11).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);

		rightEar = new ModelRenderer(this);
		rightEar.setPos(-4.0F, -3.0F, 0.0F);
		head.addChild(rightEar);
		rightEar.texOffs(25, 1).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		leftEar = new ModelRenderer(this);
		leftEar.setPos(4.0F, -5.0F, 0.0F);
		head.addChild(leftEar);
		leftEar.texOffs(25, 1).addBox(0.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(5.0F, 6.5F, 0.5F);
		leftArm.texOffs(14, 17).addBox(0.0F, -1.5F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-5.0F, 6.5F, 0.5F);
		rightArm.texOffs(1, 17).addBox(-3.0F, -1.5F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(2.5F, 13.0F, 0.5F);
		leftLeg.texOffs(40, 17).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-2.5F, 13.0F, 0.5F);
		rightLeg.texOffs(27, 17).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		this.bodyDefault = this.body.createShallowCopy();
		this.headDefault = this.head.createShallowCopy();
		this.leftArmDefault = this.leftArm.createShallowCopy();
		this.rightArmDefault = this.rightArm.createShallowCopy();
		this.leftLegDefault = this.leftLeg.createShallowCopy();
		this.rightLegDefault = this.rightLeg.createShallowCopy();
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float partialtick = ageInTicks - (float)entity.tickCount;
		float climbanimation1 = entity.getClimbingAnimationScale(partialtick);
		float climbanimation2 = Math.min(climbanimation1 * 5.0F / 3.0F, 1.0F);
		int attackanim = entity.getAttackTimer();
		HandSide mainhand = entity.getMainArm();

		this.body.copyFrom(this.bodyDefault);
		this.head.copyFrom(this.headDefault);
		this.leftArm.copyFrom(this.leftArmDefault);
		this.rightArm.copyFrom(this.rightArmDefault);
		this.leftLeg.copyFrom(this.leftLegDefault);
		this.rightLeg.copyFrom(this.rightLegDefault);

		if (entity.getAction() != ChimpanzeeAction.EATING) {
			this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
			this.head.xRot = headPitch * ((float) Math.PI / 180F);
		}

		if (entity.getAction() == ChimpanzeeAction.EATING) {
			float eatanim = MathHelper.sin(ageInTicks * 0.7F);

			this.head.xRot = -eatanim * 0.2F;
			this.head.yRot = 0.0F;

			this.rightArm.xRot = eatanim * 0.4F - 1.7F;
			this.leftArm.xRot = eatanim * 0.4F - 1.7F;
			this.rightArm.yRot = -0.6F;
			this.leftArm.yRot = 0.6F;
		} else if (entity.getAction() == ChimpanzeeAction.GROOMING) {
			float groomanim = MathHelper.sin(ageInTicks * 0.5F);

			this.rightArm.xRot = groomanim * 0.4F - (float) Math.PI / 2F;
			this.leftArm.xRot = -groomanim * 0.4F - (float) Math.PI / 2F;
		} else if (entity.getAction() == ChimpanzeeAction.SHAKING) {
			float shakeanimz = MathHelper.sin(ageInTicks * 0.82F) * 1.4F;
			float shakeanimy = MathHelper.cos(ageInTicks * 0.82F) * 1.8F;

			this.head.y += shakeanimy + 1.5F;
			this.head.z += shakeanimz;
			this.body.y += shakeanimy + 1.5F;
			this.body.z += shakeanimz;
			this.rightArm.y += 1.5F;
			this.rightArm.z += shakeanimz * 0.5F;
			this.leftArm.y += 1.5F;
			this.leftArm.z += shakeanimz * 0.5F;
			this.rightLeg.y += shakeanimy + 1.5F;
			this.rightLeg.z += shakeanimz;
			this.leftLeg.y += shakeanimy + 1.5F;
			this.leftLeg.z += shakeanimz;

			this.rightLeg.xRot = shakeanimz * 0.35F - (float) Math.PI * 0.1F;
			this.leftLeg.xRot = shakeanimz * 0.35F - (float) Math.PI * 0.1F;
		} else if (entity.getAction() == ChimpanzeeAction.HUNCHING) {
			this.head.xRot = 0.8F;
			this.head.yRot = MathHelper.sin(ageInTicks * 0.2F) * 0.2F;
		} else if (entity.getAction() == ChimpanzeeAction.CRYING) {
			float cryanim = MathHelper.sin(ageInTicks * 0.7F);

			this.head.xRot = 0.3F - cryanim * 0.2F;
			this.head.yRot = 0.0F;

			this.rightArm.xRot = cryanim * 0.4F - 1.7F;
			this.leftArm.xRot = cryanim * 0.4F - 1.7F;
		} else if (entity.getAction() == ChimpanzeeAction.LOOKING_AT_MAIN_HAND_ITEM) {
			this.head.xRot = 0.5F;
			this.head.yRot = 0.0F;

			if (entity.isLeftHanded()) {
				this.leftArm.yRot = 0.5F;
				this.leftArm.xRot = -0.9F;
			} else {
				this.rightArm.yRot = -0.5F;
				this.rightArm.xRot = -0.9F;
			}
		} else if (entity.getAction() == ChimpanzeeAction.LOOKING_AT_OFF_HAND_ITEM) {
			this.head.xRot = 0.5F;
			this.head.yRot = 0.0F;

			if (entity.isLeftHanded()) {
				this.rightArm.yRot = -0.5F;
				this.rightArm.xRot = -0.9F;
			} else {
				this.leftArm.yRot = 0.5F;
				this.leftArm.xRot = -0.9F;
			}
		} else if (entity.getAction() == ChimpanzeeAction.DRUMMING) {
			float drumanim1 = -Math.abs(MathHelper.sin(ageInTicks * 0.3F));
			float drumanim2 = -Math.abs(MathHelper.sin(ageInTicks * 0.3F + (float) Math.PI / 2F));

			this.rightArm.xRot = drumanim1 - 0.3F;
			this.leftArm.xRot = drumanim2 - 0.3F;
			this.rightArm.yRot = -0.5F;
			this.leftArm.yRot = 0.5F;
		} else {
			if (entity.isPartying()) {
				this.rightArm.xRot = (1.0F - climbanimation2) * (float) -Math.PI;
				this.leftArm.xRot = (1.0F - climbanimation2) * (float) -Math.PI;
			} else {
				this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / 1.0F;
				this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / 1.0F;

				if (!entity.getMainHandItem().isEmpty()) {
					this.getArmForSide(mainhand).xRot -= (float) Math.PI / 10F;
				}
				if (!entity.getOffhandItem().isEmpty()) {
					this.getArmForSide(mainhand.getOpposite()).xRot -= (float) Math.PI / 10F;
				}
			}

			float headshakeanimation = entity.getHeadShakeProgress(partialtick);

			if (entity.getHeadShakeProgress(partialtick) > 0.0F) {
				this.head.yRot = MathHelper.sin(headshakeanimation * (float) Math.PI / 8F) * 0.3F;
				this.head.xRot = 0.6F;
			}
		}

		this.rightArm.xRot += -climbanimation1 * (float) Math.PI;
		this.leftArm.xRot += -climbanimation1 * (float) Math.PI;
		this.rightArm.yRot += climbanimation2 * 0.6F;
		this.leftArm.yRot += -climbanimation2 * 0.6F;

		if (entity.isPartying()) {
			float partyx = MathHelper.cos(ageInTicks * 0.75F) * 0.5F;
			float partyz = MathHelper.sin(ageInTicks * 0.75F) * 0.5F;

			this.head.x += partyx;
			this.body.x += partyx;
			this.rightArm.x += partyx;
			this.leftArm.x += partyx;
			this.head.z += partyz;
			this.body.z += partyz;
			this.rightArm.z += partyz;
			this.leftArm.z += partyz;

			this.rightArm.xRot += partyx * 0.1F;
			this.leftArm.xRot += partyx * 0.1F;
			this.rightArm.zRot += partyz * 0.2F;
			this.leftArm.zRot += partyz * 0.2F;
		}

		if (attackanim > 0) {
			this.rightArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float) attackanim - partialtick, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float) attackanim - partialtick, 10.0F);
			this.rightArm.yRot = 0.0F;
			this.leftArm.yRot = 0.0F;
		} 

		if (this.riding) {
			if ((entity.getAction() == ChimpanzeeAction.DEFAULT && !entity.isPartying()) || entity.getAction() == ChimpanzeeAction.HUNCHING) {
				this.rightArm.xRot += (-(float) Math.PI / 5F);
				this.leftArm.xRot += (-(float) Math.PI / 5F);
			}
			this.rightLeg.xRot = -1.4137167F;
			this.rightLeg.yRot = ((float) Math.PI / 10F);
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = -1.4137167F;
			this.leftLeg.yRot = (-(float) Math.PI / 10F);
			this.leftLeg.zRot = -0.07853982F;
		} else if (entity.isSitting()) {
			if (!entity.isPartying()) {
				this.rightArm.xRot += (-(float) Math.PI / 5F);
				this.leftArm.xRot += (-(float) Math.PI / 5F);
			}

			this.body.y += 10.0F;
			this.head.y += 10.0F;
			this.rightArm.y += 10.0F;
			this.leftArm.y += 10.0F;
			this.rightLeg.y += 10.0F;
			this.leftLeg.y += 10.0F;

			this.rightLeg.xRot = -((float) Math.PI / 2F);
			this.rightLeg.yRot = ((float) Math.PI / 10F);
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = -((float) Math.PI / 2F);
			this.leftLeg.yRot = (-(float) Math.PI / 10F);
			this.leftLeg.zRot = -0.07853982F;
		} else if (entity.getAction() != ChimpanzeeAction.SHAKING) {
			this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount - climbanimation2 * (float) Math.PI * 0.2F;
			this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount - climbanimation2 * (float) Math.PI * 0.2F;
		}
	}

	protected HandSide getMainHand(T entityIn) {
		HandSide handside = entityIn.getMainArm();
		return entityIn.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
	}

	protected ModelRenderer getArmForSide(HandSide side) {
		return side == HandSide.LEFT ? this.leftArm : this.rightArm;
	}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(this.body, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
	}

	@Override
	public ModelRenderer getHead() {
		return this.head;
	}

	@Override
	public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn) {
		this.getArmForSide(sideIn).translateAndRotate(matrixStackIn);
	}
}