package com.teamabnormals.neapolitan.core.other;

import net.minecraft.data.BlockFamily;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.*;

public class NeapolitanBlockFamilies {
	public static final BlockFamily FROND_THATCH_FAMILY = new BlockFamily.Builder(FROND_THATCH.get()).stairs(FROND_THATCH_STAIRS.get()).slab(FROND_THATCH_SLAB.get()).getFamily();
	public static final BlockFamily CHOCOLATE_BRICK_FAMILY = new BlockFamily.Builder(CHOCOLATE_BRICKS.get()).stairs(CHOCOLATE_BRICK_STAIRS.get()).slab(CHOCOLATE_BRICK_SLAB.get()).wall(CHOCOLATE_BRICK_WALL.get()).chiseled(CHISELED_CHOCOLATE_BRICKS.get()).getFamily();
	public static final BlockFamily CHOCOLATE_TILE_FAMILY = new BlockFamily.Builder(CHOCOLATE_TILES.get()).stairs(CHOCOLATE_TILE_STAIRS.get()).slab(CHOCOLATE_TILE_SLAB.get()).wall(CHOCOLATE_TILE_WALL.get()).getFamily();
	public static final BlockFamily WAFFLE_CONE_TILE_FAMILY = new BlockFamily.Builder(WAFFLE_CONE_TILES.get()).stairs(WAFFLE_CONE_TILE_STAIRS.get()).slab(WAFFLE_CONE_TILE_SLAB.get()).wall(WAFFLE_CONE_TILE_WALL.get()).getFamily();
}