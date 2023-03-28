package com.teamabnormals.neapolitan.common.block.entity;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NeapolitanSkullBlockEntity extends SkullBlockEntity {

	public NeapolitanSkullBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return NeapolitanBlockEntityTypes.SKULL.get();
	}
}