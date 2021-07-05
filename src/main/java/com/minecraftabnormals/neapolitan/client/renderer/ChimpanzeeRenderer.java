package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.minecraftabnormals.neapolitan.client.renderer.layer.ChimpanzeeItemLayer;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeRenderer extends MobRenderer<ChimpanzeeEntity, ChimpanzeeModel<ChimpanzeeEntity>> {
	public static final ResourceLocation CHIMPANZEE = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee.png");
	public static final ResourceLocation HUNGRY_CHIMPANZEE = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/chimpanzee_hungry.png");

	public static final ResourceLocation RAINFOREST_CHIMPANZEE = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/rainforest_chimpanzee.png");
	public static final ResourceLocation HUNGRY_RAINFOREST_CHIMPANZEE = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/rainforest_chimpanzee_hungry.png");

	public static final ResourceLocation BAMBOO_CHIMPANZEE = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/bamboo_chimpanzee.png");
	public static final ResourceLocation HUNGRY_BAMBOO_CHIMPANZEE = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/bamboo_chimpanzee_hungry.png");

	public ChimpanzeeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ChimpanzeeModel<>(), 0.4F);
		this.addLayer(new HeadLayer<>(this));
		this.addLayer(new ChimpanzeeItemLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ChimpanzeeEntity entity) {
		int type = entity.getChimpanzeeType();
		boolean mouthopen = this.getMouthOpen(entity);

		if (type == 1) return !mouthopen ? RAINFOREST_CHIMPANZEE : HUNGRY_RAINFOREST_CHIMPANZEE;
		if (type == 2) return !mouthopen ? BAMBOO_CHIMPANZEE : HUNGRY_BAMBOO_CHIMPANZEE;
		return !mouthopen ? CHIMPANZEE : HUNGRY_CHIMPANZEE;

	}

	@Override
	public void render(ChimpanzeeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	private boolean getMouthOpen(ChimpanzeeEntity entity) {
		if (entity.getAction() == ChimpanzeeEntity.Action.EATING) {
			return Math.sin(Math.PI * entity.ticksExisted * 0.2D) > 0;
		} else if (entity.func_233678_J__() || entity.isTempting() || entity.isHungry() || entity.isPartying()) {
			return true;
		} else {
			return false;
		}
	}
}