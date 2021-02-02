package com.minecraftabnormals.neapolitan.common.world.gen.feature;

import com.minecraftabnormals.neapolitan.common.block.VanillaVineBlock;
import com.minecraftabnormals.neapolitan.common.block.VanillaVineTopBlock;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class VanillaPatchFeature extends Feature<BlockClusterFeatureConfig> {
	public VanillaPatchFeature(Codec<BlockClusterFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
		BlockPos blockpos;
		if (config.field_227298_k_) {
			blockpos = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		int i = 0;
		BlockPos.Mutable position = new BlockPos.Mutable();

		for (Direction direction : Direction.values()) {
			for (int j = 0; j < config.tryCount; ++j) {
				position.setAndOffset(blockpos, random.nextInt(config.xSpread + 1) - random.nextInt(config.xSpread + 1), random.nextInt(config.ySpread + 1) - random.nextInt(config.ySpread + 1), random.nextInt(config.zSpread + 1) - random.nextInt(config.zSpread + 1));
				BlockPos downPosition = position.offset(direction.getOpposite());
				BlockState downState = world.getBlockState(downPosition);

				BlockState vanillaVine = config.stateProvider.getBlockState(random, pos).with(VanillaVineTopBlock.FACING, direction);
				BlockState vanillaVinePlant = NeapolitanBlocks.VANILLA_VINE_PLANT.get().getDefaultState().with(VanillaVineBlock.FACING, direction);

				if ((world.isAirBlock(position) || config.isReplaceable && world.getBlockState(position).getMaterial().isReplaceable()) && vanillaVine.isValidPosition(world, position) && (config.whitelist.isEmpty() || config.whitelist.contains(downState.getBlock())) && !config.blacklist.contains(downState) && (!config.requiresWater || world.getFluidState(downPosition.west()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.east()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.north()).isTagged(FluidTags.WATER) || world.getFluidState(downPosition.south()).isTagged(FluidTags.WATER))) {
					if (!downState.isIn(config.stateProvider.getBlockState(random, pos).getBlock())) {
						config.blockPlacer.place(world, position, vanillaVine, random);
						if (world.getBlockState(position.offset(direction)).isAir(world, position.offset(direction))) {
							config.blockPlacer.place(world, position, vanillaVinePlant, random);
							config.blockPlacer.place(world, position.offset(direction), vanillaVine, random);
						}
					}
					++i;
				}
			}
		}

		return i > 0;
	}
}