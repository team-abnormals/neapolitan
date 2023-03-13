package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.blueprint.common.item.FuelBlockItem;
import net.minecraft.world.level.block.Block;

public class ItemNameFuelBlockItem extends FuelBlockItem {

	public ItemNameFuelBlockItem(Block block, int burnTime, Properties properties) {
		super(block, burnTime, properties);
	}

	@Override
	public String getDescriptionId() {
		return this.getOrCreateDescriptionId();
	}
}
