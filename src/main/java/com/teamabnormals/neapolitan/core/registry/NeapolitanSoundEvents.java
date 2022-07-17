package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanSoundEvents {
	public static final SoundSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> HULLABALOO = HELPER.createSoundEvent("music.record.hullabaloo");

	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_SCREAM = HELPER.createSoundEvent("entity.chimpanzee.scream");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_DEATH = HELPER.createSoundEvent("entity.chimpanzee.death");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_HOWL = HELPER.createSoundEvent("entity.chimpanzee.howl");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_HURT = HELPER.createSoundEvent("entity.chimpanzee.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_AMBIENT = HELPER.createSoundEvent("entity.chimpanzee.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_STEP = HELPER.createSoundEvent("entity.chimpanzee.step");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_SWING = HELPER.createSoundEvent("entity.chimpanzee.swing");

	public static final RegistryObject<SoundEvent> ITEM_ICE_CUBES_EAT = HELPER.createSoundEvent("item.ice_cubes.eat");
	public static final RegistryObject<SoundEvent> ITEM_BANANA_BUNCH_OPEN = HELPER.createSoundEvent("item.banana_bunch.open");
}
