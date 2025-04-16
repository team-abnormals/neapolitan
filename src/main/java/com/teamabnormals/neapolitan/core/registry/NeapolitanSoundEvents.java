package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.DataUtil.CustomNoteBlockInstrument;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NeapolitanSoundEvents {
	public static final SoundSubRegistryHelper SOUND_EVENTS = Neapolitan.REGISTRY_HELPER.getSoundSubHelper();

	public static final DeferredHolder<SoundEvent, SoundEvent> HULLABALOO = SOUND_EVENTS.createSoundEvent("music.record.hullabaloo");
	
	public static final DeferredHolder<SoundEvent, SoundEvent> BANANA_STALK_BREAK = SOUND_EVENTS.createSoundEvent("block.banana_stalk.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> BANANA_STALK_FALL = SOUND_EVENTS.createSoundEvent("block.banana_stalk.fall");
	public static final DeferredHolder<SoundEvent, SoundEvent> BANANA_STALK_HIT = SOUND_EVENTS.createSoundEvent("block.banana_stalk.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> BANANA_STALK_PLACE = SOUND_EVENTS.createSoundEvent("block.banana_stalk.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> BANANA_STALK_STEP = SOUND_EVENTS.createSoundEvent("block.banana_stalk.step");
	
	public static final DeferredHolder<SoundEvent, SoundEvent> NOTE_BLOCK_IMITATE_CHIMPANZEE = SOUND_EVENTS.createSoundEvent("block.note_block.imitate.chimpanzee");

	public static final DeferredHolder<SoundEvent, SoundEvent> CHIMPANZEE_SCREAM = SOUND_EVENTS.createSoundEvent("entity.chimpanzee.scream");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHIMPANZEE_DEATH = SOUND_EVENTS.createSoundEvent("entity.chimpanzee.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHIMPANZEE_HOWL = SOUND_EVENTS.createSoundEvent("entity.chimpanzee.howl");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHIMPANZEE_HURT = SOUND_EVENTS.createSoundEvent("entity.chimpanzee.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHIMPANZEE_AMBIENT = SOUND_EVENTS.createSoundEvent("entity.chimpanzee.ambient");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHIMPANZEE_STEP = SOUND_EVENTS.createSoundEvent("entity.chimpanzee.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHIMPANZEE_SWING = SOUND_EVENTS.createSoundEvent("entity.chimpanzee.swing");

	public static final DeferredHolder<SoundEvent, SoundEvent> ICE_CUBES_EAT = SOUND_EVENTS.createSoundEvent("item.ice_cubes.eat");
	public static final DeferredHolder<SoundEvent, SoundEvent> ICE_CREAM_EAT = SOUND_EVENTS.createSoundEvent("item.ice_cream.eat");
	public static final DeferredHolder<SoundEvent, SoundEvent> BANANA_BUNCH_OPEN = SOUND_EVENTS.createSoundEvent("item.banana_bunch.open");

	public static void registerNoteBlocks() {
		DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Neapolitan.MOD_ID, source -> source.state().is(NeapolitanBlocks.CHIMPANZEE_HEAD.get()), NOTE_BLOCK_IMITATE_CHIMPANZEE.get(), true));
	}

	public static class NeapolitanSoundTypes {
		public static final DeferredSoundType BANANA_STALK = new DeferredSoundType(1.0F, 1.0F, BANANA_STALK_BREAK, BANANA_STALK_STEP, BANANA_STALK_PLACE, BANANA_STALK_HIT, BANANA_STALK_FALL);
	}
}
