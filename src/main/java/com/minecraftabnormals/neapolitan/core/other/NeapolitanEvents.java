package com.minecraftabnormals.neapolitan.core.other;

import java.util.List;

import com.minecraftabnormals.neapolitan.common.entity.goals.AvoidBlockGoal;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanBlocks;
import com.minecraftabnormals.neapolitan.core.registry.NeapolitanItems;
import com.teamabnormals.abnormals_core.core.utils.TradeUtils;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MODID)
public class NeapolitanEvents {

    @SubscribeEvent
    public static void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof CreeperEntity) {
            CreeperEntity creeper = (CreeperEntity) event.getEntity();
            creeper.goalSelector.addGoal(3, new AvoidBlockGoal<>(creeper, NeapolitanBlocks.STRAWBERRY_BUSH.get(), 6.0F, 1.0D, 1.2D));
        }
    }

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        VillagerProfession type = event.getType();
        Int2ObjectMap<List<ITrade>> trades = event.getTrades();

        List<ITrade> novice = trades.get(1);
        List<ITrade> journeyman = trades.get(3);
        List<ITrade> expert = trades.get(4);

        if (type == VillagerProfession.FARMER) {
            novice.add(new TradeUtils.EmeraldsForItemsTrade(NeapolitanItems.STRAWBERRIES.get(), 24, 1, 16, 2));

            journeyman.add(new TradeUtils.ItemsForEmeraldsTrade(NeapolitanItems.STRAWBERRY_SCONES.get(), 3, 12, 12, 10));

            expert.add(new TradeUtils.ItemsForEmeraldsTrade(NeapolitanItems.VANILLA_CAKE.get(), 3, 1, 12, 15));
            expert.add(new TradeUtils.ItemsForEmeraldsTrade(NeapolitanItems.CHOCOLATE_CAKE.get(), 3, 1, 12, 15));
            expert.add(new TradeUtils.ItemsForEmeraldsTrade(NeapolitanItems.STRAWBERRY_CAKE.get(), 3, 1, 12, 15));
        }

    }
}
