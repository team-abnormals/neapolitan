package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.client.model.ChimpanzeeModel;
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
	}

	@Override
	public ResourceLocation getEntityTexture(ChimpanzeeEntity entity) {
		int type = entity.getChimpanzeeType();
		boolean hungry = entity.func_233678_J__();

		if (type == 1) return !hungry ? RAINFOREST_CHIMPANZEE : HUNGRY_RAINFOREST_CHIMPANZEE;
		if (type == 2) return !hungry ? BAMBOO_CHIMPANZEE : HUNGRY_BAMBOO_CHIMPANZEE;
		return !hungry ? CHIMPANZEE : HUNGRY_CHIMPANZEE;

	}

	@Override
	public void render(ChimpanzeeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
}