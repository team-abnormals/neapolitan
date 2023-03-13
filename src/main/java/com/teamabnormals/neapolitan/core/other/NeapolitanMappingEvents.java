package com.teamabnormals.neapolitan.core.other;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.MissingMappingsEvent.Mapping;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanMappingEvents {

	//TODO: 1.20
	/*
	 * Remove event
	 * Combine fronds into one block using block states
	 * Remove custom item and lang entries
	 */
	@SubscribeEvent
	public static void itemRemapping(MissingMappingsEvent event) {
		List<Mapping<Item>> mappings = event.getMappings(ForgeRegistries.Keys.ITEMS, Neapolitan.MOD_ID);
		Map<ResourceLocation, Supplier<Item>> itemRemapping = (new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>())
				.put(location("small_banana_frond"), () -> NeapolitanBlocks.BANANA_FROND.get().asItem())
				.put(location("large_banana_frond"), () -> NeapolitanBlocks.BANANA_FROND.get().asItem())
				.build();

		for (Mapping<Item> mapping : mappings) {
			Supplier<Item> itemSupplier = itemRemapping.get(mapping.getKey());
			if (itemSupplier != null) {
				Item item = itemSupplier.get();
				if (item != null && ForgeRegistries.ITEMS.getKey(item) != null) {
					mapping.remap(item);
				}
			}
		}
	}

	public static ResourceLocation location(String name) {
		return new ResourceLocation(Neapolitan.MOD_ID, name);
	}
}