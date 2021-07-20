package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraft.block.AbstractBlock.Properties;

public class BananaBundleBlock extends Block {

	public BananaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.playerWillDestroy(world, pos, state, player);

		if (world.getRandom().nextFloat() <= 0.05F && NeapolitanConfig.COMMON.plantainSpidersFromBundles.get()) {
			PlantainSpiderEntity spider = NeapolitanEntities.PLANTAIN_SPIDER.get().create(world);
			spider.moveTo(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, 0.0F, 0.0F);
			spider.setLastHurtByMob(player);
			world.addFreshEntity(spider);
			if (world.getRandom().nextFloat() <= 0.25F)
				world.addFreshEntity(spider);
			if (world.getRandom().nextFloat() <= 0.45F)
				world.addFreshEntity(spider);
		}
	}
}
