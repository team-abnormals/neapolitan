package com.minecraftabnormals.neapolitan.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecraftabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

@Mixin(BipedArmorLayer.class)
public abstract class BipedArmorLayerMixin<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends LayerRenderer<T, M> {
	private BipedArmorLayerMixin(IEntityRenderer<T, M> renderLayerParent) {
		super(renderLayerParent);
	}

	@Inject(method = "renderArmorPiece", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/model/BipedModel;copyPropertiesTo(Lnet/minecraft/client/renderer/entity/model/BipedModel;)V", shift = At.Shift.AFTER))
	public void translateChimpanzeeArmor(MatrixStack matrixStack, IRenderTypeBuffer buffer, T entity, EquipmentSlotType slot, int packedLight, A model, CallbackInfo ci) {
		if (this.getParentModel() instanceof ChimpanzeeModel) {
			if (slot == EquipmentSlotType.LEGS) {
				model.body.y -= 4F;
				model.leftLeg.x -= 0.1F;
				model.leftLeg.z += 0.1F;
				model.rightLeg.x += 0.1F;
				model.rightLeg.z += 0.1F;
			}
		}
	}
}
