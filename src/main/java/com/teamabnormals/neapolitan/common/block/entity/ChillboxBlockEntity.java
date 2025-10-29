package com.teamabnormals.neapolitan.common.block.entity;

import com.teamabnormals.neapolitan.common.block.ChillboxBlock;
import com.teamabnormals.neapolitan.common.inventory.ChillboxMenu;
import com.teamabnormals.neapolitan.common.item.component.IceCream;
import com.teamabnormals.neapolitan.common.item.component.IceCreamFlavor;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanDataMaps;
import com.teamabnormals.neapolitan.core.other.NeapolitanDataMaps.ChillboxFuel;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanItemTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlockEntityTypes;
import com.teamabnormals.neapolitan.core.registry.NeapolitanDataComponents;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.core.*;
import net.minecraft.core.Holder.Reference;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Optional;

public class ChillboxBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, MenuProvider, Nameable, StackedContentsCompatible {

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public Direction getDirection() {
		return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
	}

	public static final int FUEL_SLOT = 0;
	public static final int RESULT_SLOT = 7;
	public static final int INVENTORY_SIZE = RESULT_SLOT + 1;

	private static final int[] SLOTS_FOR_UP = new int[]{FUEL_SLOT};
	private static final int[] SLOTS_FOR_DOWN = new int[]{RESULT_SLOT};
	private static final int[] SLOTS_FOR_SIDES = new int[]{1, 2, 3, 4, 5, 6};

