package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.DataUtil.CustomNoteBlockInstrument;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanSoundEvents {
	public static final SoundSubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> HULLABALOO = HELPER.createSoundEvent("music.record.hullabaloo");

	public static final RegistryObject<SoundEvent> NOTE_BLOCK_IMITATE_CHIMPANZEE = HELPER.createSoundEvent("block.note_block.imitate.chimpanzee");

	public static final RegistryObject<SoundEvent> CHIMPANZEE_SCREAM = HELPER.createSoundEvent("entity.chimpanzee.scream");
	public static final RegistryObject<SoundEvent> CHIMPANZEE_DEATH = HELPER.createSoundEvent("entity.chimpanzee.death");
	public static final RegistryObject<SoundEvent> CHIMPANZEE_HOWL = HELPER.createSoundEvent("entity.chimpanzee.howl");
	public static final RegistryObject<SoundEvent> CHIMPANZEE_HURT = HELPER.createSoundEvent("entity.chimpanzee.hurt");
	public static final RegistryObject<SoundEvent> CHIMPANZEE_AMBIENT = HELPER.createSoundEvent("entity.chimpanzee.ambient");
	public static final RegistryObject<SoundEvent> CHIMPANZEE_STEP = HELPER.createSoundEvent("entity.chimpanzee.step");
	public static final RegistryObject<SoundEvent> CHIMPANZEE_SWING = HELPER.createSoundEvent("entity.chimpanzee.swing");

	public static final RegistryObject<SoundEvent> ICE_CUBES_EAT = HELPER.createSoundEvent("item.ice_cubes.eat");
	public static final RegistryObject<SoundEvent> BANANA_BUNCH_OPEN = HELPER.createSoundEvent("item.banana_bunch.open");

	public static void registerNoteBlocks() {
		DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Neapolitan.MOD_ID, source -> source.getBlockState().is(NeapolitanBlocks.CHIMPANZEE_HEAD.get()), NOTE_BLOCK_IMITATE_CHIMPANZEE.get(), true));
	}
}
