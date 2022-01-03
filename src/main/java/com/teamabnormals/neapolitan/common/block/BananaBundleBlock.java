package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class BananaBundleBlock extends Block {

	public BananaBundleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		super.spawnAfterBreak(state, world, pos, stack);
		if (world.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			this.spawnSpider(world, pos);
		}
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
		if (world instanceof ServerLevel) {
			this.spawnSpider((ServerLevel) world, pos);
		}
	}

	private void spawnSpider(ServerLevel world, BlockPos pos) {
		if (world.getRandom().nextFloat() <= 0.05F && NeapolitanConfig.COMMON.plantainSpidersFromBundles.get()) {
			PlantainSpiderEntity spider = NeapolitanEntityTypes.PLANTAIN_SPIDER.get().create(world);
			spider.moveTo(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, 0.0F, 0.0F);
			world.addFreshEntity(spider);
			if (world.getRandom().nextFloat() <= 0.25F)
				world.addFreshEntity(spider);
			if (world.getRandom().nextFloat() <= 0.45F)
				world.addFreshEntity(spider);
		}
	}
}
