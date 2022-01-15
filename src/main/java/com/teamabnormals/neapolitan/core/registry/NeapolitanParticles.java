package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.client.particle.ChimpanzeeNeedParticle;
import com.teamabnormals.neapolitan.client.particle.FlyParticle;
import com.teamabnormals.neapolitan.client.particle.MilkDripParticle;
import com.teamabnormals.neapolitan.client.particle.MintBoostParticle;
import com.teamabnormals.neapolitan.client.particle.TearParticle;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.SplashParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Neapolitan.MOD_ID);

	public static final RegistryObject<SimpleParticleType> MINT_BOOST = PARTICLES.register("mint_boost", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> CHIMPANZEE_NEEDS_FOOD = PARTICLES.register("chimpanzee_needs_food", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> CHIMPANZEE_NEEDS_FRIEND = PARTICLES.register("chimpanzee_needs_friend", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> CHIMPANZEE_NEEDS_SUN = PARTICLES.register("chimpanzee_needs_sun", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> FLY = PARTICLES.register("fly", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> TEAR = PARTICLES.register("tear", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> DRIPPING_DRIPSTONE_MILK = PARTICLES.register("dripping_dripstone_milk", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FALLING_DRIPSTONE_MILK = PARTICLES.register("falling_dripstone_milk", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> MILK_SPLASH = PARTICLES.register("milk_splash", () -> new SimpleParticleType(false));

	@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			ParticleEngine manager = Minecraft.getInstance().particleEngine;
			if (MINT_BOOST.isPresent()) {
				manager.register(MINT_BOOST.get(), MintBoostParticle.Factory::new);
			}
			if (CHIMPANZEE_NEEDS_FOOD.isPresent()) {
				manager.register(CHIMPANZEE_NEEDS_FOOD.get(), ChimpanzeeNeedParticle.Factory::new);
			}
			if (CHIMPANZEE_NEEDS_FRIEND.isPresent()) {
				manager.register(CHIMPANZEE_NEEDS_FRIEND.get(), ChimpanzeeNeedParticle.Factory::new);
			}
			if (CHIMPANZEE_NEEDS_SUN.isPresent()) {
				manager.register(CHIMPANZEE_NEEDS_SUN.get(), ChimpanzeeNeedParticle.Factory::new);
			}
			if (FLY.isPresent()) {
				manager.register(FLY.get(), FlyParticle.Factory::new);
			}
			if (TEAR.isPresent()) {
				manager.register(TEAR.get(), TearParticle.Factory::new);
			}
			if (DRIPPING_DRIPSTONE_MILK.isPresent()) {
				manager.register(DRIPPING_DRIPSTONE_MILK.get(), MilkDripParticle.DripstoneWaterHangProvider::new);
			}
			if (FALLING_DRIPSTONE_MILK.isPresent()) {
				manager.register(FALLING_DRIPSTONE_MILK.get(), MilkDripParticle.DripstoneWaterFallProvider::new);
			}
			if (MILK_SPLASH.isPresent()) {
				manager.register(MILK_SPLASH.get(), SplashParticle.Provider::new);
			}
		}
	}
}
