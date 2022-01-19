package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NeapolitanItemTagsProvider extends ItemTagsProvider {

	public NeapolitanItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, ExistingFileHelper helper) {
		super(generator, provider, Neapolitan.MOD_ID, helper);
	}

	@Override
	public void addTags() {
		this.tag(ItemTags.ARROWS).add(NeapolitanItems.BANANARROW.get());
		this.tag(ItemTags.FOX_FOOD).add(NeapolitanItems.STRAWBERRIES.get(), NeapolitanItems.WHITE_STRAWBERRIES.get());
		this.tag(ItemTags.MUSIC_DISCS).add(NeapolitanItems.MUSIC_DISC_HULLABALOO.get());
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);

		this.tag(NeapolitanItemTags.CHIMPANZEE_APE_MODE_ITEMS).addOptional(NeapolitanConstants.WARM_MONKEY_BRUSH).addOptional(NeapolitanConstants.HOT_MONKEY_BRUSH).addOptional(NeapolitanConstants.SCALDING_MONKEY_BRUSH);
		this.tag(NeapolitanItemTags.CHIMPANZEE_FAVORITES).add(Items.STICK, Items.BAMBOO, NeapolitanBlocks.SMALL_BANANA_FROND.get().asItem(), NeapolitanBlocks.BANANA_FROND.get().asItem(), NeapolitanBlocks.LARGE_BANANA_FROND.get().asItem());
		this.tag(NeapolitanItemTags.CHIMPANZEE_FOOD).add(NeapolitanItems.BANANA_BUNCH.get());
		this.tag(NeapolitanItemTags.CHIMPANZEE_SNACKS).add(NeapolitanItems.BANANA.get(), NeapolitanItems.BANANA_BUNCH.get(), NeapolitanItems.BANANA_BREAD.get(), NeapolitanItems.DRIED_BANANA.get(), NeapolitanItems.BANANARROW.get());
		this.tag(NeapolitanItemTags.HIDES_CHIMPANZEE_EARS).addTag(Tags.Items.HEADS).addOptional(NeapolitanConstants.GRIEFER_HELMET).addOptional(NeapolitanConstants.SANGUINE_HELMET);
		this.tag(NeapolitanItemTags.ICE_CREAM).add(NeapolitanItems.CHOCOLATE_ICE_CREAM.get(), NeapolitanItems.VANILLA_ICE_CREAM.get(), NeapolitanItems.STRAWBERRY_ICE_CREAM.get(), NeapolitanItems.BANANA_ICE_CREAM.get(), NeapolitanItems.MINT_ICE_CREAM.get(), NeapolitanItems.ADZUKI_ICE_CREAM.get(), NeapolitanItems.NEAPOLITAN_ICE_CREAM.get());

		this.tag(NeapolitanItemTags.BOTTLES).addTag(NeapolitanItemTags.BOTTLES_MILK);
		this.tag(NeapolitanItemTags.BOTTLES_MILK).add(NeapolitanItems.MILK_BOTTLE.get());
		this.tag(Tags.Items.SEEDS).addTag(NeapolitanItemTags.SEEDS_STRAWBERRY);
		this.tag(NeapolitanItemTags.SEEDS_STRAWBERRY).add(NeapolitanItems.STRAWBERRY_PIPS.get());
		this.tag(NeapolitanItemTags.FRUITS).addTag(NeapolitanItemTags.FRUITS_STRAWBERRY).addTag(NeapolitanItemTags.FRUITS_BANANA);
		this.tag(NeapolitanItemTags.FRUITS_STRAWBERRY).add(NeapolitanItems.STRAWBERRIES.get());
		this.tag(NeapolitanItemTags.FRUITS_BANANA).add(NeapolitanItems.BANANA.get());
		this.tag(NeapolitanItemTags.ICE_CUBES).add(NeapolitanItems.ICE_CUBES.get());
		this.tag(NeapolitanItemTags.PUMPKINS).add(Items.PUMPKIN);
		this.tag(BlueprintItemTags.BUCKETS_MILK);
		this.tag(NeapolitanItemTags.MILK).addTag(BlueprintItemTags.BUCKETS_MILK).addTag(NeapolitanItemTags.BOTTLES_MILK);

		this.copy(BlueprintBlockTags.VERTICAL_SLABS, BlueprintItemTags.VERTICAL_SLABS);
	}
}