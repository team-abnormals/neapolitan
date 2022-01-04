package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
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
		getCandleCakes().forEach((block -> this.tag(BlockTags.CANDLE_CAKES).add(block)));
	}

	public static Iterable<Block> getCandleCakes() {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName() != null && Neapolitan.MOD_ID.equals(block.getRegistryName().getNamespace()) && block instanceof FlavoredCandleCakeBlock).collect(Collectors.toList());
	}
}