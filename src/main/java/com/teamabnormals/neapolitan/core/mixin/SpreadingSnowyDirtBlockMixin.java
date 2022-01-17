package com.teamabnormals.neapolitan.core.mixin;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpreadingSnowyDirtBlock.class)
public abstract class SpreadingSnowyDirtBlockMixin {

	@Inject(at = @At("HEAD"), method = "canPropagate", cancellable = true)
	private static void canPropagate(BlockState state, LevelReader worldReader, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if (worldReader.getBlockState(pos.above()).is(NeapolitanBlocks.ADZUKI_SPROUTS.get()))
			cir.setReturnValue(false);
	}
}
