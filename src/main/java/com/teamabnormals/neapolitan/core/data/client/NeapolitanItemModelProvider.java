package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanItems.*;

public class NeapolitanItemModelProvider extends ItemModelProvider {

	public NeapolitanItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Neapolitan.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		this.generatedItem(
				PRIMAL_ARMOR_TRIM_SMITHING_TEMPLATE.get(),
				REFLECTION_POTTERY_SHERD.get(), ANGER_POTTERY_SHERD.get(), SPIDER_POTTERY_SHERD.get(), BANANA_POTTERY_SHERD.get()
		);
	}

	private void generatedItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "generated");
	}

	private void handheldItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "handheld");
	}

	private void item(ItemLike item, String type) {
		ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
		withExistingParent(itemName.getPath(), "item/" + type).texture("layer0", new ResourceLocation(this.modid, "item/" + itemName.getPath()));
	}

	private void spawnEggItem(ItemLike... items) {
		for (ItemLike item : items) {
			ResourceLocation itemName = ForgeRegistries.ITEMS.getKey(item.asItem());
			withExistingParent(itemName.getPath(), "item/template_spawn_egg");
		}
	}

	private void blockItem(Block block) {
		ResourceLocation name = ForgeRegistries.BLOCKS.getKey(block);
		this.getBuilder(name.getPath()).parent(new UncheckedModelFile(new ResourceLocation(this.modid, "block/" + name.getPath())));
	}
}