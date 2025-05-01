package com.teamabnormals.neapolitan.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class AdzukiSproutsBlock extends BushBlock implements BonemealableBlock {
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

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return null;
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		super.entityInside(state, level, pos, entityIn);
		if (entityIn instanceof Animal && !state.getValue(FLOWERING)) {
			level.setBlockAndUpdate(pos, state.setValue(FLOWERING, true));
			if (level.isClientSide())
				BoneMealItem.addGrowthParticles(level, pos, 0);
		}
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.ADZUKI_BEANS.get());
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 1)) return;
		int i = state.getValue(AGE);
		int speed = state.getValue(FLOWERING) ? 3 : 6;
		if (level.getRawBrightness(pos, 0) >= 9 && !this.isMaxAge(state) && CommonHooks.canCropGrow(level, pos, state, random.nextInt(speed) == 0)) {
			level.setBlock(pos, state.setValue(AGE, i + 1), 2);
			CommonHooks.fireCropGrowPost(level, pos, state);
		}
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean no) {
		super.spawnAfterBreak(state, level, pos, stack, no);
		this.replant(state, level, pos);
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		this.replant(state, level, pos);
		return super.playerWillDestroy(level, pos, state, player);
	}

	private void replant(BlockState state, Level level, BlockPos pos) {
		BlockState downState = level.getBlockState(pos.below());
		if (this.isMaxAge(state) && (downState.is(Blocks.DIRT) || downState.is(Blocks.COARSE_DIRT) || downState.is(Blocks.GRASS_BLOCK)))
			level.setBlockAndUpdate(pos.below(), NeapolitanBlocks.ADZUKI_SOIL.get().defaultBlockState());
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(AGE) >= 6;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, FLOWERING);
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
		int i = Math.min(6, state.getValue(AGE) + 1);
		level.setBlock(pos, state.setValue(AGE, i), 2);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(NeapolitanBlocks.ADZUKI_SOIL.get()) || super.mayPlaceOn(state, level, pos);
	}
}
