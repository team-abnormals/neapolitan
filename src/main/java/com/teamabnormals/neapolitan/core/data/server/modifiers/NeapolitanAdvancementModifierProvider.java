package com.teamabnormals.neapolitan.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanLootTables;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class NeapolitanAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final EntityType<?>[] BREEDABLE_ANIMALS = new EntityType[]{NeapolitanEntityTypes.CHIMPANZEE.get()};
	private static final EntityType<?>[] MOBS_TO_KILL = new EntityType[]{NeapolitanEntityTypes.PLANTAIN_SPIDER.get()};

	public NeapolitanAdvancementModifierProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(Neapolitan.MOD_ID, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		MobEffectsPredicate predicate = MobEffectsPredicate.effects();
		NeapolitanMobEffects.MOB_EFFECTS.getEntries().forEach(mobEffect -> predicate.and(mobEffect.get()));
		this.entry("nether/all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, predicate));

		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Item>> items = NeapolitanItems.HELPER.getDeferredRegister().getEntries();
		items.forEach(item -> {
			if (item.get().isEdible()) {
				balancedDiet.addCriterion(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());

		CriteriaModifier.Builder breedAllAnimals = CriteriaModifier.builder(this.modId);
		for (EntityType<?> entityType : BREEDABLE_ANIMALS) {
			breedAllAnimals.addCriterion(ForgeRegistries.ENTITY_TYPES.getKey(entityType).getPath(), BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().of(entityType)));
		}
		this.entry("husbandry/bred_all_animals").selects("husbandry/bred_all_animals").addModifier(breedAllAnimals.requirements(RequirementsStrategy.AND).build());

		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("strawberry_bush", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.STRAWBERRY_BUSH.get()))
				.addCriterion("mint", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.MINT.get()))
				.addCriterion("adzuki_soil", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(NeapolitanBlocks.ADZUKI_SOIL.get()))
				.addIndexedRequirements(0, false, "strawberry_bush", "mint", "adzuki_soil").build());

		this.entry("adventure/salvage_sherd").selects("adventure/salvage_sherd").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("banana_plant_common", LootTableTrigger.TriggerInstance.lootTableUsed(NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_COMMON))
				.addCriterion("banana_plant_rare", LootTableTrigger.TriggerInstance.lootTableUsed(NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_RARE))
				.addIndexedRequirements(0, false, "banana_plant_common", "banana_plant_rare").build());

		String entry = "armor_trimmed_" + NeapolitanItems.PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.getId();
		this.entry("adventure/trim_with_any_armor_pattern").selects("adventure/trim_with_any_armor_pattern").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion(entry, RecipeCraftedTrigger.TriggerInstance.craftedItem(NeapolitanItems.PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.getId()))
				.addIndexedRequirements(0, false, entry).build());

		CriteriaModifier.Builder killAMob = CriteriaModifier.builder(this.modId);
		CriteriaModifier.Builder killAllMobs = CriteriaModifier.builder(this.modId);
		ArrayList<String> names = Lists.newArrayList();
		for (EntityType<?> entityType : MOBS_TO_KILL) {
			String name = ForgeRegistries.ENTITY_TYPES.getKey(entityType).getPath();
			KilledTrigger.TriggerInstance triggerInstance = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityType));
			killAMob.addCriterion(name, triggerInstance);
			killAllMobs.addCriterion(name, triggerInstance);
			names.add(name);
		}

		this.entry("adventure/kill_a_mob").selects("adventure/kill_a_mob").addModifier(killAMob.addIndexedRequirements(0, false, names.toArray(new String[0])).build());
		this.entry("adventure/kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(killAllMobs.requirements(RequirementsStrategy.AND).build());
	}
}