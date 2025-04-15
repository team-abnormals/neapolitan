package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.common.item.HealingItem;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.PossibleEffect;
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
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		Item item = stack.getItem();
		if (stack.is(ItemTags.CANDLES) && state.getValue(BITES) == 0 && Block.byItem(item) instanceof CandleBlock candleblock && FlavoredCandleCakeBlock.hasEntry(candleblock, this)) {
			stack.consume(1, player);
			level.playSound(null, pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlockAndUpdate(pos, FlavoredCandleCakeBlock.byCandle(candleblock, this));
			level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
			player.awardStat(Stats.ITEM_USED.get(item));
			return ItemInteractionResult.SUCCESS;
		} else {
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.isClientSide()) {
			if (eatSlice(level, pos, state, player).consumesAction()) {
				return InteractionResult.SUCCESS;
			}

			if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
				return InteractionResult.CONSUME;
			}
		}

		return eatSlice(level, pos, state, player);
	}

	public InteractionResult eatSlice(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
		if (!player.canEat(false)) {
			return InteractionResult.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(food.nutrition(), food.saturation());

			if (this == NeapolitanBlocks.STRAWBERRY_CAKE.get())
				HealingItem.applyHealing(1.0F, level, player);
			for (PossibleEffect possibleEffect : food.effects()) {
				if (!level.isClientSide() && level.getRandom().nextFloat() < possibleEffect.probability()) {
					player.addEffect(new MobEffectInstance(possibleEffect.effect()));
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