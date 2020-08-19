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
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VanillaVineTopBlock extends Block implements IPoisonCloud, IGrowable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);
    private final double growthChance;

    public VanillaVineTopBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.growthChance = 0.1D;
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState otherState = worldIn.getBlockState(pos.offset(state.get(FACING).getOpposite()));
        Block block = otherState.getBlock();
        return facingSameDirection(state, otherState) || block.isIn(NeapolitanTags.Blocks.VANILLA_PLANTABLE_ON);
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

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(AGE, FACING);
    }

    public BlockState func_235504_a_(IWorld world) {
        return this.getDefaultState().with(AGE, Integer.valueOf(world.getRandom().nextInt(5)));
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    public boolean ticksRandomly(BlockState state) {
        return state.get(AGE) < 5;
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (state.get(AGE) < 5 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.offset(state.get(FACING)), worldIn.getBlockState(pos.offset(state.get(FACING))), random.nextDouble() < this.growthChance)) {
            BlockPos blockpos = pos.offset(state.get(FACING));
            if (this.canGrowIn(worldIn.getBlockState(blockpos))) {
                worldIn.setBlockState(blockpos, state.func_235896_a_(AGE));
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, blockpos, worldIn.getBlockState(blockpos));
            }
        }

    }

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

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return this.canGrowIn(worldIn.getBlockState(pos.offset(state.get(FACING))));
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.offset(state.get(FACING));
        int i = Math.min(state.get(AGE) + 1, 5);
        int j = this.getGrowthAmount(rand);

        for (int k = 0; k < j && this.canGrowIn(worldIn.getBlockState(blockpos)); ++k) {
            worldIn.setBlockState(blockpos, state.with(AGE, Integer.valueOf(i)));
            blockpos = blockpos.offset(state.get(FACING));
            i = Math.min(i + 1, 5);
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