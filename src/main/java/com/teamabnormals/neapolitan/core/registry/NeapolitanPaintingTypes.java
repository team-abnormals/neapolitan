package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.api.banner.BannerManager;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class NeapolitanPaintingTypes {
	public static final DeferredRegister<Motive> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Neapolitan.MOD_ID);

	public static final RegistryObject<Motive> SCREAM = PAINTING_TYPES.register("scream", () -> new Motive(32, 32));
	public static final RegistryObject<Motive> CONE = PAINTING_TYPES.register("cone", () -> new Motive(16, 32));

	public static final BannerPattern CHIMPANZEE = BannerManager.createPattern("mca", "chimpanzee", "mky");
}
