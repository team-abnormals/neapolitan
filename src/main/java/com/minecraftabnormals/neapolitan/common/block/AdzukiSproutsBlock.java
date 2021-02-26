package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class AdzukiSproutsBlock extends BushBlock implements IPlantable, IGrowable {
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
	public static final BooleanProperty FLOWERING = BooleanProperty.create("flowering");
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D)
	};

	public AdzukiSproutsBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(FLOWERING, false));
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityCollision(state, worldIn, pos, entityIn);
		if (entityIn instanceof AnimalEntity && !state.get(FLOWERING)) {
			worldIn.setBlockState(pos, state.with(FLOWERING, true));
			if (worldIn.isRemote())
				BoneMealItem.spawnBonemealParticles(worldIn, pos, 0);
		}
	}

	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.ADZUKI_BEANS.get());
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1)) return;
		int i = state.get(AGE);
		int speed = state.get(FLOWERING) ? 3 : 6;
		if (worldIn.getLightSubtracted(pos, 0) >= 9 && !this.isMaxAge(state) && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(speed) == 0)) {
			worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBlockHarvested(world, pos, state, player);
		BlockState downState = world.getBlockState(pos.down());
		if (this.isMaxAge(state) && (downState.isIn(Blocks.DIRT) || downState.isIn(Blocks.COARSE_DIRT) || downState.isIn(Blocks.GRASS_BLOCK)))
			world.setBlockState(pos.down(), NeapolitanBlocks.ADZUKI_SOIL.get().getDefaultState());
	}

	public boolean isMaxAge(BlockState state) {
		return state.get(AGE) >= 6;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE, FLOWERING);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_BY_AGE[state.get(AGE)];
	}

	@Override
	public boolean canGrow(IBlockReader block, BlockPos pos, BlockState state, boolean isClient) {
		return !this.isMaxAge(state);
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = Math.min(6, state.get(AGE) + 1);
		worldIn.setBlockState(pos, state.with(AGE, i), 2);
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.isIn(NeapolitanBlocks.ADZUKI_SOIL.get()) || super.isValidGround(state, worldIn, pos);
	}
}
