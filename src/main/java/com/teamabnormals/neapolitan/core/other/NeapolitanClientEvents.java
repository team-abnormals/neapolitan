package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.PossibleEffect;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, value = Dist.CLIENT)
public class NeapolitanClientEvents {

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		FoodProperties effects = event.getItemStack().getFoodProperties(event.getEntity());
		if (effects != null) {
			List<Component> tooltip = event.getToolTip();
			for (PossibleEffect effect : effects.effects()) {
				MobEffectInstance effectInstance = effect.effect();
				MutableComponent effectText = Component.translatable(effectInstance.getDescriptionId());
				Player player = event.getEntity();
				if (effectInstance.getAmplifier() > 0) {
					effectText = Component.translatable("potion.withAmplifier", effectText, Component.translatable("potion.potency." + effectInstance.getAmplifier()));
				}
				if (!effectInstance.endsWithin(20)) {
					effectText = Component.translatable("potion.withDuration", effectText, MobEffectUtil.formatDuration(effectInstance, 1.0F, player == null ? 20 : player.level().tickRateManager().tickrate()));
				}
				tooltip.add(effectText.withStyle(effectInstance.getEffect().value().getCategory().getTooltipFormatting()));
			}
		}
	}
}