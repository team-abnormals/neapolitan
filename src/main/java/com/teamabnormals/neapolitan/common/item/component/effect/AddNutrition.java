package com.teamabnormals.neapolitan.common.item.component.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.core.registry.NeapolitanIceCreamFlavorEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.LevelBasedValue;

public record AddNutrition(LevelBasedValue minNutrition, LevelBasedValue maxNutrition) implements IceCreamFlavorEffect {
	public static final MapCodec<AddNutrition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("min_nutrition").forGetter(AddNutrition::minNutrition),
			LevelBasedValue.CODEC.fieldOf("max_nutrition").forGetter(AddNutrition::maxNutrition)
	).apply(instance, AddNutrition::new));

	@Override
	public FoodProperties modifyFoodProperties(FoodProperties properties, int flavorLevel, ItemStack stack, Entity entity) {
		int nutrition = properties.nutrition() + Math.round(Mth.randomBetween(entity.getRandom(), this.minNutrition.calculate(flavorLevel), this.maxNutrition.calculate(flavorLevel)));
		return new FoodProperties(nutrition, properties.saturation(), properties.canAlwaysEat(), properties.eatSeconds(), properties.usingConvertsTo(), properties.effects());
	}

	@Override
	public IceCreamFlavorEffectType getType() {
		return NeapolitanIceCreamFlavorEffects.ADD_NUTRITION.get();
	}
}