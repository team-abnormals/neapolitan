package com.teamabnormals.neapolitan.client.gui.screens.inventory;

import com.teamabnormals.neapolitan.common.inventory.ChillboxMenu;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ChillboxScreen extends AbstractContainerScreen<ChillboxMenu> {
	private static final ResourceLocation FUEL_LENGTH_SPRITE = Neapolitan.location("container/chillbox/fuel_length");
	private static final ResourceLocation CHILL_PROGRESS_SPRITE = Neapolitan.location("container/chillbox/chill_progress");
	private static final ResourceLocation BACKGROUND_TEXTURE = Neapolitan.location("textures/gui/container/chillbox.png");

	public static final ResourceLocation EMPTY_SLOT_ICE_CUBES = Neapolitan.location("item/empty_slot_ice_cubes");
	public static final ResourceLocation EMPTY_SLOT_SUGAR = Neapolitan.location("item/empty_slot_sugar");

	public static final ResourceLocation EMPTY_SLOT_BUCKET = Neapolitan.location("item/empty_slot_bucket");
	public static final ResourceLocation EMPTY_SLOT_BOTTLE = Neapolitan.location("item/empty_slot_bottle");

	public static final ResourceLocation EMPTY_SLOT_BOWL = Neapolitan.location("item/empty_slot_bowl");
	public static final ResourceLocation EMPTY_SLOT_WAFFLE_CONE = Neapolitan.location("item/empty_slot_waffle_cone");

	public static final ResourceLocation EMPTY_SLOT_STRAWBERRY = Neapolitan.location("item/empty_slot_strawberry");
	public static final ResourceLocation EMPTY_SLOT_VANILLA = Neapolitan.location("item/empty_slot_vanilla");
	public static final ResourceLocation EMPTY_SLOT_CHOCOLATE = Neapolitan.location("item/empty_slot_chocolate");
	public static final ResourceLocation EMPTY_SLOT_MINT = Neapolitan.location("item/empty_slot_mint");
	public static final ResourceLocation EMPTY_SLOT_BANANA = Neapolitan.location("item/empty_slot_banana");
	public static final ResourceLocation EMPTY_SLOT_ADZUKI = Neapolitan.location("item/empty_slot_adzuki");
	public static final List<ResourceLocation> EMPTY_SLOT_FLAVORS = List.of(EMPTY_SLOT_VANILLA, EMPTY_SLOT_CHOCOLATE, EMPTY_SLOT_STRAWBERRY, EMPTY_SLOT_BANANA, EMPTY_SLOT_MINT, EMPTY_SLOT_ADZUKI);

	private final CyclingSlotBackground iceIcon = new CyclingSlotBackground(0);

	private final CyclingSlotBackground ingredient1Icon = new CyclingSlotBackground(1);
	private final CyclingSlotBackground ingredient2Icon = new OffsetCyclingSlotBackground(2, 1);
	private final CyclingSlotBackground ingredient3Icon = new OffsetCyclingSlotBackground(3, 2);

	private final CyclingSlotBackground sugarIcon = new CyclingSlotBackground(4);
	private final CyclingSlotBackground milkIcon = new CyclingSlotBackground(5);
	private final CyclingSlotBackground containerIcon = new CyclingSlotBackground(6);

	public ChillboxScreen(ChillboxMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.iceIcon.tick(List.of(EMPTY_SLOT_ICE_CUBES));
		this.ingredient1Icon.tick(EMPTY_SLOT_FLAVORS);
		this.ingredient2Icon.tick(EMPTY_SLOT_FLAVORS);
		this.ingredient3Icon.tick(EMPTY_SLOT_FLAVORS);
		this.sugarIcon.tick(List.of(EMPTY_SLOT_SUGAR));
		this.milkIcon.tick(List.of(EMPTY_SLOT_BOTTLE, EMPTY_SLOT_BUCKET));
		this.containerIcon.tick(List.of(EMPTY_SLOT_BOWL, EMPTY_SLOT_WAFFLE_CONE));
	}

	@Override
	public void init() {
		super.init();
		this.titleLabelX = 46;
	}

	@Override
	public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
		super.render(gui, mouseX, mouseY, partialTick);
		this.renderTooltip(gui, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
		int i = this.leftPos;
		int j = this.topPos;
		gui.blit(BACKGROUND_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

		this.iceIcon.render(this.menu, gui, partialTicks, this.leftPos, this.topPos);
		this.ingredient1Icon.render(this.menu, gui, partialTicks, this.leftPos, this.topPos);
		this.ingredient2Icon.render(this.menu, gui, partialTicks, this.leftPos, this.topPos);
		this.ingredient3Icon.render(this.menu, gui, partialTicks, this.leftPos, this.topPos);
		this.milkIcon.render(this.menu, gui, partialTicks, this.leftPos, this.topPos);
		this.sugarIcon.render(this.menu, gui, partialTicks, this.leftPos, this.topPos);
		this.containerIcon.render(this.menu, gui, partialTicks, this.leftPos, this.topPos);

		if (this.menu.isLit()) {
			int fuelLength = Mth.ceil(this.menu.getLitProgress() * 53.0F);
			gui.blitSprite(FUEL_LENGTH_SPRITE, 53, 6, 0, 0, i + 46, j + 57, fuelLength, 6);
		}

		int chillProgress = Mth.ceil(this.menu.getBurnProgress() * 34.0F);
		gui.blitSprite(CHILL_PROGRESS_SPRITE, 34, 18, 0, 0, i + 101, j + 24, chillProgress, 18);
	}

	public static class OffsetCyclingSlotBackground extends CyclingSlotBackground {
		private int offset;

		public OffsetCyclingSlotBackground(int slotIndex, int offset) {
			super(slotIndex);
			this.offset = offset;
		}

		@Override
		public void tick(List<ResourceLocation> icons) {
			if (!this.icons.equals(icons)) {
				this.icons = icons;
				this.iconIndex = this.offset;
			}
			super.tick(icons);
		}
	}
}