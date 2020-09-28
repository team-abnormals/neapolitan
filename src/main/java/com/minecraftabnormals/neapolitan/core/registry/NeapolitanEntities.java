package com.minecraftabnormals.neapolitan.core.registry;

import com.minecraftabnormals.neapolitan.client.renderer.BananaPeelRenderer;
import com.minecraftabnormals.neapolitan.client.renderer.BananarrowRenderer;
import com.minecraftabnormals.neapolitan.common.entity.BananaPeelEntity;
import com.minecraftabnormals.neapolitan.common.entity.BananarrowEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Neapolitan.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeapolitanEntities {
	public static final RegistryHelper HELPER = Neapolitan.REGISTRY_HELPER;

	public static final RegistryObject<EntityType<BananarrowEntity>> BANANARROW 	= HELPER.createEntity("bananarrow", BananarrowEntity::new, BananarrowEntity::new, EntityClassification.MISC, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<BananaPeelEntity>> BANANA_PEEL 	= HELPER.createEntity("banana_peel", BananaPeelEntity::new, BananaPeelEntity::new, EntityClassification.MISC, 0.5F, 0.5F);

	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(NeapolitanEntities.BANANARROW.get(), BananarrowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(NeapolitanEntities.BANANA_PEEL.get(), BananaPeelRenderer::new);
	}
}
