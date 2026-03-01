package com.devdyna.synergy.init.types;

import com.devdyna.synergy.init.Material;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;

public class zWorldGenFeatures {
    public static void register(IEventBus bus) {
    }

    public class PlacedFeatures {
        public static final ResourceKey<PlacedFeature> IRONWOOD = Material.createPlacedFeature("ironwood_tree");

    }

    public class ConfiguredFeatures {
        public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD = Material
                .createConfiguredFeature("ironwood_tree");
    }

    public class BiomeModifiers {
        public static final ResourceKey<BiomeModifier> IRONWOOD = Material.createBiomeModifier("add_ironwood");

    }

}
