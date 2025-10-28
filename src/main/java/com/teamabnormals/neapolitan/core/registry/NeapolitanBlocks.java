package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
import com.teamabnormals.blueprint.common.block.LogBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchStairBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.neapolitan.common.block.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanCauldronInteractions;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems.NeapolitanFoods;
import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents.NeapolitanSoundTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.ToIntFunction;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class NeapolitanBlocks {
	public static final BlockSubRegistryHelper BLOCKS = Neapolitan.REGISTRY_HELPER.getBlockSubHelper();

	public static final DeferredBlock<Block> CHILLBOX = BLOCKS.createBlock("chillbox", () -> new ChillboxBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

	public static final DeferredBlock<Block> VANILLA_ICE_CREAM_BLOCK = BLOCKS.createBlock("vanilla_ice_cream_block", () -> new Block(NeapolitanBlockProperties.VANILLA_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> CHOCOLATE_ICE_CREAM_BLOCK = BLOCKS.createBlock("chocolate_ice_cream_block", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> STRAWBERRY_ICE_CREAM_BLOCK = BLOCKS.createBlock("strawberry_ice_cream_block", () -> new Block(NeapolitanBlockProperties.STRAWBERRY_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> BANANA_ICE_CREAM_BLOCK = BLOCKS.createBlock("banana_ice_cream_block", () -> new Block(NeapolitanBlockProperties.BANANA_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> MINT_ICE_CREAM_BLOCK = BLOCKS.createBlock("mint_ice_cream_block", () -> new Block(NeapolitanBlockProperties.MINT_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> ADZUKI_ICE_CREAM_BLOCK = BLOCKS.createBlock("adzuki_ice_cream_block", () -> new Block(NeapolitanBlockProperties.ADZUKI_ICE_CREAM_BLOCK));

	public static final DeferredBlock<Block> MILK_CAULDRON = BLOCKS.createBlockNoItem("milk_cauldron", () -> new MilkCauldronBlock(NeapolitanBlockProperties.CAULDRON));
	public static final DeferredBlock<Block> VANILLA_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("vanilla_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.VANILLA_MILKSHAKE));
	public static final DeferredBlock<Block> CHOCOLATE_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("chocolate_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.CHOCOLATE_MILKSHAKE));
	public static final DeferredBlock<Block> STRAWBERRY_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("strawberry_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.STRAWBERRY_MILKSHAKE));
	public static final DeferredBlock<Block> BANANA_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("banana_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.BANANA_MILKSHAKE));
	public static final DeferredBlock<Block> MINT_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("mint_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.MINT_MILKSHAKE));
	public static final DeferredBlock<Block> ADZUKI_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("adzuki_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.ADZUKI_MILKSHAKE));

	public static final DeferredBlock<Block> CHOCOLATE_BLOCK = BLOCKS.createBlock("chocolate_block", () -> new Block(NeapolitanBlockProperties.CHOCOLATE));

	public static final DeferredBlock<Block> CHOCOLATE_BRICKS = BLOCKS.createBlock("chocolate_bricks", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final DeferredBlock<Block> CHOCOLATE_BRICK_SLAB = BLOCKS.createBlock("chocolate_brick_slab", () -> new SlabBlock(NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final DeferredBlock<Block> CHOCOLATE_BRICK_STAIRS = BLOCKS.createBlock("chocolate_brick_stairs", () -> new StairBlock(CHOCOLATE_BRICKS.get().defaultBlockState(), NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final DeferredBlock<Block> CHOCOLATE_BRICK_WALL = BLOCKS.createBlock("chocolate_brick_wall", () -> new WallBlock(NeapolitanBlockProperties.CHOCOLATE_BRICKS));

	public static final DeferredBlock<Block> CHISELED_CHOCOLATE_BRICKS = BLOCKS.createBlock("chiseled_chocolate_bricks", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_BRICKS));

	public static final DeferredBlock<Block> CHOCOLATE_TILES = BLOCKS.createBlock("chocolate_tiles", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final DeferredBlock<Block> CHOCOLATE_TILE_SLAB = BLOCKS.createBlock("chocolate_tile_slab", () -> new SlabBlock(NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final DeferredBlock<Block> CHOCOLATE_TILE_STAIRS = BLOCKS.createBlock("chocolate_tile_stairs", () -> new StairBlock(CHOCOLATE_TILES.get().defaultBlockState(), NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final DeferredBlock<Block> CHOCOLATE_TILE_WALL = BLOCKS.createBlock("chocolate_tile_wall", () -> new WallBlock(NeapolitanBlockProperties.CHOCOLATE_TILES));

	public static final DeferredBlock<Block> VANILLA_VINE = BLOCKS.createBlockNoItem("vanilla_vine", () -> new VanillaVineTopBlock(NeapolitanBlockProperties.VANILLA_VINE));
	public static final DeferredBlock<Block> VANILLA_VINE_PLANT = BLOCKS.createBlockNoItem("vanilla_vine_plant", () -> new VanillaVineBlock(NeapolitanBlockProperties.VANILLA_VINE));
	public static final DeferredBlock<Block> POTTED_VANILLA_VINE = BLOCKS.createBlockNoItem("potted_vanilla_vine", () -> new FlowerPotBlock(VANILLA_VINE.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> STRAWBERRY_BUSH = BLOCKS.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(NeapolitanBlockProperties.STRAWBERRY_BUSH));
	public static final DeferredBlock<Block> MINT = BLOCKS.createBlockNoItem("mint", () -> new MintBlock(NeapolitanBlockProperties.MINT));
	public static final DeferredBlock<Block> POTTED_MINT = BLOCKS.createBlockNoItem("potted_mint", () -> new FlowerPotBlock(MINT.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> CARVED_BANANA_STALK = BLOCKS.createBlock("carved_banana_stalk", () -> new RotatedPillarBlock(NeapolitanBlockProperties.BANANA_STALK));
	public static final DeferredBlock<Block> BANANA_STALK = BLOCKS.createBlock("banana_stalk", () -> new LogBlock(() -> CARVED_BANANA_STALK.get(), NeapolitanBlockProperties.BANANA_STALK));
	public static final DeferredBlock<Block> BANANA_FROND = BLOCKS.createBlock("banana_frond", () -> new BananaFrondBlock(NeapolitanBlockProperties.BANANA_FROND));
	public static final DeferredBlock<Block> POTTED_BANANA_FROND = BLOCKS.createBlockNoItem("potted_banana_frond", () -> new FlowerPotBlock(BANANA_FROND.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> FROND_THATCH = BLOCKS.createBlock("frond_thatch", () -> new ThatchBlock(NeapolitanBlockProperties.FROND_THATCH));
	public static final DeferredBlock<Block> FROND_THATCH_SLAB = BLOCKS.createBlock("frond_thatch_slab", () -> new ThatchSlabBlock(NeapolitanBlockProperties.FROND_THATCH));
	public static final DeferredBlock<Block> FROND_THATCH_STAIRS = BLOCKS.createBlock("frond_thatch_stairs", () -> new ThatchStairBlock(FROND_THATCH.get().defaultBlockState(), NeapolitanBlockProperties.FROND_THATCH));

	public static final DeferredBlock<Block> ADZUKI_SPROUTS = BLOCKS.createBlockNoItem("adzuki_sprouts", () -> new AdzukiSproutsBlock(NeapolitanBlockProperties.ADZUKI_SPROUTS));
	public static final DeferredBlock<Block> ADZUKI_SOIL = BLOCKS.createBlock("adzuki_soil", () -> new AdzukiSoilBlock(NeapolitanBlockProperties.ADZUKI_SOIL));
	public static final DeferredBlock<Block> MAGIC_BEANS = BLOCKS.createBlock("magic_beans", () -> new MagicBeansBlock(NeapolitanBlockProperties.MAGIC_BEANS));
	public static final DeferredBlock<Block> BEANSTALK = BLOCKS.createBlock("beanstalk", () -> new BeanstalkBlock(NeapolitanBlockProperties.BEANSTALK));
	public static final DeferredBlock<Block> BEANSTALK_THORNS = BLOCKS.createBlock("beanstalk_thorns", () -> new BeanstalkThornsBlock(NeapolitanBlockProperties.BEANSTALK_THORNS));

	public static final DeferredBlock<Block> VANILLA_CAKE = BLOCKS.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.VANILLA_CAKE, NeapolitanBlockProperties.VANILLA_CAKE));
	public static final DeferredBlock<Block> CHOCOLATE_CAKE = BLOCKS.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.CHOCOLATE_CAKE, NeapolitanBlockProperties.CHOCOLATE_CAKE));
	public static final DeferredBlock<Block> STRAWBERRY_CAKE = BLOCKS.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.STRAWBERRY_CAKE, NeapolitanBlockProperties.STRAWBERRY_CAKE));
	public static final DeferredBlock<Block> BANANA_CAKE = BLOCKS.createBlockNoItem("banana_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.BANANA_CAKE, NeapolitanBlockProperties.BANANA_CAKE));
	public static final DeferredBlock<Block> MINT_CAKE = BLOCKS.createBlockNoItem("mint_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.MINT_CAKE, NeapolitanBlockProperties.MINT_CAKE));
	public static final DeferredBlock<Block> ADZUKI_CAKE = BLOCKS.createBlockNoItem("adzuki_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.ADZUKI_CAKE, NeapolitanBlockProperties.ADZUKI_CAKE));

	public static final DeferredBlock<Block> VANILLA_POD_BLOCK = BLOCKS.createBlock("vanilla_pod_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.VANILLA_POD_BLOCK));
	public static final DeferredBlock<Block> DRIED_VANILLA_POD_BLOCK = BLOCKS.createBlock("dried_vanilla_pod_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.DRIED_VANILLA_POD_BLOCK));
	public static final DeferredBlock<Block> BANANA_BUNDLE = BLOCKS.createBlock("banana_bundle", () -> new BananaBundleBlock(NeapolitanBlockProperties.BANANA_BUNDLE));

	public static final DeferredBlock<Block> STRAWBERRY_BASKET = BLOCKS.createBlock("strawberry_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.STRAWBERRY_BASKET));
	public static final DeferredBlock<Block> WHITE_STRAWBERRY_BASKET = BLOCKS.createBlock("white_strawberry_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.WHITE_STRAWBERRY_BASKET));
	public static final DeferredBlock<Block> BANANA_CRATE = BLOCKS.createBlock("banana_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.BANANA_CRATE));
	public static final DeferredBlock<Block> MINT_BASKET = BLOCKS.createBlock("mint_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.MINT_BASKET));
	public static final DeferredBlock<Block> ADZUKI_CRATE = BLOCKS.createBlock("adzuki_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.ADZUKI_CRATE));
	public static final DeferredBlock<Block> ROASTED_ADZUKI_CRATE = BLOCKS.createBlock("roasted_adzuki_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.ROASTED_ADZUKI_CRATE));

	public static final DeferredBlock<Block> CHIMPANZEE_HEAD = BLOCKS.createBlockNoItem("chimpanzee_head", () -> new NeapolitanSkullBlock(NeapolitanSkullTypes.CHIMPANZEE, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> CHIMPANZEE_WALL_HEAD = BLOCKS.createBlockNoItem("chimpanzee_wall_head", () -> new NeapolitanWallSkullBlock(NeapolitanSkullTypes.CHIMPANZEE, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY).lootFrom(() -> CHIMPANZEE_HEAD.get())));

	public static final DeferredBlock<Block> VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));

	public static final DeferredBlock<Block> CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));

	public static final DeferredBlock<Block> STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));

	public static final DeferredBlock<Block> BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));

	public static final DeferredBlock<Block> MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));

	public static final DeferredBlock<Block> ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(Neapolitan.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItems(
						BANANA_STALK, CARVED_BANANA_STALK, FROND_THATCH, FROND_THATCH_STAIRS, FROND_THATCH_SLAB,
						CHOCOLATE_BLOCK, CHOCOLATE_BRICKS, CHOCOLATE_BRICK_STAIRS, CHOCOLATE_BRICK_SLAB, CHOCOLATE_BRICK_WALL, CHISELED_CHOCOLATE_BRICKS, CHOCOLATE_TILES, CHOCOLATE_TILE_STAIRS, CHOCOLATE_TILE_SLAB, CHOCOLATE_TILE_WALL,
						VANILLA_ICE_CREAM_BLOCK, CHOCOLATE_ICE_CREAM_BLOCK, STRAWBERRY_ICE_CREAM_BLOCK, BANANA_ICE_CREAM_BLOCK, MINT_ICE_CREAM_BLOCK, ADZUKI_ICE_CREAM_BLOCK
				)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsAfter(of(Blocks.CAULDRON), CHILLBOX)
				.tab(REDSTONE_BLOCKS)
				.addItemsAfter(of(Blocks.CAULDRON), CHILLBOX)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Blocks.ROOTED_DIRT), ADZUKI_SOIL)
				.addItemsAfter(of(Items.COCOA_BEANS), MAGIC_BEANS)
				.addItemsAfter(of(Blocks.CACTUS), BANANA_STALK, BANANA_FROND, BEANSTALK, BEANSTALK_THORNS)
				.addItemsAfter(of(Blocks.MELON), BANANA_BUNDLE)
				.addItemsAfter(of(Blocks.HAY_BLOCK), VANILLA_POD_BLOCK, DRIED_VANILLA_POD_BLOCK)
				.addItemsAfter(of(Blocks.HAY_BLOCK), STRAWBERRY_BASKET, WHITE_STRAWBERRY_BASKET, MINT_BASKET, BANANA_CRATE, ADZUKI_CRATE, ROASTED_ADZUKI_CRATE);
	}

	public enum NeapolitanSkullTypes implements SkullBlock.Type {
		CHIMPANZEE("chimpanzee");

		private final String name;

		private NeapolitanSkullTypes(String name) {
			this.name = name;
			TYPES.put(name, this);
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}

	public static final class NeapolitanBlockProperties {
		public static final BlockBehaviour.Properties VANILLA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties CHOCOLATE_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties STRAWBERRY_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties BANANA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties MINT_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties ADZUKI_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);

		public static final BlockBehaviour.Properties CAULDRON = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion();

		public static final BlockBehaviour.Properties CHOCOLATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_TILES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);

		public static final BlockBehaviour.Properties STRAWBERRY_BUSH = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties VANILLA_VINE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().instabreak().noCollission().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties MINT = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);

		public static final BlockBehaviour.Properties BANANA_STALK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.0F).sound(NeapolitanSoundTypes.BANANA_STALK);
		public static final BlockBehaviour.Properties BANANA_FROND = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().instabreak().noCollission().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties FROND_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.NETHER_SPROUTS);

		public static final BlockBehaviour.Properties ADZUKI_SPROUTS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties ADZUKI_SOIL = BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).randomTicks().sound(SoundType.ROOTED_DIRT);
		public static final BlockBehaviour.Properties MAGIC_BEANS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollission().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties BEANSTALK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).strength(1.0F).isSuffocating((state, reader, pos) -> false).sound(SoundType.STEM).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties BEANSTALK_THORNS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().strength(0.2F).sound(SoundType.FUNGUS).pushReaction(PushReaction.DESTROY);

		public static final BlockBehaviour.Properties CHOCOLATE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties STRAWBERRY_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties VANILLA_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties BANANA_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties MINT_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties ADZUKI_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);

		public static final BlockBehaviour.Properties CHOCOLATE_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties STRAWBERRY_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties VANILLA_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties BANANA_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties MINT_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties ADZUKI_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(litBlockEmission(3));

		public static final BlockBehaviour.Properties VANILLA_POD_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties DRIED_VANILLA_POD_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties BANANA_BUNDLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.DIDGERIDOO).strength(2.5F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);

		public static final BlockBehaviour.Properties STRAWBERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties WHITE_STRAWBERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties BANANA_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties MINT_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ADZUKI_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ROASTED_ADZUKI_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);

		private static ToIntFunction<BlockState> litBlockEmission(int level) {
			return (state) -> state.getValue(BlockStateProperties.LIT) ? level : 0;
		}
	}
}
