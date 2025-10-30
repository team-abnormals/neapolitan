package com.teamabnormals.neapolitan.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.neapolitan.common.block.entity.ChillboxBlockEntity;
import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.core.other.NeapolitanMaterials;
import com.teamabnormals.neapolitan.core.other.NeapolitanModelLayers;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDataComponents;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder.Direct;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;

public class ChillboxRenderer implements BlockEntityRenderer<ChillboxBlockEntity> {
	private final ModelPart top;
	private final ModelPart base;

	public ChillboxRenderer(BlockEntityRendererProvider.Context context) {
		ModelPart base = context.bakeLayer(NeapolitanModelLayers.CHILLBOX_BASE);
		this.base = base.getChild("base");
		ModelPart sides = context.bakeLayer(NeapolitanModelLayers.CHILLBOX_TOP);
		this.top = sides.getChild("top");
	}

	public static LayerDefinition createBaseLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 1.0F, 14.0F, 16.0F, 14.0F,
				EnumSet.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN)), PartPose.offsetAndRotation(0.0F, 16.0F, 16.0F, (float) Math.PI, 0.0F, 0.0F));
		return LayerDefinition.create(mesh, 64, 64);
	}

	public static LayerDefinition createTopLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild("top", CubeListBuilder.create().texOffs(-27, 1).addBox(1.0F, 16.0F, 1.0F, 14.0F, 0.0F, 14.0F, EnumSet.of(Direction.UP)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(mesh, 16, 16);
	}

	@Override
	public void render(ChillboxBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		poseStack.pushPose();
		Direction direction = blockEntity.getDirection();
		poseStack.translate(0.5, 0.0, 0.5);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - direction.toYRot()));
		poseStack.translate(-0.5, 0.0, -0.5);

		this.base.render(poseStack, NeapolitanMaterials.CHILLBOX_BASE.buffer(bufferSource, RenderType::entityCutout), packedLight, packedOverlay);

		if (blockEntity.getItem(ChillboxBlockEntity.RESULT_SLOT).has(NeapolitanDataComponents.ICE_CREAM)) {
			IceCream iceCream = blockEntity.getItem(ChillboxBlockEntity.RESULT_SLOT).get(NeapolitanDataComponents.ICE_CREAM);
			for (int i = 1; i <= 3; i++) {
				this.top.render(poseStack, NeapolitanMaterials.getChillboxMaterial(iceCream.flavor(i).getKey(), i).buffer(bufferSource, RenderType::entityCutout), packedLight, packedOverlay);
			}
		} else {
			this.top.render(poseStack, NeapolitanMaterials.CHILLBOX_TOP.buffer(bufferSource, RenderType::entityCutout), packedLight, packedOverlay);
		}

		poseStack.popPose();
	}

	@Override
	public AABB getRenderBoundingBox(ChillboxBlockEntity blockEntity) {
		BlockPos pos = blockEntity.getBlockPos();
		return new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.3, pos.getZ() + 1.0);
	}
}
