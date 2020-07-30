package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.neapolitan.common.entity.goals.AvoidBlockGoal;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MODID)
public class NeapolitanEvents {
	
	@SuppressWarnings("rawtypes")
	@SubscribeEvent
	public static void entityJoinWorldEvent(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof CreeperEntity) {
			CreeperEntity creeper = (CreeperEntity)event.getEntity();
			creeper.goalSelector.addGoal(0, new AvoidBlockGoal(creeper, NeapolitanBlocks.STRAWBERRY_BUSH.get(), 9.0F, 1.05D, 1.05D));
		}
	}
//	
//	@SubscribeEvent
//	public static void feedMilk(PlayerInteractEvent.EntityInteractSpecific event) {
//		if (event.getTarget() instanceof CowEntity || event.getTarget() instanceof AbstractFishEntity) {
//			event.setCanceled(true);
//		}
//		if(event.getTarget() != null && event.getTarget() instanceof LivingEntity) {
//			LivingEntity entity = (LivingEntity)event.getTarget();
//			PlayerEntity player = event.getPlayer();
//			ItemStack itemstack = event.getItemStack();
//			Item item = itemstack.getItem();
//			
//			if (item.isIn(NeapolitanTags.MILK_BUCKETS) && !event.getWorld().isRemote) {			
//				player.swingArm(event.getHand());
//				event.getWorld().playSound(null, event.getPos(), SoundEvents.ENTITY_WANDERING_TRADER_DRINK_MILK, SoundCategory.NEUTRAL, 1, 1);
//				
//				if (item == Items.MILK_BUCKET) {
//					entity.clearActivePotions();
//				}
//					
//				if (item == NeapolitanItems.CHOCOLATE_MILK_BUCKET.get()) {
//					ImmutableList<EffectInstance> effects = ImmutableList.copyOf(entity.getActivePotionEffects());
//					for (int i = 0; i < effects.size(); ++i) {
//						Effect effect = effects.get(i).getPotion();
//						if (effect.getEffectType() == EffectType.HARMFUL || effect == Effects.BAD_OMEN) {
//							entity.removePotionEffect(effect);
//						}
//					}
//				}
//					
//				if (item == NeapolitanItems.STRAWBERRY_MILK_BUCKET.get()) {
//					ImmutableList<EffectInstance> effects = ImmutableList.copyOf(entity.getActivePotionEffects());
//					for (int i = 0; i < effects.size(); ++i) {
//						Effect effect = effects.get(i).getPotion();
//						if (effect.getEffectType() == EffectType.BENEFICIAL) {
//							entity.removePotionEffect(effect);
//						}
//					}
//				}
//					
//				if (!player.abilities.isCreativeMode) player.setHeldItem(event.getHand(), new ItemStack(Items.BUCKET));
//				player.stopActiveHand();
//			}
//				
//			if (player instanceof ServerPlayerEntity) {
//				ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
//				CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, itemstack);
//				serverplayerentity.addStat(Stats.ITEM_USED.get(item));
//			}
//		}
//	}
}
