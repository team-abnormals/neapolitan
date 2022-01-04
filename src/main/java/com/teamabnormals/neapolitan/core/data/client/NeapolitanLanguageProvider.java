package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.data.server.tags.NeapolitanBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class NeapolitanLanguageProvider extends LanguageProvider {
	public NeapolitanLanguageProvider(DataGenerator gen) {
		super(gen, Neapolitan.MOD_ID, "en_us");
	}

	@Override
	public void addTranslations() {
		NeapolitanBlockTagsProvider.getCandleCakes().forEach(this::add);
	}

	private void add(Item item) {
		if (item.getRegistryName() != null)
			this.add(item, format(item.getRegistryName()));
	}

	private void add(Block block) {
		if (block.getRegistryName() != null)
			this.add(block, format(block.getRegistryName()));
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}