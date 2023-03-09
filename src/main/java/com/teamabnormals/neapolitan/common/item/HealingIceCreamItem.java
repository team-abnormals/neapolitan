package com.teamabnormals.neapolitan.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HealingIceCreamItem extends IceCreamItem {
	private final float healAmount;

	public HealingIceCreamItem(float healAmount, Properties builder) {
		super(builder);
		this.healAmount = healAmount;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		HealingItem.applyHealing(this.healAmount, worldIn, entityLiving);
		return super.finishUsingItem(stack, worldIn, entityLiving);
	}
}
