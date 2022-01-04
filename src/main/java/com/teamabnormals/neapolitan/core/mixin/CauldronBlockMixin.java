package com.teamabnormals.neapolitan.core.mixin;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CauldronBlock.class)
public abstract class CauldronBlockMixin {

	@Inject(at = @At("HEAD"), method = "receiveStalactiteDrip")
	private void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid, CallbackInfo ci) {
		if (fluid == ForgeMod.MILK.get()) {
			level.setBlockAndUpdate(pos, NeapolitanBlocks.MILK_CAULDRON.get().defaultBlockState());
			level.levelEvent(1047, pos, 0);
			level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
		}
	}
}
