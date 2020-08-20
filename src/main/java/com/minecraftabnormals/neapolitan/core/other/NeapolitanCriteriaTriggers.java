package com.minecraftabnormals.neapolitan.core.other;

import com.minecraftabnormals.neapolitan.common.advancement.HarvestStrawberriesCriteraTrigger;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MODID)
public class NeapolitanCriteriaTriggers {
    public static final EmptyTrigger CREEPER_HEAL   = CriteriaTriggers.register(new EmptyTrigger(prefix("creeper_heal")));
    public static final EmptyTrigger VANILLA_POISON = CriteriaTriggers.register(new EmptyTrigger(prefix("vanilla_poison")));
    
    public static final HarvestStrawberriesCriteraTrigger HARVEST_STRAWBERRIES = CriteriaTriggers.register(new HarvestStrawberriesCriteraTrigger());
    
    private static ResourceLocation prefix(String name) {
        return new ResourceLocation(Neapolitan.MODID, name);
    }
}
