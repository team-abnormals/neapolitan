package com.teamabnormals.neapolitan.integration.boatload;

import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class NeapolitanBoatTypes {
	public static final BoatloadBoatType KOA = BoatloadBoatType.register(BoatloadBoatType.create(Neapolitan.location("koa"), () -> NeapolitanBlocks.KOA_PLANKS.get().asItem(), () -> NeapolitanItems.KOA_BOAT.getFirst().get(), () -> NeapolitanItems.KOA_BOAT.getSecond().get(), () -> NeapolitanItems.KOA_FURNACE_BOAT.get(), () -> NeapolitanItems.LARGE_KOA_BOAT.get()));

	public static final Supplier<Item> KOA_FURNACE_BOAT = () -> new FurnaceBoatItem(KOA);
	public static final Supplier<Item> LARGE_KOA_BOAT = () -> new LargeBoatItem(KOA);
}