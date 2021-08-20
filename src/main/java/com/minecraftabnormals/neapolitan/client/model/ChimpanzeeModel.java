package com.minecraftabnormals.neapolitan.client.model;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeModel<T extends ChimpanzeeEntity> extends BipedModel<T> {
	public ModelRenderer leftEar;
	public ModelRenderer rightEar;

	protected ModelRenderer bodyDefault;
	protected ModelRenderer headDefault;
	protected ModelRenderer leftEarDefault;
	protected ModelRenderer rightEarDefault;
	protected ModelRenderer leftArmDefault;
	protected ModelRenderer rightArmDefault;
	protected ModelRenderer leftLegDefault;
	protected ModelRenderer rightLegDefault;

	public ChimpanzeeModel() {
		this(0F, false, false);
	}

	public ChimpanzeeModel(float modelSize, boolean isArmor, boolean isInner) {
		super(modelSize);
		this.texWidth = 64;
		this.texHeight = 32;

		if (isArmor) {
			this.head = new ModelRenderer(this);
			this.head.setPos(0F, 1F, 0.5F);
			this.head.texOffs(0, 0).addBox(-4F, -8F, -4F, 8F, 8F, 8F, modelSize);

			this.body = new ModelRenderer(this);
			this.body.setPos(0F, 5F, 0.5F);
			this.body.texOffs(16, 16).addBox(-4F, -0F, -2F, 8F, 12F, 4F, modelSize);

			if (isInner) {
				this.leftLeg = new ModelRenderer(this);
				this.leftLeg.setPos(2.5F, 13F, 0.5F);
				this.leftLeg.texOffs(0, 16).addBox(-2.5F, -1F, -2F, 4F, 12F, 4F, modelSize, true);

				this.rightLeg = new ModelRenderer(this);
				this.rightLeg.setPos(-2.5F, 13F, 0.5F);
				this.rightLeg.texOffs(0, 16).addBox(-1.5F, -1F, -2F, 4F, 12F, 4F, modelSize);
			} else {
				this.leftLeg = new ModelRenderer(this);
				this.leftLeg.setPos(2.5F, 13F, 0.5F);
				this.leftLeg.texOffs(0, 16).addBox(-2.5F, -1F, -2F, 4F, 12F, 4F, modelSize, true);

				this.rightLeg = new ModelRenderer(this);
				this.rightLeg.setPos(-2.5F, 13F, 0.5F);
				this.rightLeg.texOffs(0, 16).addBox(-1.5F, -1F, -2F, 4F, 12F, 4F, modelSize);
			}

			this.leftEar = new ModelRenderer(this);

			this.rightEar = new ModelRenderer(this);

			this.leftArm = new ModelRenderer(this);
			this.leftArm.setPos(5F, 6.5F, 0.5F);
			this.leftArm.texOffs(40, 16).addBox(-1F, -1.5F, -2F, 4F, 12F, 4F, modelSize, true);

			this.rightArm = new ModelRenderer(this);
			this.rightArm.setPos(-5F, 6.5F, 0.5F);
			this.rightArm.texOffs(40, 16).addBox(-3F, -1.5F, -2F, 4F, 12F, 4F, modelSize);
		} else {
			this.head = new ModelRenderer(this);
			this.head.setPos(0F, 5F, 0.5F);
			this.head.texOffs(1, 1).addBox(-4F, -8F, -3F, 8F, 8F, 6F, modelSize);
			this.head.texOffs(30, 11).addBox(-2F, -5F, -4F, 4F, 5F, 1F, modelSize);

			this.body = new ModelRenderer(this);
			this.body.setPos(0F, 5F, 0.5F);
			this.body.texOffs(37, 0).addBox(-5F, -0F, -1.5F, 10F, 8F, 3F, modelSize);

			this.leftEar = new ModelRenderer(this);
			this.leftEar.setPos(4F, -5F, 0F);
			this.head.addChild(leftEar);
			this.leftEar.texOffs(25, 1).addBox(0F, -2F, -1F, 2F, 3F, 1F, modelSize, true);

			this.rightEar = new ModelRenderer(this);
			this.rightEar.setPos(-4F, -3F, 0F);
			this.head.addChild(rightEar);
			this.rightEar.texOffs(25, 1).addBox(-2F, -4F, -1F, 2F, 3F, 1F, modelSize);

			this.leftArm = new ModelRenderer(this);
			this.leftArm.setPos(5F, 6.5F, 0.5F);
			this.leftArm.texOffs(14, 17).addBox(0F, -1.5F, -1.5F, 3F, 11F, 3F, modelSize, true);

			this.rightArm = new ModelRenderer(this);
			this.rightArm.setPos(-5F, 6.5F, 0.5F);
			this.rightArm.texOffs(1, 17).addBox(-3F, -1.5F, -1.5F, 3F, 11F, 3F, modelSize);

			this.leftLeg = new ModelRenderer(this);
			this.leftLeg.setPos(2.5F, 13F, 0.5F);
			this.leftLeg.texOffs(40, 17).addBox(-1.5F, 0F, -1.5F, 3F, 11F, 3F, modelSize, true);

			this.rightLeg = new ModelRenderer(this);
			this.rightLeg.setPos(-2.5F, 13F, 0.5F);
			this.rightLeg.texOffs(27, 17).addBox(-1.5F, 0F, -1.5F, 3F, 11F, 3F, modelSize);
		}

		this.hat = new ModelRenderer(this);

		this.bodyDefault = this.body.createShallowCopy();
		this.headDefault = this.head.createShallowCopy();
		this.leftEarDefault = this.leftEar.createShallowCopy();
		this.rightEarDefault = this.rightEar.createShallowCopy();
		this.leftArmDefault = this.leftArm.createShallowCopy();
		this.rightArmDefault = this.rightArm.createShallowCopy();
		this.leftLegDefault = this.leftLeg.createShallowCopy();
		this.rightLegDefault = this.rightLeg.createShallowCopy();
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// SETUP //

		float partialtick = ageInTicks - (float)entity.tickCount;
		float fullclimbamount = entity.getClimbingAmount(partialtick);
		float climbamount = Math.min(fullclimbamount * 5F / 3F, 1F);
		int attacktimer = entity.getAttackTimer();
		int earstate = this.getEarState(entity);
		HandSide mainhand = entity.getMainArm();

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
			this.rightArm.xRot = (1F - climbamount) * (float) -Math.PI;
			this.leftArm.xRot = (1F - climbamount) * (float) -Math.PI;
		} else {
			this.rightArm.xRot += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2F * limbSwingAmount * 0.5F / 1F;
			this.leftArm.xRot += MathHelper.cos(limbSwing * 0.6662F) * 2F * limbSwingAmount * 0.5F / 1F;
		}

		if (!entity.getMainHandItem().isEmpty()) {
			this.getArm(mainhand).xRot -= (float) Math.PI / 10F;
		}
		if (!entity.getOffhandItem().isEmpty()) {
			this.getArm(mainhand.getOpposite()).xRot -= (float) Math.PI / 10F;
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
			float f = entity.getSittingAmount(partialtick);

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

			if (entity.getAction() != ChimpanzeeAction.SHAKING) {
				this.rightLeg.xRot += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
				this.leftLeg.xRot += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			}
		}

		// ACTION ANIMATIONS //

		if (entity.getAction() == ChimpanzeeAction.EATING) {
			float f = MathHelper.sin(ageInTicks * 0.7F);

			this.head.xRot = -f * 0.2F;
			this.head.yRot = 0F;

			this.rightArm.xRot = f * 0.4F - 1.7F;
			this.leftArm.xRot = f * 0.4F - 1.7F;
			this.rightArm.yRot = -0.6F;
			this.leftArm.yRot = 0.6F;
		} else if (entity.getAction() == ChimpanzeeAction.GROOMING) {
			float f = MathHelper.sin(ageInTicks * 0.5F);

			this.rightArm.xRot = f * 0.4F - (float) Math.PI / 2F;
			this.leftArm.xRot = -f * 0.4F - (float) Math.PI / 2F;
		} else if (entity.getAction() == ChimpanzeeAction.SHAKING) {
			float f = MathHelper.sin(ageInTicks * 0.82F) * 1.4F;
			float f1 = MathHelper.cos(ageInTicks * 0.82F) * 1.8F;

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
		} else if (entity.getAction() == ChimpanzeeAction.HUNCHING) {
			this.head.xRot = 0.8F;
			this.head.yRot = MathHelper.sin(ageInTicks * 0.2F) * 0.2F;
		} else if (entity.getAction() == ChimpanzeeAction.CRYING) {
			float f = MathHelper.sin(ageInTicks * 0.7F);

			this.head.xRot = 0.3F - f * 0.2F;
			this.head.yRot = 0F;

			this.leftArm.xRot = f * 0.4F - 1.7F;
			this.leftArm.yRot = 0.5F;
			this.rightArm.xRot = f * 0.4F - 1.7F;
			this.rightArm.yRot = -0.5F;
		} else if (entity.getAction() == ChimpanzeeAction.LOOKING_AT_ITEM) {
			this.head.xRot = 0.5F;
			this.head.yRot = 0F;

			if (entity.isLeftHanded()) {
				this.leftArm.yRot = 0.5F;
				this.leftArm.xRot = -0.9F;
			} else {
				this.rightArm.yRot = -0.5F;
				this.rightArm.xRot = -0.9F;
			}
		} else if (entity.getAction() == ChimpanzeeAction.PLAYING_WITH_ITEM) {
			float f = MathHelper.sin(ageInTicks * 0.6F);

			this.head.xRot = -0.1F + MathHelper.sin(ageInTicks * 0.6F - 0.5F) * 0.1F;

			if (entity.isLeftHanded()) {
				this.leftArm.xRot = f * 0.5F - 1.8F;
				this.head.yRot = -0.5F;
			} else {
				this.rightArm.xRot = f * 0.5F - 1.8F;
				this.head.yRot = 0.5F;
			}
		} else if (entity.getAction() == ChimpanzeeAction.PLAYING_WITH_HELMET) {
			this.head.xRot = MathHelper.cos(ageInTicks * 0.55F) * 0.5F;
			this.head.yRot = 0.0F;
			this.head.zRot =  MathHelper.sin(ageInTicks * 0.55F) * 0.3F;
		} else if (entity.getAction() == ChimpanzeeAction.DRUMMING) {
			float f = -Math.abs(MathHelper.sin(ageInTicks * 0.3F));
			float f1 = -Math.abs(MathHelper.sin(ageInTicks * 0.3F + (float) Math.PI / 2F));

			this.rightArm.xRot = f - 0.9F;
			this.leftArm.xRot = f1 - 0.9F;
			this.rightArm.yRot = -0.5F;
			this.leftArm.yRot = 0.5F;
		} else {
			float f = entity.getHeadShakeAmount(partialtick);

			if (f > 0F) {
				this.head.yRot = MathHelper.sin(f * (float) Math.PI / 8F) * 0.3F;
				this.head.xRot = 0.6F;
			}
		}

		// OVERLAPPING ANIMATIONS //

		this.rightArm.xRot += -fullclimbamount * (float) Math.PI;
		this.leftArm.xRot += -fullclimbamount * (float) Math.PI;
		this.rightArm.yRot += climbamount * 0.6F;
		this.leftArm.yRot += -climbamount * 0.6F;

		this.rightLeg.xRot += -climbamount * (float) Math.PI * 0.2F;
		this.leftLeg.xRot += -climbamount * (float) Math.PI * 0.2F;

		if (entity.getAction() != ChimpanzeeAction.CLIMBING && entity.getAction() != ChimpanzeeAction.HANGING && entity.getAction() != ChimpanzeeAction.SHAKING) {
			ModelHelper.bobArms(this.rightArm, this.leftArm, ageInTicks);
		}

		if (entity.isPartying()) {
			float f = MathHelper.cos(ageInTicks * 0.75F) * 0.5F;
			float f1 = MathHelper.sin(ageInTicks * 0.75F) * 0.5F;

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
			this.rightArm.zRot += f1 * 0.2F;
			this.leftArm.zRot += f1 * 0.2F;
		}

		if (attacktimer > 0) {
			this.rightArm.xRot = -2F + 1.5F * MathHelper.triangleWave((float) attacktimer - partialtick, 10F);
			this.leftArm.xRot = -2F + 1.5F * MathHelper.triangleWave((float) attacktimer - partialtick, 10F);
			this.rightArm.yRot = 0F;
			this.leftArm.yRot = 0F;
		} 
	}

	protected HandSide getMainHand(T entityIn) {
		HandSide handside = entityIn.getMainArm();
		return entityIn.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
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
		this.getArm(sideIn).translateAndRotate(matrixStackIn);
	}

	private int getEarState(T entity) {
		ItemStack itemstack = entity.getItemBySlot(EquipmentSlotType.HEAD);
		if (itemstack != null) {
			Item item = itemstack.getItem();

			if (item.is(NeapolitanTags.Items.HIDES_CHIMPANZEE_EARS)) {
				return 2;
			} else {
				if (item instanceof BlockItem) {
					return 1;
				} else if (item instanceof ArmorItem && ((ArmorItem)item).getSlot() == EquipmentSlotType.HEAD) {
					return 1;
				}
			}
		}

		return 0;
	}
}