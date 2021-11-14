package com.minecraftabnormals.neapolitan.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

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
				
				if (vector3d.get(direction.getAxis()) == 0.0D) {
					int x = MathHelper.floor(this.position().x + normal.getX() * this.getBbWidth() * 0.51F);
					int y = MathHelper.floor(this.position().y);
					int z = MathHelper.floor(this.position().z + normal.getZ() * this.getBbWidth() * 0.51F);
					BlockPos blockpos = new BlockPos(x, y, z);
					BlockState blockstate = this.level.getBlockState(blockpos);
					if (blockstate.getCollisionShape(this.level, blockpos).isEmpty()) {
						BlockPos blockpos1 = blockpos.below();
						BlockState blockstate1 = this.level.getBlockState(blockpos1);
						if (blockstate1.collisionExtendsVertically(this.level, blockpos1, this) && !blockstate1.is(NeapolitanTags.Blocks.AGILITY_NON_CLIMBABLE)) {
							cir.setReturnValue(true);
						}
					} else if (!blockstate.is(NeapolitanTags.Blocks.AGILITY_NON_CLIMBABLE)) {
						cir.setReturnValue(true);
					}
				}
			}
		}
	}
}
