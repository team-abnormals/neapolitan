package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchStairBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchVerticalSlabBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.neapolitan.common.block.AdzukiSoilBlock;
import com.teamabnormals.neapolitan.common.block.AdzukiSproutsBlock;
import com.teamabnormals.neapolitan.common.block.BananaBundleBlock;
import com.teamabnormals.neapolitan.common.block.BananaFrondBlock;
import com.teamabnormals.neapolitan.common.block.BeanstalkBlock;
import com.teamabnormals.neapolitan.common.block.BeanstalkThornsBlock;
import com.teamabnormals.neapolitan.common.block.FlavoredCakeBlock;
import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.common.block.MilkCauldronBlock;
import com.teamabnormals.neapolitan.common.block.MintBlock;
import com.teamabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.teamabnormals.neapolitan.common.block.VanillaVineBlock;
import com.teamabnormals.neapolitan.common.block.VanillaVineTopBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanBlocks {
	public static final BlockSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> VANILLA_ICE_CREAM_BLOCK = HELPER.createBlock("vanilla_ice_cream_block", () -> new Block(Properties.VANILLA_ICE_CREAM_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_ICE_CREAM_BLOCK = HELPER.createBlock("chocolate_ice_cream_block", () -> new Block(Properties.CHOCOLATE_ICE_CREAM_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRAWBERRY_ICE_CREAM_BLOCK = HELPER.createBlock("strawberry_ice_cream_block", () -> new Block(Properties.STRAWBERRY_ICE_CREAM_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BANANA_ICE_CREAM_BLOCK = HELPER.createBlock("banana_ice_cream_block", () -> new Block(Properties.BANANA_ICE_CREAM_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MINT_ICE_CREAM_BLOCK = HELPER.createBlock("mint_ice_cream_block", () -> new Block(Properties.MINT_ICE_CREAM_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ADZUKI_ICE_CREAM_BLOCK = HELPER.createBlock("adzuki_ice_cream_block", () -> new Block(Properties.ADZUKI_ICE_CREAM_BLOCK), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> MILK_CAULDRON = HELPER.createBlockNoItem("milk_cauldron", () -> new MilkCauldronBlock(Properties.MILK_CAULDRON));

	public static final RegistryObject<Block> CHOCOLATE_BLOCK = HELPER.createBlock("chocolate_block", () -> new Block(Properties.CHOCOLATE), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_BRICKS = HELPER.createBlock("chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB = HELPER.createBlock("chocolate_brick_slab", () -> new SlabBlock(Properties.CHOCOLATE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS = HELPER.createBlock("chocolate_brick_stairs", () -> new StairBlock(() -> CHOCOLATE_BRICKS.get().defaultBlockState(), Properties.CHOCOLATE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL = HELPER.createBlock("chocolate_brick_wall", () -> new WallBlock(Properties.CHOCOLATE_BRICKS), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_CHOCOLATE_BRICKS = HELPER.createBlock("chiseled_chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_TILES = HELPER.createBlock("chocolate_tiles", () -> new Block(Properties.CHOCOLATE_TILES), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB = HELPER.createBlock("chocolate_tile_slab", () -> new SlabBlock(Properties.CHOCOLATE_TILES), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS = HELPER.createBlock("chocolate_tile_stairs", () -> new StairBlock(() -> CHOCOLATE_TILES.get().defaultBlockState(), Properties.CHOCOLATE_TILES), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_WALL = HELPER.createBlock("chocolate_tile_wall", () -> new WallBlock(Properties.CHOCOLATE_TILES), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_tile_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_TILES), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> FROND_THATCH = HELPER.createFuelBlock("frond_thatch", () -> new ThatchBlock(Properties.FROND_THATCH), 100, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_SLAB = HELPER.createFuelBlock("frond_thatch_slab", () -> new ThatchSlabBlock(Properties.FROND_THATCH), 50, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_STAIRS = HELPER.createFuelBlock("frond_thatch_stairs", () -> new ThatchStairBlock(FROND_THATCH.get().defaultBlockState(), Properties.FROND_THATCH), 100, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "frond_thatch_vertical_slab", () -> new ThatchVerticalSlabBlock(Properties.FROND_THATCH), 50, CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRAWBERRY_BUSH = HELPER.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(Properties.STRAWBERRY_BUSH));
	public static final RegistryObject<Block> VANILLA_VINE = HELPER.createBlockNoItem("vanilla_vine", () -> new VanillaVineTopBlock(Properties.VANILLA_VINE));
	public static final RegistryObject<Block> VANILLA_VINE_PLANT = HELPER.createBlockNoItem("vanilla_vine_plant", () -> new VanillaVineBlock(Properties.VANILLA_VINE));
	public static final RegistryObject<Block> MINT = HELPER.createBlockNoItem("mint", () -> new MintBlock(Properties.MINT));

	public static final RegistryObject<Block> BANANA_STALK = HELPER.createFuelBlock("banana_stalk", () -> new RotatedPillarBlock(Properties.BANANA_STALK), 800, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARVED_BANANA_STALK = HELPER.createFuelBlock("carved_banana_stalk", () -> new RotatedPillarBlock(Properties.BANANA_STALK), 800, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMALL_BANANA_FROND = HELPER.createFuelBlock("small_banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 50, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_FROND = HELPER.createFuelBlock("banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 100, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> LARGE_BANANA_FROND = HELPER.createFuelBlock("large_banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 150, CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> ADZUKI_SPROUTS = HELPER.createBlockNoItem("adzuki_sprouts", () -> new AdzukiSproutsBlock(Properties.ADZUKI_SPROUTS));
	public static final RegistryObject<Block> ADZUKI_SOIL = HELPER.createBlock("adzuki_soil", () -> new AdzukiSoilBlock(Properties.ADZUKI_SOIL), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEANSTALK = HELPER.createBlock("beanstalk", () -> new BeanstalkBlock(Properties.BEANSTALK), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEANSTALK_THORNS = HELPER.createBlock("beanstalk_thorns", () -> new BeanstalkThornsBlock(Properties.BEANSTALK_THORNS), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> CHOCOLATE_CAKE = HELPER.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.CHOCOLATE_CAKE, Properties.CHOCOLATE_CAKE));
	public static final RegistryObject<Block> STRAWBERRY_CAKE = HELPER.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.STRAWBERRY_CAKE, Properties.STRAWBERRY_CAKE));
	public static final RegistryObject<Block> VANILLA_CAKE = HELPER.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.VANILLA_CAKE, Properties.VANILLA_CAKE));
	public static final RegistryObject<Block> BANANA_CAKE = HELPER.createBlockNoItem("banana_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.BANANA_CAKE, Properties.BANANA_CAKE));
	public static final RegistryObject<Block> MINT_CAKE = HELPER.createBlockNoItem("mint_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.MINT_CAKE, Properties.MINT_CAKE));
	public static final RegistryObject<Block> ADZUKI_CAKE = HELPER.createBlockNoItem("adzuki_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.ADZUKI_CAKE, Properties.ADZUKI_CAKE));

	public static final RegistryObject<Block> VANILLA_POD_BLOCK = HELPER.createBlock("vanilla_pod_block", () -> new RotatedPillarBlock(Properties.VANILLA_POD_BLOCK), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DRIED_VANILLA_POD_BLOCK = HELPER.createBlock("dried_vanilla_pod_block", () -> new RotatedPillarBlock(Properties.DRIED_VANILLA_POD_BLOCK), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_BUNDLE = HELPER.createBlock("banana_bundle", () -> new BananaBundleBlock(Properties.BANANA_BUNDLE), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "strawberry_basket", () -> new Block(Properties.STRAWBERRY_BASKET), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> WHITE_STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "white_strawberry_basket", () -> new Block(Properties.WHITE_STRAWBERRY_BASKET), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_CRATE = HELPER.createCompatBlock("quark", "banana_crate", () -> new Block(Properties.BANANA_CRATE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MINT_BASKET = HELPER.createCompatBlock("quark", "mint_basket", () -> new Block(Properties.MINT_BASKET), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ADZUKI_CRATE = HELPER.createCompatBlock("quark", "adzuki_crate", () -> new Block(Properties.ADZUKI_CRATE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROASTED_ADZUKI_CRATE = HELPER.createCompatBlock("quark", "roasted_adzuki_crate", () -> new Block(Properties.ROASTED_ADZUKI_CRATE), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("white_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.WHITE_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("orange_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.ORANGE_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.MAGENTA_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_BLUE_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.YELLOW_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("lime_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIME_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("pink_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PINK_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GRAY_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_GRAY_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CYAN_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("purple_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PURPLE_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLUE_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("brown_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BROWN_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("green_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GREEN_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("red_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.RED_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_CHOCOLATE_CANDLE_CAKE = HELPER.createBlockNoItem("black_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLACK_CANDLE, Properties.CHOCOLATE_CANDLE_CAKE));

	public static final RegistryObject<Block> STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("white_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.WHITE_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("orange_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.ORANGE_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.MAGENTA_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_BLUE_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.YELLOW_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("lime_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIME_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("pink_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PINK_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GRAY_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_GRAY_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CYAN_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("purple_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PURPLE_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLUE_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("brown_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BROWN_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("green_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GREEN_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("red_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.RED_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_STRAWBERRY_CANDLE_CAKE = HELPER.createBlockNoItem("black_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLACK_CANDLE, Properties.STRAWBERRY_CANDLE_CAKE));

	public static final RegistryObject<Block> VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("white_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.WHITE_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("orange_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.ORANGE_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.MAGENTA_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_BLUE_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.YELLOW_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("lime_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIME_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("pink_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PINK_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GRAY_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_GRAY_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CYAN_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("purple_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PURPLE_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLUE_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("brown_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BROWN_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("green_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GREEN_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("red_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.RED_CANDLE, Properties.VANILLA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_VANILLA_CANDLE_CAKE = HELPER.createBlockNoItem("black_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLACK_CANDLE, Properties.VANILLA_CANDLE_CAKE));

	public static final RegistryObject<Block> BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("white_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.WHITE_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("orange_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.ORANGE_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.MAGENTA_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_BLUE_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.YELLOW_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("lime_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIME_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("pink_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PINK_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GRAY_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_GRAY_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CYAN_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("purple_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PURPLE_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLUE_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("brown_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BROWN_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("green_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GREEN_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("red_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.RED_CANDLE, Properties.BANANA_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_BANANA_CANDLE_CAKE = HELPER.createBlockNoItem("black_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLACK_CANDLE, Properties.BANANA_CANDLE_CAKE));

	public static final RegistryObject<Block> MINT_CANDLE_CAKE = HELPER.createBlockNoItem("mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("white_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.WHITE_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("orange_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.ORANGE_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.MAGENTA_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_BLUE_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.YELLOW_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("lime_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIME_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("pink_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PINK_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GRAY_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_GRAY_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CYAN_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("purple_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PURPLE_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLUE_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("brown_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BROWN_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("green_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GREEN_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("red_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.RED_CANDLE, Properties.MINT_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_MINT_CANDLE_CAKE = HELPER.createBlockNoItem("black_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLACK_CANDLE, Properties.MINT_CANDLE_CAKE));

	public static final RegistryObject<Block> ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> WHITE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("white_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.WHITE_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> ORANGE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("orange_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.ORANGE_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> MAGENTA_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("magenta_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.MAGENTA_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_BLUE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("light_blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_BLUE_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> YELLOW_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("yellow_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.YELLOW_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> LIME_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("lime_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIME_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> PINK_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("pink_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PINK_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> GRAY_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GRAY_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> LIGHT_GRAY_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("light_gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_GRAY_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> CYAN_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("cyan_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CYAN_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> PURPLE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("purple_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PURPLE_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> BLUE_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLUE_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> BROWN_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("brown_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BROWN_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> GREEN_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("green_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GREEN_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> RED_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("red_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.RED_CANDLE, Properties.ADZUKI_CANDLE_CAKE));
	public static final RegistryObject<Block> BLACK_ADZUKI_CANDLE_CAKE = HELPER.createBlockNoItem("black_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLACK_CANDLE, Properties.ADZUKI_CANDLE_CAKE));

	static class Properties {
		public static final BlockBehaviour.Properties VANILLA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of(Material.SNOW, MaterialColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties CHOCOLATE_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of(Material.SNOW, MaterialColor.COLOR_BROWN).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties STRAWBERRY_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of(Material.SNOW, MaterialColor.COLOR_PINK).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties BANANA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of(Material.SNOW, MaterialColor.COLOR_YELLOW).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties MINT_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of(Material.SNOW, MaterialColor.COLOR_LIGHT_GREEN).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties ADZUKI_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of(Material.SNOW, MaterialColor.COLOR_RED).strength(0.2F).sound(SoundType.SNOW);

		public static final BlockBehaviour.Properties MILK_CAULDRON = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion();

		public static final BlockBehaviour.Properties CHOCOLATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_BRICKS = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_TILES = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD);

		public static final BlockBehaviour.Properties STRAWBERRY_BUSH = BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);
		public static final BlockBehaviour.Properties VANILLA_VINE = BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES);
		public static final BlockBehaviour.Properties MINT = BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);

		public static final BlockBehaviour.Properties BANANA_STALK = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).strength(1.0F).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties BANANA_FROND = BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES);
		public static final BlockBehaviour.Properties FROND_THATCH = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.NETHER_SPROUTS);

		public static final BlockBehaviour.Properties ADZUKI_SPROUTS = BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);
		public static final BlockBehaviour.Properties ADZUKI_SOIL = BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).randomTicks().sound(SoundType.GRAVEL);
		public static final BlockBehaviour.Properties BEANSTALK = BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_GREEN).strength(1.0F).isSuffocating((state, reader, pos) -> false).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties BEANSTALK_THORNS = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_GREEN).noCollission().strength(0.2F).sound(SoundType.FUNGUS);

		public static final BlockBehaviour.Properties CHOCOLATE_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties STRAWBERRY_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties VANILLA_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties BANANA_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties MINT_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties ADZUKI_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOL);

		public static final BlockBehaviour.Properties CHOCOLATE_CANDLE_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties STRAWBERRY_CANDLE_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties VANILLA_CANDLE_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties BANANA_CANDLE_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties MINT_CANDLE_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));
		public static final BlockBehaviour.Properties ADZUKI_CANDLE_CAKE = BlockBehaviour.Properties.of(Material.CAKE, MaterialColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOL).lightLevel(litBlockEmission(3));

		public static final BlockBehaviour.Properties VANILLA_POD_BLOCK = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_LIGHT_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties DRIED_VANILLA_POD_BLOCK = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties BANANA_BUNDLE = BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_YELLOW).strength(2.5F).sound(SoundType.WOOD);

		public static final BlockBehaviour.Properties STRAWBERRY_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties WHITE_STRAWBERRY_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties BANANA_CRATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW).strength(1.5F).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties MINT_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GREEN).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ADZUKI_CRATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ROASTED_ADZUKI_CRATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).strength(1.5F).sound(SoundType.WOOD);

		private static ToIntFunction<BlockState> litBlockEmission(int level) {
			return (state) -> state.getValue(BlockStateProperties.LIT) ? level : 0;
		}
	}
}
