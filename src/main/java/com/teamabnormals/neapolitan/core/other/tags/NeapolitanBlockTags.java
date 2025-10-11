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

	public static final TagKey<Block> STORAGE_BLOCKS_CHOCOLATE_BAR = TagUtil.blockTag("c", "storage_blocks/chocolate_bar");
	public static final TagKey<Block> STORAGE_BLOCKS_VANILLA_POD = TagUtil.blockTag("c", "storage_blocks/vanilla_pod");
	public static final TagKey<Block> STORAGE_BLOCKS_DRIED_VANILLA_POD = TagUtil.blockTag("c", "storage_blocks/dried_vanilla_pod");
	public static final TagKey<Block> STORAGE_BLOCKS_STRAWBERRY = TagUtil.blockTag("c", "storage_blocks/strawberry");
	public static final TagKey<Block> STORAGE_BLOCKS_WHITE_STRAWBERRY = TagUtil.blockTag("c", "storage_blocks/white_strawberry");
	public static final TagKey<Block> STORAGE_BLOCKS_BANANA = TagUtil.blockTag("c", "storage_blocks/banana");
	public static final TagKey<Block> STORAGE_BLOCKS_MINT_LEAVES = TagUtil.blockTag("c", "storage_blocks/mint_leaves");
	public static final TagKey<Block> STORAGE_BLOCKS_ADZUKI_BEANS = TagUtil.blockTag("c", "storage_blocks/adzuki_beans");
	public static final TagKey<Block> STORAGE_BLOCKS_ROASTED_ADZUKI_BEANS = TagUtil.blockTag("c", "storage_blocks/roasted_adzuki_beans");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(Neapolitan.MOD_ID, name);
	}
}