package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ChimpLookAtItemGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private ItemStack itemStack;
	private int lookTimer;
	private boolean wasHurt;

	public ChimpLookAtItemGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.DEFAULT)) {
			return false;
		} else if (!this.chimpanzee.getNavigation().isDone()) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(160) > 0) {
			return false;
		} else {
			ItemStack mainhanditem = this.chimpanzee.getMainHandItem();
			ItemStack offhanditem = this.chimpanzee.getOffhandItem();

			if (!mainhanditem.isEmpty()) {
				if (this.shouldLookAtItem(mainhanditem)) {
					this.itemStack = mainhanditem;
					return true;
				}
			} else if (!offhanditem.isEmpty()) {
				if (this.shouldLookAtItem(offhanditem)) {
					this.chimpanzee.setItemInHand(InteractionHand.MAIN_HAND, offhanditem);
					this.chimpanzee.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
					this.itemStack = offhanditem;
					return true;
				}
			}

			return false;
		}
	}

	@Override
	public void start() {
		this.lookTimer = 60 + this.chimpanzee.getRandom().nextInt(60);
		this.wasHurt = false;
		this.chimpanzee.setAction(ChimpanzeeAction.LOOKING_AT_ITEM);
		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public boolean canContinueToUse() {
		if (this.chimpanzee.getMainHandItem() != this.itemStack) {
			return false;
		} else {
			return this.lookTimer > 0;
		}
	}

	@Override
	public void stop() {
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		--this.lookTimer;

		if (this.lookTimer == 0) {
			this.doItemInteraction();
		} else if (chimpanzee.isDoingAction(ChimpanzeeAction.PLAYING_WITH_ITEM)) {
			if (this.lookTimer == 20) {
				if (this.chimpanzee.getRandom().nextInt(10) == 0 && this.itemStack.is(BlueprintItemTags.BUCKETS_EMPTY)) {
					this.chimpanzee.spawnItemFromBucket(new ItemStack(NeapolitanItems.BANANA.get()), this.chimpanzee.getMainArm());
				}
			} else if (this.lookTimer == 4) {
				if (this.itemStack.getItem() instanceof TieredItem) {
					this.chimpanzee.hurt(DamageSource.GENERIC, 0.0F);
					this.wasHurt = true;
				} else if (this.itemStack.getItem() instanceof FireworkRocketItem) {
					this.chimpanzee.setOffFirework(this.itemStack, this.chimpanzee.getMainArm());
					this.chimpanzee.getMainHandItem().shrink(1);
					this.itemStack = this.chimpanzee.getMainHandItem();
				}
			}
		}
	}

	private void doItemInteraction() {
		Item item = this.itemStack.getItem();

		if (this.itemStack.is(NeapolitanItemTags.CHIMPANZEE_APE_MODE_ITEMS) && this.chimpanzee.getApeModeTime() <= 0) {
			this.lookTimer = 40;
			this.chimpanzee.setApeModeTime(1200 + this.chimpanzee.getRandom().nextInt(1200));
		} else if (this.shouldPlayWithItem(this.itemStack) && this.chimpanzee.isDoingAction(ChimpanzeeAction.LOOKING_AT_ITEM)) {
			this.lookTimer = 60 + this.chimpanzee.getRandom().nextInt(20);
			this.chimpanzee.setAction(ChimpanzeeAction.PLAYING_WITH_ITEM);
		} else {
			if (!this.chimpanzee.isBaby() && item instanceof ArmorItem && ((ArmorItem) item).getSlot() == EquipmentSlot.HEAD && this.chimpanzee.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
				this.chimpanzee.setItemSlot(EquipmentSlot.HEAD, this.itemStack);
				this.chimpanzee.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
				this.chimpanzee.setDropChance(EquipmentSlot.HEAD, 2.0F);
				this.playEquipSound(item);
			} else {
				if (this.chimpanzee.getMainHandItem() == this.itemStack) {
					this.chimpanzee.throwHeldItem(InteractionHand.MAIN_HAND);
				}
			}

			if (this.wasHurt) {
				this.runAway();
			}

			this.chimpanzee.setDefaultAction();
		}
	}

	private void playEquipSound(Item item) {
		SoundEvent soundevent = ((ArmorItem) item).getMaterial().getEquipSound();
		this.chimpanzee.playSound(soundevent, 1.0F, 1.0F);
	}

	private void runAway() {
		Vec3 vector3d = DefaultRandomPos.getPos(this.chimpanzee, 10, 5);
		if (vector3d != null) {
			this.chimpanzee.getNavigation().moveTo(vector3d.x, vector3d.y, vector3d.z, 1.25D);
		}

		this.chimpanzee.getJumpControl().jump();
		this.chimpanzee.playScreamSound();
	}

	private boolean shouldLookAtItem(ItemStack stack) {
		return !this.chimpanzee.isSnack(stack) && !this.chimpanzee.isFavoriteItem(stack);
	}

	private boolean shouldPlayWithItem(ItemStack stack) {
		return stack.getItem() instanceof TieredItem || stack.getItem() instanceof FireworkRocketItem || stack.is(BlueprintItemTags.BUCKETS_EMPTY);
	}
}
