package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.blueprint.common.block.thatch.ThatchSlabBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class TrimmableThatchSlabBlock extends ThatchSlabBlock {
	public static final BooleanProperty TRIMMED = BooleanProperty.create("trimmed");

	public TrimmableThatchSlabBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(TRIMMED, false));
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility action, boolean simulate) {
		if (action == ItemAbilities.SHEARS_TRIM && !state.getValue(TRIMMED)) {
			context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), this.soundType.getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
			return state.setValue(TRIMMED, true);
		}
		return super.getToolModifiedState(state, context, action, simulate);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(TRIMMED);
	}
}
