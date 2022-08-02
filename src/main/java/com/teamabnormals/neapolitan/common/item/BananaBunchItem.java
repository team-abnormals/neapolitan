package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.common.entity.projectile.BananaPeel;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanSoundEvents;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BananaBunchItem extends Item {

	public BananaBunchItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getClickedFace() == Direction.UP) {
			Level world = context.getLevel();
			this.placeBanana(world, context.getClickLocation().x(), context.getClickLocation().y(), context.getClickLocation().z(), context.getRotation());
			this.handleOpening(world, context.getPlayer(), context.getHand(), context.getItemInHand());
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
		if (!target.getType().is(NeapolitanEntityTypeTags.UNAFFECTED_BY_SLIPPING)) {
			Level world = player.level;
			this.placeBanana(world, target.getX(), target.getY(), target.getZ(), player.getViewYRot(1.0F));
			this.handleOpening(world, player, hand, stack);
			return InteractionResult.sidedSuccess(world.isClientSide);
		} else {
			return super.interactLivingEntity(stack, player, target, hand);
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		this.throwBanana(world, player, player.getViewXRot(1.0F), player.getViewYRot(1.0F));
		this.handleOpening(world, player, hand, player.getItemInHand(hand));
		return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
	}

	private void placeBanana(Level world, double posX, double posY, double posZ, float yaw) {
		if (!world.isClientSide) {
			BananaPeel bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(world);
			bananapeel.moveTo(posX, posY, posZ, yaw, 0.0F);
			world.addFreshEntity(bananapeel);
		}
	}

	private void throwBanana(Level world, Player player, float pitch, float yaw) {
		if (!world.isClientSide) {
			BananaPeel bananapeel = NeapolitanEntityTypes.BANANA_PEEL.get().create(world);
			bananapeel.moveTo(player.getX(), player.getEyeY() - (double) 0.1F, player.getZ(), yaw, 0.0F);
			float f = -Mth.sin(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F)) * 0.6F;
			float f1 = -Mth.sin(pitch * ((float) Math.PI / 180F)) * 0.6F;
			float f2 = Mth.cos(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F)) * 0.6F;
			Vec3 vector3d = player.getDeltaMovement();
			bananapeel.setDeltaMovement(new Vec3(f, f1, f2).add(vector3d.x, player.isOnGround() ? 0.0D : vector3d.y, vector3d.z));
			world.addFreshEntity(bananapeel);
		}
	}

	private void handleOpening(Level world, Player player, InteractionHand hand, ItemStack stack) {
		if (!world.isClientSide) {
			player.getCooldowns().addCooldown(this, 5);

			if (!player.getAbilities().instabuild) {
				stack.shrink(1);
			}

			if (player instanceof ServerPlayer serverplayerentity) {
				CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
				serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
			}

			if (stack.isEmpty()) {
				player.setItemInHand(hand, new ItemStack(NeapolitanItems.BANANA.get(), 1 + world.random.nextInt(3)));
			} else {
				ItemStack itemstack = new ItemStack(NeapolitanItems.BANANA.get(), 1 + world.random.nextInt(3));
				if (!player.getInventory().add(itemstack)) {
					player.drop(itemstack, false);
				}
			}
		}

		world.playSound(player, player.blockPosition(), NeapolitanSoundEvents.ITEM_BANANA_BUNCH_OPEN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
	}
}
