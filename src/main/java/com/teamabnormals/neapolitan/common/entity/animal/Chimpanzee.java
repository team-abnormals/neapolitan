package com.teamabnormals.neapolitan.common.entity.animal;

import com.teamabnormals.neapolitan.common.entity.goal.*;
import com.teamabnormals.neapolitan.common.entity.monster.PlantainSpider;
import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeTypes;
import com.teamabnormals.neapolitan.common.item.MilkshakeItem;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import com.teamabnormals.neapolitan.core.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class Chimpanzee extends Animal implements NeutralMob {
	private static final EntityDataAccessor<Integer> CHIMPANZEE_TYPE = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ANGER_TIME = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> APE_MODE_TIME = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DIRTINESS = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> PALENESS = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_HAND_DYE_COLOR = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_HAND_DYE_COLOR = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> IS_LEFT_HAND_DYED = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> IS_RIGHT_HAND_DYED = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> ACTION = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Direction> FACING = SynchedEntityData.defineId(Chimpanzee.class, EntityDataSerializers.DIRECTION);

	private static final UUID SPEED_MODIFIER_SITTING_UUID = UUID.fromString("2EF64346-9E56-44E9-9574-1BF9FD6443CF");
	private static final AttributeModifier SPEED_MODIFIER_SITTING = new AttributeModifier(SPEED_MODIFIER_SITTING_UUID, "Sitting speed reduction", -0.75D, AttributeModifier.Operation.MULTIPLY_BASE);

	public static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.scalable(0.6F, 1.0F);

	private static final UniformInt ANGER_RANGE = TimeUtil.rangeOfSeconds(20, 39);
	private UUID lastHurtBy;
	private int attackTimer;
	private int climbingStamina = 20 + this.random.nextInt(40);

	private boolean isLeader;
	private boolean lookingForBundle;

	@Nullable
	private Chimpanzee groomingTarget;
	@Nullable
	private Chimpanzee groomer;

	private float climbAnim;
	private float climbAnimO;

	private float sitAnim;
	private float sitAnimO;

	private int headShakeAnim;
	private int headShakeAnimO;

	private float flipAnim;
	private float flipAnimO;

	public boolean isPartying = false;
	BlockPos jukeboxPosition;

	public Chimpanzee(EntityType<? extends Animal> type, Level worldIn) {
		super(type, worldIn);
		this.lookControl = new Chimpanzee.LookHelperController();
		this.setCanPickUpLoot(true);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new ChimpSitGoal(this));
		this.goalSelector.addGoal(2, new ChimpGetScaredGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new ChimpGrabBananaGoal(this, 1.25D));
		this.goalSelector.addGoal(4, new ChimpAttackGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new ChimpPanicGoal(this, 1.25D));
		this.goalSelector.addGoal(6, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new ChimpAvoidEntityGoal<>(this, PlantainSpider.class, 4.0F, 1.0D, 1.25D));
		this.goalSelector.addGoal(8, new ChimpOpenBunchGoal(this));
		this.goalSelector.addGoal(9, new ChimpEatBananaGoal(this));
		this.goalSelector.addGoal(10, new ChimpTemptBananaGoal(this, 1.25D));
		this.goalSelector.addGoal(11, new TemptGoal(this, 1.25D, Ingredient.of(NeapolitanItemTags.CHIMPANZEE_FOOD), false));
		this.goalSelector.addGoal(12, new ChimpFollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(13, new ChimpShareBananaGoal(this, 1.0D));
		this.goalSelector.addGoal(14, new ChimpBeGroomedGoal(this));
		this.goalSelector.addGoal(15, new ChimpGroomGoal(this, 1.0D));
		this.goalSelector.addGoal(16, new ChimpFollowOthersGoal(this, 1.0D));
		this.goalSelector.addGoal(17, new ChimpShakeBundleGoal(this, 1.0D, 48, 16));
		this.goalSelector.addGoal(18, new ChimpPlayWithHelmetGoal(this));
		this.goalSelector.addGoal(19, new ChimpCryGoal(this));
		this.goalSelector.addGoal(20, new ChimpShakeHeadGoal(this));
		this.goalSelector.addGoal(21, new ChimpLookAtItemGoal(this));
		this.goalSelector.addGoal(22, new ChimpJumpOnBouncyGoal(this, 1.0D, 16));
		this.goalSelector.addGoal(23, new ChimpPlayNoteBlockGoal(this, 1.0D, 16));
		this.goalSelector.addGoal(24, new ChimpFlipGoal(this));
		this.goalSelector.addGoal(25, new ChimpApeModeGoal(this, 1.0D));
		this.goalSelector.addGoal(26, new ChimpRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(27, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(28, new LookAtPlayerGoal(this, Mob.class, 6.0F));
		this.goalSelector.addGoal(29, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(1, new ChimpHurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CHIMPANZEE_TYPE, 0);
		this.entityData.define(ANGER_TIME, 0);
		this.entityData.define(APE_MODE_TIME, 0);
		this.entityData.define(HUNGER, 0);
		this.entityData.define(DIRTINESS, 0);
		this.entityData.define(PALENESS, 0);
		this.entityData.define(LEFT_HAND_DYE_COLOR, 0);
		this.entityData.define(RIGHT_HAND_DYE_COLOR, 0);
		this.entityData.define(SITTING, false);
		this.entityData.define(IS_LEFT_HAND_DYED, false);
		this.entityData.define(IS_RIGHT_HAND_DYED, false);
		this.entityData.define(ACTION, (byte) 0);
		this.entityData.define(CLIMBING, (byte) 0);
		this.entityData.define(FACING, Direction.DOWN);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
		compound.putInt("ChimpanzeeType", this.getChimpanzeeType());
		compound.putInt("ApeModeTime", this.getApeModeTime());
		compound.putInt("Hunger", this.getHunger());
		compound.putInt("Dirtiness", this.getDirtiness());
		compound.putInt("Paleness", this.getPaleness());
		compound.putByte("LeftHandDyeColor", (byte) this.getHandDyeColor(HumanoidArm.LEFT).getId());
		compound.putByte("RightHandDyeColor", (byte) this.getHandDyeColor(HumanoidArm.RIGHT).getId());
		compound.putBoolean("Sitting", this.isSitting());
		compound.putBoolean("IsLeftHandDyed", this.getHandDyed(HumanoidArm.LEFT));
		compound.putBoolean("IsRightHandDyed", this.getHandDyed(HumanoidArm.RIGHT));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.readPersistentAngerSaveData(this.level, compound);
		this.setChimpanzeeType(compound.getInt("ChimpanzeeType"));
		this.setApeModeTime(compound.getInt("ApeModeTime"));
		this.setHunger(compound.getInt("Hunger"));
		this.setDirtiness(compound.getInt("Dirtiness"));
		this.setPaleness(compound.getInt("Paleness"));
		this.setHandDyeColor(DyeColor.byId(compound.getInt("LeftHandDyeColor")), HumanoidArm.LEFT);
		this.setHandDyeColor(DyeColor.byId(compound.getInt("RightHandDyeColor")), HumanoidArm.RIGHT);
		this.setSitting(compound.getBoolean("Sitting"));
		this.setHandDyed(compound.getBoolean("IsLeftHandDyed"), HumanoidArm.LEFT);
		this.setHandDyed(compound.getBoolean("IsRightHandDyed"), HumanoidArm.RIGHT);
	}

	@Override
	protected BodyRotationControl createBodyControl() {
		return new Chimpanzee.BodyHelperController();
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		return new WallClimberNavigation(this, worldIn);
	}

	// SOUND //

	@Override
	public int getAmbientSoundInterval() {
		return this.getApeModeTime() > 0 ? 20 : 120;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return (this.isAngry() || this.getApeModeTime() > 0) ? NeapolitanSoundEvents.ENTITY_CHIMPANZEE_SCREAM.get() : NeapolitanSoundEvents.ENTITY_CHIMPANZEE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return NeapolitanSoundEvents.ENTITY_CHIMPANZEE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return NeapolitanSoundEvents.ENTITY_CHIMPANZEE_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(NeapolitanSoundEvents.ENTITY_CHIMPANZEE_STEP.get(), 0.15F, 1.0F);
	}

	@Nullable
	@Override
	public SoundEvent getEatingSound(ItemStack itemStackIn) {
		return null;
	}

	public void playScreamSound() {
		this.playSound(NeapolitanSoundEvents.ENTITY_CHIMPANZEE_SCREAM.get(), this.getSoundVolume(), this.getVoicePitch());
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	// AI //

	@Override
	protected void customServerAiStep() {
		this.updatePersistentAnger((ServerLevel) this.level, true);

		if (this.isAngry()) {
			this.lastHurtByPlayerTime = this.tickCount;
		}

		if (this.getApeModeTime() > 0 && this.getMoveControl().hasWanted() && !this.isSitting()) {
			double d0 = this.getMoveControl().getSpeedModifier();
			this.setSprinting(d0 >= 1.0D);
		} else {
			this.setSprinting(false);
		}

		super.customServerAiStep();
	}

	@Override
	public boolean doHurtTarget(Entity entityIn) {
		this.swingArms();
		this.level.broadcastEntityEvent(this, (byte) 4);
		float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
		float f2 = this.isChimpanzeeWeapon(this.getMainHandItem()) || this.isChimpanzeeWeapon(this.getOffhandItem()) ? f1 + 1.0F : f1;
		boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f2);
		if (flag) {
			this.doEnchantDamageEffects(this, entityIn);
		}

		return flag;
	}

	public boolean isChimpanzeeWeapon(ItemStack stack) {
		Item item = stack.getItem();
		return item == Items.STICK || item == Items.BAMBOO;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			if (this.canStandUp()) {
				this.setSitting(false);
			}
			return super.hurt(source, amount);
		}
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide) {
			if (this.isDoingAction(ChimpanzeeAction.DEFAULT, ChimpanzeeAction.CLIMBING)) {
				this.setDefaultAction();
			}
		}

		super.tick();

		if (this.isAlive()) {
			if (!this.level.isClientSide) {
				this.handleClimbing();

				if (this.random.nextInt(60) == 0) {
					this.setLeader(this.shouldBeLeader());
				}

				if (this.random.nextInt(100) == 0 && (this.isInWaterRainOrBubble() || this.level.isRainingAt(this.blockPosition()))) {
					this.setHandDyed(false, this.random.nextBoolean() ? HumanoidArm.LEFT : HumanoidArm.RIGHT);
				}
			}

			this.spawnParticles();
		}

		this.climbAnimO = this.climbAnim;
		if (this.isDoingAction(ChimpanzeeAction.CLIMBING)) {
			this.climbAnim = Math.min(this.climbAnim + 0.125F, 0.75F);
		} else if (this.isDoingAction(ChimpanzeeAction.HANGING, ChimpanzeeAction.SHAKING)) {
			this.climbAnim = Math.min(this.climbAnim + 0.125F, 1);
		} else {
			this.climbAnim = Math.max(this.climbAnim - 0.125F, 0);
		}

		this.sitAnimO = this.sitAnim;
		if (this.isSitting()) {
			this.sitAnim = Math.min(this.sitAnim + 0.167F, 1);
		} else {
			this.sitAnim = Math.max(this.sitAnim - 0.167F, 0);
		}

		this.headShakeAnimO = this.headShakeAnim;
		if (this.headShakeAnim > 0) {
			--this.headShakeAnim;
		}

		this.flipAnimO = this.flipAnim;
		if (this.flipAnim > 0) {
			--this.flipAnim;
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}

		if (this.jukeboxPosition == null || !this.jukeboxPosition.closerToCenterThan(this.position(), 3.46D) || this.level.getBlockState(jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
			this.isPartying = false;
			this.jukeboxPosition = null;
		}

		this.refreshDimensions();

		if (!this.level.isClientSide) {
			if (this.getApeModeTime() > 0) {
				this.setApeModeTime(this.getApeModeTime() - 1);
			}

			if (!this.isHungry() && this.getHunger() >= 0) {
				this.setHunger(this.getHunger() + 1);
				if (this.isHungry()) {
					this.setLeader(this.shouldBeLeader());
				}
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

			if (this.shouldClimb() && this.verticalCollision) {
				if (--this.climbingStamina <= 0) {
					this.climbingStamina = -20;
				}
			} else if (this.onGround) {
				if (this.climbingStamina < 0) {
					++this.climbingStamina;
				} else {
					this.climbingStamina = 20 + this.random.nextInt(40);
				}
			}
		}
	}

	private void spawnParticles() {
		if (this.isDirty()) {
			if (this.tickCount % 6 == 0) {
				double d0 = ((double) this.random.nextFloat() + 1.0D) * 0.06D;
				double d1 = this.random.nextInt(360) - 360.0D;
				double d2 = ((double) this.random.nextFloat() + 1.0D) * 14.0D;
				d2 *= this.random.nextBoolean() ? 1.0D : -1.0D;

				level.addParticle(NeapolitanParticleTypes.FLY.get(), this.getRandomX(0.5D), this.getEyeY() + this.random.nextDouble() * 0.2D + 0.3D, this.getRandomZ(0.5D), d0, d1, d2);
			}
		}

		if (this.isDoingAction(ChimpanzeeAction.EATING)) {
			ItemStack food = this.getSnack();
			if (this.tickCount % 10 == 0 && !food.isEmpty()) {
				if (this.level.isClientSide) {
					for (int i = 0; i < 6; ++i) {
						Vec3 vector3d = new Vec3(((double) this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double) this.random.nextFloat() - 0.5D) * 0.1D);
						vector3d = vector3d.xRot(-this.getXRot() * ((float) Math.PI / 180F));
						vector3d = vector3d.yRot(-this.getYRot() * ((float) Math.PI / 180F));
						double d0 = (double) (-this.random.nextFloat()) * 0.2D;
						Vec3 vector3d1 = new Vec3(((double) this.random.nextFloat() - 0.5D) * 0.2D, d0, 0.6D * this.getScale() + ((double) this.random.nextFloat() - 0.5D) * 0.2D);
						vector3d1 = vector3d1.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
						vector3d1 = vector3d1.add(this.getX(), this.getEyeY(), this.getZ());
						this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItemInHand(this.getSnackHand())), vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y + 0.05D, vector3d.z);
					}
				}

				if (food.getUseAnimation() == UseAnim.DRINK) {
					this.playSound(food.getDrinkingSound(), 0.5F, this.random.nextFloat() * 0.1F + 0.9F);
				} else {
					this.playSound(food.getEatingSound(), 0.5F + 0.5F * (float) this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				}
			}
		} else if (this.isDoingAction(ChimpanzeeAction.CRYING)) {
			if (this.level.isClientSide) {
				if (this.tickCount % 2 == 0 && this.random.nextInt(4) > 0) {
					for (int i = 0; i < 2; ++i) {
						double d0 = i == 0 ? (double) (this.random.nextFloat()) * 0.15D + 0.1D : (double) (-this.random.nextFloat()) * 0.15D - 0.1D;
						double d1 = ((double) this.random.nextFloat()) * 0.1D + 0.15D;
						double d2 = i == 0 ? 0.15D : -0.15D;

						Vec3 vector3d = new Vec3(d0, Math.random() * 0.2D + 0.1D, (double) (this.random.nextFloat()) * 0.2D + 0.1D);
						vector3d = vector3d.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
						Vec3 vector3d1 = new Vec3(d2, d1, 0.35D);
						vector3d1 = vector3d1.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
						vector3d1 = vector3d1.add(this.getX(), this.getEyeY(), this.getZ());

						this.level.addParticle(NeapolitanParticleTypes.TEAR.get(), vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y + 0.05D, vector3d.z);
					}
				}
			}
		}
	}

	private void handleClimbing() {
		this.setBesideClimbableBlock(this.horizontalCollision);

		if (this.isDoingAction(ChimpanzeeAction.CLIMBING)) {
			Direction newfacing = Direction.DOWN;

			for (Direction direction : Direction.Plane.HORIZONTAL) {
				Vec3 vector3d = this.collide(Vec3.atLowerCornerOf(direction.getNormal()));

				if (Math.abs(vector3d.get(direction.getAxis())) <= 0.2D) {
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
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (!itemstack.isEmpty()) {
			if (itemstack.getItem() instanceof MilkshakeItem) {
				InteractionResult actionresulttype = itemstack.interactLivingEntity(player, this, hand);
				if (actionresulttype.consumesAction()) {
					return actionresulttype;
				}
			}

			if (!(this.isFood(itemstack) && !this.isHungry())) {
				if (this.getMainHandItem().isEmpty() || (this.isHungry() && this.isSnack(itemstack) && !this.isSnack(this.getMainHandItem()))) {
					if (!this.getMainHandItem().isEmpty()) {
						this.dropItem(this.getMainHandItem());
					}

					if (this.isSnack(itemstack)) {
						this.stopBeingAngry();
					}

					ItemStack itemstack1 = itemstack.copy();
					itemstack1.setCount(1);
					this.setItemInHand(InteractionHand.MAIN_HAND, itemstack1);
					this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
					this.usePlayerItem(player, InteractionHand.MAIN_HAND, itemstack);

					return InteractionResult.sidedSuccess(this.level.isClientSide);
				}
				return InteractionResult.PASS;
			}
		}

		return super.mobInteract(player, hand);
	}

	public void openBunch(InteractionHand hand) {
		if (!this.level.isClientSide) {
			BananaPeel bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(this.level);
			bananapeel.moveTo(this.getX(), this.getEyeY(), this.getZ(), this.getYRot(), 0.0F);
			bananapeel.setDeltaMovement(this.random.nextDouble() * 0.4D - 0.2D, 0.4D, this.random.nextDouble() * 0.4D - 0.2D);
			this.level.addFreshEntity(bananapeel);

			this.setItemInHand(hand, new ItemStack(NeapolitanItems.BANANA.get()));
		}
	}

	public void eatSnack() {
		if (!this.getSnack().isEmpty()) {
			if (this.getSnack().getItem() == NeapolitanItems.BANANARROW.get()) {
				this.heal((float) NeapolitanItems.BANANA.get().getFoodProperties().getNutrition());
				this.hurt(DamageSource.GENERIC, 0.0F);
				this.setItemInHand(this.getSnackHand(), new ItemStack(Items.ARROW));
			} else {
				if (this.getSnack().isEdible()) {
					this.heal((float) this.getSnack().getItem().getFoodProperties().getNutrition());
				}
				this.setItemInHand(this.getSnackHand(), this.getSnack().finishUsingItem(this.level, this));
			}
		}
		this.setHunger(0);
	}

	@Override
	public void calculateEntityAnimation(LivingEntity entity, boolean isFlying) {
		this.animationSpeedOld = this.animationSpeed;
		double d0 = this.getX() - this.xo;
		double d1 = this.isDoingAction(ChimpanzeeAction.CLIMBING) ? this.getY() - this.yo : 0.0D;
		double d2 = this.getZ() - this.zo;
		float f = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2)) * 4.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		this.animationSpeed += (f - this.animationSpeed) * 0.4F;
		this.animationPosition += this.animationSpeed;
	}

	@Override
	public boolean onClimbable() {
		return this.isDoingAction(ChimpanzeeAction.DEFAULT, ChimpanzeeAction.CLIMBING) && this.isBesideClimbableBlock() && !this.isSitting() && this.climbingStamina > 0;
	}

	public boolean shouldClimb() {
		return !this.onGround && this.onClimbable();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
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

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		if (this.isSitting()) {
			return SITTING_DIMENSIONS.scale(this.getScale());
		} else {
			return super.getDimensions(pose);
		}
	}

	public boolean canStandUp() {
		EntityDimensions entitysize = this.getType().getDimensions();
		float f = entitysize.width / 2.0F;
		Vec3 vector3d = new Vec3(this.getX() - (double) f, this.getY(), this.getZ() - (double) f);
		Vec3 vector3d1 = new Vec3(this.getX() + (double) f, this.getY() + (double) entitysize.height, this.getZ() + (double) f);
		AABB axisalignedbb = new AABB(vector3d, vector3d1);
		return this.level.noCollision(this, axisalignedbb.deflate(1.0E-7D));
	}

	@Override
	public double getMyRidingOffset() {
		return this.isBaby() ? -0.05D : -0.3D;
	}

	@Override
	public boolean canTakeItem(ItemStack itemstackIn) {
		EquipmentSlot equipmentslottype = Mob.getEquipmentSlotForItem(itemstackIn);
		if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
			return false;
		} else {
			return equipmentslottype == EquipmentSlot.MAINHAND && super.canTakeItem(itemstackIn);
		}
	}

	public int getApeModeTime() {
		return this.entityData.get(APE_MODE_TIME);
	}

	public void setApeModeTime(int time) {
		this.entityData.set(APE_MODE_TIME, time);
	}

	@Override
	public boolean canHoldItem(ItemStack stack) {
		ItemStack heldstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
		return heldstack.isEmpty() || this.getItemValue(stack) > this.getItemValue(heldstack);
	}

	private int getItemValue(ItemStack stack) {
		if (this.isSnack(stack)) {
			return this.isHungry() ? 2 : 1;
		} else if (this.isFavoriteItem(stack)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	protected void pickUpItem(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (!this.isDoingAction(ChimpanzeeAction.LOOKING_AT_ITEM, ChimpanzeeAction.PLAYING_WITH_ITEM) && this.canHoldItem(itemstack)) {
			int i = itemstack.getCount();
			if (i > 1) {
				ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), itemstack.split(i - 1));
				this.level.addFreshEntity(itementity);
			}

			this.dropItem(this.getMainHandItem());

			this.onItemPickup(itemEntity);
			this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
			this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
			this.take(itemEntity, itemstack.getCount());
			itemEntity.discard();

			if (this.isSnack(itemstack)) {
				this.stopBeingAngry();
			}
		}
	}

	public boolean isFavoriteItem(ItemStack stack) {
		return stack.is(NeapolitanItemTags.CHIMPANZEE_FAVORITES);
	}

	public void throwHeldItem(InteractionHand hand) {
		ItemStack stack = this.getItemInHand(hand);
		if (!stack.isEmpty() && !this.level.isClientSide) {
			Item item = stack.getItem();

			if (item instanceof ArrowItem arrowItem) {
				List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(8.0D, 4.0D, 8.0D), (entity) -> entity.getType().is(NeapolitanEntityTypeTags.CHIMPANZEE_DART_TARGETS));
				Entity target = null;

				double maxValue = Double.MAX_VALUE;
				for (Entity entity : list) {
					double distance = this.distanceToSqr(entity);
					if (!(distance > maxValue)) {
						maxValue = distance;
						target = entity;
					}
				}

				if (target != null) {
					AbstractArrow arrow = arrowItem.createArrow(level, stack, this);
					double d0 = target.getEyeY() - (double) 1.1F;
					double d1 = target.getX() - this.getX();
					double d2 = d0 - arrow.getY();
					double d3 = target.getZ() - this.getZ();
					float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
					this.lookAt(target, 90.0F, 90.0F);
					arrow.shoot(d1, d2 + (double) f, d3, 1.6F, 6.0F);
					this.playSound(SoundEvents.ARROW_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
					this.level.addFreshEntity(arrow);
					this.setItemInHand(hand, ItemStack.EMPTY);

					this.swingArms();
					this.level.broadcastEntityEvent(this, (byte) 4);
				}

			} else {
				if (item instanceof DyeItem || (ModList.get().isLoaded(NeapolitanConstants.ENVIRONMENTAL) && item == ForgeRegistries.ITEMS.getValue(NeapolitanConstants.MUD_BALL))) {
					HumanoidArm handside = hand == InteractionHand.MAIN_HAND ? this.getMainArm() : this.getMainArm().getOpposite();
					this.setHandDyed(true, handside);
					this.setHandDyeColor(item instanceof DyeItem ? ((DyeItem) item).getDyeColor() : DyeColor.BROWN, handside);
				}

				ItemEntity itemEntity = new ItemEntity(this.level, this.getX() + this.getLookAngle().x * 0.2D, this.getY() + this.getBbHeight() * 0.625F, this.getZ() + this.getLookAngle().z * 0.2D, stack);
				Vec3 vector3d = new Vec3(this.getLookAngle().x * 0.25D, 0.0D, this.getLookAngle().z * 0.25D);
				itemEntity.setDeltaMovement(vector3d);
				itemEntity.setPickUpDelay(40);
				itemEntity.setThrower(this.getUUID());
				this.level.addFreshEntity(itemEntity);
				this.setItemInHand(hand, ItemStack.EMPTY);

				this.swingArms();
				this.level.broadcastEntityEvent(this, (byte) 4);
			}
		}
	}

	public void dropItem(ItemStack itemStack) {
		ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getEyeY() - (double) 0.3F, this.getZ(), itemStack);
		itementity.setPickUpDelay(40);
		itementity.setThrower(this.getUUID());
		this.level.addFreshEntity(itementity);
	}

	public void spawnItemFromBucket(ItemStack itemStack, HumanoidArm hand) {
		Vec3 vector3d = new Vec3(hand == HumanoidArm.LEFT ? 0.35D : -0.35D, 0.0D, 0.5D);
		vector3d = vector3d.yRot(-this.yBodyRot * ((float) Math.PI / 180F));

		ItemEntity itementity = new ItemEntity(this.level, this.getX() + vector3d.x * this.getScale(), this.getEyeY() - (double) 0.15F, this.getZ() + vector3d.z * this.getScale(), itemStack);
		itementity.setDeltaMovement(0.0D, 0.25D, 0.0D);
		itementity.setPickUpDelay(40);
		itementity.setThrower(this.getUUID());
		this.level.addFreshEntity(itementity);
	}

	public void setOffFirework(ItemStack itemStack, HumanoidArm hand) {
		Vec3 vector3d = new Vec3(hand == HumanoidArm.LEFT ? 0.35D : -0.35D, 0.0D, 0.5D);
		vector3d = vector3d.yRot(-this.yBodyRot * ((float) Math.PI / 180F));

		FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(this.level, this, this.getX() + vector3d.x * this.getScale(), this.getEyeY(), this.getZ() + vector3d.z * this.getScale(), itemStack);
		this.level.addFreshEntity(fireworkrocketentity);
	}

	// GROUP BEHAVIOR //

	public boolean isLeader() {
		return this.isLeader;
	}

	public void setLeader(boolean isLeaderIn) {
		this.isLeader = isLeaderIn;
	}

	public boolean isLookingForBundle() {
		return this.lookingForBundle;
	}

	public void setLookingForBundle(boolean lookingForBundleIn) {
		this.lookingForBundle = lookingForBundleIn;

		if (lookingForBundleIn) {
			this.setLeader(this.shouldBeLeader());
			Predicate<Chimpanzee> predicate = (chimpanzeeentity) -> {
				return chimpanzeeentity != this && chimpanzeeentity.getAge() >= 0;
			};
			List<Chimpanzee> list = this.level.getEntitiesOfClass(Chimpanzee.class, this.getBoundingBox().inflate(12.0D, 8.0D, 12.0D), predicate);

			for (Chimpanzee chimpanzeeentity : list) {
				chimpanzeeentity.setLeader(chimpanzeeentity.shouldBeLeader());
			}
		}
	}

	private boolean shouldBeLeader() {
		Predicate<Chimpanzee> predicate = (chimpanzeeentity) -> {
			return chimpanzeeentity != this && chimpanzeeentity.getAge() >= 0;
		};
		List<Chimpanzee> list = this.level.getEntitiesOfClass(Chimpanzee.class, this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);

		if (list.isEmpty()) {
			return false;
		}

		int chimpamount = list.size() + 1;
		int leaderamount = 0;

		for (Chimpanzee chimpanzeeentity : list) {
			if (chimpanzeeentity.isLeader()) {
				++leaderamount;
			}

			if (leaderamount * 4 >= chimpamount) {
				if (!(this.isLookingForBundle() && !chimpanzeeentity.isLookingForBundle())) {
					return false;
				}
			}
		}

		return true;
	}

	// SPAWNING //

	public static boolean canChimpanzeeSpawn(EntityType<Chimpanzee> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
		return world.getRawBrightness(pos, 0) > 8;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		this.setTypeForPosition(this, worldIn);
		this.setHunger(this.random.nextInt(4800));
		this.setDirtiness(this.random.nextInt(4800));
		this.populateDefaultEquipmentSlots(difficultyIn);
		return spawnDataIn;
	}

	public void setTypeForPosition(Chimpanzee entity, LevelAccessor worldIn) {
		if (worldIn.getBiome(this.blockPosition()).value().getRegistryName().getPath().contains("rainforest")) {
			entity.setChimpanzeeType(ChimpanzeeTypes.RAINFOREST.getId());
		} else if (worldIn.getBiome(this.blockPosition()).value().getRegistryName().getPath().contains("bamboo")) {
			entity.setChimpanzeeType(ChimpanzeeTypes.BAMBOO.getId());
		} else {
			entity.setChimpanzeeType(ChimpanzeeTypes.JUNGLE.getId());
		}
	}

	protected void populateDefaultEquipmentSlots(DifficultyInstance difficultyIn) {
		if (this.random.nextFloat() < 0.1F) {
			float f = this.random.nextFloat();
			ItemStack itemstack;
			if (f < 0.6F) {
				if (this.level.getBiome(this.blockPosition()).value().getRegistryName().getPath().contains("bamboo")) {
					itemstack = new ItemStack(Items.BAMBOO);
				} else {
					itemstack = new ItemStack(Items.STICK);
				}
			} else {
				itemstack = new ItemStack(NeapolitanBlocks.BANANA_FROND.get());
			}

			this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
		}

	}

	@Override
	public Chimpanzee getBreedOffspring(ServerLevel world, AgeableMob ageableEntity) {
		Chimpanzee baby = NeapolitanEntityTypes.CHIMPANZEE.get().create(world);
		baby.setChimpanzeeType(this.random.nextBoolean() ? this.getChimpanzeeType() : ((Chimpanzee) ageableEntity).getChimpanzeeType());
		return baby;
	}

	// DATA //

	public int getChimpanzeeType() {
		return this.entityData.get(CHIMPANZEE_TYPE);
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

	public Chimpanzee getGroomingTarget() {
		return this.groomingTarget;
	}

	public void setGroomingTarget(Chimpanzee target) {
		this.groomingTarget = target;
	}

	public Chimpanzee getGroomer() {
		return this.groomer;
	}

	public void setGroomer(Chimpanzee groomerIn) {
		this.groomer = groomerIn;
	}

	public Direction getFacing() {
		return this.entityData.get(FACING);
	}

	public void setFacing(Direction direction) {
		this.entityData.set(FACING, direction);
	}

	public boolean isSitting() {
		return this.entityData.get(SITTING);
	}

	public void setSitting(boolean sitting) {
		this.entityData.set(SITTING, sitting);
		AttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
		if (modifiableattributeinstance.getModifier(SPEED_MODIFIER_SITTING_UUID) != null) {
			modifiableattributeinstance.removeModifier(SPEED_MODIFIER_SITTING);
		}

		if (sitting) {
			modifiableattributeinstance.addTransientModifier(SPEED_MODIFIER_SITTING);
		}
	}

	public DyeColor getHandDyeColor(HumanoidArm handSide) {
		return DyeColor.byId(handSide == HumanoidArm.LEFT ? this.entityData.get(LEFT_HAND_DYE_COLOR) : this.entityData.get(RIGHT_HAND_DYE_COLOR));
	}

	public void setHandDyeColor(DyeColor dyeColor, HumanoidArm handSide) {
		this.entityData.set(handSide == HumanoidArm.LEFT ? LEFT_HAND_DYE_COLOR : RIGHT_HAND_DYE_COLOR, dyeColor.getId());
	}

	public boolean getHandDyed(HumanoidArm handSide) {
		return handSide == HumanoidArm.LEFT ? this.entityData.get(IS_LEFT_HAND_DYED) : this.entityData.get(IS_RIGHT_HAND_DYED);
	}

	public void setHandDyed(boolean dyed, HumanoidArm handSide) {
		this.entityData.set(handSide == HumanoidArm.LEFT ? IS_LEFT_HAND_DYED : IS_RIGHT_HAND_DYED, dyed);
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
		this.setRemainingPersistentAngerTime(ANGER_RANGE.sample(this.random));
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

	public InteractionHand getSnackHand() {
		if (!this.getSnack(InteractionHand.MAIN_HAND).isEmpty()) {
			return InteractionHand.MAIN_HAND;
		} else {
			return InteractionHand.OFF_HAND;
		}
	}

	public ItemStack getSnack() {
		if (!this.getSnack(InteractionHand.MAIN_HAND).isEmpty()) {
			return this.getSnack(InteractionHand.MAIN_HAND);
		} else if (!this.getSnack(InteractionHand.OFF_HAND).isEmpty()) {
			return this.getSnack(InteractionHand.OFF_HAND);
		}
		return ItemStack.EMPTY;
	}

	public ItemStack getSnack(InteractionHand hand) {
		ItemStack snack = this.getItemInHand(hand);
		if (this.isSnack(snack)) {
			return snack;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(NeapolitanItemTags.CHIMPANZEE_FOOD);
	}

	public boolean isSnack(ItemStack stack) {
		return stack.is(NeapolitanItemTags.CHIMPANZEE_SNACKS);
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
		this.setHandDyed(false, HumanoidArm.LEFT);
		this.setHandDyed(false, HumanoidArm.RIGHT);
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
		return Mth.clamp((this.getPaleness() - 4800.0F) / 1200.0F, 0.0F, 1.0F);
	}

	public boolean isInSunlight() {
		BlockPos blockpos = this.getVehicle() instanceof Boat ? (new BlockPos(this.getX(), (double) Math.round(this.getY()), this.getZ())).above() : new BlockPos(this.getX(), (double) Math.round(this.getY()), this.getZ());
		return this.level.getBrightness(LightLayer.SKY, blockpos) > 8;
	}

	// ACTION STUFF //

	public ChimpanzeeAction getAction() {
		return ChimpanzeeAction.byId(this.entityData.get(ACTION));
	}

	public boolean isDoingAction(ChimpanzeeAction... actions) {
		for (ChimpanzeeAction action : actions) {
			if (this.getAction() == action) {
				return true;
			}
		}

		return false;
	}

	public void setAction(ChimpanzeeAction action) {
		this.entityData.set(ACTION, (byte) action.getId());
	}

	public void setDefaultAction() {
		if (!this.isPassenger() && this.shouldClimb()) {
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
		if (this.isDoingAction(ChimpanzeeAction.EATING)) {
			return Math.sin(Math.PI * this.tickCount * 0.2D) > 0;
		} else if (this.isDoingAction(ChimpanzeeAction.CRYING, ChimpanzeeAction.PLAYING_WITH_ITEM, ChimpanzeeAction.PLAYING_WITH_HELMET, ChimpanzeeAction.JUMPING, ChimpanzeeAction.DRUMMING)) {
			return true;
		} else return this.getApeModeTime() > 0 || this.isAngry() || this.isHungry() || this.isPartying();
	}

	@OnlyIn(Dist.CLIENT)
	public float getClimbingAnim(float partialTicks) {
		return Mth.lerp(partialTicks, this.climbAnimO, this.climbAnim);
	}

	@OnlyIn(Dist.CLIENT)
	public float getSitAnim(float partialTicks) {
		return Mth.lerp(partialTicks, this.sitAnimO, this.sitAnim);
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadShakeAnim(float partialTicks) {
		return Mth.lerp(partialTicks, this.headShakeAnimO, this.headShakeAnim);
	}

	@OnlyIn(Dist.CLIENT)
	public float getFlipAnim(float partialTicks) {
		return Mth.lerp(partialTicks, this.flipAnimO, this.flipAnim);
	}

	public void swingArms() {
		this.attackTimer = 10;
	}

	public void shakeHead() {
		this.headShakeAnim = 40;
		this.headShakeAnimO = 40;
	}

	public void doFlip() {
		this.flipAnim = 20;
		this.flipAnimO = 20;
	}

	public void shakeHead(ParticleOptions particleData) {
		this.shakeHead();

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
			this.shakeHead(NeapolitanParticleTypes.CHIMPANZEE_NEEDS_FRIEND.get());
		} else if (id == 7) {
			this.shakeHead(NeapolitanParticleTypes.CHIMPANZEE_NEEDS_SUN.get());
		} else if (id == 8) {
			this.shakeHead(NeapolitanParticleTypes.CHIMPANZEE_NEEDS_FOOD.get());
		} else if (id == 9) {
			this.doFlip();
		} else {
			super.handleEntityEvent(id);
		}
	}

	// CONTROLLERS //

	class LookHelperController extends LookControl {

		public LookHelperController() {
			super(Chimpanzee.this);
		}

		public void tick() {
			if (this.resetXRotOnTick()) {
				Chimpanzee.this.setXRot(0.0F);
			}

			if (this.isLookingAtTarget()) {
				if (this.getYRotD().isPresent() && this.getXRotD().isPresent()) {
					Chimpanzee.this.yHeadRot = this.rotateTowards(Chimpanzee.this.yHeadRot, this.getYRotD().get(), this.yMaxRotSpeed);
					Chimpanzee.this.setXRot(this.rotateTowards(Chimpanzee.this.getXRot(), this.getXRotD().get(), this.xMaxRotAngle));
				}
			} else {
				Chimpanzee.this.yHeadRot = this.rotateTowards(Chimpanzee.this.yHeadRot, Chimpanzee.this.yBodyRot, 10.0F);
			}

			Direction facing = Chimpanzee.this.getFacing();
			if (Chimpanzee.this.isDoingAction(ChimpanzeeAction.CLIMBING) && facing != Direction.DOWN) {
				Chimpanzee.this.yHeadRot = Mth.rotateIfNecessary(Chimpanzee.this.yHeadRot, facing.toYRot(), (float) Chimpanzee.this.getMaxHeadYRot());
			} else if (!Chimpanzee.this.getNavigation().isDone()) {
				Chimpanzee.this.yHeadRot = Mth.rotateIfNecessary(Chimpanzee.this.yHeadRot, Chimpanzee.this.yBodyRot, (float) Chimpanzee.this.getMaxHeadYRot());
			}
		}
	}

	class BodyHelperController extends BodyRotationControl {
		private int bodyRotationTickCounter;
		private Direction prevFacing;

		public BodyHelperController() {
			super(Chimpanzee.this);
		}

		public void clientTick() {
			super.clientTick();

			Direction facing = Chimpanzee.this.getFacing();

			if (facing != this.prevFacing || !Chimpanzee.this.isDoingAction(ChimpanzeeAction.CLIMBING)) {
				this.bodyRotationTickCounter = 10;
			}

			this.prevFacing = facing;

			if (facing != Direction.DOWN && Chimpanzee.this.isDoingAction(ChimpanzeeAction.CLIMBING)) {
				int i = this.bodyRotationTickCounter;
				float f = Mth.clamp((float) i / 10.0F, 0.0F, 1.0F);
				float f1 = 90.0F * f;
				Chimpanzee.this.yBodyRot = Mth.rotateIfNecessary(Chimpanzee.this.yBodyRot, facing.toYRot(), f1);

				if (this.bodyRotationTickCounter > 0) {
					--this.bodyRotationTickCounter;
				}
			} else if (facing != Direction.DOWN && Chimpanzee.this.isDoingAction(ChimpanzeeAction.CLIMBING)) {
				int i = this.bodyRotationTickCounter;
				float f = Mth.clamp((float) i / 10.0F, 0.0F, 1.0F);
				float f1 = 90.0F * f;
				Chimpanzee.this.yBodyRot = Mth.rotateIfNecessary(Chimpanzee.this.yBodyRot, facing.toYRot(), f1);

				if (this.bodyRotationTickCounter > 0) {
					--this.bodyRotationTickCounter;
				}
			}
		}
	}
}
