package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.*;

public class NeapolitanBlockTagsProvider extends BlockTagsProvider {

	public NeapolitanBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Neapolitan.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(BlockTags.CAULDRONS).add(MILK_CAULDRON.get());
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(CHOCOLATE_BLOCK.get(), CHOCOLATE_BRICKS.get(), CHOCOLATE_BRICK_SLAB.get(), CHOCOLATE_BRICK_STAIRS.get(), CHOCOLATE_BRICK_WALL.get(), CHOCOLATE_BRICK_VERTICAL_SLAB.get(), CHISELED_CHOCOLATE_BRICKS.get(), CHOCOLATE_TILES.get(), CHOCOLATE_TILE_SLAB.get(), CHOCOLATE_TILE_STAIRS.get(), CHOCOLATE_TILE_WALL.get(), CHOCOLATE_TILE_VERTICAL_SLAB.get(), STRAWBERRY_BUSH.get(), VANILLA_VINE.get(), VANILLA_VINE_PLANT.get(), MINT.get(), ADZUKI_SPROUTS.get(), STRAWBERRY_BASKET.get(), WHITE_STRAWBERRY_BASKET.get(), BANANA_CRATE.get(), MINT_BASKET.get(), ADZUKI_CRATE.get(), ROASTED_ADZUKI_CRATE.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE).add(FROND_THATCH.get(), FROND_THATCH_SLAB.get(), FROND_THATCH_STAIRS.get(), FROND_THATCH_VERTICAL_SLAB.get(), BANANA_STALK.get(), CARVED_BANANA_STALK.get(), SMALL_BANANA_FROND.get(), BANANA_FROND.get(), LARGE_BANANA_FROND.get(), BEANSTALK.get(), BEANSTALK_THORNS.get(), VANILLA_POD_BLOCK.get(), DRIED_VANILLA_POD_BLOCK.get(), BANANA_BUNDLE.get());
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(VANILLA_ICE_CREAM_BLOCK.get(), CHOCOLATE_ICE_CREAM_BLOCK.get(), STRAWBERRY_ICE_CREAM_BLOCK.get(), BANANA_ICE_CREAM_BLOCK.get(), MINT_ICE_CREAM_BLOCK.get(), ADZUKI_ICE_CREAM_BLOCK.get(), ADZUKI_SOIL.get());
		this.tag(BlockTags.SLABS).add(CHOCOLATE_BRICK_SLAB.get(), CHOCOLATE_TILE_SLAB.get(), FROND_THATCH_SLAB.get());
		this.tag(BlockTags.STAIRS).add(CHOCOLATE_BRICK_STAIRS.get(), CHOCOLATE_TILE_STAIRS.get(), FROND_THATCH_STAIRS.get());
		this.tag(BlockTags.WALLS).add(CHOCOLATE_BRICK_WALL.get(), CHOCOLATE_TILE_WALL.get());
		this.tag(BlockTags.FLOWER_POTS).add(POTTED_VANILLA_VINE.get(), POTTED_MINT.get(), POTTED_BANANA_FROND.get());

		this.tag(NeapolitanBlockTags.CHIMPANZEE_JUMPING_BLOCKS).addTag(BlockTags.BEDS).add(Blocks.SLIME_BLOCK);
		this.tag(NeapolitanBlockTags.UNAFFECTED_BY_MINT).add(MINT.get());
		this.tag(NeapolitanBlockTags.VANILLA_PLANTABLE_ON).addTag(BlockTags.LEAVES).addTag(BlockTags.LOGS_THAT_BURN).addTag(BlockTags.DIRT);

		this.tag(NeapolitanBlockTags.DROPS_VANILLA_CAKE_SLICE).add(VANILLA_CAKE.get());
		this.tag(NeapolitanBlockTags.DROPS_CHOCOLATE_CAKE_SLICE).add(CHOCOLATE_CAKE.get());
		this.tag(NeapolitanBlockTags.DROPS_STRAWBERRY_CAKE_SLICE).add(STRAWBERRY_CAKE.get());
		this.tag(NeapolitanBlockTags.DROPS_BANANA_CAKE_SLICE).add(BANANA_CAKE.get());
		this.tag(NeapolitanBlockTags.DROPS_MINT_CAKE_SLICE).add(MINT_CAKE.get());
		this.tag(NeapolitanBlockTags.DROPS_ADZUKI_CAKE_SLICE).add(ADZUKI_CAKE.get());

		this.tag(BlueprintBlockTags.VERTICAL_SLABS).add(CHOCOLATE_BRICK_VERTICAL_SLAB.get(), CHOCOLATE_TILE_VERTICAL_SLAB.get(), FROND_THATCH_VERTICAL_SLAB.get());

		FlavoredCandleCakeBlock.getCandleCakes().forEach(block -> {
			String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
			this.tag(BlockTags.CANDLE_CAKES).add(block);
			if (name.contains("vanilla")) this.tag(NeapolitanBlockTags.DROPS_VANILLA_CAKE_SLICE).add(block);
			if (name.contains("chocolate")) this.tag(NeapolitanBlockTags.DROPS_CHOCOLATE_CAKE_SLICE).add(block);
			if (name.contains("strawberry")) this.tag(NeapolitanBlockTags.DROPS_STRAWBERRY_CAKE_SLICE).add(block);
			if (name.contains("banana")) this.tag(NeapolitanBlockTags.DROPS_BANANA_CAKE_SLICE).add(block);
			if (name.contains("mint")) this.tag(NeapolitanBlockTags.DROPS_MINT_CAKE_SLICE).add(block);
			if (name.contains("adzuki")) this.tag(NeapolitanBlockTags.DROPS_ADZUKI_CAKE_SLICE).add(block);
		});
	}
}