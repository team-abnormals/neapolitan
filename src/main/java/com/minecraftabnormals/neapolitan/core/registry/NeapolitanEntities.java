package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.client.renderer.BananaPeelRenderer;
import com.minecraftabnormals.neapolitan.client.renderer.BananarrowRenderer;
import com.minecraftabnormals.neapolitan.client.renderer.MonkeyRenderer;
import com.minecraftabnormals.neapolitan.client.renderer.PlantainSpiderRenderer;
import com.minecraftabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.minecraftabnormals.neapolitan.common.entity.BananarrowEntity;
import com.minecraftabnormals.neapolitan.common.entity.MonkeyEntity;
import com.minecraftabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanEntities {
	public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;

	public static final RegistryObject<EntityType<BananarrowEntity>> 		BANANARROW 		= HELPER.createEntity("bananarrow", BananarrowEntity::new, BananarrowEntity::new, EntityClassification.MISC, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<BananaPeelEntity>> 		BANANA_PEEL 	= HELPER.createEntity("banana_peel", BananaPeelEntity::new, BananaPeelEntity::new, EntityClassification.MISC, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<MonkeyEntity>> 			MONKEY 			= HELPER.createLivingEntity("monkey", MonkeyEntity::new, EntityClassification.CREATURE, 0.6F, 1.6F);
	public static final RegistryObject<EntityType<PlantainSpiderEntity>> 	PLANTAIN_SPIDER = HELPER.createLivingEntity("plantain_spider", PlantainSpiderEntity::new, EntityClassification.MONSTER, 0.65F, 0.55F);

	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(NeapolitanEntities.BANANARROW.get(), BananarrowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(NeapolitanEntities.BANANA_PEEL.get(), BananaPeelRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(NeapolitanEntities.PLANTAIN_SPIDER.get(), PlantainSpiderRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(NeapolitanEntities.MONKEY.get(), MonkeyRenderer::new);
	}

	public static void registerEntityAttributes() {
		GlobalEntityTypeAttributes.put(PLANTAIN_SPIDER.get(), PlantainSpiderEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(MONKEY.get(), MonkeyEntity.registerAttributes().create());
	}

	public static void registerEntitySpawns() {
		EntitySpawnPlacementRegistry.register(PLANTAIN_SPIDER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PlantainSpiderEntity::canPlantainSpiderSpawn);
		ForgeRegistries.BIOMES.getValues().forEach(NeapolitanEntities::addSpawns);
	}

	private static void addSpawns(Biome biome) {
		if (biome.getCategory() == Biome.Category.JUNGLE || (biome.getRegistryName() != null && biome.getRegistryName().getPath().contains("rainforest"))) {
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(PLANTAIN_SPIDER.get(), 120, 3, 5));
		}
	}
}
