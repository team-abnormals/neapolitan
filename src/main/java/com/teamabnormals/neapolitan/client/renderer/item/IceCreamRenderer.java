package com.teamabnormals.neapolitan.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.blueprint.client.model.DynamicItemModel;
import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.common.item.component.IceCreamOverride;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDataComponents;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Holder.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.Optional;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class IceCreamRenderer extends BlockEntityWithoutLevelRenderer {
	public static final IceCreamRenderer INSTANCE = new IceCreamRenderer();

	public IceCreamRenderer() {
		super(null, null);
	}

	@Override
	public void onResourceManagerReload(ResourceManager manager) {
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		IceCream iceCream = stack.get(NeapolitanDataComponents.ICE_CREAM);
		if (iceCream != null) {
			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.scale(-1.0F, 1.0F, -1.0F);

			ResourceLocation[] flavors = new ResourceLocation[]{
					iceCream.primaryFlavor().key().location(),
					iceCream.secondaryFlavor().key().location(),
					iceCream.tertiaryFlavor().key().location()
			};

			String container = "bowl_";
			boolean cone = stack.is(NeapolitanItems.ICE_CREAM_CONE);
			if (cone) {
				container = "cone_";
			}

			ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
			if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.registryAccess() != null) {
				Optional<Reference<IceCreamOverride>> override = IceCreamOverride.getFromIceCream(Minecraft.getInstance().level.registryAccess(), iceCream);
				if (override.isPresent()) {
					IceCreamOverride value = override.get().value();
					Optional<ResourceLocation> location = cone ? value.coneItemModel() : value.bowlItemModel();
					if (location.isPresent()) {
						BakedModel model = itemRenderer.getItemModelShaper().getModelManager().getModel(ModelResourceLocation.standalone(location.get().withPrefix("item/ice_cream/")));
						itemRenderer.render(stack, ItemDisplayContext.FIXED, true, poseStack, buffer, packedLight, packedOverlay, model);
						poseStack.popPose();
						return;
					}
				}
			}

			for (int i = 0; i < 3; i++) {
				ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(flavors[i].getNamespace(), "item/ice_cream/" + container + "layer" + (i + 1) + "_" + flavors[i].getNamespace() + "_" + flavors[i].getPath());
				BakedModel model = itemRenderer.getItemModelShaper().getModelManager().getModel(ModelResourceLocation.standalone(loc));
				itemRenderer.render(stack, ItemDisplayContext.FIXED, true, poseStack, buffer, packedLight, packedOverlay, model);
			}

//			ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(flavors[0].getNamespace(), "item/ice_cream/" + container + flavors[0].getPath() + "_topping");
//			BakedModel bowlItemModel = itemRenderer.getItemModelShaper().getModelManager().getModel(ModelResourceLocation.standalone(loc));
//			itemRenderer.render(stack, ItemDisplayContext.FIXED, true, poseStack, buffer, packedLight, packedOverlay, bowlItemModel);

			poseStack.popPose();
		}
	}

	public static class IceCreamClientExtension implements IClientItemExtensions {
		@Override
		public BlockEntityWithoutLevelRenderer getCustomRenderer() {
			return INSTANCE;
		}
	}

	@SubscribeEvent
	public static void registerAdditional(ModelEvent.RegisterAdditional event) {
		DynamicItemModel.register(event, "ice_cream");
	}
}