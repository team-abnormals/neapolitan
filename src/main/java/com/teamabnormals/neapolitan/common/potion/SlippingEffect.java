package com.teamabnormals.neapolitan.common.potion;

import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class SlippingEffect extends MobEffect {

	public SlippingEffect() {
		super(MobEffectCategory.NEUTRAL, 0xD4B133);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.isOnGround() && !entity.isInWater() && !NeapolitanEntityTypeTags.UNAFFECTED_BY_SLIPPING.contains(entity.getType())) {
			Random rand = entity.getCommandSenderWorld().getRandom();
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
