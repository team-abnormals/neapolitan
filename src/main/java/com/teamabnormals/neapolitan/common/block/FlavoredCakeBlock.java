package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.common.item.HealingItem;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class FlavoredCakeBlock extends CakeBlock {
	private final MobEffectCategory effectType;
	private final FoodProperties food;

	public FlavoredCakeBlock(FoodProperties food, MobEffectCategory effectType, Properties properties) {
		super(properties);
		this.effectType = effectType;
		this.food = food;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (worldIn.isClientSide) {
			ItemStack itemstack = player.getItemInHand(handIn);
			if (this.eatSlice(worldIn, pos, state, player).consumesAction()) {
				return InteractionResult.SUCCESS;
			}

			if (itemstack.isEmpty()) {
				return InteractionResult.CONSUME;
			}
		}

		return this.eatSlice(worldIn, pos, state, player);
	}

	private InteractionResult eatSlice(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
		if (!player.canEat(false)) {
			return InteractionResult.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(food.getNutrition(), food.getSaturationModifier());
			int i = state.getValue(BITES);

			if (this == NeapolitanBlocks.STRAWBERRY_CAKE.get())
				HealingItem.applyHealing(1.0F, world, player);

			for (Pair<MobEffectInstance, Float> pair : food.getEffects()) {
				if (!world.isClientSide() && pair.getFirst() != null && world.getRandom().nextFloat() < pair.getSecond()) {
					player.addEffect(new MobEffectInstance(pair.getFirst()));
				}
			}

			if (i < 6) {
				world.setBlock(pos, state.setValue(BITES, i + 1), 3);
			} else {
				world.removeBlock(pos, false);
			}

			return InteractionResult.SUCCESS;
		}
	}

	public MobEffectCategory getEffectType() {
		return this.effectType;
	}
}
