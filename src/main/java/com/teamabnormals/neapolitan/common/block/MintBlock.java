package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBlockTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class MintBlock extends BushBlock implements IPlantable, BonemealableBlock {
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
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!this.isMaxAge(state) && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (this.isMaxAge(state)) {
			popResource(worldIn, pos, new ItemStack(NeapolitanItems.MINT_LEAVES.get(), state.getValue(SPROUTS)));
			worldIn.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, 1), 2);
			return InteractionResult.sidedSuccess(worldIn.isClientSide);
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && state.getValue(SPROUTS) < 4 || super.canBeReplaced(state, useContext);
	}

	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		super.entityInside(state, worldIn, pos, entityIn);
	}

	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(NeapolitanItems.MINT_SPROUT.get());
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1)) return;
		int i = state.getValue(AGE);
		if (worldIn.getRawBrightness(pos, 0) >= 9 && !this.isMaxAge(state) && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(9) == 0)) {
			worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		} else {
			if (this.isMaxAge(state) && random.nextInt(3) != 0) {
				spawnGrowthParticles(worldIn, pos, random);
				Plane.HORIZONTAL.stream().forEach(direction -> {
					BlockPos offsetPos = pos.relative(direction);
					BlockState offsetState = worldIn.getBlockState(offsetPos);
					if (!offsetState.is(NeapolitanBlockTags.UNAFFECTED_BY_MINT))
						offsetState.randomTick(worldIn, offsetPos, random);
				});
			}
		}
	}

	private static void spawnGrowthParticles(ServerLevel worldIn, BlockPos posIn, Random random) {
		BlockState blockstate = worldIn.getBlockState(posIn);
		if (!blockstate.isAir()) {
			double d1 = blockstate.getShape(worldIn, posIn).max(Direction.Axis.Y);
			for (int i = 0; i < 8; ++i) {
				double d2 = random.nextGaussian() * 0.02D;
				double d3 = random.nextGaussian() * 0.02D;
				double d4 = random.nextGaussian() * 0.02D;
				double d6 = (double) posIn.getX() + random.nextDouble();
				double d7 = (double) posIn.getY() + random.nextDouble() * d1;
				double d8 = (double) posIn.getZ() + random.nextDouble();
				if (!worldIn.getBlockState((new BlockPos(d6, d7, d8)).below()).isAir()) {
					NetworkUtil.spawnParticle("neapolitan:mint_boost", d6, d7, d8, d2, d3, d4);
				}
			}
		}
	}

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

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, SPROUTS);
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(AGE)];
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter block, BlockPos pos, BlockState state, boolean isClient) {
		return !this.isMaxAge(state);
	}

	@Override
	public boolean isBonemealSuccess(Level world, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = Math.min(4, state.getValue(AGE) + 1);
		worldIn.setBlock(pos, state.setValue(AGE, i), 2);
	}
}
