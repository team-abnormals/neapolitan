package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags.*;
import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;

public class NeapolitanItemTagsProvider extends ItemTagsProvider {

	public NeapolitanItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
		super(output, provider, lookup, Neapolitan.MOD_ID, helper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.tag(ItemTags.ARROWS).add(BANANARROW.get());
		this.tag(Tags.Items.MUSIC_DISCS).add(MUSIC_DISC_HULLABALOO.get());
		this.tag(ItemTags.DECORATED_POT_SHERDS).add(REFLECTION_POTTERY_SHERD.get(), SCREAM_POTTERY_SHERD.get(), SPIDER_POTTERY_SHERD.get(), SNACK_POTTERY_SHERD.get());
		this.tag(ItemTags.TRIM_TEMPLATES).add(PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.get());
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BlueprintBlockTags.NOTE_BLOCK_TOP_INSTRUMENTS, ItemTags.NOTE_BLOCK_TOP_INSTRUMENTS);
		this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

		this.tag(CHIMPANZEE_APE_MODE_ITEMS).addOptional(NeapolitanConstants.WARM_MONKEY_BRUSH).addOptional(NeapolitanConstants.HOT_MONKEY_BRUSH).addOptional(NeapolitanConstants.SCALDING_MONKEY_BRUSH);
		this.tag(CHIMPANZEE_FAVORITES).add(NeapolitanBlocks.BANANA_FROND.asItem()).addTag(CHIMPANZEE_WEAPONS);
		this.tag(CHIMPANZEE_WEAPONS).add(Items.STICK, Items.BAMBOO);
		this.tag(CHIMPANZEE_FOOD).add(BANANA_BUNCH.get());
		this.tag(CHIMPANZEE_SNACKS).add(BANANA.get(), BANANA_BUNCH.get(), BANANA_BREAD.get(), DRIED_BANANA.get(), BANANARROW.get(), Items.POTION);
		this.tag(HIDES_CHIMPANZEE_EARS).addOptional(NeapolitanConstants.GRIEFER_HELMET).addOptional(NeapolitanConstants.SANGUINE_HELMET);

		this.tag(ICE_CREAM_INGREDIENTS).addTags(ICE_CREAM_FLAVORS, ICE_CREAM_SWEETENERS, ICE_CREAM_CONTAINERS, Tags.Items.DRINKS_MILK);
		this.tag(ICE_CREAM_FLAVORS).add(DRIED_VANILLA_PODS.get(), CHOCOLATE_BAR.get(), STRAWBERRIES.get(), BANANA.get(), MINT_LEAVES.get(), ROASTED_ADZUKI_BEANS.get());
		this.tag(ICE_CREAM_SWEETENERS).add(Items.SUGAR);
		this.tag(ICE_CREAM_CONTAINERS).add(Items.BOWL, WAFFLE_CONE.get());

		this.tag(ItemTags.CHICKEN_FOOD).add(STRAWBERRY_PIPS.get());
		this.tag(ItemTags.PARROT_FOOD).add(STRAWBERRY_PIPS.get());
		this.tag(ItemTags.FOX_FOOD).add(STRAWBERRIES.get(), WHITE_STRAWBERRIES.get());
		this.tag(ItemTags.MEAT).add(MINT_CHOPS.get(), COOKED_MINT_CHOPS.get());

		this.tag(Tags.Items.SEEDS).addTag(SEEDS_STRAWBERRY);
		this.tag(SEEDS_STRAWBERRY).add(STRAWBERRY_PIPS.get());

		this.tag(Tags.Items.FOODS).addTags(FOODS_ICE_CREAM, FOODS_PASTRY).add(
				NeapolitanItems.ICE_CUBES.get(),
				WHITE_STRAWBERRIES.get(), CHOCOLATE_STRAWBERRIES.get(),
				DRIED_VANILLA_PODS.get(), VANILLA_FUDGE.get(), VANILLA_PUDDING.get(),
				DRIED_BANANA.get(), VANILLA_CHOCOLATE_FINGERS.get(),
				MINT_LEAVES.get(), ROASTED_ADZUKI_BEANS.get(), ADZUKI_CURRY.get()
		);

		this.tag(Tags.Items.FOODS_EDIBLE_WHEN_PLACED).addTags(FOODS_CAKE);
		this.tag(Tags.Items.FOODS_FRUIT).addTag(FOODS_STRAWBERRY).addTag(FOODS_BANANA);
		this.tag(Tags.Items.FOODS_RAW_MEAT).add(MINT_CHOPS.get());
		this.tag(Tags.Items.FOODS_COOKED_MEAT).add(COOKED_MINT_CHOPS.get());
		this.tag(Tags.Items.FOODS_FOOD_POISONING).add(CHOCOLATE_SPIDER_EYE.get());
		this.tag(Tags.Items.FOODS_CANDY).add(CHOCOLATE_BAR.get(), MINT_CHOCOLATE.get(), MINT_CANDIES.get(), STRAWBERRY_BEAN_BONBONS.get());
		this.tag(Tags.Items.FOODS_SOUP).add(ADZUKI_STEW.get());

		this.tag(FOODS_STRAWBERRY).add(STRAWBERRIES.get());
		this.tag(FOODS_BANANA).add(BANANA.get());
		this.tag(FOODS_CHOCOLATE_BAR).add(CHOCOLATE_BAR.get());
		this.tag(FOODS_ICE_CREAM).add(ICE_CREAM.get(), ICE_CREAM_CONE.get());
		this.tag(FOODS_CAKE).add(CHOCOLATE_CAKE.get(), VANILLA_CAKE.get(), STRAWBERRY_CAKE.get(), BANANA_CAKE.get(), MINT_CAKE.get(), ADZUKI_CAKE.get());
		this.tag(FOODS_PASTRY).add(STRAWBERRY_SCONES.get(), WAFFLE_CONE.get(), BANANA_BREAD.get(), ADZUKI_BUN.get());

		this.tag(Tags.Items.DRINKS).addTags(DRINKS_MILKSHAKE).add(STRAWBERRY_BANANA_SMOOTHIE.get());
		this.tag(Tags.Items.DRINKS_MILK).add(MILK_BOTTLE.get());
		this.tag(DRINKS_MILKSHAKE).add(CHOCOLATE_MILKSHAKE.get(), VANILLA_MILKSHAKE.get(), STRAWBERRY_MILKSHAKE.get(), BANANA_MILKSHAKE.get(), MINT_MILKSHAKE.get(), ADZUKI_MILKSHAKE.get());

		this.tag(NeapolitanItemTags.ICE_CUBES).add(NeapolitanItems.ICE_CUBES.get());
		this.tag(Tags.Items.ANIMAL_FOODS).addTag(CHIMPANZEE_FOOD);
	}
}