package com.minecraftabnormals.neapolitan.common.entity;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanCriteriaTriggers;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;
import java.util.Objects;

public class BananarrowEntity extends AbstractArrowEntity {
	public boolean impacted = false;

	public BananarrowEntity(EntityType<? extends BananarrowEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public BananarrowEntity(World worldIn, double x, double y, double z) {
		super(NeapolitanEntities.BANANARROW.get(), x, y, z, worldIn);
	}

	public BananarrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(NeapolitanEntities.BANANARROW.get(), world);
	}

	public BananarrowEntity(World worldIn, LivingEntity shooter) {
		super(NeapolitanEntities.BANANARROW.get(), shooter, worldIn);
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult result) {
		super.onHitBlock(result);
		if (!impacted) {
			BananaPeelEntity bananaPeel = NeapolitanEntities.BANANA_PEEL.get().create(level);
			bananaPeel.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			this.level.addFreshEntity(bananaPeel);
			this.impacted = true;
		}
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		if (!impacted && !(entity instanceof BananaPeelEntity)) {
			BananaPeelEntity bananaPeel = NeapolitanEntities.BANANA_PEEL.get().create(level);
			bananaPeel.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			this.level.addFreshEntity(bananaPeel);
			this.impacted = true;
			if (entity instanceof LivingEntity && !level.isClientSide()) {
				((LivingEntity) entity).addEffect(new EffectInstance(NeapolitanEffects.SLIPPING.get(), 100));
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

			if (!chimps.isEmpty() && this.getOwner() instanceof ServerPlayerEntity)
				NeapolitanCriteriaTriggers.CHIMPANZEE_ATTACK.trigger((ServerPlayerEntity) Objects.requireNonNull(this.getOwner()));
		}
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(!this.impacted ? NeapolitanItems.BANANARROW.get() : Items.ARROW);
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}