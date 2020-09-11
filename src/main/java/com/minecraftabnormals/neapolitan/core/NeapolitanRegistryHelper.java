package com.minecraftabnormals.neapolitan.core;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.teamabnormals.abnormals_core.common.items.FuelBlockItem;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class NeapolitanRegistryHelper extends RegistryHelper {

	public NeapolitanRegistryHelper(String modId) {
		super(modId);
	}

	public <B extends Block> RegistryObject<B> createFuelBlock(String name, Supplier<? extends B> supplier, int burnTime, @Nullable ItemGroup group) {
		RegistryObject<B> block = this.getDeferredBlockRegister().register(name, supplier);
		this.getDeferredItemRegister().register(name, () -> new FuelBlockItem(block.get(), burnTime, new Item.Properties().group(group)));
		return block;
	}
}
