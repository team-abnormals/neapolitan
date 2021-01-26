package com.minecraftabnormals.neapolitan.common.block;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanCriteriaTriggers;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.block.*;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import java.util.Optional;
import java.util.Random;

public class VanillaVineBlock extends Block implements IGrowable {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D), // DOWN
			Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D), // UP
			Block.makeCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D), // NORTH
			Block.makeCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D), // SOUTH
			Block.makeCuboidShape(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D), // WEST
			Block.makeCuboidShape(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D), // EAST
	};

	public VanillaVineBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.UP));
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!state.isValidPosition(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(FACING).getIndex()];
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState otherState = worldIn.getBlockState(pos.offset(state.get(FACING).getOpposite()));
		Block block = otherState.getBlock();
		return VanillaVineTopBlock.facingSameDirection(state, otherState) || block.isIn(NeapolitanTags.Blocks.VANILLA_PLANTABLE_ON);
	}


	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == stateIn.get(FACING).getOpposite() && !stateIn.isValidPosition(worldIn, currentPos)) {
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}

		VanillaVineTopBlock topVine = (VanillaVineTopBlock) NeapolitanBlocks.VANILLA_VINE.get();
		if (facing == stateIn.get(FACING)) {
			Block block = facingState.getBlock();
			if (block != this && block != topVine) {
				return topVine.getDefaultState().with(FACING, stateIn.get(FACING));
			}
		}

		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}


	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.VANILLA_PODS.get());
	}


	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		Optional<BlockPos> optional = this.nextGrowPosition(worldIn, pos, state);
		return optional.isPresent() && PlantBlockHelper.isAir(worldIn.getBlockState(optional.get().offset(state.get(FACING))));
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		Optional<BlockPos> optional = this.nextGrowPosition(worldIn, pos, state);
		if (optional.isPresent()) {
			BlockState blockstate = worldIn.getBlockState(optional.get());
			((VanillaVineTopBlock) blockstate.getBlock()).grow(worldIn, rand, optional.get(), blockstate);
		}

	}

	private Optional<BlockPos> nextGrowPosition(IBlockReader reader, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos;

		Block block;
		while (true) {
			blockpos = blockpos.offset(state.get(FACING));
			block = reader.getBlockState(blockpos).getBlock();
			if (block != state.getBlock()) {
				break;
			}
		}

		return block == NeapolitanBlocks.VANILLA_VINE.get() ? Optional.of(blockpos) : Optional.empty();
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		boolean flag = super.isReplaceable(state, useContext);
		return flag && useContext.getItem().getItem() == NeapolitanBlocks.VANILLA_VINE.get().asItem() ? false : flag;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBlockHarvested(world, pos, state, player);
		createPoisonCloud(world, pos, state, player);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	public static void createPoisonCloud(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		//FROM: @link CreeperEntity
		if (!player.getHeldItemMainhand().getItem().isIn(Tags.Items.SHEARS) && !player.abilities.isCreativeMode) {
			if (player instanceof ServerPlayerEntity)
				NeapolitanCriteriaTriggers.VANILLA_POISON.trigger((ServerPlayerEntity) player);
			AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
			areaeffectcloudentity.addEffect(new EffectInstance(new EffectInstance(Effects.POISON, 300)));
			areaeffectcloudentity.setRadius(1.0F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());

			world.addEntity(areaeffectcloudentity);
		}
	}
}