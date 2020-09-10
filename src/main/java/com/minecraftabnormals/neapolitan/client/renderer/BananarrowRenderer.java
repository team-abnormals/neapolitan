package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.common.entity.BananarrowEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class BananarrowRenderer extends ArrowRenderer<BananarrowEntity> {

	public BananarrowRenderer(EntityRendererManager manager) {
		super(manager);
	}

	public ResourceLocation getEntityTexture(BananarrowEntity entity) {
		return new ResourceLocation(Neapolitan.MODID, "textures/entity/projectiles/bananarrow.png");
	}
}