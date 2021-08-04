package com.minecraftabnormals.neapolitan.common.block;

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
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D)
	};

	public AdzukiSproutsBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(FLOWERING, false));
	}

	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.entityInside(state, worldIn, pos, entityIn);
		if (entityIn instanceof AnimalEntity && !state.getValue(FLOWERING)) {
			worldIn.setBlockAndUpdate(pos, state.setValue(FLOWERING, true));
			if (worldIn.isClientSide())
				BoneMealItem.addGrowthParticles(worldIn, pos, 0);
		}
	}

	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.ADZUKI_BEANS.get());
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1)) return;
		int i = state.getValue(AGE);
		int speed = state.getValue(FLOWERING) ? 3 : 6;
		if (worldIn.getRawBrightness(pos, 0) >= 9 && !this.isMaxAge(state) && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(speed) == 0)) {
			worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.playerWillDestroy(world, pos, state, player);
		BlockState downState = world.getBlockState(pos.below());
		if (this.isMaxAge(state) && (downState.is(Blocks.DIRT) || downState.is(Blocks.COARSE_DIRT) || downState.is(Blocks.GRASS_BLOCK)))
			world.setBlockAndUpdate(pos.below(), NeapolitanBlocks.ADZUKI_SOIL.get().defaultBlockState());
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(AGE) >= 6;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE, FLOWERING);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_BY_AGE[state.getValue(AGE)];
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader block, BlockPos pos, BlockState state, boolean isClient) {
		return !this.isMaxAge(state);
	}

	@Override
	public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = Math.min(6, state.getValue(AGE) + 1);
		worldIn.setBlock(pos, state.setValue(AGE, i), 2);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.is(NeapolitanBlocks.ADZUKI_SOIL.get()) || super.mayPlaceOn(state, worldIn, pos);
	}
}
