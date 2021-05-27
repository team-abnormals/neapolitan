package com.minecraftabnormals.neapolitan.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlyParticle extends SpriteTexturedParticle {
	protected final IAnimatedSprite animatedSprite;
	private float speed;
	private float angle;
	private float turnSpeed;
	
	private FlyParticle(IAnimatedSprite animatedSpriteIn, ClientWorld world, double x, double y, double z, float speedIn, float angleIn, float turnSpeedIn) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.01;
		this.motionY *= 0.01;
		this.motionZ *= 0.01;
		this.speed = speedIn;
		this.angle = angleIn;
		this.turnSpeed = turnSpeedIn;
		this.particleGravity = 1.0F;
		this.maxAge = 40;
		this.animatedSprite = animatedSpriteIn;
		this.selectSpriteWithAge(this.animatedSprite);
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.age++ >= this.maxAge) {
			this.setExpired();
		} else {
			Vector3d vector3d = Vector3d.fromPitchYaw(0.0F, this.angle);
			this.motionX = vector3d.x * (double) this.speed;
			this.motionY = (double) Math.sin(this.age * 0.3F) * 0.08D + 0.006D;
			this.motionZ = vector3d.z * (double) this.speed;
			this.move(this.motionX, this.motionY, this.motionZ);
			this.angle += this.turnSpeed;
			while (this.angle > 180.0F) {
				this.angle -= 360.0F;
			}
			while (this.angle < -180.0F) {
				this.angle += 360.0F;
			}
			this.selectSpriteWithAge(this.animatedSprite);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite animatedSprite;

		public Factory(IAnimatedSprite animatedSpriteIn) {
			this.animatedSprite = animatedSpriteIn;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new FlyParticle(this.animatedSprite, worldIn, x, y, z, (float)xSpeed, (float)ySpeed, (float)zSpeed);
		}
	}
}