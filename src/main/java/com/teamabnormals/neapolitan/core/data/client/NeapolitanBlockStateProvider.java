package com.teamabnormals.neapolitan.core.data.client;

import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import com.teamabnormals.neapolitan.common.block.FlavoredCandleCakeBlock;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanBlockFamilies;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
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
		this.logBlock(CINNAMON_STICK_BLOCK);
		this.directionalBlock(SUGAR_SACK);
		this.directionalBlock(COCOA_BEAN_SACK);
		this.directionalBlock(MANGO_CRATE);

		this.logBlock(CINNAMON_STALK);

		this.block(MANGO_ICE_CREAM_BLOCK);
		this.block(CINNAMON_ICE_CREAM_BLOCK);
		this.block(BUBBLEGUM_ICE_CREAM_BLOCK);

		this.cake(MANGO_CAKE);
		this.cake(CINNAMON_CAKE);
		this.cake(BUBBLEGUM_CAKE);

		this.blockFamily(NeapolitanBlockFamilies.KOA_PLANKS_FAMILY);

		this.logBlocks(KOA_LOG, KOA_WOOD);
		this.logBlocks(STRIPPED_KOA_LOG, STRIPPED_KOA_WOOD);
		this.hangingSignBlocks(STRIPPED_KOA_LOG, KOA_HANGING_SIGNS.getFirst(), KOA_HANGING_SIGNS.getSecond());
		this.leavesBlocks(KOA_LEAVES, KOA_LEAF_PILE);
		this.crossBlockWithPot(KOA_SAPLING, POTTED_KOA_SAPLING);
		this.woodworksBlocks(KOA_PLANKS, KOA_BOARDS, KOA_LADDER, KOA_BOOKSHELF, KOA_BEEHIVE, KOA_CHEST, TRAPPED_KOA_CHEST);
		this.chiseledBookshelfBlock(CHISELED_KOA_BOOKSHELF);

		this.thatchBlocks(CINNAMON_THATCH, CINNAMON_THATCH_STAIRS, CINNAMON_THATCH_SLAB);
		this.thatchBlocks(FLOWERING_CINNAMON_THATCH, FLOWERING_CINNAMON_THATCH_STAIRS, FLOWERING_CINNAMON_THATCH_SLAB);

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

	private void thatchBlocks(DeferredHolder<Block, ?> thatch, DeferredHolder<Block, ?> thatchStairs, DeferredHolder<Block, ?> thatchSlab) {
		this.thatchBlock(thatch);
		this.thatchStairsBlock(thatchStairs, blockTexture(thatch.get()));
		this.thatchSlabBlock(thatchSlab, blockTexture(thatch.get()));
	}

	private void thatchBlock(DeferredHolder<Block, ?> thatch) {
		String name = name(thatch.get());
		ResourceLocation texture = this.blockTexture(thatch.get());
		ResourceLocation extrudes = this.modLoc("block/" + name + "_extrudes");
		this.simpleBlock(thatch.get(), this.models().withExistingParent(name, Blueprint.location("block/template_thatch")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout"));
		this.blockItem(thatch);
	}

	private void thatchSlabBlock(DeferredHolder<Block, ?> thatchSlab, ResourceLocation texture) {
		String name = name(thatchSlab.get());
		ResourceLocation extrudes = ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), texture.getPath() + "_extrudes");
		ModelFile bottom = this.models().withExistingParent(name, Blueprint.location("block/template_thatch_slab")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
		ModelFile top = this.models().withExistingParent(name + "_top", Blueprint.location("block/template_thatch_slab_top")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
		this.slabBlock((SlabBlock) thatchSlab.get(), bottom, top, this.models().getExistingFile(texture));
		this.blockItem(thatchSlab);
	}

	private void thatchStairsBlock(DeferredHolder<Block, ?> thatch, ResourceLocation texture) {
		String name = name(thatch.get());
		ResourceLocation extrudes = ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), texture.getPath() + "_extrudes");

		ModelFile stairs = this.models().withExistingParent(name, Blueprint.location("block/template_thatch_stairs")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
		ModelFile inner = this.models().withExistingParent(name + "_inner", Blueprint.location("block/template_thatch_stairs_inner")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
		ModelFile outer = this.models().withExistingParent(name + "_outer", Blueprint.location("block/template_thatch_stairs_outer")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
		ModelFile inner_top = this.models().withExistingParent(name + "_inner_top", Blueprint.location("block/template_thatch_stairs_inner_top")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
		ModelFile outer_top = this.models().withExistingParent(name + "_outer_top", Blueprint.location("block/template_thatch_stairs_outer_top")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");
		ModelFile top = this.models().withExistingParent(name + "_top", Blueprint.location("block/template_thatch_stairs_top")).texture("thatch", texture).texture("extrudes", extrudes).renderType("cutout");

		this.blockItem(thatch);
		this.getVariantBuilder(thatch.get()).forAllStatesExcept(state -> {
			Direction facing = state.getValue(StairBlock.FACING);
			Half half = state.getValue(StairBlock.HALF);
			StairsShape shape = state.getValue(StairBlock.SHAPE);
			int yRot = (int) facing.getClockWise().toYRot();

			if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) yRot += 270;
			if (half == Half.TOP && shape == StairsShape.STRAIGHT) yRot += 180;
			if (half == Half.TOP && (shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT))
				yRot += 90;

			yRot %= 360;

			return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT ? (half == Half.BOTTOM ? stairs : top)
					: shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? (half == Half.BOTTOM ? inner : inner_top)
					: (half == Half.BOTTOM ? outer : outer_top)).rotationY(yRot).uvLock(true).build();
		}, StairBlock.WATERLOGGED);
	}
}