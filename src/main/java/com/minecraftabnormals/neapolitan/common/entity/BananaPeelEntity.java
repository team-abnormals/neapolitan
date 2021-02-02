package com.minecraftabnormals.neapolitan.common.entity;

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
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerData() {
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(NeapolitanItems.BANANA.get());
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return this.isAlive();
	}

	@Override
	public void tick() {
		super.tick();
		this.setMotion(this.getMotion().mul(0.65F, 1.0F, 0.65F));

		if (!this.hasNoGravity()) {
			this.setMotion(this.getMotion().add(0.0D, -0.06D, 0.0D));
		}
		this.getMotion().mul(0.9F, 0.9F, 0.9F);
		this.move(MoverType.SELF, this.getMotion());

		if (!this.world.isRemote) {
			if (this.age < 0)
				this.remove();
			else
				this.age += -1;
		}
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		super.applyEntityCollision(entityIn);
		if (entityIn instanceof LivingEntity && !this.world.isRemote()) {
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(NeapolitanEffects.SLIPPING.get(), 100, 0, false, false, false));
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			if (this.isAlive() && !this.world.isRemote) {
				this.remove();
				this.markVelocityChanged();
				this.playSound(SoundEvents.ENTITY_ITEM_FRAME_BREAK, 1.0F, 1.0F);
			}
			return true;
		}
	}

	public int getAge() {
		return this.age;
	}
}