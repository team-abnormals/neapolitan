package com.teamabnormals.neapolitan.core.data.server;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.other.NeapolitanLootTables;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeapolitanLootTableProvider extends LootTableProvider {

	public NeapolitanLootTableProvider(PackOutput output) {
		super(output, BuiltInLootTables.all(), ImmutableList.of(
				new LootTableProvider.SubProviderEntry(NeapolitanBlockLoot::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(NeapolitanArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
		));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
	}

	private static class NeapolitanBlockLoot extends BlockLootSubProvider {
		private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

		protected NeapolitanBlockLoot() {
			super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
		}

		@Override
		public void generate() {
			FlavoredCandleCakeBlock.getCandleCakes().forEach((block -> this.add(block, createCandleCakeDrops(((FlavoredCandleCakeBlock) block).getCandle()))));
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return FlavoredCandleCakeBlock.getCandleCakes();
		}
	}

	private static class NeapolitanArchaeologyLoot implements LootTableSubProvider {

		public void generate(BiConsumer<ResourceLocation, Builder> consumer) {
			consumer.accept(NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_COMMON, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(NeapolitanItems.DRIED_BANANA.get()).setWeight(2))
					.add(LootItem.lootTableItem(Items.FERMENTED_SPIDER_EYE).setWeight(2))
					.add(LootItem.lootTableItem(NeapolitanItems.BANANA_FROND.get()))
					.add(LootItem.lootTableItem(Items.COCOA_BEANS))
					.add(LootItem.lootTableItem(Items.SPIDER_EYE))
					.add(LootItem.lootTableItem(Items.STRING))
					.add(LootItem.lootTableItem(Items.BONE))
					.add(LootItem.lootTableItem(Items.STICK))));
			consumer.accept(NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_RARE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(NeapolitanItems.REFLECTION_POTTERY_SHERD.get()))
					.add(LootItem.lootTableItem(NeapolitanItems.ANGER_POTTERY_SHERD.get()))
					.add(LootItem.lootTableItem(NeapolitanItems.SPIDER_POTTERY_SHERD.get()))
					.add(LootItem.lootTableItem(NeapolitanItems.BANANA_POTTERY_SHERD.get()))
					.add(LootItem.lootTableItem(NeapolitanItems.CHIMPANZEE_HEAD.get()))));

		}
	}
}