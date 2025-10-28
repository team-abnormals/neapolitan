package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.neapolitan.core.other.NeapolitanDataMaps;
import com.teamabnormals.neapolitan.core.other.NeapolitanDataMaps.ChillboxFuel;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
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
		this.builder(NeoForgeDataMaps.PARROT_IMITATIONS)
				.add(NeapolitanEntityTypes.PLANTAIN_SPIDER, new ParrotImitation(SoundEvents.PARROT_IMITATE_SPIDER), false);

		this.builder(NeapolitanDataMaps.CHILLBOX_FUELS)
				.add(NeapolitanItems.ICE_CUBES, new ChillboxFuel(150), false)
				.add(Items.ICE.builtInRegistryHolder(), new ChillboxFuel(1350), false)
				.add(Items.PACKED_ICE.builtInRegistryHolder(), new ChillboxFuel(12000), false)
				.add(Items.BLUE_ICE.builtInRegistryHolder(), new ChillboxFuel(100000), false);

		this.builder(NeoForgeDataMaps.FURNACE_FUELS)
				.add(NeapolitanBlocks.BANANA_FROND.getId(), new FurnaceFuel(100), false)
				.add(NeapolitanBlocks.BANANA_STALK.getId(), new FurnaceFuel(800), false)
				.add(NeapolitanBlocks.CARVED_BANANA_STALK.getId(), new FurnaceFuel(800), false)
				.add(NeapolitanBlocks.FROND_THATCH.getId(), new FurnaceFuel(100), false)
				.add(NeapolitanBlocks.FROND_THATCH_SLAB.getId(), new FurnaceFuel(50), false)
				.add(NeapolitanBlocks.FROND_THATCH_STAIRS.getId(), new FurnaceFuel(100), false);

		this.builder(NeoForgeDataMaps.COMPOSTABLES)
				.add(NeapolitanItems.STRAWBERRY_PIPS, new Compostable(0.3F), false)
				.add(NeapolitanItems.STRAWBERRIES, new Compostable(0.3F), false)
				.add(NeapolitanItems.WHITE_STRAWBERRIES, new Compostable(0.65F), false)
				.add(NeapolitanItems.STRAWBERRY_SCONES, new Compostable(0.65F), false)
				.add(NeapolitanItems.STRAWBERRY_CAKE, new Compostable(1.0F), false)

				.add(NeapolitanItems.CHOCOLATE_BAR, new Compostable(0.3F), false)
				.add(NeapolitanItems.CHOCOLATE_CAKE, new Compostable(1.0F), false)

				.add(NeapolitanItems.VANILLA_PODS, new Compostable(0.3F), false)
				.add(NeapolitanItems.DRIED_VANILLA_PODS, new Compostable(0.3F), false)
				.add(NeapolitanItems.VANILLA_CAKE, new Compostable(1.0F), false)

				.add(NeapolitanItems.BANANA, new Compostable(0.3F), false)
				.add(NeapolitanItems.BANANA_BUNCH, new Compostable(0.5F), false)
				.add(NeapolitanItems.DRIED_BANANA, new Compostable(0.5F), false)
				.add(NeapolitanItems.BANANA_BREAD, new Compostable(0.65F), false)
				.add(NeapolitanItems.BANANA_CAKE, new Compostable(1.0F), false)

				.add(NeapolitanItems.MINT_SPROUT, new Compostable(0.3F), false)
				.add(NeapolitanItems.MINT_LEAVES, new Compostable(0.5F), false)
				.add(NeapolitanItems.MINT_CAKE, new Compostable(1.0F), false)

				.add(NeapolitanItems.ADZUKI_BEANS, new Compostable(0.3F), false)
				.add(NeapolitanItems.ROASTED_ADZUKI_BEANS, new Compostable(0.5F), false)
				.add(NeapolitanItems.ADZUKI_BUN, new Compostable(0.85F), false)
				.add(NeapolitanItems.ADZUKI_CAKE, new Compostable(1.0F), false)

				.add(NeapolitanItems.VANILLA_CHOCOLATE_FINGERS, new Compostable(0.85F), false)
				.add(NeapolitanItems.CHOCOLATE_STRAWBERRIES, new Compostable(0.85F), false)
				.add(NeapolitanItems.MINT_CHOCOLATE, new Compostable(0.85F), false)

				.add(NeapolitanBlocks.VANILLA_POD_BLOCK.getId(), new Compostable(0.5F), false)
				.add(NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.getId(), new Compostable(0.5F), false)

				.add(NeapolitanBlocks.CHOCOLATE_BLOCK.getId(), new Compostable(1.0F), false)

				.add(NeapolitanBlocks.BANANA_BUNDLE.getId(), new Compostable(0.85F), false)
				.add(NeapolitanBlocks.BANANA_STALK.getId(), new Compostable(0.85F), false)
				.add(NeapolitanBlocks.CARVED_BANANA_STALK.getId(), new Compostable(0.85F), false)
				.add(NeapolitanBlocks.BANANA_FROND.getId(), new Compostable(0.3F), false)
				.add(NeapolitanBlocks.FROND_THATCH.getId(), new Compostable(0.65F), false)
				.add(NeapolitanBlocks.FROND_THATCH_SLAB.getId(), new Compostable(0.65F), false)
				.add(NeapolitanBlocks.FROND_THATCH_STAIRS.getId(), new Compostable(0.65F), false)

				.add(NeapolitanBlocks.BEANSTALK.getId(), new Compostable(0.65F), false)
				.add(NeapolitanBlocks.BEANSTALK_THORNS.getId(), new Compostable(0.5F), false)

				.add(NeapolitanBlocks.STRAWBERRY_BASKET.getId(), new Compostable(1.0F), false)
				.add(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.getId(), new Compostable(1.0F), false)
				.add(NeapolitanBlocks.BANANA_CRATE.getId(), new Compostable(1.0F), false)
				.add(NeapolitanBlocks.MINT_BASKET.getId(), new Compostable(1.0F), false)
				.add(NeapolitanBlocks.ADZUKI_CRATE.getId(), new Compostable(1.0F), false)
				.add(NeapolitanBlocks.ROASTED_ADZUKI_CRATE.getId(), new Compostable(1.0F), false)

				.add(NeapolitanBlocks.CHOCOLATE_BRICKS.getId(), new Compostable(0.85F), false)
				.add(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.getId(), new Compostable(0.85F), false)
				.add(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.getId(), new Compostable(0.3F), false)
				.add(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.getId(), new Compostable(0.85F), false)

				.add(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.getId(), new Compostable(0.85F), false)

				.add(NeapolitanBlocks.CHOCOLATE_TILES.getId(), new Compostable(0.85F), false)
				.add(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.getId(), new Compostable(0.85F), false)
				.add(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.getId(), new Compostable(0.3F), false)
				.add(NeapolitanBlocks.CHOCOLATE_TILE_WALL.getId(), new Compostable(0.85F), false);
	}
}