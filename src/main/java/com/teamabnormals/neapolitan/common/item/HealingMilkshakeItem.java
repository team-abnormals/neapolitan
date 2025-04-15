package com.teamabnormals.neapolitan.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class HealingMilkshakeItem extends MilkshakeItem {
	private final float healAmount;

	public HealingMilkshakeItem(float healAmount, Properties builder) {
		super(builder);
		this.healAmount = healAmount;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		HealingItem.applyHealing(this.healAmount, worldIn, entityLiving);
		return super.finishUsingItem(stack, worldIn, entityLiving);
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
