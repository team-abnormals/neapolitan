package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.List;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import com.minecraftabnormals.neapolitan.core.other.NeapolitanTags;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.vector.Vector3d;

public class ChimpGetScaredGoal extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	protected Entity toAvoid;
	public final double speedModifier;
	protected Path path;
	protected final PathNavigator pathNav;

	public ChimpGetScaredGoal(ChimpanzeeEntity chimpanzeeIn, double speed) {
		this.chimpanzee = chimpanzeeIn;
		this.speedModifier = speed;
		this.pathNav = chimpanzeeIn.getNavigation();
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.getAction() == ChimpanzeeAction.PLAYING_WITH_ITEM) {
			return false;
		} else {
			List<Entity> list = this.chimpanzee.level.getEntitiesOfClass(Entity.class, this.chimpanzee.getBoundingBox().inflate(8.0D, 4.0D, 8.0D), (entity) -> {
				return NeapolitanTags.EntityTypes.SCARES_CHIMPANZEES.contains(entity.getType());
			});
			double d0 = Double.MAX_VALUE;

			for(Entity entity : list) {
				double d1 = this.chimpanzee.distanceToSqr(entity);
				if (!(d1 > d0)) {
					d0 = d1;
					this.toAvoid = entity;
				}
			}

			if (this.toAvoid == null) {
				return false;
			} else {
				Vector3d vector3d = RandomPositionGenerator.getPosAvoid(this.chimpanzee, 10, 5, this.toAvoid.position());
				if (vector3d == null) {
					return false;
				} else if (this.toAvoid.distanceToSqr(vector3d.x, vector3d.y, vector3d.z) < this.toAvoid.distanceToSqr(this.chimpanzee)) {
					return false;
				} else {
					this.path = this.pathNav.createPath(vector3d.x, vector3d.y, vector3d.z, 0);
					return this.path != null;
				}
			}
		}
	}

	@Override
	public boolean canContinueToUse() {
		return !this.pathNav.isDone();
	}

	@Override
	public void start() {
		this.chimpanzee.getJumpControl().jump();
		this.chimpanzee.playScreamSound();
		this.pathNav.moveTo(this.path, this.speedModifier);
	}

	@Override
	public void stop() {
		this.toAvoid = null;
	}

	@Override
	public void tick() {
		if (this.toAvoid != null && this.toAvoid.isAlive()) {
			this.chimpanzee.getLookControl().setLookAt(this.toAvoid, 30.0F, 30.0F);
		}
	}
}