package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.client.particle.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.SplashParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Neapolitan.MOD_ID);

	public static final RegistryObject<SimpleParticleType> MINT_BOOST = PARTICLE_TYPES.register("mint_boost", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> CHIMPANZEE_NEEDS_FOOD = PARTICLE_TYPES.register("chimpanzee_needs_food", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> CHIMPANZEE_NEEDS_FRIEND = PARTICLE_TYPES.register("chimpanzee_needs_friend", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> CHIMPANZEE_NEEDS_SUN = PARTICLE_TYPES.register("chimpanzee_needs_sun", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> FLY = PARTICLE_TYPES.register("fly", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> TEAR = PARTICLE_TYPES.register("tear", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> DRIPPING_DRIPSTONE_MILK = PARTICLE_TYPES.register("dripping_dripstone_milk", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FALLING_DRIPSTONE_MILK = PARTICLE_TYPES.register("falling_dripstone_milk", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> MILK_SPLASH = PARTICLE_TYPES.register("milk_splash", () -> new SimpleParticleType(false));

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
		ParticleEngine manager = Minecraft.getInstance().particleEngine;
		manager.register(MINT_BOOST.get(), MintBoostParticle.Provider::new);
		manager.register(CHIMPANZEE_NEEDS_FOOD.get(), ChimpanzeeNeedParticle.Provider::new);
		manager.register(CHIMPANZEE_NEEDS_FRIEND.get(), ChimpanzeeNeedParticle.Provider::new);
		manager.register(CHIMPANZEE_NEEDS_SUN.get(), ChimpanzeeNeedParticle.Provider::new);
		manager.register(FLY.get(), FlyParticle.Provider::new);
		manager.register(TEAR.get(), TearParticle.Provider::new);
		manager.register(DRIPPING_DRIPSTONE_MILK.get(), MilkDripParticle.DripstoneWaterHangProvider::new);
		manager.register(FALLING_DRIPSTONE_MILK.get(), MilkDripParticle.DripstoneWaterFallProvider::new);
		manager.register(MILK_SPLASH.get(), SplashParticle.Provider::new);
	}
}
