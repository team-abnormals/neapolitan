package com.minecraftabnormals.neapolitan.common.entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class PlantainSpiderEntity extends SpiderEntity {
	public PlantainSpiderEntity(EntityType<? extends PlantainSpiderEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public PlantainSpiderEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(NeapolitanEntities.PLANTAIN_SPIDER.get(), world);
	}

	public static boolean canPlantainSpiderSpawn(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
		return worldIn.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(worldIn, pos, randomIn) && pos.getY() > 60 && canSpawnOn(type, worldIn, reason, pos, randomIn);
	}

	public static AttributeModifierMap.MutableAttribute func_234277_m_() {
		return SpiderEntity.func_234305_eI_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D);
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entityIn;
				int i = 0;
				if (this.world.getDifficulty() == Difficulty.NORMAL) {
					i = 4;
				} else if (this.world.getDifficulty() == Difficulty.HARD) {
					i = 9;
				}

				if (i > 0) {
					livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, i * 20, 0));
				}
				livingEntity.addPotionEffect(new EffectInstance(NeapolitanEffects.SLIPPING.get(), i * 30, 0));
			}

			return true;
		} else {
			return false;
		}
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		return spawnDataIn;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.45F;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(NeapolitanItems.PLANTAIN_SPIDER_SPAWN_EGG.get());
	}
}