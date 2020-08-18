package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.common.potion.SugarRushEffect;
import com.minecraftabnormals.neapolitan.common.potion.VanillaScentEffect;
import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NeapolitanEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Neapolitan.MODID);

    public static final RegistryObject<Effect> SUGAR_RUSH       = EFFECTS.register("sugar_rush", () -> new SugarRushEffect().addAttributesModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double) 0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> VANILLA_SCENT    = EFFECTS.register("vanilla_scent", () -> new VanillaScentEffect());
}
