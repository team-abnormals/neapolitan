package com.teamabnormals.neapolitan.core.mixin;

import com.teamabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.teamabnormals.neapolitan.common.block.StrawberryBushBlock.StrawberryType;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fox.FoxEatBerriesGoal.class)
public abstract class FoxEatBerriesGoalMixin extends MoveToBlockGoal {

	public FoxEatBerriesGoalMixin(PathfinderMob mob, double speedModifier, int searchRange) {
		super(mob, speedModifier, searchRange);
	}

	@Inject(at = @At("HEAD"), method = "onReachedTarget")
	private void onReachedTarget(CallbackInfo ci) {
		if (ForgeEventFactory.getMobGriefingEvent(this.mob.level, this.mob)) {
			BlockState state = this.mob.level.getBlockState(this.blockPos);
			if (state.is(NeapolitanBlocks.STRAWBERRY_BUSH.get())) {
				state.setValue(StrawberryBushBlock.AGE, 1);

				Item strawberry = state.getValue(StrawberryBushBlock.TYPE) == StrawberryType.WHITE ? NeapolitanItems.WHITE_STRAWBERRIES.get() : NeapolitanItems.STRAWBERRIES.get();
				int strawberryCount = 1 + this.mob.level.random.nextInt(2);

				ItemStack mainStack = this.mob.getItemBySlot(EquipmentSlot.MAINHAND);
				if (mainStack.isEmpty()) {
					this.mob.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(strawberry));
					--strawberryCount;
				}
				if (strawberryCount > 0) {
					Block.popResource(this.mob.level, this.blockPos, new ItemStack(strawberry, strawberryCount));
				}

				this.mob.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, 1.0F);
				this.mob.level.setBlock(this.blockPos, state.setValue(StrawberryBushBlock.AGE, 1).setValue(StrawberryBushBlock.TYPE, StrawberryType.NONE), 2);
			}
		}
	}

	@Inject(at = @At("RETURN"), method = "isValidTarget", cancellable = true)
	private void isValidTarget(LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		BlockState state = level.getBlockState(pos);
		if (state.is(NeapolitanBlocks.STRAWBERRY_BUSH.get()) && state.getValue(StrawberryBushBlock.AGE) == 6) {
			cir.setReturnValue(true);
		}
	}
}
