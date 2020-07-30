package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.common.block.ChocolateCakeBlock;
import com.minecraftabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.minecraftabnormals.neapolitan.common.block.StrawberryCakeBlock;
import com.minecraftabnormals.neapolitan.common.block.VanillaCakeBlock;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanBlocks {
	public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;
		
	public static final RegistryObject<Block> CHOCOLATE_BLOCK 			= HELPER.createBlock("chocolate_block", () -> new Block(Properties.CHOCOLATE), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_BRICKS 				= HELPER.createBlock("chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB 			= HELPER.createBlock("chocolate_brick_slab", () -> new SlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS 		= HELPER.createBlock("chocolate_brick_stairs", () -> new StairsBlock(() -> CHOCOLATE_BRICKS.get().getDefaultState(), Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL 			= HELPER.createBlock("chocolate_brick_wall", () -> new WallBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHOCOLATE_TILES 				= HELPER.createBlock("chocolate_tiles", () -> new Block(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB 			= HELPER.createBlock("chocolate_tile_slab", () -> new SlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS 		= HELPER.createBlock("chocolate_tile_stairs", () -> new StairsBlock(() -> CHOCOLATE_TILES.get().getDefaultState(), Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_WALL 			= HELPER.createBlock("chocolate_tile_wall", () -> new WallBlock(Properties.CHOCOLATE_TILES), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHOCOLATE_TILE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "chocolate_tile_vertical_slab",	() -> new VerticalSlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRAWBERRY_BUSH = HELPER.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(Properties.STRAWBERRY_BUSH));
	
	public static final RegistryObject<Block> CHOCOLATE_CAKE 	= HELPER.createBlockNoItem("chocolate_cake", () -> new ChocolateCakeBlock(Properties.CHOCOLATE_CAKE));
	public static final RegistryObject<Block> STRAWBERRY_CAKE 	= HELPER.createBlockNoItem("strawberry_cake", () -> new StrawberryCakeBlock(Properties.STRAWBERRY_CAKE));
	public static final RegistryObject<Block> VANILLA_CAKE 		= HELPER.createBlockNoItem("vanilla_cake", () -> new VanillaCakeBlock(Properties.VANILLA_CAKE));
	
	static class Properties {
		public static final Block.Properties VANILLA 			= Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT);
	    public static final Block.Properties CHOCOLATE 			= Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	    public static final Block.Properties CHOCOLATE_BRICKS 	= Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	    public static final Block.Properties CHOCOLATE_TILES 	= Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
	    
	    public static final Block.Properties STRAWBERRY_BUSH 	= Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.CROP);
	    
	    public static final Block.Properties CHOCOLATE_CAKE 	= Block.Properties.from(Blocks.CAKE);
	    public static final Block.Properties STRAWBERRY_CAKE 	= Block.Properties.from(Blocks.CAKE);
	    public static final Block.Properties VANILLA_CAKE 		= Block.Properties.from(Blocks.CAKE);
	}
}
