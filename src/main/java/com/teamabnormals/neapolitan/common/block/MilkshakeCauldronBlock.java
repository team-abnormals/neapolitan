package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.NeapolitanBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MilkshakeCauldronBlock extends LayeredCauldronBlock {

	public MilkshakeCauldronBlock(CauldronInteraction.InteractionMap map) {
		super(null, map, NeapolitanBlockProperties.CAULDRON);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.CAULDRON);
	}
}