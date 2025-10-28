package com.teamabnormals.neapolitan.core.other;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanDataMaps {
	public static final DataMapType<Item, ChillboxFuel> CHILLBOX_FUELS = DataMapType.builder(Neapolitan.location("chillbox_fuels"), Registries.ITEM, ChillboxFuel.CODEC).synced(ChillboxFuel.CHILL_TIME_CODEC, false).build();

	@SubscribeEvent
	public static void registerDataMaps(RegisterDataMapTypesEvent event) {
		event.register(CHILLBOX_FUELS);
	}

	public record ChillboxFuel(int chillTime) {
		public static final Codec<ChillboxFuel> CHILL_TIME_CODEC = ExtraCodecs.POSITIVE_INT.xmap(ChillboxFuel::new, ChillboxFuel::chillTime);
		public static final Codec<ChillboxFuel> CODEC = Codec.withAlternative(RecordCodecBuilder.create(in -> in.group(ExtraCodecs.POSITIVE_INT.fieldOf("chill_time").forGetter(ChillboxFuel::chillTime)).apply(in, ChillboxFuel::new)), CHILL_TIME_CODEC);
	}
}
