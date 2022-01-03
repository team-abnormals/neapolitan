package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

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
		if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.DEFAULT)) {
			return false;
		} else if (!this.chimpanzee.getNavigation().isDone()) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(200) > 0) {
			return false;
		} else {
			ItemStack helmet = this.chimpanzee.getItemBySlot(EquipmentSlot.HEAD);
			if (!helmet.isEmpty() && helmet.getItem() instanceof ArmorItem && ((ArmorItem) helmet.getItem()).getSlot() == EquipmentSlot.HEAD) {
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
		} else if (this.chimpanzee.getItemBySlot(EquipmentSlot.HEAD) != this.itemStack) {
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

		if (this.playTimer == 6 && !EnchantmentHelper.hasBindingCurse(this.itemStack)) {
			ItemEntity itementity = new ItemEntity(this.chimpanzee.level, this.chimpanzee.getX(), this.chimpanzee.getY(1.0D), this.chimpanzee.getZ(), this.itemStack);
			Vec3 vector3d = itementity.getDeltaMovement();
			itementity.setDeltaMovement(vector3d.x * 1.6D, vector3d.y, vector3d.z * 1.6D);
			itementity.setPickUpDelay(40);
			itementity.setThrower(this.chimpanzee.getUUID());
			this.chimpanzee.level.addFreshEntity(itementity);

			this.chimpanzee.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
			this.itemStack = this.chimpanzee.getItemBySlot(EquipmentSlot.HEAD);
		}
	}
}
