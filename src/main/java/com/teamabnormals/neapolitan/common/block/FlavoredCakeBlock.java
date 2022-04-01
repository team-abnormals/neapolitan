package com.teamabnormals.neapolitan.common.block;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.neapolitan.common.item.HealingItem;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class FlavoredCakeBlock extends CakeBlock {
	private final FoodProperties food;

	public FlavoredCakeBlock(FoodProperties food, Properties properties) {
		super(properties);
		this.food = food;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(handIn);
		Item item = stack.getItem();
		if (stack.is(ItemTags.CANDLES) && state.getValue(BITES) == 0) {
			Block block = Block.byItem(item);
			if (block instanceof CandleBlock && FlavoredCandleCakeBlock.hasEntry(block, this)) {
				if (!player.isCreative()) {
					stack.shrink(1);
				}

				worldIn.playSound(null, pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
				worldIn.setBlockAndUpdate(pos, FlavoredCandleCakeBlock.byCandle(block, this));
				worldIn.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
				player.awardStat(Stats.ITEM_USED.get(item));
				return InteractionResult.SUCCESS;
			}
		}

		if (worldIn.isClientSide) {
			ItemStack itemstack = player.getItemInHand(handIn);
			if (eatSlice(worldIn, pos, state, player).consumesAction()) {
				return InteractionResult.SUCCESS;
			}

			if (itemstack.isEmpty()) {
				return InteractionResult.CONSUME;
			}
		}

		return eatSlice(worldIn, pos, state, player);
	}

	public InteractionResult eatSlice(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
		if (!player.canEat(false)) {
			return InteractionResult.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(food.getNutrition(), food.getSaturationModifier());

			if (this == NeapolitanBlocks.STRAWBERRY_CAKE.get())
				HealingItem.applyHealing(1.0F, level, player);
			for (Pair<MobEffectInstance, Float> pair : food.getEffects()) {
				if (!level.isClientSide() && pair.getFirst() != null && level.getRandom().nextFloat() < pair.getSecond()) {
					player.addEffect(new MobEffectInstance(pair.getFirst()));
				}
			}

			int i = state.getValue(BITES);
			level.gameEvent(player, GameEvent.EAT, pos);
			if (i < 6) {
				level.setBlock(pos, state.setValue(BITES, i + 1), 3);
			} else {
				level.removeBlock(pos, false);
				level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
			}

			return InteractionResult.SUCCESS;
		}
	}
}