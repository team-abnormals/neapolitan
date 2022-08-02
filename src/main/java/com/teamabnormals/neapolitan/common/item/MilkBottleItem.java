package com.teamabnormals.neapolitan.common.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.Random;

public class MilkBottleItem extends Item {
	public MilkBottleItem(Item.Properties builder) {
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entity) {
		clearRandomEffect(worldIn, entity);

		if (entity instanceof ServerPlayer serverplayerentity) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (entity instanceof Player && !((Player) entity).getAbilities().instabuild)
			stack.shrink(1);

		if (stack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (entity instanceof Player playerentity && !((Player) entity).getAbilities().instabuild) {
				ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
				if (!playerentity.getInventory().add(itemstack)) {
					playerentity.drop(itemstack, false);
				}
			}

			return stack;
		}

	}

	public static void clearRandomEffect(Level worldIn, LivingEntity entity) {
		if (!worldIn.isClientSide) {
			ImmutableList<MobEffectInstance> effects = ImmutableList.copyOf(entity.getActiveEffects());
			if (effects.size() > 0) {
				Random rand = new Random();
				MobEffectInstance effectToRemove = effects.get(rand.nextInt(effects.size()));
				entity.removeEffect(effectToRemove.getEffect());
			}
		}
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		return ItemUtils.startUsingInstantly(worldIn, playerIn, handIn);
	}
}