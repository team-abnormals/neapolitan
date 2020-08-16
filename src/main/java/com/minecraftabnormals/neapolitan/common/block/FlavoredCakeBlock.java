package com.minecraftabnormals.neapolitan.common.block;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
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

    public FlavoredCakeBlock(EffectType effectType, Properties properties) {
        super(properties);
        this.effectType = effectType;
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
            player.getFoodStats().addStats(2, 0.1F);
            int i = state.get(BITES);

            ImmutableList<EffectInstance> effects = ImmutableList.copyOf(player.getActivePotionEffects());
            for (int j = 0; j < effects.size(); ++j) {
                Effect effect = effects.get(j).getPotion();
                if (effect.getEffectType() == this.getEffectType() || (this.getEffectType() == EffectType.HARMFUL && effect == Effects.BAD_OMEN) || this.getEffectType() == EffectType.NEUTRAL) {
                    player.removePotionEffect(effect);
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
