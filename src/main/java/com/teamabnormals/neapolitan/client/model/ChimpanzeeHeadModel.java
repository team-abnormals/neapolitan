package com.teamabnormals.neapolitan.client.model;

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
		CubeDeformation deformation = CubeDeformation.NONE;
		MeshDefinition mesh = SkullModel.createHeadModel();
		PartDefinition root = mesh.getRoot();
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 6.0F, deformation).texOffs(30, 11).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 5.0F, 1.0F, false), PartPose.ZERO);
		PartDefinition leftEar = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(25, 1).mirror().addBox(4.0F, -7.0F, -1.0F, 2.0F, 3.0F, 1.0F, deformation), PartPose.ZERO);
		PartDefinition rightEar = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(25, 1).addBox(-6.0F, -7.0F, -1.0F, 2.0F, 3.0F, 1.0F, deformation), PartPose.ZERO);

		return LayerDefinition.create(mesh, 64, 32);
	}
}