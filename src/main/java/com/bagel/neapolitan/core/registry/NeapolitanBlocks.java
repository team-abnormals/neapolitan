package com.bagel.neapolitan.core.registry;

import com.bagel.neapolitan.common.block.ChocolateCakeBlock;
import com.bagel.neapolitan.common.block.StrawberryBushBlock;
import com.bagel.neapolitan.common.block.StrawberryCakeBlock;
import com.bagel.neapolitan.common.block.VanillaCakeBlock;
import com.bagel.neapolitan.core.Neapolitan;
import com.bagel.neapolitan.core.util.BlockProperties;
import com.minecraftabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import com.minecraftabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanBlocks {
	public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;
		
	public static final RegistryObject<Block> CHOCOLATE_BLOCK 			= HELPER.createBlock("chocolate_block", 			() -> new Block(BlockProperties.CHOCOLATE), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_BRICKS 				= HELPER.createBlock("chocolate_bricks", 			() -> new Block(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB 			= HELPER.createBlock("chocolate_brick_slab", 		() -> new SlabBlock(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS 		= HELPER.createBlock("chocolate_brick_stairs", 		() -> new StairsBlock(() -> CHOCOLATE_BRICKS.get().getDefaultState(), BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL 			= HELPER.createBlock("chocolate_brick_wall", 		() -> new WallBlock(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_brick_vertical_slab",() -> new VerticalSlabBlock(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_TILES 				= HELPER.createBlock("chocolate_tiles", 				() -> new Block(BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB 			= HELPER.createBlock("chocolate_tile_slab", 			() -> new SlabBlock(BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS 		= HELPER.createBlock("chocolate_tile_stairs",			() -> new StairsBlock(() -> CHOCOLATE_TILES.get().getDefaultState(), BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_WALL 			= HELPER.createBlock("chocolate_tile_wall", 			() -> new WallBlock(BlockProperties.CHOCOLATE_TILES), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "chocolate_tile_vertical_slab",	() -> new VerticalSlabBlock(BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRAWBERRY_BUSH = HELPER.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(BlockProperties.STRAWBERRY_BUSH));
	
	public static final RegistryObject<Block> CHOCOLATE_CAKE 	= HELPER.createBlockNoItem("chocolate_cake", () -> new ChocolateCakeBlock(BlockProperties.CHOCOLATE_CAKE));
	public static final RegistryObject<Block> STRAWBERRY_CAKE 	= HELPER.createBlockNoItem("strawberry_cake", () -> new StrawberryCakeBlock(BlockProperties.STRAWBERRY_CAKE));
	public static final RegistryObject<Block> VANILLA_CAKE 		= HELPER.createBlockNoItem("vanilla_cake", () -> new VanillaCakeBlock(BlockProperties.VANILLA_CAKE));
}
