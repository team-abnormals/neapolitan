package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.common.block.MilkCauldronBlock;
import com.teamabnormals.neapolitan.common.entity.projectile.Bananarrow;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Position;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

public class NeapolitanCompat {

	public static void transformCookies() {
		Foods.COOKIE.fastFood = true;
		Foods.COOKIE.saturationModifier = 0.3F;
	}

	public static void registerCompat() {
		registerCompostables();
		registerFlammables();
		registerDispenserBehaviors();
		registerItemProperties();
		registerCauldronInteractions();
	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRY_PIPS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRIES.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.WHITE_STRAWBERRIES.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRY_SCONES.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRY_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.CHOCOLATE_BAR.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.CHOCOLATE_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.VANILLA_PODS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.DRIED_VANILLA_PODS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.VANILLA_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.BANANA.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.BANANA_BUNCH.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.DRIED_BANANA.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.BANANA_BREAD.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanItems.BANANA_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.MINT_SPROUT.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.MINT_LEAVES.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.MINT_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.ADZUKI_BEANS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.ROASTED_ADZUKI_BEANS.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.ADZUKI_BUN.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanItems.ADZUKI_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.VANILLA_CHOCOLATE_FINGERS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanItems.CHOCOLATE_STRAWBERRIES.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanItems.MINT_CHOCOLATE.get(), 0.85F);

