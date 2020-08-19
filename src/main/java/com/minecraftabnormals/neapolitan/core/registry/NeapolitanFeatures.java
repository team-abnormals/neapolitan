package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.common.world.gen.feature.StrawberryFeature;
import com.minecraftabnormals.neapolitan.core.Neapolitan;

import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NeapolitanFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Neapolitan.MODID);
    public static final RegistryObject<Feature<BlockClusterFeatureConfig>> STRAWBERRY_PATCH = FEATURES.register("strawberry_patch", () -> new StrawberryFeature(BlockClusterFeatureConfig.field_236587_a_));
}
