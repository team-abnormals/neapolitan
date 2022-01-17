package com.teamabnormals.neapolitan.common.effect;

import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Map.Entry;

public class SugarRushMobEffect extends MobEffect {

	public SugarRushMobEffect() {
		super(MobEffectCategory.NEUTRAL, 6739711);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", 0.0F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
		return 0.1 * (double) (amplifier + 1);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMapIn, int amplifier) {
		if (!entity.level.isClientSide) {
			int duration = entity.getEffect(NeapolitanMobEffects.SUGAR_RUSH.get()).getDuration();
			int totalDuration = entity.getPersistentData().contains("SugarRushDuration") ? entity.getPersistentData().getInt("SugarRushDuration") : 0;
			float amount = duration >= totalDuration / 3 ? (amplifier + 2) * 0.5F : -(amplifier + 2) * 1.25F;
			for (Entry<Attribute, AttributeModifier> entry : this.getAttributeModifiers().entrySet()) {
				AttributeInstance iattributeinstance = attributeMapIn.getInstance(entry.getKey());
				if (iattributeinstance != null) {
					AttributeModifier attributemodifier = entry.getValue();
					iattributeinstance.removeModifier(attributemodifier);
					iattributeinstance.addPermanentModifier(new AttributeModifier(attributemodifier.getId(), this.getDescriptionId() + " " + amplifier, amount * this.getAttributeModifierValue(amplifier, attributemodifier), attributemodifier.getOperation()));
				}
			}
		}
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		this.addAttributeModifiers(entity, entity.getAttributes(), amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
