package com.teamabnormals.neapolitan.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeHeadModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkullBlockRenderer.class)
public abstract class SkullBlockRendererMixin {

	@Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V", ordinal = 1, shift = Shift.AFTER), method = "renderSkull")
	private static void renderSkull(Direction direction, float p_173665_, float p_173666_, PoseStack stack, MultiBufferSource p_173668_, int p_173669_, SkullModelBase model, RenderType type, CallbackInfo ci) {
		if (model instanceof ChimpanzeeHeadModel) {
			stack.translate(direction.getStepX() * -0.0625F, 0.0F, direction.getStepZ() * -0.0625F);
		}
	}
}
