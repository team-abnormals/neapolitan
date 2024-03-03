package com.teamabnormals.neapolitan.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.neapolitan.common.block.entity.NeapolitanSkullBlockEntity;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

@EventBusSubscriber(modid = Neapolitan.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeapolitanBlockEntityTypes {
	public static final BlockEntitySubRegistryHelper HELPER = Neapolitan.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final RegistryObject<BlockEntityType<NeapolitanSkullBlockEntity>> SKULL = HELPER.createBlockEntity("skull", NeapolitanSkullBlockEntity::new, () -> Set.of(NeapolitanBlocks.CHIMPANZEE_HEAD.get(), NeapolitanBlocks.CHIMPANZEE_WALL_HEAD.get()));
}