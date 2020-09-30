package com.minecraftabnormals.neapolitan.client.model;

import com.minecraftabnormals.neapolitan.common.entity.MonkeyEntity;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MonkeyModel<E extends MonkeyEntity> extends BipedModel<E> {
	private final ModelRenderer tail;
	private final ModelRenderer rightear;
	private final ModelRenderer leftear;

	public MonkeyModel(float modelSize) {
		super(modelSize);
		textureWidth = 64;
		textureHeight = 32;

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, 17.0F, 2.0F);
		bipedBody.setTextureOffset(37, 0).addBox(-5.0F, 4.0F, -3.0F, 10.0F, 8.0F, 3.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -5.0F, 0.0F);
		bipedBody.addChild(tail);
		tail.setTextureOffset(53, 19).addBox(-1.0F, 15.0F, 0.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		bipedHead = new ModelRenderer(this);
		bipedHead.setRotationPoint(0.0F, 5.0F, -1.0F);
		bipedHead.setTextureOffset(1, 1).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);
		bipedHead.setTextureOffset(30, 11).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 5.0F, 1.0F, 0.0F, false);

		rightear = new ModelRenderer(this);
		rightear.setRotationPoint(-4.0F, -3.0F, 1.0F);
		bipedHead.addChild(rightear);
		rightear.setTextureOffset(25, 1).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		leftear = new ModelRenderer(this);
		leftear.setRotationPoint(4.0F, -5.0F, 1.0F);
		bipedHead.addChild(leftear);
		leftear.setTextureOffset(25, 1).addBox(0.0F, 2.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, true);

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(5.0F, 6.0F, 0.0F);
		bipedLeftArm.setTextureOffset(14, 17).addBox(0.0F, 2.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		bipedRightArm = new ModelRenderer(this);
		bipedRightArm.setRotationPoint(-5.0F, 6.0F, 0.0F);
		bipedRightArm.setTextureOffset(1, 17).addBox(-3.0F, 2.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		bipedLeftLeg = new ModelRenderer(this);
		bipedLeftLeg.setRotationPoint(1.0F, 13.0F, 0.0F);
		bipedLeftLeg.setTextureOffset(40, 17).addBox(0.0F, 0.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(-1.0F, 13.0F, 0.0F);
		bipedRightLeg.setTextureOffset(27, 17).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);
		
		bipedHeadwear.showModel = false;
	}
}