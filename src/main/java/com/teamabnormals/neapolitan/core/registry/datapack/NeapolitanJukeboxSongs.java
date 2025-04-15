package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NeapolitanJukeboxSongs {
	public static final ResourceKey<JukeboxSong> HULLABALOO = create("hullabaloo");

	public static void bootstrap(BootstrapContext<JukeboxSong> context) {
		register(context, HULLABALOO, NeapolitanSoundEvents.HULLABALOO, 132, 3);
	}

	private static ResourceKey<JukeboxSong> create(String name) {
		return ResourceKey.create(Registries.JUKEBOX_SONG, Neapolitan.location(name));
	}

	private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, DeferredHolder<SoundEvent, SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
		context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float) lengthInSeconds, comparatorOutput));
	}
}