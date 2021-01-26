package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BananaBundleBlock extends Block {

	public BananaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBlockHarvested(world, pos, state, player);

		if (world.getRandom().nextFloat() <= 0.05F) {
			PlantainSpiderEntity spider = NeapolitanEntities.PLANTAIN_SPIDER.get().create(world);
			spider.setLocationAndAngles(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, 0.0F, 0.0F);
			spider.setRevengeTarget(player);
			world.addEntity(spider);
			if (world.getRandom().nextFloat() <= 0.25F)
				world.addEntity(spider);
			if (world.getRandom().nextFloat() <= 0.45F)
				world.addEntity(spider);
		}
	}
}
