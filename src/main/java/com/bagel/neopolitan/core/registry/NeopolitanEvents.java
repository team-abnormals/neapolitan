//package com.bagel.neopolitan.core.registry;
//
//import com.bagel.neopolitan.core.Neopolitan;
//import com.google.common.collect.ImmutableList;
//
//import net.minecraft.advancements.CriteriaTriggers;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.passive.CowEntity;
//import net.minecraft.entity.passive.fish.AbstractFishEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.potion.Effect;
//import net.minecraft.potion.EffectInstance;
//import net.minecraft.potion.EffectType;
//import net.minecraft.potion.Effects;
//import net.minecraft.stats.Stats;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.SoundEvents;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
//
//@EventBusSubscriber(modid = Neopolitan.MODID)
//public class NeopolitanEvents {
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
//			if (item.isIn(NeopolitanTags.MILK_BUCKETS) && !event.getWorld().isRemote) {			
//				player.swingArm(event.getHand());
//				event.getWorld().playSound(null, event.getPos(), SoundEvents.ENTITY_WANDERING_TRADER_DRINK_MILK, SoundCategory.NEUTRAL, 1, 1);
//				
//				if (item == Items.MILK_BUCKET) {
//					entity.clearActivePotions();
//				}
//					
//				if (item == NeopolitanItems.CHOCOLATE_MILK_BUCKET.get()) {
//					ImmutableList<EffectInstance> effects = ImmutableList.copyOf(entity.getActivePotionEffects());
//					for (int i = 0; i < effects.size(); ++i) {
//						Effect effect = effects.get(i).getPotion();
//						if (effect.getEffectType() == EffectType.HARMFUL || effect == Effects.BAD_OMEN) {
//							entity.removePotionEffect(effect);
//						}
//					}
//				}
//					
//				if (item == NeopolitanItems.STRAWBERRY_MILK_BUCKET.get()) {
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
//}
