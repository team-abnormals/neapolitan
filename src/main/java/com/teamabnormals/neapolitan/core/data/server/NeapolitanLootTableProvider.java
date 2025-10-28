package com.teamabnormals.neapolitan.core.data.server;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.neapolitan.common.block.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanLootTables;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.*;

public class NeapolitanLootTableProvider extends LootTableProvider {

	public NeapolitanLootTableProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, BuiltInLootTables.all(), ImmutableList.of(
				new LootTableProvider.SubProviderEntry(NeapolitanBlockLoot::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(NeapolitanEntityLoot::new, LootContextParamSets.ENTITY),
				new LootTableProvider.SubProviderEntry(NeapolitanArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
		), provider);
	}

	@Override
	protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
	}

	private static class NeapolitanBlockLoot extends BlockLootSubProvider {
		private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(CHIMPANZEE_HEAD.get()).map(ItemLike::asItem).collect(Collectors.toSet());

		protected NeapolitanBlockLoot(Provider provider) {
			super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

			this.add(CHILLBOX.get(), this::createNameableBlockEntityTable);

			this.add(VANILLA_CAKE.get(), noDrop());
			this.add(CHOCOLATE_CAKE.get(), noDrop());
			this.add(STRAWBERRY_CAKE.get(), noDrop());
			this.add(BANANA_CAKE.get(), noDrop());
			this.add(MINT_CAKE.get(), noDrop());
			this.add(ADZUKI_CAKE.get(), noDrop());

			this.dropSelf(VANILLA_ICE_CREAM_BLOCK.get());
			this.dropSelf(CHOCOLATE_ICE_CREAM_BLOCK.get());
			this.dropSelf(STRAWBERRY_ICE_CREAM_BLOCK.get());
			this.dropSelf(BANANA_ICE_CREAM_BLOCK.get());
			this.dropSelf(MINT_ICE_CREAM_BLOCK.get());
			this.dropSelf(ADZUKI_ICE_CREAM_BLOCK.get());

			this.dropOther(MILK_CAULDRON.get(), Blocks.CAULDRON);
			this.dropOther(VANILLA_MILKSHAKE_CAULDRON.get(), Blocks.CAULDRON);
			this.dropOther(CHOCOLATE_MILKSHAKE_CAULDRON.get(), Blocks.CAULDRON);
			this.dropOther(STRAWBERRY_MILKSHAKE_CAULDRON.get(), Blocks.CAULDRON);
			this.dropOther(BANANA_MILKSHAKE_CAULDRON.get(), Blocks.CAULDRON);
			this.dropOther(MINT_MILKSHAKE_CAULDRON.get(), Blocks.CAULDRON);
			this.dropOther(ADZUKI_MILKSHAKE_CAULDRON.get(), Blocks.CAULDRON);

			this.dropSelf(CHOCOLATE_BLOCK.get());
			this.dropSelf(CHOCOLATE_BRICKS.get());
			this.dropSelf(CHOCOLATE_BRICK_STAIRS.get());
			this.add(CHOCOLATE_BRICK_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CHOCOLATE_BRICK_WALL.get());
			this.dropSelf(CHISELED_CHOCOLATE_BRICKS.get());
			this.dropSelf(CHOCOLATE_TILES.get());
			this.dropSelf(CHOCOLATE_TILE_STAIRS.get());
			this.add(CHOCOLATE_TILE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CHOCOLATE_TILE_WALL.get());

			this.dropSelf(FROND_THATCH.get());
			this.dropSelf(FROND_THATCH_STAIRS.get());
			this.add(FROND_THATCH_SLAB.get(), this::createSlabItemTable);

			this.add(STRAWBERRY_BUSH.get(), this::createStrawberryDrops);
			this.add(MINT.get(), this::createMintDrops);
			this.add(ADZUKI_SPROUTS.get(), this::createAdzukiDrops);

			this.dropSelf(MAGIC_BEANS.get());
			this.dropSelf(BEANSTALK.get());
			this.add(BEANSTALK_THORNS.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.add(ADZUKI_SOIL.get(), LootTable.lootTable()
					.withPool(this.applyExplosionCondition(Blocks.DIRT, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Blocks.DIRT))))
					.withPool(this.applyExplosionCondition(NeapolitanItems.ADZUKI_BEANS, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.ADZUKI_BEANS)))));

			this.dropSelf(BANANA_STALK.get());
			this.dropSelf(CARVED_BANANA_STALK.get());

			this.add(BANANA_BUNDLE.get(), block -> this.createSilkTouchOrShearsDispatchTable(block, this.applyExplosionDecay(block,
					LootItem.lootTableItem(NeapolitanItems.BANANA_BUNCH)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
							.apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
							.apply(LimitCount.limitCount(IntRange.upperBound(9)))
			)));

			this.add(BANANA_FROND.get(), block -> LootTable.lootTable().withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(this.applyExplosionDecay(BANANA_FROND.get(), LootItem.lootTableItem(block).apply(List.of(2, 3), i -> SetItemCountFunction
							.setCount(ConstantValue.exactly((float) i.intValue()))
							.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties()
									.hasProperty(BananaFrondBlock.SIZE, i.intValue())))))))
			);

			this.dropSelf(VANILLA_VINE.get());
			this.dropSelf(VANILLA_VINE_PLANT.get());

			this.dropSelf(BANANA_CRATE.get());
			this.dropSelf(ADZUKI_CRATE.get());
			this.dropSelf(ROASTED_ADZUKI_CRATE.get());
			this.dropSelf(MINT_BASKET.get());
			this.dropSelf(STRAWBERRY_BASKET.get());
			this.dropSelf(WHITE_STRAWBERRY_BASKET.get());
			this.dropSelf(VANILLA_POD_BLOCK.get());
			this.dropSelf(DRIED_VANILLA_POD_BLOCK.get());

			this.dropPottedContents(POTTED_MINT.get());
			this.dropPottedContents(POTTED_VANILLA_VINE.get());
			this.dropPottedContents(POTTED_BANANA_FROND.get());

			this.dropSelf(CHIMPANZEE_HEAD.get());

			FlavoredCandleCakeBlock.getCandleCakes().forEach((block -> this.add(block, createCandleCakeDrops(block.getCandle()))));
		}

		protected LootTable.Builder createStrawberryDrops(Block block) {
			LootItemBlockStatePropertyCondition.Builder condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
					.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryBushBlock.AGE, 6).hasProperty(StrawberryBushBlock.WHITE, false));

			LootItemBlockStatePropertyCondition.Builder whiteCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
					.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryBushBlock.AGE, 6).hasProperty(StrawberryBushBlock.WHITE, true));

			return this.applyExplosionDecay(block, LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(AlternativesEntry.alternatives(
							LootItem.lootTableItem(NeapolitanItems.STRAWBERRIES.get()).when(condition),
							LootItem.lootTableItem(NeapolitanItems.WHITE_STRAWBERRIES.get()).when(whiteCondition)))
					)
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(NeapolitanItems.STRAWBERRY_PIPS.get())))
			);
		}

		protected LootTable.Builder createMintDrops(Block block) {
			return LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(NeapolitanItems.MINT_LEAVES.get())
							.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MintBlock.AGE, 4)))
							.apply(List.of(2, 3, 4), i -> SetItemCountFunction.setCount(ConstantValue.exactly((float) i.intValue()))
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties()
											.hasProperty(MintBlock.SPROUTS, i.intValue()))))))

					).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(NeapolitanItems.MINT_SPROUT.get())
							.apply(List.of(2, 3, 4), i -> SetItemCountFunction.setCount(ConstantValue.exactly((float) i.intValue()))
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MintBlock.SPROUTS, i.intValue()))))))
					);
		}

		protected LootTable.Builder createAdzukiDrops(Block block) {
			LootItemBlockStatePropertyCondition.Builder condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
					.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AdzukiSproutsBlock.AGE, 6).hasProperty(AdzukiSproutsBlock.FLOWERING, true));

			return this.applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(NeapolitanItems.ADZUKI_BEANS.get())
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
							.when(condition)
							.otherwise(LootItem.lootTableItem(NeapolitanItems.ADZUKI_BEANS.get()))))
			);
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(Neapolitan.MOD_ID)).collect(Collectors.toSet());
		}
	}

	private static class NeapolitanEntityLoot extends EntityLootSubProvider {

		protected NeapolitanEntityLoot(Provider provider) {
			super(FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.add(NeapolitanEntityTypes.CHIMPANZEE.get(), LootTable.lootTable());
			this.add(NeapolitanEntityTypes.PLANTAIN_SPIDER.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.STRING)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.SPIDER_EYE)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
					).when(LootItemKilledByPlayerCondition.killedByPlayer()))
			);
		}

		@Override
		public Stream<EntityType<?>> getKnownEntityTypes() {
			return BuiltInRegistries.ENTITY_TYPE.stream().filter(entity -> BuiltInRegistries.ENTITY_TYPE.getKey(entity).getNamespace().equals(Neapolitan.MOD_ID));
		}
	}

	private record NeapolitanArchaeologyLoot(Provider provider) implements LootTableSubProvider {

		@Override
		public void generate(BiConsumer<ResourceKey<LootTable>, Builder> consumer) {
			consumer.accept(NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_COMMON, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(NeapolitanItems.DRIED_BANANA.get()).setWeight(2))
					.add(LootItem.lootTableItem(Items.FERMENTED_SPIDER_EYE).setWeight(2))
					.add(LootItem.lootTableItem(NeapolitanBlocks.BANANA_FROND.get()))
					.add(LootItem.lootTableItem(Items.COCOA_BEANS))
					.add(LootItem.lootTableItem(Items.SPIDER_EYE))
					.add(LootItem.lootTableItem(Items.STRING))
					.add(LootItem.lootTableItem(Items.BONE))
					.add(LootItem.lootTableItem(Items.STICK))));
			consumer.accept(NeapolitanLootTables.BANANA_PLANT_ARCHAEOLOGY_RARE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(NeapolitanItems.REFLECTION_POTTERY_SHERD.get()).setWeight(2))
					.add(LootItem.lootTableItem(NeapolitanItems.SCREAM_POTTERY_SHERD.get()).setWeight(2))
					.add(LootItem.lootTableItem(NeapolitanItems.SPIDER_POTTERY_SHERD.get()).setWeight(2))
					.add(LootItem.lootTableItem(NeapolitanItems.SNACK_POTTERY_SHERD.get()).setWeight(2))
					.add(LootItem.lootTableItem(NeapolitanItems.PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
					.add(LootItem.lootTableItem(NeapolitanItems.CHIMPANZEE_HEAD.get()))));

		}
	}
}