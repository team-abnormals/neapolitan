package com.teamabnormals.neapolitan.common.entity.projectile;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
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

import javax.annotation.Nullable;
import java.util.List;

public class Bananarrow extends AbstractArrow {
	public boolean impacted = false;

	public Bananarrow(EntityType<? extends Bananarrow> entityType, Level level) {
		super(entityType, level);
	}

	public Bananarrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(NeapolitanEntityTypes.BANANARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
	}

	public Bananarrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(NeapolitanEntityTypes.BANANARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!impacted) {
			BananaPeel bananaPeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(this.level());
			bananaPeel.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			this.level().addFreshEntity(bananaPeel);
			this.impacted = true;
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		if (!impacted && !(entity instanceof BananaPeel)) {
			BananaPeel bananaPeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(this.level());
			bananaPeel.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			this.level().addFreshEntity(bananaPeel);
			this.impacted = true;
			if (entity instanceof LivingEntity && !this.level().isClientSide()) {
				((LivingEntity) entity).addEffect(new MobEffectInstance(NeapolitanMobEffects.SLIPPING, 100));
			}
		}

		if (entity instanceof LivingEntity livingEntity && !(entity instanceof Chimpanzee)) {
			List<Chimpanzee> chimps = this.level().getEntitiesOfClass(Chimpanzee.class, livingEntity.getBoundingBox().inflate(16.0D, 6.0D, 16.0D));
			for (Chimpanzee chimp : chimps) {
				if (!chimp.isBaby() && livingEntity.canBeSeenAsEnemy()) {
					chimp.setTarget(livingEntity);
				}
			}

			// TODO: Advancements
			// if (!chimps.isEmpty() && this.getOwner() instanceof ServerPlayer) NeapolitanCriteriaTriggers.CHIMPANZEE_ATTACK.trigger((ServerPlayer) Objects.requireNonNull(this.getOwner()));
		}
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(!this.impacted ? NeapolitanItems.BANANARROW.get() : Items.ARROW);
	}
}