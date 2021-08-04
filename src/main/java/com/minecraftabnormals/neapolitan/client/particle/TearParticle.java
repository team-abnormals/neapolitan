package com.minecraftabnormals.neapolitan.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TearParticle extends SpriteTexturedParticle {
	private TearParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.xd *= (double)0.1F;
		this.yd *= (double)0.1F;
		this.zd *= (double)0.1F;
		this.xd += motionX * 0.4D;
		this.yd += motionY * 0.4D;
		this.zd += motionZ * 0.4D;
		this.quadSize *= 0.75F;
		this.gravity = 0.06F;
		this.lifetime = (int) (20.0D / (Math.random() * 0.8D + 0.2D));
	}

	public float getQuadSize(float scaleFactor) {
		return this.quadSize * MathHelper.clamp(((float)this.age + scaleFactor) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			this.move(this.xd, this.yd, this.zd);
			this.xd *= (double)0.7F;
			this.yd *= (double)0.7F;
			this.zd *= (double)0.7F;
			this.yd -= (double)this.gravity;
			if (this.onGround) {
				this.remove();
			}
		}
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			TearParticle tearparticle = new TearParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			tearparticle.pickSprite(this.spriteSet);
			return tearparticle;
		}
	}
}
