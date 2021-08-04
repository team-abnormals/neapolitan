package com.minecraftabnormals.neapolitan.common.entity.goals;

import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.common.entity.util.ChimpanzeeAction;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.EnumSet;

public class PlayNoteBlockGoal extends MoveToBlockGoal {
	private final ChimpanzeeEntity chimpanzee;
	private int timePlayed;
	private int noteTime;

	public PlayNoteBlockGoal(ChimpanzeeEntity chimpanzeeIn, double speed, int length) {
		super(chimpanzeeIn, speed, length, 6);
		this.chimpanzee = chimpanzeeIn;
		this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		return this.chimpanzee.getRandom().nextInt(400) == 0 && !this.chimpanzee.isPassenger() && super.canUse();
	}

	@Override
	public boolean canContinueToUse() {
		if (this.timePlayed > 320 && this.chimpanzee.getRandom().nextInt(300) == 0) {
			return false;
		} else if (this.chimpanzee.isPassenger()) {
			return false;
		} else if (this.timePlayed > 0 && !this.isReachedTarget()) {
			return false;
		} else {
			return super.canContinueToUse();
		}
	}

	@Override
	public void start() {
		super.start();
		this.timePlayed = 0;
		this.noteTime = 20;
	}

	@Override
	public void stop() {
		super.stop();
		this.chimpanzee.setDefaultAction();
	}

	@Override
	public void tick() {
		super.tick();

		this.chimpanzee.getLookControl().setLookAt(this.blockPos.getX() + 0.5D, this.blockPos.getY() + 0.5D, this.blockPos.getZ() + 0.5D, (float)(this.chimpanzee.getMaxHeadYRot() + 20), (float)this.chimpanzee.getMaxHeadXRot());

		if (this.isReachedTarget() && this.chimpanzee.getNavigation().isDone() && this.chimpanzee.getAction().canBeInterrupted()) {
			this.chimpanzee.setAction(ChimpanzeeAction.DRUMMING);

			if (--this.noteTime <= 0) {
				BlockState state = this.chimpanzee.level.getBlockState(this.blockPos);
				int note = state.getValue(NoteBlock.NOTE);
				note = MathHelper.clamp(note + this.chimpanzee.getRandom().nextInt(7) - 3, 0, 24);
				this.chimpanzee.level.setBlock(this.blockPos, state.setValue(NoteBlock.NOTE, note), 3);
				this.chimpanzee.level.blockEvent(this.blockPos, Blocks.NOTE_BLOCK, 0, 0);

				this.noteTime = 8 + this.chimpanzee.getRandom().nextInt(5);
			}

			++this.timePlayed;
		} else {
			this.chimpanzee.setDefaultAction();
		}
	}

	@Override
	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		return worldIn.isEmptyBlock(pos.above()) && worldIn.isEmptyBlock(pos.above().above()) && worldIn.getBlockState(pos).getBlock() == Blocks.NOTE_BLOCK && !this.getBlockBeingPlayed((World) worldIn, pos);
	}

	private boolean getBlockBeingPlayed(World worldIn, BlockPos pos) {
		return !worldIn.getEntitiesOfClass(ChimpanzeeEntity.class, new AxisAlignedBB(pos.above()), (chimpanzee) -> {
			return chimpanzee != this.chimpanzee && chimpanzee.getAction() == ChimpanzeeAction.DRUMMING;
		}).isEmpty();
	}
}
