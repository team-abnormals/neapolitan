package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class NeapolitanBlockTagsProvider extends BlockTagsProvider {

	public NeapolitanBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Neapolitan.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(NeapolitanBlocks.VANILLA_ICE_CREAM_BLOCK.get(), NeapolitanBlocks.CHOCOLATE_ICE_CREAM_BLOCK.get(), NeapolitanBlocks.STRAWBERRY_ICE_CREAM_BLOCK.get(), NeapolitanBlocks.BANANA_ICE_CREAM_BLOCK.get(), NeapolitanBlocks.MINT_ICE_CREAM_BLOCK.get(), NeapolitanBlocks.ADZUKI_ICE_CREAM_BLOCK.get(), NeapolitanBlocks.ADZUKI_SOIL.get());
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), NeapolitanBlocks.CHOCOLATE_BRICKS.get(), NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), NeapolitanBlocks.CHOCOLATE_BRICK_VERTICAL_SLAB.get(), NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), NeapolitanBlocks.CHOCOLATE_TILES.get(), NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), NeapolitanBlocks.CHOCOLATE_TILE_VERTICAL_SLAB.get(), NeapolitanBlocks.STRAWBERRY_BUSH.get(), NeapolitanBlocks.VANILLA_VINE.get(), NeapolitanBlocks.VANILLA_VINE_PLANT.get(), NeapolitanBlocks.MINT.get(), NeapolitanBlocks.ADZUKI_SPROUTS.get(), NeapolitanBlocks.STRAWBERRY_BASKET.get(), NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), NeapolitanBlocks.BANANA_CRATE.get(), NeapolitanBlocks.MINT_BASKET.get(), NeapolitanBlocks.ADZUKI_CRATE.get(), NeapolitanBlocks.ROASTED_ADZUKI_CRATE.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE).add(NeapolitanBlocks.FROND_THATCH.get(), NeapolitanBlocks.FROND_THATCH_SLAB.get(), NeapolitanBlocks.FROND_THATCH_STAIRS.get(), NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), NeapolitanBlocks.BANANA_STALK.get(), NeapolitanBlocks.CARVED_BANANA_STALK.get(), NeapolitanBlocks.SMALL_BANANA_FROND.get(), NeapolitanBlocks.BANANA_FROND.get(), NeapolitanBlocks.LARGE_BANANA_FROND.get(), NeapolitanBlocks.BEANSTALK.get(), NeapolitanBlocks.BEANSTALK_THORNS.get(), NeapolitanBlocks.VANILLA_POD_BLOCK.get(), NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.get(), NeapolitanBlocks.BANANA_BUNDLE.get());
		this.tag(BlockTags.CAULDRONS).add(NeapolitanBlocks.MILK_CAULDRON.get());

		getCandleCakes().forEach((block -> this.tag(BlockTags.CANDLE_CAKES).add(block)));
	}

	public static Iterable<Block> getCandleCakes() {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName() != null && Neapolitan.MOD_ID.equals(block.getRegistryName().getNamespace()) && block instanceof FlavoredCandleCakeBlock).collect(Collectors.toList());
	}
}