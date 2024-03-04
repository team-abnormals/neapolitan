package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;

public class NeapolitanItemTagsProvider extends ItemTagsProvider {

	public NeapolitanItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
		super(output, provider, lookup, Neapolitan.MOD_ID, helper);
	}

	@Override
	public void addTags(HolderLookup.Provider provider) {
		this.tag(ItemTags.ARROWS).add(BANANARROW.get());
		this.tag(ItemTags.FOX_FOOD).add(STRAWBERRIES.get(), WHITE_STRAWBERRIES.get());
		this.tag(ItemTags.MUSIC_DISCS).add(MUSIC_DISC_HULLABALOO.get());
		this.tag(ItemTags.DECORATED_POT_SHERDS).add(REFLECTION_POTTERY_SHERD.get(), ANGER_POTTERY_SHERD.get(), SPIDER_POTTERY_SHERD.get(), BANANA_POTTERY_SHERD.get());
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);

		this.tag(NeapolitanItemTags.CHIMPANZEE_APE_MODE_ITEMS).addOptional(NeapolitanConstants.WARM_MONKEY_BRUSH).addOptional(NeapolitanConstants.HOT_MONKEY_BRUSH).addOptional(NeapolitanConstants.SCALDING_MONKEY_BRUSH);
		this.tag(NeapolitanItemTags.CHIMPANZEE_FAVORITES).add(Items.STICK, Items.BAMBOO, BANANA_FROND.get());
		this.tag(NeapolitanItemTags.CHIMPANZEE_FOOD).add(BANANA_BUNCH.get());
		this.tag(NeapolitanItemTags.CHIMPANZEE_SNACKS).add(BANANA.get(), BANANA_BUNCH.get(), BANANA_BREAD.get(), DRIED_BANANA.get(), BANANARROW.get(), Items.POTION);
		this.tag(NeapolitanItemTags.HIDES_CHIMPANZEE_EARS).addTag(Tags.Items.HEADS).addOptional(NeapolitanConstants.GRIEFER_HELMET).addOptional(NeapolitanConstants.SANGUINE_HELMET);
		this.tag(NeapolitanItemTags.ICE_CREAM).add(CHOCOLATE_ICE_CREAM.get(), VANILLA_ICE_CREAM.get(), STRAWBERRY_ICE_CREAM.get(), BANANA_ICE_CREAM.get(), MINT_ICE_CREAM.get(), ADZUKI_ICE_CREAM.get(), NEAPOLITAN_ICE_CREAM.get());

		this.tag(NeapolitanItemTags.BOTTLES).addTag(NeapolitanItemTags.BOTTLES_MILK);
		this.tag(NeapolitanItemTags.BOTTLES_MILK).add(MILK_BOTTLE.get());
		this.tag(Tags.Items.SEEDS).addTag(NeapolitanItemTags.SEEDS_STRAWBERRY);
		this.tag(NeapolitanItemTags.SEEDS_STRAWBERRY).add(STRAWBERRY_PIPS.get());
		this.tag(NeapolitanItemTags.FRUITS).addTag(NeapolitanItemTags.FRUITS_STRAWBERRY).addTag(NeapolitanItemTags.FRUITS_BANANA);
		this.tag(NeapolitanItemTags.FRUITS_STRAWBERRY).add(STRAWBERRIES.get());
		this.tag(NeapolitanItemTags.FRUITS_BANANA).add(BANANA.get());
		this.tag(NeapolitanItemTags.ICE_CUBES).add(ICE_CUBES.get());
		this.tag(BlueprintItemTags.MILK).addTag(NeapolitanItemTags.BOTTLES_MILK);
		this.tag(Tags.Items.HEADS).add(CHIMPANZEE_HEAD.get());
		this.tag(BlueprintItemTags.CHICKEN_FOOD).add(STRAWBERRY_PIPS.get());
	}
}