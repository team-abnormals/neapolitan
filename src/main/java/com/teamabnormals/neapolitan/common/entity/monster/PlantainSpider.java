package com.teamabnormals.neapolitan.common.entity.monster;

import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.Random;

public class PlantainSpider extends Spider {
	public PlantainSpider(EntityType<? extends PlantainSpider> type, Level worldIn) {
		super(type, worldIn);
	}

	public static boolean canPlantainSpiderSpawn(EntityType<? extends Monster> type, ServerLevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn) {
		return pos.getY() > 60 && checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Spider.createAttributes().add(Attributes.MAX_HEALTH, 8.0D);
	}

	public boolean doHurtTarget(Entity entityIn) {
		if (super.doHurtTarget(entityIn)) {
			if (entityIn instanceof LivingEntity livingEntity && NeapolitanConfig.COMMON.plantainSpidersGiveSlipping.get()) {
				livingEntity.addEffect(new MobEffectInstance(NeapolitanMobEffects.SLIPPING.get(), this.level.getDifficulty().getId() * 60));
			}
			return true;
		} else {
			return false;
		}
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		return spawnDataIn;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.40F;
	}
}