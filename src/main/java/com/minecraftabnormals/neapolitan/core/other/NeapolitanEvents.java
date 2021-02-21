package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.abnormals_core.core.util.TradeUtil;
import com.minecraftabnormals.abnormals_core.core.util.TradeUtil.AbnormalsTrade;
import com.minecraftabnormals.neapolitan.common.entity.goals.AvoidBlockGoal;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanEffects;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanEvents {
	public static final String SAVAGE_AND_RAVAGE = "savageandravage";
	public static final ResourceLocation CREEPIE = new ResourceLocation(SAVAGE_AND_RAVAGE, "creepie");

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof CreeperEntity) {
			CreeperEntity creeper = (CreeperEntity) event.getEntity();
			creeper.goalSelector.addGoal(3, new AvoidBlockGoal<>(creeper, 6, 1.0D, 1.2D));
		} else if (entity instanceof MonsterEntity && entity.getType() != null && (ModList.get().isLoaded(SAVAGE_AND_RAVAGE) && entity.getType() == ForgeRegistries.ENTITIES.getValue(CREEPIE))) {
			MonsterEntity creepie = (MonsterEntity) event.getEntity();
			creepie.goalSelector.addGoal(3, new AvoidBlockGoal<>(creepie, 6, 1.0D, 1.2D));
		}
	}

	@SubscribeEvent
	public static void onEntityInteract(EntityInteractSpecific event) {
		ItemStack stack = event.getItemStack();
		Entity entity = event.getTarget();
		Hand hand = event.getHand();
		PlayerEntity player = event.getPlayer();

		if (NeapolitanConfig.COMMON.milkingWithGlassBottles.get() && entity.getType().isContained(NeapolitanTags.EntityTypes.MILKABLE)) {
			boolean notChild = !(entity instanceof LivingEntity) || !((LivingEntity) entity).isChild();
			if (stack.getItem() == Items.GLASS_BOTTLE && notChild) {
				player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
				ItemStack itemstack1 = DrinkHelper.fill(stack, event.getPlayer(), NeapolitanItems.MILK_BOTTLE.get().getDefaultInstance());
				player.swingArm(hand);
				player.setHeldItem(hand, itemstack1);
			}
		}
	}

	@SubscribeEvent
	public static void onExplosion(ExplosionEvent.Detonate event) {
		LivingEntity source = event.getExplosion().getExplosivePlacedBy();
		if (source != null && (source instanceof CreeperEntity || (ModList.get().isLoaded(SAVAGE_AND_RAVAGE) && source.getType() == ForgeRegistries.ENTITIES.getValue(CREEPIE)))) {
			if (event.getWorld().getBlockState(source.getPosition()).getBlock() == NeapolitanBlocks.STRAWBERRY_BUSH.get()) {
				for (Entity entity : event.getAffectedEntities()) {
					if (entity instanceof LivingEntity) {
						LivingEntity livingEntity = ((LivingEntity) entity);
						livingEntity.heal(5.0F);
					}
					if (entity instanceof ServerPlayerEntity)
						NeapolitanCriteriaTriggers.CREEPER_HEAL.trigger((ServerPlayerEntity) entity);
				}

				event.getAffectedEntities().clear();
				event.getAffectedBlocks().clear();
			}
		}
	}

	@SubscribeEvent
	public static void onPotionAdded(PotionEvent.PotionApplicableEvent event) {
		Effect effect = event.getPotionEffect().getPotion();
		LivingEntity entity = event.getEntityLiving();

		if (entity.getActivePotionEffect(NeapolitanEffects.VANILLA_SCENT.get()) != null) {
			if (effect != NeapolitanEffects.VANILLA_SCENT.get()) {
				event.setResult(Result.DENY);
			}
		}

		if (effect == NeapolitanEffects.SUGAR_RUSH.get() && !entity.world.isRemote) {
			entity.getPersistentData().putInt("SugarRushDuration", event.getPotionEffect().getDuration());
		}
	}

	@SubscribeEvent
	public static void onVillagerTrades(VillagerTradesEvent event) {
		if (event.getType().equals(VillagerProfession.FARMER)) {
			TradeUtil.addVillagerTrades(event, TradeUtil.APPRENTICE,
					new AbnormalsTrade(NeapolitanItems.STRAWBERRIES.get(), 24, 1, 16, 2)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.JOURNEYMAN,
					new AbnormalsTrade(NeapolitanItems.BANANA.get(), 8, 1, 12, 10),
					new AbnormalsTrade(3, NeapolitanItems.STRAWBERRY_SCONES.get(), 12, 12, 10)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.EXPERT,
					new AbnormalsTrade(3, NeapolitanItems.VANILLA_CAKE.get(), 1, 12, 15),
					new AbnormalsTrade(3, NeapolitanItems.CHOCOLATE_CAKE.get(), 1, 12, 15),
					new AbnormalsTrade(3, NeapolitanItems.STRAWBERRY_CAKE.get(), 1, 12, 15),
					new AbnormalsTrade(3, NeapolitanItems.BANANA_CAKE.get(), 1, 12, 15),
					new AbnormalsTrade(3, NeapolitanItems.MINT_CAKE.get(), 1, 12, 15)
			);
		}

		TradeUtil.addVillagerTrades(event, VillagerProfession.BUTCHER, TradeUtil.MASTER,
				new AbnormalsTrade(NeapolitanItems.DRIED_VANILLA_PODS.get(), 16, 1, 16, 30)
		);

		TradeUtil.addVillagerTrades(event, VillagerProfession.FLETCHER, TradeUtil.EXPERT,
				new AbnormalsTrade(1, NeapolitanItems.BANANARROW.get(), 4, 12, 15)
		);
	}

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		TradeUtil.addWandererTrades(event,
				new AbnormalsTrade(1, NeapolitanItems.VANILLA_PODS.get(), 3, 4, 1),
				new AbnormalsTrade(5, NeapolitanBlocks.LARGE_BANANA_FROND.get().asItem(), 1, 4, 1),
				new AbnormalsTrade(1, NeapolitanItems.STRAWBERRY_PIPS.get(), 1, 12, 1),
				new AbnormalsTrade(2, NeapolitanItems.VANILLA_PODS.get(), 1, 5, 1),
				new AbnormalsTrade(2, NeapolitanItems.MINT_SPROUT.get(), 1, 5, 1),
				new AbnormalsTrade(5, NeapolitanBlocks.LARGE_BANANA_FROND.get().asItem(), 1, 4, 1)
		);

		TradeUtil.addRareWandererTrades(event,
				new AbnormalsTrade(4, NeapolitanItems.WHITE_STRAWBERRIES.get(), 8, 2, 1)
		);
	}
}
