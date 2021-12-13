package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.function.Predicate;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.vector.Vector3d;

public class ChimpAvoidEntityGoal<T extends LivingEntity> extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private final double walkSpeedModifier;
	private final double sprintSpeedModifier;
	protected T toAvoid;
	protected final float maxDist;
	protected Path path;
	protected final PathNavigator pathNav;
	protected final Class<T> avoidClass;
	protected final Predicate<LivingEntity> predicateOnAvoidEntity;
	private final EntityPredicate avoidEntityTargeting;


	public ChimpAvoidEntityGoal(ChimpanzeeEntity chimpanzeeIn, Class<T> avoidClassIn, float maxDistIn, double walkSpeedModifierIn, double sprintSpeedModifierIn) {
		this.chimpanzee = chimpanzeeIn;
		this.avoidClass = avoidClassIn;
		this.maxDist = maxDistIn;
		this.walkSpeedModifier = walkSpeedModifierIn;
		this.sprintSpeedModifier = sprintSpeedModifierIn;
		this.predicateOnAvoidEntity = EntityPredicates.NO_CREATIVE_OR_SPECTATOR::test;
		this.pathNav = chimpanzeeIn.getNavigation();
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		this.avoidEntityTargeting = (new EntityPredicate()).range((double)this.maxDist).selector(this.predicateOnAvoidEntity);
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		this.toAvoid = this.chimpanzee.level.getNearestLoadedEntity(this.avoidClass, this.avoidEntityTargeting, this.chimpanzee, this.chimpanzee.getX(), this.chimpanzee.getY(), this.chimpanzee.getZ(), this.chimpanzee.getBoundingBox().inflate((double)this.maxDist, 3.0D, (double)this.maxDist));
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

	@Override
	public boolean canContinueToUse() {
		return !this.pathNav.isDone();
	}

	@Override
	public void start() {
		super.start();
		if (this.chimpanzee.distanceToSqr(this.toAvoid) < 4.0D) {
			this.chimpanzee.playScreamSound();
		}
	}

	@Override
	public void stop() {
		this.toAvoid = null;
	}

	@Override
	public void tick() {
		if (this.chimpanzee.distanceToSqr(this.toAvoid) < 4.0D) {
			this.chimpanzee.getNavigation().setSpeedModifier(this.sprintSpeedModifier);
		} else {
			this.chimpanzee.getNavigation().setSpeedModifier(this.walkSpeedModifier);
		}
	}
}