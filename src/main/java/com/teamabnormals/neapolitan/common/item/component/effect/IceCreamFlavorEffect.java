package com.teamabnormals.neapolitan.common.item.component.effect;

import com.mojang.serialization.Codec;
import com.teamabnormals.neapolitan.core.registry.NeapolitanIceCreamFlavorEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;


public interface IceCreamFlavorEffect {
	Codec<IceCreamFlavorEffect> CODEC = Codec.lazyInitialized(() -> NeapolitanIceCreamFlavorEffects.ICE_CREAM_FLAVOR_EFFECTS_REGISTRY
			.byNameCodec()
			.dispatch(IceCreamFlavorEffect::getType, IceCreamFlavorEffectType::codec));

	IceCreamFlavorEffectType getType();


	default FoodProperties modifyFoodProperties(FoodProperties properties, int flavorLevel, ItemStack stack, Entity entity) {
		return properties;
	}

	default void finishUsingItem(ServerLevel level, int flavorLevel, ItemStack stack, Entity entity) {

	}
}
