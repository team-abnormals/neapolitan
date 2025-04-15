package com.teamabnormals.neapolitan.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class BananaPeelModel extends EntityModel<Entity> {
	private final ModelPart bananaPeel;

	public BananaPeelModel(ModelPart root) {
		this.bananaPeel = root.getChild("banana_peel");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		PartDefinition bananaPeel = root.addOrReplaceChild("banana_peel", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, false).texOffs(0, 12).addBox(1.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, false).texOffs(0, 14).addBox(-5.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, false).texOffs(0, 7).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 0.0F, 4.0F, false).texOffs(4, 7).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 0.0F, 4.0F, false), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(mesh, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		bananaPeel.render(poseStack, buffer, packedLight, packedOverlay);
	}
}