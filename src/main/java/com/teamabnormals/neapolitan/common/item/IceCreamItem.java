package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.common.item.component.IceCreamOverride;
import com.teamabnormals.neapolitan.common.item.component.effect.IceCreamFlavorEffect;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDataComponents;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class IceCreamItem extends Item {

	public IceCreamItem(Properties builder) {
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		// entity.setTicksFrozen(entity.getTicksFrozen() + 200);
		if (stack.has(NeapolitanDataComponents.ICE_CREAM.get()) && entity.level() instanceof ServerLevel serverLevel) {
			IceCream iceCream = stack.get(NeapolitanDataComponents.ICE_CREAM.get());
			iceCream.distinctFlavors().forEach(flavor -> {
				flavor.value().effects().forEach(effect -> {
					effect.finishUsingItem(serverLevel, iceCream.flavorCount(flavor), stack, entity);
				});
			});
		}

		return super.finishUsingItem(stack, level, entity);
	}

	@Override
	public SoundEvent getEatingSound() {
		return NeapolitanSoundEvents.ICE_CREAM_EAT.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		stack.addToTooltip(NeapolitanDataComponents.ICE_CREAM.get(), context, tooltipComponents::add, tooltipFlag);
	}

	@Override
	public FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
		FoodProperties base = super.getFoodProperties(stack, entity);
		if (stack.has(NeapolitanDataComponents.ICE_CREAM.get())) {
			IceCream iceCream = stack.get(NeapolitanDataComponents.ICE_CREAM.get());
			for (Holder<IceCreamFlavor> flavor : iceCream.distinctFlavors()) {
				for (IceCreamFlavorEffect effect : flavor.value().effects()) {
					base = effect.modifyFoodProperties(base, iceCream.flavorCount(flavor), stack, entity);
				}
			}
		}

		return base;
	}

	@Override
	public Component getName(ItemStack stack) {
		IceCream iceCream = stack.get(NeapolitanDataComponents.ICE_CREAM.get());
		Component name = super.getName(stack);
		if (Minecraft.getInstance().level != null && iceCream != null) {
			Optional<Reference<IceCreamOverride>> override = IceCreamOverride.getFromIceCream(Minecraft.getInstance().level.registryAccess(), iceCream);
			if (override.isPresent()) {
				IceCreamOverride value = override.get().value();
				return stack.is(NeapolitanItems.ICE_CREAM_CONE) ? value.coneDescription().orElse(name) : value.bowlDescription().orElse(name);
			}
		}
		return name;
	}
}