package com.teamabnormals.neapolitan.common.entity.goal;

import com.teamabnormals.neapolitan.common.entity.animal.Chimpanzee;
import com.teamabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;

public class ChimpPlayNoteBlockGoal extends MoveToBlockGoal {
	private final Chimpanzee chimpanzee;
	private int timePlayed;
	private int noteTime;

	public ChimpPlayNoteBlockGoal(Chimpanzee chimpanzeeIn, double speed, int length) {
		super(chimpanzeeIn, speed, length, 6);
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (!this.chimpanzee.isDoingAction(ChimpanzeeAction.DEFAULT, ChimpanzeeAction.CLIMBING)) {
			return false;
		} else if (this.chimpanzee.getRandom().nextInt(600) != 0) {
			return false;
		} else {
			return this.findNearestBlock();
		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.timePlayed > 320 && this.chimpanzee.getRandom().nextInt(250) == 0) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (this.timePlayed > 0 && (!this.isReachedTarget() || !this.chimpanzee.isSitting())) {
			return false;
		} else {
			return super.canContinueToUse();
		}
	}

	@Override
	public void start() {
		super.start();
		this.noteTime = 20;
	}

	@Override
	public void stop() {
		super.stop();
		this.timePlayed = 0;
		this.chimpanzee.setDefaultAction();
		if (this.chimpanzee.canStandUp()) {
			this.chimpanzee.setSitting(false);
		}
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		super.tick();

		this.chimpanzee.getLookControl().setLookAt(this.blockPos.getX() + 0.5D, this.blockPos.getY() + 0.5D, this.blockPos.getZ() + 0.5D, (float) (this.chimpanzee.getMaxHeadYRot() + 20), (float) this.chimpanzee.getMaxHeadXRot());

		if (this.isReachedTarget() && this.chimpanzee.getNavigation().isDone() && this.chimpanzee.getAction().canBeInterrupted()) {
			this.chimpanzee.setAction(ChimpanzeeAction.DRUMMING);
			this.chimpanzee.setSitting(true);

			if (--this.noteTime <= 0) {
				if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.chimpanzee.level, this.chimpanzee)) {
					BlockState state = this.chimpanzee.level.getBlockState(this.blockPos);
					int note = state.getValue(NoteBlock.NOTE);
					note = Mth.clamp(note + this.chimpanzee.getRandom().nextInt(7) - 3, 0, 24);
					this.chimpanzee.level.setBlock(this.blockPos, state.setValue(NoteBlock.NOTE, note), 3);
				}
				this.chimpanzee.level.blockEvent(this.blockPos, Blocks.NOTE_BLOCK, 0, 0);
				this.noteTime = 8 + this.chimpanzee.getRandom().nextInt(5);
			}

			++this.timePlayed;
		}
	}

	@Override
	protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
		return worldIn.isEmptyBlock(pos.above()) && worldIn.isEmptyBlock(pos.above().above()) && worldIn.getBlockState(pos).getBlock() == Blocks.NOTE_BLOCK && !this.isBlockBeingPlayed((Level) worldIn, pos);
	}

	private boolean isBlockBeingPlayed(Level worldIn, BlockPos pos) {
		return !worldIn.getEntitiesOfClass(Chimpanzee.class, new AABB(pos.above()), (chimpanzee) -> {
			return chimpanzee != this.chimpanzee && chimpanzee.isDoingAction(ChimpanzeeAction.DRUMMING);
		}).isEmpty();
	}
}
