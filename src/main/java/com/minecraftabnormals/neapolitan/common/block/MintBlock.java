package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction.Plane;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class MintBlock extends BushBlock implements IPlantable, IGrowable {
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	public static final IntegerProperty SPROUTS = IntegerProperty.create("sprouts", 1, 4);
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D)
	};

	public MintBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(SPROUTS, 1));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!this.isMaxAge(state) && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (this.isMaxAge(state)) {
			spawnAsEntity(worldIn, pos, new ItemStack(NeapolitanItems.MINT_LEAVES.get(), state.get(SPROUTS)));
			worldIn.playSound(null, pos, SoundEvents.BLOCK_CROP_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, 1), 2);
			return ActionResultType.func_233537_a_(worldIn.isRemote);
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItem().getItem() == this.asItem() && state.get(SPROUTS) < 4 || super.isReplaceable(state, useContext);
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityCollision(state, worldIn, pos, entityIn);
	}

	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.MINT_SPROUT.get());
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1)) return;
		int i = state.get(AGE);
		if (worldIn.getLightSubtracted(pos, 0) >= 9 && !this.isMaxAge(state) && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(9) == 0)) {
			worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);

		} else {
			Plane.HORIZONTAL.getDirectionValues().forEach(direction -> {
				BlockPos offsetPos = pos.offset(direction);
				BlockState offsetState = worldIn.getBlockState(offsetPos);
				if (offsetState.getBlock() instanceof IGrowable && random.nextFloat() <= 0.6F) {
					IGrowable igrowable = (IGrowable) offsetState.getBlock();
					if (igrowable.canGrow(worldIn, offsetPos, offsetState, false)) {
						igrowable.grow(worldIn, worldIn.rand, offsetPos, offsetState);
					}
				}
			});
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getWorld().getBlockState(context.getPos());
		if (blockstate.isIn(this)) {
			return blockstate.with(SPROUTS, Math.min(4, blockstate.get(SPROUTS) + 1));
		}
		return super.getStateForPlacement(context);
	}

	public boolean isMaxAge(BlockState state) {
		return state.get(AGE) >= 4;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE, SPROUTS);
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
		int i = Math.min(4, state.get(AGE) + 1);
		worldIn.setBlockState(pos, state.with(AGE, i), 2);
	}
}
