package com.minecraftabnormals.neapolitan.common.entity;

import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class PlantainSpiderEntity extends SpiderEntity {
	public PlantainSpiderEntity(EntityType<? extends PlantainSpiderEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static boolean canPlantainSpiderSpawn(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
		return pos.getY() > 60 && canMonsterSpawnInLight(type, worldIn, reason, pos, randomIn);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return SpiderEntity.func_234305_eI_().createMutableAttribute(Attributes.MAX_HEALTH, 8.0D);
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof LivingEntity && NeapolitanConfig.COMMON.plantainSpidersGiveSlipping.get()) {
				LivingEntity livingEntity = (LivingEntity) entityIn;
				livingEntity.addPotionEffect(new EffectInstance(NeapolitanEffects.SLIPPING.get(), this.world.getDifficulty().getId() * 60));
			}
			return true;
		} else {
			return false;
		}
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		return spawnDataIn;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.40F;
	}
}