package com.teamabnormals.neapolitan.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class StrawberryBushFeature extends Feature<SimpleBlockConfiguration> {
	public StrawberryBushFeature(Codec<SimpleBlockConfiguration> config) {
		super(config);
	}

	public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
		SimpleBlockConfiguration config = context.config();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		BlockPos origin = context.origin();
		BlockPos downPos = origin.below();
		BlockState state = config.toPlace().getState(random, origin);
		if (state.canSurvive(level, origin)) {
			if (random.nextInt(10) != 0) {
				int age = level.getBlockState(origin.below()).is(Blocks.COARSE_DIRT) ? 2 : 6;
				level.setBlock(origin, state.setValue(StrawberryBushBlock.AGE, age), 2);
			} else {
				level.setBlock(origin, state.setValue(StrawberryBushBlock.TYPE, StrawberryBushBlock.StrawberryType.NONE).setValue(StrawberryBushBlock.AGE, 2), 2);
				level.setBlock(downPos, Blocks.COARSE_DIRT.defaultBlockState(), 2);
			}

			for (Direction direction : Direction.Plane.HORIZONTAL) {
				BlockState offsetState = level.getBlockState(origin.relative(direction));
				BlockState offsetDownState = level.getBlockState(downPos.relative(direction));

				if (!(offsetState.is(NeapolitanBlocks.STRAWBERRY_BUSH.get()) && offsetState.getValue(StrawberryBushBlock.AGE) != 2) && (offsetDownState.is(Blocks.DIRT) || offsetDownState.is(Blocks.GRASS_BLOCK))) {
					if (random.nextBoolean()) {
						level.setBlock(downPos.relative(direction), Blocks.COARSE_DIRT.defaultBlockState(), 2);
					}
				}
			}

			return true;
		} else {
			return false;
		}
	}
}