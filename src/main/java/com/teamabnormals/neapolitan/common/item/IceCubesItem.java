package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.core.registry.NeapolitanSounds;
import net.minecraft.world.item.Item;
import net.minecraft.sounds.SoundEvent;

public class IceCubesItem extends Item {

	public IceCubesItem(Properties properties) {
		super(properties);
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return NeapolitanSounds.ITEM_ICE_CUBES_EAT.get();
	}

	@Override
	public SoundEvent getEatingSound() {
		return NeapolitanSounds.ITEM_ICE_CUBES_EAT.get();
	}
}
