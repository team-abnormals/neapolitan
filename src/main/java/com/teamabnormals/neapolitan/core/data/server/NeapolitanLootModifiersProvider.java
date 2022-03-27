package com.teamabnormals.neapolitan.core.data.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.loot.modification.LootModificationManager;
import com.teamabnormals.blueprint.common.loot.modification.LootModifierSerializers;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolsModifier;
import com.teamabnormals.blueprint.core.util.modification.ObjectModifierProvider;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate.Builder;
import net.minecraft.advancements.critereon.MinMaxBounds.Ints;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.Deserializers;
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

public class NeapolitanLootModifiersProvider extends ObjectModifierProvider<LootTableLoadEvent, Gson, Pair<Gson, PredicateManager>> {

	public NeapolitanLootModifiersProvider(DataGenerator dataGenerator) {
		super(dataGenerator, Neapolitan.MOD_ID, true, LootModificationManager.TARGET_DIRECTORY, new GsonBuilder().setPrettyPrinting().create(), LootModifierSerializers.REGISTRY, (group) -> Deserializers.createLootTableSerializer().create());
	}

	@Override
	protected void registerEntries() {
		this.registerEntry("village_plains_house", new LootPoolEntriesModifier(false, 0, Arrays.asList(createLootEntry(NeapolitanItems.STRAWBERRIES.get(), 10, 1, 13), createLootEntry(NeapolitanItems.STRAWBERRY_SCONES.get(), 10, 1, 5))), BuiltInLootTables.VILLAGE_PLAINS_HOUSE);
		this.registerEntry("village_savanna_house", new LootPoolEntriesModifier(false, 0, Arrays.asList(createLootEntry(NeapolitanItems.VANILLA_PODS.get(), 10, 1, 7), createLootEntry(NeapolitanItems.VANILLA_FUDGE.get(), 10, 1, 4))), BuiltInLootTables.VILLAGE_SAVANNA_HOUSE);
		this.registerEntry("village_snowy_house", new LootPoolEntriesModifier(false, 0, Collections.singletonList(createLootEntry(NeapolitanItems.ICE_CUBES.get(), 10, 1, 8))), BuiltInLootTables.VILLAGE_SNOWY_HOUSE);

		this.registerEntry("jungle_temple", new LootPoolEntriesModifier(false, 0, Arrays.asList(createLootEntry(NeapolitanItems.CHOCOLATE_BAR.get(), 10, 2, 6), createLootEntry(NeapolitanItems.CHOCOLATE_SPIDER_EYE.get(), 12, 1, 3))), BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER);
		this.registerEntry("jungle_temple_dispenser", new LootPoolEntriesModifier(false, 0, Collections.singletonList(createLootEntry(NeapolitanItems.BANANARROW.get(), 20, 2, 5))), BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER);

		this.registerEntry("ravager", new LootPoolsModifier(Collections.singletonList(LootPool.lootPool().name("neapolitan:ravager").setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.MUSIC_DISC_HULLABALOO.get()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(NeapolitanEntityTypes.CHIMPANZEE.get())))).build()), false), new ResourceLocation("entities/ravager"));
		this.registerEntry("ice", new LootPoolsModifier(Collections.singletonList(LootPool.lootPool().name("neapolitan:ice").setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.ICE_CUBES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(ApplyExplosionDecay.explosionDecay())).when(MatchTool.toolMatches(Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, Ints.atLeast(1)))).invert()).build()), false), new ResourceLocation("blocks/ice"));
	}

	private static LootPoolEntryContainer createLootEntry(ItemLike item, int weight, int min, int max) {
		return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
	}
}