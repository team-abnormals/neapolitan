package com.teamabnormals.neapolitan.common.entity;

import com.teamabnormals.neapolitan.core.other.NeapolitanCriteriaTriggers;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import java.util.List;
import java.util.Objects;

public class BananarrowEntity extends AbstractArrow {
	public boolean impacted = false;

	public BananarrowEntity(EntityType<? extends BananarrowEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public BananarrowEntity(Level worldIn, double x, double y, double z) {
		super(NeapolitanEntityTypes.BANANARROW.get(), x, y, z, worldIn);
	}

	public BananarrowEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
		this(NeapolitanEntityTypes.BANANARROW.get(), world);
	}

	public BananarrowEntity(Level worldIn, LivingEntity shooter) {
		super(NeapolitanEntityTypes.BANANARROW.get(), shooter, worldIn);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!impacted) {
			BananaPeelEntity bananaPeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(level);
			bananaPeel.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			this.level.addFreshEntity(bananaPeel);
			this.impacted = true;
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		if (!impacted && !(entity instanceof BananaPeelEntity)) {
			BananaPeelEntity bananaPeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(level);
			bananaPeel.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			this.level.addFreshEntity(bananaPeel);
			this.impacted = true;
			if (entity instanceof LivingEntity && !level.isClientSide()) {
				((LivingEntity) entity).addEffect(new MobEffectInstance(NeapolitanEffects.SLIPPING.get(), 100));
			}
		}

		if (entity instanceof LivingEntity && !(entity instanceof ChimpanzeeEntity)) {
			LivingEntity livingEntity = (LivingEntity) entity;
			List<ChimpanzeeEntity> chimps = level.getEntitiesOfClass(ChimpanzeeEntity.class, livingEntity.getBoundingBox().inflate(16.0D, 6.0D, 16.0D));
			for (ChimpanzeeEntity chimp : chimps) {
				if (!chimp.isBaby()) {
					chimp.setTarget(livingEntity);
				}
			}

			if (!chimps.isEmpty() && this.getOwner() instanceof ServerPlayer)
				NeapolitanCriteriaTriggers.CHIMPANZEE_ATTACK.trigger((ServerPlayer) Objects.requireNonNull(this.getOwner()));
		}
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(!this.impacted ? NeapolitanItems.BANANARROW.get() : Items.ARROW);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}