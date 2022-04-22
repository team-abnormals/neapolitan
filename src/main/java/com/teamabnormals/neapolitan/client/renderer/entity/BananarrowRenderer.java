package com.teamabnormals.neapolitan.client.renderer.entity;

import com.teamabnormals.neapolitan.common.entity.projectile.Bananarrow;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BananarrowRenderer extends ArrowRenderer<Bananarrow> {
	private static final ResourceLocation BANANARROW = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/projectiles/bananarrow.png");
	private static final ResourceLocation ARROW = new ResourceLocation("textures/entity/projectiles/arrow.png");

	public BananarrowRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public ResourceLocation getTextureLocation(Bananarrow entity) {
		return !entity.impacted ? BANANARROW : ARROW;
	}
}