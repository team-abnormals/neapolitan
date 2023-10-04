package com.teamabnormals.neapolitan.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.loot.modification.LootModifierProvider;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolsModifier;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemPredicate.Builder;
import net.minecraft.advancements.critereon.MinMaxBounds.Ints;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;

import java.util.Arrays;
import java.util.Collections;

public class NeapolitanLootModifierProvider extends LootModifierProvider {

	public NeapolitanLootModifierProvider(DataGenerator generator) {
		super(generator, Neapolitan.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		this.entry("village_plains_house").selects(BuiltInLootTables.VILLAGE_PLAINS_HOUSE).addModifier(new LootPoolEntriesModifier(false, 0, Arrays.asList(createLootEntry(NeapolitanItems.STRAWBERRIES.get(), 10, 1, 13), createLootEntry(NeapolitanItems.STRAWBERRY_SCONES.get(), 10, 1, 5))));
		this.entry("village_savanna_house").selects(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE).addModifier(new LootPoolEntriesModifier(false, 0, Arrays.asList(createLootEntry(NeapolitanItems.VANILLA_PODS.get(), 10, 1, 7), createLootEntry(NeapolitanItems.VANILLA_FUDGE.get(), 10, 1, 4))));
		this.entry("village_snowy_house").selects(BuiltInLootTables.VILLAGE_SNOWY_HOUSE).addModifier(new LootPoolEntriesModifier(false, 0, Collections.singletonList(createLootEntry(NeapolitanItems.ICE_CUBES.get(), 10, 1, 8))));

		this.entry("jungle_temple").selects(BuiltInLootTables.JUNGLE_TEMPLE).addModifier(new LootPoolEntriesModifier(false, 0, Arrays.asList(createLootEntry(NeapolitanItems.CHOCOLATE_BAR.get(), 10, 2, 6), createLootEntry(NeapolitanItems.CHOCOLATE_SPIDER_EYE.get(), 12, 1, 3))));
		this.entry("jungle_temple_dispenser").selects(BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER).addModifier(new LootPoolEntriesModifier(false, 0, Collections.singletonList(createLootEntry(NeapolitanItems.BANANARROW.get(), 20, 2, 5))));

		this.entry("ravager").selects("entities/ravager").addModifier(new LootPoolsModifier(Collections.singletonList(LootPool.lootPool().name("neapolitan:ravager").setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.MUSIC_DISC_HULLABALOO.get()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(NeapolitanEntityTypes.CHIMPANZEE.get())))).build()), false));
		this.entry("ice").selects("blocks/ice").addModifier(new LootPoolsModifier(Collections.singletonList(LootPool.lootPool().name("neapolitan:ice").setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.ICE_CUBES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(ApplyExplosionDecay.explosionDecay())).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_PICKAXES))).when(MatchTool.toolMatches(Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, Ints.atLeast(1)))).invert()).build()), false));
	}

	private static LootPoolEntryContainer createLootEntry(ItemLike item, int weight, int min, int max) {
		return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
	}
}