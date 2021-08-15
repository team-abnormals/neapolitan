package com.minecraftabnormals.neapolitan.common.entity;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BananaPeelEntity extends Entity {
	private int age;

	public BananaPeelEntity(EntityType<? extends BananaPeelEntity> type, World worldIn) {
		super(type, worldIn);
		this.age = 1200;
	}

	public BananaPeelEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(NeapolitanEntities.BANANA_PEEL.get(), world);
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
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

		if (this.onGround) {
			this.setDeltaMovement(this.getDeltaMovement().multiply(0.65F, 1.0F, 0.65F));
		}

		if (!this.isNoGravity()) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.06D, 0.0D));
		}
		this.getDeltaMovement().multiply(0.9F, 0.9F, 0.9F);
		this.move(MoverType.SELF, this.getDeltaMovement());

		if (!this.level.isClientSide) {
			if (this.age < 0)
				this.remove();
			else
				this.age += -1;
		}
	}

	@Override
	public void push(Entity entityIn) {
		super.push(entityIn);
		if (this.onGround && !this.isInWater() && entityIn instanceof LivingEntity && !NeapolitanTags.EntityTypes.UNAFFECTED_BY_SLIPPING.contains(entityIn.getType()) && !this.level.isClientSide()) {
			((LivingEntity) entityIn).addEffect(new EffectInstance(NeapolitanEffects.SLIPPING.get(), 100));
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			if (this.isAlive() && !this.level.isClientSide) {
				this.remove();
				this.markHurt();
				this.playSound(SoundEvents.ITEM_FRAME_BREAK, 1.0F, 1.0F);
			}
			return true;
		}
	}

	public int getAge() {
		return this.age;
	}
}