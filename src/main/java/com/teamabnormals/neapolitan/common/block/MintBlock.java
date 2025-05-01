package com.teamabnormals.neapolitan.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.common.network.particle.SpawnParticlesPayload.ParticleInstance;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBlockTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nullable;
import java.util.List;

public class MintBlock extends BushBlock implements BonemealableBlock {
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	public static final IntegerProperty SPROUTS = IntegerProperty.create("sprouts", 1, 4);
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D)
	};

	public MintBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(SPROUTS, 1));
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return null;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		return !this.isMaxAge(state) && stack.is(Items.BONE_MEAL) ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (this.isMaxAge(state)) {
			popResource(level, pos, new ItemStack(NeapolitanItems.MINT_LEAVES.get(), state.getValue(SPROUTS)));
			level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(AGE, 1), 2);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.useWithoutItem(state, level, pos, player, hit);
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && state.getValue(SPROUTS) < 4 || super.canBeReplaced(state, useContext);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		super.entityInside(state, level, pos, entityIn);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.MINT_SPROUT.get());
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 1)) return;
		int i = state.getValue(AGE);
		if (level.getRawBrightness(pos.above(), 0) >= 9 && !this.isMaxAge(state) && CommonHooks.canCropGrow(level, pos, state, random.nextInt(9) == 0)) {
			level.setBlock(pos, state.setValue(AGE, i + 1), 2);
			CommonHooks.fireCropGrowPost(level, pos, state);
		} else {
			if (this.isMaxAge(state) && random.nextInt(3) != 0) {
				spawnGrowthParticles(level, pos, random);
				Plane.HORIZONTAL.stream().forEach(direction -> {
					BlockPos offsetPos = pos.relative(direction);
					BlockState offsetState = level.getBlockState(offsetPos);
					if (!offsetState.is(NeapolitanBlockTags.UNAFFECTED_BY_MINT))
						offsetState.randomTick(level, offsetPos, random);
				});
			}
		}
	}

	private static void spawnGrowthParticles(ServerLevel level, BlockPos posIn, RandomSource random) {
		BlockState blockstate = level.getBlockState(posIn);
		if (!blockstate.isAir()) {
			double d1 = blockstate.getShape(level, posIn).max(Direction.Axis.Y);
			for (int i = 0; i < 8; ++i) {
				double d2 = random.nextGaussian() * 0.02D;
				double d3 = random.nextGaussian() * 0.02D;
				double d4 = random.nextGaussian() * 0.02D;
				double d6 = (double) posIn.getX() + random.nextDouble();
				double d7 = (double) posIn.getY() + random.nextDouble() * d1;
				double d8 = (double) posIn.getZ() + random.nextDouble();
				if (!level.getBlockState((BlockPos.containing(d6, d7, d8)).below()).isAir()) {
					NetworkUtil.spawnParticle(level, NeapolitanParticleTypes.MINT_BOOST.get(), List.of(new ParticleInstance(d6, d7, d8, d2, d3, d4)));
				}
			}
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		if (blockstate.is(this)) {
			return blockstate.setValue(SPROUTS, Math.min(4, blockstate.getValue(SPROUTS) + 1));
		}
		return super.getStateForPlacement(context);
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(AGE) >= 4;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, SPROUTS);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(AGE)];
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader block, BlockPos pos, BlockState state) {
		return !this.isMaxAge(state);
	}

	@Override
	public boolean isBonemealSuccess(Level world, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
		int i = Math.min(4, state.getValue(AGE) + 1);
		level.setBlock(pos, state.setValue(AGE, i), 2);
	}
}
