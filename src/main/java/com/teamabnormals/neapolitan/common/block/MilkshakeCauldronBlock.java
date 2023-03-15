package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.NeapolitanBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.function.Predicate;

public class MilkshakeCauldronBlock extends LayeredCauldronBlock {
	public static final Predicate<Biome.Precipitation> FALSE = (precipitation) -> false;

	public MilkshakeCauldronBlock(Map<Item, CauldronInteraction> map) {
		super(NeapolitanBlockProperties.CAULDRON, FALSE, map);
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.CAULDRON);
	}
}