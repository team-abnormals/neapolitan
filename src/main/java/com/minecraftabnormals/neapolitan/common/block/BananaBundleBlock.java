package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;

public class BananaBundleBlock extends DirectionalBlock {
	
	public BananaBundleBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		super.spawnAfterBreak(state, world, pos, stack);
		if (world.getRandom().nextFloat() <= 0.05F && NeapolitanConfig.COMMON.plantainSpidersFromBundles.get() && world.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			this.spawnSpider(world, pos);
		}
	}

	@Override
	public void wasExploded(World world, BlockPos pos, Explosion explosion) {
		if (world.getRandom().nextFloat() <= 0.05F && NeapolitanConfig.COMMON.plantainSpidersFromBundles.get() && world instanceof ServerWorld) {
			this.spawnSpider((ServerWorld)world, pos);
		}
	}

	private void spawnSpider(ServerWorld world, BlockPos pos) {
		PlantainSpiderEntity spider = NeapolitanEntities.PLANTAIN_SPIDER.get().create(world);
		spider.moveTo(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, 0.0F, 0.0F);
		world.addFreshEntity(spider);
		if (world.getRandom().nextFloat() <= 0.25F)
			world.addFreshEntity(spider);
		if (world.getRandom().nextFloat() <= 0.45F)
			world.addFreshEntity(spider);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
	}
}
