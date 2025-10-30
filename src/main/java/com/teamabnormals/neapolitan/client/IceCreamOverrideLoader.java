package com.teamabnormals.neapolitan.client;

import com.google.common.collect.MapMaker;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.teamabnormals.neapolitan.common.item.component.IceCream;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IceCreamOverrideLoader extends SimpleJsonResourceReloadListener {
	private static final Logger LOGGER = LogUtils.getLogger();

	public static final IceCreamOverrideLoader INSTANCE = new IceCreamOverrideLoader();

	private final Map<ResourceLocation, IceCreamOverrideHolder> overrides = new MapMaker().weakValues().concurrencyLevel(1).makeMap();
	private final List<IceCreamOverrideHolder> overridesReferences = new ArrayList<>();

	public IceCreamOverrideLoader() {
		super(new Gson(), "ice_cream_overrides");
	}

	public IceCreamOverride getOverride(ResourceLocation key) {
		final var holder = overrides.get(key);
		return holder != null ? holder.getOrNull() : null;
	}

	public Optional<IceCreamOverride> getFromIceCream(IceCream iceCream) {
		return this.overridesReferences.stream().map(holder -> holder.value).filter(ref -> {
			return ref.strict() ? ref.iceCream().is(iceCream) : ref.iceCream().matches(iceCream);
		}).findFirst();
	}

	public IceCreamOverrideHolder getOverrideHolder(ResourceLocation key) {
		return overrides.computeIfAbsent(key, IceCreamOverrideHolder::new);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager resourceManager, ProfilerFiller profiler) {
		overrides.values().forEach(IceCreamOverrideHolder::unbind);
		overridesReferences.clear();
		int loaded = 0;
		for (final var entry : jsons.entrySet()) {
			try {
				final var override = IceCreamOverride.CODEC.parse(JsonOps.INSTANCE, entry.getValue())
						.getOrThrow(JsonParseException::new);
				final var holder = getOverrideHolder(entry.getKey());
				holder.bind(override);
				overridesReferences.add(holder);
				loaded++;
			} catch (Exception e) {
				LOGGER.error("Failed to load ice cream override {}", entry.getKey(), e);
			}
		}
		LOGGER.info("Loaded {} ice cream overrides", loaded);
	}

	public static final class IceCreamOverrideHolder {
		public static final IceCreamOverride EMPTY_ANIMATION = new IceCreamOverride(IceCream.neapolitan(), false, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());

		private static final Logger LOGGER = LogUtils.getLogger();

		private final ResourceLocation key;
		@Nullable
		private IceCreamOverride value;
		private boolean absentWarned;

		IceCreamOverrideHolder(ResourceLocation key) {
			this.key = key;
		}

		void unbind() {
			value = null;
			absentWarned = false;
		}

		void bind(IceCreamOverride value) {
			this.value = value;
		}

		public ResourceLocation key() {
			return key;
		}

		public IceCreamOverride get() {
			final var result = value;
			if (result == null) {
				if (!absentWarned) {
					absentWarned = true;
					LOGGER.warn("Missing entity animation {}", key);
				}
				return EMPTY_ANIMATION;
			}
			return result;
		}

		@Nullable
		public IceCreamOverride getOrNull() {
			return value;
		}

		public boolean isBound() {
			return value != null;
		}
	}
}
