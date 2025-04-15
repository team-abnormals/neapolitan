package com.teamabnormals.neapolitan.core.mixin;

import com.teamabnormals.neapolitan.core.registry.datapack.NeapolitanBiomes;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Sheep.class)
public abstract class SheepMixin extends Animal {
	@Shadow
	public abstract void setColor(DyeColor color);

	protected SheepMixin(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	@Inject(method = "finalizeSpawn", at = @At("TAIL"))
	public void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
		if (level.getBiome(this.blockPosition()).is(NeapolitanBiomes.STRAWBERRY_FIELDS) && level.getRandom().nextBoolean())
			this.setColor(DyeColor.RED);
	}
}