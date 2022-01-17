package com.teamabnormals.neapolitan.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.neapolitan.common.block.VanillaVineBlock;
import com.teamabnormals.neapolitan.common.block.VanillaVineTopBlock;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

import java.util.Random;

public class VanillaPatchFeature extends Feature<RandomPatchConfiguration> {
	public VanillaPatchFeature(Codec<RandomPatchConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
		RandomPatchConfiguration config = context.config();
		Random random = context.random();
		BlockPos origin = context.origin();
		WorldGenLevel level = context.level();
		BlockState state = NeapolitanBlocks.VANILLA_VINE.get().defaultBlockState();
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

		int i = 0;
		int j = config.xzSpread() + 1;
		int k = config.ySpread() + 1;

		for (Direction direction : Direction.values()) {
			for (int l = 0; l < config.tries(); ++l) {
				mutablePos.setWithOffset(origin, random.nextInt(j) - random.nextInt(j), random.nextInt(k) - random.nextInt(k), random.nextInt(j) - random.nextInt(j));
				BlockPos downPosition = mutablePos.relative(direction.getOpposite());
				BlockState downState = level.getBlockState(downPosition);

				BlockState vanillaVine = state.setValue(VanillaVineTopBlock.FACING, direction);
				BlockState vanillaVinePlant = NeapolitanBlocks.VANILLA_VINE_PLANT.get().defaultBlockState().setValue(VanillaVineBlock.FACING, direction);

				if ((level.isEmptyBlock(mutablePos) || level.getBlockState(mutablePos).getMaterial().isReplaceable()) && vanillaVine.canSurvive(level, mutablePos)) {
					if (!downState.is(state.getBlock())) {
						level.setBlock(mutablePos, vanillaVine, 2);
						if (level.getBlockState(mutablePos.relative(direction)).isAir()) {
							level.setBlock(mutablePos, vanillaVinePlant, 2);
							level.setBlock(mutablePos.relative(direction), vanillaVine, 2);
						}
					}
					++i;
				}
			}
		}

		return i > 0;
	}
}