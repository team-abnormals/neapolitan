package com.minecraftabnormals.neapolitan.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SoupItem;
import net.minecraft.world.World;

public class HealingSoupItem extends SoupItem {
    private final float healAmount;

    public HealingSoupItem(float healAmount, Properties builder) {
        super(builder);
        this.healAmount = healAmount;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		HealingItem.applyHealing(this.healAmount, worldIn, entityLiving);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
