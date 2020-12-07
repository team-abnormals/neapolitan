package com.minecraftabnormals.neapolitan.common.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanSoundEvents;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MonkeyEntity extends AnimalEntity implements IAngerable {
	private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.createKey(MonkeyEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> MONKEY_TYPE = EntityDataManager.createKey(MonkeyEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(MonkeyEntity.class, DataSerializers.BYTE);

	private static final RangedInteger ANGER_RANGE = TickRangeConverter.convertRange(20, 39);
	private UUID lastHurtBy;
	private int attackTimer;

	public MonkeyEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(ANGER_TIME, 0);
		this.dataManager.register(MONKEY_TYPE, 0);
		this.dataManager.register(CLIMBING, (byte) 0);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		this.writeAngerNBT(compound);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.readAngerNBT((ServerWorld) this.world, compound);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromTag(NeapolitanTags.Items.MONKEY_TEMPTATION_ITEMS), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp());
		this.targetSelector.addGoal(2, new ResetAngerGoal<>(this, true));
	}

	@Override
	protected void updateAITasks() {
		this.func_241359_a_((ServerWorld) this.world, true);

		if (this.func_233678_J__()) {
			this.recentlyHit = this.ticksExisted;
		}
		super.updateAITasks();
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte) 4);
		float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float f1 = (int) f > 0 ? f / 2.0F + (float) this.rand.nextInt((int) f) : f;
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
		if (flag) {
			this.applyEnchantments(this, entityIn);
		}

		return flag;
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new ClimberPathNavigator(this, worldIn);
	}

	public void tick() {
		super.tick();
		if (!this.world.isRemote) {
			this.setBesideClimbableBlock(this.collidedHorizontally);
		}
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	public void setMotionMultiplier(BlockState state, Vector3d motionMultiplierIn) {
		if (!state.isIn(Blocks.COBWEB)) {
			super.setMotionMultiplier(state, motionMultiplierIn);
		}
	}

	@Override
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		setTypeForPosition(this, worldIn);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public void setTypeForPosition(MonkeyEntity entity, IWorld worldIn) {
		if (worldIn.getBiome(this.getPosition()).getRegistryName().getPath().contains("rainforest")) {
			entity.setMonkeyType(1);
		} else if (worldIn.getBiome(this.getPosition()).getRegistryName().getPath().contains("bamboo")) {
			entity.setMonkeyType(2);
		} else {
			entity.setMonkeyType(0);
		}
	}

	public boolean isBesideClimbableBlock() {
		return (this.dataManager.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte b0 = this.dataManager.get(CLIMBING);
		if (climbing) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}

		this.dataManager.set(CLIMBING, b0);
	}

	@Override
	public double getYOffset() {
		return this.isChild() ? -0.05D : -0.3D;
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return AnimalEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.3F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.func_233678_J__() ? NeapolitanSoundEvents.ENTITY_MONKEY_ANGRY.get() : NeapolitanSoundEvents.ENTITY_MONKEY_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return NeapolitanSoundEvents.ENTITY_MONKEY_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return NeapolitanSoundEvents.ENTITY_MONKEY_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(NeapolitanSoundEvents.ENTITY_MONKEY_STEP.get(), 0.3F, 1.0F);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem().isIn(NeapolitanTags.Items.MONKEY_BREEDING_ITEMS);
	}

	@Override
	public MonkeyEntity func_241840_a(ServerWorld world, AgeableEntity ageableEntity) {
		MonkeyEntity baby = NeapolitanEntities.MONKEY.get().create(world);
		setTypeForPosition(baby, this.getEntityWorld());
		return baby;
	}

	@Override
	public int getAngerTime() {
		return this.dataManager.get(ANGER_TIME);
	}

	@Override
	public void setAngerTime(int time) {
		this.dataManager.set(ANGER_TIME, time);
	}

	public int getMonkeyType() {
		return this.dataManager.get(MONKEY_TYPE);
	}

	public void setMonkeyType(int type) {
		this.dataManager.set(MONKEY_TYPE, type);
	}

	@Override
	public UUID getAngerTarget() {
		return this.lastHurtBy;
	}

	@Override
	public void setAngerTarget(UUID target) {
		this.lastHurtBy = target;
	}

	@Override
	public void func_230258_H__() {
		this.setAngerTime(ANGER_RANGE.getRandomWithinRange(this.rand));
	}
}
