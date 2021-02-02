package com.minecraftabnormals.neapolitan.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;

import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {

	@Inject(at = @At("RETURN"), method = "getUseDuration", cancellable = true)
	private int getUseDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		return NeapolitanConfig.COMMON.milkBucketUseTime.get();
	}
}
