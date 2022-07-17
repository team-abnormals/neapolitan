package com.teamabnormals.neapolitan.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeModel<T extends Chimpanzee> extends HumanoidModel<T> {
	public ModelPart leftEar;
	public ModelPart rightEar;

	protected ModelPart bodyDefault;
	protected ModelPart headDefault;
	protected ModelPart leftEarDefault;
	protected ModelPart rightEarDefault;
	protected ModelPart leftArmDefault;
	protected ModelPart rightArmDefault;
	protected ModelPart leftLegDefault;
	protected ModelPart rightLegDefault;

	public ChimpanzeeModel(ModelPart root) {
		super(root);
		this.leftEar = this.head.getChild("left_ear");
		this.rightEar = this.head.getChild("right_ear");
		this.bodyDefault = root.getChild("body_default");
		this.headDefault = root.getChild("head_default");
		this.leftEarDefault = root.getChild("left_ear_default");
		this.rightEarDefault = root.getChild("right_ear_default");
		this.leftArmDefault = root.getChild("left_arm_default");
		this.rightArmDefault = root.getChild("right_arm_default");
		this.leftLegDefault = root.getChild("left_leg_default");
		this.rightLegDefault = root.getChild("right_leg_default");
	}

	public static LayerDefinition createBodyLayer(float modelSize, boolean isArmor, boolean isInner) {
		CubeDeformation deformation = CubeDeformation.NONE;
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition root = meshdefinition.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(37, 0).addBox(-5.0F, -0.0F, -1.5F, 10.0F, 8.0F, 3.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 6.0F, deformation.extend(modelSize)).texOffs(30, 11).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 5.0F, 1.0F, false), PartPose.offsetAndRotation(0.0F, 5.0F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(25, 1).mirror().addBox(0.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(4.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(25, 1).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(-4.0F, -3.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(14, 17).mirror().addBox(0.0F, -1.5F, -1.5F, 3.0F, 11.0F, 3.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(5.0F, 6.5F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(1, 17).addBox(-3.0F, -1.5F, -1.5F, 3.0F, 11.0F, 3.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(-5.0F, 6.5F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(40, 17).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(27, 17).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(-2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));

		if (isArmor) {
			head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.5F, 0.0F, 0.0F, 0.0F));
			body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.5F, 0.0F, 0.0F, 0.0F));
			rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(-5.0F, 6.5F, 0.5F, 0.0F, 0.0F, 0.0F));
			leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(5.0F, 6.5F, 0.5F, 0.0F, 0.0F, 0.0F));

			if (isInner) {
				rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(-2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));
				leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));
			} else {
				rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(-2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));
				leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.5F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(modelSize)), PartPose.offsetAndRotation(2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));
			}

			leftEar = root.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
			rightEar = root.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		}

		PartDefinition hat = root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition bodyDefault = root.addOrReplaceChild("body_default", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition headDefault = root.addOrReplaceChild("head_default", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftEarDefault = root.addOrReplaceChild("left_ear_default", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightEarDefault = root.addOrReplaceChild("right_ear_default", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, -3.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftArmDefault = root.addOrReplaceChild("left_arm_default", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 6.5F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightArmDefault = root.addOrReplaceChild("right_arm_default", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 6.5F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftLegDefault = root.addOrReplaceChild("left_leg_default", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightLegDefault = root.addOrReplaceChild("right_leg_default", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, 13.0F, 0.5F, 0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// SETUP //

		float partialtick = ageInTicks - (float) entity.tickCount;
		float fullclimbanim = entity.getClimbingAnim(partialtick);
		float climbanim = Math.min(fullclimbanim * 5F / 3F, 1F);
		int attacktimer = entity.getAttackTimer();
		int earstate = this.getEarState(entity);
		boolean apemode = entity.getApeModeTime() > 0;
		HumanoidArm mainhand = entity.getMainArm();

		this.body.copyFrom(this.bodyDefault);
		this.head.copyFrom(this.headDefault);
		this.leftEar.copyFrom(this.leftEarDefault);
		this.rightEar.copyFrom(this.rightEarDefault);
		this.leftArm.copyFrom(this.leftArmDefault);
		this.rightArm.copyFrom(this.rightArmDefault);
		this.leftLeg.copyFrom(this.leftLegDefault);
		this.rightLeg.copyFrom(this.rightLegDefault);

		if (earstate == 2) {
			this.leftEar.visible = false;
			this.rightEar.visible = false;
		} else {
			if (earstate == 1) {
				this.leftEar.yRot = (float) -Math.PI / 2F;
				this.leftEar.x -= 0.1F;
				this.rightEar.yRot = (float) Math.PI / 2F;
				this.rightEar.x += 0.1F;
			}
			this.leftEar.visible = true;
			this.rightEar.visible = true;
		}

		if (this.young) {
			this.head.y -= 1.6F;
		}

		// BASE ANIMATIONS //

		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		if (entity.isPartying()) {
			this.rightArm.xRot = (1F - climbanim) * (float) -Math.PI;
			this.leftArm.xRot = (1F - climbanim) * (float) -Math.PI;
		} else {
			float f = apemode ? 1.4F : 1.0F;
			this.rightArm.xRot += Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2F * limbSwingAmount * 0.5F * f;
			this.leftArm.xRot += Mth.cos(limbSwing * 0.6662F) * 2F * limbSwingAmount * 0.5F * f;

			if (!entity.getMainHandItem().isEmpty()) {
				this.getArm(mainhand).xRot -= (float) Math.PI / 10F;
			}
			if (!entity.getOffhandItem().isEmpty()) {
				this.getArm(mainhand.getOpposite()).xRot -= (float) Math.PI / 10F;
			}
		}

		// SITTING ANIMATIONS //

		if (this.riding) {
			if (!entity.isPartying()) {
				this.rightArm.xRot += (-(float) Math.PI / 5F);
				this.leftArm.xRot += (-(float) Math.PI / 5F);
			}

			this.rightLeg.xRot = -1.4137167F;
			this.rightLeg.yRot = ((float) Math.PI / 10F);
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = -1.4137167F;
			this.leftLeg.yRot = (-(float) Math.PI / 10F);
			this.leftLeg.zRot = -0.07853982F;
		} else {
			float f = entity.getSitAnim(partialtick);

			if (f > 0F) {
				if (!entity.isPartying()) {
					this.rightArm.xRot += f * -((float) Math.PI / 5F);
					this.leftArm.xRot += f * -((float) Math.PI / 5F);
				}

				this.body.y += f * 10F;
				this.head.y += f * (this.young ? 6.5F : 10F);
				this.rightArm.y += f * 10F;
				this.leftArm.y += f * 10F;
				this.rightLeg.y += f * 10F;
				this.rightLeg.z += f;
				this.leftLeg.y += f * 10F;
				this.leftLeg.z += f;

				this.rightLeg.xRot = f * -((float) Math.PI / 2F);
				this.rightLeg.yRot = f * ((float) Math.PI / 10F);
				this.leftLeg.xRot = f * -((float) Math.PI / 2F);
				this.leftLeg.yRot = f * -((float) Math.PI / 10F);
			}

			if (!entity.isDoingAction(ChimpanzeeAction.SHAKING)) {
				this.rightLeg.xRot += Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
				this.leftLeg.xRot += Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			}
		}

		// ACTION ANIMATIONS //

		if (entity.isDoingAction(ChimpanzeeAction.EATING)) {
			float f = Mth.sin(ageInTicks * 0.7F);

			this.head.xRot = -f * 0.2F;
			this.head.yRot = 0F;

			this.rightArm.xRot = f * 0.4F - 1.7F;
			this.leftArm.xRot = f * 0.4F - 1.7F;
			this.rightArm.yRot = -0.6F;
			this.leftArm.yRot = 0.6F;
		} else if (entity.isDoingAction(ChimpanzeeAction.GROOMING)) {
			float f = Mth.sin(ageInTicks * 0.5F);

			this.rightArm.xRot = f * 0.4F - (float) Math.PI / 2F;
			this.leftArm.xRot = -f * 0.4F - (float) Math.PI / 2F;
		} else if (entity.isDoingAction(ChimpanzeeAction.SHAKING)) {
			float f = Mth.sin(ageInTicks * 0.82F) * 1.4F;
			float f1 = Mth.cos(ageInTicks * 0.82F) * 1.8F;

			this.head.y += f1 + 1.5F;
			this.head.z += f;
			this.body.y += f1 + 1.5F;
			this.body.z += f;
			this.rightArm.y += 1.5F;
			this.rightArm.z += f * 0.5F;
			this.leftArm.y += 1.5F;
			this.leftArm.z += f * 0.5F;
			this.rightLeg.y += f1 + 1.5F;
			this.rightLeg.z += f;
			this.leftLeg.y += f1 + 1.5F;
			this.leftLeg.z += f;

			this.rightLeg.xRot = f * 0.35F - (float) Math.PI * 0.1F;
			this.leftLeg.xRot = f * 0.35F - (float) Math.PI * 0.1F;
		} else if (entity.isDoingAction(ChimpanzeeAction.CRYING)) {
			float f = Mth.sin(ageInTicks * 0.6F);

			this.head.xRot = 0.3F - f * 0.2F;
			this.head.yRot = 0F;

			this.leftArm.xRot = f * 0.4F - 1.7F;
			this.leftArm.yRot = 0.5F;
			this.rightArm.xRot = f * 0.4F - 1.7F;
			this.rightArm.yRot = -0.5F;
		} else if (entity.isDoingAction(ChimpanzeeAction.LOOKING_AT_ITEM)) {
			this.head.xRot = 0.5F;
			this.head.yRot = 0F;

			if (entity.isLeftHanded()) {
				this.leftArm.yRot = 0.5F;
				this.leftArm.xRot = -0.9F;
			} else {
				this.rightArm.yRot = -0.5F;
				this.rightArm.xRot = -0.9F;
			}
		} else if (entity.isDoingAction(ChimpanzeeAction.PLAYING_WITH_ITEM)) {
			float f = Mth.sin(ageInTicks * 0.6F);

			this.head.xRot = -0.1F + Mth.sin(ageInTicks * 0.6F - 0.5F) * 0.1F;

			if (entity.isLeftHanded()) {
				this.leftArm.xRot = f * 0.5F - 1.8F;
				this.head.yRot = -0.5F;
			} else {
				this.rightArm.xRot = f * 0.5F - 1.8F;
				this.head.yRot = 0.5F;
			}
		} else if (entity.isDoingAction(ChimpanzeeAction.PLAYING_WITH_HELMET)) {
			this.head.xRot = Mth.cos(ageInTicks * 0.55F) * 0.5F;
			this.head.yRot = 0.0F;
			this.head.zRot = Mth.sin(ageInTicks * 0.55F) * 0.3F;
		} else if (entity.isDoingAction(ChimpanzeeAction.JUMPING)) {
			float f = -Math.abs(Mth.sin(ageInTicks * 0.2F));

			this.rightArm.x += 1.0F;
			this.leftArm.x += -1.0F;
			this.rightArm.xRot = (float) -Math.PI;
			this.leftArm.xRot = (float) -Math.PI;
			this.rightArm.zRot = f * 0.4F - 0.4F;
			this.leftArm.zRot = -f * 0.4F + 0.4F;
		} else if (entity.isDoingAction(ChimpanzeeAction.DRUMMING)) {
			float f = -Math.abs(Mth.sin(ageInTicks * 0.3F));
			float f1 = -Math.abs(Mth.sin(ageInTicks * 0.3F + (float) Math.PI / 2F));

			this.rightArm.xRot = f - 0.9F;
			this.leftArm.xRot = f1 - 0.9F;
			this.rightArm.yRot = -0.5F;
			this.leftArm.yRot = 0.5F;
		} else {
			float f = entity.getHeadShakeAnim(partialtick);

			if (f > 0F) {
				this.head.yRot = Mth.sin(f * (float) Math.PI / 8F) * 0.3F;
				this.head.xRot = 0.6F;
			}
		}

		// OVERLAPPING ANIMATIONS //

		this.rightArm.xRot += -fullclimbanim * (float) Math.PI;
		this.leftArm.xRot += -fullclimbanim * (float) Math.PI;
		this.rightArm.yRot += climbanim * 0.6F;
		this.leftArm.yRot += -climbanim * 0.6F;

		this.rightLeg.xRot += -climbanim * (float) Math.PI * 0.2F;
		this.leftLeg.xRot += -climbanim * (float) Math.PI * 0.2F;

		if (!entity.isDoingAction(ChimpanzeeAction.CLIMBING, ChimpanzeeAction.HANGING, ChimpanzeeAction.SHAKING)) {
			AnimationUtils.bobArms(this.rightArm, this.leftArm, ageInTicks);
		}

		if (entity.isPartying()) {
			float f = Mth.cos(ageInTicks * 0.75F) * 0.5F;
			float f1 = Mth.sin(ageInTicks * 0.75F) * 0.5F;

			this.head.x += f;
			this.body.x += f;
			this.rightArm.x += f;
			this.leftArm.x += f;
			this.head.z += f1;
			this.body.z += f1;
			this.rightArm.z += f1;
			this.leftArm.z += f1;

			this.rightArm.xRot += f * 0.1F;
			this.leftArm.xRot += f * 0.1F;
			this.rightArm.zRot += f1 * 0.2F - 0.1F;
			this.leftArm.zRot += f1 * 0.2F + 0.1F;
		}

		if (attacktimer > 0) {
			this.rightArm.xRot = -2F + 1.5F * Mth.triangleWave((float) attacktimer - partialtick, 10F);
			this.leftArm.xRot = -2F + 1.5F * Mth.triangleWave((float) attacktimer - partialtick, 10F);
			this.rightArm.yRot = 0F;
			this.leftArm.yRot = 0F;
		}
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}

	@Override
	public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
		this.getArm(sideIn).translateAndRotate(matrixStackIn);
	}

	private int getEarState(T entity) {
		ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);
		Item item = itemstack.getItem();

		if (itemstack.is(NeapolitanItemTags.HIDES_CHIMPANZEE_EARS)) {
			return 2;
		} else if (item instanceof BlockItem || (item instanceof ArmorItem && ((ArmorItem) item).getSlot() == EquipmentSlot.HEAD)) {
			return 1;
		}

		return 0;
	}
}