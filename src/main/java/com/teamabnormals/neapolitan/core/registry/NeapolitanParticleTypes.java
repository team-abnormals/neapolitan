package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.client.particle.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.particle.SplashParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NeapolitanParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, Neapolitan.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MINT_BOOST = PARTICLE_TYPES.register("mint_boost", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CHIMPANZEE_NEEDS_FOOD = PARTICLE_TYPES.register("chimpanzee_needs_food", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CHIMPANZEE_NEEDS_FRIEND = PARTICLE_TYPES.register("chimpanzee_needs_friend", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CHIMPANZEE_NEEDS_SUN = PARTICLE_TYPES.register("chimpanzee_needs_sun", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FLY = PARTICLE_TYPES.register("fly", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TEAR = PARTICLE_TYPES.register("tear", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_DRIPSTONE_MILK = PARTICLE_TYPES.register("dripping_dripstone_milk", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_DRIPSTONE_MILK = PARTICLE_TYPES.register("falling_dripstone_milk", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MILK_SPLASH = PARTICLE_TYPES.register("milk_splash", () -> new SimpleParticleType(false));

	@SubscribeEvent
	public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(MINT_BOOST.get(), MintBoostParticle.Provider::new);
		event.registerSpriteSet(CHIMPANZEE_NEEDS_FOOD.get(), ChimpanzeeNeedParticle.Provider::new);
		event.registerSpriteSet(CHIMPANZEE_NEEDS_FRIEND.get(), ChimpanzeeNeedParticle.Provider::new);
		event.registerSpriteSet(CHIMPANZEE_NEEDS_SUN.get(), ChimpanzeeNeedParticle.Provider::new);
		event.registerSpriteSet(FLY.get(), FlyParticle.Provider::new);
		event.registerSpriteSet(TEAR.get(), TearParticle.Provider::new);
		event.registerSpriteSet(DRIPPING_DRIPSTONE_MILK.get(), MilkDripParticle.DripstoneWaterHangProvider::new);
		event.registerSpriteSet(FALLING_DRIPSTONE_MILK.get(), MilkDripParticle.DripstoneWaterFallProvider::new);
		event.registerSpriteSet(MILK_SPLASH.get(), SplashParticle.Provider::new);
	}
}
