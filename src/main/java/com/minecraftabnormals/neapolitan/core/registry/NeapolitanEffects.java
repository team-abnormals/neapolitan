package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.abnormals_core.common.potion.AbnormalsEffect;
import com.minecraftabnormals.neapolitan.common.potion.BerserkingEffect;
import com.minecraftabnormals.neapolitan.common.potion.SlippingEffect;
import com.minecraftabnormals.neapolitan.common.potion.SugarRushEffect;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NeapolitanEffects {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Neapolitan.MOD_ID);

	public static final RegistryObject<Effect> SUGAR_RUSH = EFFECTS.register("sugar_rush", SugarRushEffect::new);
	public static final RegistryObject<Effect> VANILLA_SCENT = EFFECTS.register("vanilla_scent", () -> new AbnormalsEffect(EffectType.BENEFICIAL, 15913066));
	public static final RegistryObject<Effect> AGILITY = EFFECTS.register("agility", () -> new AbnormalsEffect(EffectType.NEUTRAL, 0xA06951));
	public static final RegistryObject<Effect> SLIPPING = EFFECTS.register("slipping", SlippingEffect::new);
	public static final RegistryObject<Effect> BERSERKING = EFFECTS.register("berserking", BerserkingEffect::new);
	public static final RegistryObject<Effect> HARMONY = EFFECTS.register("harmony", () -> new AbnormalsEffect(EffectType.BENEFICIAL, 0xCA2F3E));

}
