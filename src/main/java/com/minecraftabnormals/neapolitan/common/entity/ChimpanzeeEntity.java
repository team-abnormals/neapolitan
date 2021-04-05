package com.minecraftabnormals.neapolitan.common.entity;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public class ChimpanzeeEntity extends AnimalEntity implements IAngerable {
	private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.createKey(ChimpanzeeEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CHIMPANZEE_TYPE = EntityDataManager.createKey(ChimpanzeeEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(ChimpanzeeEntity.class, DataSerializers.BYTE);

	private static final RangedInteger ANGER_RANGE = TickRangeConverter.convertRange(20, 39);
	private UUID lastHurtBy;
	private int attackTimer;

	private float climbAnim;
	private float prevClimbAnim;

	public boolean isPartying = false;
	BlockPos jukeboxPosition;

	public ChimpanzeeEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(ANGER_TIME, 0);
		this.dataManager.register(CHIMPANZEE_TYPE, 0);
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
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromTag(NeapolitanTags.Items.CHIMPANZEE_TEMPTATION_ITEMS), false));
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

		if (this.jukeboxPosition == null || !this.jukeboxPosition.withinDistance(this.getPositionVec(), 3.46D) || this.world.getBlockState(jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
			this.isPartying = false;
			this.jukeboxPosition = null;
		}

		if (this.world.isRemote) {
			this.prevClimbAnim = this.climbAnim;
			if (this.getAnimation() == Animation.CLIMBING) {
				this.climbAnim = Math.min(this.climbAnim + 1, 4.0F);
			} else {
				this.climbAnim = Math.max(this.climbAnim - 1, 0.0F);
			}
		}
	}

	@Override
	public void func_233629_a_(LivingEntity entity, boolean isFlying) {
		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d0 = this.getPosX() - this.prevPosX;
		double d1 = this.isClimbing() ? this.getPosY() - this.prevPosY : 0.0D;
		double d2 = this.getPosZ() - this.prevPosZ;
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 4.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		this.limbSwingAmount += (f - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

	@Override
	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	@Override
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

	public static boolean canChimpanzeeSpawn(EntityType<ChimpanzeeEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return random.nextInt(3) != 0;
	}

	@OnlyIn(Dist.CLIENT)
	public float getClimbingAnimationScale(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.prevClimbAnim, this.climbAnim) / 4.0F;
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		setTypeForPosition(this, worldIn);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public void setTypeForPosition(ChimpanzeeEntity entity, IWorld worldIn) {
		if (worldIn.getBiome(this.getPosition()).getRegistryName().getPath().contains("rainforest")) {
			entity.setChimpanzeeType(1);
		} else if (worldIn.getBiome(this.getPosition()).getRegistryName().getPath().contains("bamboo")) {
			entity.setChimpanzeeType(2);
		} else {
			entity.setChimpanzeeType(0);
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
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.func_233678_J__() ? NeapolitanSounds.ENTITY_CHIMPANZEE_ANGRY.get() : NeapolitanSounds.ENTITY_CHIMPANZEE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return NeapolitanSounds.ENTITY_CHIMPANZEE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return NeapolitanSounds.ENTITY_CHIMPANZEE_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(NeapolitanSounds.ENTITY_CHIMPANZEE_STEP.get(), 0.3F, 1.0F);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	public int getAttackTimer() {
		return this.attackTimer;
	}

	public boolean isClimbing() {
		return !this.onGround && this.isBesideClimbableBlock();
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem().isIn(NeapolitanTags.Items.CHIMPANZEE_BREEDING_ITEMS);
	}

	@Override
	public ChimpanzeeEntity func_241840_a(ServerWorld world, AgeableEntity ageableEntity) {
		ChimpanzeeEntity baby = NeapolitanEntities.CHIMPANZEE.get().create(world);
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

	public int getChimpanzeeType() {
		return this.dataManager.get(CHIMPANZEE_TYPE);
	}

	public void setChimpanzeeType(int type) {
		this.dataManager.set(CHIMPANZEE_TYPE, type);
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

	public boolean isPartying() {
		return this.isPartying;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void setPartying(BlockPos pos, boolean isPartying) {
		this.jukeboxPosition = pos;
		this.isPartying = isPartying;
	}

	@OnlyIn(Dist.CLIENT)
	public Animation getAnimation() {
		if (this.isClimbing()) {
			return Animation.CLIMBING;
		} else {
			return Animation.DEFAULT;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static enum Animation {
		CLIMBING,
		SHAKING,
		EATING,
		DRUMMING,
		DEFAULT;
	}
}
