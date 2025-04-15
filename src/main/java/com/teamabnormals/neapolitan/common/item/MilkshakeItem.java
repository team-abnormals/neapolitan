package com.teamabnormals.neapolitan.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class MilkshakeItem extends DrinkItem {

	public MilkshakeItem(Properties builder) {
		super(builder);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
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
}
