package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.*;
import net.minecraft.advancements.critereon.PlayerTrigger.TriggerInstance;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;

public class NeapolitanCriteriaTriggers {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Neapolitan.MOD_ID);

	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> ATTACK_WITH_CHIMPANZEES = TRIGGERS.register("attack_with_chimpanzees", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> HEAL_FROM_CREEPER = TRIGGERS.register("heal_from_creeper", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, AnyBlockInteractionTrigger> VANILLA_VINE_DESTROYED = TRIGGERS.register("break_vanilla_vine", AnyBlockInteractionTrigger::new);

	public static Criterion<TriggerInstance> attackedWithChimpanzees() {
		return ATTACK_WITH_CHIMPANZEES.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()));
	}

	public static Criterion<TriggerInstance> healedFromCreeper() {
		return HEAL_FROM_CREEPER.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()));
	}

	public static Criterion<AnyBlockInteractionTrigger.TriggerInstance> vanillaVineDestroyedWithShears() {
		return VANILLA_VINE_DESTROYED.get().createCriterion(new AnyBlockInteractionTrigger.TriggerInstance(
				Optional.of(EntityPredicate.wrap(EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR)).build()).build())),
				Optional.empty())
		);
	}
}
