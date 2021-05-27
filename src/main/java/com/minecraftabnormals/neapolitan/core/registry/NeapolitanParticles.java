package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.client.particle.FlyParticle;
import com.minecraftabnormals.neapolitan.client.particle.ChimpanzeeHungryParticle;
import com.minecraftabnormals.neapolitan.client.particle.MintBoostParticle;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Neapolitan.MOD_ID);

	public static final RegistryObject<BasicParticleType> MINT_BOOST = PARTICLES.register("mint_boost", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> CHIMPANZEE_HUNGRY = PARTICLES.register("chimpanzee_hungry", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> FLY = PARTICLES.register("fly", () -> new BasicParticleType(true));

	@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			ParticleManager manager = Minecraft.getInstance().particles;
			if (MINT_BOOST.isPresent()) {
				manager.registerFactory(MINT_BOOST.get(), MintBoostParticle.Factory::new);
			}
			if (CHIMPANZEE_HUNGRY.isPresent()) {
				manager.registerFactory(CHIMPANZEE_HUNGRY.get(), ChimpanzeeHungryParticle.Factory::new);
			}
			if (FLY.isPresent()) {
				manager.registerFactory(FLY.get(), FlyParticle.Factory::new);
			}
		}
	}
}
