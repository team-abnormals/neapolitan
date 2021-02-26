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

	public static final RegistryObject<Block> VANILLA_ICE_CREAM_BLOCK = HELPER.createBlock("vanilla_ice_cream_block", () -> new Block(Properties.VANILLA_ICE_CREAM_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_ICE_CREAM_BLOCK = HELPER.createBlock("chocolate_ice_cream_block", () -> new Block(Properties.CHOCOLATE_ICE_CREAM_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRAWBERRY_ICE_CREAM_BLOCK = HELPER.createBlock("strawberry_ice_cream_block", () -> new Block(Properties.STRAWBERRY_ICE_CREAM_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BANANA_ICE_CREAM_BLOCK = HELPER.createBlock("banana_ice_cream_block", () -> new Block(Properties.BANANA_ICE_CREAM_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> MINT_ICE_CREAM_BLOCK = HELPER.createBlock("mint_ice_cream_block", () -> new Block(Properties.MINT_ICE_CREAM_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ADZUKI_ICE_CREAM_BLOCK = HELPER.createBlock("adzuki_ice_cream_block", () -> new Block(Properties.ADZUKI_ICE_CREAM_BLOCK), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_BLOCK = HELPER.createBlock("chocolate_block", () -> new Block(Properties.CHOCOLATE), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_BRICKS = HELPER.createBlock("chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB = HELPER.createBlock("chocolate_brick_slab", () -> new SlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS = HELPER.createBlock("chocolate_brick_stairs", () -> new StairsBlock(() -> CHOCOLATE_BRICKS.get().getDefaultState(), Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL = HELPER.createBlock("chocolate_brick_wall", () -> new WallBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_CHOCOLATE_BRICKS = HELPER.createBlock("chiseled_chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_TILES = HELPER.createBlock("chocolate_tiles", () -> new Block(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB = HELPER.createBlock("chocolate_tile_slab", () -> new SlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS = HELPER.createBlock("chocolate_tile_stairs", () -> new StairsBlock(() -> CHOCOLATE_TILES.get().getDefaultState(), Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_WALL = HELPER.createBlock("chocolate_tile_wall", () -> new WallBlock(Properties.CHOCOLATE_TILES), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_tile_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> FROND_THATCH = HELPER.createFuelBlock("frond_thatch", () -> new ThatchBlock(Properties.FROND_THATCH), 100, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_SLAB = HELPER.createFuelBlock("frond_thatch_slab", () -> new ThatchSlabBlock(Properties.FROND_THATCH), 50, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_STAIRS = HELPER.createFuelBlock("frond_thatch_stairs", () -> new ThatchStairsBlock(FROND_THATCH.get().getDefaultState(), Properties.FROND_THATCH), 100, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FROND_THATCH_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "frond_thatch_vertical_slab", () -> new ThatchVerticalSlabBlock(Properties.FROND_THATCH), 50, ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRAWBERRY_BUSH = HELPER.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(Properties.STRAWBERRY_BUSH));
	public static final RegistryObject<Block> VANILLA_VINE = HELPER.createBlockNoItem("vanilla_vine", () -> new VanillaVineTopBlock(Properties.VANILLA_VINE));
	public static final RegistryObject<Block> VANILLA_VINE_PLANT = HELPER.createBlockNoItem("vanilla_vine_plant", () -> new VanillaVineBlock(Properties.VANILLA_VINE));
	public static final RegistryObject<Block> MINT = HELPER.createBlockNoItem("mint", () -> new MintBlock(Properties.MINT));

	public static final RegistryObject<Block> BANANA_STALK = HELPER.createFuelBlock("banana_stalk", () -> new RotatedPillarBlock(Properties.BANANA_STALK), 800, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CARVED_BANANA_STALK = HELPER.createFuelBlock("carved_banana_stalk", () -> new RotatedPillarBlock(Properties.BANANA_STALK), 800, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SMALL_BANANA_FROND = HELPER.createFuelBlock("small_banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 50, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BANANA_FROND = HELPER.createFuelBlock("banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 100, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LARGE_BANANA_FROND = HELPER.createFuelBlock("large_banana_frond", () -> new BananaFrondBlock(Properties.BANANA_FROND), 150, ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> ADZUKI_SPROUTS = HELPER.createBlockNoItem("adzuki_sprouts", () -> new AdzukiSproutsBlock(Properties.ADZUKI_SPROUTS));
	public static final RegistryObject<Block> ADZUKI_SOIL = HELPER.createBlock("adzuki_soil", () -> new AdzukiSoilBlock(Properties.ADZUKI_SOIL), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEANSTALK = HELPER.createBlock("beanstalk", () -> new BeanstalkBlock(Properties.BEANSTALK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEANSTALK_THORNS = HELPER.createBlock("beanstalk_thorns", () -> new BeanstalkThornsBlock(Properties.BEANSTALK_THORNS), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> CHOCOLATE_CAKE = HELPER.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.CHOCOLATE_CAKE, EffectType.BENEFICIAL, Properties.CHOCOLATE_CAKE));
	public static final RegistryObject<Block> STRAWBERRY_CAKE = HELPER.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.STRAWBERRY_CAKE, EffectType.HARMFUL, Properties.STRAWBERRY_CAKE));
	public static final RegistryObject<Block> VANILLA_CAKE = HELPER.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.VANILLA_CAKE, EffectType.NEUTRAL, Properties.VANILLA_CAKE));
	public static final RegistryObject<Block> BANANA_CAKE = HELPER.createBlockNoItem("banana_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.BANANA_CAKE, null, Properties.BANANA_CAKE));
	public static final RegistryObject<Block> MINT_CAKE = HELPER.createBlockNoItem("mint_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.MINT_CAKE, null, Properties.MINT_CAKE));
	public static final RegistryObject<Block> ADZUKI_CAKE = HELPER.createBlockNoItem("adzuki_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.ADZUKI_CAKE, null, Properties.ADZUKI_CAKE));

	public static final RegistryObject<Block> VANILLA_POD_BLOCK = HELPER.createBlock("vanilla_pod_block", () -> new RotatedPillarBlock(Properties.VANILLA_POD_BLOCK), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DRIED_VANILLA_POD_BLOCK = HELPER.createBlock("dried_vanilla_pod_block", () -> new RotatedPillarBlock(Properties.DRIED_VANILLA_POD_BLOCK), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BANANA_BUNDLE = HELPER.createBlock("banana_bundle", () -> new BananaBundleBlock(Properties.BANANA_BUNDLE), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "strawberry_basket", () -> new Block(Properties.STRAWBERRY_BASKET), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "white_strawberry_basket", () -> new Block(Properties.WHITE_STRAWBERRY_BASKET), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BANANA_CRATE = HELPER.createCompatBlock("quark", "banana_crate", () -> new Block(Properties.BANANA_CRATE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MINT_BASKET = HELPER.createCompatBlock("quark", "mint_basket", () -> new Block(Properties.MINT_BASKET), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ADZUKI_CRATE = HELPER.createCompatBlock("quark", "adzuki_crate", () -> new Block(Properties.ADZUKI_CRATE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROASTED_ADZUKI_CRATE = HELPER.createCompatBlock("quark", "roasted_adzuki_crate", () -> new Block(Properties.ROASTED_ADZUKI_CRATE), ItemGroup.DECORATIONS);

	static class Properties {
		public static final AbstractBlock.Properties VANILLA_ICE_CREAM_BLOCK = AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.WHITE_TERRACOTTA).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties CHOCOLATE_ICE_CREAM_BLOCK = AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.BROWN).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties STRAWBERRY_ICE_CREAM_BLOCK = AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.PINK).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties BANANA_ICE_CREAM_BLOCK = AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.YELLOW).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties MINT_ICE_CREAM_BLOCK = AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.LIME).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.2F).sound(SoundType.SNOW);
		public static final AbstractBlock.Properties ADZUKI_ICE_CREAM_BLOCK = AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.RED).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.2F).sound(SoundType.SNOW);

		public static final AbstractBlock.Properties CHOCOLATE = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties CHOCOLATE_BRICKS = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties CHOCOLATE_TILES = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);

		public static final AbstractBlock.Properties STRAWBERRY_BUSH = AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.CROP);
		public static final AbstractBlock.Properties VANILLA_VINE = AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.LIME).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.NETHER_VINE);
		public static final AbstractBlock.Properties MINT = AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.CROP);

		public static final AbstractBlock.Properties BANANA_STALK = AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.BROWN).hardnessAndResistance(1.0F).sound(SoundType.HYPHAE).harvestTool(ToolType.HOE);
		public static final AbstractBlock.Properties BANANA_FROND = AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.LIME).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.NETHER_VINE).harvestTool(ToolType.HOE);
		public static final AbstractBlock.Properties FROND_THATCH = AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.LIME).hardnessAndResistance(0.5F).sound(SoundType.NETHER_SPROUT).harvestTool(ToolType.HOE);

		public static final AbstractBlock.Properties ADZUKI_SPROUTS = AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.CROP);
		public static final AbstractBlock.Properties ADZUKI_SOIL = AbstractBlock.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).tickRandomly().harvestTool(ToolType.SHOVEL).sound(SoundType.GROUND);
		public static final AbstractBlock.Properties BEANSTALK = AbstractBlock.Properties.create(Material.GOURD, MaterialColor.GREEN).hardnessAndResistance(1.0F).harvestTool(ToolType.HOE).setSuffocates((state, reader, pos) -> false).sound(SoundType.HYPHAE);
		public static final AbstractBlock.Properties BEANSTALK_THORNS = AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.GREEN).doesNotBlockMovement().hardnessAndResistance(0.2F).harvestTool(ToolType.HOE).sound(SoundType.FUNGUS);

		public static final AbstractBlock.Properties CHOCOLATE_CAKE = AbstractBlock.Properties.create(Material.CAKE, MaterialColor.BROWN).hardnessAndResistance(0.5F).sound(SoundType.CLOTH);
		public static final AbstractBlock.Properties STRAWBERRY_CAKE = AbstractBlock.Properties.create(Material.CAKE, MaterialColor.PINK).hardnessAndResistance(0.5F).sound(SoundType.CLOTH);
		public static final AbstractBlock.Properties VANILLA_CAKE = AbstractBlock.Properties.create(Material.CAKE, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.CLOTH);
		public static final AbstractBlock.Properties BANANA_CAKE = AbstractBlock.Properties.create(Material.CAKE, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.CLOTH);
		public static final AbstractBlock.Properties MINT_CAKE = AbstractBlock.Properties.create(Material.CAKE, MaterialColor.LIME).hardnessAndResistance(0.5F).sound(SoundType.CLOTH);
		public static final AbstractBlock.Properties ADZUKI_CAKE = AbstractBlock.Properties.create(Material.CAKE, MaterialColor.RED).hardnessAndResistance(0.5F).sound(SoundType.CLOTH);

		public static final AbstractBlock.Properties VANILLA_POD_BLOCK = AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.LIME).harvestTool(ToolType.HOE).hardnessAndResistance(0.5F, 2.5F).sound(SoundType.PLANT);
		public static final AbstractBlock.Properties DRIED_VANILLA_POD_BLOCK = AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.BROWN).harvestTool(ToolType.HOE).hardnessAndResistance(0.5F, 2.5F).sound(SoundType.PLANT);
		public static final AbstractBlock.Properties BANANA_BUNDLE = AbstractBlock.Properties.create(Material.GOURD, MaterialColor.YELLOW).harvestTool(ToolType.HOE).hardnessAndResistance(2.5F).sound(SoundType.WOOD);

		public static final AbstractBlock.Properties STRAWBERRY_BASKET = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties WHITE_STRAWBERRY_BASKET = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties BANANA_CRATE = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(1.5F).sound(SoundType.HYPHAE);
		public static final AbstractBlock.Properties MINT_BASKET = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIME).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties ADZUKI_CRATE = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
		public static final AbstractBlock.Properties ROASTED_ADZUKI_CRATE = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
	}
}
