package com.minecraftabnormals.neapolitan.common.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.minecraftabnormals.neapolitan.common.entity.goals.*;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanParticles;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanSounds;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.LookController;
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
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChimpanzeeEntity extends AnimalEntity implements IAngerable {
	private static final DataParameter<Integer> CHIMPANZEE_TYPE = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> HUNGER = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> DIRTINESS = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> PALENESS = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.INT);
	private static final DataParameter<Byte> ACTION = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Direction> FACING = EntityDataManager.defineId(ChimpanzeeEntity.class, DataSerializers.DIRECTION);

	public static final EntitySize SITTING_DIMENSIONS = EntitySize.fixed(0.6F, 1.0F);

	private static final RangedInteger ANGER_RANGE = TickRangeConverter.rangeOfSeconds(20, 39);
	private UUID lastHurtBy;
	private int attackTimer;
	private int pickUpTimer;
	
	private boolean isLeader;

	@Nullable
	private ChimpanzeeEntity groomingTarget;
	@Nullable
	private ChimpanzeeEntity groomer;

	private int climbAnim;
	private int prevClimbAnim;

	private int headShakeAnim;
	private int prevHeadShakeAnim;

	public boolean isPartying = false;
	BlockPos jukeboxPosition;

	public ChimpanzeeEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.lookControl = new ChimpanzeeEntity.LookHelperController();
		this.setCanPickUpLoot(true);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new GrabBananaGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new OpenBunchGoal(this));
		this.goalSelector.addGoal(5, new EatBananaGoal(this));
		this.goalSelector.addGoal(6, new TemptBananaGoal(this, 1.25D));
		this.goalSelector.addGoal(7, new TemptGoal(this, 1.25D, Ingredient.of(NeapolitanTags.Items.CHIMPANZEE_FOOD), false));
		this.goalSelector.addGoal(8, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(9, new ShakeBundleGoal(this, 1.0D, 32, 12));
		this.goalSelector.addGoal(10, new ShareBananaGoal(this, 1.0D));
		this.goalSelector.addGoal(11, new BeGroomedGoal(this));
		this.goalSelector.addGoal(12, new GroomGoal(this, 1.0D));
		this.goalSelector.addGoal(13, new FollowOthersGoal(this, 1.0D));
		this.goalSelector.addGoal(14, new CryGoal(this));
		this.goalSelector.addGoal(15, new ShakeHeadGoal(this));
		this.goalSelector.addGoal(16, new PlayNoteBlockGoal(this, 1.0D, 16));
		this.goalSelector.addGoal(17, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(18, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(19, new LookAtGoal(this, ChimpanzeeEntity.class, 6.0F));
		this.goalSelector.addGoal(20, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new ResetAngerGoal<>(this, true));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return AnimalEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CHIMPANZEE_TYPE, 0);
		this.entityData.define(ANGER_TIME, 0);
		this.entityData.define(HUNGER, 0);
		this.entityData.define(DIRTINESS, 0);
		this.entityData.define(PALENESS, 0);
		this.entityData.define(ACTION, (byte) 0);
		this.entityData.define(CLIMBING, (byte) 0);
		this.entityData.define(FACING, Direction.DOWN);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
		compound.putInt("ChimpanzeeType", this.getChimpanzeeType());
		compound.putInt("Hunger", this.getHunger());
		compound.putInt("Dirtiness", this.getDirtiness());
		compound.putInt("Paleness", this.getPaleness());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
		this.setChimpanzeeType(compound.getInt("ChimpanzeeType"));
		this.setHunger(compound.getInt("Hunger"));
		this.setDirtiness(compound.getInt("Dirtiness"));
		this.setPaleness(compound.getInt("Paleness"));
	}

	@Override
	protected BodyController createBodyControl() {
		return new ChimpanzeeEntity.BodyHelperController();
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new ClimberPathNavigator(this, worldIn);
	}

	// SOUND //

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isAngry() ? NeapolitanSounds.ENTITY_CHIMPANZEE_ANGRY.get() : NeapolitanSounds.ENTITY_CHIMPANZEE_AMBIENT.get();
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

	@Nullable
	@Override
	public SoundEvent getEatingSound(ItemStack itemStackIn) {
		return null;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	// AI //

	@Override
	protected void customServerAiStep() {
		this.updatePersistentAnger((ServerWorld) this.level, true);

		if (this.isAngry()) {
			this.lastHurtByPlayerTime = this.tickCount;
		}
		
		super.customServerAiStep();
	}

	@Override
	public boolean doHurtTarget(Entity entityIn) {
		this.swingArms();
		this.level.broadcastEntityEvent(this, (byte) 4);
		float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
		boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
		if (flag) {
			this.doEnchantDamageEffects(this, entityIn);
		}

		return flag;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			this.setAction(ChimpanzeeAction.DEFAULT);
			return super.hurt(source, amount);
		}
	}

	public void tick() {
		if (!this.level.isClientSide) {
			if (this.getAction() == ChimpanzeeAction.DEFAULT || this.getAction() == ChimpanzeeAction.CLIMBING) {
				this.setDefaultAction();
			}
		}

		super.tick();

		if (!this.level.isClientSide) {
			this.handleClimbing();
			
			if (this.random.nextInt(60) == 0) {
				this.setLeader(this.shouldBeLeader());
			}
		} else {
			if (this.isDirty()) {
				if (this.tickCount % 6 == 0) {
					double d0 = ((double) this.random.nextFloat() + 1.0D) * 0.06D;
					double d1 = this.random.nextInt(360) - 360.0D;
					double d2 = ((double) this.random.nextFloat() + 1.0D) * 14.0D;
					d2 *= this.random.nextBoolean() ? 1.0D : -1.0D;

					level.addParticle(NeapolitanParticles.FLY.get(), this.getRandomX(0.5D), this.getEyeY() + this.random.nextDouble() * 0.2D + 0.3D, this.getRandomZ(0.5D), d0, d1, d2);
				}
			}
		}

		if (this.getAction() == ChimpanzeeAction.EATING) {
			ItemStack food = this.getSnack();
			if (this.tickCount % 10 == 0 && !food.isEmpty()) {
				if (this.level.isClientSide) {
					for (int i = 0; i < 6; ++i) {
						Vector3d vector3d = new Vector3d(((double) this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double) this.random.nextFloat() - 0.5D) * 0.1D);
						vector3d = vector3d.xRot(-this.xRot * ((float) Math.PI / 180F));
						vector3d = vector3d.yRot(-this.yRot * ((float) Math.PI / 180F));
						double d0 = (double) (-this.random.nextFloat()) * 0.2D;
						Vector3d vector3d1 = new Vector3d(((double) this.random.nextFloat() - 0.5D) * 0.2D, d0, 0.8D + ((double) this.random.nextFloat() - 0.5D) * 0.2D);
						vector3d1 = vector3d1.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
						vector3d1 = vector3d1.add(this.getX(), this.getEyeY(), this.getZ());
						this.level.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItemInHand(this.getSnackHand())), vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y + 0.05D, vector3d.z);
					}
				}

				this.playSound(NeapolitanSounds.ENTITY_CHIMPANZEE_EAT.get(), 0.25F + 0.5F * (float) this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			}
		} else if (this.getAction() == ChimpanzeeAction.CRYING) {
			if (this.level.isClientSide) {
				if (this.tickCount % 2 == 0 && this.random.nextInt(4) > 0) {
					for (int i = 0; i < 2; ++i) {
						double d0 = i == 0 ? (double) (this.random.nextFloat()) * 0.15D + 0.1D : (double) (-this.random.nextFloat()) * 0.15D - 0.1D;
						double d1 = ((double) this.random.nextFloat()) * 0.1D + 0.15D;
						double d2 = i == 0 ? 0.15D : -0.15D;

						Vector3d vector3d = new Vector3d(d0, Math.random() * 0.2D + 0.1D, (double) (this.random.nextFloat()) * 0.2D + 0.1D);
						vector3d = vector3d.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
						Vector3d vector3d1 = new Vector3d(d2, d1, 0.35D);
						vector3d1 = vector3d1.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
						vector3d1 = vector3d1.add(this.getX(), this.getEyeY(), this.getZ());

						this.level.addParticle(NeapolitanParticles.TEAR.get(), vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y + 0.05D, vector3d.z);
					}
				}
			}
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}

		if (this.pickUpTimer > 0) {
			--this.pickUpTimer;
		}

		if (this.jukeboxPosition == null || !this.jukeboxPosition.closerThan(this.position(), 3.46D) || this.level.getBlockState(jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
			this.isPartying = false;
			this.jukeboxPosition = null;
		}

		this.refreshDimensions();

		if (!this.level.isClientSide) {
			if (!this.isHungry() && this.getHunger() >= 0) {
				this.setHunger(this.getHunger() + 1);
			}

			if (!this.isDirty() && this.getDirtiness() >= 0) {
				this.setDirtiness(this.getDirtiness() + 1);
			}

			if (this.getPaleness() >= 0) {
				if (this.isInSunlight()) {
					this.setPaleness(this.getPaleness() - 1);
				} else if (!this.needsSunlight()) {
					this.setPaleness(this.getPaleness() + 1);
				}
			}
		} else {
			this.prevClimbAnim = this.climbAnim;
			if (this.getAction() == ChimpanzeeAction.CLIMBING) {
				this.climbAnim = Math.min(this.climbAnim + 1, 6);
			} else if (this.getAction() == ChimpanzeeAction.HANGING || this.getAction() == ChimpanzeeAction.SHAKING) {
				this.climbAnim = Math.min(this.climbAnim + 1, 8);
			} else {
				this.climbAnim = Math.max(this.climbAnim - 1, 0);
			}

			this.prevHeadShakeAnim = this.headShakeAnim;
			if (this.headShakeAnim > 0) {
				--this.headShakeAnim;
			}
		}
	}

	private void handleClimbing() {
		this.setBesideClimbableBlock(this.horizontalCollision);

		if (this.getAction() == ChimpanzeeAction.CLIMBING) {
			Direction newfacing = Direction.DOWN;

			for(Direction direction : Direction.Plane.HORIZONTAL) {
				Vector3d vector3d = this.collide(Vector3d.atLowerCornerOf(direction.getNormal()));

				if (Math.abs(vector3d.get(direction.getAxis())) <= 0.2D ) {
					newfacing = direction;
					if (direction == this.entityData.get(FACING)) {
						break;
					}
				}
			}

			this.setFacing(newfacing);
		}
	}
	
	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (this.isSnack(itemstack) && !(this.isFood(itemstack) && !this.isHungry())) {
			if (this.getMainHandItem().isEmpty()) {
				ItemStack itemstack1 = itemstack.copy();
				itemstack1.setCount(1);
				this.setItemInHand(Hand.MAIN_HAND, itemstack1);
				this.usePlayerItem(player, itemstack);
				this.stopBeingAngry();

				return ActionResultType.sidedSuccess(this.level.isClientSide);
			}
			return ActionResultType.PASS;
		}

		return super.mobInteract(player, hand);
	}

	public void openBunch(Hand hand) {
		if (!this.level.isClientSide) {
			BananaPeelEntity bananapeel = NeapolitanEntities.BANANA_PEEL.get().create(this.level);
			bananapeel.moveTo(this.getX(), this.getEyeY(), this.getZ(), this.yRot, 0.0F);
			bananapeel.setDeltaMovement(this.random.nextDouble() * 0.4D - 0.2D, 0.4D, this.random.nextDouble() * 0.4D - 0.2D);
			this.level.addFreshEntity(bananapeel);

			this.setItemInHand(hand, new ItemStack(NeapolitanItems.BANANA.get()));
		}
	}

	public void eatSnack() {
		if (!this.getSnack().isEmpty()) {
			this.heal((float)this.getSnack().getItem().getFoodProperties().getNutrition());
			this.setItemInHand(this.getSnackHand(), this.getSnack().finishUsingItem(this.level, this));
		}
		this.setHunger(0);
	}

	@Override
	public void calculateEntityAnimation(LivingEntity entity, boolean isFlying) {
		this.animationSpeedOld = this.animationSpeed;
		double d0 = this.getX() - this.xo;
		double d1 = this.getAction() == ChimpanzeeAction.CLIMBING ? this.getY() - this.yo : 0.0D;
		double d2 = this.getZ() - this.zo;
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 4.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		this.animationSpeed += (f - this.animationSpeed) * 0.4F;
		this.animationPosition += this.animationSpeed;
	}

	@Override
	public boolean onClimbable() {
		return (this.getAction() == ChimpanzeeAction.DEFAULT || this.getAction() == ChimpanzeeAction.CLIMBING) && this.isBesideClimbableBlock();
	}

	public boolean shouldClimb() {
		return !this.onGround && this.isBesideClimbableBlock();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		return false;
	}

	public boolean isBesideClimbableBlock() {
		return (this.entityData.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte b0 = this.entityData.get(CLIMBING);
		if (climbing) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}

		this.entityData.set(CLIMBING, b0);
	}

	public boolean isSitting() {
		return this.getAction().shouldSit();
	}

	@Override
	public EntitySize getDimensions(Pose pose) {
		if (this.isSitting()) {
			return SITTING_DIMENSIONS.scale(this.getScale());
		} else {
			return super.getDimensions(pose);
		}
	}

	@Override
	public double getMyRidingOffset() {
		return this.isBaby() ? -0.05D : -0.3D;
	}

	@Override
	public boolean wantsToPickUp(ItemStack itemStack) {
		return this.isSnack(itemStack);
	}

	@Override
	public boolean canTakeItem(ItemStack itemstackIn) {
		if (this.pickUpTimer > 0) {
			return false;
		} else {
			EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(itemstackIn);
			if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
				return false;
			} else {
				return equipmentslottype == EquipmentSlotType.MAINHAND && super.canTakeItem(itemstackIn);
			}
		}
	}

	@Override
	public boolean canHoldItem(ItemStack stack) {
		ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.MAINHAND);
		return !this.isSnack(itemstack) && this.isSnack(stack);
	}

	@Override
	protected void pickUpItem(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (this.canHoldItem(itemstack)) {
			int i = itemstack.getCount();
			if (i > 1) {
				ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), itemstack.split(i - 1));
				this.level.addFreshEntity(itementity);
			}

			ItemEntity itementity1 = new ItemEntity(this.level, this.getX(), this.getEyeY() - (double)0.3F, this.getZ(), this.getItemBySlot(EquipmentSlotType.MAINHAND));
			this.level.addFreshEntity(itementity1);

			this.onItemPickup(itemEntity);
			this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack.split(1));
			this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
			this.take(itemEntity, itemstack.getCount());
			itemEntity.remove();

			this.stopBeingAngry();
		}
	}

	// GROUP BEHAVIOR //

	public boolean isLeader() {
		return this.isLeader;
	}
	
	public void setLeader(boolean isLeaderIn) {
		this.isLeader = isLeaderIn;
	}

	private boolean shouldBeLeader() {
		Predicate<ChimpanzeeEntity> predicate = (chimpanzeeentity) -> {
			return chimpanzeeentity != this && chimpanzeeentity.getAge() >= 0;
		};
		List<ChimpanzeeEntity> list = this.level.getEntitiesOfClass(ChimpanzeeEntity.class, this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
		
		if (list.isEmpty()) {
			return false;
		}
		
		int chimpamount = list.size() + 1;
		int leaderamount = 0;
		
		for(ChimpanzeeEntity chimpanzeeentity : list) {
			if (chimpanzeeentity.isLeader()) {
				++leaderamount;
			}
			
			if (leaderamount * 4 >= chimpamount) {
				if (!(this.needsSnack() && !chimpanzeeentity.needsSnack())) {
					return false;
				}
			}
		}
		
		return true;
	}

	// SPAWNING //

	public static boolean canChimpanzeeSpawn(EntityType<ChimpanzeeEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return world.getRawBrightness(pos, 0) > 8 && random.nextInt(3) != 0;
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setTypeForPosition(this, worldIn);
		this.setHunger(this.random.nextInt(4800));
		this.setDirtiness(this.random.nextInt(4800));
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public void setTypeForPosition(ChimpanzeeEntity entity, IWorld worldIn) {
		if (worldIn.getBiome(this.blockPosition()).getRegistryName().getPath().contains("rainforest")) {
			entity.setChimpanzeeType(1);
		} else if (worldIn.getBiome(this.blockPosition()).getRegistryName().getPath().contains("bamboo")) {
			entity.setChimpanzeeType(2);
		} else {
			entity.setChimpanzeeType(0);
		}
	}

	@Override
	public ChimpanzeeEntity getBreedOffspring(ServerWorld world, AgeableEntity ageableEntity) {
		ChimpanzeeEntity baby = NeapolitanEntities.CHIMPANZEE.get().create(world);
		baby.setChimpanzeeType(this.random.nextBoolean() ? this.getChimpanzeeType() : ((ChimpanzeeEntity)ageableEntity).getChimpanzeeType());
		return baby;
	}

	// DATA //

	public int getChimpanzeeType() {
		return MathHelper.clamp(this.entityData.get(CHIMPANZEE_TYPE), 0, 2);
	}

	public void setChimpanzeeType(int type) {
		this.entityData.set(CHIMPANZEE_TYPE, type);
	}

	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.entityData.get(ANGER_TIME);
	}

	@Override
	public void setRemainingPersistentAngerTime(int time) {
		this.entityData.set(ANGER_TIME, time);
	}

	public void setPickUpTimer(int time) {
		this.pickUpTimer = time;
	}
	
	public ChimpanzeeEntity getGroomingTarget() {
		return this.groomingTarget;
	}

	public void setGroomingTarget(ChimpanzeeEntity target) {
		this.groomingTarget = target;
	}

	public ChimpanzeeEntity getGroomer() {
		return this.groomer;
	}

	public void setGroomer(ChimpanzeeEntity groomerIn) {
		this.groomer = groomerIn;
	}

	public Direction getFacing() {
		return this.entityData.get(FACING);
	}

	public void setFacing(Direction direction) {
		this.entityData.set(FACING, direction);
	}

	@Override
	public UUID getPersistentAngerTarget() {
		return this.lastHurtBy;
	}

	@Override
	public void setPersistentAngerTarget(UUID target) {
		this.lastHurtBy = target;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(ANGER_RANGE.randomValue(this.random));
	}

	// NEED STUFF //

	public int getHunger() {
		return this.entityData.get(HUNGER);
	}

	public void setHunger(int amount) {
		this.entityData.set(HUNGER, amount);
	}

	public boolean isHungry() {
		return this.getHunger() >= 9600;
	}

	public boolean needsSnack() {
		return this.isHungry() && this.getSnack().isEmpty();
	}
	
	public Hand getSnackHand() {
		if (!this.getSnack(Hand.MAIN_HAND).isEmpty()) {
			return Hand.MAIN_HAND;
		} else {
			return Hand.OFF_HAND;
		}
	}

	public ItemStack getSnack() {
		if (!this.getSnack(Hand.MAIN_HAND).isEmpty()) {
			return this.getSnack(Hand.MAIN_HAND);
		} else if (!this.getSnack(Hand.OFF_HAND).isEmpty()) {
			return this.getSnack(Hand.OFF_HAND);
		}
		return ItemStack.EMPTY;
	}

	public ItemStack getSnack(Hand hand) {
		ItemStack food = this.getItemInHand(hand);
		if (this.isSnack(food)) {
			return food;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.getItem().is(NeapolitanTags.Items.CHIMPANZEE_FOOD);
	}

	public boolean isSnack(ItemStack stack) {
		return stack.getItem().is(NeapolitanTags.Items.CHIMPANZEE_SNACKS);
	}

	public int getDirtiness() {
		return this.entityData.get(DIRTINESS);
	}

	public void setDirtiness(int amount) {
		this.entityData.set(DIRTINESS, amount);
	}

	public boolean isDirty() {
		return this.getDirtiness() >= 12000;
	}

	public void getCleaned() {
		this.setDirtiness(0);
	}

	public int getPaleness() {
		return this.entityData.get(PALENESS);
	}

	public void setPaleness(int amount) {
		this.entityData.set(PALENESS, amount);
	}

	public boolean needsSunlight() {
		return this.getPaleness() >= 6000;
	}

	public float getVisiblePaleness() {
		return MathHelper.clamp((this.getPaleness() - 4800.0F) / 1200.0F, 0.0F, 1.0F);
	}

	public boolean isInSunlight() {
		BlockPos blockpos = this.getVehicle() instanceof BoatEntity ? (new BlockPos(this.getX(), (double)Math.round(this.getY()), this.getZ())).above() : new BlockPos(this.getX(), (double)Math.round(this.getY()), this.getZ());
		return this.level.getBrightness(LightType.SKY, blockpos) > 8;
	}

	// ACTION STUFF //

	public ChimpanzeeAction getAction() {
		return ChimpanzeeAction.byIndex(this.entityData.get(ACTION));
	}

	public void setAction(ChimpanzeeAction action) {
		this.entityData.set(ACTION, (byte)action.getIndex());
	}

	public void setDefaultAction() {
		boolean flag = !this.isPassenger() && this.shouldClimb();

		if (flag) {
			this.setAction(ChimpanzeeAction.CLIMBING);
		} else {
			this.setAction(ChimpanzeeAction.DEFAULT);
		}
	}

	// ANIMATION //

	public boolean isPartying() {
		return this.isPartying;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void setRecordPlayingNearby(BlockPos pos, boolean isPartying) {
		this.jukeboxPosition = pos;
		this.isPartying = isPartying;
	}

	public boolean isMouthOpen() {
		if (this.getAction() == ChimpanzeeAction.EATING) {
			return Math.sin(Math.PI * this.tickCount * 0.2D) > 0;
		} else if (this.getAction() == ChimpanzeeAction.CRYING || this.isAngry() || this.isHungry() || this.isPartying()) {
			return true;
		} else {
			return false;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getClimbingAnimationScale(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.prevClimbAnim, this.climbAnim) / 8.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadShakeProgress(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.prevHeadShakeAnim, this.headShakeAnim);
	}

	public void swingArms() {
		this.attackTimer = 10;
	}

	public void shakeHead(IParticleData particleData) {
		this.headShakeAnim = 40;
		this.prevHeadShakeAnim = 40;

		double d0 = this.random.nextGaussian() * 0.02D;
		double d1 = this.random.nextGaussian() * 0.02D;
		double d2 = this.random.nextGaussian() * 0.02D;

		this.level.addParticle(particleData, this.getX(), this.getY(1.0D), this.getZ(), d0, d1, d2);
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.swingArms();
		} else if (id == 6) {
			this.shakeHead(NeapolitanParticles.CHIMPANZEE_NEEDS_FRIEND.get());
		} else if (id == 7) {
			this.shakeHead(NeapolitanParticles.CHIMPANZEE_NEEDS_SUN.get());
		} else if (id == 8) {
			this.shakeHead(NeapolitanParticles.CHIMPANZEE_NEEDS_FOOD.get());
		} else {
			super.handleEntityEvent(id);
		}
	}

	// CONTROLLERS //

	class LookHelperController extends LookController {

		public LookHelperController() {
			super(ChimpanzeeEntity.this);
		}

		public void tick() {
			if (this.resetXRotOnTick()) {
				ChimpanzeeEntity.this.xRot = 0.0F;
			}

			if (this.hasWanted) {
				this.hasWanted = false;
				ChimpanzeeEntity.this.yHeadRot = this.rotateTowards(ChimpanzeeEntity.this.yHeadRot, this.getYRotD(), this.yMaxRotSpeed);
				ChimpanzeeEntity.this.xRot = this.rotateTowards(ChimpanzeeEntity.this.xRot, this.getXRotD(), this.xMaxRotAngle);
			} else {
				ChimpanzeeEntity.this.yHeadRot = this.rotateTowards(ChimpanzeeEntity.this.yHeadRot, ChimpanzeeEntity.this.yBodyRot, 10.0F);
			}

			Direction facing = ChimpanzeeEntity.this.getFacing();
			if (ChimpanzeeEntity.this.getAction() == ChimpanzeeAction.CLIMBING && facing != Direction.DOWN) {
				ChimpanzeeEntity.this.yHeadRot = MathHelper.rotateIfNecessary(ChimpanzeeEntity.this.yHeadRot, facing.toYRot(), (float)ChimpanzeeEntity.this.getMaxHeadYRot());
			} else if (!ChimpanzeeEntity.this.getNavigation().isDone()) {
				ChimpanzeeEntity.this.yHeadRot = MathHelper.rotateIfNecessary(ChimpanzeeEntity.this.yHeadRot, ChimpanzeeEntity.this.yBodyRot, (float)ChimpanzeeEntity.this.getMaxHeadYRot());
			}
		}
	}

	class BodyHelperController extends BodyController {
		private int bodyRotationTickCounter;
		private Direction prevFacing;

		public BodyHelperController() {
			super(ChimpanzeeEntity.this);
		}

		public void clientTick() {
			super.clientTick();

			Direction facing = ChimpanzeeEntity.this.getFacing();

			if (facing != this.prevFacing || ChimpanzeeEntity.this.getAction() != ChimpanzeeAction.CLIMBING) {
				this.bodyRotationTickCounter = 10;
			}

			this.prevFacing = facing;

			if (facing != Direction.DOWN && ChimpanzeeEntity.this.getAction() == ChimpanzeeAction.CLIMBING) {
				int i = this.bodyRotationTickCounter;
				float f = MathHelper.clamp((float)i / 10.0F, 0.0F, 1.0F);
				float f1 = 90.0F * f;
				ChimpanzeeEntity.this.yBodyRot = MathHelper.rotateIfNecessary(ChimpanzeeEntity.this.yBodyRot, facing.toYRot(), f1);

				if (this.bodyRotationTickCounter > 0) {
					--this.bodyRotationTickCounter;
				}
			}
		}
	}
}
