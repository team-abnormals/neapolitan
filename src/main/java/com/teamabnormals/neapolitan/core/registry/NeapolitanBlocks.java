package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.*;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.neapolitan.common.block.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

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

	public static final RegistryObject<Block> CHOCOLATE_CAKE = HELPER.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.CHOCOLATE_CAKE, MobEffectCategory.BENEFICIAL, Properties.CHOCOLATE_CAKE));
	public static final RegistryObject<Block> STRAWBERRY_CAKE = HELPER.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.STRAWBERRY_CAKE, MobEffectCategory.HARMFUL, Properties.STRAWBERRY_CAKE));
	public static final RegistryObject<Block> VANILLA_CAKE = HELPER.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.VANILLA_CAKE, MobEffectCategory.NEUTRAL, Properties.VANILLA_CAKE));
	public static final RegistryObject<Block> BANANA_CAKE = HELPER.createBlockNoItem("banana_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.BANANA_CAKE, null, Properties.BANANA_CAKE));
	public static final RegistryObject<Block> MINT_CAKE = HELPER.createBlockNoItem("mint_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.MINT_CAKE, null, Properties.MINT_CAKE));
	public static final RegistryObject<Block> ADZUKI_CAKE = HELPER.createBlockNoItem("adzuki_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.ADZUKI_CAKE, null, Properties.ADZUKI_CAKE));

	public static final RegistryObject<Block> VANILLA_POD_BLOCK = HELPER.createBlock("vanilla_pod_block", () -> new RotatedPillarBlock(Properties.VANILLA_POD_BLOCK), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DRIED_VANILLA_POD_BLOCK = HELPER.createBlock("dried_vanilla_pod_block", () -> new RotatedPillarBlock(Properties.DRIED_VANILLA_POD_BLOCK), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_BUNDLE = HELPER.createBlock("banana_bundle", () -> new BananaBundleBlock(Properties.BANANA_BUNDLE), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "strawberry_basket", () -> new Block(Properties.STRAWBERRY_BASKET), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> WHITE_STRAWBERRY_BASKET = HELPER.createCompatBlock("quark", "white_strawberry_basket", () -> new Block(Properties.WHITE_STRAWBERRY_BASKET), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BANANA_CRATE = HELPER.createCompatBlock("quark", "banana_crate", () -> new Block(Properties.BANANA_CRATE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MINT_BASKET = HELPER.createCompatBlock("quark", "mint_basket", () -> new Block(Properties.MINT_BASKET), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ADZUKI_CRATE = HELPER.createCompatBlock("quark", "adzuki_crate", () -> new Block(Properties.ADZUKI_CRATE), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ROASTED_ADZUKI_CRATE = HELPER.createCompatBlock("quark", "roasted_adzuki_crate", () -> new Block(Properties.ROASTED_ADZUKI_CRATE), CreativeModeTab.TAB_DECORATIONS);

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

		public static final BlockBehaviour.Properties VANILLA_POD_BLOCK = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_LIGHT_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties DRIED_VANILLA_POD_BLOCK = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties BANANA_BUNDLE = BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_YELLOW).strength(2.5F).sound(SoundType.WOOD);

		public static final BlockBehaviour.Properties STRAWBERRY_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties WHITE_STRAWBERRY_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties BANANA_CRATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW).strength(1.5F).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties MINT_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GREEN).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ADZUKI_CRATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ROASTED_ADZUKI_CRATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).strength(1.5F).sound(SoundType.WOOD);
	}
}
