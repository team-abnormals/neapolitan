package com.teamabnormals.neapolitan.common.effect;

import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SlippingMobEffect extends MobEffect {

	public SlippingMobEffect() {
		super(MobEffectCategory.NEUTRAL, 0xD4B133);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.onGround() && !entity.isInWater() && !entity.getType().is(NeapolitanEntityTypeTags.UNAFFECTED_BY_SLIPPING)) {
			RandomSource rand = entity.getCommandSenderWorld().getRandom();
			float amount = rand.nextFloat() * 0.2F;
			float x = 0.0F;
			float z = 0.0F;
			if (rand.nextBoolean())
				amount *= -1;
			if (rand.nextBoolean())
				x = amount;
			else
				z = amount;

			entity.setDeltaMovement(entity.getDeltaMovement().add(x, 0.0F, z));
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
