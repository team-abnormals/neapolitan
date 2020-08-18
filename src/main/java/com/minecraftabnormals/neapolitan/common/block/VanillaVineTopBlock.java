package com.minecraftabnormals.neapolitan.common.block;

import java.util.Random;

import com.minecraftabnormals.neapolitan.common.block.api.IPoisonCloud;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class VanillaVineTopBlock extends AbstractTopPlantBlock implements IPoisonCloud {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public VanillaVineTopBlock(AbstractBlock.Properties properties) {
       super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }
    
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.offset(this.growthDirection.getOpposite())).getBlock();
        return block == this.getTopPlantBlock() || block == this.func_230330_d_() || block.isIn(BlockTags.LEAVES);
    }

    @Override
    protected int getGrowthAmount(Random rand) {
       return rand.nextInt(2) + 1;
    }

    @Override
    protected Block func_230330_d_() {
       return NeapolitanBlocks.VANILLA_VINE_PLANT.get();
    }
    
    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(world, pos, state, player);
        this.createPoisonCloud(world, pos, state, player);
    }

    @Override
    protected boolean canGrowIn(BlockState state) {
       return PlantBlockHelper.isAir(state);
    }
 }