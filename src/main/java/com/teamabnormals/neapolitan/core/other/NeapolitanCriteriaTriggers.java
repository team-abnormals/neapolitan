package com.teamabnormals.neapolitan.core.other;

import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import com.teamabnormals.neapolitan.common.advancement.HarvestStrawberriesCriteraTrigger;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Neapolitan.MOD_ID)
public class NeapolitanCriteriaTriggers {
	public static final EmptyTrigger CREEPER_HEAL = CriteriaTriggers.register(new EmptyTrigger(prefix("creeper_heal")));
	public static final EmptyTrigger VANILLA_POISON = CriteriaTriggers.register(new EmptyTrigger(prefix("vanilla_poison")));
	public static final EmptyTrigger CHIMPANZEE_ATTACK = CriteriaTriggers.register(new EmptyTrigger(prefix("chimpanzee_attack")));

	public static final HarvestStrawberriesCriteraTrigger HARVEST_STRAWBERRIES = CriteriaTriggers.register(new HarvestStrawberriesCriteraTrigger());

	private static ResourceLocation prefix(String name) {
		return new ResourceLocation(Neapolitan.MOD_ID, name);
	}
}
