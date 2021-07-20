package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.common.item.HealingItem;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import net.minecraft.block.AbstractBlock.Properties;

public class FlavoredCakeBlock extends CakeBlock {
	private EffectType effectType;
	private Food food;

	public FlavoredCakeBlock(Food food, EffectType effectType, Properties properties) {
		super(properties);
		this.effectType = effectType;
		this.food = food;
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isClientSide) {
			ItemStack itemstack = player.getItemInHand(handIn);
			if (this.eatSlice(worldIn, pos, state, player).consumesAction()) {
				return ActionResultType.SUCCESS;
			}

			if (itemstack.isEmpty()) {
				return ActionResultType.CONSUME;
			}
		}

		return this.eatSlice(worldIn, pos, state, player);
	}

	private ActionResultType eatSlice(IWorld world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!player.canEat(false)) {
			return ActionResultType.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(food.getNutrition(), food.getSaturationModifier());
			int i = state.getValue(BITES);

			if (this == NeapolitanBlocks.STRAWBERRY_CAKE.get())
				HealingItem.applyHealing(1.0F, world, player);

			for (Pair<EffectInstance, Float> pair : food.getEffects()) {
				if (!world.isClientSide() && pair.getFirst() != null && world.getRandom().nextFloat() < pair.getSecond()) {
					player.addEffect(new EffectInstance(pair.getFirst()));
				}
			}

			if (i < 6) {
				world.setBlock(pos, state.setValue(BITES, i + 1), 3);
			} else {
				world.removeBlock(pos, false);
			}

			return ActionResultType.SUCCESS;
		}
	}

	public EffectType getEffectType() {
		return this.effectType;
	}
}
