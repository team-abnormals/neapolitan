package com.teamabnormals.neapolitan.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;

public class StrawberryBushBlock extends BushBlock implements BonemealableBlock {
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
	public static final BooleanProperty WHITE = BooleanProperty.create("white");
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D)
	};

	public StrawberryBushBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(WHITE, false));
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return null;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		return state.getValue(AGE) != this.getMaxAge() && stack.is(Items.BONE_MEAL) ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (state.getValue(AGE) == this.getMaxAge()) {
			int strawberryCount = 1 + level.random.nextInt(2);
			Item strawberry = state.getValue(WHITE) ? NeapolitanItems.WHITE_STRAWBERRIES.get() : NeapolitanItems.STRAWBERRIES.get();
			popResource(level, pos, new ItemStack(strawberry, strawberryCount));
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			BlockState newState = state.setValue(AGE, 1);
			level.setBlock(pos, newState, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.useWithoutItem(state, level, pos, player, hitResult);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(WHITE, this.isWhite(context.getLevel(), context.getClickedPos()));
	}

	protected int getBonemealAgeIncrease(Level level) {
		return Mth.nextInt(level.random, 2, 5);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		super.randomTick(state, level, pos, rand);
		if (!level.isAreaLoaded(pos, 1))
			return;
		if (level.getRawBrightness(pos.above(), 0) >= 13) {
			int age = this.getAge(state);
			int maxAgeForPos = level.getBlockState(pos.below()).is(Blocks.COARSE_DIRT) ? 2 : this.getMaxAge();
			int growthChance = !level.isRaining() ? 7 : 5;
			if (age < maxAgeForPos) {
				if (CommonHooks.canCropGrow(level, pos, state, rand.nextInt(growthChance) == 0)) {
					level.setBlock(pos, state.setValue(AGE, age + 1), 2);
					CommonHooks.fireCropGrowPost(level, pos, state);
				}
			}
		}
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		if (level.random.nextInt(15) == 0) {
			if (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ()) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
				double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					level.playSound(null, pos, SoundEvents.GRASS_STEP, SoundSource.BLOCKS, 1.5F, 0.8F + level.random.nextFloat() * 0.4F);
				}
			}
		}
		if (entityIn instanceof Ravager && EventHooks.canEntityGrief(level, entityIn)) {
			level.destroyBlock(pos, true, entityIn);
		}
		if (entityIn instanceof LivingEntity entity && entity.getType().is(EntityTypeTags.ARTHROPOD) && state.getValue(AGE) > 0 && NeapolitanConfig.COMMON.strawberryBushArthropodInvisibility.get()) {
			entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 3, 0, false, false, false));

		}
		super.entityInside(state, level, pos, entityIn);
	}

	@Nullable
	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		if (entity instanceof Creeper)
			return PathType.DANGER_OTHER;
		return super.getBlockPathType(state, world, pos, entity);
	}

	protected ItemLike getSeedsItem() {
		return NeapolitanItems.STRAWBERRY_PIPS.get();
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(this.getSeedsItem());
	}

	protected int getAge(BlockState state) {
		return state.getValue(this.getAgeProperty());
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(this.getAgeProperty()) >= this.getMaxAge();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, WHITE);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	public int getMaxAge() {
		return 6;
	}

	private boolean isWhite(Level level, BlockPos pos) {
		return (pos.getY() >= NeapolitanConfig.COMMON.whiteStrawberryMinHeight.get() && level.dimension() == Level.OVERWORLD) || level.dimension() == Level.END;
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
		int age = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(level), this.getMaxAge());
		level.setBlock(pos, state.setValue(AGE, age), 2);
	}
}
