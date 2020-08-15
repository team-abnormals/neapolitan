package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.neapolitan.common.entity.goals.AvoidBlockGoal;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MODID)
public class NeapolitanEvents {
	
	@SubscribeEvent
	public static void entityJoinWorldEvent(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof CreeperEntity) {
			CreeperEntity creeper = (CreeperEntity)event.getEntity();
			creeper.goalSelector.addGoal(3, new AvoidBlockGoal<>(creeper, NeapolitanBlocks.STRAWBERRY_BUSH.get(), 6.0F, 1.0D, 1.2D));
		}
	}
}
