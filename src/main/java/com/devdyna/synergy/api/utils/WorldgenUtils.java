package com.devdyna.synergy.api.utils;

import java.util.List;

import net.minecraft.core.HolderSet;
import net.minecraft.core.HolderSet.Named;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;

public class WorldgenUtils {
        public static void registerPatchConfig(
                        BootstrapContext<ConfiguredFeature<?, ?>> c, ResourceKey<ConfiguredFeature<?, ?>> k,
                        Block block, int tries,
                        int xz, int y) {

                c.register(k,
                                new ConfiguredFeature<>(
                                                Feature.RANDOM_PATCH,
                                                new RandomPatchConfiguration(
                                                                tries,
                                                                xz,
                                                                y,
                                                                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                                                                new SimpleBlockConfiguration(
                                                                                                BlockStateProvider
                                                                                                                .simple(block))))));
        }

        public static void registerPatchPlaced(BootstrapContext<PlacedFeature> c,
                        ResourceKey<PlacedFeature> k,
                        ResourceKey<ConfiguredFeature<?, ?>> configured, int rarity, int minY, int maxY) {

                c.register(k,
                                new PlacedFeature(
                                                c.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(configured),
                                                List.of(
                                                                CountPlacement.of(4),
                                                                InSquarePlacement.spread(),
                                                                RarityFilter.onAverageOnceEvery(rarity),
                                                                BiomeFilter.biome(),
                                                                HeightRangePlacement.uniform(
                                                                                VerticalAnchor.absolute(minY),
                                                                                VerticalAnchor.absolute(maxY)))));
        }

        public static void registerBiomeModifer(BootstrapContext<BiomeModifier> c, ResourceKey<BiomeModifier> m,
                        Named<Biome> biome, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) {
                c.register(m, new AddFeaturesBiomeModifier(biome, features, step));
        }
}