	protected NonNullList<ItemStack> items = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);
	int chillTime;
	int chillDuration;
	int cookingProgress;
	int cookingTotalTime;
	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int i) {
			switch (i) {
				case 0:
					if (chillDuration > Short.MAX_VALUE) {
						return Mth.floor(((double) chillTime / chillDuration) * Short.MAX_VALUE);
					}

					return ChillboxBlockEntity.this.chillTime;
				case 1:
					return Math.min(ChillboxBlockEntity.this.chillDuration, Short.MAX_VALUE);
				case 2:
					return ChillboxBlockEntity.this.cookingProgress;
				case 3:
					return ChillboxBlockEntity.this.cookingTotalTime;
				default:
					return 0;
			}
		}

		@Override
		public void set(int i, int value) {
			switch (i) {
				case 0:
					ChillboxBlockEntity.this.chillTime = value;
					break;
				case 1:
					ChillboxBlockEntity.this.chillDuration = value;
					break;
				case 2:
					ChillboxBlockEntity.this.cookingProgress = value;
					break;
				case 3:
					ChillboxBlockEntity.this.cookingTotalTime = value;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	public ChillboxBlockEntity(BlockPos pos, BlockState state) {
		super(NeapolitanBlockEntityTypes.CHILLBOX.get(), pos, state);
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, NeapolitanBlockEntityTypes.CHILLBOX.get(), (sidedContainer, side) -> side == null ? new InvWrapper(sidedContainer) : new SidedInvWrapper(sidedContainer, side));
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.items, registries);
		this.chillTime = tag.getInt("ChillTime");
		this.cookingProgress = tag.getInt("CookTime");
		this.cookingTotalTime = tag.getInt("CookTimeTotal");
		this.chillDuration = this.getChillDuration(this.items.get(1));
	}

	@Override
	public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putInt("ChillTime", this.chillTime);
		tag.putInt("CookTime", this.cookingProgress);
		tag.putInt("CookTimeTotal", this.cookingTotalTime);
		ContainerHelper.saveAllItems(tag, this.items, registries);
	}

	private static boolean areIngredientsFull(NonNullList<ItemStack> inventory) {
		for (int i = 1; i <= 6; i++) {
			if (inventory.get(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}


	private static int SUGAR_SLOT = 4;
	private static int MILK_SLOT = 5;
	private static int CONTAINER_SLOT = 6;

	private static ItemStack assembleOutput(RegistryAccess access, NonNullList<ItemStack> inventory) {
		if (!inventory.get(SUGAR_SLOT).is(Items.SUGAR) || !inventory.get(MILK_SLOT).is(Tags.Items.DRINKS_MILK)) {
			return ItemStack.EMPTY;
		}

		Holder<IceCreamFlavor>[] flavors = new Holder[3];
		for (int i = 1; i <= 3; i++) {
			Optional<Reference<IceCreamFlavor>> flavor = IceCreamFlavor.getFromIngredient(access, inventory.get(i));
			if (flavor.isEmpty()) {
				return ItemStack.EMPTY;
			} else {
				flavors[i - 1] = flavor.get();
			}
		}

		IceCream iceCream = new IceCream(flavors[0], flavors[1], flavors[2]);
		if (inventory.get(CONTAINER_SLOT).is(Items.BOWL)) {
			ItemStack stack = new ItemStack(NeapolitanItems.ICE_CREAM.get());
			stack.set(NeapolitanDataComponents.ICE_CREAM, iceCream);
			return stack;
		} else if (inventory.get(CONTAINER_SLOT).is(NeapolitanItems.WAFFLE_CONE)) {
			ItemStack stack = new ItemStack(NeapolitanItems.ICE_CREAM_CONE.get());
			stack.set(NeapolitanDataComponents.ICE_CREAM, iceCream);
			return stack;
		}

		return ItemStack.EMPTY;
	}

	private static boolean canChill(RegistryAccess registryAccess, NonNullList<ItemStack> inventory, int maxStackSize, ChillboxBlockEntity chillbox) {
		if (areIngredientsFull(inventory)) {
			ItemStack itemstack = assembleOutput(registryAccess, inventory);
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = inventory.get(RESULT_SLOT);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!ItemStack.isSameItemSameComponents(itemstack1, itemstack)) {
					return false;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= maxStackSize && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize() || itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}

	private boolean isLit() {
		return this.chillTime > 0;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, ChillboxBlockEntity chillbox) {
		boolean isLit = chillbox.isLit();
		boolean changed = false;
		if (chillbox.isLit()) {
			chillbox.chillTime--;
		}

		ItemStack fuelStack = chillbox.items.get(0);
		ItemStack inputStack = chillbox.items.get(1);
		boolean hasInputs = !inputStack.isEmpty();
		boolean hasFuel = !fuelStack.isEmpty();
		if (chillbox.isLit() || hasFuel && hasInputs) {

			int i = chillbox.getMaxStackSize();
			if (!chillbox.isLit() && canChill(level.registryAccess(), chillbox.items, i, chillbox)) {
				chillbox.chillTime = chillbox.getChillDuration(fuelStack);
				chillbox.chillDuration = chillbox.chillTime;
				if (chillbox.isLit()) {
					changed = true;
					if (fuelStack.hasCraftingRemainingItem())
						chillbox.items.set(1, fuelStack.getCraftingRemainingItem());
					else if (hasFuel) {
						fuelStack.shrink(1);
						if (fuelStack.isEmpty()) {
							chillbox.items.set(1, fuelStack.getCraftingRemainingItem());
						}
					}
				}
			}

			if (chillbox.isLit() && canChill(level.registryAccess(), chillbox.items, i, chillbox)) {
				chillbox.cookingProgress++;
				if (chillbox.cookingProgress == chillbox.cookingTotalTime) {
					chillbox.cookingProgress = 0;
					chillbox.cookingTotalTime = getTotalCookTime(level, chillbox);
					if (chill(level.registryAccess(), chillbox.items, i, chillbox)) {
						for (int index = 1; index <= 6; index++) {
							ItemStack stack = chillbox.items.get(index);
							if (stack.hasCraftingRemainingItem()) {
								ejectIngredientRemainder(level, pos.above(), state, stack.getCraftingRemainingItem());
							}
							if (!stack.isEmpty()) {
								stack.shrink(1);
							}
						}
					}

					changed = true;
				}
			} else {
				chillbox.cookingProgress = 0;
			}
		} else if (!chillbox.isLit() && chillbox.cookingProgress > 0) {
			chillbox.cookingProgress = Mth.clamp(chillbox.cookingProgress - 2, 0, chillbox.cookingTotalTime);
		}

		if (isLit != chillbox.isLit()) {
			changed = true;
//			state = state.setValue(AbstractFurnaceBlock.LIT, chillbox.isLit());
//			level.setBlock(pos, state, 3);
		}

		if (changed) {
			setChanged(level, pos, state);
			chillbox.setChanged();
		}
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return saveWithoutMetadata(registries);
	}

	@Override
	public void setChanged() {
		super.setChanged();
		if (level != null) {
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
		}
	}

	private static boolean chill(RegistryAccess registryAccess, NonNullList<ItemStack> inventory, int maxStackSize, ChillboxBlockEntity chillbox) {
		if (canChill(registryAccess, inventory, maxStackSize, chillbox)) {
			ItemStack output = assembleOutput(registryAccess, inventory);
			ItemStack outputSlot = inventory.get(RESULT_SLOT);
			if (outputSlot.isEmpty()) {
				inventory.set(RESULT_SLOT, output.copy());
			} else if (ItemStack.isSameItemSameComponents(outputSlot, output)) {
				outputSlot.grow(output.getCount());
			}
			return true;
		} else {
			return false;
		}
	}

	protected static void ejectIngredientRemainder(Level level, BlockPos pos, BlockState state, ItemStack remainderStack) {
		Direction direction = state.getValue(ChillboxBlock.FACING).getCounterClockWise();
		double x = pos.getX() + 0.5 + (direction.getStepX() * 0.25);
		double y = pos.getY();
		double z = pos.getZ() + 0.5 + (direction.getStepZ() * 0.25);

		ItemEntity entity = new ItemEntity(level, x, y, z, remainderStack);
		entity.setDeltaMovement(direction.getStepX() * 0.08F, 0.25F, direction.getStepZ() * 0.08F);
		level.addFreshEntity(entity);
	}

	protected int getChillDuration(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			ChillboxFuel fuel = stack.getItemHolder().getData(NeapolitanDataMaps.CHILLBOX_FUELS);
			return fuel != null ? fuel.chillTime() : 0;
		}
	}

	private static int getTotalCookTime(Level level, ChillboxBlockEntity chillbox) {
		return 200;
	}

	public static boolean isFuel(ItemStack stack) {
		return stack.getItemHolder().getData(NeapolitanDataMaps.CHILLBOX_FUELS) != null;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return side == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
		return this.canPlaceItem(index, itemStack);
	}

	public static TagKey<Item> getTagForSlot(int index) {
		return index < 4 ? NeapolitanItemTags.ICE_CREAM_FLAVORS : index == 4 ? NeapolitanItemTags.ICE_CREAM_SWEETENERS : index == 5 ? Tags.Items.DRINKS_MILK : NeapolitanItemTags.ICE_CREAM_CONTAINERS;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		if (index == RESULT_SLOT) {
			return false;
		} else if (index == FUEL_SLOT) {
			return isFuel(stack);
		} else {
			return stack.is(getTagForSlot(index));
		}
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, stack);
		this.items.set(index, stack);
		stack.limitSize(this.getMaxStackSize(stack));
		if (index == 0 && !flag) {
			this.cookingTotalTime = getTotalCookTime(this.level, this);
			this.cookingProgress = 0;
			this.setChanged();
		}
	}

	public void popExperience(ServerPlayer player) {
		this.getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
	}

	public void getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 vec3) {
		createExperience(level, vec3, 0, 0.35F);
	}

	private static void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
		int i = Mth.floor((float) recipeIndex * experience);
		float f = Mth.frac((float) recipeIndex * experience);
		if (f != 0.0F && Math.random() < (double) f) {
			i++;
		}

		ExperienceOrb.award(level, popVec, i);
	}

	@Override
	public void fillStackedContents(StackedContents helper) {
		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}
	}

	@Override
	public Component getDefaultName() {
		return Component.translatable("container." + Neapolitan.MOD_ID + ".chillbox");
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory player) {
		return new ChillboxMenu(id, player, this, this.dataAccess);
	}
}