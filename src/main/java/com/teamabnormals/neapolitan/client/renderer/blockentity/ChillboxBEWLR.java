package com.teamabnormals.neapolitan.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.neapolitan.common.block.entity.ChillboxBlockEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ChillboxBEWLR extends BlockEntityWithoutLevelRenderer {
	private final ChillboxBlockEntity chillbox;

	public ChillboxBEWLR(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet, ChillboxBlockEntity be) {
		super(dispatcher, modelSet);
		this.chillbox = be;
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource source, int combinedLight, int combinedOverlay) {
		this.blockEntityRenderDispatcher.renderItem(this.chillbox, poseStack, source, combinedLight, combinedOverlay);
	}
}