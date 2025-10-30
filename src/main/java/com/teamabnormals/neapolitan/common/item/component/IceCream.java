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
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.EitherHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public record IceCream(EitherHolder<IceCreamFlavor> primaryFlavor, EitherHolder<IceCreamFlavor> secondaryFlavor, EitherHolder<IceCreamFlavor> tertiaryFlavor, ImmutableList<EitherHolder<IceCreamFlavor>> flavors) implements TooltipProvider {

	public static final Codec<IceCream> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							EitherHolder.codec(NeapolitanRegistries.ICE_CREAM_FLAVOR, IceCreamFlavor.CODEC).fieldOf("primary_flavor").forGetter(iceCream -> iceCream.primaryFlavor),
							EitherHolder.codec(NeapolitanRegistries.ICE_CREAM_FLAVOR, IceCreamFlavor.CODEC).fieldOf("secondary_flavor").forGetter(iceCream -> iceCream.secondaryFlavor),
							EitherHolder.codec(NeapolitanRegistries.ICE_CREAM_FLAVOR, IceCreamFlavor.CODEC).fieldOf("tertiary_flavor").forGetter(iceCream -> iceCream.tertiaryFlavor)
					)
					.apply(instance, IceCream::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, IceCream> STREAM_CODEC = StreamCodec.composite(
			EitherHolder.streamCodec(NeapolitanRegistries.ICE_CREAM_FLAVOR, IceCreamFlavor.STREAM_CODEC), IceCream::primaryFlavor,
			EitherHolder.streamCodec(NeapolitanRegistries.ICE_CREAM_FLAVOR, IceCreamFlavor.STREAM_CODEC), IceCream::secondaryFlavor,
			EitherHolder.streamCodec(NeapolitanRegistries.ICE_CREAM_FLAVOR, IceCreamFlavor.STREAM_CODEC), IceCream::tertiaryFlavor,
			IceCream::new
	);

	public IceCream(EitherHolder<IceCreamFlavor> flavor) {
		this(flavor, flavor, flavor);
	}

	public IceCream(EitherHolder<IceCreamFlavor> primary, EitherHolder<IceCreamFlavor> secondary, EitherHolder<IceCreamFlavor> tertiary) {
		this(primary, secondary, tertiary, ImmutableList.of(primary, secondary, tertiary));
	}

	public IceCream(Holder<IceCreamFlavor> flavor) {
		this(flavor, flavor, flavor);
	}

	public IceCream(Holder<IceCreamFlavor> primary, Holder<IceCreamFlavor> secondary, Holder<IceCreamFlavor> tertiary) {
		this(new EitherHolder<>(primary), new EitherHolder<>(secondary), new EitherHolder<>(tertiary));
	}

	public IceCream(ResourceKey<IceCreamFlavor> flavor) {
		this(flavor, flavor, flavor);
	}

	public IceCream(ResourceKey<IceCreamFlavor> primary, ResourceKey<IceCreamFlavor> secondary, ResourceKey<IceCreamFlavor> tertiary) {
		this(new EitherHolder<>(primary), new EitherHolder<>(secondary), new EitherHolder<>(tertiary));
	}

	public EitherHolder<IceCreamFlavor> flavor(int i) {
		return switch (i) {
			case 3 -> tertiaryFlavor;
			case 2 -> secondaryFlavor;
			default -> primaryFlavor;
		};
	}

	public ArrayList<ResourceKey<IceCreamFlavor>> flavorKeys() {
		return new ArrayList<>(this.flavors().stream().map(EitherHolder::key).toList());
	}

	public ArrayList<ResourceKey<IceCreamFlavor>> distinctFlavorKeys() {
		return new ArrayList<>(this.flavorKeys().stream().distinct().toList());
	}

	public int flavorCount(ResourceKey<IceCreamFlavor> flavor) {
		return Collections.frequency(this.flavorKeys(), flavor);
	}

	private static final Component FLAVORS_TITLE = Component.translatable(Util.makeDescriptionId("item", Neapolitan.location("ice_cream.flavors"))).withStyle(ChatFormatting.GRAY);

	public boolean is(EitherHolder<IceCreamFlavor> flavor1, EitherHolder<IceCreamFlavor> flavor2, EitherHolder<IceCreamFlavor> flavor3) {
		return this.is(flavor1.key(), flavor2.key(), flavor3.key());
	}

	public boolean is(ResourceKey<IceCreamFlavor> flavor1, ResourceKey<IceCreamFlavor> flavor2, ResourceKey<IceCreamFlavor> flavor3) {
		return this.primaryFlavor.key().equals(flavor1) && this.secondaryFlavor.key().equals(flavor2) && this.tertiaryFlavor.key().equals(flavor3);
	}

	public boolean is(ResourceKey<IceCreamFlavor> flavor) {
		return this.is(flavor, flavor, flavor);
	}

	public boolean is(IceCream iceCream) {
		return this.is(iceCream.primaryFlavor().key(), iceCream.secondaryFlavor().key(), iceCream.tertiaryFlavor().key());
	}

	public boolean matches(IceCream iceCream) {
		List<ResourceKey<IceCreamFlavor>> flavors1 = this.flavorKeys();
		for (ResourceKey<IceCreamFlavor> flavor : iceCream.flavorKeys()) {
			flavors1.remove(flavor);
		}
		return flavors1.isEmpty();
	}

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		HolderLookup.Provider registries = context.registries();
		if (registries != null) {
			tooltipAdder.accept(FLAVORS_TITLE);
			List<ResourceKey<IceCreamFlavor>> added = Lists.newArrayList();
			this.flavors().forEach(flavor -> {
				if (!added.contains(flavor.key())) {
					MutableComponent flavorComponent = CommonComponents.space().append(flavor.unwrap(registries).get().value().description());
					int flavorCount = this.getFlavorCount(flavor.key());
					if (flavorCount > 1) {
						flavorComponent.append(Component.literal(" x" + flavorCount).withStyle(ChatFormatting.GRAY));
						added.add(flavor.key());
					}
					tooltipAdder.accept(flavorComponent);
				}
			});
		}
	}

	public int getFlavorCount(ResourceKey<IceCreamFlavor> flavor) {
		return this.flavorKeys().stream().filter(f -> f.equals(flavor)).toList().size();
	}

	public static ItemStack setFlavors(ItemLike item, EitherHolder<IceCreamFlavor> flavor1, EitherHolder<IceCreamFlavor> flavor2, EitherHolder<IceCreamFlavor> flavor3) {
		ItemStack stack = new ItemStack(item);
		stack.set(NeapolitanDataComponents.ICE_CREAM, new IceCream(flavor1, flavor2, flavor3));
		return stack;
	}

	public static ItemStack setFlavors(ItemLike item, IceCream iceCream) {
		ItemStack stack = new ItemStack(item);
		stack.set(NeapolitanDataComponents.ICE_CREAM, iceCream);
		return stack;
	}

	public static ItemStack setFlavor(ItemLike item, ResourceKey<IceCreamFlavor> flavor) {
		return setFlavor(item, new EitherHolder<>(flavor));
	}

	public static ItemStack setFlavor(ItemLike item, EitherHolder<IceCreamFlavor> flavor) {
		return setFlavors(item, flavor, flavor, flavor);
	}

	public static ItemStack setFlavor(ItemLike item, Holder<IceCreamFlavor> flavor) {
		return setFlavors(item, new EitherHolder<>(flavor), new EitherHolder<>(flavor), new EitherHolder<>(flavor));
	}

	public static IceCream neapolitan() {
		return new IceCream(new EitherHolder<>(NeapolitanIceCreamFlavors.VANILLA), new EitherHolder<>(NeapolitanIceCreamFlavors.CHOCOLATE), new EitherHolder<>(NeapolitanIceCreamFlavors.STRAWBERRY));
	}
}