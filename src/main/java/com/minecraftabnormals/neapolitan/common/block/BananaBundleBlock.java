package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BananaBundleBlock extends Block {

	public BananaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		super.spawnAfterBreak(state, world, pos, stack);
		if (world.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			this.spawnSpider(world, pos);
		}
	}

	@Override
	public void wasExploded(World world, BlockPos pos, Explosion explosion) {
		if (world instanceof ServerWorld) {
			this.spawnSpider((ServerWorld) world, pos);
		}
	}

	private void spawnSpider(ServerWorld world, BlockPos pos) {
		if (world.getRandom().nextFloat() <= 0.05F && NeapolitanConfig.COMMON.plantainSpidersFromBundles.get()) {
			PlantainSpiderEntity spider = NeapolitanEntities.PLANTAIN_SPIDER.get().create(world);
			spider.moveTo(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, 0.0F, 0.0F);
			world.addFreshEntity(spider);
			if (world.getRandom().nextFloat() <= 0.25F)
				world.addFreshEntity(spider);
			if (world.getRandom().nextFloat() <= 0.45F)
				world.addFreshEntity(spider);
		}
	}
}
