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

                public static final ResourceKey<PlacedFeature> MUSH_BLUE_CUP = Material.createPlacedFeature("blue_cup");
                public static final ResourceKey<PlacedFeature> MUSH_VIOLET_WEBCAP = Material
                                .createPlacedFeature("violet_webcap");
                public static final ResourceKey<PlacedFeature> WILD_RICE = Material.createPlacedFeature("wild_rice");
                public static final ResourceKey<PlacedFeature> WILD_COTTON = Material
                                .createPlacedFeature("wild_cotton");
                public static final ResourceKey<PlacedFeature> WILD_CAVE_WHEAT = Material
                                .createPlacedFeature("wild_cave_wheat");

                public static final ResourceKey<PlacedFeature> AQUAMARINE_CLUSTER = Material
                                .createPlacedFeature("aquamarine_cluster");
        }

        public class ConfiguredFeatures {
                public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD = Material
                                .createConfiguredFeature("ironwood_tree");

                public static final ResourceKey<ConfiguredFeature<?, ?>> MUSH_BLUE_CUP = Material
                                .createConfiguredFeature("blue_cup");
                public static final ResourceKey<ConfiguredFeature<?, ?>> MUSH_VIOLET_WEBCAP = Material
                                .createConfiguredFeature("violet_webcap");
                public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_RICE = Material
                                .createConfiguredFeature("wild_rice");
                public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_COTTON = Material
                                .createConfiguredFeature("wild_cotton");
                public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_CAVE_WHEAT = Material
                                .createConfiguredFeature("wild_cave_wheat");

                public static final ResourceKey<ConfiguredFeature<?, ?>> AQUAMARINE_CLUSTER = Material
                                .createConfiguredFeature("aquamarine_cluster");

        }

        public class BiomeModifiers {
                public static final ResourceKey<BiomeModifier> IRONWOOD = Material.createBiomeModifier("add_ironwood");

                public static final ResourceKey<BiomeModifier> MUSH_BLUE_CUP = Material
                                .createBiomeModifier("add_blue_cup");
                public static final ResourceKey<BiomeModifier> MUSH_VIOLET_WEBCAP = Material
                                .createBiomeModifier("add_violet_webcap");
                public static final ResourceKey<BiomeModifier> WILD_RICE = Material
                                .createBiomeModifier("add_wild_rice");
                public static final ResourceKey<BiomeModifier> WILD_COTTON = Material
                                .createBiomeModifier("add_wild_cotton");
                public static final ResourceKey<BiomeModifier> WILD_CAVE_WHEAT = Material
                                .createBiomeModifier("add_wild_cave_wheat");
                public static final ResourceKey<BiomeModifier> AQUAMARINE_CLUSTER = Material
                                .createBiomeModifier("aquamarine_cluster");
        }

}
