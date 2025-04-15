package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IceCreamItem extends Item {

	public IceCreamItem(Properties builder) {
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		entity.setTicksFrozen(entity.getTicksFrozen() + 200);
		return super.finishUsingItem(stack, level, entity);
	}

	@Override
	public SoundEvent getEatingSound() {
		return NeapolitanSoundEvents.ICE_CREAM_EAT.get();
	}
}