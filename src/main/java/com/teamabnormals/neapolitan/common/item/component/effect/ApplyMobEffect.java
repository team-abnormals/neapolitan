package com.teamabnormals.neapolitan.common.item.component.effect;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.core.registry.NeapolitanIceCreamFlavorEffects;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.PossibleEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.ArrayList;
import java.util.Optional;

public record ApplyMobEffect(HolderSet<MobEffect> toApply, LevelBasedValue minDuration, LevelBasedValue maxDuration, LevelBasedValue minAmplifier, LevelBasedValue maxAmplifier) implements IceCreamFlavorEffect {
	public static final MapCodec<ApplyMobEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			RegistryCodecs.homogeneousList(Registries.MOB_EFFECT).fieldOf("to_apply").forGetter(ApplyMobEffect::toApply),
			LevelBasedValue.CODEC.fieldOf("min_duration").forGetter(ApplyMobEffect::minDuration),
			LevelBasedValue.CODEC.fieldOf("max_duration").forGetter(ApplyMobEffect::maxDuration),
			LevelBasedValue.CODEC.fieldOf("min_amplifier").forGetter(ApplyMobEffect::minAmplifier),
			LevelBasedValue.CODEC.fieldOf("max_amplifier").forGetter(ApplyMobEffect::maxAmplifier)
	).apply(instance, ApplyMobEffect::new));

	@Override
	public FoodProperties modifyFoodProperties(FoodProperties properties, int flavorLevel, ItemStack stack, Entity entity) {
		ArrayList<PossibleEffect> possibleEffects = Lists.newArrayList(properties.effects());
		if (entity instanceof LivingEntity living) {
			RandomSource random = living.getRandom();
			Optional<Holder<MobEffect>> optional = this.toApply.getRandomElement(random);
			if (optional.isPresent()) {
				int duration = Math.round(Mth.randomBetween(random, this.minDuration.calculate(flavorLevel), this.maxDuration.calculate(flavorLevel)) * 20.0F);
				int amplifier = Math.max(0, Math.round(Mth.randomBetween(random, this.minAmplifier.calculate(flavorLevel), this.maxAmplifier.calculate(flavorLevel))));
				possibleEffects.add(new PossibleEffect(() -> new MobEffectInstance(optional.get(), duration, amplifier), 1.0F));
			}
		}

		possibleEffects.sort((a, b) -> Boolean.compare(
				a.effect().is(NeapolitanMobEffects.VANILLA_SCENT),
				b.effect().is(NeapolitanMobEffects.VANILLA_SCENT)
		));

		return new FoodProperties(properties.nutrition(), properties.saturation(), properties.canAlwaysEat(), properties.eatSeconds(), properties.usingConvertsTo(), possibleEffects);
	}

	@Override
	public IceCreamFlavorEffectType getType() {
		return NeapolitanIceCreamFlavorEffects.APPLY_MOB_EFFECT.get();
	}
}