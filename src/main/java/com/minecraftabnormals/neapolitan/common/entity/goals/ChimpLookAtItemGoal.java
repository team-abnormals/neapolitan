package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;

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
		if (!this.chimpanzee.getAction().canBeInterrupted()) {
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
					this.chimpanzee.setItemInHand(Hand.MAIN_HAND, offhanditem);
					this.chimpanzee.setItemInHand(Hand.OFF_HAND, ItemStack.EMPTY);
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
				if (this.chimpanzee.getRandom().nextInt(10) == 0 && this.itemStack.getItem().is(NeapolitanTags.Items.CHIMPANZEE_SHAKEABLE_BUCKETS)) {
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

		if (item.is(NeapolitanTags.Items.CHIMPANZEE_APE_MODE_ITEMS) && this.chimpanzee.getApeModeTime() <= 0) {
			this.lookTimer = 40;
			this.chimpanzee.setApeModeTime(1200 + this.chimpanzee.getRandom().nextInt(1200));
		} else if (this.shouldPlayWithItem(item) && this.chimpanzee.isDoingAction(ChimpanzeeAction.LOOKING_AT_ITEM)) {
			this.lookTimer = 60 + this.chimpanzee.getRandom().nextInt(20);
			this.chimpanzee.setAction(ChimpanzeeAction.PLAYING_WITH_ITEM);
		} else {
			if (!this.chimpanzee.isBaby() && item instanceof ArmorItem && ((ArmorItem) item).getSlot() == EquipmentSlotType.HEAD && this.chimpanzee.getItemBySlot(EquipmentSlotType.HEAD).isEmpty()) {
				this.chimpanzee.setItemSlot(EquipmentSlotType.HEAD, this.itemStack);
				this.chimpanzee.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
				this.chimpanzee.setDropChance(EquipmentSlotType.HEAD, 2.0F);
				this.playEquipSound(item);
			} else {
				if (this.chimpanzee.getMainHandItem() == this.itemStack) {
					this.chimpanzee.throwHeldItem(Hand.MAIN_HAND);
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
		Vector3d vector3d = RandomPositionGenerator.getPos(this.chimpanzee, 10, 5);
		if (vector3d != null) {
			this.chimpanzee.getNavigation().moveTo(vector3d.x, vector3d.y, vector3d.z, 1.25D);
		}

		this.chimpanzee.getJumpControl().jump();
		this.chimpanzee.playScreamSound();
	}

	private boolean shouldLookAtItem(ItemStack itemStackIn) {
		return !this.chimpanzee.isSnack(itemStackIn) && !this.chimpanzee.isFavoriteItem(itemStackIn);
	}

	private boolean shouldPlayWithItem(Item item) {
		return item instanceof TieredItem || item instanceof FireworkRocketItem || item.is(NeapolitanTags.Items.CHIMPANZEE_SHAKEABLE_BUCKETS);
	}
}
