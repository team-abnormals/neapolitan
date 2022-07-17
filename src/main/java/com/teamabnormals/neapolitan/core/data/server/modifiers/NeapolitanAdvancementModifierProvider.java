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
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Collection;

public class NeapolitanAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final EntityType<?>[] BREEDABLE_ANIMALS = new EntityType[]{NeapolitanEntityTypes.CHIMPANZEE.get()};
	private static final EntityType<?>[] MOBS_TO_KILL = new EntityType[]{NeapolitanEntityTypes.PLANTAIN_SPIDER.get()};

	public NeapolitanAdvancementModifierProvider(DataGenerator dataGenerator) {
		super(dataGenerator, Neapolitan.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		MobEffectsPredicate predicate = MobEffectsPredicate.effects();
		NeapolitanMobEffects.MOB_EFFECTS.getEntries().forEach(mobEffect -> predicate.and(mobEffect.get()));
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, predicate));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Item>> items = NeapolitanItems.HELPER.getDeferredRegister().getEntries();
		items.forEach(item -> {
			if (item.get().isEdible()) {
				balancedDiet.addCriterion(item.get().getRegistryName().getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());

		CriteriaModifier.Builder breedAllAnimals = CriteriaModifier.builder(this.modId);
		for (EntityType<?> entityType : BREEDABLE_ANIMALS) {
			breedAllAnimals.addCriterion(entityType.getRegistryName().getPath(), BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().of(entityType)));
		}
		this.entry("husbandry/bred_all_animals").selects("husbandry/bred_all_animals").addModifier(breedAllAnimals.requirements(RequirementsStrategy.AND).build());

		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("strawberry_bush", PlacedBlockTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.STRAWBERRY_BUSH.get()))
				.addCriterion("mint", PlacedBlockTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.MINT.get()))
				.addCriterion("adzuki_soil", PlacedBlockTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.ADZUKI_SOIL.get()))
				.addIndexedRequirements(0, false, "strawberry_bush", "mint", "adzuki_soil").build());

		CriteriaModifier.Builder killAMob = CriteriaModifier.builder(this.modId);
		CriteriaModifier.Builder killAllMobs = CriteriaModifier.builder(this.modId);
		ArrayList<String> names = Lists.newArrayList();
		for (EntityType<?> entityType : MOBS_TO_KILL) {
			String name = entityType.getRegistryName().getPath();
			KilledTrigger.TriggerInstance triggerInstance = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityType));
			killAMob.addCriterion(name, triggerInstance);
			killAllMobs.addCriterion(name, triggerInstance);
			names.add(name);
		}

		this.entry("adventure/kill_a_mob").selects("adventure/kill_a_mob").addModifier(killAMob.addIndexedRequirements(0, false, names.toArray(new String[0])).build());
		this.entry("adventure/kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(killAllMobs.requirements(RequirementsStrategy.AND).build());
	}
}