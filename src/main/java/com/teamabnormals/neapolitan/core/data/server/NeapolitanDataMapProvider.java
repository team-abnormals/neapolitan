package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.ParrotImitation;

import java.util.concurrent.CompletableFuture;

public class NeapolitanDataMapProvider extends DataMapProvider {

	public NeapolitanDataMapProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void gather(Provider provider) {
		this.builder(NeoForgeDataMaps.FURNACE_FUELS)
				.add(NeapolitanItems.BANANA_FROND, new FurnaceFuel(100), false)
				.add(NeapolitanBlocks.BANANA_STALK.getId(), new FurnaceFuel(800), false)
				.add(NeapolitanBlocks.CARVED_BANANA_STALK.getId(), new FurnaceFuel(800), false)
				.add(NeapolitanBlocks.FROND_THATCH.getId(), new FurnaceFuel(100), false)
				.add(NeapolitanBlocks.FROND_THATCH_SLAB.getId(), new FurnaceFuel(50), false)
				.add(NeapolitanBlocks.FROND_THATCH_STAIRS.getId(), new FurnaceFuel(100), false);

		// TODO: Move over from NeapolitanCompat
		this.builder(NeoForgeDataMaps.COMPOSTABLES);

		this.builder(NeoForgeDataMaps.PARROT_IMITATIONS)
				.add(NeapolitanEntityTypes.PLANTAIN_SPIDER, new ParrotImitation(SoundEvents.PARROT_IMITATE_SPIDER), false);
	}
}