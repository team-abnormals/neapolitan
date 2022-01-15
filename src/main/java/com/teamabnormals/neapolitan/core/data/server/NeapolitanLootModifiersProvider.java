package com.teamabnormals.neapolitan.core.data.server;

import com.google.gson.Gson;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.loot.modification.LootModifiers;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier.Config;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolsModifier;
import com.teamabnormals.blueprint.core.util.modification.ConfiguredModifier;
import com.teamabnormals.blueprint.core.util.modification.ModifierDataProvider;
import com.teamabnormals.blueprint.core.util.modification.TargetedModifier;
import com.teamabnormals.blueprint.core.util.modification.targeting.ConditionedModifierTargetSelector;
import com.teamabnormals.blueprint.core.util.modification.targeting.ModifierTargetSelectorRegistry;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.PredicateManager;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NeapolitanLootModifiersProvider {
	public static ModifierDataProvider<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> createLootModifierDataProvider(DataGenerator dataGenerator) {
		return LootModifiers.createDataProvider(dataGenerator, "Neapolitan Loot Modifiers", Neapolitan.MOD_ID,
				add("village_plains_house", Collections.singletonList(entriesModifier(Arrays.asList(
						createLootEntry(NeapolitanItems.STRAWBERRIES.get(), 10, 1, 13),
						createLootEntry(NeapolitanItems.STRAWBERRY_SCONES.get(), 10, 1, 5)))
				), BuiltInLootTables.VILLAGE_PLAINS_HOUSE),

				add("village_savanna_house", Collections.singletonList(entriesModifier(Arrays.asList(
						createLootEntry(NeapolitanItems.VANILLA_PODS.get(), 10, 1, 7),
						createLootEntry(NeapolitanItems.VANILLA_FUDGE.get(), 10, 1, 4)))
				), BuiltInLootTables.VILLAGE_SAVANNA_HOUSE),

				add("village_snowy_house", Collections.singletonList(entriesModifier(NeapolitanItems.ICE_CUBES.get(), 10, 1, 8)), BuiltInLootTables.VILLAGE_SNOWY_HOUSE),

				add("jungle_temple", Collections.singletonList(entriesModifier(Arrays.asList(
						createLootEntry(NeapolitanItems.CHOCOLATE_BAR.get(), 10, 2, 6),
						createLootEntry(NeapolitanItems.CHOCOLATE_SPIDER_EYE.get(), 12, 1, 3)))
				), BuiltInLootTables.JUNGLE_TEMPLE),

				add("jungle_temple_dispenser", Collections.singletonList(entriesModifier(NeapolitanItems.BANANARROW.get(), 20, 2, 5)), BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER),
				add("ravager", LootPool.lootPool().name("neapolitan:ravager").setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.MUSIC_DISC_HULLABALOO.get()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(NeapolitanEntityTypes.CHIMPANZEE.get())))).build(), new ResourceLocation("entities/ravager")),
				add("ice", LootPool.lootPool().name("neapolitan:ice").setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.ICE_CUBES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(ApplyExplosionDecay.explosionDecay())).when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))).invert()).build(), new ResourceLocation("blocks/ice"))
		);
	}

	private static ModifierDataProvider.ProviderEntry<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> add(String name, boolean replace, int index, ItemLike item, int weight, ResourceLocation... lootTables) {
		return add(name, Collections.singletonList(entriesModifier(replace, index, item, weight)), lootTables);
	}

	private static ModifierDataProvider.ProviderEntry<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> add(String name, LootPool pool, ResourceLocation... lootTables) {
		return add(name, Collections.singletonList(poolsModifier(Collections.singletonList(pool))), lootTables);
	}

	private static ModifierDataProvider.ProviderEntry<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> add(String name, List<ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?>> modifiers, ResourceLocation... lootTables) {
		return new ModifierDataProvider.ProviderEntry<>(
				new TargetedModifier<>(new ConditionedModifierTargetSelector<>(ModifierTargetSelectorRegistry.NAMES.withConfiguration(Arrays.asList(lootTables))), modifiers),
				new ResourceLocation(Neapolitan.MOD_ID, name)
		);
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> entriesModifier(boolean replace, int index, List<LootPoolEntryContainer> entries) {
		return new ConfiguredModifier<>(LootModifiers.ENTRIES_MODIFIER, new Config(replace, index, entries));
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> entriesModifier(List<LootPoolEntryContainer> entries) {
		return new ConfiguredModifier<>(LootModifiers.ENTRIES_MODIFIER, new Config(false, 0, entries));
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> poolsModifier(List<LootPool> pools) {
		return new ConfiguredModifier<>(LootModifiers.POOLS_MODIFIER, new LootPoolsModifier.Config(pools, false));
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> entriesModifier(boolean replace, int index, ItemLike item, int weight) {
		return entriesModifier(replace, index, Collections.singletonList(createLootEntry(item, weight)));
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> entriesModifier(boolean replace, int index, ItemLike item, int weight, int min, int max) {
		return entriesModifier(replace, index, Collections.singletonList(createLootEntry(item, weight, min, max)));
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> entriesModifier(ItemLike item, int weight, int min, int max) {
		return entriesModifier(false, 0, item, weight, min, max);
	}

	private static ConfiguredModifier<LootTableLoadEvent, ?, Gson, Pair<Gson, PredicateManager>, ?> entriesModifier(Item item, int weight) {
		return entriesModifier(false, 0, Collections.singletonList(createLootEntry(item, weight)));
	}

	private static LootPoolEntryContainer createLootEntry(ItemLike item, int weight) {
		return LootItem.lootTableItem(item).setWeight(weight).build();
	}

	private static LootPoolEntryContainer createLootEntry(ItemLike item, int weight, int min, int max) {
		return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
	}
}