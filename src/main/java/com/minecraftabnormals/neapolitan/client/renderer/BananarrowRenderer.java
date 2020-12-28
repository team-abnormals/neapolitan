package com.minecraftabnormals.neapolitan.client.renderer;

import com.minecraftabnormals.neapolitan.common.entity.BananarrowEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class BananarrowRenderer extends ArrowRenderer<BananarrowEntity> {
	private static final ResourceLocation BANANARROW = new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/projectiles/bananarrow.png");
	private static final ResourceLocation ARROW = new ResourceLocation("minecraft", "textures/entity/projectiles/arrow.png");

	public BananarrowRenderer(EntityRendererManager manager) {
		super(manager);
	}

	public ResourceLocation getEntityTexture(BananarrowEntity entity) {
		return !entity.impacted ? BANANARROW : ARROW;
	}
}