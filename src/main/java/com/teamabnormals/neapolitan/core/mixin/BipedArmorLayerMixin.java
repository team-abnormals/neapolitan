package com.teamabnormals.neapolitan.core.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class BipedArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
	private BipedArmorLayerMixin(RenderLayerParent<T, M> renderLayerParent) {
		super(renderLayerParent);
	}

	@Inject(method = "renderArmorPiece", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/HumanoidModel;copyPropertiesTo(Lnet/minecraft/client/model/HumanoidModel;)V", shift = At.Shift.AFTER))
	public void translateChimpanzeeArmor(PoseStack matrixStack, MultiBufferSource buffer, T entity, EquipmentSlot slot, int packedLight, A model, CallbackInfo ci) {
		if (this.getParentModel() instanceof ChimpanzeeModel) {
			if (slot == EquipmentSlot.LEGS) {
				model.body.y -= 4F;
				model.leftLeg.x -= 0.1F;
				model.leftLeg.z += 0.1F;
				model.rightLeg.x += 0.1F;
				model.rightLeg.z += 0.1F;
			}
		}
	}
}
