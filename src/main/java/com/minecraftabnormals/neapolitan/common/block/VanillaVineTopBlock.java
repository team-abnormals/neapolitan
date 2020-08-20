package com.minecraftabnormals.neapolitan.common.block;

import java.util.Random;

import com.minecraftabnormals.neapolitan.common.block.api.IPoisonCloud;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VanillaVineTopBlock extends Block implements IPoisonCloud, IGrowable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public VanillaVineTopBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.UP));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState otherState = worldIn.getBlockState(pos.offset(state.get(FACING).getOpposite()));
        Block block = otherState.getBlock();
        return facingSameDirection(state, otherState) || block.isIn(NeapolitanTags.Blocks.VANILLA_PLANTABLE_ON);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public static boolean facingSameDirection(BlockState state, BlockState otherState) {
        Block block = otherState.getBlock();
        if (block == NeapolitanBlocks.VANILLA_VINE.get() || block == NeapolitanBlocks.VANILLA_VINE_PLANT.get()) {
            if (otherState.get(FACING) == state.get(FACING)) {
                return true;
            }
        }

        return false;
    }

    protected int getGrowthAmount(Random rand) {
        return rand.nextInt(2) + 1;
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(world, pos, state, player);
        this.createPoisonCloud(world, pos, state, player);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (this.canGrowUp(state, worldIn, pos) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.offset(state.get(FACING)), worldIn.getBlockState(pos.offset(state.get(FACING))), random.nextDouble() < 0.1D)) {
            BlockPos blockpos = pos.offset(state.get(FACING));
            if (this.canGrowIn(worldIn.getBlockState(blockpos))) {
                worldIn.setBlockState(blockpos, state);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, blockpos, worldIn.getBlockState(blockpos));
            }
        }
    }
    
    private boolean canGrowUp(BlockState state, ServerWorld world, BlockPos pos) {
        Direction facing = state.get(FACING);
        if (Direction.Plane.VERTICAL.test(facing)) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                if (world.getBlockState(pos.offset(direction)).isSolid()) {
                    return true;
                }
            }
        } else {
            for(Direction direction : Direction.Plane.VERTICAL) {
                if (world.getBlockState(pos.offset(direction)).isSolid()) {
                    return true;
                }
            }
            
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                if (direction.getAxis() != Axis.Y && direction.getAxis() != facing.getAxis()) {
                    if (world.getBlockState(pos.offset(direction)).isSolid()) {
                        return true;
                    }
                }
            }
        }
        
        return world.getBlockState(pos.offset(facing.getOpposite())).isIn(NeapolitanTags.Blocks.VANILLA_PLANTABLE_ON);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == stateIn.get(FACING).getOpposite() && !stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }
        if (facing == stateIn.get(FACING) && facingState.isIn(this)) {
            return NeapolitanBlocks.VANILLA_VINE_PLANT.get().getDefaultState().with(FACING, stateIn.get(FACING));
        } else {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return this.canGrowIn(worldIn.getBlockState(pos.offset(state.get(FACING))));
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.offset(state.get(FACING));
        int j = this.getGrowthAmount(rand);

        for (int k = 0; k < j && this.canGrowIn(worldIn.getBlockState(blockpos)); ++k) {
            worldIn.setBlockState(blockpos, state);
            blockpos = blockpos.offset(state.get(FACING));
        }
    }

    protected boolean canGrowIn(BlockState state) {
        return PlantBlockHelper.isAir(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getFace());
    }
}