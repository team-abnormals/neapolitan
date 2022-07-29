package com.teamabnormals.neapolitan.core.data.server.tags;

import com.teamabnormals.neapolitan.core.Neapolitan;
import com.teamabnormals.neapolitan.core.other.NeapolitanConstants;
import com.teamabnormals.neapolitan.core.other.tags.NeapolitanEntityTypeTags;
import com.teamabnormals.neapolitan.core.registry.NeapolitanEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NeapolitanEntityTypeTagsProvider extends EntityTypeTagsProvider {

	public NeapolitanEntityTypeTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Neapolitan.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(EntityTypeTags.ARROWS).add(NeapolitanEntityTypes.BANANARROW.get());

		this.tag(NeapolitanEntityTypeTags.CHIMPANZEE_DART_TARGETS).addOptional(NeapolitanConstants.BOLLOOM_FRUIT).addOptional(NeapolitanConstants.BOLLOOM_BALLOON);
		this.tag(NeapolitanEntityTypeTags.SCARES_CHIMPANZEES).add(EntityType.DRAGON_FIREBALL, EntityType.EVOKER_FANGS, EntityType.FIREBALL, EntityType.FIREWORK_ROCKET, EntityType.LIGHTNING_BOLT, EntityType.SMALL_FIREBALL, EntityType.TNT, EntityType.WITHER_SKULL);
		this.tag(NeapolitanEntityTypeTags.UNAFFECTED_BY_HARMONY);
		this.tag(NeapolitanEntityTypeTags.UNAFFECTED_BY_SLIPPING).add(NeapolitanEntityTypes.CHIMPANZEE.get(), NeapolitanEntityTypes.PLANTAIN_SPIDER.get());
		this.tag(NeapolitanEntityTypeTags.PLANTAIN_SPIDERS_CAN_REPLACE).add(EntityType.SPIDER);
		this.tag(NeapolitanEntityTypeTags.EXPLOSION_HEALS_IN_STRAWBERRY).add(EntityType.CREEPER).addOptional(NeapolitanConstants.CREEPIE);
		this.tag(NeapolitanEntityTypeTags.MUDDY_PROJECTILES).addOptional(NeapolitanConstants.MUD_BALL);
	}
}