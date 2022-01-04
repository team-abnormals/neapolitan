package com.teamabnormals.neapolitan.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class MintBoostParticle extends TextureSheetParticle {
	public MintBoostParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		float f = this.random.nextFloat() * 0.1F + 0.2F;
		this.rCol = f;
		this.gCol = f;
		this.bCol = f;
		this.setSize(0.02F, 0.02F);
		this.quadSize *= this.random.nextFloat() * 0.6F + 0.5F;
		this.xd *= 0.02F;
		this.yd *= 0.02F;
		this.zd *= 0.02F;
		this.lifetime = (int) (20.0D / (Math.random() * 0.8D + 0.2D));
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().move(x, y, z));
		this.setLocationFromBoundingbox();
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.lifetime-- <= 0) {
			this.remove();
		} else {
			this.move(this.xd, this.yd, this.zd);
			this.xd *= 0.99D;
			this.yd *= 0.99D;
			this.zd *= 0.99D;
		}
	}

	public static class Factory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Factory(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			MintBoostParticle mintBoostParticle = new MintBoostParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			mintBoostParticle.pickSprite(this.spriteSet);
			mintBoostParticle.setColor(1.0F, 1.0F, 1.0F);
			return mintBoostParticle;
		}
	}
}
