package com.teamabnormals.neapolitan.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeItemLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
	public ChimpanzeeItemLayer(RenderLayerParent<T, M> p_i50934_1_) {
		super(p_i50934_1_);
	}

	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean flag = entitylivingbaseIn.getMainArm() == HumanoidArm.RIGHT;
		ItemStack itemstack = flag ? entitylivingbaseIn.getOffhandItem() : entitylivingbaseIn.getMainHandItem();
		ItemStack itemstack1 = flag ? entitylivingbaseIn.getMainHandItem() : entitylivingbaseIn.getOffhandItem();
		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			matrixStackIn.pushPose();
			if (this.getParentModel().young) {
				matrixStackIn.translate(0.0D, 0.75D, 0.0D);
				matrixStackIn.scale(0.5F, 0.5F, 0.5F);
			}

			this.renderArmWithItem(entitylivingbaseIn, itemstack1, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, matrixStackIn, bufferIn, packedLightIn);
			this.renderArmWithItem(entitylivingbaseIn, itemstack, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.popPose();
		}
	}

	private void renderArmWithItem(LivingEntity p_229135_1_, ItemStack p_229135_2_, ItemTransforms.TransformType p_229135_3_, HumanoidArm p_229135_4_, PoseStack p_229135_5_, MultiBufferSource p_229135_6_, int p_229135_7_) {
		if (!p_229135_2_.isEmpty()) {
			p_229135_5_.pushPose();
			this.getParentModel().translateToHand(p_229135_4_, p_229135_5_);
			p_229135_5_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
			p_229135_5_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
			boolean flag = p_229135_4_ == HumanoidArm.LEFT;
			p_229135_5_.translate((float) (flag ? -1 : 1) / 16.0F * 1.5F, 0.125D, -0.5625D);
			Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
			p_229135_5_.popPose();
		}
	}
}
