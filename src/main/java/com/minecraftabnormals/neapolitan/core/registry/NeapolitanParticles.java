package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.client.particle.FlyParticle;
import com.minecraftabnormals.neapolitan.client.particle.ChimpanzeeNeedParticle;
import com.minecraftabnormals.neapolitan.client.particle.MintBoostParticle;
import com.minecraftabnormals.neapolitan.client.particle.TearParticle;
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
	public static final RegistryObject<BasicParticleType> CHIMPANZEE_NEEDS_FOOD = PARTICLES.register("chimpanzee_needs_food", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> CHIMPANZEE_NEEDS_FRIEND = PARTICLES.register("chimpanzee_needs_friend", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> CHIMPANZEE_NEEDS_SUN = PARTICLES.register("chimpanzee_needs_sun", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> FLY = PARTICLES.register("fly", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> TEAR = PARTICLES.register("tear", () -> new BasicParticleType(true));

	@Mod.EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			ParticleManager manager = Minecraft.getInstance().particleEngine;
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
		}
	}
}
