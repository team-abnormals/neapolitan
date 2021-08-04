package com.minecraftabnormals.neapolitan.common.item;

import com.minecraftabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
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
	public ActionResultType useOn(ItemUseContext context) {
		if (context.getClickedFace() == Direction.UP) {
			World world = context.getLevel();
			this.placeBanana(world, context.getClickLocation().x(), context.getClickLocation().y(), context.getClickLocation().z(), context.getRotation());
			this.handleOpening(world, context.getPlayer(), context.getHand(), context.getItemInHand());
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
		if (!NeapolitanTags.EntityTypes.UNAFFECTED_BY_SLIPPING.contains(target.getType())) {
			World world = player.level;
			this.placeBanana(world, target.getX(), target.getY(), target.getZ(), player.getViewYRot(1.0F));
			this.handleOpening(world, player, hand, stack);
			return ActionResultType.sidedSuccess(world.isClientSide);
		} else {
			return super.interactLivingEntity(stack, player, target, hand);
		}
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		this.throwBanana(world, player, player.getViewXRot(1.0F), player.getViewYRot(1.0F));
		this.handleOpening(world, player, hand, player.getItemInHand(hand));
		return ActionResult.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
	}

	private void placeBanana(World world, double posX, double posY, double posZ, float yaw) {
		if (!world.isClientSide) {
			BananaPeelEntity bananapeel = NeapolitanEntities.BANANA_PEEL.get().create(world);
			bananapeel.moveTo(posX, posY, posZ, yaw, 0.0F);
			world.addFreshEntity(bananapeel);
		}
	}

	private void throwBanana(World world, PlayerEntity player, float pitch, float yaw) {
		if (!world.isClientSide) {
			BananaPeelEntity bananapeel = NeapolitanEntities.BANANA_PEEL.get().create(world);
			bananapeel.moveTo(player.getX(), player.getEyeY() - (double)0.1F, player.getZ(), yaw, 0.0F);
			float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F)) * 0.6F;
			float f1 = -MathHelper.sin(pitch * ((float)Math.PI / 180F)) * 0.6F;
			float f2 = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F)) * 0.6F;
			Vector3d vector3d = player.getDeltaMovement();
			bananapeel.setDeltaMovement(new Vector3d((double)f, (double)f1, (double)f2).add(vector3d.x, player.isOnGround() ? 0.0D : vector3d.y, vector3d.z));
			world.addFreshEntity(bananapeel);
		}
	}

	private void handleOpening(World world, PlayerEntity player, Hand hand, ItemStack stack) {
		if (!world.isClientSide) {
			player.getCooldowns().addCooldown(this, 5);

			if (!player.abilities.instabuild) {
				stack.shrink(1);
			}

			if (player instanceof ServerPlayerEntity) {
				ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
				CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
				serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
			}

			if (stack.isEmpty()) {
				player.setItemInHand(hand, new ItemStack(NeapolitanItems.BANANA.get(), 1 + world.random.nextInt(3)));
			} else {
				ItemStack itemstack = new ItemStack(NeapolitanItems.BANANA.get(), 1 + world.random.nextInt(3));
				if (!player.inventory.add(itemstack)) {
					player.drop(itemstack, false);
				}
			}
		}

		world.playSound(player, player.blockPosition(), NeapolitanSounds.ITEM_BANANA_BUNCH_OPEN.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
	}
}
