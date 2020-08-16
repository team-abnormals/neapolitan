package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.common.block.FlavoredCakeBlock;
import com.minecraftabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.minecraftabnormals.neapolitan.common.block.VanillaVineBlock;
import com.minecraftabnormals.neapolitan.common.block.VanillaVineTopBlock;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanBlocks {
    public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;

    public static final RegistryObject<Block> CHOCOLATE_BLOCK = HELPER.createBlock("chocolate_block", () -> new Block(Properties.CHOCOLATE), ItemGroup.BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHOCOLATE_BRICKS              = HELPER.createBlock("chocolate_bricks", () -> new Block(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHOCOLATE_BRICK_SLAB          = HELPER.createBlock("chocolate_brick_slab", () -> new SlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHOCOLATE_BRICK_STAIRS        = HELPER.createBlock("chocolate_brick_stairs", () -> new StairsBlock(() -> CHOCOLATE_BRICKS.get().getDefaultState(), Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHOCOLATE_BRICK_WALL          = HELPER.createBlock("chocolate_brick_wall", () -> new WallBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> CHOCOLATE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "chocolate_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_BRICKS), ItemGroup.BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHOCOLATE_TILES               = HELPER.createBlock("chocolate_tiles", () -> new Block(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHOCOLATE_TILE_SLAB           = HELPER.createBlock("chocolate_tile_slab", () -> new SlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHOCOLATE_TILE_STAIRS         = HELPER.createBlock("chocolate_tile_stairs", () -> new StairsBlock(() -> CHOCOLATE_TILES.get().getDefaultState(), Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHOCOLATE_TILE_WALL           = HELPER.createBlock("chocolate_tile_wall", () -> new WallBlock(Properties.CHOCOLATE_TILES), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> CHOCOLATE_TILE_VERTICAL_SLAB  = HELPER.createCompatBlock("quark", "chocolate_tile_vertical_slab", () -> new VerticalSlabBlock(Properties.CHOCOLATE_TILES), ItemGroup.BUILDING_BLOCKS);

    public static final RegistryObject<Block> STRAWBERRY_BUSH = HELPER.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(Properties.STRAWBERRY_BUSH));

    public static final RegistryObject<Block> VANILLA_VINE        = HELPER.createBlockNoItem("vanilla_vine", () -> new VanillaVineTopBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.LIME).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.NETHER_VINE)));
    public static final RegistryObject<Block> VANILLA_VINE_PLANT  = HELPER.createBlockNoItem("vanilla_vine_plant", () -> new VanillaVineBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.LIME).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.NETHER_VINE)));

    public static final RegistryObject<Block> CHOCOLATE_CAKE    = HELPER.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.CHOCOLATE_CAKE, EffectType.HARMFUL, Properties.CHOCOLATE_CAKE));
    public static final RegistryObject<Block> STRAWBERRY_CAKE   = HELPER.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.STRAWBERRY_CAKE, EffectType.BENEFICIAL, Properties.STRAWBERRY_CAKE));
    public static final RegistryObject<Block> VANILLA_CAKE      = HELPER.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanItems.Foods.VANILLA_CAKE, EffectType.NEUTRAL, Properties.VANILLA_CAKE));
    
    public static final RegistryObject<Block> STRAWBERRY_BASKET         = HELPER.createCompatBlock("quark", "strawberry_basket", () -> new Block(Properties.STRAWBERRY_BASKET), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> WHITE_STRAWBERRY_BASKET   = HELPER.createCompatBlock("quark", "white_strawberry_basket", () -> new Block(Properties.WHITE_STRAWBERRY_BASKET), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> VANILLA_BEAN_BLOCK        = HELPER.createBlock("vanilla_bean_block", () -> new RotatedPillarBlock(Properties.VANILLA_BEAN_BLOCK), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> DRIED_VANILLA_BEAN_BLOCK  = HELPER.createBlock("dried_vanilla_bean_block", () -> new RotatedPillarBlock(Properties.DRIED_VANILLA_BEAN_BLOCK), ItemGroup.DECORATIONS);

    static class Properties {
        public static final AbstractBlock.Properties VANILLA            = AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT);
        public static final AbstractBlock.Properties CHOCOLATE          = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties CHOCOLATE_BRICKS   = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties CHOCOLATE_TILES    = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);

        public static final AbstractBlock.Properties STRAWBERRY_BUSH    = AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.CROP);

        public static final AbstractBlock.Properties CHOCOLATE_CAKE     = AbstractBlock.Properties.from(Blocks.CAKE);
        public static final AbstractBlock.Properties STRAWBERRY_CAKE    = AbstractBlock.Properties.from(Blocks.CAKE);
        public static final AbstractBlock.Properties VANILLA_CAKE       = AbstractBlock.Properties.from(Blocks.CAKE);

        public static final AbstractBlock.Properties STRAWBERRY_BASKET          = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties WHITE_STRAWBERRY_BASKET    = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties VANILLA_BEAN_BLOCK         = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIME).hardnessAndResistance(0.5F, 2.5F).sound(SoundType.PLANT);
        public static final AbstractBlock.Properties DRIED_VANILLA_BEAN_BLOCK   = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(0.5F, 2.5F).sound(SoundType.PLANT);
    }
}
