package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.neapolitan.common.entity.BananarrowEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.abnormals_core.core.registry.LootInjectionRegistry;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.world.World;

public class NeapolitanCompat {

	public static void transformCookies() {
		Foods.COOKIE.fastToEat = true;
		Foods.COOKIE.saturation = 0.3F;
	}
	
	public static void registerLootInjectors() {
		LootInjectionRegistry.LootInjector injector = new LootInjectionRegistry.LootInjector(Neapolitan.MODID);
		injector.registerLootInjection(injector.buildLootBool("village_savanna_house", 2, 0), LootTables.CHESTS_VILLAGE_VILLAGE_SAVANNA_HOUSE);
		injector.registerLootInjection(injector.buildLootBool("village_plains_house", 2, 0), LootTables.CHESTS_VILLAGE_VILLAGE_PLAINS_HOUSE);
		injector.registerLootInjection(injector.buildLootBool("village_snowy_house", 2, 0), LootTables.CHESTS_VILLAGE_VILLAGE_SNOWY_HOUSE);
		injector.registerLootInjection(injector.buildLootBool("jungle_temple", 2, 0), LootTables.CHESTS_JUNGLE_TEMPLE);
		injector.registerLootInjection(injector.buildLootBool("jungle_temple_dispenser", 1, 0), LootTables.CHESTS_JUNGLE_TEMPLE_DISPENSER);
	}

	public static void registerCompostables() {
		DataUtils.registerCompostable(NeapolitanItems.STRAWBERRY_PIPS.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanItems.STRAWBERRIES.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanItems.WHITE_STRAWBERRIES.get(), 0.65F);
		DataUtils.registerCompostable(NeapolitanItems.STRAWBERRY_SCONES.get(), 0.65F);
		DataUtils.registerCompostable(NeapolitanItems.STRAWBERRY_CAKE.get(), 1.0F);

		DataUtils.registerCompostable(NeapolitanItems.CHOCOLATE_BAR.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanItems.CHOCOLATE_CAKE.get(), 1.0F);

		DataUtils.registerCompostable(NeapolitanItems.VANILLA_PODS.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanItems.DRIED_VANILLA_PODS.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanItems.VANILLA_CAKE.get(), 1.0F);
		
		DataUtils.registerCompostable(NeapolitanItems.BANANA.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanItems.BANANA_BUNCH.get(), 0.5F);
		DataUtils.registerCompostable(NeapolitanItems.DRIED_BANANA.get(), 0.5F);
		DataUtils.registerCompostable(NeapolitanItems.BANANA_BREAD.get(), 0.65F);
		DataUtils.registerCompostable(NeapolitanItems.BANANA_CAKE.get(), 1.0F);

		DataUtils.registerCompostable(NeapolitanItems.VANILLA_CHOCOLATE_FINGERS.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanItems.CHOCOLATE_STRAWBERRIES.get(), 0.85F);

		DataUtils.registerCompostable(NeapolitanBlocks.VANILLA_POD_BLOCK.get(), 0.5F);
		DataUtils.registerCompostable(NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.get(), 0.5F);

		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), 1.0F);
		
		DataUtils.registerCompostable(NeapolitanBlocks.BANANA_BUNDLE.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanBlocks.BANANA_STALK.get(), 0.85F);

		DataUtils.registerCompostable(NeapolitanBlocks.SMALL_BANANA_FROND.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanBlocks.BANANA_FROND.get(), 0.5F);
		DataUtils.registerCompostable(NeapolitanBlocks.LARGE_BANANA_FROND.get(), 0.65F);

		DataUtils.registerCompostable(NeapolitanBlocks.STRAWBERRY_BASKET.get(), 1.0F);
		DataUtils.registerCompostable(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), 1.0F);
		DataUtils.registerCompostable(NeapolitanBlocks.BANANA_CRATE.get(), 1.0F);

		DataUtils.registerCompostable(NeapolitanBlocks.FROND_THATCH.get(), 0.65F);
		DataUtils.registerCompostable(NeapolitanBlocks.FROND_THATCH_SLAB.get(), 0.65F);
		DataUtils.registerCompostable(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), 0.65F);
		DataUtils.registerCompostable(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), 0.65F);

		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICKS.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_VERTICAL_SLAB.get(), 0.3F);

		DataUtils.registerCompostable(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), 0.85F);

		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILES.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), 0.3F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), 0.85F);
		DataUtils.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_VERTICAL_SLAB.get(), 0.3F);
	}

	public static void registerFlammables() {
		DataUtils.registerFlammable(NeapolitanBlocks.VANILLA_VINE.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), 60, 100);

		DataUtils.registerFlammable(NeapolitanBlocks.VANILLA_POD_BLOCK.get(), 30, 60);
		DataUtils.registerFlammable(NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.get(), 30, 60);

		DataUtils.registerFlammable(NeapolitanBlocks.STRAWBERRY_BUSH.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.STRAWBERRY_BASKET.get(), 5, 20);
		DataUtils.registerFlammable(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), 5, 20);

		DataUtils.registerFlammable(NeapolitanBlocks.BANANA_STALK.get(), 5, 5);
		DataUtils.registerFlammable(NeapolitanBlocks.SMALL_BANANA_FROND.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.BANANA_FROND.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.LARGE_BANANA_FROND.get(), 60, 100);

		DataUtils.registerFlammable(NeapolitanBlocks.FROND_THATCH.get(), 60, 20);
		DataUtils.registerFlammable(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), 60, 20);
		DataUtils.registerFlammable(NeapolitanBlocks.FROND_THATCH_SLAB.get(), 60, 20);
		DataUtils.registerFlammable(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), 60, 20);

		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), 60, 100);

		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICKS.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_VERTICAL_SLAB.get(), 60, 100);

		DataUtils.registerFlammable(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), 60, 100);

		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILES.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), 60, 100);
		DataUtils.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_VERTICAL_SLAB.get(), 60, 100);
	}
	
	public static void registerDispenserBehaviors() {
		DispenserBlock.registerDispenseBehavior(NeapolitanItems.BANANARROW.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
				BananarrowEntity arrowentity = new BananarrowEntity(worldIn, position.getX(), position.getY(), position.getZ());
				return arrowentity;
			}
		});
	}

	public static void registerRenderLayers() {
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.VANILLA_VINE.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), RenderType.getCutout());

		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.SMALL_BANANA_FROND.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.BANANA_FROND.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.LARGE_BANANA_FROND.get(), RenderType.getCutout());
		
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH_SLAB.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), RenderType.getCutout());
	}
}
