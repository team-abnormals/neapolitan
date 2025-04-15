package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import com.teamabnormals.neapolitan.common.effect.SlippingMobEffect;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeapolitanMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Neapolitan.MOD_ID);

	public static final DeferredHolder<MobEffect, MobEffect> VANILLA_SCENT = MOB_EFFECTS.register("vanilla_scent", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 15913066));
	public static final DeferredHolder<MobEffect, MobEffect> SUGAR_RUSH = MOB_EFFECTS.register("sugar_rush", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 6739711)
			.addAttributeModifier(Attributes.MOVEMENT_SPEED, Neapolitan.location("effect.sugar_rush.speed"), 0.6F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
			.addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, Neapolitan.location("effect.sugar_rush.block_break_speed"), 0.3F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
	public static final DeferredHolder<MobEffect, MobEffect> SUGAR_CRASH = MOB_EFFECTS.register("sugar_crash", () -> new BlueprintMobEffect(MobEffectCategory.HARMFUL, 6739711)
			.addAttributeModifier(Attributes.MOVEMENT_SPEED, Neapolitan.location("effect.sugar_crash.speed"), -0.3F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
			.addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, Neapolitan.location("effect.sugar_crash.block_break_speed"), -0.3F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

	public static final DeferredHolder<MobEffect, MobEffect> AGILITY = MOB_EFFECTS.register("agility", () -> new BlueprintMobEffect(MobEffectCategory.NEUTRAL, 0xA06951));
	public static final DeferredHolder<MobEffect, MobEffect> SLIPPING = MOB_EFFECTS.register("slipping", SlippingMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> BERSERKING = MOB_EFFECTS.register("berserking", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 0x8DF4AE)
			.addAttributeModifier(Attributes.ARMOR, Neapolitan.location("effect.berserking.armor"), 1.0F, Operation.ADD_VALUE)
			.addAttributeModifier(Attributes.ATTACK_DAMAGE, Neapolitan.location("effect.berserking.attack_damage"), 0.5F, Operation.ADD_VALUE));
	public static final DeferredHolder<MobEffect, MobEffect> HARMONY = MOB_EFFECTS.register("harmony", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 0xCA2F3E));
}
