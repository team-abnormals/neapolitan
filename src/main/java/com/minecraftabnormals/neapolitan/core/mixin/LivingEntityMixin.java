package com.minecraftabnormals.neapolitan.core.mixin;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(at = @At("RETURN"), method = "onClimbable", cancellable = true)
	private void isClimbing(CallbackInfoReturnable<Boolean> cir) {
		if (((LivingEntity) this.getEntity()).hasEffect(NeapolitanEffects.AGILITY.get())) {
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				Vector3i normal = direction.getNormal();
				Vector3d vector3d = this.collide(Vector3d.atLowerCornerOf(normal));
				if (Math.abs(vector3d.get(direction.getAxis())) <= 0.2D) {
					cir.setReturnValue(true);
				}
			}
		}
	}
}
