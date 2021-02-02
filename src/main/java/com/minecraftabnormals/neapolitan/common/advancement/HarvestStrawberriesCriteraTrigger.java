package com.minecraftabnormals.neapolitan.common.advancement;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.minecraftabnormals.neapolitan.core.Neapolitan;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;

public class HarvestStrawberriesCriteraTrigger extends AbstractCriterionTrigger<HarvestStrawberriesCriteraTrigger.Instance> {
	private static final ResourceLocation ID = new ResourceLocation(Neapolitan.MOD_ID, "harvest_strawberries");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public HarvestStrawberriesCriteraTrigger.Instance deserializeTrigger(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser conditionsParser) {
		Block block = deserializeBlock(json);
		StatePropertiesPredicate statepropertiespredicate = StatePropertiesPredicate.deserializeProperties(json.get("state"));
		if (block != null) {
			statepropertiespredicate.forEachNotPresent(block.getStateContainer(), (p_227148_1_) -> {
				throw new JsonSyntaxException("Block " + block + " has no property " + p_227148_1_);
			});
		}

		return new HarvestStrawberriesCriteraTrigger.Instance(entityPredicate, block, statepropertiespredicate);
	}

	@Nullable
	private static Block deserializeBlock(JsonObject object) {
		if (object.has("block")) {
			ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getString(object, "block"));
			return Registry.BLOCK.getOptional(resourcelocation).orElseThrow(() -> {
				return new JsonSyntaxException("Unknown block type '" + resourcelocation + "'");
			});
		} else {
			return null;
		}
	}

	public void trigger(ServerPlayerEntity player, BlockState state) {
		this.triggerListeners(player, (instance) -> {
			return instance.test(state);
		});
	}

	public static class Instance extends CriterionInstance {
		private final Block block;
		private final StatePropertiesPredicate stateCondition;

		public Instance(EntityPredicate.AndPredicate player, @Nullable Block block, StatePropertiesPredicate stateCondition) {
			super(HarvestStrawberriesCriteraTrigger.ID, player);
			this.block = block;
			this.stateCondition = stateCondition;
		}

		public static HarvestStrawberriesCriteraTrigger.Instance create(Block block) {
			return new HarvestStrawberriesCriteraTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND, block, StatePropertiesPredicate.EMPTY);
		}

		@Override
		public JsonObject serialize(ConditionArraySerializer conditions) {
			JsonObject jsonobject = super.serialize(conditions);
			if (this.block != null) {
				jsonobject.addProperty("block", Registry.BLOCK.getKey(this.block).toString());
			}

			jsonobject.add("state", this.stateCondition.toJsonElement());
			return jsonobject;
		}

		public boolean test(BlockState state) {
			if (this.block != null && !state.isIn(this.block)) {
				return false;
			} else {
				return this.stateCondition.matches(state);
			}
		}
	}
}