package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext.EntityTarget;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.common.remolder.RemolderTypes.sequence;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addEntry;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addPool;

public class NeapolitanDataRemolderProvider extends RemolderProvider {

	public NeapolitanDataRemolderProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(Neapolitan.MOD_ID, Target.DATA_PACK, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		this.entry("village_plains_house").path("loot_table/chests/village/village_plains_house").remolder(sequence(
				addEntry(0, lootEntry(NeapolitanItems.STRAWBERRIES.get(), 10, 1, 13)),
				addEntry(0, lootEntry(NeapolitanItems.STRAWBERRY_SCONES.get(), 10, 1, 5))
		));

		this.entry("village_savanna_house").path("loot_table/chests/village/village_savanna_house").remolder(sequence(
				addEntry(0, lootEntry(NeapolitanItems.VANILLA_PODS.get(), 10, 1, 7)),
				addEntry(0, lootEntry(NeapolitanItems.VANILLA_FUDGE.get(), 10, 1, 4))
		));

		this.entry("village_snowy_house").path("loot_table/chests/village/village_snowy_house").remolder(
				addEntry(0, lootEntry(NeapolitanItems.ICE_CUBES.get(), 10, 1, 8))
		);

		this.entry("jungle_temple").path("loot_table/chests/jungle_temple").remolder(sequence(
				addEntry(0, lootEntry(NeapolitanItems.CHOCOLATE_BAR.get(), 10, 2, 6)),
				addEntry(0, lootEntry(NeapolitanItems.CHOCOLATE_SPIDER_EYE.get(), 12, 1, 3))
		));

		this.entry("jungle_temple_dispenser").path("loot_table/chests/jungle_temple_dispenser").remolder(
				addEntry(0, lootEntry(NeapolitanItems.BANANARROW.get(), 20, 2, 5))
		);

		this.entry("ravager").path("loot_table/entities/ravager").remolder(addPool(LootPool.lootPool()
				.name("neapolitan:ravager")
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(NeapolitanItems.MUSIC_DISC_HULLABALOO.get()).when(LootItemEntityPropertyCondition.hasProperties(EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(NeapolitanEntityTypes.CHIMPANZEE.get()))))
				.build()
		));

		this.entry("ice").path("loot_table/blocks/ice").remolder(addPool(LootPool.lootPool()
				.name("neapolitan:ice")
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(NeapolitanItems.ICE_CUBES.get())
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						.apply(ApplyBonusCount.addOreBonusCount(provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))
						.when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.PICKAXES)).and(hasSilkTouch(provider).invert())))
				.build()
		));
	}

	private static LootPoolEntryContainer lootEntry(ItemLike item, int weight, int min, int max) {
		return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
	}

	public static LootItemCondition.Builder hasSilkTouch(Provider provider) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = provider.lookupOrThrow(Registries.ENCHANTMENT);
		return MatchTool.toolMatches(
				ItemPredicate.Builder.item()
						.withSubPredicate(
								ItemSubPredicates.ENCHANTMENTS,
								ItemEnchantmentsPredicate.enchantments(
										List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))
								)
						)
		);
	}
}