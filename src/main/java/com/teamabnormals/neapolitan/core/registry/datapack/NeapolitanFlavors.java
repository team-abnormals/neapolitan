package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.Util;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class NeapolitanFlavors {
	public static final ResourceKey<IceCreamFlavor> VANILLA = create("vanilla");
	public static final ResourceKey<IceCreamFlavor> CHOCOLATE = create("chocolate");
	public static final ResourceKey<IceCreamFlavor> STRAWBERRY = create("strawberry");
	public static final ResourceKey<IceCreamFlavor> BANANA = create("banana");
	public static final ResourceKey<IceCreamFlavor> MINT = create("mint");
	public static final ResourceKey<IceCreamFlavor> ADZUKI = create("adzuki");

	public static void bootstrap(BootstrapContext<IceCreamFlavor> context) {
		register(context, VANILLA, NeapolitanItems.DRIED_VANILLA_PODS.get(), Style.EMPTY.withColor(0xE2CAA1), NeapolitanBlocks.VANILLA_MILKSHAKE_CAULDRON.get());
		register(context, CHOCOLATE, NeapolitanItems.CHOCOLATE_BAR.get(), Style.EMPTY.withColor(0xB77861), NeapolitanBlocks.CHOCOLATE_MILKSHAKE_CAULDRON.get());
		register(context, STRAWBERRY, NeapolitanItems.STRAWBERRIES.get(), Style.EMPTY.withColor(0xDB95CC), NeapolitanBlocks.STRAWBERRY_MILKSHAKE_CAULDRON.get());
		register(context, BANANA, NeapolitanItems.BANANA.get(), Style.EMPTY.withColor(0xF4B858), NeapolitanBlocks.BANANA_MILKSHAKE_CAULDRON.get());
		register(context, MINT, NeapolitanItems.MINT_LEAVES.get(), Style.EMPTY.withColor(0x59B788), NeapolitanBlocks.MINT_MILKSHAKE_CAULDRON.get());
		register(context, ADZUKI, NeapolitanItems.ROASTED_ADZUKI_BEANS.get(), Style.EMPTY.withColor(0xED7D83), NeapolitanBlocks.ADZUKI_MILKSHAKE_CAULDRON.get());
	}

	private static ResourceKey<IceCreamFlavor> create(String name) {
		return ResourceKey.create(NeapolitanRegistries.ICE_CREAM_FLAVOR, Neapolitan.location(name));
	}

	private static void register(BootstrapContext<IceCreamFlavor> context, ResourceKey<IceCreamFlavor> materialKey, Item ingredient, Style style, Block cauldron) {
		IceCreamFlavor flavor = IceCreamFlavor.create(ingredient, Component.translatable(Util.makeDescriptionId("flavor", materialKey.location())).withStyle(style), cauldron);
		context.register(materialKey, flavor);
	}
}