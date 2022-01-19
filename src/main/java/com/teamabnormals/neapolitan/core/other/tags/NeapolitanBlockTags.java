package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class NeapolitanBlockTags {
	public static final Tag.Named<Block> VANILLA_PLANTABLE_ON = blockTag("vanilla_plantable_on");
	public static final Tag.Named<Block> UNAFFECTED_BY_MINT = blockTag("unaffected_by_mint");
	public static final Tag.Named<Block> CHIMPANZEE_JUMPING_BLOCKS = blockTag("chimpanzee_jumping_blocks");

	private static Tag.Named<Block> blockTag(String name) {
		return TagUtil.blockTag(Neapolitan.MOD_ID, name);
	}
}
