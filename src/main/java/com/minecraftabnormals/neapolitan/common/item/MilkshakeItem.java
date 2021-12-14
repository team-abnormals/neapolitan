package com.minecraftabnormals.neapolitan.common.item;

import com.google.common.collect.Lists;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class MilkshakeItem extends DrinkItem {
	private final EffectType effectType;

	public MilkshakeItem(EffectType effectType, Properties builder) {
		super(builder);
		this.effectType = effectType;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entity) {
		handleEffects(entity);
		return super.finishUsingItem(stack, worldIn, entity);
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
		if (entity.level.isClientSide)
			return ActionResultType.CONSUME;
		entity.level.playSound(null, entity.blockPosition(), SoundEvents.WANDERING_TRADER_DRINK_MILK, SoundCategory.NEUTRAL, 1, 1);

		if (player instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (entity.getEffect(NeapolitanEffects.VANILLA_SCENT.get()) == null)
			handleEffects(entity);

		if (!player.abilities.instabuild) {
			stack.shrink(1);
			ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
			if (!player.inventory.add(itemstack)) {
				player.drop(itemstack, false);
			}
		}

		return ActionResultType.SUCCESS;
	}

	private void handleEffects(LivingEntity user) {
		List<EffectInstance> effects = Lists.newArrayList(user.getActiveEffects());
		if (this.getEffectType() != null) {
			for (EffectInstance effectInstance : effects) {
				Effect effect = effectInstance.getEffect();
				if (effect.getCategory() == this.getEffectType() || (this.getEffectType() == EffectType.HARMFUL && effect == Effects.BAD_OMEN) || this.getEffectType() == EffectType.NEUTRAL) {
					user.removeEffect(effect);
				}
			}
		} else {
			LivingEntity nearest = user.level.getNearestEntity(LivingEntity.class, EntityPredicate.DEFAULT.selector((living) -> living != user && living.getEffect(NeapolitanEffects.VANILLA_SCENT.get()) == null), user, user.getX(), user.getY(), user.getZ(), user.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
			if (nearest != null) {
				List<EffectInstance> nearestEffects = Lists.newArrayList(nearest.getActiveEffects());

				nerfEffects(effects);
				nerfEffects(nearestEffects);

				if (this == NeapolitanItems.BANANA_MILKSHAKE.get()) {
					user.removeAllEffects();
					nearest.removeAllEffects();
					effects.forEach(nearest::addEffect);
					nearestEffects.forEach(user::addEffect);
				} else if (this == NeapolitanItems.MINT_MILKSHAKE.get()) {
					nearest.removeAllEffects();
					nearestEffects.forEach(user::addEffect);
				} else if (this == NeapolitanItems.ADZUKI_MILKSHAKE.get()) {
					user.removeAllEffects();
					effects.forEach(nearest::addEffect);
				}
			}
		}
	}

	private void nerfEffects(List<EffectInstance> effects) {
		List<EffectInstance> toNerf = Lists.newArrayList();
		effects.forEach(effect -> {
			if (effect.getDuration() > 32766)
				toNerf.add(effect);
		});
		if (toNerf.isEmpty())
			return;
		effects.removeAll(toNerf);
		toNerf.forEach((effect -> effects.add(new EffectInstance(effect.getEffect(), 32766, effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()))));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	public EffectType getEffectType() {
		return this.effectType;
	}
}
