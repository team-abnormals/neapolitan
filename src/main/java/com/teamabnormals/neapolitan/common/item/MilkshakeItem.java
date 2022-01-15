package com.teamabnormals.neapolitan.common.item;

import com.google.common.collect.Lists;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class MilkshakeItem extends DrinkItem {
	private final MobEffectCategory effectType;

	public MilkshakeItem(MobEffectCategory effectType, Properties builder) {
		super(builder);
		this.effectType = effectType;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entity) {
		handleEffects(entity);
		return super.finishUsingItem(stack, worldIn, entity);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity.level.isClientSide)
			return InteractionResult.CONSUME;
		entity.level.playSound(null, entity.blockPosition(), SoundEvents.WANDERING_TRADER_DRINK_MILK, SoundSource.NEUTRAL, 1, 1);

		if (player instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) player;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (entity.getEffect(NeapolitanEffects.VANILLA_SCENT.get()) == null)
			handleEffects(entity);

		if (!player.getAbilities().instabuild) {
			stack.shrink(1);
			ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
			if (!player.getInventory().add(itemstack)) {
				player.drop(itemstack, false);
			}
		}

		return InteractionResult.SUCCESS;
	}

	private void handleEffects(LivingEntity user) {
		List<MobEffectInstance> effects = Lists.newArrayList(user.getActiveEffects());
		if (this.getEffectType() != null) {
			for (MobEffectInstance effectInstance : effects) {
				MobEffect effect = effectInstance.getEffect();
				if (effect.getCategory() == this.getEffectType() || (this.getEffectType() == MobEffectCategory.HARMFUL && effect == MobEffects.BAD_OMEN) || this.getEffectType() == MobEffectCategory.NEUTRAL) {
					user.removeEffect(effect);
				}
			}
		} else {
			LivingEntity nearest = user.level.getNearestEntity(LivingEntity.class, TargetingConditions.DEFAULT.selector((living) -> living != user && living.getEffect(NeapolitanEffects.VANILLA_SCENT.get()) == null), user, user.getX(), user.getY(), user.getZ(), user.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
			if (nearest != null) {
				List<MobEffectInstance> nearestEffects = Lists.newArrayList(nearest.getActiveEffects());

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

	private void nerfEffects(List<MobEffectInstance> effects) {
		List<MobEffectInstance> toNerf = Lists.newArrayList();
		effects.forEach(effect -> {
			if (effect.getDuration() > 32766)
				toNerf.add(effect);
		});
		if (toNerf.isEmpty())
			return;
		effects.removeAll(toNerf);
		toNerf.forEach((effect -> effects.add(new MobEffectInstance(effect.getEffect(), 32766, effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()))));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	public MobEffectCategory getEffectType() {
		return this.effectType;
	}
}
