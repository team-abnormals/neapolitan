package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;

public class ChimpPlayWithHelmetGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private ItemStack itemStack;
	private int playTimer;

	public ChimpPlayWithHelmetGoal(ChimpanzeeEntity chimpanzeeIn) {
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!this.chimpanzee.getAction().canBeInterrupted()) {
			return false;
		} else if (!this.chimpanzee.getNavigation().isDone()) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(200) > 0) {
			return false;
		} else {
			ItemStack helmet = this.chimpanzee.getItemBySlot(EquipmentSlotType.HEAD);
			if (!helmet.isEmpty() && helmet.getItem() instanceof ArmorItem && ((ArmorItem) helmet.getItem()).getSlot() == EquipmentSlotType.HEAD) {
				this.itemStack = helmet;
				return true;
			}

			return false;
		}
	}

	@Override
	public void start() {
		this.playTimer = 60 + this.chimpanzee.getRandom().nextInt(40);
		this.chimpanzee.setAction(ChimpanzeeAction.PLAYING_WITH_HELMET);
		this.chimpanzee.getNavigation().stop();
	}

	@Override
	public boolean canContinueToUse() {
		if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.PLAYING_WITH_HELMET)) {
			return false;
		} else if (this.chimpanzee.getItemBySlot(EquipmentSlotType.HEAD) != this.itemStack) {
			return false;
		} else {
			return this.playTimer > 0;
		}
	}

	@Override
	public void stop() {
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		--this.playTimer;

		if (this.playTimer == 6 && !EnchantmentHelper.hasBindingCurse(this.itemStack) ) {
			ItemEntity itementity = new ItemEntity(this.chimpanzee.level, this.chimpanzee.getX(), this.chimpanzee.getY(1.0D), this.chimpanzee.getZ(), this.itemStack);
			Vector3d vector3d = itementity.getDeltaMovement();
			itementity.setDeltaMovement(vector3d.x * 1.6D, vector3d.y, vector3d.z * 1.6D);
			itementity.setPickUpDelay(40);
			itementity.setThrower(this.chimpanzee.getUUID());
			this.chimpanzee.level.addFreshEntity(itementity);

			this.chimpanzee.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
			this.itemStack = this.chimpanzee.getItemBySlot(EquipmentSlotType.HEAD);
		}
	}
}
