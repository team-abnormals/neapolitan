package com.minecraftabnormals.neapolitan.common.entity;

import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEntities;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BananarrowEntity extends AbstractArrowEntity {

	public BananarrowEntity(EntityType<? extends BananarrowEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public BananarrowEntity(World worldIn, double x, double y, double z) {
		super(NeapolitanEntities.BANANARROW.get(), x, y, z, worldIn);
	}

	public BananarrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		this(NeapolitanEntities.BANANARROW.get(), world);
	}

	public BananarrowEntity(World worldIn, LivingEntity shooter) {
		super(NeapolitanEntities.BANANARROW.get(), shooter, worldIn);
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(NeapolitanItems.BANANARROW.get());
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}