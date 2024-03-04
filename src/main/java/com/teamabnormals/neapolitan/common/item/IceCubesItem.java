package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class IceCubesItem extends Item {

	public IceCubesItem(Properties properties) {
		super(properties);
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return NeapolitanSoundEvents.ICE_CUBES_EAT.get();
	}

	@Override
	public SoundEvent getEatingSound() {
		return NeapolitanSoundEvents.ICE_CUBES_EAT.get();
	}
}
