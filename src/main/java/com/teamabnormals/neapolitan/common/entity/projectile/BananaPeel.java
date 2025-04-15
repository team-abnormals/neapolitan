package com.teamabnormals.neapolitan.common.entity.projectile;

import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BananaPeel extends Entity {
	private int age;

	public BananaPeel(EntityType<? extends BananaPeel> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		this.age = compound.getShort("Age");
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putShort("Age", (short) this.age);
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(NeapolitanItems.BANANA.get());
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	@Override
	public boolean isPickable() {
		return this.isAlive();
	}

	@Override
	public void tick() {
		super.tick();

		if (this.onGround()) {
			this.setDeltaMovement(this.getDeltaMovement().multiply(0.65F, 1.0F, 0.65F));
		}

		if (!this.isNoGravity()) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.06D, 0.0D));
		}
		this.getDeltaMovement().multiply(0.9F, 0.9F, 0.9F);
		this.move(MoverType.SELF, this.getDeltaMovement());

		if (!this.level().isClientSide) {
			++this.age;
			if (this.age >= 1200) {
				this.discard();
			}
		}
	}

	@Override
	public void push(Entity entityIn) {
		super.push(entityIn);
		if (this.onGround() && !this.isInWater() && entityIn instanceof LivingEntity && this.getY() <= entityIn.getY() && !entityIn.getType().is(NeapolitanEntityTypeTags.UNAFFECTED_BY_SLIPPING) && !this.level().isClientSide()) {
			((LivingEntity) entityIn).addEffect(new MobEffectInstance(NeapolitanMobEffects.SLIPPING, 100));
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			if (this.isAlive() && !this.level().isClientSide) {
				this.discard();
				this.markHurt();
				this.playSound(SoundEvents.ITEM_FRAME_BREAK, 1.0F, 1.0F);
			}
			return true;
		}
	}

	public void throwPeel(float pitch, float yaw, double xSpeed, double ySpeed, double zSpeed, float uncertainty) {
		float f = -Mth.sin(yaw * Mth.DEG_TO_RAD) * Mth.cos(pitch * Mth.DEG_TO_RAD) * 0.6F;
		float f1 = -Mth.sin(pitch * Mth.DEG_TO_RAD) * 0.6F;
		float f2 = Mth.cos(yaw * Mth.DEG_TO_RAD) * Mth.cos(pitch * Mth.DEG_TO_RAD) * 0.6F;
		this.setDeltaMovement(new Vec3(f, f1, f2).add(xSpeed, ySpeed, zSpeed).add(this.random.triangle(0.0D, 0.0172275D * (double) uncertainty), this.random.triangle(0.0D, 0.0172275D * (double) uncertainty), this.random.triangle(0.0D, 0.0172275D * (double) uncertainty)));
	}

	public int getAge() {
		return this.age;
	}
}