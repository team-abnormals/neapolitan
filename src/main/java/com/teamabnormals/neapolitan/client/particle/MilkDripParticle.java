package com.teamabnormals.neapolitan.client.particle;

import com.teamabnormals.neapolitan.core.registry.NeapolitanParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.*;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;

@OnlyIn(Dist.CLIENT)
public class MilkDripParticle extends TextureSheetParticle {
	private final Fluid type;
	protected boolean isGlowing;

	MilkDripParticle(ClientLevel p_106051_, double p_106052_, double p_106053_, double p_106054_, Fluid p_106055_) {
		super(p_106051_, p_106052_, p_106053_, p_106054_);
		this.setSize(0.01F, 0.01F);
		this.gravity = 0.06F;
		this.type = p_106055_;
	}

	protected Fluid getType() {
		return this.type;
	}

	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public int getLightColor(float p_106065_) {
		return this.isGlowing ? 240 : super.getLightColor(p_106065_);
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		this.preMoveUpdate();
		if (!this.removed) {
			this.yd -= this.gravity;
			this.move(this.xd, this.yd, this.zd);
			this.postMoveUpdate();
			if (!this.removed) {
				this.xd *= 0.98F;
				this.yd *= 0.98F;
				this.zd *= 0.98F;
				BlockPos blockpos = new BlockPos(this.x, this.y, this.z);
				FluidState fluidstate = this.level.getFluidState(blockpos);
				if (fluidstate.getType() == this.type && this.y < (double) ((float) blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
					this.remove();
				}

			}
		}
	}

	protected void preMoveUpdate() {
		if (this.lifetime-- <= 0) {
			this.remove();
		}

	}

	protected void postMoveUpdate() {
	}

	@OnlyIn(Dist.CLIENT)
	static class DripHangParticle extends MilkDripParticle {
		private final ParticleOptions fallingParticle;

		DripHangParticle(ClientLevel p_106085_, double p_106086_, double p_106087_, double p_106088_, Fluid p_106089_, ParticleOptions p_106090_) {
			super(p_106085_, p_106086_, p_106087_, p_106088_, p_106089_);
			this.fallingParticle = p_106090_;
			this.gravity *= 0.02F;
			this.lifetime = 40;
		}

		protected void preMoveUpdate() {
			if (this.lifetime-- <= 0) {
				this.remove();
				this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
			}

		}

		protected void postMoveUpdate() {
			this.xd *= 0.02D;
			this.yd *= 0.02D;
			this.zd *= 0.02D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	static class DripstoneFallAndLandParticle extends MilkDripParticle.FallAndLandParticle {
		DripstoneFallAndLandParticle(ClientLevel p_171930_, double p_171931_, double p_171932_, double p_171933_, Fluid p_171934_, ParticleOptions p_171935_) {
			super(p_171930_, p_171931_, p_171932_, p_171933_, p_171934_, p_171935_);
		}

		protected void postMoveUpdate() {
			if (this.onGround) {
				this.remove();
				this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
				SoundEvent soundevent = this.getType() == Fluids.LAVA ? SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA : SoundEvents.POINTED_DRIPSTONE_DRIP_WATER;
				float f = Mth.randomBetween(this.random, 0.3F, 1.0F);
				this.level.playLocalSound(this.x, this.y, this.z, soundevent, SoundSource.BLOCKS, f, 1.0F, false);
			}

		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class DripstoneWaterFallProvider implements ParticleProvider<SimpleParticleType> {
		protected final SpriteSet sprite;

		public DripstoneWaterFallProvider(SpriteSet p_171981_) {
			this.sprite = p_171981_;
		}

		public Particle createParticle(SimpleParticleType p_171992_, ClientLevel p_171993_, double p_171994_, double p_171995_, double p_171996_, double p_171997_, double p_171998_, double p_171999_) {
			MilkDripParticle dripparticle = new MilkDripParticle.DripstoneFallAndLandParticle(p_171993_, p_171994_, p_171995_, p_171996_, ForgeMod.MILK.get(), NeapolitanParticles.MILK_SPLASH.get());
			dripparticle.setColor(0.87F, 0.95F, 0.96F);
			dripparticle.pickSprite(this.sprite);
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class DripstoneWaterHangProvider implements ParticleProvider<SimpleParticleType> {
		protected final SpriteSet sprite;

		public DripstoneWaterHangProvider(SpriteSet p_172002_) {
			this.sprite = p_172002_;
		}

		public Particle createParticle(SimpleParticleType p_172013_, ClientLevel p_172014_, double p_172015_, double p_172016_, double p_172017_, double p_172018_, double p_172019_, double p_172020_) {
			MilkDripParticle dripparticle = new MilkDripParticle.DripHangParticle(p_172014_, p_172015_, p_172016_, p_172017_, ForgeMod.MILK.get(), NeapolitanParticles.FALLING_DRIPSTONE_MILK.get());
			dripparticle.setColor(1.0F, 1.0F, 1.0F);
			dripparticle.pickSprite(this.sprite);
			return dripparticle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	static class FallAndLandParticle extends MilkDripParticle.FallingParticle {
		protected final ParticleOptions landParticle;

		FallAndLandParticle(ClientLevel p_106116_, double p_106117_, double p_106118_, double p_106119_, Fluid p_106120_, ParticleOptions p_106121_) {
			super(p_106116_, p_106117_, p_106118_, p_106119_, p_106120_);
			this.landParticle = p_106121_;
		}

		protected void postMoveUpdate() {
			if (this.onGround) {
				this.remove();
				this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
			}

		}
	}

	@OnlyIn(Dist.CLIENT)
	static class FallingParticle extends MilkDripParticle {
		FallingParticle(ClientLevel p_106132_, double p_106133_, double p_106134_, double p_106135_, Fluid p_106136_) {
			this(p_106132_, p_106133_, p_106134_, p_106135_, p_106136_, (int) (64.0D / (Math.random() * 0.8D + 0.2D)));
		}

		FallingParticle(ClientLevel p_172022_, double p_172023_, double p_172024_, double p_172025_, Fluid p_172026_, int p_172027_) {
			super(p_172022_, p_172023_, p_172024_, p_172025_, p_172026_);
			this.lifetime = p_172027_;
		}

		protected void postMoveUpdate() {
			if (this.onGround) {
				this.remove();
			}
		}
	}
}
