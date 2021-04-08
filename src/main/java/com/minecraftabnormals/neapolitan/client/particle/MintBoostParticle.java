package com.minecraftabnormals.neapolitan.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class MintBoostParticle extends SpriteTexturedParticle {
	public MintBoostParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		float f = this.rand.nextFloat() * 0.1F + 0.2F;
		this.particleRed = f;
		this.particleGreen = f;
		this.particleBlue = f;
		this.setSize(0.02F, 0.02F);
		this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
		this.motionX *= 0.02F;
		this.motionY *= 0.02F;
		this.motionZ *= 0.02F;
		this.maxAge = (int) (20.0D / (Math.random() * 0.8D + 0.2D));
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}

	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.maxAge-- <= 0) {
			this.setExpired();
		} else {
			this.move(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.99D;
			this.motionY *= 0.99D;
			this.motionZ *= 0.99D;
		}
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			MintBoostParticle mintBoostParticle = new MintBoostParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			mintBoostParticle.selectSpriteRandomly(this.spriteSet);
			mintBoostParticle.setColor(1.0F, 1.0F, 1.0F);
			return mintBoostParticle;
		}
	}
}
