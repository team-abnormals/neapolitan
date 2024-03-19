package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.monster.PlantainSpider;
import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.common.entity.projectile.Bananarrow;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanEntityTypes {
	public static final EntitySubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<Bananarrow>> BANANARROW = HELPER.createEntity("bananarrow", Bananarrow::new, Bananarrow::new, MobCategory.MISC, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<BananaPeel>> BANANA_PEEL = HELPER.createEntity("banana_peel", BananaPeel::new, BananaPeel::new, MobCategory.MISC, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<Chimpanzee>> CHIMPANZEE = HELPER.createLivingEntity("chimpanzee", Chimpanzee::new, MobCategory.CREATURE, 0.6F, 1.6F);
	public static final RegistryObject<EntityType<PlantainSpider>> PLANTAIN_SPIDER = HELPER.createLivingEntity("plantain_spider", PlantainSpider::new, MobCategory.MONSTER, 0.65F, 0.55F);

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(PLANTAIN_SPIDER.get(), PlantainSpider.registerAttributes().build());
		event.put(CHIMPANZEE.get(), Chimpanzee.registerAttributes().build());
	}

	@SubscribeEvent
	public static void registerEntityAttributes(SpawnPlacementRegisterEvent event) {
		event.register(PLANTAIN_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PlantainSpider::canPlantainSpiderSpawn, Operation.AND);
		event.register(CHIMPANZEE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Chimpanzee::canChimpanzeeSpawn, Operation.AND);
	}
}
