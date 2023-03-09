package com.teamabnormals.neapolitan.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.neapolitan.client.model.BananaPeelModel;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.client.renderer.entity.BananaPeelRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.BananarrowRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.ChimpanzeeRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.PlantainSpiderRenderer;
import com.teamabnormals.neapolitan.core.data.client.NeapolitanBlockStateProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanLootTableProvider;
import com.teamabnormals.neapolitan.core.data.server.modifiers.NeapolitanAdvancementModifierProvider;
import com.teamabnormals.neapolitan.core.data.server.modifiers.NeapolitanBiomeModifierProvider;
import com.teamabnormals.neapolitan.core.data.server.modifiers.NeapolitanLootModifierProvider;
import com.teamabnormals.neapolitan.core.data.server.tags.*;
import com.teamabnormals.neapolitan.core.other.NeapolitanCompat;
import com.teamabnormals.neapolitan.core.other.NeapolitanModelLayers;
import com.teamabnormals.neapolitan.core.registry.*;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanConfiguredFeatures;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanPlacedFeatures;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Neapolitan.MOD_ID)
@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class Neapolitan {
	public static final String MOD_ID = "neapolitan";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public Neapolitan() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();
		MinecraftForge.EVENT_BUS.register(this);

		ForgeMod.enableMilkFluid();

		REGISTRY_HELPER.register(bus);
		NeapolitanMobEffects.MOB_EFFECTS.register(bus);
		NeapolitanFeatures.FEATURES.register(bus);
		NeapolitanConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
		NeapolitanPlacedFeatures.PLACED_FEATURES.register(bus);
		NeapolitanPaintingVariants.PAINTING_VARIANTS.register(bus);
		NeapolitanBannerPatterns.BANNER_PATTERNS.register(bus);
		NeapolitanParticleTypes.PARTICLE_TYPES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});

		context.registerConfig(ModConfig.Type.COMMON, NeapolitanConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			NeapolitanCompat.transformCookies();
			NeapolitanCompat.registerCompat();
			NeapolitanEntityTypes.registerEntitySpawns();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(NeapolitanCompat::registerRenderLayers);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		NeapolitanBlockTagsProvider blockTagsProvider = new NeapolitanBlockTagsProvider(generator, helper);
		generator.addProvider(includeServer, blockTagsProvider);
		generator.addProvider(includeServer, new NeapolitanItemTagsProvider(generator, blockTagsProvider, helper));
		generator.addProvider(includeServer, new NeapolitanEntityTypeTagsProvider(generator, helper));
		generator.addProvider(includeServer, new NeapolitanBannerPatternTagsProvider(generator, helper));
		generator.addProvider(includeServer, new NeapolitanBiomeTagsProvider(generator, helper));
		generator.addProvider(includeServer, new NeapolitanMobEffectTagsProvider(generator, helper));
		generator.addProvider(includeServer, new NeapolitanPaintingVariantTagsProvider(generator, helper));
		generator.addProvider(includeServer, new NeapolitanLootTableProvider(generator));
		generator.addProvider(includeServer, new NeapolitanLootModifierProvider(generator));
		generator.addProvider(includeServer, new NeapolitanAdvancementModifierProvider(generator));
		generator.addProvider(includeServer, NeapolitanBiomeModifierProvider.create(generator, helper));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new NeapolitanBlockStateProvider(generator, helper));
		//generator.addProvider(new NeapolitanLanguageProvider(generator));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(NeapolitanModelLayers.BANANA_PEEL, BananaPeelModel::createBodyLayer);
		event.registerLayerDefinition(NeapolitanModelLayers.CHIMPANZEE, () -> ChimpanzeeModel.createBodyLayer(0.0F, false, false));
		event.registerLayerDefinition(NeapolitanModelLayers.CHIMPANZEE_INNER_ARMOR, () -> ChimpanzeeModel.createBodyLayer(0.5F, true, true));
		event.registerLayerDefinition(NeapolitanModelLayers.CHIMPANZEE_OUTER_ARMOR, () -> ChimpanzeeModel.createBodyLayer(1.0F, true, false));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(NeapolitanEntityTypes.CHIMPANZEE.get(), ChimpanzeeRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.PLANTAIN_SPIDER.get(), PlantainSpiderRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.BANANA_PEEL.get(), BananaPeelRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.BANANARROW.get(), BananarrowRenderer::new);
	}
}
