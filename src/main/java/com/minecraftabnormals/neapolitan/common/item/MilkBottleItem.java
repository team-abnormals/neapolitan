package com.minecraftabnormals.neapolitan.common.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Random;

public class MilkBottleItem extends Item {
	public MilkBottleItem(Item.Properties builder) {
		super(builder);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entity) {
		clearRandomEffect(worldIn, entity);

		if (entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entity;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}

		if (entity instanceof PlayerEntity && !((PlayerEntity) entity).abilities.isCreativeMode)
			stack.shrink(1);

		if (stack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (entity instanceof PlayerEntity && !((PlayerEntity) entity).abilities.isCreativeMode) {
				ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
				PlayerEntity playerentity = (PlayerEntity) entity;
				if (!playerentity.inventory.addItemStackToInventory(itemstack)) {
					playerentity.dropItem(itemstack, false);
				}
			}

			return stack;
		}

	}

	public static void clearRandomEffect(World worldIn, LivingEntity entity) {
		if (!worldIn.isRemote) {
			ImmutableList<EffectInstance> effects = ImmutableList.copyOf(entity.getActivePotionEffects());
			if (effects.size() > 0) {
				Random rand = new Random();
				EffectInstance effectToRemove = effects.get(rand.nextInt(effects.size()));
				entity.removePotionEffect(effectToRemove.getPotion());
			}
		}
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
	}
}