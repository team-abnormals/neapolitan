package com.teamabnormals.neapolitan.core.mixin;

import com.teamabnormals.blueprint.core.other.tags.BlueprintEntityTypeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin {

	@Shadow
	private static boolean isStalactiteStartPos(BlockState state, LevelReader level, BlockPos pos) {
		return false;
	}

	@Shadow
	private static BlockPos findTip(BlockState state, LevelAccessor level, BlockPos pos, int num, boolean bool) {
		return null;
	}

	@Shadow
	private static BlockPos findFillableCauldronBelowStalactiteTip(Level level, BlockPos pos, Fluid fluid) {
		return null;
	}

	@Shadow
	private static boolean isStalactite(BlockState state) {
		return false;
	}

	@Shadow
	private static Optional<BlockPos> findRootBlock(Level level, BlockPos pos, BlockState state, int num) {
		return Optional.empty();
	}

	@Shadow
	private static Fluid getDripFluid(Level level, Fluid fluid) {
		return null;
	}

	@Inject(at = @At("RETURN"), method = "canFillCauldron", cancellable = true)
	private static void canFillCauldron(Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
		if (fluid == ForgeMod.MILK.get())
			cir.setReturnValue(true);
	}

	@Inject(at = @At("RETURN"), method = "getFluidAboveStalactite", cancellable = true)
	private static void getFluidAboveStalactite(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Optional<Fluid>> cir) {
		Optional<List<Entity>> entities = findRootBlock(level, pos, state, 11).map((newPos) -> level.getEntities(EntityTypeTest.forClass(Entity.class), new AABB(newPos.above()), (entity) -> entity.getType().is(BlueprintEntityTypeTags.MILKABLE)));
		if (isStalactite(state) && entities.isPresent() && !entities.get().isEmpty()) {
			cir.setReturnValue(Optional.of(ForgeMod.MILK.get()));
		}
	}

	@ModifyVariable(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", shift = At.Shift.BY, by = -10), index = 14, method = "spawnDripParticle(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/material/Fluid;)V")
	private static ParticleOptions spawnDripParticle(ParticleOptions options, Level level, BlockPos pos, BlockState state, Fluid fluid) {
		Fluid dripFluid = getDripFluid(level, fluid);
		if (dripFluid != null && dripFluid.is(Tags.Fluids.MILK)) {
			options = NeapolitanParticles.DRIPPING_DRIPSTONE_MILK.get();
		}

		return options;
	}

	@Inject(at = @At("HEAD"), method = "maybeFillCauldron")
	private static void maybeFillCauldron(BlockState state, ServerLevel level, BlockPos pos, float chance, CallbackInfo ci) {
		if (!(chance > 0.17578125F) || !(chance > 0.05859375F)) {
			if (isStalactiteStartPos(state, level, pos)) {
				Fluid fluid = PointedDripstoneBlock.getCauldronFillFluidType(level, pos);
				if (fluid == ForgeMod.MILK.get()) {
					if (!(chance >= 0.1171875F)) {
						BlockPos tipPos = findTip(state, level, pos, 11, false);
						if (tipPos != null) {
							BlockPos cauldronPos = findFillableCauldronBelowStalactiteTip(level, tipPos, fluid);
							if (cauldronPos != null) {
								level.levelEvent(1504, tipPos, 0);
								int i = tipPos.getY() - cauldronPos.getY();
								int j = 50 + i;
								BlockState blockstate = level.getBlockState(cauldronPos);
								level.scheduleTick(cauldronPos, blockstate.getBlock(), j);
							}
						}
					}
				}
			}
		}
	}
}
