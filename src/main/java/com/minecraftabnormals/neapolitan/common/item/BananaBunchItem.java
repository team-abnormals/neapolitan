package com.minecraftabnormals.neapolitan.common.item;

import com.minecraftabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanSounds;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BananaBunchItem extends Item {

	public BananaBunchItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		if (context.getFace() == Direction.UP) {
			World world = context.getWorld();
			this.placeBanana(world, context.getHitVec().getX(), context.getHitVec().getY(), context.getHitVec().getZ(), context.getPlacementYaw());
			this.handleOpening(world, context.getPlayer(), context.getHand(), context.getItem());
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
	}

	@Override
	public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
		World world = player.world;
		this.placeBanana(world, target.getPosX(), target.getPosY(), target.getPosZ(), player.getYaw(1.0F));
		this.handleOpening(world, player, hand, stack);
		return ActionResultType.func_233537_a_(world.isRemote);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		this.throwBanana(world, player, player.getPitch(1.0F), player.getYaw(1.0F));
		this.handleOpening(world, player, hand, player.getHeldItem(hand));
		return ActionResult.func_233538_a_(player.getHeldItem(hand), world.isRemote());
	}

	private void placeBanana(World world, double posX, double posY, double posZ, float yaw) {
		if (!world.isRemote) {
			BananaPeelEntity bananapeel = NeapolitanEntities.BANANA_PEEL.get().create(world);
			bananapeel.setLocationAndAngles(posX, posY, posZ, yaw, 0.0F);
			world.addEntity(bananapeel);
		}
	}

	private void throwBanana(World world, PlayerEntity player, float pitch, float yaw) {
		if (!world.isRemote) {
			BananaPeelEntity bananapeel = NeapolitanEntities.BANANA_PEEL.get().create(world);
			bananapeel.setLocationAndAngles(player.getPosX(), player.getPosYEye() - (double)0.1F, player.getPosZ(), yaw, 0.0F);
			float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F)) * 0.6F;
			float f1 = -MathHelper.sin(pitch * ((float)Math.PI / 180F)) * 0.6F;
			float f2 = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F)) * 0.6F;
			Vector3d vector3d = player.getMotion();
			bananapeel.setMotion(new Vector3d((double)f, (double)f1, (double)f2).add(vector3d.x, player.isOnGround() ? 0.0D : vector3d.y, vector3d.z));
			world.addEntity(bananapeel);
		}
	}

	private void handleOpening(World world, PlayerEntity player, Hand hand, ItemStack stack) {
		if (!world.isRemote) {
			player.getCooldownTracker().setCooldown(this, 5);

			if (!player.abilities.isCreativeMode) {
				stack.shrink(1);
			}

			if (player instanceof ServerPlayerEntity) {
				ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
				CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
				serverplayerentity.addStat(Stats.ITEM_USED.get(this));
			}

			if (stack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(NeapolitanItems.BANANA.get(), 1 + world.rand.nextInt(3)));
			} else {
				ItemStack itemstack = new ItemStack(NeapolitanItems.BANANA.get(), 1 + world.rand.nextInt(3));
				if (!player.inventory.addItemStackToInventory(itemstack)) {
					player.dropItem(itemstack, false);
				}
			}
		}

		world.playSound(player, player.getPosition(), NeapolitanSounds.ITEM_BANANA_BUNCH_OPEN.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
	}
}
