package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.api.BlueprintCauldronInteraction;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class NeapolitanCauldronInteractions {
	public static BlueprintCauldronInteraction MILK = BlueprintCauldronInteraction.register(new ResourceLocation(Neapolitan.MOD_ID, "milk"), CauldronInteraction.newInteractionMap());
	public static BlueprintCauldronInteraction VANILLA_MILKSHAKE = BlueprintCauldronInteraction.register(new ResourceLocation(Neapolitan.MOD_ID, "vanilla_milkshake"), CauldronInteraction.newInteractionMap());
	public static BlueprintCauldronInteraction CHOCOLATE_MILKSHAKE = BlueprintCauldronInteraction.register(new ResourceLocation(Neapolitan.MOD_ID, "chocolate_milkshake"), CauldronInteraction.newInteractionMap());
	public static BlueprintCauldronInteraction STRAWBERRY_MILKSHAKE = BlueprintCauldronInteraction.register(new ResourceLocation(Neapolitan.MOD_ID, "strawberry_milkshake"), CauldronInteraction.newInteractionMap());
	public static BlueprintCauldronInteraction BANANA_MILKSHAKE = BlueprintCauldronInteraction.register(new ResourceLocation(Neapolitan.MOD_ID, "banana_milkshake"), CauldronInteraction.newInteractionMap());
	public static BlueprintCauldronInteraction MINT_MILKSHAKE = BlueprintCauldronInteraction.register(new ResourceLocation(Neapolitan.MOD_ID, "mint_milkshake"), CauldronInteraction.newInteractionMap());
	public static BlueprintCauldronInteraction ADZUKI_MILKSHAKE = BlueprintCauldronInteraction.register(new ResourceLocation(Neapolitan.MOD_ID, "adzuki_milkshake"), CauldronInteraction.newInteractionMap());

	public static void registerCauldronInteractions() {
		if (NeapolitanConfig.COMMON.milkCauldron.get()) {
			BlueprintCauldronInteraction.addMoreDefaultInteractions(Items.MILK_BUCKET, FILL_MILK);
			MILK.map().put(Items.BUCKET, (state, level, pos, player, hand, stack) -> CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(Items.MILK_BUCKET), (cauldronState) -> cauldronState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL));
			addMilkInteractions(NeapolitanItems.MILK_BOTTLE.get(), NeapolitanBlocks.MILK_CAULDRON.get(), MILK.map());
		}

		if (NeapolitanConfig.COMMON.milkshakeCauldrons.get()) {
			addMilkshakeInteractions(NeapolitanItems.VANILLA_MILKSHAKE.get(), NeapolitanBlocks.VANILLA_MILKSHAKE_CAULDRON.get(), NeapolitanItems.VANILLA_ICE_CREAM.get(), VANILLA_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.CHOCOLATE_MILKSHAKE.get(), NeapolitanBlocks.CHOCOLATE_MILKSHAKE_CAULDRON.get(), NeapolitanItems.CHOCOLATE_ICE_CREAM.get(), CHOCOLATE_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.STRAWBERRY_MILKSHAKE.get(), NeapolitanBlocks.STRAWBERRY_MILKSHAKE_CAULDRON.get(), NeapolitanItems.STRAWBERRY_ICE_CREAM.get(), STRAWBERRY_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.BANANA_MILKSHAKE.get(), NeapolitanBlocks.BANANA_MILKSHAKE_CAULDRON.get(), NeapolitanItems.BANANA_ICE_CREAM.get(), BANANA_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.MINT_MILKSHAKE.get(), NeapolitanBlocks.MINT_MILKSHAKE_CAULDRON.get(), NeapolitanItems.MINT_ICE_CREAM.get(), MINT_MILKSHAKE.map());
			addMilkshakeInteractions(NeapolitanItems.ADZUKI_MILKSHAKE.get(), NeapolitanBlocks.ADZUKI_MILKSHAKE_CAULDRON.get(), NeapolitanItems.ADZUKI_ICE_CREAM.get(), ADZUKI_MILKSHAKE.map());
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

			return InteractionResult.sidedSuccess(level.isClientSide);
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
				return InteractionResult.sidedSuccess(level.isClientSide);
			} else {
				return InteractionResult.PASS;
			}
		});
		CauldronInteraction.EMPTY.put(filledBottle, (state, level, pos, player, hand, stack) -> {
			if (!level.isClientSide) {
				Item item = stack.getItem();
				player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				level.setBlockAndUpdate(pos, filledCauldron.defaultBlockState());
				level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		});
	}

	public static void addMilkshakeInteractions(Item filledBottle, Block filledCauldron, Item iceCream, Map<Item, CauldronInteraction> map) {
		addMilkInteractions(filledBottle, filledCauldron, map);
		if (NeapolitanConfig.COMMON.milkCauldron.get()) {
			MILK.map().put(iceCream, (state, level, pos, player, hand, stack) -> {
				if (!level.isClientSide) {
					Item item = stack.getItem();
					player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BOWL)));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					level.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(level.getBlockState(pos), filledCauldron.defaultBlockState()));
					level.playSound(null, pos, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
				}
				return InteractionResult.sidedSuccess(level.isClientSide);
			});
		}
	}
}
