package com.minecraftabnormals.neapolitan.common.item;

import com.minecraftabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BananaBunchItem extends Item {

	public BananaBunchItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		playerIn.getCooldownTracker().setCooldown(this, 5);
		
		BananaPeelEntity bananaPeel = NeapolitanEntities.BANANA_PEEL.get().create(worldIn);
		bananaPeel.setLocationAndAngles(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), 0.0F, 0.0F);
		worldIn.addEntity(bananaPeel);
		
		if (!playerIn.abilities.isCreativeMode) {
			stack.shrink(1);
		}

		if (playerIn instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) playerIn;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}

		if (stack.isEmpty()) {
			playerIn.setHeldItem(handIn, new ItemStack(NeapolitanItems.BANANA.get(), 1 + worldIn.rand.nextInt(3)));
		} else {
			ItemStack itemstack = new ItemStack(NeapolitanItems.BANANA.get(), 1 + worldIn.rand.nextInt(3));
			worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 1.0F, 1.0F);
			if (!playerIn.inventory.addItemStackToInventory(itemstack)) {
				playerIn.dropItem(itemstack, false);
			}
		}

		return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
	}
}
