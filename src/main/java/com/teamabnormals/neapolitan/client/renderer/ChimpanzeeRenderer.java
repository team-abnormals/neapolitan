package com.teamabnormals.neapolitan.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.client.renderer.layers.*;
import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeTypes;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeRenderer extends MobRenderer<ChimpanzeeEntity, ChimpanzeeModel<ChimpanzeeEntity>> {

	public ChimpanzeeRenderer(EntityRendererProvider.Context context) {
		super(context, new ChimpanzeeModel<>(ChimpanzeeModel.createLayerDefinition().bakeRoot()), 0.4F);
		this.addLayer(new HumanoidArmorLayer<>(this, new ChimpanzeeModel<>(ChimpanzeeModel.createLayerDefinition().bakeRoot()), new ChimpanzeeModel<>(ChimpanzeeModel.createLayerDefinition().bakeRoot())));
		this.addLayer(new ChimpanzeePaleSkinLayer<>(this));
		this.addLayer(new ChimpanzeeDirtLayer<>(this));
		this.addLayer(new ChimpanzeeDyeLayer<>(this));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet()));
		this.addLayer(new ChimpanzeeItemLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(ChimpanzeeEntity entity) {
		ChimpanzeeTypes type = ChimpanzeeTypes.byId(entity.getChimpanzeeType());
		String textureend = entity.isMouthOpen() ? "_chimpanzee_mouth_open.png" : "_chimpanzee.png";
		return new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/" + type.name().toLowerCase(Locale.ROOT) + textureend);
	}

	@Override
	public void render(ChimpanzeeEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	protected boolean isShaking(ChimpanzeeEntity entityIn) {
		return entityIn.getApeModeTime() > 0;
	}
}