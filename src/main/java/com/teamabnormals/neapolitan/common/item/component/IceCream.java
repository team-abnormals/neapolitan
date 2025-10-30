package com.teamabnormals.neapolitan.common.item.component;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDataComponents;
import com.teamabnormals.neapolitan.core.registry.NeapolitanRegistries;
import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanIceCreamFlavors;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public record IceCream(Holder<IceCreamFlavor> primaryFlavor, Holder<IceCreamFlavor> secondaryFlavor, Holder<IceCreamFlavor> tertiaryFlavor, ImmutableList<Holder<IceCreamFlavor>> flavors) implements TooltipProvider {

	public static final Codec<IceCream> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							RegistryFixedCodec.create(NeapolitanRegistries.ICE_CREAM_FLAVOR).fieldOf("primary_flavor").forGetter(iceCream -> iceCream.primaryFlavor),
							RegistryFixedCodec.create(NeapolitanRegistries.ICE_CREAM_FLAVOR).fieldOf("secondary_flavor").forGetter(iceCream -> iceCream.secondaryFlavor),
							RegistryFixedCodec.create(NeapolitanRegistries.ICE_CREAM_FLAVOR).fieldOf("tertiary_flavor").forGetter(iceCream -> iceCream.tertiaryFlavor)
					)
					.apply(instance, IceCream::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, IceCream> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.holderRegistry(NeapolitanRegistries.ICE_CREAM_FLAVOR), IceCream::primaryFlavor,
			ByteBufCodecs.holderRegistry(NeapolitanRegistries.ICE_CREAM_FLAVOR), IceCream::secondaryFlavor,
			ByteBufCodecs.holderRegistry(NeapolitanRegistries.ICE_CREAM_FLAVOR), IceCream::tertiaryFlavor,
			IceCream::new
	);

	public IceCream(Holder<IceCreamFlavor> flavor) {
		this(flavor, flavor, flavor);
	}

	public IceCream(Holder<IceCreamFlavor> primary, Holder<IceCreamFlavor> secondary, Holder<IceCreamFlavor> tertiary) {
		this(primary, secondary, tertiary, ImmutableList.of(primary, secondary, tertiary));
	}

	public Holder<IceCreamFlavor> flavor(int i) {
		return switch (i) {
			case 3 -> tertiaryFlavor;
			case 2 -> secondaryFlavor;
			default -> primaryFlavor;
		};
	}

	public ArrayList<Holder<IceCreamFlavor>> distinctFlavors() {
		return new ArrayList<>(this.flavors.stream().distinct().toList());
	}

	public int flavorCount(Holder<IceCreamFlavor> flavor) {
		return Collections.frequency(this.flavors, flavor);
	}

	private static final Component FLAVORS_TITLE = Component.translatable(Util.makeDescriptionId("item", Neapolitan.location("ice_cream.flavors"))).withStyle(ChatFormatting.GRAY);

	public boolean is(ResourceKey<IceCreamFlavor> flavor1, ResourceKey<IceCreamFlavor> flavor2, ResourceKey<IceCreamFlavor> flavor3) {
		return this.primaryFlavor.is(flavor1) && this.secondaryFlavor.is(flavor2) && this.tertiaryFlavor.is(flavor3);
	}

	public boolean is(ResourceKey<IceCreamFlavor> flavor) {
		return this.is(flavor, flavor, flavor);
	}

	public boolean is(IceCream iceCream) {
		return this.is(iceCream.primaryFlavor().getKey(), iceCream.secondaryFlavor().getKey(), iceCream.tertiaryFlavor().getKey());
	}

	public boolean matches(IceCream iceCream) {
		List<Holder<IceCreamFlavor>> flavors1 = Lists.newArrayList(this.flavors);
		for (Holder<IceCreamFlavor> flavor : iceCream.flavors()) {
			flavors1.remove(flavor);
		}
		return flavors1.isEmpty();
	}

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		HolderLookup.Provider registries = context.registries();
		if (registries != null) {
			tooltipAdder.accept(FLAVORS_TITLE);
			List<Holder<IceCreamFlavor>> added = Lists.newArrayList();
			this.flavors().forEach(flavor -> {
				if (!added.contains(flavor)) {
					MutableComponent flavorComponent = CommonComponents.space().append(flavor.value().description());
					int flavorCount = this.getFlavorCount(flavor.getKey());
					if (flavorCount > 1) {
						flavorComponent.append(Component.literal(" x" + flavorCount).withStyle(ChatFormatting.GRAY));
						added.add(flavor);
					}
					tooltipAdder.accept(flavorComponent);
				}
			});
		}
	}

	public int getFlavorCount(ResourceKey<IceCreamFlavor> flavor) {
		return this.flavors().stream().filter(f -> f.is(flavor)).toList().size();
	}

	public static ItemStack setFlavors(ItemLike item, Holder<IceCreamFlavor> flavor1, Holder<IceCreamFlavor> flavor2, Holder<IceCreamFlavor> flavor3) {
		ItemStack stack = new ItemStack(item);
		stack.set(NeapolitanDataComponents.ICE_CREAM, new IceCream(flavor1, flavor2, flavor3));
		return stack;
	}

	public static ItemStack setFlavor(ItemLike item, Holder<IceCreamFlavor> flavor) {
		return setFlavors(item, flavor, flavor, flavor);
	}

	public static IceCream neapolitan(RegistryLookup<IceCreamFlavor> lookup) {
		return new IceCream(
				lookup.getOrThrow(NeapolitanIceCreamFlavors.VANILLA),
				lookup.getOrThrow(NeapolitanIceCreamFlavors.CHOCOLATE),
				lookup.getOrThrow(NeapolitanIceCreamFlavors.STRAWBERRY)
		);
	}
}