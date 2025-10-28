package com.teamabnormals.neapolitan.common.inventory;

import com.teamabnormals.neapolitan.common.block.entity.ChillboxBlockEntity;
import com.teamabnormals.neapolitan.core.other.NeapolitanDataMaps;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMenuTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class ChillboxMenu extends AbstractContainerMenu {
	public static final int INGREDIENT_SLOT = 1;
	public static final int FUEL_SLOT = 0;
	public static final int RESULT_SLOT = 7;

	private final Container container;
	private final ContainerData data;
	protected final Level level;

	public ChillboxMenu(int containerId, Inventory playerInventory) {
		this(containerId, playerInventory, new SimpleContainer(8), new SimpleContainerData(4));
	}

	public ChillboxMenu(int containerId, Inventory playerInventory, Container chillbox, ContainerData chillboxData) {
		super(NeapolitanMenuTypes.CHILLBOX.get(), containerId);
		this.container = chillbox;
		this.data = chillboxData;
		this.level = playerInventory.player.level();

		this.addSlot(new ChillboxFuelSlot(this, container, FUEL_SLOT, 17, 27));

		for (int row = 0; row < 2; ++row) {
			for (int column = 0; column < 3; ++column) {
				int index = (row * 3) + column + 1;
				this.addSlot(new ChillboxInputSlot(ChillboxBlockEntity.getTagForSlot(index), container, index, 47 + column * 18, 17 + row * 18));
			}
		}

		this.addSlot(new ChillboxResultSlot(playerInventory.player, chillbox, RESULT_SLOT, 141, 26));

		int startPlayerInvY = 18 * 4 + 12;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, 8 + column * 18, startPlayerInvY + row * 18));
			}
		}

		for (int column = 0; column < 9; column++) {
			this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
		}

		this.addDataSlots(chillboxData);
	}

	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		int startPlayerInv = RESULT_SLOT + 1;
		int endPlayerInv = startPlayerInv + 36;
		ItemStack slotStackCopy = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			slotStackCopy = slotStack.copy();
			if (index == RESULT_SLOT) {
				if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, true)) {
					return ItemStack.EMPTY;
				}
			} else if (index > RESULT_SLOT) {
				if (!this.moveItemStackTo(slotStack, FUEL_SLOT, RESULT_SLOT, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (slotStack.getCount() == slotStackCopy.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, slotStack);
		}
		return slotStackCopy;
	}

	protected boolean isFuel(ItemStack stack) {
		return stack.getItemHolder().getData(NeapolitanDataMaps.CHILLBOX_FUELS) != null;
	}

	public float getBurnProgress() {
		int i = this.data.get(2);
		int j = this.data.get(3);
		return j != 0 && i != 0 ? Mth.clamp((float) i / (float) j, 0.0F, 1.0F) : 0.0F;
	}

	public float getLitProgress() {
		int i = this.data.get(1);
		if (i == 0) {
			i = 200;
		}

		return Mth.clamp((float) this.data.get(0) / (float) i, 0.0F, 1.0F);
	}

	public boolean isLit() {
		return this.data.get(0) > 0;
	}

	public static class ChillboxInputSlot extends Slot {
		private final TagKey<Item> allowedItems;

		public ChillboxInputSlot(TagKey<Item> allowedTag, Container chillbox, int index, int xPosition, int yPosition) {
			super(chillbox, index, xPosition, yPosition);
			this.allowedItems = allowedTag;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return stack.is(this.allowedItems);
		}
	}

	public static class ChillboxFuelSlot extends Slot {
		private final ChillboxMenu menu;

		public ChillboxFuelSlot(ChillboxMenu menu, Container chillbox, int index, int xPosition, int yPosition) {
			super(chillbox, index, xPosition, yPosition);
			this.menu = menu;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.menu.isFuel(stack);
		}
	}

	public static class ChillboxResultSlot extends Slot {
		private final Player player;
		private int removeCount;

		public ChillboxResultSlot(Player player, Container container, int index, int xPosition, int yPosition) {
			super(container, index, xPosition, yPosition);
			this.player = player;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		@Nonnull
		public ItemStack remove(int amount) {
			if (this.hasItem()) {
				this.removeCount += Math.min(amount, this.getItem().getCount());
			}

			return super.remove(amount);
		}

		@Override
		public void onTake(Player player, ItemStack stack) {
			this.checkTakeAchievements(stack);
			super.onTake(player, stack);
		}

		@Override
		protected void onQuickCraft(ItemStack stack, int amount) {
			this.removeCount += amount;
			this.checkTakeAchievements(stack);
		}

		@Override
		protected void checkTakeAchievements(ItemStack stack) {
			stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
			if (this.player instanceof ServerPlayer serverplayer && this.container instanceof ChillboxBlockEntity chillbox) {
				chillbox.popExperience(serverplayer);
			}

			this.removeCount = 0;
		}
	}
}