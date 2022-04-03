package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class NeapolitanBlockTags {
	public static final TagKey<Block> VANILLA_PLANTABLE_ON = blockTag("vanilla_plantable_on");
	public static final TagKey<Block> UNAFFECTED_BY_MINT = blockTag("unaffected_by_mint");
	public static final TagKey<Block> CHIMPANZEE_JUMPING_BLOCKS = blockTag("chimpanzee_jumping_blocks");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(Neapolitan.MOD_ID, name);
	}
}
