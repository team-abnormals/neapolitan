package com.teamabnormals.neapolitan.common.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FlavoredCandleCakeBlock extends AbstractCandleBlock {
	public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
	protected static final VoxelShape CAKE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
	protected static final VoxelShape CANDLE_SHAPE = Block.box(7.0D, 8.0D, 7.0D, 9.0D, 14.0D, 9.0D);
	protected static final VoxelShape SHAPE = Shapes.or(CAKE_SHAPE, CANDLE_SHAPE);
	private static final Map<Pair<Block, FlavoredCakeBlock>, FlavoredCandleCakeBlock> BY_CANDLE_AND_CAKE = Maps.newHashMap();
	private static final Iterable<Vec3> PARTICLE_OFFSETS = ImmutableList.of(new Vec3(0.5D, 1.0D, 0.5D));

	private final Supplier<Block> baseCake;
	private final Block candle;

	public FlavoredCandleCakeBlock(Supplier<Block> baseCake, Block candle, Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.FALSE));
		this.baseCake = baseCake;
		this.candle = candle;

		BY_CANDLE_AND_CAKE.put(Pair.of(candle, (FlavoredCakeBlock) baseCake.get()), this);
	}

	@Override
	protected Iterable<Vec3> getParticleOffsets(BlockState p_152868_) {
		return PARTICLE_OFFSETS;
	}

	@Override
	public VoxelShape getShape(BlockState p_152875_, BlockGetter p_152876_, BlockPos p_152877_, CollisionContext p_152878_) {
		return SHAPE;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE) && baseCake.get() instanceof FlavoredCakeBlock cakeBlock) {
			if (candleHit(result) && player.getItemInHand(hand).isEmpty() && state.getValue(LIT)) {
				extinguish(player, state, level, pos);
				return InteractionResult.sidedSuccess(level.isClientSide);
			} else {
				InteractionResult interactionresult = cakeBlock.eatSlice(level, pos, cakeBlock.defaultBlockState(), player);
				if (interactionresult.consumesAction()) {
					dropResources(state, level, pos);
				}

				return interactionresult;
			}
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(baseCake.get());
	}

	private static boolean candleHit(BlockHitResult result) {
		return result.getLocation().y - (double) result.getBlockPos().getY() > 0.5D;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152905_) {
		p_152905_.add(LIT);
	}

	@Override
	public BlockState updateShape(BlockState p_152898_, Direction p_152899_, BlockState p_152900_, LevelAccessor p_152901_, BlockPos p_152902_, BlockPos p_152903_) {
		return p_152899_ == Direction.DOWN && !p_152898_.canSurvive(p_152901_, p_152902_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_152898_, p_152899_, p_152900_, p_152901_, p_152902_, p_152903_);
	}

	@Override
	public boolean canSurvive(BlockState p_152891_, LevelReader p_152892_, BlockPos p_152893_) {
		return p_152892_.getBlockState(p_152893_.below()).isSolid();
	}

	@Override
	public int getAnalogOutputSignal(BlockState p_152880_, Level p_152881_, BlockPos p_152882_) {
		return CakeBlock.FULL_CAKE_SIGNAL;
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_152909_) {
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState p_152870_, BlockGetter p_152871_, BlockPos p_152872_, PathComputationType p_152873_) {
		return false;
	}

	public static boolean hasEntry(Block candle, FlavoredCakeBlock cake) {
		return BY_CANDLE_AND_CAKE.get(Pair.of(candle, cake)) != null;
	}

	public static BlockState byCandle(Block candle, FlavoredCakeBlock cake) {
		return BY_CANDLE_AND_CAKE.get(Pair.of(candle, cake)).defaultBlockState();
	}

	public Block getCandle() {
		return candle;
	}

	public Block getCake() {
		return baseCake.get();
	}

	public static Iterable<Block> getCandleCakes() {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block) != null && Neapolitan.MOD_ID.equals(ForgeRegistries.BLOCKS.getKey(block).getNamespace()) && block instanceof FlavoredCandleCakeBlock).collect(Collectors.toList());
	}
}