package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.teamabnormals.neapolitan.common.entity.BananarrowEntity;
import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.PlantainSpiderEntity;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanEntityTypes {
	public static final EntitySubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<BananarrowEntity>> BANANARROW = HELPER.createEntity("bananarrow", BananarrowEntity::new, BananarrowEntity::new, MobCategory.MISC, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<BananaPeelEntity>> BANANA_PEEL = HELPER.createEntity("banana_peel", BananaPeelEntity::new, BananaPeelEntity::new, MobCategory.MISC, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<ChimpanzeeEntity>> CHIMPANZEE = HELPER.createLivingEntity("chimpanzee", ChimpanzeeEntity::new, MobCategory.CREATURE, 0.6F, 1.6F);
	public static final RegistryObject<EntityType<PlantainSpiderEntity>> PLANTAIN_SPIDER = HELPER.createLivingEntity("plantain_spider", PlantainSpiderEntity::new, MobCategory.MONSTER, 0.65F, 0.55F);

	public static void registerEntitySpawns() {
		SpawnPlacements.register(PLANTAIN_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PlantainSpiderEntity::canPlantainSpiderSpawn);
		SpawnPlacements.register(CHIMPANZEE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, ChimpanzeeEntity::canChimpanzeeSpawn);
	}

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(PLANTAIN_SPIDER.get(), PlantainSpiderEntity.registerAttributes().build());
		event.put(CHIMPANZEE.get(), ChimpanzeeEntity.registerAttributes().build());
	}
}
