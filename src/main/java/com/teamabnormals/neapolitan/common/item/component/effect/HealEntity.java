package com.teamabnormals.neapolitan.common.item.component.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.blueprint.common.network.particle.SpawnParticlesPayload;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.neapolitan.core.registry.NeapolitanIceCreamFlavorEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.List;

public record HealEntity(LevelBasedValue minHealth, LevelBasedValue maxHealth) implements IceCreamFlavorEffect {
	public static final MapCodec<HealEntity> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LevelBasedValue.CODEC.fieldOf("min_health").forGetter(HealEntity::minHealth),
			LevelBasedValue.CODEC.fieldOf("max_health").forGetter(HealEntity::maxHealth)
	).apply(instance, HealEntity::new));

	@Override
	public void finishUsingItem(ServerLevel level, int flavorLevel, ItemStack stack, Entity entity) {
		if (entity instanceof LivingEntity living) {
			float amount = Mth.randomBetween(entity.getRandom(), this.minHealth.calculate(flavorLevel), this.maxHealth.calculate(flavorLevel));
			living.heal(amount);

			RandomSource rand = entity.getRandom();
			int times = 2 * Math.round(amount);
			for (int i = 0; i < times; ++i) {
				double d0 = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
				NetworkUtil.spawnParticle(level, ParticleTypes.HEART, List.of(new SpawnParticlesPayload.ParticleInstance(
						entity.getRandomX(1.0D), entity.getRandomY() + 0.5D, entity.getRandomZ(1.0D), d0, d1, d2))
				);
			}
		}
	}

	@Override
	public IceCreamFlavorEffectType getType() {
		return NeapolitanIceCreamFlavorEffects.HEAL_ENTITY.get();
	}
}