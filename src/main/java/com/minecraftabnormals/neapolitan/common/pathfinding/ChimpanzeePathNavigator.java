package com.minecraftabnormals.neapolitan.common.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChimpanzeePathNavigator extends GroundPathNavigator {
	private BlockPos targetPosition;

	public ChimpanzeePathNavigator(MobEntity entityLivingIn, World worldIn) {
		super(entityLivingIn, worldIn);
	}

	@Override
	public Path getPathToPos(BlockPos pos, int p_179680_2_) {
		this.targetPosition = pos;
		return super.getPathToPos(pos, p_179680_2_);
	}

	@Override
	public Path getPathToEntity(Entity entityIn, int p_75494_2_) {
		this.targetPosition = entityIn.getPosition();
		return super.getPathToEntity(entityIn, p_75494_2_);
	}

	@Override
	public boolean tryMoveToEntityLiving(Entity entityIn, double speedIn) {
		Path path = this.getPathToEntity(entityIn, 0);
		if (path != null) {
			return this.setPath(path, speedIn);
		} else {
			this.targetPosition = entityIn.getPosition();
			this.speed = speedIn;
			return true;
		}
	}

	@Override
	public void tick() {
		if (!this.noPath()) {
			super.tick();
		} else {
			if (this.targetPosition != null) {
				if (!this.targetPosition.withinDistance(this.entity.getPositionVec(), Math.max((double)this.entity.getWidth(), 1.0D)) && (!(this.entity.getPosY() > (double)this.targetPosition.getY()) || !(new BlockPos((double)this.targetPosition.getX(), this.entity.getPosY(), (double)this.targetPosition.getZ())).withinDistance(this.entity.getPositionVec(), Math.max((double)this.entity.getWidth(), 1.0D)))) {
					this.entity.getMoveHelper().setMoveTo((double)this.targetPosition.getX(), (double)this.targetPosition.getY(), (double)this.targetPosition.getZ(), this.speed);
				} else {
					this.targetPosition = null;
				}
			}
		}
	}

	public void clearPath() {
		super.clearPath();
		this.targetPosition = null;
	}
}
