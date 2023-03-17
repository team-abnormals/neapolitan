package com.teamabnormals.neapolitan.core.mixin;

import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	@Shadow
	public abstract boolean hasEffect(MobEffect effect);

	public LivingEntityMixin(EntityType<?> entityTypeIn, Level level) {
		super(entityTypeIn, level);
	}

	@Inject(at = @At("RETURN"), method = "onClimbable", cancellable = true)
	private void onClimbable(CallbackInfoReturnable<Boolean> cir) {
		if (this.level.isClientSide() && this.hasEffect(NeapolitanMobEffects.AGILITY.get())) {
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				Vec3i normal = direction.getNormal();
				Vec3 vec3 = this.collide(Vec3.atLowerCornerOf(normal));
				if (Math.abs(vec3.get(direction.getAxis())) <= 0.2D) {
					cir.setReturnValue(true);
				}
			}
		}
	}
}
