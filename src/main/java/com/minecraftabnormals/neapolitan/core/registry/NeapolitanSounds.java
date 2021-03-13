package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.SoundSubRegistryHelper;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanSounds {
	public static final SoundSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> HULLABALOO = HELPER.createSoundEvent("music.record.hullabaloo");

	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_ANGRY = HELPER.createSoundEvent("entity.chimpanzee.angry");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_DEATH = HELPER.createSoundEvent("entity.chimpanzee.death");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_EAT = HELPER.createSoundEvent("entity.chimpanzee.eat");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_HOWL = HELPER.createSoundEvent("entity.chimpanzee.howl");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_HURT = HELPER.createSoundEvent("entity.chimpanzee.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_AMBIENT = HELPER.createSoundEvent("entity.chimpanzee.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_STEP = HELPER.createSoundEvent("entity.chimpanzee.step");
	public static final RegistryObject<SoundEvent> ENTITY_CHIMPANZEE_SWING = HELPER.createSoundEvent("entity.chimpanzee.swing");

	public static final RegistryObject<SoundEvent> ITEM_ICE_CUBES_EAT = HELPER.createSoundEvent("item.ice_cubes.eat");
}
