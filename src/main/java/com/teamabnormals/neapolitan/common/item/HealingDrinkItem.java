package com.teamabnormals.neapolitan.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HealingDrinkItem extends DrinkItem {
	private final float healAmount;

	public HealingDrinkItem(float healAmount, Properties builder) {
		super(builder);
		this.healAmount = healAmount;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entity) {
		HealingItem.applyHealing(this.healAmount, worldIn, entity);
		return super.finishUsingItem(stack, worldIn, entity);
	}
}
