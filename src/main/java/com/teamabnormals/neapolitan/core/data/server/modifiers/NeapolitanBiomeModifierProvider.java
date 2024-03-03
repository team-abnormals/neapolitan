package com.teamabnormals.neapolitan.core.data.server.modifiers;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeapolitanBiomeModifierProvider {

	public static void bootstrap(BootstapContext<BiomeModifier> context) {
		addFeature(context, "strawberry_bush", NeapolitanBiomeTags.HAS_STRAWBERRY_BUSH, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_STRAWBERRY_BUSH);
		addFeature(context, "adzuki_sprouts", NeapolitanBiomeTags.HAS_ADZUKI_SPROUTS, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_ADZUKI_SPROUTS);
		addFeature(context, "vanilla_vine", NeapolitanBiomeTags.HAS_VANILLA_VINE, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_VANILLA_VINE);
		addFeature(context, "mint_pond", NeapolitanBiomeTags.HAS_MINT_POND, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.MINT_POND);

		addFeature(context, "banana_plant/common", NeapolitanBiomeTags.HAS_COMMON_BANANA_PLANT, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_COMMON);
		addFeature(context, "banana_plant/uncommon", NeapolitanBiomeTags.HAS_UNCOMMON_BANANA_PLANT, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_UNCOMMON);
		addFeature(context, "banana_plant/rare", NeapolitanBiomeTags.HAS_RARE_BANANA_PLANT, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_RARE);
	}

	@SafeVarargs
	private static void addFeature(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
		register(context, name, () -> new AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
	}

	private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
		context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Neapolitan.MOD_ID, "add_feature/" + name)), modifier.get());
	}

	@SafeVarargs
	private static HolderSet<PlacedFeature> featureSet(BootstapContext<?> context, ResourceKey<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
	}
}