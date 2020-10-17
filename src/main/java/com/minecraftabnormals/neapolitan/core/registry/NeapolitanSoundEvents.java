package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanSoundEvents {
	public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;

	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_ANGRY 	= HELPER.createSoundEvent("entity.monkey.angry");
	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_DEATH 	= HELPER.createSoundEvent("entity.monkey.death");
	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_EAT 	= HELPER.createSoundEvent("entity.monkey.eat");
	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_HOWL 	= HELPER.createSoundEvent("entity.monkey.howl");
	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_HURT 	= HELPER.createSoundEvent("entity.monkey.hurt");
	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_AMBIENT= HELPER.createSoundEvent("entity.monkey.ambient");
	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_STEP 	= HELPER.createSoundEvent("entity.monkey.step");
	public static final RegistryObject<SoundEvent> ENTITY_MONKEY_SWING 	= HELPER.createSoundEvent("entity.monkey.swing");
	
	public static final RegistryObject<SoundEvent> ITEM_ICE_CUBES_EAT 	= HELPER.createSoundEvent("item.ice_cubes.eat");
}
