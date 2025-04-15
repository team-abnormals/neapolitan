package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.api.BlueprintRabbitVariants;
import com.teamabnormals.blueprint.core.events.LoadThisClassEvent;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanRabbitVariants extends BlueprintRabbitVariants {
	private static final int UNIQUE_OFFSET = 3421;

	public static final BlueprintRabbitVariant STRAWBERRY = BlueprintRabbitVariants.register(UNIQUE_OFFSET, Neapolitan.location("strawberry"), context -> getBiome(context).is(NeapolitanBiomeTags.SPAWNS_STRAWBERRY_RABBITS));

	@SubscribeEvent
	public static void $(LoadThisClassEvent event) {
	}
}