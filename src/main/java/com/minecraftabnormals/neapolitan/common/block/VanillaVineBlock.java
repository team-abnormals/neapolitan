package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.common.block.api.IPoisonCloud;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class VanillaVineBlock extends AbstractBodyPlantBlock implements IPoisonCloud {
    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public VanillaVineBlock(AbstractBlock.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }
    
    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(world, pos, state, player);
        this.createPoisonCloud(world, pos, state, player);
    }

    protected AbstractTopPlantBlock getTopPlantBlock() {
        return (AbstractTopPlantBlock) NeapolitanBlocks.VANILLA_VINE.get();
    }
}