package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.api.banner.BannerManager;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.*;

public final class NeapolitanBanners {
	public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Neapolitan.MOD_ID);

	public static final RegistryObject<Motive> SCREAM = PAINTINGS.register("scream", () -> new Motive(32, 32));
	public static final RegistryObject<Motive> CONE = PAINTINGS.register("cone", () -> new Motive(16, 32));

	public static final BannerPattern CHIMPANZEE = BannerManager.createPattern("mca", "chimpanzee", "mky");
}
