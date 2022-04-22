package com.teamabnormals.neapolitan.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.client.renderer.entity.layers.ChimpanzeeDirtLayer;
import com.teamabnormals.neapolitan.client.renderer.entity.layers.ChimpanzeeDyeLayer;
import com.teamabnormals.neapolitan.client.renderer.entity.layers.ChimpanzeeItemLayer;
import com.teamabnormals.neapolitan.client.renderer.entity.layers.ChimpanzeePaleSkinLayer;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeTypes;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class ChimpanzeeRenderer extends MobRenderer<Chimpanzee, ChimpanzeeModel<Chimpanzee>> {

	public ChimpanzeeRenderer(EntityRendererProvider.Context context) {
		super(context, new ChimpanzeeModel<>(context.bakeLayer(NeapolitanModelLayers.CHIMPANZEE)), 0.4F);
		this.addLayer(new HumanoidArmorLayer<>(this, new ChimpanzeeModel<>(context.bakeLayer(NeapolitanModelLayers.CHIMPANZEE_INNER_ARMOR)), new ChimpanzeeModel<>(context.bakeLayer(NeapolitanModelLayers.CHIMPANZEE_OUTER_ARMOR))));
		this.addLayer(new ChimpanzeePaleSkinLayer<>(this));
		this.addLayer(new ChimpanzeeDirtLayer<>(this));
		this.addLayer(new ChimpanzeeDyeLayer<>(this));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet()));
		this.addLayer(new ChimpanzeeItemLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Chimpanzee chimpanzee) {
		ChimpanzeeTypes type = ChimpanzeeTypes.byId(chimpanzee.getChimpanzeeType());
		String textureend = chimpanzee.isMouthOpen() ? "_chimpanzee_mouth_open.png" : "_chimpanzee.png";
		return new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/" + type.name().toLowerCase(Locale.ROOT) + textureend);
	}

	@Override
	protected void setupRotations(Chimpanzee chimpanzee, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(chimpanzee, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = chimpanzee.getFlipAnim(partialTicks);
		if (f > 0.0F) {
			float f1 = Mth.PI * f / 10F;
			matrixStackIn.translate(0.0D, -Mth.cos(f1) * 0.8D + 0.8D, Mth.sin(f1) * 0.8D);
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-f * 18F));
		}
	}

	@Override
	protected boolean isShaking(Chimpanzee entityIn) {
		return entityIn.getApeModeTime() > 0;
	}
}