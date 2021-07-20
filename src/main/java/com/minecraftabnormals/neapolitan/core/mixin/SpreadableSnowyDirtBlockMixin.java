package com.minecraftabnormals.neapolitan.core.mixin;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpreadableSnowyDirtBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpreadableSnowyDirtBlock.class)
public abstract class SpreadableSnowyDirtBlockMixin {

	@Inject(at = @At("HEAD"), method = "canPropagate", cancellable = true)
	private static void isSnowyAndNotUnderwater(BlockState state, IWorldReader worldReader, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if (worldReader.getBlockState(pos.above()).is(NeapolitanBlocks.ADZUKI_SPROUTS.get()))
			cir.setReturnValue(false);
	}
}
