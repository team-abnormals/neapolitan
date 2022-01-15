package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import com.teamabnormals.neapolitan.common.potion.BerserkingEffect;
import com.teamabnormals.neapolitan.common.potion.SlippingEffect;
import com.teamabnormals.neapolitan.common.potion.SugarRushEffect;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NeapolitanEffects {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Neapolitan.MOD_ID);

	public static final RegistryObject<MobEffect> SUGAR_RUSH = EFFECTS.register("sugar_rush", SugarRushEffect::new);
	public static final RegistryObject<MobEffect> VANILLA_SCENT = EFFECTS.register("vanilla_scent", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 15913066));
	public static final RegistryObject<MobEffect> AGILITY = EFFECTS.register("agility", () -> new BlueprintMobEffect(MobEffectCategory.NEUTRAL, 0xA06951));
	public static final RegistryObject<MobEffect> SLIPPING = EFFECTS.register("slipping", SlippingEffect::new);
	public static final RegistryObject<MobEffect> BERSERKING = EFFECTS.register("berserking", BerserkingEffect::new);
	public static final RegistryObject<MobEffect> HARMONY = EFFECTS.register("harmony", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 0xCA2F3E));

}
