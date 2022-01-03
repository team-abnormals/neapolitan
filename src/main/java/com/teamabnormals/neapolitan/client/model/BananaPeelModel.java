package com.teamabnormals.neapolitan.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BananaPeelModel extends EntityModel<Entity> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(Neapolitan.MOD_ID, "banana_peel"), "main");
	private final ModelPart bananaPeel;

	public BananaPeelModel(ModelPart root) {
		this.bananaPeel = root.getChild("banana_peel");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		PartDefinition bananaPeel = root.addOrReplaceChild("banana_peel", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, false).texOffs(0, 12).addBox(1.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, false).texOffs(0, 14).addBox(-5.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, false).texOffs(0, 7).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 0.0F, 4.0F, false).texOffs(4, 7).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 0.0F, 4.0F, false), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(mesh, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bananaPeel.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}