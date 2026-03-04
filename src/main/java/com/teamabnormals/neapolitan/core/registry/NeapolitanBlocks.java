package com.teamabnormals.neapolitan.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.*;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchStairBlock;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.neapolitan.common.block.*;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanCauldronInteractions;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems.NeapolitanFoods;
import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents.NeapolitanSoundTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class NeapolitanBlocks {
	public static final BlockSubRegistryHelper BLOCKS = Neapolitan.REGISTRY_HELPER.getBlockSubHelper();

	public static final DeferredBlock<Block> CHILLBOX = BLOCKS.createBlock("chillbox", () -> new ChillboxBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

	public static final DeferredBlock<Block> ICE_CREAM_BLOCK = BLOCKS.createBlock("ice_cream_block", () -> new Block(NeapolitanBlockProperties.ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> VANILLA_ICE_CREAM_BLOCK = BLOCKS.createBlock("vanilla_ice_cream_block", () -> new Block(NeapolitanBlockProperties.VANILLA_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> CHOCOLATE_ICE_CREAM_BLOCK = BLOCKS.createBlock("chocolate_ice_cream_block", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> STRAWBERRY_ICE_CREAM_BLOCK = BLOCKS.createBlock("strawberry_ice_cream_block", () -> new Block(NeapolitanBlockProperties.STRAWBERRY_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> BANANA_ICE_CREAM_BLOCK = BLOCKS.createBlock("banana_ice_cream_block", () -> new Block(NeapolitanBlockProperties.BANANA_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> MINT_ICE_CREAM_BLOCK = BLOCKS.createBlock("mint_ice_cream_block", () -> new Block(NeapolitanBlockProperties.MINT_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> ADZUKI_ICE_CREAM_BLOCK = BLOCKS.createBlock("adzuki_ice_cream_block", () -> new Block(NeapolitanBlockProperties.ADZUKI_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> MANGO_ICE_CREAM_BLOCK = BLOCKS.createBlock("mango_ice_cream_block", () -> new Block(NeapolitanBlockProperties.MANGO_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> CINNAMON_ICE_CREAM_BLOCK = BLOCKS.createBlock("cinnamon_ice_cream_block", () -> new Block(NeapolitanBlockProperties.CINNAMON_ICE_CREAM_BLOCK));
	public static final DeferredBlock<Block> BUBBLEGUM_ICE_CREAM_BLOCK = BLOCKS.createBlock("bubblegum_ice_cream_block", () -> new Block(NeapolitanBlockProperties.BUBBLEGUM_ICE_CREAM_BLOCK));

	public static final DeferredBlock<Block> WAFFLE_CONE_BLOCK = BLOCKS.createBlock("waffle_cone_block", () -> new Block(NeapolitanBlockProperties.WAFFLE_CONE_BLOCK));
	public static final DeferredBlock<Block> WAFFLE_CONE_TILES = BLOCKS.createBlock("waffle_cone_tiles", () -> new Block(NeapolitanBlockProperties.WAFFLE_CONE_BLOCK));
	public static final DeferredBlock<Block> WAFFLE_CONE_TILE_SLAB = BLOCKS.createBlock("waffle_cone_tile_slab", () -> new SlabBlock(NeapolitanBlockProperties.WAFFLE_CONE_BLOCK));
	public static final DeferredBlock<Block> WAFFLE_CONE_TILE_STAIRS = BLOCKS.createBlock("waffle_cone_tile_stairs", () -> new StairBlock(WAFFLE_CONE_TILES.get().defaultBlockState(), NeapolitanBlockProperties.WAFFLE_CONE_BLOCK));
	public static final DeferredBlock<Block> WAFFLE_CONE_TILE_WALL = BLOCKS.createBlock("waffle_cone_tile_wall", () -> new WallBlock(NeapolitanBlockProperties.WAFFLE_CONE_BLOCK));
	public static final DeferredBlock<Block> WAFFLE_CONE_PILLAR = BLOCKS.createBlock("waffle_cone_pillar", () -> new RotatedPillarBlock(NeapolitanBlockProperties.WAFFLE_CONE_BLOCK));

	public static final DeferredBlock<Block> MILK_CAULDRON = BLOCKS.createBlockNoItem("milk_cauldron", () -> new MilkCauldronBlock(NeapolitanBlockProperties.CAULDRON));
	public static final DeferredBlock<Block> VANILLA_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("vanilla_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.VANILLA_MILKSHAKE));
	public static final DeferredBlock<Block> CHOCOLATE_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("chocolate_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.CHOCOLATE_MILKSHAKE));
	public static final DeferredBlock<Block> STRAWBERRY_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("strawberry_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.STRAWBERRY_MILKSHAKE));
	public static final DeferredBlock<Block> BANANA_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("banana_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.BANANA_MILKSHAKE));
	public static final DeferredBlock<Block> MINT_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("mint_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.MINT_MILKSHAKE));
	public static final DeferredBlock<Block> ADZUKI_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("adzuki_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.ADZUKI_MILKSHAKE));
	public static final DeferredBlock<Block> MANGO_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("mango_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.MANGO_MILKSHAKE));
	public static final DeferredBlock<Block> CINNAMON_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("cinnamon_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.CINNAMON_MILKSHAKE));
	public static final DeferredBlock<Block> BUBBLEGUM_MILKSHAKE_CAULDRON = BLOCKS.createBlockNoItem("bubblegum_milkshake_cauldron", () -> new MilkshakeCauldronBlock(NeapolitanCauldronInteractions.BUBBLEGUM_MILKSHAKE));

	public static final DeferredBlock<Block> CHOCOLATE_BLOCK = BLOCKS.createBlock("chocolate_block", () -> new Block(NeapolitanBlockProperties.CHOCOLATE));

	public static final DeferredBlock<Block> CHOCOLATE_BRICKS = BLOCKS.createBlock("chocolate_bricks", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final DeferredBlock<Block> CHOCOLATE_BRICK_SLAB = BLOCKS.createBlock("chocolate_brick_slab", () -> new SlabBlock(NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final DeferredBlock<Block> CHOCOLATE_BRICK_STAIRS = BLOCKS.createBlock("chocolate_brick_stairs", () -> new StairBlock(CHOCOLATE_BRICKS.get().defaultBlockState(), NeapolitanBlockProperties.CHOCOLATE_BRICKS));
	public static final DeferredBlock<Block> CHOCOLATE_BRICK_WALL = BLOCKS.createBlock("chocolate_brick_wall", () -> new WallBlock(NeapolitanBlockProperties.CHOCOLATE_BRICKS));

	public static final DeferredBlock<Block> CHISELED_CHOCOLATE_BRICKS = BLOCKS.createBlock("chiseled_chocolate_bricks", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_BRICKS));

	public static final DeferredBlock<Block> CHOCOLATE_TILES = BLOCKS.createBlock("chocolate_tiles", () -> new Block(NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final DeferredBlock<Block> CHOCOLATE_TILE_SLAB = BLOCKS.createBlock("chocolate_tile_slab", () -> new SlabBlock(NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final DeferredBlock<Block> CHOCOLATE_TILE_STAIRS = BLOCKS.createBlock("chocolate_tile_stairs", () -> new StairBlock(CHOCOLATE_TILES.get().defaultBlockState(), NeapolitanBlockProperties.CHOCOLATE_TILES));
	public static final DeferredBlock<Block> CHOCOLATE_TILE_WALL = BLOCKS.createBlock("chocolate_tile_wall", () -> new WallBlock(NeapolitanBlockProperties.CHOCOLATE_TILES));

	public static final DeferredBlock<Block> VANILLA_VINE = BLOCKS.createBlockNoItem("vanilla_vine", () -> new VanillaVineTopBlock(NeapolitanBlockProperties.VANILLA_VINE));
	public static final DeferredBlock<Block> VANILLA_VINE_PLANT = BLOCKS.createBlockNoItem("vanilla_vine_plant", () -> new VanillaVineBlock(NeapolitanBlockProperties.VANILLA_VINE));
	public static final DeferredBlock<Block> POTTED_VANILLA_VINE = BLOCKS.createBlockNoItem("potted_vanilla_vine", () -> new FlowerPotBlock(VANILLA_VINE.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> STRAWBERRY_BUSH = BLOCKS.createBlockNoItem("strawberry_bush", () -> new StrawberryBushBlock(NeapolitanBlockProperties.STRAWBERRY_BUSH));
	public static final DeferredBlock<Block> MINT = BLOCKS.createBlockNoItem("mint", () -> new MintBlock(NeapolitanBlockProperties.MINT));
	public static final DeferredBlock<Block> POTTED_MINT = BLOCKS.createBlockNoItem("potted_mint", () -> new FlowerPotBlock(MINT.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> CARVED_BANANA_STALK = BLOCKS.createBlock("carved_banana_stalk", () -> new RotatedPillarBlock(NeapolitanBlockProperties.BANANA_STALK));
	public static final DeferredBlock<Block> BANANA_STALK = BLOCKS.createBlock("banana_stalk", () -> new LogBlock(() -> CARVED_BANANA_STALK.get(), NeapolitanBlockProperties.BANANA_STALK));
	public static final DeferredBlock<Block> BANANA_FROND = BLOCKS.createBlock("banana_frond", () -> new BananaFrondBlock(NeapolitanBlockProperties.BANANA_FROND));
	public static final DeferredBlock<Block> POTTED_BANANA_FROND = BLOCKS.createBlockNoItem("potted_banana_frond", () -> new FlowerPotBlock(BANANA_FROND.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> FROND_THATCH = BLOCKS.createBlock("frond_thatch", () -> new TrimmableThatchBlock(NeapolitanBlockProperties.FROND_THATCH));
	public static final DeferredBlock<Block> FROND_THATCH_SLAB = BLOCKS.createBlock("frond_thatch_slab", () -> new TrimmableThatchSlabBlock(NeapolitanBlockProperties.FROND_THATCH));
	public static final DeferredBlock<Block> FROND_THATCH_STAIRS = BLOCKS.createBlock("frond_thatch_stairs", () -> new TrimmableThatchStairBlock(FROND_THATCH.get().defaultBlockState(), NeapolitanBlockProperties.FROND_THATCH));

	public static final DeferredBlock<Block> ADZUKI_SPROUTS = BLOCKS.createBlockNoItem("adzuki_sprouts", () -> new AdzukiSproutsBlock(NeapolitanBlockProperties.ADZUKI_SPROUTS));
	public static final DeferredBlock<Block> ADZUKI_SOIL = BLOCKS.createBlock("adzuki_soil", () -> new AdzukiSoilBlock(NeapolitanBlockProperties.ADZUKI_SOIL));
	public static final DeferredBlock<Block> MAGIC_BEANS = BLOCKS.createBlock("magic_beans", () -> new MagicBeansBlock(NeapolitanBlockProperties.MAGIC_BEANS));
	public static final DeferredBlock<Block> BEANSTALK = BLOCKS.createBlock("beanstalk", () -> new BeanstalkBlock(NeapolitanBlockProperties.BEANSTALK));
	public static final DeferredBlock<Block> BEANSTALK_THORNS = BLOCKS.createBlock("beanstalk_thorns", () -> new BeanstalkThornsBlock(NeapolitanBlockProperties.BEANSTALK_THORNS));

	public static final DeferredBlock<Block> STRIPPED_KOA_LOG = BLOCKS.createBlock("stripped_koa_log", () -> new RotatedPillarBlock(NeapolitanBlockProperties.KOA.log()));
	public static final DeferredBlock<Block> STRIPPED_KOA_WOOD = BLOCKS.createBlock("stripped_koa_wood", () -> new RotatedPillarBlock(NeapolitanBlockProperties.KOA.log()));
	public static final DeferredBlock<Block> KOA_LOG = BLOCKS.createBlock("koa_log", () -> new LogBlock(STRIPPED_KOA_LOG, NeapolitanBlockProperties.KOA.log()));
	public static final DeferredBlock<Block> KOA_WOOD = BLOCKS.createBlock("koa_wood", () -> new LogBlock(STRIPPED_KOA_WOOD, NeapolitanBlockProperties.KOA.log()));
	public static final DeferredBlock<Block> KOA_PLANKS = BLOCKS.createBlock("koa_planks", () -> new Block(NeapolitanBlockProperties.KOA.planks()));
	public static final DeferredBlock<Block> KOA_STAIRS = BLOCKS.createBlock("koa_stairs", () -> new StairBlock(KOA_PLANKS.get().defaultBlockState(), NeapolitanBlockProperties.KOA.planks()));
	public static final DeferredBlock<Block> KOA_SLAB = BLOCKS.createBlock("koa_slab", () -> new SlabBlock(NeapolitanBlockProperties.KOA.planks()));
	public static final DeferredBlock<Block> KOA_PRESSURE_PLATE = BLOCKS.createBlock("koa_pressure_plate", () -> new PressurePlateBlock(NeapolitanBlockProperties.KOA_BLOCK_SET, NeapolitanBlockProperties.KOA.pressurePlate()));
	public static final DeferredBlock<Block> KOA_BUTTON = BLOCKS.createBlock("koa_button", () -> new ButtonBlock(NeapolitanBlockProperties.KOA_BLOCK_SET, 30, NeapolitanBlockProperties.KOA.button()));
	public static final DeferredBlock<Block> KOA_FENCE = BLOCKS.createBlock("koa_fence", () -> new FenceBlock(NeapolitanBlockProperties.KOA.planks()));
	public static final DeferredBlock<Block> KOA_FENCE_GATE = BLOCKS.createBlock("koa_fence_gate", () -> new FenceGateBlock(NeapolitanBlockProperties.KOA_WOOD_TYPE, NeapolitanBlockProperties.KOA.planks()));
	public static final DeferredBlock<Block> KOA_DOOR = BLOCKS.createBlock("koa_door", () -> new DoorBlock(NeapolitanBlockProperties.KOA_BLOCK_SET, NeapolitanBlockProperties.KOA.door()));
	public static final DeferredBlock<Block> KOA_TRAPDOOR = BLOCKS.createBlock("koa_trapdoor", () -> new TrapDoorBlock(NeapolitanBlockProperties.KOA_BLOCK_SET, NeapolitanBlockProperties.KOA.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> KOA_SIGNS = BLOCKS.createSignBlock("koa", NeapolitanBlockProperties.KOA_WOOD_TYPE, NeapolitanBlockProperties.KOA.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> KOA_HANGING_SIGNS = BLOCKS.createHangingSignBlock("koa", NeapolitanBlockProperties.KOA_WOOD_TYPE, NeapolitanBlockProperties.KOA.hangingSign());

	public static final DeferredBlock<Block> KOA_BOARDS = BLOCKS.createBlock("koa_boards", () -> new RotatedPillarBlock(NeapolitanBlockProperties.KOA.planks()));
	public static final DeferredBlock<Block> KOA_BOOKSHELF = BLOCKS.createBlock("koa_bookshelf", () -> new Block(NeapolitanBlockProperties.KOA.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_KOA_BOOKSHELF = BLOCKS.createBlock("chiseled_koa_bookshelf", () -> new BlueprintChiseledBookShelfBlock(NeapolitanBlockProperties.KOA.chiseledBookshelf()));
	public static final DeferredBlock<Block> KOA_LADDER = BLOCKS.createBlock("koa_ladder", () -> new LadderBlock(NeapolitanBlockProperties.KOA.ladder()));
	public static final DeferredBlock<Block> KOA_BEEHIVE = BLOCKS.createBlock("koa_beehive", () -> new BlueprintBeehiveBlock(NeapolitanBlockProperties.KOA.beehive()));
	public static final DeferredBlock<BlueprintChestBlock> KOA_CHEST = BLOCKS.createChestBlock("koa", NeapolitanBlockProperties.KOA.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_KOA_CHEST = BLOCKS.createTrappedChestBlock("koa", NeapolitanBlockProperties.KOA.chest());

	public static final DeferredBlock<Block> KOA_LEAVES = BLOCKS.createBlock("koa_leaves", () -> new LeavesBlock(NeapolitanBlockProperties.KOA.leaves()));
	public static final DeferredBlock<Block> KOA_SAPLING = BLOCKS.createBlock("koa_sapling", () -> new SaplingBlock(TreeGrower.ACACIA, NeapolitanBlockProperties.KOA.sapling()));
	public static final DeferredBlock<Block> POTTED_KOA_SAPLING = BLOCKS.createBlockNoItem("potted_koa_sapling", () -> new FlowerPotBlock(KOA_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> KOA_LEAF_PILE = BLOCKS.createBlock("koa_leaf_pile", () -> new LeafPileBlock(NeapolitanBlockProperties.KOA.leafPile()));

	public static final DeferredBlock<Block> CINNAMON_STALK = BLOCKS.createBlock("cinnamon_stalk", () -> new RotatedPillarBlock(NeapolitanBlockProperties.CINNAMON_STALK));
	public static final DeferredBlock<Block> CINNAMON_THATCH = BLOCKS.createBlock("cinnamon_thatch", () -> new TrimmableThatchBlock(NeapolitanBlockProperties.CINNAMON_THATCH));
	public static final DeferredBlock<Block> CINNAMON_THATCH_SLAB = BLOCKS.createBlock("cinnamon_thatch_slab", () -> new TrimmableThatchSlabBlock(NeapolitanBlockProperties.CINNAMON_THATCH));
	public static final DeferredBlock<Block> CINNAMON_THATCH_STAIRS = BLOCKS.createBlock("cinnamon_thatch_stairs", () -> new TrimmableThatchStairBlock(CINNAMON_THATCH.get().defaultBlockState(), NeapolitanBlockProperties.CINNAMON_THATCH));
	public static final DeferredBlock<Block> FLOWERING_CINNAMON_THATCH = BLOCKS.createBlock("flowering_cinnamon_thatch", () -> new TrimmableThatchBlock(NeapolitanBlockProperties.CINNAMON_THATCH));
	public static final DeferredBlock<Block> FLOWERING_CINNAMON_THATCH_SLAB = BLOCKS.createBlock("flowering_cinnamon_thatch_slab", () -> new TrimmableThatchSlabBlock(NeapolitanBlockProperties.CINNAMON_THATCH));
	public static final DeferredBlock<Block> FLOWERING_CINNAMON_THATCH_STAIRS = BLOCKS.createBlock("flowering_cinnamon_thatch_stairs", () -> new TrimmableThatchStairBlock(FLOWERING_CINNAMON_THATCH.get().defaultBlockState(), NeapolitanBlockProperties.CINNAMON_THATCH));

	public static final DeferredBlock<Block> VANILLA_CAKE = BLOCKS.createBlockNoItem("vanilla_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.VANILLA_CAKE, NeapolitanBlockProperties.VANILLA_CAKE));
	public static final DeferredBlock<Block> CHOCOLATE_CAKE = BLOCKS.createBlockNoItem("chocolate_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.CHOCOLATE_CAKE, NeapolitanBlockProperties.CHOCOLATE_CAKE));
	public static final DeferredBlock<Block> STRAWBERRY_CAKE = BLOCKS.createBlockNoItem("strawberry_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.STRAWBERRY_CAKE, NeapolitanBlockProperties.STRAWBERRY_CAKE));
	public static final DeferredBlock<Block> BANANA_CAKE = BLOCKS.createBlockNoItem("banana_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.BANANA_CAKE, NeapolitanBlockProperties.BANANA_CAKE));
	public static final DeferredBlock<Block> MINT_CAKE = BLOCKS.createBlockNoItem("mint_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.MINT_CAKE, NeapolitanBlockProperties.MINT_CAKE));
	public static final DeferredBlock<Block> ADZUKI_CAKE = BLOCKS.createBlockNoItem("adzuki_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.ADZUKI_CAKE, NeapolitanBlockProperties.ADZUKI_CAKE));
	public static final DeferredBlock<Block> MANGO_CAKE = BLOCKS.createBlockNoItem("mango_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.MANGO_CAKE, NeapolitanBlockProperties.MANGO_CAKE));
	public static final DeferredBlock<Block> CINNAMON_CAKE = BLOCKS.createBlockNoItem("cinnamon_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.CINNAMON_CAKE, NeapolitanBlockProperties.CINNAMON_CAKE));
	public static final DeferredBlock<Block> BUBBLEGUM_CAKE = BLOCKS.createBlockNoItem("bubblegum_cake", () -> new FlavoredCakeBlock(NeapolitanFoods.BUBBLEGUM_CAKE, NeapolitanBlockProperties.BUBBLEGUM_CAKE));

	public static final DeferredBlock<Block> SUGAR_CANE_BLOCK = BLOCKS.createBlock("sugar_cane_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.SUGAR_CANE_BLOCK));
	public static final DeferredBlock<Block> VANILLA_POD_BLOCK = BLOCKS.createBlock("vanilla_pod_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.VANILLA_POD_BLOCK));
	public static final DeferredBlock<Block> DRIED_VANILLA_POD_BLOCK = BLOCKS.createBlock("dried_vanilla_pod_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.DRIED_VANILLA_POD_BLOCK));
	public static final DeferredBlock<Block> BANANA_BUNDLE = BLOCKS.createBlock("banana_bundle", () -> new BananaBundleBlock(NeapolitanBlockProperties.BANANA_BUNDLE));
	public static final DeferredBlock<Block> CINNAMON_STICK_BLOCK = BLOCKS.createBlock("cinnamon_stick_block", () -> new RotatedPillarBlock(NeapolitanBlockProperties.SUGAR_CANE_BLOCK));

	public static final DeferredBlock<Block> STRAWBERRY_BASKET = BLOCKS.createBlock("strawberry_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.STRAWBERRY_BASKET));
	public static final DeferredBlock<Block> WHITE_STRAWBERRY_BASKET = BLOCKS.createBlock("white_strawberry_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.WHITE_STRAWBERRY_BASKET));
	public static final DeferredBlock<Block> BANANA_CRATE = BLOCKS.createBlock("banana_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.BANANA_CRATE));
	public static final DeferredBlock<Block> MINT_BASKET = BLOCKS.createBlock("mint_basket", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.MINT_BASKET));
	public static final DeferredBlock<Block> ADZUKI_CRATE = BLOCKS.createBlock("adzuki_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.ADZUKI_CRATE));
	public static final DeferredBlock<Block> ROASTED_ADZUKI_CRATE = BLOCKS.createBlock("roasted_adzuki_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.ROASTED_ADZUKI_CRATE));
	public static final DeferredBlock<Block> MANGO_CRATE = BLOCKS.createBlock("mango_crate", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.ADZUKI_CRATE));

	public static final DeferredBlock<Block> COCOA_BEAN_SACK = BLOCKS.createBlock("cocoa_bean_sack", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.COCOA_BEAN_SACK));
	public static final DeferredBlock<Block> SUGAR_SACK = BLOCKS.createBlock("sugar_sack", () -> new BlueprintDirectionalBlock(NeapolitanBlockProperties.SUGAR_SACK));

	public static final DeferredBlock<Block> CHIMPANZEE_HEAD = BLOCKS.createBlockNoItem("chimpanzee_head", () -> new NeapolitanSkullBlock(NeapolitanSkullTypes.CHIMPANZEE, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY)));
	public static final DeferredBlock<Block> CHIMPANZEE_WALL_HEAD = BLOCKS.createBlockNoItem("chimpanzee_wall_head", () -> new NeapolitanWallSkullBlock(NeapolitanSkullTypes.CHIMPANZEE, BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY).lootFrom(() -> CHIMPANZEE_HEAD.get())));

	public static final DeferredBlock<Block> VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_VANILLA_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_vanilla_candle_cake", () -> new FlavoredCandleCakeBlock(VANILLA_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.VANILLA_CANDLE_CAKE));

	public static final DeferredBlock<Block> CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_CHOCOLATE_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_chocolate_candle_cake", () -> new FlavoredCandleCakeBlock(CHOCOLATE_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.CHOCOLATE_CANDLE_CAKE));

	public static final DeferredBlock<Block> STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_STRAWBERRY_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_strawberry_candle_cake", () -> new FlavoredCandleCakeBlock(STRAWBERRY_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.STRAWBERRY_CANDLE_CAKE));

	public static final DeferredBlock<Block> BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_BANANA_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_banana_candle_cake", () -> new FlavoredCandleCakeBlock(BANANA_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.BANANA_CANDLE_CAKE));

	public static final DeferredBlock<Block> MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_MINT_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_mint_candle_cake", () -> new FlavoredCandleCakeBlock(MINT_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.MINT_CANDLE_CAKE));

	public static final DeferredBlock<Block> ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_ADZUKI_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_adzuki_candle_cake", () -> new FlavoredCandleCakeBlock(ADZUKI_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.ADZUKI_CANDLE_CAKE));

	public static final DeferredBlock<Block> MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_MANGO_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_mango_candle_cake", () -> new FlavoredCandleCakeBlock(MANGO_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.MANGO_CANDLE_CAKE));

	public static final DeferredBlock<Block> CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_CINNAMON_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_cinnamon_candle_cake", () -> new FlavoredCandleCakeBlock(CINNAMON_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.CINNAMON_CANDLE_CAKE));

	public static final DeferredBlock<Block> BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> WHITE_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("white_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.WHITE_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> ORANGE_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("orange_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.ORANGE_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> MAGENTA_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("magenta_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.MAGENTA_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_blue_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.LIGHT_BLUE_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> YELLOW_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("yellow_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.YELLOW_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIME_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("lime_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.LIME_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> PINK_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("pink_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.PINK_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> GRAY_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("gray_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.GRAY_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("light_gray_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.LIGHT_GRAY_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> CYAN_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("cyan_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.CYAN_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> PURPLE_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("purple_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.PURPLE_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLUE_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("blue_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.BLUE_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> BROWN_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("brown_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.BROWN_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> GREEN_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("green_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.GREEN_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> RED_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("red_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.RED_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));
	public static final DeferredBlock<Block> BLACK_BUBBLEGUM_CANDLE_CAKE = BLOCKS.createBlockNoItem("black_bubblegum_candle_cake", () -> new FlavoredCandleCakeBlock(BUBBLEGUM_CAKE, Blocks.BLACK_CANDLE, NeapolitanBlockProperties.BUBBLEGUM_CANDLE_CAKE));

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(Neapolitan.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK), KOA_LOG, KOA_WOOD, STRIPPED_KOA_LOG, STRIPPED_KOA_WOOD, KOA_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), KOA_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK), KOA_STAIRS, KOA_SLAB, KOA_FENCE, KOA_FENCE_GATE, KOA_DOOR, KOA_TRAPDOOR, KOA_PRESSURE_PLATE, KOA_BUTTON)
				.addItems(
						BANANA_STALK, CARVED_BANANA_STALK, FROND_THATCH, FROND_THATCH_STAIRS, FROND_THATCH_SLAB,
						CINNAMON_STALK, CINNAMON_THATCH, CINNAMON_THATCH_STAIRS, CINNAMON_THATCH_SLAB, FLOWERING_CINNAMON_THATCH, FLOWERING_CINNAMON_THATCH_STAIRS, FLOWERING_CINNAMON_THATCH_SLAB,
						CHOCOLATE_BLOCK, CHOCOLATE_BRICKS, CHOCOLATE_BRICK_STAIRS, CHOCOLATE_BRICK_SLAB, CHOCOLATE_BRICK_WALL, CHISELED_CHOCOLATE_BRICKS, CHOCOLATE_TILES, CHOCOLATE_TILE_STAIRS, CHOCOLATE_TILE_SLAB, CHOCOLATE_TILE_WALL,
						ICE_CREAM_BLOCK, VANILLA_ICE_CREAM_BLOCK, CHOCOLATE_ICE_CREAM_BLOCK, STRAWBERRY_ICE_CREAM_BLOCK, BANANA_ICE_CREAM_BLOCK, MINT_ICE_CREAM_BLOCK, ADZUKI_ICE_CREAM_BLOCK, MANGO_ICE_CREAM_BLOCK, CINNAMON_ICE_CREAM_BLOCK, BUBBLEGUM_ICE_CREAM_BLOCK,
						WAFFLE_CONE_BLOCK, WAFFLE_CONE_PILLAR, WAFFLE_CONE_TILES, WAFFLE_CONE_TILE_STAIRS, WAFFLE_CONE_TILE_SLAB, WAFFLE_CONE_TILE_WALL
				)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsAfter(of(Blocks.CAULDRON), CHILLBOX)
				.addItemsBefore(of(Blocks.BAMBOO_SIGN), KOA_SIGNS.getFirst(), KOA_HANGING_SIGNS.getFirst())
				.tab(REDSTONE_BLOCKS)
				.addItemsAfter(of(Blocks.CAULDRON), CHILLBOX)
				.tab(NATURAL_BLOCKS)
				.addItemsBefore(of(Blocks.MUSHROOM_STEM), KOA_LOG)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), KOA_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), KOA_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA), KOA_SAPLING)
				.addItemsAfter(of(Blocks.ROOTED_DIRT), ADZUKI_SOIL)
				.addItemsAfter(of(Items.COCOA_BEANS), MAGIC_BEANS)
				.addItemsAfter(of(Blocks.CACTUS), BANANA_STALK, BANANA_FROND, BEANSTALK, BEANSTALK_THORNS, CINNAMON_STALK)
				.addItemsAfter(of(Blocks.MELON), BANANA_BUNDLE)
				.addItemsAfter(of(Blocks.HAY_BLOCK), SUGAR_CANE_BLOCK, VANILLA_POD_BLOCK, DRIED_VANILLA_POD_BLOCK, CINNAMON_STICK_BLOCK, SUGAR_SACK, COCOA_BEAN_SACK, STRAWBERRY_BASKET, WHITE_STRAWBERRY_BASKET, MINT_BASKET, BANANA_CRATE, ADZUKI_CRATE, ROASTED_ADZUKI_CRATE, MANGO_CRATE);

		CreativeModeTabContentsPopulator.mod("woodworks_1")
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(ofID(NeapolitanConstants.BAMBOO_LADDER), KOA_LADDER)
				.addItemsBefore(ofID(NeapolitanConstants.BAMBOO_BEEHIVE), KOA_BEEHIVE)
				.addItemsBefore(ofID(NeapolitanConstants.BAMBOO_BOOKSHELF), KOA_BOOKSHELF, CHISELED_KOA_BOOKSHELF)
				.addItemsBefore(ofID(NeapolitanConstants.BAMBOO_CLOSET), KOA_CHEST)
				.tab(REDSTONE_BLOCKS)
				.addItemsBefore(ofID(NeapolitanConstants.TRAPPED_BAMBOO_CLOSET), TRAPPED_KOA_CHEST);
	}
	
	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(BuiltInRegistries.ITEM.get(location)).test(stack));
	}

	public enum NeapolitanSkullTypes implements SkullBlock.Type {
		CHIMPANZEE("chimpanzee");

		private final String name;

		private NeapolitanSkullTypes(String name) {
			this.name = name;
			TYPES.put(name, this);
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}

	public static final class NeapolitanBlockProperties {
		public static final WoodSetProperties KOA = WoodSetProperties.builder(MapColor.TERRACOTTA_ORANGE).build();
		public static final BlockSetType KOA_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(Neapolitan.MOD_ID + ":koa"));
		public static final WoodType KOA_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(Neapolitan.MOD_ID + ":koa", KOA_BLOCK_SET));

		public static final BlockBehaviour.Properties ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties VANILLA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties CHOCOLATE_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties STRAWBERRY_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties BANANA_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties MINT_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties ADZUKI_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties MANGO_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties CINNAMON_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);
		public static final BlockBehaviour.Properties BUBBLEGUM_ICE_CREAM_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).instrument(NoteBlockInstrument.CHIME).strength(0.2F).sound(SoundType.SNOW);

		public static final BlockBehaviour.Properties CAULDRON = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion();

		public static final BlockBehaviour.Properties CHOCOLATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties CHOCOLATE_TILES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);

		public static final BlockBehaviour.Properties WAFFLE_CONE_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.PACKED_MUD);

		public static final BlockBehaviour.Properties STRAWBERRY_BUSH = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties VANILLA_VINE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().instabreak().noCollission().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties MINT = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);

		public static final BlockBehaviour.Properties BANANA_STALK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.0F).sound(NeapolitanSoundTypes.BANANA_STALK);
		public static final BlockBehaviour.Properties BANANA_FROND = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().instabreak().noCollission().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties FROND_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F).sound(SoundType.NETHER_SPROUTS);

		public static final BlockBehaviour.Properties ADZUKI_SPROUTS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties ADZUKI_SOIL = BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(0.5F).randomTicks().sound(SoundType.ROOTED_DIRT);
		public static final BlockBehaviour.Properties MAGIC_BEANS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollission().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties BEANSTALK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).strength(1.0F).isSuffocating((state, reader, pos) -> false).sound(SoundType.STEM).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties BEANSTALK_THORNS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().strength(0.2F).sound(SoundType.FUNGUS).pushReaction(PushReaction.DESTROY);
		
		public static final BlockBehaviour.Properties CINNAMON_STALK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.0F).sound(NeapolitanSoundTypes.BANANA_STALK);
		public static final BlockBehaviour.Properties CINNAMON_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5F).sound(SoundType.NETHER_SPROUTS);
		
		public static final BlockBehaviour.Properties CHOCOLATE_CAKE = cake(MapColor.COLOR_BROWN, false);
		public static final BlockBehaviour.Properties STRAWBERRY_CAKE = cake(MapColor.COLOR_PINK, false);
		public static final BlockBehaviour.Properties VANILLA_CAKE = cake(MapColor.TERRACOTTA_WHITE, false);
		public static final BlockBehaviour.Properties BANANA_CAKE = cake(MapColor.COLOR_YELLOW, false);
		public static final BlockBehaviour.Properties MINT_CAKE = cake(MapColor.COLOR_LIGHT_GREEN, false);
		public static final BlockBehaviour.Properties ADZUKI_CAKE = cake(MapColor.COLOR_RED, false);
		public static final BlockBehaviour.Properties MANGO_CAKE = cake(MapColor.COLOR_ORANGE, false);
		public static final BlockBehaviour.Properties CINNAMON_CAKE = cake(MapColor.TERRACOTTA_WHITE, false);
		public static final BlockBehaviour.Properties BUBBLEGUM_CAKE = cake(MapColor.COLOR_LIGHT_BLUE, false);

		public static final BlockBehaviour.Properties CHOCOLATE_CANDLE_CAKE = cake(MapColor.COLOR_BROWN, true);
		public static final BlockBehaviour.Properties STRAWBERRY_CANDLE_CAKE = cake(MapColor.COLOR_PINK, true);
		public static final BlockBehaviour.Properties VANILLA_CANDLE_CAKE = cake(MapColor.TERRACOTTA_WHITE, true);
		public static final BlockBehaviour.Properties BANANA_CANDLE_CAKE = cake(MapColor.COLOR_YELLOW, true);
		public static final BlockBehaviour.Properties MINT_CANDLE_CAKE = cake(MapColor.COLOR_LIGHT_GREEN, true);
		public static final BlockBehaviour.Properties ADZUKI_CANDLE_CAKE = cake(MapColor.COLOR_RED, true);
		public static final BlockBehaviour.Properties MANGO_CANDLE_CAKE = cake(MapColor.COLOR_ORANGE, true);
		public static final BlockBehaviour.Properties CINNAMON_CANDLE_CAKE = cake(MapColor.TERRACOTTA_WHITE, true);
		public static final BlockBehaviour.Properties BUBBLEGUM_CANDLE_CAKE = cake(MapColor.COLOR_LIGHT_BLUE, true);

		public static final BlockBehaviour.Properties SUGAR_CANE_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties VANILLA_POD_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties DRIED_VANILLA_POD_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F, 2.5F).sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties BANANA_BUNDLE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.DIDGERIDOO).strength(2.5F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);

		public static final BlockBehaviour.Properties STRAWBERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties WHITE_STRAWBERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties BANANA_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.STEM);
		public static final BlockBehaviour.Properties MINT_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ADZUKI_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties ROASTED_ADZUKI_CRATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD);
		public static final BlockBehaviour.Properties SUGAR_SACK = BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).ignitedByLava().instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL);
		public static final BlockBehaviour.Properties COCOA_BEAN_SACK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL);

		private static ToIntFunction<BlockState> litBlockEmission(int level) {
			return (state) -> state.getValue(BlockStateProperties.LIT) ? level : 0;
		}

		private static BlockBehaviour.Properties cake(MapColor color, boolean candle) {
			return BlockBehaviour.Properties.of().mapColor(color).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(candle ? litBlockEmission(3) : s -> 0);
		}
	}
}
