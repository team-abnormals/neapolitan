package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

import static net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS;
import static net.minecraft.world.item.CreativeModeTabs.NATURAL_BLOCKS;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanBlocks {
	public static final BlockSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> VANILLA_ICE_CREAM_BLOCK = HELPER.createBlock("vanilla_ice_cream_block", () -> new Block(NeapolitanBlockProperties.VANILLA_ICE_CREAM_BLOCK));
	public static final RegistryObject<Block> CHOCOLATE_ICE_CREAM_BLOCK = HELPER.createBlock("chocolate_ice_cream_block", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_ICE_CREAM_BLOCK));
	public static final RegistryObject<Block> STRAWBERRY_ICE_CREAM_BLOCK = HELPER.createBlock("strawberry_ice_cream_block", () -> new Block(NeapolitanBlockProperties.STRAWBERRY_ICE_CREAM_BLOCK));
	public static final RegistryObject<Block> BANANA_ICE_CREAM_BLOCK = HELPER.createBlock("banana_ice_cream_block", () -> new Block(NeapolitanBlockProperties.BANANA_ICE_CREAM_BLOCK));
	public static final RegistryObject<Block> MINT_ICE_CREAM_BLOCK = HELPER.createBlock("mint_ice_cream_block", () -> new Block(NeapolitanBlockProperties.MINT_ICE_CREAM_BLOCK));
	public static final RegistryObject<Block> ADZUKI_ICE_CREAM_BLOCK = HELPER.createBlock("adzuki_ice_cream_block", () -> new Block(NeapolitanBlockProperties.ADZUKI_ICE_CREAM_BLOCK));

	public static final RegistryObject<Block> MILK_CAULDRON = HELPER.createBlockNoItem("milk_cauldron", () -> new MilkCauldronBlock(NeapolitanBlockProperties.CAULDRON));
	public static final RegistryObject<Block> VANILLA_MILKSHAKE_CAULDRON = HELPER.createBlockNoItem("vanilla_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.VANILLA_MILKSHAKE.map()));
	public static final RegistryObject<Block> CHOCOLATE_MILKSHAKE_CAULDRON = HELPER.createBlockNoItem("chocolate_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.CHOCOLATE_MILKSHAKE.map()));
	public static final RegistryObject<Block> STRAWBERRY_MILKSHAKE_CAULDRON = HELPER.createBlockNoItem("strawberry_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.STRAWBERRY_MILKSHAKE.map()));
	public static final RegistryObject<Block> BANANA_MILKSHAKE_CAULDRON = HELPER.createBlockNoItem("banana_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.BANANA_MILKSHAKE.map()));
	public static final RegistryObject<Block> MINT_MILKSHAKE_CAULDRON = HELPER.createBlockNoItem("mint_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.MINT_MILKSHAKE.map()));
	public static final RegistryObject<Block> ADZUKI_MILKSHAKE_CAULDRON = HELPER.createBlockNoItem("adzuki_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.ADZUKI_MILKSHAKE.map()));

	public static final RegistryObject<Block> CHOCOLATE_BLOCK = HELPER.createBlock("chocolate_block", () -> new Block(NeapolitanBlockProperties.CHOCOLATE));

	public static final RegistryObject<Block> CHOCOLATE_BRICKS = HELPER.createBlock("chocolate_bricks", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB = HELPER.createBlock("chocolate_brick_slab", () -> new SlabBlock(NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS = HELPER.createBlock("chocolate_brick_stairs", () -> new StairBlock(() -> CHOCOLATE_BRICKS.get().defaultBlockState(), NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL = HELPER.createBlock("chocolate_brick_wall", () -> new WallBlock(NeapolitanBlockProperties.CHOCOLATE_BRICKS));

	public static final RegistryObject<Block> CHISELED_CHOCOLATE_BRICKS = HELPER.createBlock("chiseled_chocolate_bricks", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_BRICKS));

	public static final RegistryObject<Block> CHOCOLATE_TILES = HELPER.createBlock("chocolate_tiles", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB = HELPER.createBlock("chocolate_tile_slab", () -> new SlabBlock(NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS = HELPER.createBlock("chocolate_tile_stairs", () -> new StairBlock(() -> CHOCOLATE_TILES.get().defaultBlockState(), NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final RegistryObject<Block> CHOCOLATE_TILE_WALL = HELPER.createBlock("chocolate_tile_wall", () -> new WallBlock(NeapolitanBlockProperties.CHOCOLATE_TILES));

	public static final RegistryObject<Block> VANILLA_VINE = HELPER.createBlockNoItem("vanilla_vine", () -> new VanillaVineTopBlock(NeapolitanBlockProperties.VANILLA_VINE));
	public static final RegistryObject<Block> VANILLA_VINE_PLANT = HELPER.createBlockNoItem("vanilla_vine_plant", () -> new VanillaVineBlock(NeapolitanBlockProperties.VANILLA_VINE));
	public static final RegistryObject<Block> POTTED_VANILLA_VINE = HELPER.createBlockNoItem("potted_vanilla_vine", () -> new FlowerPotBlock(VANILLA_VINE.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> STRAWBERRY_BUSH = HELPER.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(NeapolitanBlockProperties.STRAWBERRY_BUSH));
	public static final RegistryObject<Block> MINT = HELPER.createBlockNoItem("mint", () -> new MintBlock(NeapolitanBlockProperties.MINT));
	public static final RegistryObject<Block> POTTED_MINT = HELPER.createBlockNoItem("potted_mint", () -> new FlowerPotBlock(MINT.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> BANANA_STALK = HELPER.createFuelBlock("banana_stalk", () -> new RotatedPillarBlock(NeapolitanBlockProperties.BANANA_STALK), 800);
	public static final RegistryObject<Block> CARVED_BANANA_STALK = HELPER.createFuelBlock("carved_banana_stalk", () -> new RotatedPillarBlock(NeapolitanBlockProperties.BANANA_STALK), 800);
	public static final RegistryObject<Block> SMALL_BANANA_FROND = HELPER.createBlockNoItem("small_banana_frond", () -> new BananaFrondBlock(NeapolitanBlockProperties.BANANA_FROND));
	public static final RegistryObject<Block> BANANA_FROND = HELPER.createBlockNoItem("banana_frond", () -> new BananaFrondBlock(NeapolitanBlockProperties.BANANA_FROND));
	public static final RegistryObject<Block> LARGE_BANANA_FROND = HELPER.createBlockNoItem("large_banana_frond", () -> new BananaFrondBlock(NeapolitanBlockProperties.BANANA_FROND));
	public static final RegistryObject<Block> POTTED_BANANA_FROND = HELPER.createBlockNoItem("potted_banana_frond", () -> new FlowerPotBlock(SMALL_BANANA_FROND.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> FROND_THATCH = HELPER.createFuelBlock("frond_thatch", () -> new ThatchBlock(NeapolitanBlockProperties.FROND_THATCH), 100);
	public static final RegistryObject<Block> FROND_THATCH_SLAB = HELPER.createFuelBlock("frond_thatch_slab", () -> new ThatchSlabBlock(NeapolitanBlockProperties.FROND_THATCH), 50);
	public static final RegistryObject<Block> FROND_THATCH_STAIRS = HELPER.createFuelBlock("frond_thatch_stairs", () -> new ThatchStairBlock(FROND_THATCH.get().defaultBlockState(), NeapolitanBlockProperties.FROND_THATCH), 100);

	public static final RegistryObject<Block> ADZUKI_SPROUTS = HELPER.createBlockNoItem("adzuki_sprouts", () -> new AdzukiSproutsBlock(NeapolitanBlockProperties.ADZUKI_SPROUTS));
	public static final RegistryObject<Block> ADZUKI_SOIL = HELPER.createBlock("adzuki_soil", () -> new AdzukiSoilBlock(NeapolitanBlockProperties.ADZUKI_SOIL));
	public static final RegistryObject<Block> BEANSTALK = HELPER.createBlock("beanstalk", () -> new BeanstalkBlock(NeapolitanBlockProperties.BEANSTALK));
	public static final RegistryObject<Block> BEANSTALK_THORNS = HELPER.createBlock("beanstalk_thorns", () -> new BeanstalkThornsBlock(NeapolitanBlockProperties.BEANSTALK_THORNS));

	public static final RegistryObject<Block> VANILLA_CAKE = HELPER.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.VANILLA_CAKE, NeapolitanBlockProperties.VANILLA_CAKE));
	public static final RegistryObject<Block> CHOCOLATE_CAKE = HELPER.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.CHOCOLATE_CAKE, NeapolitanBlockProperties.CHOCOLATE_CAKE));
	public static final RegistryObject<Block> STRAWBERRY_CAKE = HELPER.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.STRAWBERRY_CAKE, NeapolitanBlockProperties.STRAWBERRY_CAKE));
	public static final RegistryObject<Block> BANANA_CAKE = HELPER.createBlockNoItem("banana_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.BANANA_CAKE, NeapolitanBlockProperties.BANANA_CAKE));
	public static final RegistryObject<Block> MINT_CAKE = HELPER.createBlockNoItem("mint_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.MINT_CAKE, NeapolitanBlockProperties.MINT_CAKE));
	public static final RegistryObject<Block> ADZUKI_CAKE = HELPER.createBlockNoItem("adzuki_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.ADZUKI_CAKE, NeapolitanBlockProperties.ADZUKI_CAKE));

	public static final RegistryObject<Block> VANILLA_POD_BLOCK = HELPER.createBlock("vanilla_pod_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.VANILLA_POD_BLOCK));
	public static final RegistryObject<Block> DRIED_VANILLA_POD_BLOCK = HELPER.createBlock("dried_vanilla_pod_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.DRIED_VANILLA_POD_BLOCK));
	public static final RegistryObject<Block> BANANA_BUNDLE = HELPER.createBlock("banana_bundle", () -> new BananaBundleBlock(NeapolitanBlockProperties.BANANA_BUNDLE));

	public static final RegistryObject<Block> STRAWBERRY_BASKET = HELPER.createBlock("strawberry_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.STRAWBERRY_BASKET));
	public static final RegistryObject<Block> WHITE_STRAWBERRY_BASKET = HELPER.createBlock("white_strawberry_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.WHITE_STRAWBERRY_BASKET));
	public static final RegistryObject<Block> BANANA_CRATE = HELPER.createBlock("banana_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.BANANA_CRATE));
	public static final RegistryObject<Block> MINT_BASKET = HELPER.createBlock("mint_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.MINT_BASKET));
	public static final RegistryObject<Block> ADZUKI_CRATE = HELPER.createBlock("adzuki_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.ADZUKI_CRATE));
	public static final RegistryObject<Block> ROASTED_ADZUKI_CRATE = HELPER.createBlock("roasted_adzuki_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.ROASTED_ADZUKI_CRATE));

	public static final RegistryObject<Block> CHIMPANZEE_HEAD = HELPER.createBlockNoItem("chimpanzee_head", () -> new NeapolitanSkullBlock(NeapolitanSkullTypes.CHIMPANZEE, BlockBehaviour.Properties.of().strength(1.0F)));
	public static final RegistryObject<Block> CHIMPANZEE_WALL_HEAD = HELPER.createBlockNoItem("chimpanzee_wall_head", () -> new NeapolitanWallSkullBlock(NeapolitanSkullTypes.CHIMPANZEE, BlockBehaviour.Properties.of().strength(1.0F).lootFrom(() -> CHIMPANZEE_HEAD.get())));

	public static final RegistryObject<Block> VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("white_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("orange_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("lime_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("pink_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("purple_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("brown_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("green_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("red_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("black_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));

	public static final RegistryObject<Block> CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("white_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("orange_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("lime_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("pink_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("purple_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("brown_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("green_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("red_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("black_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));

	public static final RegistryObject<Block> STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("white_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("orange_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("lime_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("pink_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("purple_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("brown_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("green_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("red_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("black_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));

	public static final RegistryObject<Block> BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("white_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("orange_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("lime_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("pink_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("purple_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("brown_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("green_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("red_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("black_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));

	public static final RegistryObject<Block> MINT_CANDLE_CAKE = HELPER.createBlockNoItem("mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("white_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("orange_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("lime_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("pink_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("purple_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("brown_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("green_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("red_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("black_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));

	public static final RegistryObject<Block> ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("white_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("orange_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("lime_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("pink_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("purple_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("brown_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("green_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("red_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("black_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(Neapolitan.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItems(
						BANANA_STALK, CARVED_BANANA_STALK, FROND_THATCH, FROND_THATCH_STAIRS, FROND_THATCH_SLAB,
						CHOCOLATE_BLOCK, CHOCOLATE_BRICKS, CHOCOLATE_BRICK_STAIRS, CHOCOLATE_BRICK_SLAB, CHOCOLATE_BRICK_WALL, CHISELED_CHOCOLATE_BRICKS, CHOCOLATE_TILES, CHOCOLATE_TILE_STAIRS, CHOCOLATE_TILE_SLAB, CHOCOLATE_TILE_WALL,
						VANILLA_ICE_CREAM_BLOCK, CHOCOLATE_ICE_CREAM_BLOCK, STRAWBERRY_ICE_CREAM_BLOCK, BANANA_ICE_CREAM_BLOCK, MINT_ICE_CREAM_BLOCK, ADZUKI_ICE_CREAM_BLOCK
				)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Blocks.ROOTED_DIRT), ADZUKI_SOIL)
				.addItemsAfter(of(Blocks.CACTUS), BEANSTALK, BEANSTALK_THORNS)
				.addItemsAfter(of(Blocks.MELON), BANANA_BUNDLE)
				.addItemsAfter(of(Blocks.HAY_BLOCK), VANILLA_POD_BLOCK, DRIED_VANILLA_POD_BLOCK)
				.predicate(event -> event.getTabKey() == NATURAL_BLOCKS && ModList.get().isLoaded("berry_good"))
				.addItemsAfter(of(Blocks.HAY_BLOCK), STRAWBERRY_BASKET, WHITE_STRAWBERRY_BASKET, MINT_BASKET)
				.predicate(event -> event.getTabKey() == NATURAL_BLOCKS && ModList.get().isLoaded("quark"))
				.addItemsAfter(of(Blocks.HAY_BLOCK), BANANA_CRATE, ADZUKI_CRATE, ROASTED_ADZUKI_CRATE);

	}

	public enum NeapolitanSkullTypes implements SkullBlock.Type {
		CHIMPANZEE
	}

	public static final class NeapolitanBlockProperties {
		public static final BlockBehaviour.Properties VANILLA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties CHOCOLATE_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties STRAWBERRY_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties BANANA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties MINT_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties ADZUKI_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);

		public static final BlockBehaviour.Properties CAULDRON = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion();

		public static final BlockBehaviour.Properties CHOCOLATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_TILES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);

		public static final BlockBehaviour.Properties STRAWBERRY_BUSH = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);
		public static final BlockBehaviour.Properties VANILLA_VINE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES);
		public static final BlockBehaviour.Properties MINT = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);

		public static final BlockBehaviour.Properties BANANA_STALK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.0F).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties BANANA_FROND = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES);
		public static final BlockBehaviour.Properties FROND_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.NETHER_SPROUTS);

		public static final BlockBehaviour.Properties ADZUKI_SPROUTS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);
		public static final BlockBehaviour.Properties ADZUKI_SOIL = BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).randomTicks().sound(SoundType.ROOTED_DIRT);
		public static final BlockBehaviour.Properties BEANSTALK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).strength(1.0F).isSuffocating((state, reader, pos) -> false).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties BEANSTALK_THORNS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().strength(0.2F).sound(SoundType.FUNGUS);

		public static final BlockBehaviour.Properties CHOCOLATE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties STRAWBERRY_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties VANILLA_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties BANANA_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties MINT_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties ADZUKI_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOL);

		public static final BlockBehaviour.Properties CHOCOLATE_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties STRAWBERRY_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties VANILLA_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties BANANA_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties MINT_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties ADZUKI_CANDLE_CAKE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));

		public static final BlockBehaviour.Properties VANILLA_POD_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties DRIED_VANILLA_POD_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties BANANA_BUNDLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.DIDGERIDOO).strength(2.5F).sound(SoundType.WOOD);

		public static final BlockBehaviour.Properties STRAWBERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties WHITE_STRAWBERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties BANANA_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties MINT_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ADZUKI_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ROASTED_ADZUKI_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);

		private static ToIntFunction<BlockState> litBlockEmission(int level) {
			return (state) -> state.getValue(BlockStateProperties.LIT) ? level : 0;
		}
	}
}
