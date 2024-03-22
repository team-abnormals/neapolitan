package com.teamabnormals.neapolitan.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.neapolitan.client.model.BananaPeelModel;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeHeadModel;
import com.teamabnormals.neapolitan.client.model.ChimpanzeeModel;
import com.teamabnormals.neapolitan.client.renderer.entity.BananaPeelRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.BananarrowRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.ChimpanzeeRenderer;
import com.teamabnormals.neapolitan.client.renderer.entity.PlantainSpiderRenderer;
import com.teamabnormals.neapolitan.core.data.client.NeapolitanBlockStateProvider;
import com.teamabnormals.neapolitan.core.data.client.NeapolitanItemModelProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanDatapackBuiltinEntriesProvider;
import com.teamabnormals.neapolitan.core.data.server.NeapolitanLootTableProvider;
import com.teamabnormals.neapolitan.core.data.server.modifiers.NeapolitanAdvancementModifierProvider;
import com.teamabnormals.neapolitan.core.data.server.modifiers.NeapolitanLootModifierProvider;
import com.teamabnormals.neapolitan.core.data.server.tags.*;
import com.teamabnormals.neapolitan.core.other.NeapolitanClientCompat;
import com.teamabnormals.neapolitan.core.other.NeapolitanCompat;
import com.teamabnormals.neapolitan.core.other.NeapolitanModelLayers;
import com.teamabnormals.neapolitan.core.registry.*;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.NeapolitanSkullTypes;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.CompletableFuture;

@Mod(Neapolitan.MOD_ID)
@EventBusSubscriber(modid = Neapolitan.MOD_ID)
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
		NeapolitanPaintingVariants.PAINTING_VARIANTS.register(bus);
		NeapolitanBannerPatterns.BANNER_PATTERNS.register(bus);
		NeapolitanParticleTypes.PARTICLE_TYPES.register(bus);
		NeapolitanDecoratedPotPatterns.DECORATED_POT_PATTERNS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			NeapolitanBlocks.setupTabEditors();
			NeapolitanItems.setupTabEditors();
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
			bus.addListener(this::createSkullModels);
		});

		context.registerConfig(ModConfig.Type.COMMON, NeapolitanConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			NeapolitanCompat.registerCompat();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			SkullBlockRenderer.SKIN_BY_TYPE.put(NeapolitanSkullTypes.CHIMPANZEE, new ResourceLocation(Neapolitan.MOD_ID, "textures/entity/chimpanzee/jungle_chimpanzee.png"));
			NeapolitanClientCompat.registerClientCompat();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		NeapolitanBlockTagsProvider blockTags = new NeapolitanBlockTagsProvider(output, provider, helper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new NeapolitanItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(includeServer, new NeapolitanEntityTypeTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new NeapolitanBannerPatternTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new NeapolitanBiomeTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new NeapolitanMobEffectTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new NeapolitanPaintingVariantTagsProvider(output, provider, helper));
		generator.addProvider(includeServer, new NeapolitanLootTableProvider(output));
		generator.addProvider(includeServer, new NeapolitanLootModifierProvider(output, provider));
		generator.addProvider(includeServer, new NeapolitanAdvancementModifierProvider(output, provider));
		generator.addProvider(includeServer, new NeapolitanDatapackBuiltinEntriesProvider(output, provider));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new NeapolitanBlockStateProvider(output, helper));
		generator.addProvider(includeClient, new NeapolitanItemModelProvider(output, helper));
//		generator.addProvider(includeClient, new NeapolitanLanguageProvider(output));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(NeapolitanModelLayers.BANANA_PEEL, BananaPeelModel::createBodyLayer);
		event.registerLayerDefinition(NeapolitanModelLayers.CHIMPANZEE, () -> ChimpanzeeModel.createBodyLayer(0.0F, false, false));
		event.registerLayerDefinition(NeapolitanModelLayers.CHIMPANZEE_INNER_ARMOR, () -> ChimpanzeeModel.createBodyLayer(0.5F, true, true));
		event.registerLayerDefinition(NeapolitanModelLayers.CHIMPANZEE_OUTER_ARMOR, () -> ChimpanzeeModel.createBodyLayer(1.0F, true, false));
		event.registerLayerDefinition(NeapolitanModelLayers.CHIMPANZEE_HEAD, ChimpanzeeHeadModel::createHeadLayer);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(NeapolitanEntityTypes.CHIMPANZEE.get(), ChimpanzeeRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.PLANTAIN_SPIDER.get(), PlantainSpiderRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.BANANA_PEEL.get(), BananaPeelRenderer::new);
		event.registerEntityRenderer(NeapolitanEntityTypes.BANANARROW.get(), BananarrowRenderer::new);

		event.registerBlockEntityRenderer(NeapolitanBlockEntityTypes.SKULL.get(), SkullBlockRenderer::new);
	}

	@OnlyIn(Dist.CLIENT)
	private void createSkullModels(EntityRenderersEvent.CreateSkullModels event) {
		event.registerSkullModel(NeapolitanSkullTypes.CHIMPANZEE, new ChimpanzeeHeadModel(event.getEntityModelSet().bakeLayer(NeapolitanModelLayers.CHIMPANZEE_HEAD)));
	}
}
