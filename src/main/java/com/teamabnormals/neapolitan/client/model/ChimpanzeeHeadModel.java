package com.teamabnormals.neapolitan.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeHeadModel extends SkullModel {

	public ChimpanzeeHeadModel(ModelPart part) {
		super(part);
	}

	public static LayerDefinition createHeadLayer() {
		float modelSize = 1.0F;
		CubeDeformation deformation = CubeDeformation.NONE;
		MeshDefinition meshdefinition = SkullModel.createHeadModel();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 6.0F, deformation).texOffs(30, 11).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 5.0F, 1.0F, false), PartPose.ZERO);
		PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(25, 1).mirror().addBox(4.0F, -7.0F, -1.0F, 2.0F, 3.0F, 1.0F, deformation), PartPose.ZERO);
		PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(25, 1).addBox(-6.0F, -7.0F, -1.0F, 2.0F, 3.0F, 1.0F, deformation), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(float p_104188_, float p_104189_, float p_104190_) {
		super.setupAnim(p_104188_, p_104189_, p_104190_);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer p_103816_, int p_103817_, int p_103818_, float p_103819_, float p_103820_, float p_103821_, float p_103822_) {
		float scale = 1.125F;
		stack.scale(scale, scale, scale);
		super.renderToBuffer(stack, p_103816_, p_103817_, p_103818_, p_103819_, p_103820_, p_103821_, p_103822_);
	}
}