package com.minecraftabnormals.neapolitan.common.block;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class FlavoredCakeBlock extends CakeBlock {
    private EffectType effectType;
    private Food food;
    
    public FlavoredCakeBlock(Food food, EffectType effectType, Properties properties) {
        super(properties);
        this.effectType = effectType;
        this.food = food;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            ItemStack itemstack = player.getHeldItem(handIn);
            if (this.eatSlice(worldIn, pos, state, player).isSuccessOrConsume()) {
                return ActionResultType.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResultType.CONSUME;
            }
        }

        return this.eatSlice(worldIn, pos, state, player);
    }

    private ActionResultType eatSlice(IWorld world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canEat(false)) {
            return ActionResultType.PASS;
        } else {
            player.addStat(Stats.EAT_CAKE_SLICE);
            player.getFoodStats().addStats(food.getHealing(), food.getSaturation());
            int i = state.get(BITES);

            if (this == NeapolitanBlocks.STRAWBERRY_CAKE.get()) player.heal(1.0F);
            
            ImmutableList<EffectInstance> effects = ImmutableList.copyOf(player.getActivePotionEffects());
            if (this.getEffectType() != null) {
                for (int j = 0; j < effects.size(); ++j) {
                    Effect effect = effects.get(j).getPotion();
                    if (effect.getEffectType() == this.getEffectType() || (this.getEffectType() == EffectType.HARMFUL && effect == Effects.BAD_OMEN) || this.getEffectType() == EffectType.NEUTRAL) {
                        player.removePotionEffect(effect);
                    }
                }
            } else {
            	Random rand = new Random();
            	EffectInstance effectToRemove = effects.get(rand.nextInt(effects.size()));
            	player.removePotionEffect(effectToRemove.getPotion());
            }
            
            for(Pair<EffectInstance, Float> pair : food.getEffects()) {
                if (!world.isRemote() && pair.getFirst() != null && world.getRandom().nextFloat() < pair.getSecond()) {
                    player.addPotionEffect(new EffectInstance(pair.getFirst()));
                }
             }
            
            if (i < 6) {
                world.setBlockState(pos, state.with(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                world.removeBlock(pos, false);
            }

            return ActionResultType.SUCCESS;
        }
    }

    public EffectType getEffectType() {
        return this.effectType;
    }
}
