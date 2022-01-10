package com.teamabnormals.neapolitan.common.block;

import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.other.NeapolitanCriteriaTriggers;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.Random;

public class StrawberryBushBlock extends BushBlock implements IPlantable, BonemealableBlock {
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
	public static final EnumProperty<StrawberryType> TYPE = EnumProperty.create("type", StrawberryType.class);
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
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(TYPE, StrawberryType.NONE));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		int age = state.getValue(AGE);
		boolean fullyGrown = age == this.getMaxAge();
		if (!fullyGrown && player.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (fullyGrown) {
			int strawberryCount = 1 + level.random.nextInt(2);
			Item strawberry = state.getValue(TYPE) == StrawberryType.WHITE ? NeapolitanItems.WHITE_STRAWBERRIES.get() : NeapolitanItems.STRAWBERRIES.get();
			popResource(level, pos, new ItemStack(strawberry, strawberryCount));
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(AGE, 1).setValue(TYPE, StrawberryType.NONE), 2);
			if (player instanceof ServerPlayer)
				NeapolitanCriteriaTriggers.HARVEST_STRAWBERRIES.trigger((ServerPlayer) player, state);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.use(state, level, pos, player, hand, hit);
		}
	}

	protected int getBonemealAgeIncrease(Level worldIn) {
		return Mth.nextInt(worldIn.random, 2, 5);
	}

	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		if (!worldIn.isAreaLoaded(pos, 1))
			return;
		if (worldIn.getRawBrightness(pos, 0) >= 13) {
			int age = this.getAge(state);
			int maxAgeForPos = worldIn.getBlockState(pos.below()).is(Blocks.COARSE_DIRT) ? 2 : this.getMaxAge();
			int growthChance = !worldIn.isRaining() ? 7 : 5;
			if (age < maxAgeForPos) {
				if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(growthChance) == 0)) {
					if (age != 5) {
						worldIn.setBlock(pos, this.withAge(age + 1), 2);
					} else {
						worldIn.setBlock(pos, this.withAge(age + 1).setValue(TYPE, this.isWhite(worldIn, pos) ? StrawberryType.WHITE : StrawberryType.RED), 2);
					}
					ForgeHooks.onCropsGrowPost(worldIn, pos, state);
				}
			}
		}

	}

	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		if (worldIn.random.nextInt(15) == 0) {
			if (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ()) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
				double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					worldIn.playSound(null, pos, SoundEvents.GRASS_STEP, SoundSource.BLOCKS, 1.5F, 0.8F + worldIn.random.nextFloat() * 0.4F);
				}
			}
		}
		if (entityIn instanceof Ravager && ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
			worldIn.destroyBlock(pos, true, entityIn);
		}
		if (entityIn instanceof LivingEntity entity && entity.getMobType() == MobType.ARTHROPOD && state.getValue(AGE) > 0 && NeapolitanConfig.COMMON.strawberryBushArthropodInvisibility.get()) {
			entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 3, 0, false, false, false));

		}
		super.entityInside(state, worldIn, pos, entityIn);
	}

	@Nullable
	@Override
	public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		if (entity instanceof Creeper)
			return BlockPathTypes.DANGER_OTHER;
		return super.getAiPathNodeType(state, world, pos, entity);
	}

	protected ItemLike getSeedsItem() {
		return NeapolitanItems.STRAWBERRY_PIPS.get();
	}

	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.getSeedsItem());
	}

	protected int getAge(BlockState state) {
		return state.getValue(this.getAgeProperty());
	}

	public BlockState withAge(int age) {
		return this.defaultBlockState().setValue(this.getAgeProperty(), age);
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(this.getAgeProperty()) >= this.getMaxAge();
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, TYPE);
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	public int getMaxAge() {
		return 6;
	}

	private boolean isWhite(ServerLevel worldIn, BlockPos pos) {
		return (pos.getY() >= NeapolitanConfig.COMMON.whiteStrawberryMinHeight.get() && worldIn.dimension() == Level.OVERWORLD) || worldIn.dimension() == Level.END;
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
		int age = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(worldIn), this.getMaxAge());
		if (age != 6) {
			worldIn.setBlock(pos, this.withAge(age), 2);
		} else {
			worldIn.setBlock(pos, this.withAge(age).setValue(TYPE, this.isWhite(worldIn, pos) ? StrawberryType.WHITE : StrawberryType.RED), 2);
		}
	}

	public enum StrawberryType implements StringRepresentable {
		NONE("none"), RED("red"), WHITE("white");

		private final String name;

		StrawberryType(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
