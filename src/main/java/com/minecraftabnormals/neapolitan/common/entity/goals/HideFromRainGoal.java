package com.minecraftabnormals.neapolitan.common.entity.goals;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class HideFromRainGoal extends Goal {
	protected final ChimpanzeeEntity chimpanzee;
	private Vector3d shelterPos;
	private final double movementSpeed;
	private final World world;

	public HideFromRainGoal(ChimpanzeeEntity chimpanzeeIn, double movementSpeedIn) {
		this.chimpanzee = chimpanzeeIn;
		this.movementSpeed = movementSpeedIn;
		this.world = chimpanzeeIn.world;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	public boolean shouldExecute() {
		if (this.chimpanzee.getAttackTarget() != null) {
			return false;
		} else if (!this.world.isRaining()) {
			return false;
		} else if (!this.world.canSeeSky(this.chimpanzee.getPosition())) {
			return false;
		} else {
			return this.isPossibleShelter();
		}
	}

	protected boolean isPossibleShelter() {
		Vector3d vector3d = this.findPossibleShelter();
		if (vector3d == null) {
			return false;
		} else {
			this.shelterPos = vector3d;
			return true;
		}
	}


	public boolean shouldContinueExecuting() {
		return !this.chimpanzee.getNavigator().noPath();
	}

	public void startExecuting() {
		this.chimpanzee.getNavigator().tryMoveToXYZ(this.shelterPos.x, this.shelterPos.y, this.shelterPos.z, this.movementSpeed);
	}

	@Nullable
	protected Vector3d findPossibleShelter() {
		Random random = this.chimpanzee.getRNG();
		BlockPos blockpos = this.chimpanzee.getPosition();

		for(int i = 0; i < 10; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
			if (!this.world.canSeeSky(blockpos1) && this.chimpanzee.getBlockPathWeight(blockpos1) < 0.0F) {
				return Vector3d.copyCenteredHorizontally(blockpos1);
			}
		}

		return null;
	}
}
