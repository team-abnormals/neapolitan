package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDataComponents;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteraction.InteractionMap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

public class NeapolitanCauldronInteractions {
	public static InteractionMap MILK = CauldronInteraction.newInteractionMap("neapolitan:milk");
	public static InteractionMap VANILLA_MILKSHAKE = CauldronInteraction.newInteractionMap("neapolitan:vanilla_milkshake");
	public static InteractionMap CHOCOLATE_MILKSHAKE = CauldronInteraction.newInteractionMap("neapolitan:chocolate_milkshake");
	public static InteractionMap STRAWBERRY_MILKSHAKE = CauldronInteraction.newInteractionMap("neapolitan:strawberry_milkshake");
	public static InteractionMap BANANA_MILKSHAKE = CauldronInteraction.newInteractionMap("neapolitan:banana_milkshake");
	public static InteractionMap MINT_MILKSHAKE = CauldronInteraction.newInteractionMap("neapolitan:mint_milkshake");
	public static InteractionMap ADZUKI_MILKSHAKE = CauldronInteraction.newInteractionMap("neapolitan:adzuki_milkshake");

	public static void registerCauldronInteractions() {
		if (NeapolitanConfig.COMMON.milkCauldron.get()) {
			DataUtil.addDefaultCauldronInteraction(Items.MILK_BUCKET, FILL_MILK);
			MILK.map().put(Items.BUCKET, (state, level, pos, player, hand, stack) -> CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(Items.MILK_BUCKET), (cauldronState) -> cauldronState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL));
			addMilkInteractions(NeapolitanItems.MILK_BOTTLE.get(), NeapolitanBlocks.MILK_CAULDRON.get(), MILK.map());
		}

		if (NeapolitanConfig.COMMON.milkshakeCauldrons.get()) {
			addMilkshakeInteractions(NeapolitanItems.VANILLA_MILKSHAKE.get(), NeapolitanBlocks.VANILLA_MILKSHAKE_CAULDRON.get(), VANILLA_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.CHOCOLATE_MILKSHAKE.get(), NeapolitanBlocks.CHOCOLATE_MILKSHAKE_CAULDRON.get(), CHOCOLATE_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.STRAWBERRY_MILKSHAKE.get(), NeapolitanBlocks.STRAWBERRY_MILKSHAKE_CAULDRON.get(), STRAWBERRY_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.BANANA_MILKSHAKE.get(), NeapolitanBlocks.BANANA_MILKSHAKE_CAULDRON.get(), BANANA_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.MINT_MILKSHAKE.get(), NeapolitanBlocks.MINT_MILKSHAKE_CAULDRON.get(), MINT_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.ADZUKI_MILKSHAKE.get(), NeapolitanBlocks.ADZUKI_MILKSHAKE_CAULDRON.get(), ADZUKI_MILKSHAKE.map());

			if (NeapolitanConfig.COMMON.milkCauldron.get()) {
				MILK.map().put(NeapolitanItems.ICE_CREAM.get(), emptyIceCream(Items.BOWL));
				MILK.map().put(NeapolitanItems.ICE_CREAM_CONE.get(), emptyIceCream(NeapolitanItems.WAFFLE_CONE.get()));
			}
		}
	}

	public static final CauldronInteraction FILL_MILK = (state, level, pos, player, hand, stack) -> CauldronInteraction.emptyBucket(level, pos, player, hand, stack, NeapolitanBlocks.MILK_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY);

	public static void addMilkInteractions(Item filledBottle, Block filledCauldron, Map<Item, CauldronInteraction> map) {
		CauldronInteraction.addDefaultInteractions(map);
		map.put(Items.GLASS_BOTTLE, (state, level, pos, player, hand, stack) -> {
			if (!level.isClientSide) {
				Item item = stack.getItem();
				player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(filledBottle)));
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				LayeredCauldronBlock.lowerFillLevel(state, level, pos);
				level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}

			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		});
		map.put(filledBottle, (state, level, pos, player, hand, stack) -> {
			if (state.getValue(LayeredCauldronBlock.LEVEL) != 3) {
				if (!level.isClientSide) {
					player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
					level.setBlockAndUpdate(pos, state.cycle(LayeredCauldronBlock.LEVEL));
					level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
				}
				return ItemInteractionResult.sidedSuccess(level.isClientSide);
			} else {
				return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			}
		});
		CauldronInteraction.EMPTY.map().put(filledBottle, (state, level, pos, player, hand, stack) -> {
			if (!level.isClientSide) {
				Item item = stack.getItem();
				player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				level.setBlockAndUpdate(pos, filledCauldron.defaultBlockState());
				level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
			}
			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		});
	}

	public static void addMilkshakeInteractions(Item filledBottle, Block filledCauldron, Map<Item, CauldronInteraction> map) {
		addMilkInteractions(filledBottle, filledCauldron, map);
	}

	public static CauldronInteraction emptyIceCream(Item returnItem) {
		return (state, level, pos, player, hand, stack) -> {
			for (Reference<IceCreamFlavor> flavor : level.registryAccess().registryOrThrow(NeapolitanRegistries.ICE_CREAM_FLAVOR).holders().toList()) {
				if (stack.has(NeapolitanDataComponents.ICE_CREAM) && stack.get(NeapolitanDataComponents.ICE_CREAM).is(flavor.key()) && flavor.value().milkshakeCauldron().isPresent()) {
					if (!level.isClientSide) {
						Item item = stack.getItem();
						player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(returnItem)));
						player.awardStat(Stats.USE_CAULDRON);
						player.awardStat(Stats.ITEM_USED.get(item));
						level.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(level.getBlockState(pos), flavor.value().milkshakeCauldron().get().value().defaultBlockState()));
						level.playSound(null, pos, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);
						level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
					}
					return ItemInteractionResult.sidedSuccess(level.isClientSide);
				}
			}
			return ItemInteractionResult.FAIL;
		};
	}
}
