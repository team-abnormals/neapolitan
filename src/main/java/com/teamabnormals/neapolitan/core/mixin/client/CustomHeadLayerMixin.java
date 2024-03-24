package com.teamabnormals.neapolitan.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomHeadLayer.class)
public abstract class CustomHeadLayerMixin<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {

	public CustomHeadLayerMixin(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V", ordinal = 2, shift = Shift.AFTER), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V")
	private void renderSkull(PoseStack stack, MultiBufferSource source, int p_116733_, T entity, float p_116735_, float p_116736_, float p_116737_, float p_116738_, float p_116739_, float p_116740_, CallbackInfo ci) {
		if (entity.getItemBySlot(EquipmentSlot.HEAD).is(NeapolitanItems.CHIMPANZEE_HEAD.get())) {
			stack.scale(1.0F, 1.0F, 1.28F);
		}
	}
}
