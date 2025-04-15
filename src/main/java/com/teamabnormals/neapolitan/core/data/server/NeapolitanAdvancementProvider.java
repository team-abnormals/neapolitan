package com.teamabnormals.neapolitan.core.data.server;

import com.teamabnormals.neapolitan.common.block.StrawberryBushBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanCriteriaTriggers;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DefaultBlockInteractionTrigger;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class NeapolitanAdvancementProvider extends AdvancementProvider {
	@SuppressWarnings("unchecked")
	private static final DeferredBlock<Block>[] CHOCOLATE_BLOCKS = new DeferredBlock[]{
			NeapolitanBlocks.CHOCOLATE_BLOCK,
			NeapolitanBlocks.CHOCOLATE_BRICKS, NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS, NeapolitanBlocks.CHOCOLATE_BRICK_SLAB, NeapolitanBlocks.CHOCOLATE_BRICK_WALL,
			NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS,
			NeapolitanBlocks.CHOCOLATE_TILES, NeapolitanBlocks.CHOCOLATE_TILE_STAIRS, NeapolitanBlocks.CHOCOLATE_TILE_SLAB, NeapolitanBlocks.CHOCOLATE_TILE_WALL
	};

	public NeapolitanAdvancementProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, helper, List.of(new NeapolitanAdvancementGenerator()));
	}

	public static class NeapolitanAdvancementGenerator implements AdvancementGenerator {

		@Override
		public void generate(Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
			advancementBuilder("chimpanzee_attack", "adventure", ResourceLocation.withDefaultNamespace("adventure/shoot_arrow"), NeapolitanItems.BANANARROW.get(), AdvancementType.TASK, true, true, false)
					.addCriterion("chimpanzee_attack", NeapolitanCriteriaTriggers.attackedWithChimpanzees())
					.save(consumer, Neapolitan.MOD_ID + ":adventure/chimpanzee_attack");

			AdvancementHolder advancement = advancementBuilder("creeper_heal", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/plant_seed"), NeapolitanItems.STRAWBERRIES.get(), AdvancementType.TASK, true, true, false)
					.addCriterion("creeper_heal", NeapolitanCriteriaTriggers.healedFromCreeper())
					.save(consumer, Neapolitan.MOD_ID + ":husbandry/creeper_heal");

			advancementBuilder("vanilla_poison", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/plant_seed"), NeapolitanItems.VANILLA_PODS.get(), AdvancementType.TASK, true, true, false)
					.addCriterion("vanilla_poison", NeapolitanCriteriaTriggers.vanillaVineDestroyedWithShears())
					.save(consumer, Neapolitan.MOD_ID + ":husbandry/vanilla_poison");

			advancementBuilder("harvest_white_strawberries", "husbandry", advancement.id(), NeapolitanItems.WHITE_STRAWBERRIES.get(), AdvancementType.TASK, true, true, false)
					.addCriterion("harvest_white_strawberries", CriteriaTriggers.DEFAULT_BLOCK_USE.createCriterion(new DefaultBlockInteractionTrigger.TriggerInstance(
							Optional.empty(),
							Optional.of(ContextAwarePredicate.create(
									LootItemBlockStatePropertyCondition.hasBlockStateProperties(NeapolitanBlocks.STRAWBERRY_BUSH.get())
											.setProperties(StatePropertiesPredicate.Builder.properties()
													.hasProperty(StrawberryBushBlock.WHITE, true)
													).build())))
					))
					.save(consumer, Neapolitan.MOD_ID + ":husbandry/harvest_white_strawberries");

			Advancement.Builder chocolateBuilder = advancementBuilder("place_all_chocolate_blocks", "husbandry", ResourceLocation.withDefaultNamespace("husbandry/plant_seed"), NeapolitanItems.CHOCOLATE_BAR.get(), AdvancementType.CHALLENGE, true, true, false);
			for (DeferredBlock<Block> block : CHOCOLATE_BLOCKS) {
				chocolateBuilder.addCriterion(block.getId().toString(), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(block.get()));
			}
			chocolateBuilder.save(consumer, Neapolitan.MOD_ID + ":husbandry/place_all_chocolate_blocks");

		}

		private static Advancement.Builder advancementBuilder(String name, String category, ResourceLocation parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
			return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon,
					Component.translatable("advancements." + Neapolitan.MOD_ID + "." + category + "." + name + ".title"),
					Component.translatable("advancements." + Neapolitan.MOD_ID + "." + category + "." + name + ".description"),
					null, frame, showToast, announceToChat, hidden);
		}
	}
}