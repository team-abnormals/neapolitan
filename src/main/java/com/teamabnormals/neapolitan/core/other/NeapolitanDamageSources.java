package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class NeapolitanDamageSources {
	public static final ResourceKey<DamageType> BEANSTALK_THORNS = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Neapolitan.MOD_ID, "beanstalk_thorns"));

	public static void bootstrap(BootstapContext<DamageType> context) {
		context.register(BEANSTALK_THORNS, new DamageType(Neapolitan.MOD_ID + ".beanstalkThorns", 0.1F));
	}

	public static DamageSource beanstalkThorns(Level level) {
		return level.damageSources().source(BEANSTALK_THORNS);
	}
}
