package com.teamabnormals.neapolitan.core.registry.datapack;

import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.common.item.component.IceCreamOverride;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import net.minecraft.Util;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public final class NeapolitanIceCreamOverrides {
	public static final ResourceKey<IceCreamOverride> VANILLA = create("vanilla");
	public static final ResourceKey<IceCreamOverride> CHOCOLATE = create("chocolate");
	public static final ResourceKey<IceCreamOverride> STRAWBERRY = create("strawberry");
	public static final ResourceKey<IceCreamOverride> BANANA = create("banana");
	public static final ResourceKey<IceCreamOverride> MINT = create("mint");
	public static final ResourceKey<IceCreamOverride> ADZUKI = create("adzuki");
	public static final ResourceKey<IceCreamOverride> NEAPOLITAN = create("neapolitan");

	public static void bootstrap(BootstrapContext<IceCreamOverride> context) {
		register(context, VANILLA, NeapolitanFlavors.VANILLA);
		register(context, CHOCOLATE, NeapolitanFlavors.CHOCOLATE);
		register(context, STRAWBERRY, NeapolitanFlavors.STRAWBERRY);
		register(context, BANANA, NeapolitanFlavors.BANANA);
		register(context, MINT, NeapolitanFlavors.MINT);
		register(context, ADZUKI, NeapolitanFlavors.ADZUKI);
		register(context, NEAPOLITAN, new IceCream(NeapolitanFlavors.VANILLA, NeapolitanFlavors.CHOCOLATE, NeapolitanFlavors.STRAWBERRY), null);
	}

	private static ResourceKey<IceCreamOverride> create(String name) {
		return ResourceKey.create(NeapolitanRegistries.ICE_CREAM_OVERRIDE, Neapolitan.location(name));
	}

	private static void register(BootstrapContext<IceCreamOverride> context, ResourceKey<IceCreamOverride> key, ResourceKey<IceCreamFlavor> flavor) {
		context.register(key, new IceCreamOverride(new IceCream(flavor), false,
				Optional.of(Component.translatable(Util.makeDescriptionId("item", key.location().withSuffix("_ice_cream")))),
				Optional.of(key.location().withSuffix("_ice_cream")),
				Optional.of(Component.translatable(Util.makeDescriptionId("item", key.location().withSuffix("_ice_cream_cone")))),
				Optional.empty())
		);
	}

	private static void register(BootstrapContext<IceCreamOverride> context, ResourceKey<IceCreamOverride> key, IceCream iceCream, ResourceLocation location) {
		context.register(key, new IceCreamOverride(iceCream, false,
				Optional.of(Component.translatable(Util.makeDescriptionId("item", key.location().withSuffix("_ice_cream")))),
				Optional.ofNullable(location),
				Optional.of(Component.translatable(Util.makeDescriptionId("item", key.location().withSuffix("_ice_cream_cone")))),
				Optional.empty())
		);
	}
}