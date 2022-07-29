package com.teamabnormals.neapolitan.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.neapolitan.common.block.AdzukiSproutsBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class AdzukiSproutsFeature extends Feature<SimpleBlockConfiguration> {

	public AdzukiSproutsFeature(Codec<SimpleBlockConfiguration> config) {
		super(config);
	}

	public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
		SimpleBlockConfiguration config = context.config();
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		BlockPos downPos = pos.below();
		BlockState state = config.toPlace().getState(context.random(), pos);
		if (state.canSurvive(level, pos)) {
			level.setBlock(pos, state.setValue(AdzukiSproutsBlock.AGE, 6), 2);
			level.setBlock(downPos, Blocks.DIRT.defaultBlockState(), 2);
			return true;
		} else {
			return false;
		}
	}
}