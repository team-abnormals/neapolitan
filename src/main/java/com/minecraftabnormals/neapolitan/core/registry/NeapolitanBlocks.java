package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchSlabBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchStairsBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchVerticalSlabBlock;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.minecraftabnormals.neapolitan.common.block.*;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanBlocks {
	public static final BlockSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> VANILLA_ICE_CREAM_BLOCK = HELPER.createBlock("vanilla_ice_cream_block", () -> new Block(Properties.VANILLA_ICE_CREAM_BLOCK), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_ICE_CREAM_BLOCK = HELPER.createBlock("chocolate_ice_cream_block", () -> new Block(Properties.CHOCOLATE_ICE_CREAM_BLOCK), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRAWBERRY_ICE_CREAM_BLOCK = HELPER.createBlock("strawberry_ice_cream_block", () -> new Block(Properties.STRAWBERRY_ICE_CREAM_BLOCK), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BANANA_ICE_CREAM_BLOCK = HELPER.createBlock("banana_ice_cream_block", () -> new Block(Properties.BANANA_ICE_CREAM_BLOCK), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MINT_ICE_CREAM_BLOCK = HELPER.createBlock("mint_ice_cream_block", () -> new Block(Properties.MINT_ICE_CREAM_BLOCK), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ADZUKI_ICE_CREAM_BLOCK = HELPER.createBlock("adzuki_ice_cream_block", () -> new Block(Properties.ADZUKI_ICE_CREAM_BLOCK), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> MILK_CAULDRON = HELPER.createBlockNoItem("milk_cauldron", () -> new MilkCauldronBlock(Properties.MILK_CAULDRON));

	public static final RegistryObject<Block> CHOCOLATE_BLOCK = HELPER.createBlock("chocolate_block", () -> new Block(Properties.CHOCOLATE), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_BRICKS = HELPER.createBlock("chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB = HELPER.createBlock("chocolate_brick_slab", () -> new SlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS = HELPER.createBlock("chocolate_brick_stairs", () -> new StairsBlock(() -> CHOCOLATE_BRICKS.get().defaultBlockState(), Properties.CHOCOLATE_BRICKS), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL = HELPER.createBlock("chocolate_brick_wall", () -> new WallBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_CHOCOLATE_BRICKS = HELPER.createBlock("chiseled_chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_TILES = HELPER.createBlock("chocolate_tiles", () -> new Block(Properties.CHOCOLATE_TILES), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB = HELPER.createBlock("chocolate_tile_slab", () -> new SlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS = HELPER.createBlock("chocolate_tile_stairs", () -> new StairsBlock(() -> CHOCOLATE_TILES.get().defaultBlockState(), Properties.CHOCOLATE_TILES), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_WALL = HELPER.createBlock("chocolate_tile_wall", () -> new WallBlock(Properties.CHOCOLATE_TILES), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_tile_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> FROND_THATCH = HELPER.createFuelBlock("frond_thatch", () -> new ThatchBlock(Properties.FROND_THATCH), 100, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_SLAB = HELPER.createFuelBlock("frond_thatch_slab", () -> new ThatchSlabBlock(Properties.FROND_THATCH), 50, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_STAIRS = HELPER.createFuelBlock("frond_thatch_stairs", () -> new ThatchStairsBlock(FROND_THATCH.get().defaultBlockState(), Properties.FROND_THATCH), 100, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "frond_thatch_vertical_slab", () -> new ThatchVerticalSlabBlock(Properties.FROND_THATCH), 50, ItemGroup.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRAWBERRY_BUSH = HELPER.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(Properties.STRAWBERRY_BUSH));
	public static final RegistryObject<Block> VANILLA_VINE = HELPER.createBlockNoItem("vanilla_vine", () -> new VanillaVineTopBlock(Properties.VANILLA_VINE));
	public static final RegistryObject<Block> VANILLA_VINE_PLANT = HELPER.createBlockNoItem("vanilla_vine_plant", () -> new VanillaVineBlock(Properties.VANILLA_VINE));
	public static final RegistryObject<Block> MINT = HELPER.createBlockNoItem("mint", () -> new MintBlock(Properties.MINT));

	public static final RegistryObject<Block> BANANA_STALK = HELPER.createFuelBlock("banana_stalk", () -> new RotatedPillarBlock(Properties.BANANA_STALK), 800, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARVED_BANANA_STALK = HELPER.createFuelBlock("carved_banana_stalk", () -> new RotatedPillarBlock(Properties.BANANA_STALK), 800, ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMALL_BANANA_FROND = HELPER.createFuelBlock("small_banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 50, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_FROND = HELPER.createFuelBlock("banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 100, ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> LARGE_BANANA_FROND = HELPER.createFuelBlock("large_banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 150, ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> ADZUKI_SPROUTS = HELPER.createBlockNoItem("adzuki_sprouts", () -> new AdzukiSproutsBlock(Properties.ADZUKI_SPROUTS));
	public static final RegistryObject<Block> ADZUKI_SOIL = HELPER.createBlock("adzuki_soil", () -> new AdzukiSoilBlock(Properties.ADZUKI_SOIL), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEANSTALK = HELPER.createBlock("beanstalk", () -> new BeanstalkBlock(Properties.BEANSTALK), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEANSTALK_THORNS = HELPER.createBlock("beanstalk_thorns", () -> new BeanstalkThornsBlock(Properties.BEANSTALK_THORNS), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> CHOCOLATE_CAKE = HELPER.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.CHOCOLATE_CAKE, EffectType.BENEFICIAL, Properties.CHOCOLATE_CAKE));
	public static final RegistryObject<Block> STRAWBERRY_CAKE = HELPER.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.STRAWBERRY_CAKE, EffectType.HARMFUL, Properties.STRAWBERRY_CAKE));
	public static final RegistryObject<Block> VANILLA_CAKE = HELPER.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.VANILLA_CAKE, EffectType.NEUTRAL, Properties.VANILLA_CAKE));
	public static final RegistryObject<Block> BANANA_CAKE = HELPER.createBlockNoItem("banana_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.BANANA_CAKE, null, Properties.BANANA_CAKE));
	public static final RegistryObject<Block> MINT_CAKE = HELPER.createBlockNoItem("mint_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.MINT_CAKE, null, Properties.MINT_CAKE));
	public static final RegistryObject<Block> ADZUKI_CAKE = HELPER.createBlockNoItem("adzuki_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.ADZUKI_CAKE, null, Properties.ADZUKI_CAKE));

	public static final RegistryObject<Block> VANILLA_POD_BLOCK = HELPER.createBlock("vanilla_pod_block", () -> new RotatedPillarBlock(Properties.VANILLA_POD_BLOCK), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> DRIED_VANILLA_POD_BLOCK = HELPER.createBlock("dried_vanilla_pod_block", () -> new RotatedPillarBlock(Properties.DRIED_VANILLA_POD_BLOCK), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_BUNDLE = HELPER.createBlock("banana_bundle", () -> new BananaBundleBlock(Properties.BANANA_BUNDLE), ItemGroup.TAB_DECORATIONS);

	public static final RegistryObject<Block> STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "strawberry_basket", () -> new Block(Properties.STRAWBERRY_BASKET), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> WHITE_STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "white_strawberry_basket", () -> new Block(Properties.WHITE_STRAWBERRY_BASKET), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_CRATE = HELPER.createCompatBlock("quark", "banana_crate", () -> new Block(Properties.BANANA_CRATE), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> MINT_BASKET = HELPER.createCompatBlock("quark", "mint_basket", () -> new Block(Properties.MINT_BASKET), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ADZUKI_CRATE = HELPER.createCompatBlock("quark", "adzuki_crate", () -> new Block(Properties.ADZUKI_CRATE), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROASTED_ADZUKI_CRATE = HELPER.createCompatBlock("quark", "roasted_adzuki_crate", () -> new Block(Properties.ROASTED_ADZUKI_CRATE), ItemGroup.TAB_DECORATIONS);

	static class Properties {
		public static final AbstractBlock.Properties VANILLA_ICE_CREAM_BLOCK = AbstractBlock.Properties.of(Material.SNOW, MaterialColor.TERRACOTTA_WHITE).harvestTool(ToolType.SHOVEL).strength(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties CHOCOLATE_ICE_CREAM_BLOCK = AbstractBlock.Properties.of(Material.SNOW, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties STRAWBERRY_ICE_CREAM_BLOCK = AbstractBlock.Properties.of(Material.SNOW, MaterialColor.COLOR_PINK).harvestTool(ToolType.SHOVEL).strength(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties BANANA_ICE_CREAM_BLOCK = AbstractBlock.Properties.of(Material.SNOW, MaterialColor.COLOR_YELLOW).harvestTool(ToolType.SHOVEL).strength(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties MINT_ICE_CREAM_BLOCK = AbstractBlock.Properties.of(Material.SNOW, MaterialColor.COLOR_LIGHT_GREEN).harvestTool(ToolType.SHOVEL).strength(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties ADZUKI_ICE_CREAM_BLOCK = AbstractBlock.Properties.of(Material.SNOW, MaterialColor.COLOR_RED).harvestTool(ToolType.SHOVEL).strength(0.2F).sound(SoundType.SNOW);

		public static final AbstractBlock.Properties MILK_CAULDRON = AbstractBlock.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion();

		public static final AbstractBlock.Properties CHOCOLATE = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties CHOCOLATE_BRICKS = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties CHOCOLATE_TILES = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD);

		public static final AbstractBlock.Properties STRAWBERRY_BUSH = AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);
		public static final AbstractBlock.Properties VANILLA_VINE = AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES);
		public static final AbstractBlock.Properties MINT = AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);

		public static final AbstractBlock.Properties BANANA_STALK = AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).strength(1.0F).sound(SoundType.STEM).harvestTool(ToolType.HOE);
		public static final AbstractBlock.Properties BANANA_FROND = AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES).harvestTool(ToolType.HOE);
		public static final AbstractBlock.Properties FROND_THATCH = AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.NETHER_SPROUTS).harvestTool(ToolType.HOE);

		public static final AbstractBlock.Properties ADZUKI_SPROUTS = AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.CROP);
		public static final AbstractBlock.Properties ADZUKI_SOIL = AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).randomTicks().harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL);
		public static final AbstractBlock.Properties BEANSTALK = AbstractBlock.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_GREEN).strength(1.0F).harvestTool(ToolType.HOE).isSuffocating((state, reader, pos) -> false).sound(SoundType.STEM);
		public static final AbstractBlock.Properties BEANSTALK_THORNS = AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_GREEN).noCollission().strength(0.2F).harvestTool(ToolType.HOE).sound(SoundType.FUNGUS);

		public static final AbstractBlock.Properties CHOCOLATE_CAKE = AbstractBlock.Properties.of(Material.CAKE, MaterialColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL);
		public static final AbstractBlock.Properties STRAWBERRY_CAKE = AbstractBlock.Properties.of(Material.CAKE, MaterialColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOL);
		public static final AbstractBlock.Properties VANILLA_CAKE = AbstractBlock.Properties.of(Material.CAKE, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.WOOL);
		public static final AbstractBlock.Properties BANANA_CAKE = AbstractBlock.Properties.of(Material.CAKE, MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.WOOL);
		public static final AbstractBlock.Properties MINT_CAKE = AbstractBlock.Properties.of(Material.CAKE, MaterialColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.WOOL);
		public static final AbstractBlock.Properties ADZUKI_CAKE = AbstractBlock.Properties.of(Material.CAKE, MaterialColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOL);

		public static final AbstractBlock.Properties VANILLA_POD_BLOCK = AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_LIGHT_GREEN).harvestTool(ToolType.HOE).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final AbstractBlock.Properties DRIED_VANILLA_POD_BLOCK = AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).harvestTool(ToolType.HOE).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final AbstractBlock.Properties BANANA_BUNDLE = AbstractBlock.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_YELLOW).harvestTool(ToolType.HOE).strength(2.5F).sound(SoundType.WOOD);

		public static final AbstractBlock.Properties STRAWBERRY_BASKET = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties WHITE_STRAWBERRY_BASKET = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties BANANA_CRATE = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW).strength(1.5F).sound(SoundType.STEM);
		public static final AbstractBlock.Properties MINT_BASKET = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GREEN).strength(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties ADZUKI_CRATE = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties ROASTED_ADZUKI_CRATE = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).strength(1.5F).sound(SoundType.WOOD);
	}
}
