package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.common.dispenser.BananaBunchDispenseBehavior;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDecoratedPotPatterns;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

public class NeapolitanCompat {

	public static void transformCookies() {
		Foods.COOKIE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).fast().build();
	}

	public static void registerCompat() {
		transformCookies();
		registerFlammables();
		registerDispenserBehaviors();
		NeapolitanSoundEvents.registerNoteBlocks();
		NeapolitanDecoratedPotPatterns.registerDecoratedPotPatterns();
		NeapolitanCauldronInteractions.registerCauldronInteractions();
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

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICKS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILES.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), 60, 100);
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(NeapolitanItems.BANANA_BUNCH.get(), new BananaBunchDispenseBehavior());

		DispenserBlock.registerProjectileBehavior(NeapolitanItems.BANANARROW.get());

		DispenserBlock.registerBehavior(NeapolitanItems.CHIMPANZEE_HEAD.get(), new OptionalDispenseItemBehavior() {
			@Override
			protected ItemStack execute(BlockSource source, ItemStack stack) {
				this.setSuccess(ArmorItem.dispenseArmor(source, stack));
				return stack;
			}
		});
	}
}