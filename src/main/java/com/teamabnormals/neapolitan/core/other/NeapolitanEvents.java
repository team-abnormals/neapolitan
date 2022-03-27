package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.core.other.tags.BlueprintEntityTypeTags;
import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import com.teamabnormals.blueprint.core.util.TradeUtil.BlueprintTrade;
import com.teamabnormals.neapolitan.common.entity.animal.ChimpanzeeEntity;
import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.NeapolitanConfig;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.teamabnormals.neapolitan.core.registry.NeapolitanMobEffects;
import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.neapolitan.core.registry.NeapolitanParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
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

import java.util.Random;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanEvents {

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Monster mobEntity && !entity.getType().is(NeapolitanEntityTypeTags.UNAFFECTED_BY_HARMONY)) {
			mobEntity.goalSelector.addGoal(0, new AvoidEntityGoal<>(mobEntity, Player.class, 12.0F, 1.0D, 1.0D, (player) -> player.getEffect(NeapolitanMobEffects.HARMONY.get()) != null));
		}
	}

	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		Entity entity = event.getEntity();
		if (entity.getType().is(BlueprintEntityTypeTags.MILKABLE)) {
			Level level = event.getEntity().getLevel();
			BlockPos entityPos = event.getEntityLiving().blockPosition();
			BlockPos dripstonePos = event.getEntityLiving().getOnPos().below();
			Random random = level.random;

			if (level.getGameTime() % 60 == 0 && PointedDripstoneBlock.canDrip(level.getBlockState(dripstonePos))) {
				Vec3 vec3 = level.getBlockState(entityPos).getOffset(level, entityPos);
				double d1 = (double) entityPos.getX() + 0.5D + vec3.x + MathUtil.makeNegativeRandomly(random.nextFloat() * 0.25F, random);
				double d2 = entityPos.getY() + entity.getBbHeight() / 2;
				double d3 = (double) entityPos.getZ() + 0.5D + vec3.z + MathUtil.makeNegativeRandomly(random.nextFloat() * 0.25F, random);
				level.addParticle(NeapolitanParticleTypes.DRIPPING_DRIPSTONE_MILK.get(), d1, d2, d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityInteract(EntityInteractSpecific event) {
		Level world = event.getWorld();
		ItemStack stack = event.getItemStack();
		Entity entity = event.getTarget();
		InteractionHand hand = event.getHand();
		Player player = event.getPlayer();

		if (NeapolitanConfig.COMMON.milkingWithGlassBottles.get() && entity.getType().is(BlueprintEntityTypeTags.MILKABLE)) {
			boolean notChild = !(entity instanceof LivingEntity) || !((LivingEntity) entity).isBaby();
			if (stack.getItem() == Items.GLASS_BOTTLE && notChild) {
				player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
				ItemStack newStack = ItemUtils.createFilledResult(stack, event.getPlayer(), NeapolitanItems.MILK_BOTTLE.get().getDefaultInstance());
				player.setItemInHand(hand, newStack);

				event.setCanceled(true);
				event.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide()));
			}
		}

		if (entity instanceof LivingEntity && !entity.getType().is(NeapolitanEntityTypeTags.UNAFFECTED_BY_SLIPPING) && stack.getItem() == NeapolitanItems.BANANA_BUNCH.get()) {
			InteractionResult result = stack.interactLivingEntity(player, (LivingEntity) entity, hand);
			if (result.consumesAction()) {
				event.setCanceled(true);
				event.setCancellationResult(result);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		if (event.getSource().getEntity() instanceof LivingEntity attacker) {
			if (attacker.getEffect(NeapolitanMobEffects.BERSERKING.get()) != null) {
				MobEffectInstance berserking = attacker.getEffect(NeapolitanMobEffects.BERSERKING.get());
				if (berserking.getAmplifier() < 9) {
					MobEffectInstance upgrade = new MobEffectInstance(berserking.getEffect(), berserking.getDuration(), berserking.getAmplifier() + 1, berserking.isAmbient(), berserking.isVisible(), berserking.showIcon());
					attacker.removeEffectNoUpdate(NeapolitanMobEffects.BERSERKING.get());
					attacker.addEffect(upgrade);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onExplosion(ExplosionEvent.Detonate event) {
		LivingEntity source = event.getExplosion().getSourceMob();
		if (source != null && (source instanceof Creeper || (ModList.get().isLoaded(NeapolitanConstants.SAVAGE_AND_RAVAGE) && source.getType() == ForgeRegistries.ENTITIES.getValue(NeapolitanConstants.CREEPIE)))) {
			if (event.getWorld().getBlockState(source.blockPosition()).getBlock() == NeapolitanBlocks.STRAWBERRY_BUSH.get()) {
				for (Entity entity : event.getAffectedEntities()) {
					if (entity instanceof LivingEntity livingEntity) {
						livingEntity.heal(5.0F);
					}
					if (entity instanceof ServerPlayer)
						NeapolitanCriteriaTriggers.CREEPER_HEAL.trigger((ServerPlayer) entity);
				}

				event.getAffectedEntities().clear();
				event.getAffectedBlocks().clear();
			}
		}
	}

	@SubscribeEvent
	public static void onProjectileImpact(ProjectileImpactEvent event) {
		Projectile projectileEntity = event.getProjectile();

		if (projectileEntity instanceof ThrowableItemProjectile && ModList.get().isLoaded(NeapolitanConstants.ENVIRONMENTAL) && ((ThrowableItemProjectile) projectileEntity).getItem().getItem() == ForgeRegistries.ITEMS.getValue(NeapolitanConstants.MUD_BALL)) {
			if (event.getRayTraceResult().getType() == HitResult.Type.ENTITY) {
				EntityHitResult entity = (EntityHitResult) event.getRayTraceResult();
				if (entity.getEntity() instanceof ChimpanzeeEntity chimpanzee) {
					chimpanzee.setDirtiness(12000);
				}
			}
		}
	}


	@SubscribeEvent
	public static void onPotionAdded(PotionEvent.PotionApplicableEvent event) {
		MobEffect effect = event.getPotionEffect().getEffect();
		LivingEntity entity = event.getEntityLiving();

		if (entity.getEffect(NeapolitanMobEffects.VANILLA_SCENT.get()) != null && effect != NeapolitanMobEffects.VANILLA_SCENT.get()) {
			if (effect.getRegistryName() != null && !effect.getRegistryName().equals(new ResourceLocation("autumnity", "foul_taste"))) {
				event.setResult(Result.DENY);
			}
		}

		if (effect == NeapolitanMobEffects.SUGAR_RUSH.get() && !entity.level.isClientSide) {
			entity.getPersistentData().putInt("SugarRushDuration", event.getPotionEffect().getDuration());
		}
	}

	@SubscribeEvent
	public static void onVillagerTrades(VillagerTradesEvent event) {
		if (event.getType().equals(VillagerProfession.FARMER)) {
			TradeUtil.addVillagerTrades(event, TradeUtil.APPRENTICE,
					new BlueprintTrade(NeapolitanItems.STRAWBERRIES.get(), 24, 1, 16, 2)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.JOURNEYMAN,
					new BlueprintTrade(NeapolitanItems.BANANA.get(), 8, 1, 12, 10),
					new BlueprintTrade(3, NeapolitanItems.STRAWBERRY_SCONES.get(), 12, 12, 10)
			);

			TradeUtil.addVillagerTrades(event, TradeUtil.EXPERT,
					new BlueprintTrade(3, NeapolitanItems.VANILLA_CAKE.get(), 1, 12, 15),
					new BlueprintTrade(3, NeapolitanItems.CHOCOLATE_CAKE.get(), 1, 12, 15),
					new BlueprintTrade(3, NeapolitanItems.STRAWBERRY_CAKE.get(), 1, 12, 15),
					new BlueprintTrade(3, NeapolitanItems.BANANA_CAKE.get(), 1, 12, 15),
					new BlueprintTrade(3, NeapolitanItems.MINT_CAKE.get(), 1, 12, 15)
			);
		}

		TradeUtil.addVillagerTrades(event, VillagerProfession.BUTCHER, TradeUtil.MASTER,
				new BlueprintTrade(NeapolitanItems.MINT_LEAVES.get(), 10, 1, 12, 30)
		);

		TradeUtil.addVillagerTrades(event, VillagerProfession.FLETCHER, TradeUtil.EXPERT,
				new BlueprintTrade(1, NeapolitanItems.BANANARROW.get(), 4, 12, 15)
		);
	}

	@SubscribeEvent
	public static void onWandererTradesEvent(WandererTradesEvent event) {
		TradeUtil.addWandererTrades(event,
				new BlueprintTrade(1, NeapolitanItems.STRAWBERRY_PIPS.get(), 1, 12, 1),
				new BlueprintTrade(2, NeapolitanItems.VANILLA_PODS.get(), 1, 5, 1),
				new BlueprintTrade(2, NeapolitanBlocks.BANANA_FROND.get().asItem(), 1, 5, 1),
				new BlueprintTrade(2, NeapolitanItems.MINT_SPROUT.get(), 1, 5, 1),
				new BlueprintTrade(2, NeapolitanItems.ADZUKI_BEANS.get(), 1, 5, 1)
		);

		TradeUtil.addRareWandererTrades(event,
				new BlueprintTrade(1, NeapolitanItems.WHITE_STRAWBERRIES.get(), 1, 8, 1),
				new BlueprintTrade(3, NeapolitanItems.MAGIC_BEANS.get(), 1, 6, 1)
		);
	}
}
