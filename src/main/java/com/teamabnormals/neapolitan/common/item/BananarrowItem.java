package com.teamabnormals.neapolitan.common.item;

import com.teamabnormals.neapolitan.common.entity.projectile.Bananarrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class BananarrowItem extends ArrowItem {

	public BananarrowItem(Properties builder) {
		super(builder);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
		return new Bananarrow(level, shooter, ammo.copyWithCount(1), weapon);
	}
}