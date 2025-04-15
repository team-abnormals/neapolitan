package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.monster.PlantainSpider;
import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.common.entity.projectile.Bananarrow;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanEntityTypes {
	public static final EntitySubRegistryHelper ENTITY_TYPES = Neapolitan.REGISTRY_HELPER.getEntitySubHelper();

	public static final DeferredHolder<EntityType<?>, EntityType<Bananarrow>> BANANARROW = ENTITY_TYPES.createEntity("bananarrow", Bananarrow::new, MobCategory.MISC, builder -> builder
			.sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20));
	public static final DeferredHolder<EntityType<?>, EntityType<BananaPeel>> BANANA_PEEL = ENTITY_TYPES.createEntity("banana_peel", BananaPeel::new, MobCategory.MISC, builder -> builder
			.sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20));
	public static final DeferredHolder<EntityType<?>, EntityType<Chimpanzee>> CHIMPANZEE = ENTITY_TYPES.createEntity("chimpanzee", Chimpanzee::new, MobCategory.CREATURE, builder -> builder
			.sized(0.6F, 1.65F).eyeHeight(1.55F).ridingOffset(-0.55F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<PlantainSpider>> PLANTAIN_SPIDER = ENTITY_TYPES.createEntity("plantain_spider", PlantainSpider::new, MobCategory.MONSTER, builder -> builder
			.sized(0.65F, 0.55F).eyeHeight(0.4F).clientTrackingRange(8));

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(PLANTAIN_SPIDER.get(), PlantainSpider.registerAttributes().build());
		event.put(CHIMPANZEE.get(), Chimpanzee.registerAttributes().build());
	}

	@SubscribeEvent
	public static void registerEntitySpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(PLANTAIN_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PlantainSpider::canPlantainSpiderSpawn, Operation.AND);
		event.register(CHIMPANZEE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Chimpanzee::canChimpanzeeSpawn, Operation.AND);
	}
}
