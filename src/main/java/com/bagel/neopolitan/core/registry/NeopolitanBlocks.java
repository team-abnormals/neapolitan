package com.bagel.neopolitan.core.registry;

import com.bagel.neopolitan.common.block.VerticalSlabBlock;
import com.bagel.neopolitan.core.Neopolitan;
import com.bagel.neopolitan.core.util.BlockProperties;
import com.bagel.neopolitan.core.util.RegistryUtils;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NeopolitanBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Neopolitan.MODID);

//	public static final RegistryObject<Block> VANILLA = RegistryUtils.createBlock("vanilla", () -> new FlowerBlock(Effects.POISON, 8, BlockProperties.VANILLA), ItemGroup.DECORATIONS);
	
	public static final RegistryObject<Block> CHOCOLATE_BLOCK 			= RegistryUtils.createBlock("chocolate_block", 			() -> new Block(BlockProperties.CHOCOLATE), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_BRICKS 				= RegistryUtils.createBlock("chocolate_bricks", 			() -> new Block(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB 			= RegistryUtils.createBlock("chocolate_brick_slab", 		() -> new SlabBlock(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS 		= RegistryUtils.createBlock("chocolate_brick_stairs", 		() -> new StairsBlock(() -> CHOCOLATE_BRICKS.get().getDefaultState(), BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL 			= RegistryUtils.createBlock("chocolate_brick_wall", 		() -> new WallBlock(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_VERTICAL_SLAB = RegistryUtils.createBlockCompat("quark", "chocolate_brick_vertical_slab",() -> new VerticalSlabBlock(BlockProperties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_TILES 				= RegistryUtils.createBlock("chocolate_tiles", 				() -> new Block(BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB 			= RegistryUtils.createBlock("chocolate_tile_slab", 			() -> new SlabBlock(BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS 		= RegistryUtils.createBlock("chocolate_tile_stairs",		() -> new StairsBlock(() -> CHOCOLATE_TILES.get().getDefaultState(), BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_WALL 			= RegistryUtils.createBlock("chocolate_tile_wall", 			() -> new WallBlock(BlockProperties.CHOCOLATE_TILES), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_VERTICAL_SLAB 	= RegistryUtils.createBlockCompat("quark", "chocolate_tile_vertical_slab",	() -> new VerticalSlabBlock(BlockProperties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
}
