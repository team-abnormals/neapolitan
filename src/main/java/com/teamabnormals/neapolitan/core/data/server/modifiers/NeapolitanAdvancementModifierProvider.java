package com.teamabnormals.neapolitan.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.BredAnimalsTrigger;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.advancements.critereon.PlacedBlockTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class NeapolitanAdvancementModifierProvider extends AdvancementModifierProvider {

	public NeapolitanAdvancementModifierProvider(DataGenerator dataGenerator) {
		super(dataGenerator, Neapolitan.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		MobEffectsPredicate predicate = MobEffectsPredicate.effects();
		NeapolitanMobEffects.MOB_EFFECTS.getEntries().forEach(mobEffect -> predicate.and(mobEffect.get()));
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, predicate));

		CriteriaModifier.Builder criteria = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Item>> items = NeapolitanItems.HELPER.getDeferredRegister().getEntries();
		items.forEach(item -> {
			if (item.get().getFoodProperties() != null) {
				ResourceLocation name = item.get().getRegistryName();
				criteria.addCriterion(name.getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(criteria.requirements(RequirementsStrategy.AND).build());

		this.entry("husbandry/bred_all_animals").selects("husbandry/bred_all_animals").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("chimpanzee", BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().of(NeapolitanEntityTypes.CHIMPANZEE.get())))
				.requirements(RequirementsStrategy.AND).build());

		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("strawberry_bush", PlacedBlockTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.STRAWBERRY_BUSH.get()))
				.addCriterion("mint", PlacedBlockTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.MINT.get()))
				.addCriterion("adzuki_soil", PlacedBlockTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.ADZUKI_SOIL.get()))
				.addIndexedRequirements(0, false, "strawberry_bush", "mint", "adzuki_soil").build());

		this.entry("adventure/kill_a_mob").selects("adventure/kill_a_mob").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("plantain_spider", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(NeapolitanEntityTypes.PLANTAIN_SPIDER.get())))
				.addIndexedRequirements(0, false, "plantain_spider").build());
		this.entry("adventure/kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("plantain_spider", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(NeapolitanEntityTypes.PLANTAIN_SPIDER.get())))
				.requirements(RequirementsStrategy.AND).build());
	}
}