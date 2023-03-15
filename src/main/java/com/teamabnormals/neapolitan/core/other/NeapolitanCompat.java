package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.neapolitan.common.entity.projectile.Bananarrow;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class NeapolitanCompat {

	public static void transformCookies() {
		Foods.COOKIE.fastFood = true;
		Foods.COOKIE.saturationModifier = 0.3F;
	}

	public static void registerCompat() {
		registerCompostables();
		registerFlammables();
		registerDispenserBehaviors();
		registerItemProperties();
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
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_FROND.get(), 0.3F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_SLAB.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), 0.65F);

		DataUtil.registerCompostable(NeapolitanBlocks.BEANSTALK.get(), 0.65F);
		DataUtil.registerCompostable(NeapolitanBlocks.BEANSTALK_THORNS.get(), 0.5F);

		DataUtil.registerCompostable(NeapolitanBlocks.STRAWBERRY_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.WHITE_STRAWBERRY_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.BANANA_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.MINT_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.ADZUKI_CRATE.get(), 1.0F);
		DataUtil.registerCompostable(NeapolitanBlocks.ROASTED_ADZUKI_CRATE.get(), 1.0F);

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

	public static void registerItemProperties() {
		ItemProperties.register(Items.CROSSBOW, new ResourceLocation(Neapolitan.MOD_ID, "bananarrow"), (stack, world, entity, i) -> {
			return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, NeapolitanItems.BANANARROW.get()) ? 1.0F : 0.0F;
		});
	}

	public static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(NeapolitanItems.BANANARROW.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn) {
				return new Bananarrow(worldIn, position.x(), position.y(), position.z());
			}
		});
	}

	public static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.STRAWBERRY_BUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.VANILLA_VINE_PLANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_VANILLA_VINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_MINT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.ADZUKI_SPROUTS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BEANSTALK_THORNS.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.SMALL_BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.LARGE_BANANA_FROND.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.POTTED_BANANA_FROND.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_STAIRS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_SLAB.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NeapolitanBlocks.FROND_THATCH_VERTICAL_SLAB.get(), RenderType.cutout());
	}
}
