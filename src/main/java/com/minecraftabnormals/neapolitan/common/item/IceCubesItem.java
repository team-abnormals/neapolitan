package com.minecraftabnormals.neapolitan.common.item;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanSounds;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

import net.minecraft.item.Item.Properties;

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
