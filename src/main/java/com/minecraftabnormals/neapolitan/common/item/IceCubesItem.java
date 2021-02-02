package com.minecraftabnormals.neapolitan.common.item;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

public class IceCubesItem extends Item {

	public IceCubesItem(Properties properties) {
		super(properties);
	}

	@Override
	public SoundEvent getDrinkSound() {
		return NeapolitanSoundEvents.ITEM_ICE_CUBES_EAT.get();
	}

	@Override
	public SoundEvent getEatSound() {
		return NeapolitanSoundEvents.ITEM_ICE_CUBES_EAT.get();
	}
}
