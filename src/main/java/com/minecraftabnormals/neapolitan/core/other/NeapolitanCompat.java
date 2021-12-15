package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.abnormals_core.core.registry.LootInjectionRegistry;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.neapolitan.common.entity.BananarrowEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class NeapolitanCompat {
	
	public static void transformCookies() {
		Foods.COOKIE.fastFood = true;
		Foods.COOKIE.saturationModifier = 0.3F;
	}

	public static void registerCompat() {
		registerLootInjectors();
		registerCompostables();
		registerFlammables();
		registerDispenserBehaviors();
	}

	public static void registerLootInjectors() {
		LootInjectionRegistry.LootInjector injector = new LootInjectionRegistry.LootInjector(Neapolitan.MOD_ID);
		injector.addLootInjection(injector.buildLootPool("village_savanna_house", 2, 0), LootTables.VILLAGE_SAVANNA_HOUSE);
		injector.addLootInjection(injector.buildLootPool("village_plains_house", 2, 0), LootTables.VILLAGE_PLAINS_HOUSE);
		injector.addLootInjection(injector.buildLootPool("village_snowy_house", 2, 0), LootTables.VILLAGE_SNOWY_HOUSE);
		injector.addLootInjection(injector.buildLootPool("jungle_temple", 2, 0), LootTables.JUNGLE_TEMPLE);
		injector.addLootInjection(injector.buildLootPool("jungle_temple_dispenser", 1, 0), LootTables.JUNGLE_TEMPLE_DISPENSER);
		injector.addLootInjection(injector.buildLootPool("ravager", 1, 0), new ResourceLocation("entities/ravager"));
	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRY_PIPS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRIES.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.WHITE_STRAWBERRIES.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRY_SCONES.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanItems.STRAWBERRY_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.CHOCOLATE_BAR.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.CHOCOLATE_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.VANILLA_PODS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.DRIED_VANILLA_PODS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.VANILLA_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.BANANA.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.BANANA_BUNCH.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.DRIED_BANANA.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.BANANA_BREAD.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanItems.BANANA_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.MINT_SPROUT.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.MINT_LEAVES.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.MINT_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.ADZUKI_BEANS.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanItems.ROASTED_ADZUKI_BEANS.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanItems.ADZUKI_BUN.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanItems.ADZUKI_CAKE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanItems.VANILLA_CHOCOLATE_FINGERS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanItems.CHOCOLATE_STRAWBERRIES.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanItems.MINT_CHOCOLATE.get(), 0.85F);

		DataUtil.registerCompostable(NeapolitanBlocks.VANILLA_POD_BLOCK.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.get(), 0.5F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_BUNDLE.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_STALK.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CARVED_BANANA_STALK.get(), 0.85F);

		DataUtil.registerCompostable(NeapolitanBlocks.SMALL_BANANA_FROND.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_FROND.get(), 0.5F);
		DataUtil.registerCompostable(NeapolitanBlocks.LARGE_BANANA_FROND.get(), 0.65F);

		DataUtil.registerCompostable(NeapolitanBlocks.BEANSTALK.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.BEANSTALK_THORNS.get(), 0.5F);

		DataUtil.registerCompostable(NeapolitanBlocks.STRAWBERRY_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.MINT_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.ADZUKI_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.ROASTED_ADZUKI_CRATE.get(), 1.0F);

		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_SLAB.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), 0.65F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICKS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_BRICK_VERTICAL_SLAB.get(), 0.3F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), 0.85F);

		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILES.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), 0.85F);
		DataUtil.registerCompostable(NeapolitanBlocks.CHOCOLATE_TILE_VERTICAL_SLAB.get(), 0.3F);
	}

	public static void registerFlammables() {
		DataUtil.registerFlammable(NeapolitanBlocks.VANILLA_VINE.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.VANILLA_POD_BLOCK.get(), 30, 60);
		DataUtil.registerFlammable(NeapolitanBlocks.DRIED_VANILLA_POD_BLOCK.get(), 30, 60);

		DataUtil.registerFlammable(NeapolitanBlocks.STRAWBERRY_BUSH.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.STRAWBERRY_BASKET.get(), 5, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), 5, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.MINT.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.MINT_BASKET.get(), 5, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.ADZUKI_SPROUTS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.ADZUKI_CRATE.get(), 5, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.ROASTED_ADZUKI_CRATE.get(), 5, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.BANANA_STALK.get(), 5, 5);
		DataUtil.registerFlammable(NeapolitanBlocks.SMALL_BANANA_FROND.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.BANANA_FROND.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.LARGE_BANANA_FROND.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH.get(), 60, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), 60, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH_SLAB.get(), 60, 20);
		DataUtil.registerFlammable(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), 60, 20);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BLOCK.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICKS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_STAIRS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_SLAB.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_WALL.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_BRICK_VERTICAL_SLAB.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHISELED_CHOCOLATE_BRICKS.get(), 60, 100);

		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILES.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_STAIRS.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_SLAB.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_WALL.get(), 60, 100);
		DataUtil.registerFlammable(NeapolitanBlocks.CHOCOLATE_TILE_VERTICAL_SLAB.get(), 60, 100);
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(NeapolitanItems.BANANARROW.get(), new ProjectileDispenseBehavior() {
			protected ProjectileEntity getProjectile(World worldIn, IPosition position, ItemStack stackIn) {
				return new BananarrowEntity(worldIn, position.x(), position.y(), position.z());
			}
		});
	}

	public static void registerRenderLayers() {
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.VANILLA_VINE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.MINT.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.ADZUKI_SPROUTS.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.BEANSTALK_THORNS.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.SMALL_BANANA_FROND.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.BANANA_FROND.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.LARGE_BANANA_FROND.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH_SLAB.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), RenderType.cutout());
	}
}
