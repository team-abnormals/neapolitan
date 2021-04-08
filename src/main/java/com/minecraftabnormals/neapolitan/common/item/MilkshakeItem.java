package com.minecraftabnormals.neapolitan.common.item;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class MilkshakeItem extends DrinkItem {
	private final EffectType effectType;

	public MilkshakeItem(EffectType effectType, Properties builder) {
		super(builder);
		this.effectType = effectType;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entity) {
		handleEffects(entity);
		return super.onItemUseFinish(stack, worldIn, entity);
	}

	@Override
	public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
		if (entity.world.isRemote)
			return ActionResultType.PASS;
		entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_WANDERING_TRADER_DRINK_MILK, SoundCategory.NEUTRAL, 1, 1);

		if (player instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}

		if (entity.getActivePotionEffect(NeapolitanEffects.VANILLA_SCENT.get()) == null)
			handleEffects(entity);

		if (!player.abilities.isCreativeMode) {
			stack.shrink(1);
			ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
			if (!player.inventory.addItemStackToInventory(itemstack)) {
				player.dropItem(itemstack, false);
			}
		}

		return ActionResultType.SUCCESS;
	}

	private void handleEffects(LivingEntity user) {
		ImmutableList<EffectInstance> effects = ImmutableList.copyOf(user.getActivePotionEffects());
		if (this.getEffectType() != null) {
			for (int i = 0; i < effects.size(); ++i) {
				Effect effect = effects.get(i).getPotion();
				if (effect.getEffectType() == this.getEffectType() || (this.getEffectType() == EffectType.HARMFUL && effect == Effects.BAD_OMEN) || this.getEffectType() == EffectType.NEUTRAL) {
					user.removePotionEffect(effect);
				}
			}
		} else {
			LivingEntity nearest = user.world.getClosestEntityWithinAABB(LivingEntity.class, EntityPredicate.DEFAULT.setCustomPredicate((living) -> living != user && living.getActivePotionEffect(NeapolitanEffects.VANILLA_SCENT.get()) == null), user, user.getPosX(), user.getPosY(), user.getPosZ(), user.getBoundingBox().grow(6.0D, 2.0D, 6.0D));
			if (nearest != null) {
				ImmutableList<EffectInstance> nearestEffects = ImmutableList.copyOf(nearest.getActivePotionEffects());
				if (this == NeapolitanItems.BANANA_MILKSHAKE.get()) {
					user.clearActivePotions();
					nearest.clearActivePotions();
					effects.forEach(nearest::addPotionEffect);
					nearestEffects.forEach(user::addPotionEffect);
				} else if (this == NeapolitanItems.MINT_MILKSHAKE.get()) {
					nearest.clearActivePotions();
					nearestEffects.forEach(user::addPotionEffect);
				} else if (this == NeapolitanItems.ADZUKI_MILKSHAKE.get()) {
					user.clearActivePotions();
					effects.forEach(nearest::addPotionEffect);
				}
			}
		}
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public SoundEvent getDrinkSound() {
		return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
	}

	@Override
	public SoundEvent getEatSound() {
		return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
	}

	public EffectType getEffectType() {
		return this.effectType;
	}
}
