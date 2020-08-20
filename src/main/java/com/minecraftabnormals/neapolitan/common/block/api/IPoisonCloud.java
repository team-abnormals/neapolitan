package com.minecraftabnormals.neapolitan.common.block.api;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanCriteriaTriggers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

public interface IPoisonCloud {
    default void createPoisonCloud(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        //FROM: @link CreeperEntity
        if (!player.getHeldItemMainhand().getItem().isIn(Tags.Items.SHEARS) && !player.abilities.isCreativeMode) {
            if (player instanceof ServerPlayerEntity)
                NeapolitanCriteriaTriggers.VANILLA_POISON.trigger((ServerPlayerEntity)player);
            AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
            areaeffectcloudentity.addEffect(new EffectInstance(new EffectInstance(Effects.POISON, 300)));
            areaeffectcloudentity.setRadius(1.0F);
            areaeffectcloudentity.setRadiusOnUse(-0.5F);
            areaeffectcloudentity.setWaitTime(10);
            areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
            areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());

            world.addEntity(areaeffectcloudentity); 
        }
    }
}
