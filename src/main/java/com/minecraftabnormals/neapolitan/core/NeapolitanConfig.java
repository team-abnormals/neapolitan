package com.minecraftabnormals.neapolitan.core;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class NeapolitanConfig {
    public static class Common {
        public final ConfigValue<Boolean> milkingWithGlassBottles;
        public final ConfigValue<Integer> milkBucketUseTime;

        Common(ForgeConfigSpec.Builder builder) {
            milkingWithGlassBottles = builder.define("Milking with Glass Bottles", false);
            milkBucketUseTime = builder.define("Milk Bucket drink time", 8);
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
