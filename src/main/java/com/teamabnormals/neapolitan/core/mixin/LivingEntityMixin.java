package com.teamabnormals.neapolitan.core.mixin;

import com.teamabnormals.neapolitan.core.registry.NeapolitanEffects;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("RETURN"), method = "onClimbable", cancellable = true)
	private void isClimbing(CallbackInfoReturnable<Boolean> cir) {
		if (((LivingEntity) (Object) this).hasEffect(NeapolitanEffects.AGILITY.get())) {
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				Vec3i normal = direction.getNormal();
				Vec3 vector3d = this.collide(Vec3.atLowerCornerOf(normal));
				if (Math.abs(vector3d.get(direction.getAxis())) <= 0.2D) {
					cir.setReturnValue(true);
				}
			}
		}
	}
}