		DataUtil.registerCompostable(NeapolitanBlocks.VANILLA_POD_BLOCK.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.get(), 0.5F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_BUNDLE.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_STALK.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CARVED_BANANA_STALK.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_FROND.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_SLAB.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), 0.65F);

		DataUtil.registerCompostable(NeapolitanBlocks.BEANSTALK.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.BEANSTALK_THORNS.get(), 0.5F);

		DataUtil.registerCompostable(NeapolitanBlocks.STRAWBERRY_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.MINT_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.ADZUKI_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.ROASTED_ADZUKI_CRATE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICKS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_VERTICAL_SLAB.get(), 0.3F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), 0.85F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILES.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_VERTICAL_SLAB.get(), 0.3F);
	}

	public static void registerFlammables() {
		DataUtil.registerFlammable(NeapolitanBlocks.VANILLA_VINE.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.VANILLA_POD_BLOCK.get(), 30, 60);
		DataUtil.registerFlammable(NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.get(), 30, 60);

		DataUtil.registerFlammable(NeapolitanBlocks.STRAWBERRY_BUSH.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.STRAWBERRY_BASKET.get(), 5, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), 5, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.MINT.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.MINT_BASKET.get(), 5, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.ADZUKI_SPROUTS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.ADZUKI_CRATE.get(), 5, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.ROASTED_ADZUKI_CRATE.get(), 5, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.BANANA_STALK.get(), 5, 5);
		DataUtil.registerFlammable(NeapolitanBlocks.SMALL_BANANA_FROND.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.BANANA_FROND.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.LARGE_BANANA_FROND.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH.get(), 60, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), 60, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH_SLAB.get(), 60, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), 60, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICKS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_VERTICAL_SLAB.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILES.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_VERTICAL_SLAB.get(), 60, 100);
	}

	public static void registerItemProperties() {
		ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Neapolitan.MOD_ID, "bananarrow"), (stack, world, entity, i) -> {
			return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, NeapolitanItems.BANANARROW.get()) ? 1.0F : 0.0F;
		});
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(NeapolitanItems.BANANARROW.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
				return new Bananarrow(worldIn, position.x(), position.y(), position.z());
			}
		});
	}

	public static void registerCauldronInteractions() {
		if(NeapolitanConfig.COMMON.milkCauldron.get()) {
			registerMilkCauldronInteractions(NeapolitanItems.MILK_BOTTLE.get(), NeapolitanBlocks.MILK_CAULDRON.get(), MilkCauldronBlock.MILK);
			CauldronInteraction.EMPTY.put(Items.MILK_BUCKET, MilkCauldronBlock.FILL_MILK);
			CauldronInteraction.WATER.put(Items.MILK_BUCKET, MilkCauldronBlock.FILL_MILK);
			CauldronInteraction.LAVA.put(Items.MILK_BUCKET, MilkCauldronBlock.FILL_MILK);
			CauldronInteraction.POWDER_SNOW.put(Items.MILK_BUCKET, MilkCauldronBlock.FILL_MILK);
			MilkCauldronBlock.MILK.put(Items.MILK_BUCKET, MilkCauldronBlock.FILL_MILK);
			MilkCauldronBlock.MILK.put(Items.BUCKET, (state, level, pos, player, hand, stack) -> CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(Items.MILK_BUCKET), (cauldronState) -> cauldronState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL));
		}

		registerMilkCauldronInteractions(NeapolitanItems.VANILLA_MILKSHAKE.get(), NeapolitanBlocks.VANILLA_MILKSHAKE_CAULDRON.get(), MilkCauldronBlock.VANILLA_MILKSHAKE);
		registerMilkCauldronInteractions(NeapolitanItems.CHOCOLATE_MILKSHAKE.get(), NeapolitanBlocks.CHOCOLATE_MILKSHAKE_CAULDRON.get(), MilkCauldronBlock.CHOCOLATE_MILKSHAKE);
		registerMilkCauldronInteractions(NeapolitanItems.STRAWBERRY_MILKSHAKE.get(), NeapolitanBlocks.STRAWBERRY_MILKSHAKE_CAULDRON.get(), MilkCauldronBlock.STRAWBERRY_MILKSHAKE);
		registerMilkCauldronInteractions(NeapolitanItems.BANANA_MILKSHAKE.get(), NeapolitanBlocks.BANANA_MILKSHAKE_CAULDRON.get(), MilkCauldronBlock.BANANA_MILKSHAKE);
		registerMilkCauldronInteractions(NeapolitanItems.MINT_MILKSHAKE.get(), NeapolitanBlocks.MINT_MILKSHAKE_CAULDRON.get(), MilkCauldronBlock.MINT_MILKSHAKE);
		registerMilkCauldronInteractions(NeapolitanItems.ADZUKI_MILKSHAKE.get(), NeapolitanBlocks.ADZUKI_MILKSHAKE_CAULDRON.get(), MilkCauldronBlock.ADZUKI_MILKSHAKE);

		registerIceCreamCauldronInteraction(NeapolitanItems.VANILLA_ICE_CREAM.get(), NeapolitanBlocks.VANILLA_MILKSHAKE_CAULDRON.get());
		registerIceCreamCauldronInteraction(NeapolitanItems.CHOCOLATE_ICE_CREAM.get(), NeapolitanBlocks.CHOCOLATE_MILKSHAKE_CAULDRON.get());
		registerIceCreamCauldronInteraction(NeapolitanItems.STRAWBERRY_ICE_CREAM.get(), NeapolitanBlocks.STRAWBERRY_MILKSHAKE_CAULDRON.get());
		registerIceCreamCauldronInteraction(NeapolitanItems.BANANA_ICE_CREAM.get(), NeapolitanBlocks.BANANA_MILKSHAKE_CAULDRON.get());
		registerIceCreamCauldronInteraction(NeapolitanItems.MINT_ICE_CREAM.get(), NeapolitanBlocks.MINT_MILKSHAKE_CAULDRON.get());
		registerIceCreamCauldronInteraction(NeapolitanItems.ADZUKI_ICE_CREAM.get(), NeapolitanBlocks.ADZUKI_MILKSHAKE_CAULDRON.get());
	}

	public static void registerIceCreamCauldronInteraction(Item iceCream, Block filledCauldron) {
		MilkCauldronBlock.MILK.put(iceCream, (state, level, pos, player, hand, stack) -> {
			if (!level.isClientSide) {
				Item item = stack.getItem();
				player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BOWL)));
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				level.setBlockAndUpdate(pos, BlockUtil.transferAllBlockStates(level.getBlockState(pos), filledCauldron.defaultBlockState()));
				level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		});
	}


	public static void registerMilkCauldronInteractions(Item filledBottle, Block filledCauldron, Map<Item, CauldronInteraction> map) {
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

	public static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.ADZUKI_SPROUTS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BEANSTALK_THORNS.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.SMALL_BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.LARGE_BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_BANANA_FROND.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_SLAB.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), RenderType.cutout());
	}
}
