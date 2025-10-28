package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.neapolitan.client.gui.screens.inventory.ChillboxScreen;
import com.teamabnormals.neapolitan.common.inventory.ChillboxMenu;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NeapolitanMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, Neapolitan.MOD_ID);

	public static final Supplier<MenuType<ChillboxMenu>> CHILLBOX = MENU_TYPES.register("chillbox", () -> new MenuType<>(ChillboxMenu::new, FeatureFlags.VANILLA_SET));

	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(CHILLBOX.get(), ChillboxScreen::new);
	}
}