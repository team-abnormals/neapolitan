package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.common.block.entity.NeapolitanSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NeapolitanSkullBlock extends SkullBlock {

	public NeapolitanSkullBlock(Type type, Properties props) {
		super(type, props);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NeapolitanSkullBlockEntity(pos, state);
	}
}