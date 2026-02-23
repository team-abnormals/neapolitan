package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.common.item.component.effect.AddNutrition;
import com.teamabnormals.neapolitan.common.item.component.effect.ApplyMobEffect;
import com.teamabnormals.neapolitan.common.item.component.effect.HealEntity;
import com.teamabnormals.neapolitan.common.item.component.effect.IceCreamFlavorEffect;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.block.Block;

import java.util.List;

public final class NeapolitanIceCreamFlavors {
	public static final ResourceKey<IceCreamFlavor> SNOW = create("snow");

	public static final ResourceKey<IceCreamFlavor> VANILLA = create("vanilla");
	public static final ResourceKey<IceCreamFlavor> CHOCOLATE = create("chocolate");
	public static final ResourceKey<IceCreamFlavor> STRAWBERRY = create("strawberry");
	public static final ResourceKey<IceCreamFlavor> BANANA = create("banana");
	public static final ResourceKey<IceCreamFlavor> MINT = create("mint");
	public static final ResourceKey<IceCreamFlavor> ADZUKI = create("adzuki");
	public static final ResourceKey<IceCreamFlavor> MANGO = create("mango");
	public static final ResourceKey<IceCreamFlavor> CINNAMON = create("cinnamon");
	public static final ResourceKey<IceCreamFlavor> BUBBLEGUM = create("bubblegum");

	public static final List<ResourceKey<IceCreamFlavor>> FLAVORS = List.of(SNOW, VANILLA, CHOCOLATE, STRAWBERRY, BANANA, MINT, ADZUKI, MANGO, CINNAMON, BUBBLEGUM);

	public static void bootstrap(BootstrapContext<IceCreamFlavor> context) {
		register(context, SNOW, Items.SNOWBALL, Style.EMPTY.withColor(0xD2DFE0), null, addNutrition(2));

		register(context, VANILLA, NeapolitanItems.DRIED_VANILLA_PODS.get(), Style.EMPTY.withColor(0xE2CAA1), NeapolitanBlocks.VANILLA_MILKSHAKE_CAULDRON.get(), applyMobEffect(NeapolitanMobEffects.VANILLA_SCENT, 20));
		register(context, CHOCOLATE, NeapolitanItems.CHOCOLATE_BAR.get(), Style.EMPTY.withColor(0xB77861), NeapolitanBlocks.CHOCOLATE_MILKSHAKE_CAULDRON.get(), applyMobEffect(NeapolitanMobEffects.SUGAR_RUSH, 30, 2));
		register(context, STRAWBERRY, NeapolitanItems.STRAWBERRIES.get(), Style.EMPTY.withColor(0xDB95CC), NeapolitanBlocks.STRAWBERRY_MILKSHAKE_CAULDRON.get(), healEntity(2));
		register(context, BANANA, NeapolitanItems.BANANA.get(), Style.EMPTY.withColor(0xF4B858), NeapolitanBlocks.BANANA_MILKSHAKE_CAULDRON.get(), applyMobEffect(NeapolitanMobEffects.AGILITY, 60));
		register(context, MINT, NeapolitanItems.MINT_LEAVES.get(), Style.EMPTY.withColor(0x59B788), NeapolitanBlocks.MINT_MILKSHAKE_CAULDRON.get(), applyMobEffect(NeapolitanMobEffects.BERSERKING, 80));
		register(context, ADZUKI, NeapolitanItems.ROASTED_ADZUKI_BEANS.get(), Style.EMPTY.withColor(0xED7D83), NeapolitanBlocks.ADZUKI_MILKSHAKE_CAULDRON.get(), applyMobEffect(NeapolitanMobEffects.HARMONY, 30));
		register(context, MANGO, NeapolitanItems.MANGO.get(), Style.EMPTY.withColor(0xF48727), NeapolitanBlocks.MANGO_MILKSHAKE_CAULDRON.get());
		register(context, CINNAMON, NeapolitanItems.CINNAMON_STICKS.get(), Style.EMPTY.withColor(0xCE6658), NeapolitanBlocks.CINNAMON_MILKSHAKE_CAULDRON.get());
		register(context, BUBBLEGUM, NeapolitanItems.BUBBLEGUM.get(), Style.EMPTY.withColor(0x4F96DD), NeapolitanBlocks.BUBBLEGUM_MILKSHAKE_CAULDRON.get());
	}

	public static HealEntity healEntity(int amount) {
		return new HealEntity(LevelBasedValue.perLevel(amount), LevelBasedValue.perLevel(amount));
	}

	public static AddNutrition addNutrition(int amount) {
		return new AddNutrition(LevelBasedValue.perLevel(amount));
	}

	public static ApplyMobEffect applyMobEffect(Holder<MobEffect> effect, int duration) {
		return applyMobEffect(effect, duration, 0);
	}

	public static ApplyMobEffect applyMobEffect(Holder<MobEffect> effect, int duration, int level) {
		return new ApplyMobEffect(HolderSet.direct(effect), LevelBasedValue.perLevel(duration), LevelBasedValue.perLevel(duration), LevelBasedValue.constant(level), LevelBasedValue.constant(level));
	}

	private static ResourceKey<IceCreamFlavor> create(String name) {
		return ResourceKey.create(NeapolitanRegistries.ICE_CREAM_FLAVOR, Neapolitan.location(name));
	}

	private static void register(BootstrapContext<IceCreamFlavor> context, ResourceKey<IceCreamFlavor> materialKey, Item ingredient, Style style, Block cauldron, IceCreamFlavorEffect... effects) {
		IceCreamFlavor flavor = IceCreamFlavor.create(ingredient, Component.translatable(Util.makeDescriptionId("flavor", materialKey.location())).withStyle(style), cauldron, effects);
		context.register(materialKey, flavor);
	}
}