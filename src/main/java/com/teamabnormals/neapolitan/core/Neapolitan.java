package com.teamabnormals.neapolitan.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.gallery.core.data.client.GalleryItemModelProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanDataMapProvider;
import com.teamabnormals.neapolitan.core.data.client.NeapolitanBlockStateProvider;
import com.teamabnormals.neapolitan.core.data.client.NeapolitanItemModelProvider;
import com.teamabnormals.neapolitan.core.data.client.NeapolitanSpriteSourceProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanDatapackProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanLootTableProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanRecipeProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanAdvancementModifierProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanDataRemolderProvider;
import com.teamabnormals.neapolitan.core.data.server.tags.*;
import com.teamabnormals.neapolitan.core.other.NeapolitanClientCompat;
import com.teamabnormals.neapolitan.core.other.NeapolitanCompat;
import com.teamabnormals.neapolitan.core.registry.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(Neapolitan.MOD_ID)
public class Neapolitan {
	public static final String MOD_ID = "neapolitan";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public Neapolitan(IEventBus bus, ModContainer container) {
		NeoForgeMod.enableMilkFluid();

		NeapolitanBlocks.BLOCKS.register(bus);
		NeapolitanItems.ITEMS.register(bus);
		NeapolitanEntityTypes.ENTITY_TYPES.register(bus);
		NeapolitanBlockEntityTypes.BLOCK_ENTITY_TYPES.register(bus);
		NeapolitanSoundEvents.SOUND_EVENTS.register(bus);
		NeapolitanMobEffects.MOB_EFFECTS.register(bus);
		NeapolitanFeatures.FEATURES.register(bus);
		NeapolitanPoiTypes.POI_TYPES.register(bus);
		NeapolitanParticleTypes.PARTICLE_TYPES.register(bus);
		NeapolitanDecoratedPotPatterns.DECORATED_POT_PATTERNS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		container.registerConfig(ModConfig.Type.COMMON, NeapolitanConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(NeapolitanCompat::registerCompat);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(NeapolitanClientCompat::register);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();
		NeapolitanDatapackProvider datapackEntries = new NeapolitanDatapackProvider(output, provider);
		generator.addProvider(server, datapackEntries);
		provider = datapackEntries.getRegistryProvider();

		NeapolitanBlockTagsProvider blockTags = new NeapolitanBlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new NeapolitanItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new NeapolitanEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(server, new NeapolitanBannerPatternTagsProvider(output, provider, helper));
		generator.addProvider(server, new NeapolitanBiomeTagsProvider(output, provider, helper));
		generator.addProvider(server, new NeapolitanMobEffectTagsProvider(output, provider, helper));
		generator.addProvider(server, new NeapolitanPaintingVariantTagsProvider(output, provider, helper));
		generator.addProvider(server, new NeapolitanEnchantmentTagsProvider(output, provider, helper));
		generator.addProvider(server, new NeapolitanLootTableProvider(output, provider));
		generator.addProvider(server, new NeapolitanDataRemolderProvider(output, provider));
		generator.addProvider(server, new NeapolitanAdvancementModifierProvider(output, provider));
		generator.addProvider(server, new NeapolitanRecipeProvider(output, provider));
		generator.addProvider(server, new NeapolitanDataMapProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new NeapolitanBlockStateProvider(output, helper));
		generator.addProvider(client, new NeapolitanItemModelProvider(output, helper));
		generator.addProvider(client, new NeapolitanSpriteSourceProvider(output, provider, helper));
//		generator.addProvider(includeClient, new NeapolitanLanguageProvider(output));

		generator.addProvider(client, new GalleryItemModelProvider(MOD_ID, output, helper, provider));
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
