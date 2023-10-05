package com.teamabnormals.neapolitan.core.data.server.modifiers;

import com.mojang.serialization.JsonOps;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanBiomeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanFeatures.NeapolitanPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeapolitanBiomeModifierProvider {
	private static final RegistryAccess ACCESS = RegistryAccess.builtinCopy();
	private static final Registry<Biome> BIOMES = ACCESS.registryOrThrow(Registry.BIOME_REGISTRY);
	private static final Registry<PlacedFeature> PLACED_FEATURES = ACCESS.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
	private static final HashMap<ResourceLocation, BiomeModifier> MODIFIERS = new HashMap<>();

	public static JsonCodecProvider<BiomeModifier> create(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		addFeature("strawberry_bush", NeapolitanBiomeTags.HAS_STRAWBERRY_BUSH, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_STRAWBERRY_BUSH);
		addFeature("adzuki_sprouts", NeapolitanBiomeTags.HAS_ADZUKI_SPROUTS, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_ADZUKI_SPROUTS);
		addFeature("vanilla_vine", NeapolitanBiomeTags.HAS_VANILLA_VINE, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.PATCH_VANILLA_VINE);
		addFeature("mint_pond", NeapolitanBiomeTags.HAS_MINT_POND, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.MINT_POND);

		addFeature("banana_plant/common", NeapolitanBiomeTags.HAS_COMMON_BANANA_PLANT, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_COMMON);
		addFeature("banana_plant/uncommon", NeapolitanBiomeTags.HAS_UNCOMMON_BANANA_PLANT, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_UNCOMMON);
		addFeature("banana_plant/rare", NeapolitanBiomeTags.HAS_RARE_BANANA_PLANT, GenerationStep.Decoration.VEGETAL_DECORATION, NeapolitanPlacedFeatures.BANANA_PLANT_RARE);

		return JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, Neapolitan.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, ACCESS), ForgeRegistries.Keys.BIOME_MODIFIERS, MODIFIERS);
	}

	private static void addModifier(String name, BiomeModifier modifier) {
		MODIFIERS.put(new ResourceLocation(Neapolitan.MOD_ID, name), modifier);
	}

	@SafeVarargs
	private static void addFeature(String name, TagKey<Biome> biomes, GenerationStep.Decoration step, RegistryObject<PlacedFeature>... features) {
		addModifier("add_feature/" + name, new AddFeaturesBiomeModifier(new HolderSet.Named<>(BIOMES, biomes), featureSet(features), step));
	}

	@SafeVarargs
	private static HolderSet<PlacedFeature> featureSet(RegistryObject<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(registryObject -> PLACED_FEATURES.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}
}