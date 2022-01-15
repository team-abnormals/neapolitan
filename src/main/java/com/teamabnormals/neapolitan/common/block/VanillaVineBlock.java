package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.other.NeapolitanCriteriaTriggers;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBlockTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

import java.util.Optional;
import java.util.Random;

public class VanillaVineBlock extends Block implements BonemealableBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D), // DOWN
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D), // UP
			Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D), // NORTH
			Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D), // SOUTH
			Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D), // WEST
			Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D), // EAST
	};

	public VanillaVineBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP));
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (!state.canSurvive(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get3DDataValue()];
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState otherState = worldIn.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
		return VanillaVineTopBlock.facingSameDirection(state, otherState) || otherState.is(NeapolitanBlockTags.VANILLA_PLANTABLE_ON);
	}


	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == stateIn.getValue(FACING).getOpposite() && !stateIn.canSurvive(worldIn, currentPos)) {
			worldIn.scheduleTick(currentPos, this, 1);
		}

		VanillaVineTopBlock topVine = (VanillaVineTopBlock) NeapolitanBlocks.VANILLA_VINE.get();
		if (facing == stateIn.getValue(FACING)) {
			Block block = facingState.getBlock();
			if (block != this && block != topVine) {
				return topVine.defaultBlockState().setValue(FACING, stateIn.getValue(FACING));
			}
		}

		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}


	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.VANILLA_PODS.get());
	}


	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		Optional<BlockPos> optional = this.nextGrowPosition(worldIn, pos, state);
		return optional.isPresent() && NetherVines.isValidGrowthState(worldIn.getBlockState(optional.get().relative(state.getValue(FACING))));
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
		Optional<BlockPos> optional = this.nextGrowPosition(worldIn, pos, state);
		if (optional.isPresent()) {
			BlockState blockstate = worldIn.getBlockState(optional.get());
			((VanillaVineTopBlock) blockstate.getBlock()).performBonemeal(worldIn, rand, optional.get(), blockstate);
		}
	}

	private Optional<BlockPos> nextGrowPosition(BlockGetter reader, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos;

		Block block;
		while (true) {
			blockpos = blockpos.relative(state.getValue(FACING));
			block = reader.getBlockState(blockpos).getBlock();
			if (block != state.getBlock()) {
				break;
			}
		}

		return block == NeapolitanBlocks.VANILLA_VINE.get() ? Optional.of(blockpos) : Optional.empty();
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		boolean flag = super.canBeReplaced(state, useContext);
		return (!flag || useContext.getItemInHand().getItem() != NeapolitanBlocks.VANILLA_VINE.get().asItem()) && flag;
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		super.playerWillDestroy(world, pos, state, player);
		createPoisonCloud(world, pos, state, player);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	public static void createPoisonCloud(Level world, BlockPos pos, BlockState state, Player player) {
		if (!player.getAbilities().instabuild) {
			if (!player.getMainHandItem().is(Tags.Items.SHEARS)) {
				AreaEffectCloud areaeffectcloudentity = new AreaEffectCloud(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
				areaeffectcloudentity.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.POISON, 300)));
				areaeffectcloudentity.setRadius(1.0F);
				areaeffectcloudentity.setRadiusOnUse(-0.5F);
				areaeffectcloudentity.setWaitTime(10);
				areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
				areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());

				world.addFreshEntity(areaeffectcloudentity);
			} else if (player instanceof ServerPlayer)
				NeapolitanCriteriaTriggers.VANILLA_POISON.trigger((ServerPlayer) player);
		}
	}
}