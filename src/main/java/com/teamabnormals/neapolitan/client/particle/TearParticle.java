package com.teamabnormals.neapolitan.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TearParticle extends TextureSheetParticle {
	private TearParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.xd *= 0.1F;
		this.yd *= 0.1F;
		this.zd *= 0.1F;
		this.xd += motionX * 0.4D;
		this.yd += motionY * 0.4D;
		this.zd += motionZ * 0.4D;
		this.quadSize *= 0.75F;
		this.gravity = 0.06F;
		this.lifetime = (int) (20.0D / (Math.random() * 0.8D + 0.2D));
	}

	@Override
	public float getQuadSize(float scaleFactor) {
		return this.quadSize * Mth.clamp(((float) this.age + scaleFactor) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			this.move(this.xd, this.yd, this.zd);
			this.xd *= 0.7F;
			this.yd *= 0.7F;
			this.zd *= 0.7F;
			this.yd -= this.gravity;
			if (this.onGround) {
				this.remove();
			}
		}
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			TearParticle tearparticle = new TearParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			tearparticle.pickSprite(this.spriteSet);
			return tearparticle;
		}
	}
}
