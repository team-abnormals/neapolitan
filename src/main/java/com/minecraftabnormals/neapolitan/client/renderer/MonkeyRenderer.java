package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.client.model.MonkeyModel;
import com.minecraftabnormals.neapolitan.client.renderer.layer.MonkeyEyesLayer;
import com.minecraftabnormals.neapolitan.common.entity.MonkeyEntity;
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
public class MonkeyRenderer extends MobRenderer<MonkeyEntity, MonkeyModel<MonkeyEntity>> {
	public static final ResourceLocation MONKEY = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/monkey/monkey.png");
	public static final ResourceLocation HUNGRY_MONKEY = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/monkey/monkey_hungry.png");
	
	public static final ResourceLocation RAINFOREST_MONKEY = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/monkey/rainforest_monkey.png");
	public static final ResourceLocation HUNGRY_RAINFOREST_MONKEY = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/monkey/rainforest_monkey_hungry.png");
	
	public static final ResourceLocation BAMBOO_MONKEY = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/monkey/bamboo_monkey.png");
	public static final ResourceLocation HUNGRY_BAMBOO_MONKEY = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/monkey/bamboo_monkey_hungry.png");

	public MonkeyRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MonkeyModel<>(), 0.4F);
		this.addLayer(new HeadLayer<>(this));
		this.addLayer(new MonkeyEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(MonkeyEntity entity) {
		int type = entity.getMonkeyType();
		boolean hungry = entity.func_233678_J__();
		
		if (type == 1) return !hungry ? RAINFOREST_MONKEY : HUNGRY_RAINFOREST_MONKEY;
		if (type == 2) return !hungry ? BAMBOO_MONKEY : HUNGRY_BAMBOO_MONKEY;
		return !hungry ? MONKEY : HUNGRY_MONKEY;

	}

	@Override
	public void render(MonkeyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
}