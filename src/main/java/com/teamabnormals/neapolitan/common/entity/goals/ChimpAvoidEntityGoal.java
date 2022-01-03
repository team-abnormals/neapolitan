package com.teamabnormals.neapolitan.common.entity.goals;

import com.teamabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.function.Predicate;

public class ChimpAvoidEntityGoal<T extends LivingEntity> extends Goal {
	private final ChimpanzeeEntity chimpanzee;
	private final double walkSpeedModifier;
	private final double sprintSpeedModifier;
	protected T toAvoid;
	protected final float maxDist;
	protected Path path;
	protected final PathNavigation pathNav;
	protected final Class<T> avoidClass;
	protected final Predicate<LivingEntity> predicateOnAvoidEntity;
	private final TargetingConditions avoidEntityTargeting;


	public ChimpAvoidEntityGoal(ChimpanzeeEntity chimpanzeeIn, Class<T> avoidClassIn, float maxDistIn, double walkSpeedModifierIn, double sprintSpeedModifierIn) {
		this.chimpanzee = chimpanzeeIn;
		this.avoidClass = avoidClassIn;
		this.maxDist = maxDistIn;
		this.walkSpeedModifier = walkSpeedModifierIn;
		this.sprintSpeedModifier = sprintSpeedModifierIn;
		this.predicateOnAvoidEntity = EntitySelector.NO_CREATIVE_OR_SPECTATOR::test;
		this.pathNav = chimpanzeeIn.getNavigation();
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		this.avoidEntityTargeting = TargetingConditions.forCombat().range(this.maxDist).selector(this.predicateOnAvoidEntity);
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		this.toAvoid = this.chimpanzee.level.getNearestEntity(this.chimpanzee.level.getEntitiesOfClass(this.avoidClass, this.chimpanzee.getBoundingBox().inflate(this.maxDist, 3.0D, this.maxDist), (p_148078_) -> true), this.avoidEntityTargeting, this.chimpanzee, this.chimpanzee.getX(), this.chimpanzee.getY(), this.chimpanzee.getZ());
		if (this.toAvoid == null) {
			return false;
		} else {
			Vec3 vector3d = DefaultRandomPos.getPosAway(this.chimpanzee, 10, 5, this.toAvoid.position());
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