package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import com.teamabnormals.neapolitan.common.effect.BerserkingMobEffect;
import com.teamabnormals.neapolitan.common.effect.SlippingMobEffect;
import com.teamabnormals.neapolitan.common.effect.SugarRushMobEffect;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NeapolitanMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Neapolitan.MOD_ID);

	public static final RegistryObject<MobEffect> SUGAR_RUSH = MOB_EFFECTS.register("sugar_rush", SugarRushMobEffect::new);
	public static final RegistryObject<MobEffect> VANILLA_SCENT = MOB_EFFECTS.register("vanilla_scent", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 15913066));
	public static final RegistryObject<MobEffect> AGILITY = MOB_EFFECTS.register("agility", () -> new BlueprintMobEffect(MobEffectCategory.NEUTRAL, 0xA06951));
	public static final RegistryObject<MobEffect> SLIPPING = MOB_EFFECTS.register("slipping", SlippingMobEffect::new);
	public static final RegistryObject<MobEffect> BERSERKING = MOB_EFFECTS.register("berserking", BerserkingMobEffect::new);
	public static final RegistryObject<MobEffect> HARMONY = MOB_EFFECTS.register("harmony", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 0xCA2F3E));

}
