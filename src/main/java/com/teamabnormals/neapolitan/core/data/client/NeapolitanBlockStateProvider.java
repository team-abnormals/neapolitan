package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanBlockFamilies;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Function;

import static com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks.*;


public class NeapolitanBlockStateProvider extends BlueprintBlockStateProvider {

	public NeapolitanBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Neapolitan.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.block(WAFFLE_CONE_BLOCK);
		this.logBlock(WAFFLE_CONE_PILLAR);
		this.blockFamily(NeapolitanBlockFamilies.WAFFLE_CONE_TILE_FAMILY);

		this.block(ICE_CREAM_BLOCK);
		this.logBlock(SUGAR_CANE_BLOCK);
		this.directionalBlock(SUGAR_SACK);
		this.directionalBlock(COCOA_BEAN_SACK);

		this.block(MANGO_ICE_CREAM_BLOCK);
		this.block(CINNAMON_ICE_CREAM_BLOCK);
		this.block(BUBBLEGUM_ICE_CREAM_BLOCK);

		this.cake(MANGO_CAKE);
		this.cake(CINNAMON_CAKE);
		this.cake(BUBBLEGUM_CAKE);

		FlavoredCandleCakeBlock.getCandleCakes().forEach(this::candleCake);
	}

	public void cake(DeferredHolder<Block, ?> block) {
		ModelFile base = cakeModel(block.get(), "", "block/cake");
		this.getVariantBuilder(block.get()).forAllStates(state -> {
			int bites = state.getValue(CakeBlock.BITES);
			return ConfiguredModel.builder().modelFile(bites == 0 ? base : cakeModel(block.get(), "_slice" + bites, "block/cake_slice" + bites)).build();
		});
	}

	public ModelFile cakeModel(Block block, String suffix, String parent) {
		return models().withExistingParent(name(block) + suffix, parent)
				.texture("bottom", suffix(blockTexture(block), "_bottom"))
				.texture("side", suffix(blockTexture(block), "_side"))
				.texture("top", suffix(blockTexture(block), "_top"))
				.texture("inside", suffix(blockTexture(block), "_top"))
				.texture("particle", suffix(blockTexture(block), "_side"));
	}

	public void candleCake(FlavoredCandleCakeBlock block) {
		Block candle = block.getCandle();
		Block cake = block.getCake();

		ModelFile candleCake = models().withExistingParent(name(block), "block/template_cake_with_candle")
				.texture("candle", blockTexture(candle))
				.texture("bottom", suffix(blockTexture(cake), "_bottom"))
				.texture("side", suffix(blockTexture(cake), "_side"))
				.texture("top", suffix(blockTexture(cake), "_top"))
				.texture("particle", suffix(blockTexture(cake), "_side"));

		ModelFile candleCakeLit = models().withExistingParent(name(block) + "_lit", "block/template_cake_with_candle")
				.texture("candle", suffix(blockTexture(candle), "_lit"))
				.texture("bottom", suffix(blockTexture(cake), "_bottom"))
				.texture("side", suffix(blockTexture(cake), "_side"))
				.texture("top", suffix(blockTexture(cake), "_top"))
				.texture("particle", suffix(blockTexture(cake), "_side"));

		this.candleCakeBlock(block, (state -> state.getValue(BlockStateProperties.LIT) ? candleCakeLit : candleCake));
	}

	public void candleCakeBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).build());
	}
}