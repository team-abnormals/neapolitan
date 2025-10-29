package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.common.item.component.effect.AddNutrition;
import com.teamabnormals.neapolitan.common.item.component.effect.ApplyMobEffect;
import com.teamabnormals.neapolitan.common.item.component.effect.HealEntity;
import com.teamabnormals.neapolitan.common.item.component.effect.IceCreamFlavorEffectType;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeapolitanIceCreamFlavorEffects {
	public static final ResourceKey<Registry<IceCreamFlavorEffectType>> ICE_CREAM_FLAVOR_EFFECTS_KEY = ResourceKey.createRegistryKey(Neapolitan.location("ice_cream_flavor_effects"));
	public static final DeferredRegister<IceCreamFlavorEffectType> ICE_CREAM_FLAVOR_EFFECTS = DeferredRegister.create(ICE_CREAM_FLAVOR_EFFECTS_KEY, Neapolitan.MOD_ID);

	public static final Registry<IceCreamFlavorEffectType> ICE_CREAM_FLAVOR_EFFECTS_REGISTRY = ICE_CREAM_FLAVOR_EFFECTS.makeRegistry(builder -> builder.defaultKey(Neapolitan.location("heal_entity")));

	public static final DeferredHolder<IceCreamFlavorEffectType, IceCreamFlavorEffectType> HEAL_ENTITY = ICE_CREAM_FLAVOR_EFFECTS.register("heal_entity", () -> new IceCreamFlavorEffectType(HealEntity.CODEC));
	public static final DeferredHolder<IceCreamFlavorEffectType, IceCreamFlavorEffectType> ADD_NUTRITION = ICE_CREAM_FLAVOR_EFFECTS.register("add_nutrition", () -> new IceCreamFlavorEffectType(AddNutrition.CODEC));
	public static final DeferredHolder<IceCreamFlavorEffectType, IceCreamFlavorEffectType> APPLY_MOB_EFFECT = ICE_CREAM_FLAVOR_EFFECTS.register("apply_mob_effect", () -> new IceCreamFlavorEffectType(ApplyMobEffect.CODEC));
}