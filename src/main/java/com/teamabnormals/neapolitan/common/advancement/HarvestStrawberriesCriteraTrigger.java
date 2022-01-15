package com.teamabnormals.neapolitan.common.advancement;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.teamabnormals.neapolitan.core.Neapolitan;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class HarvestStrawberriesCriteraTrigger extends SimpleCriterionTrigger<HarvestStrawberriesCriteraTrigger.Instance> {
	private static final ResourceLocation ID = new ResourceLocation(Neapolitan.MOD_ID, "harvest_strawberries");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public HarvestStrawberriesCriteraTrigger.Instance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext conditionsParser) {
		Block block = deserializeBlock(json);
		StatePropertiesPredicate statepropertiespredicate = StatePropertiesPredicate.fromJson(json.get("state"));
		if (block != null) {
			statepropertiespredicate.checkState(block.getStateDefinition(), (p_227148_1_) -> {
				throw new JsonSyntaxException("Block " + block + " has no property " + p_227148_1_);
			});
		}

		return new HarvestStrawberriesCriteraTrigger.Instance(entityPredicate, block, statepropertiespredicate);
	}

	@Nullable
	private static Block deserializeBlock(JsonObject object) {
		if (object.has("block")) {
			ResourceLocation resourcelocation = new ResourceLocation(GsonHelper.getAsString(object, "block"));
			return Registry.BLOCK.getOptional(resourcelocation).orElseThrow(() -> {
				return new JsonSyntaxException("Unknown block type '" + resourcelocation + "'");
			});
		} else {
			return null;
		}
	}

	public void trigger(ServerPlayer player, BlockState state) {
		this.trigger(player, (instance) -> instance.test(state));
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		private final Block block;
		private final StatePropertiesPredicate stateCondition;

		public Instance(EntityPredicate.Composite player, @Nullable Block block, StatePropertiesPredicate stateCondition) {
			super(HarvestStrawberriesCriteraTrigger.ID, player);
			this.block = block;
			this.stateCondition = stateCondition;
		}

		public static HarvestStrawberriesCriteraTrigger.Instance create(Block block) {
			return new HarvestStrawberriesCriteraTrigger.Instance(EntityPredicate.Composite.ANY, block, StatePropertiesPredicate.ANY);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext conditions) {
			JsonObject jsonobject = super.serializeToJson(conditions);
			if (this.block != null) {
				jsonobject.addProperty("block", Registry.BLOCK.getKey(this.block).toString());
			}

			jsonobject.add("state", this.stateCondition.serializeToJson());
			return jsonobject;
		}

		public boolean test(BlockState state) {
			if (this.block != null && !state.is(this.block)) {
				return false;
			} else {
				return this.stateCondition.matches(state);
			}
		}
	}
}