package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.neapolitan.common.block.entity.ChillboxBlockEntity;
import com.teamabnormals.neapolitan.common.block.entity.NeapolitanSkullBlockEntity;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public class NeapolitanBlockEntityTypes {
	public static final BlockEntitySubRegistryHelper BLOCK_ENTITY_TYPES = Neapolitan.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChillboxBlockEntity>> CHILLBOX = BLOCK_ENTITY_TYPES.createBlockEntity("chillbox", ChillboxBlockEntity::new, () -> Set.of(NeapolitanBlocks.CHILLBOX.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NeapolitanSkullBlockEntity>> SKULL = BLOCK_ENTITY_TYPES.createBlockEntity("skull", NeapolitanSkullBlockEntity::new, () -> Set.of(NeapolitanBlocks.CHIMPANZEE_HEAD.get(), NeapolitanBlocks.CHIMPANZEE_WALL_HEAD.get()));
}