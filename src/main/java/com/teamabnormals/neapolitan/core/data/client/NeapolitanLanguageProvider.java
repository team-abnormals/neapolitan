package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class NeapolitanLanguageProvider extends LanguageProvider {
	public NeapolitanLanguageProvider(DataGenerator gen) {
		super(gen, Neapolitan.MOD_ID, "en_us");
	}

	@Override
	public void addTranslations() {
		FlavoredCandleCakeBlock.getCandleCakes().forEach(this::addCandleCake);
	}

	private void addCandleCake(Block block) {
		if (block instanceof FlavoredCandleCakeBlock candleCakeBlock) {
			this.add(block, format(candleCakeBlock.getCake().getRegistryName()) + " with " + format(candleCakeBlock.getCandle().getRegistryName()));
		}
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}