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

	public static JsonCodecProvider<BiomeModifier> create(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		RegistryAccess access = RegistryAccess.builtinCopy();
		Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<PlacedFeature> placedFeatures = access.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
		HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();

		addModifier(modifiers, "add_feature/strawberry_bush", new AddFeaturesBiomeModifier(tag(biomeRegistry, NeapolitanBiomeTags.HAS_STRAWBERRY_BUSH), of(placedFeatures, NeapolitanPlacedFeatures.PATCH_STRAWBERRY_BUSH), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/adzuki_sprouts", new AddFeaturesBiomeModifier(tag(biomeRegistry, NeapolitanBiomeTags.HAS_ADZUKI_SPROUTS), of(placedFeatures, NeapolitanPlacedFeatures.PATCH_ADZUKI_SPROUTS), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/vanilla_vine", new AddFeaturesBiomeModifier(tag(biomeRegistry, NeapolitanBiomeTags.HAS_VANILLA_VINE), of(placedFeatures, NeapolitanPlacedFeatures.PATCH_VANILLA_VINE), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/mint_pond", new AddFeaturesBiomeModifier(tag(biomeRegistry, NeapolitanBiomeTags.HAS_MINT_POND), of(placedFeatures, NeapolitanPlacedFeatures.MINT_POND), GenerationStep.Decoration.VEGETAL_DECORATION));

		addModifier(modifiers, "add_feature/banana_plant/common", new AddFeaturesBiomeModifier(tag(biomeRegistry, NeapolitanBiomeTags.HAS_COMMON_BANANA_PLANT), of(placedFeatures, NeapolitanPlacedFeatures.BANANA_PLANT_COMMON), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/banana_plant/uncommon", new AddFeaturesBiomeModifier(tag(biomeRegistry, NeapolitanBiomeTags.HAS_UNCOMMON_BANANA_PLANT), of(placedFeatures, NeapolitanPlacedFeatures.BANANA_PLANT_UNCOMMON), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/banana_plant/rare", new AddFeaturesBiomeModifier(tag(biomeRegistry, NeapolitanBiomeTags.HAS_RARE_BANANA_PLANT), of(placedFeatures, NeapolitanPlacedFeatures.BANANA_PLANT_RARE), GenerationStep.Decoration.VEGETAL_DECORATION));

		return JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, Neapolitan.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, access), ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
	}

	private static HolderSet<Biome> tag(Registry<Biome> biomeRegistry, TagKey<Biome> tagKey) {
		return new HolderSet.Named<>(biomeRegistry, tagKey);
	}

	private static void addModifier(HashMap<ResourceLocation, BiomeModifier> modifiers, String name, BiomeModifier modifier) {
		modifiers.put(new ResourceLocation(Neapolitan.MOD_ID, name), modifier);
	}

	@SafeVarargs
	@SuppressWarnings("ConstantConditions")
	private static HolderSet<PlacedFeature> of(Registry<PlacedFeature> placedFeatures, RegistryObject<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(registryObject -> placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}
}