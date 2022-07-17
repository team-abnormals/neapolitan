package com.teamabnormals.neapolitan.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class NeapolitanBlockTags {
	public static final TagKey<Block> VANILLA_PLANTABLE_ON = blockTag("vanilla_plantable_on");
	public static final TagKey<Block> UNAFFECTED_BY_MINT = blockTag("unaffected_by_mint");
	public static final TagKey<Block> CHIMPANZEE_JUMPING_BLOCKS = blockTag("chimpanzee_jumping_blocks");

	public static final TagKey<Block> DROPS_VANILLA_CAKE_SLICE = TagUtil.blockTag("abnormals_delight", "drops_vanilla_cake_slice");
	public static final TagKey<Block> DROPS_CHOCOLATE_CAKE_SLICE = TagUtil.blockTag("abnormals_delight", "drops_chocolate_cake_slice");
	public static final TagKey<Block> DROPS_STRAWBERRY_CAKE_SLICE = TagUtil.blockTag("abnormals_delight", "drops_strawberry_cake_slice");
	public static final TagKey<Block> DROPS_BANANA_CAKE_SLICE = TagUtil.blockTag("abnormals_delight", "drops_banana_cake_slice");
	public static final TagKey<Block> DROPS_MINT_CAKE_SLICE = TagUtil.blockTag("abnormals_delight", "drops_mint_cake_slice");
	public static final TagKey<Block> DROPS_ADZUKI_CAKE_SLICE = TagUtil.blockTag("abnormals_delight", "drops_adzuki_cake_slice");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(Neapolitan.MOD_ID, name);
	}
}