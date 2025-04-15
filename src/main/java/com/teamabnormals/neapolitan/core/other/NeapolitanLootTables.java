package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class NeapolitanLootTables {
	public static final ResourceKey<LootTable> BANANA_PLANT_ARCHAEOLOGY_COMMON = create("archaeology/banana_plant_common");
	public static final ResourceKey<LootTable> BANANA_PLANT_ARCHAEOLOGY_RARE = create("archaeology/banana_plant_rare");

	private static ResourceKey<LootTable> create(String name) {
		return ResourceKey.create(Registries.LOOT_TABLE, Neapolitan.location(name));
	}
}
