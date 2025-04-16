package com.teamabnormals.neapolitan.core.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.component.Tool;
import org.apache.commons.compress.utils.Lists;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(ShearsItem.class)
public abstract class ShearsItemMixin {

	@Inject(method = "createToolProperties", at = @At("RETURN"), cancellable = true)
	private static void getDestroySpeed(CallbackInfoReturnable<Tool> cir) {
		Tool ret = cir.getReturnValue();
		ArrayList<Tool.Rule> rules = Lists.newArrayList();
		rules.addAll(ret.rules());
		//TODO: add rules
		//rules.add(Tool.Rule.overrideSpeed(List.of(NeapolitanBlocks.BEANSTALK_THORNS.get()), 15.0F));
		//rules.add(Tool.Rule.overrideSpeed(List.of(NeapolitanBlocks.BANANA_BUNDLE.get()), 5.0F));

		cir.setReturnValue(new Tool(
				ImmutableList.copyOf(rules),
				ret.defaultMiningSpeed(),
				ret.damagePerBlock()
		));
	}
}