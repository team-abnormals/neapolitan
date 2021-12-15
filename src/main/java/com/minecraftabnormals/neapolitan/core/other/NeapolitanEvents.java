package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.abnormals_core.core.util.TradeUtil;
import com.minecraftabnormals.abnormals_core.core.util.TradeUtil.AbnormalsTrade;
import com.minecraftabnormals.neapolitan.common.block.MilkCauldronBlock;
import com.minecraftabnormals.neapolitan.common.entity.ChimpanzeeEntity;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.NeapolitanConfig;
import com.minecraftabnormals.neapolitan.core.registry.*;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
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
	public static final String ENVIRONMENTAL = "environmental";
	public static final String SAVAGE_AND_RAVAGE = "savageandravage";
	public static final ResourceLocation MUD_BALL = new ResourceLocation(ENVIRONMENTAL, "mud_ball");
	public static final ResourceLocation CREEPIE = new ResourceLocation(SAVAGE_AND_RAVAGE, "creepie");

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof MonsterEntity && !entity.getType().is(NeapolitanTags.EntityTypes.UNAFFECTED_BY_HARMONY)) {
			MonsterEntity mobEntity = (MonsterEntity) entity;
			mobEntity.goalSelector.addGoal(0, new AvoidEntityGoal<>(mobEntity, PlayerEntity.class, 12.0F, 1.0D, 1.0D, (player) -> player.getEffect(NeapolitanEffects.HARMONY.get()) != null));
		}
	}

	@SubscribeEvent
	public static void onRightClickBlock(RightClickBlock event) {
		BlockPos pos = event.getPos();
		ItemStack stack = event.getItemStack();
		World world = event.getWorld();
		Item item = stack.getItem();
		BlockState state = event.getWorld().getBlockState(pos);
		PlayerEntity player = event.getPlayer();
		Hand hand = event.getHand();

		if (NeapolitanConfig.COMMON.milkCauldron.get() && state.is(Blocks.CAULDRON)) {
			int i = state.getValue(CauldronBlock.LEVEL);
			if (item == Items.MILK_BUCKET) {
				if (i < 3 && !world.isClientSide) {
					if (!player.abilities.instabuild) {
						player.setItemInHand(hand, new ItemStack(Items.BUCKET));
					}

					player.awardStat(Stats.FILL_CAULDRON);
					((MilkCauldronBlock) NeapolitanBlocks.MILK_CAULDRON.get()).setMilkLevel(world, pos, 3);
					world.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				event.setCanceled(true);
				event.setCancellationResult(ActionResultType.sidedSuccess(world.isClientSide));
			} else if (item == NeapolitanItems.MILK_BOTTLE.get()) {
				if (i < 3 && !world.isClientSide) {
					if (!player.abilities.instabuild) {
						stack.shrink(1);
						ItemStack returnStack = new ItemStack(Items.GLASS_BOTTLE);
						if (!player.inventory.add(returnStack)) {
							player.drop(returnStack, false);
						}

						player.awardStat(Stats.USE_CAULDRON);
						if (player instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) player).refreshContainer(player.inventoryMenu);
						}
					}

					world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					((MilkCauldronBlock) NeapolitanBlocks.MILK_CAULDRON.get()).setMilkLevel(world, pos, i + 1);
				}

				event.setCanceled(true);
				event.setCancellationResult(ActionResultType.sidedSuccess(world.isClientSide));
			}
		}
	}

	@SubscribeEvent
	public static void onEntityInteract(EntityInteractSpecific event) {
		World world = event.getWorld();
		ItemStack stack = event.getItemStack();
		Entity entity = event.getTarget();
		Hand hand = event.getHand();
		PlayerEntity player = event.getPlayer();

		if (NeapolitanConfig.COMMON.milkingWithGlassBottles.get() && entity.getType().is(NeapolitanTags.EntityTypes.MILKABLE)) {
			boolean notChild = !(entity instanceof LivingEntity) || !((LivingEntity) entity).isBaby();
			if (stack.getItem() == Items.GLASS_BOTTLE && notChild) {
				player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
				ItemStack itemstack1 = DrinkHelper.createFilledResult(stack, event.getPlayer(), NeapolitanItems.MILK_BOTTLE.get().getDefaultInstance());
				player.setItemInHand(hand, itemstack1);

				event.setCanceled(true);
				event.setCancellationResult(ActionResultType.sidedSuccess(world.isClientSide()));
			}
		}

		if (entity instanceof LivingEntity && !NeapolitanTags.EntityTypes.UNAFFECTED_BY_SLIPPING.contains(entity.getType()) && stack.getItem() == NeapolitanItems.BANANA_BUNCH.get()) {
			ActionResultType actionresulttype = stack.interactLivingEntity(player, (LivingEntity) entity, hand);
			if (actionresulttype.consumesAction()) {
				event.setCanceled(true);
				event.setCancellationResult(actionresulttype);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		if (event.getSource().getEntity() instanceof LivingEntity) {
			LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
			if (attacker.getEffect(NeapolitanEffects.BERSERKING.get()) != null) {
				EffectInstance berserking = attacker.getEffect(NeapolitanEffects.BERSERKING.get());
				if (berserking.getAmplifier() < 9) {
					EffectInstance upgrade = new EffectInstance(berserking.getEffect(), berserking.getDuration(), berserking.getAmplifier() + 1, berserking.isAmbient(), berserking.isVisible(), berserking.showIcon());
					attacker.removeEffectNoUpdate(NeapolitanEffects.BERSERKING.get());
					attacker.addEffect(upgrade);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onExplosion(ExplosionEvent.Detonate event) {
		LivingEntity source = event.getExplosion().getSourceMob();
		if (source != null && (source instanceof CreeperEntity || (ModList.get().isLoaded(SAVAGE_AND_RAVAGE) && source.getType() == ForgeRegistries.ENTITIES.getValue(CREEPIE)))) {
			if (event.getWorld().getBlockState(source.blockPosition()).getBlock() == NeapolitanBlocks.STRAWBERRY_BUSH.get()) {
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
	public static void onProjectileImpact(ProjectileImpactEvent.Throwable event) {
		ThrowableEntity projectileEntity = event.getThrowable();

		if (projectileEntity instanceof ProjectileItemEntity && ModList.get().isLoaded("environmental") && ((ProjectileItemEntity) projectileEntity).getItem().getItem() == ForgeRegistries.ITEMS.getValue(MUD_BALL)) {
			if (event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
				EntityRayTraceResult entity = (EntityRayTraceResult) event.getRayTraceResult();
				if (entity.getEntity() instanceof ChimpanzeeEntity) {
					ChimpanzeeEntity chimpanzee = (ChimpanzeeEntity) entity.getEntity();
					chimpanzee.setDirtiness(12000);
				}
			}
		}
	}


	@SubscribeEvent
	public static void onPotionAdded(PotionEvent.PotionApplicableEvent event) {
		Effect effect = event.getPotionEffect().getEffect();
		LivingEntity entity = event.getEntityLiving();

		if (entity.getEffect(NeapolitanEffects.VANILLA_SCENT.get()) != null && effect != NeapolitanEffects.VANILLA_SCENT.get()) {
			if (effect.getRegistryName() != null && !effect.getRegistryName().equals(new ResourceLocation("autumnity", "foul_taste"))) {
				event.setResult(Result.DENY);
			}
		}

		if (effect == NeapolitanEffects.SUGAR_RUSH.get() && !entity.level.isClientSide) {
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
				new AbnormalsTrade(NeapolitanItems.MINT_LEAVES.get(), 10, 1, 12, 30)
		);

		TradeUtil.addVillagerTrades(event, VillagerProfession.FLETCHER, TradeUtil.EXPERT,
				new AbnormalsTrade(1, NeapolitanItems.BANANARROW.get(), 4, 12, 15)
		);
	}

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		TradeUtil.addWandererTrades(event,
				new AbnormalsTrade(1, NeapolitanItems.STRAWBERRY_PIPS.get(), 1, 12, 1),
				new AbnormalsTrade(2, NeapolitanItems.VANILLA_PODS.get(), 1, 5, 1),
				new AbnormalsTrade(2, NeapolitanBlocks.BANANA_FROND.get().asItem(), 1, 5, 1),
				new AbnormalsTrade(2, NeapolitanItems.MINT_SPROUT.get(), 1, 5, 1),
				new AbnormalsTrade(2, NeapolitanItems.ADZUKI_BEANS.get(), 1, 5, 1)
		);

		TradeUtil.addRareWandererTrades(event,
				new AbnormalsTrade(1, NeapolitanItems.WHITE_STRAWBERRIES.get(), 1, 8, 1),
				new AbnormalsTrade(3, NeapolitanItems.MAGIC_BEANS.get(), 1, 6, 1)
		);
	}
}
