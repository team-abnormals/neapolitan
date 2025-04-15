package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class NeapolitanLanguageProvider extends LanguageProvider {

	public NeapolitanLanguageProvider(PackOutput output) {
		super(output, Neapolitan.MOD_ID, "en_us");
	}

	@Override
	public void addTranslations() {
		FlavoredCandleCakeBlock.getCandleCakes().forEach(this::addCandleCake);
	}

	private void addCandleCake(Block block) {
		if (block instanceof FlavoredCandleCakeBlock candleCakeBlock) {
			this.add(block, format(BuiltInRegistries.BLOCK.getKey(candleCakeBlock.getCake())) + " with " + format(BuiltInRegistries.BLOCK.getKey(candleCakeBlock.getCandle())));
		}
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}