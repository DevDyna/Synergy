package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DataWorldgen extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, DataWorldgen::biomeModifiers)
            .add(Registries.CONFIGURED_FEATURE, DataWorldgen::configuredFeatures)
            .add(Registries.PLACED_FEATURE, DataWorldgen::placedFeatures);

    public DataWorldgen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ID));
    }

    protected static void biomeModifiers(BootstrapContext<BiomeModifier> context) {

    }

    protected static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {

    }

    protected static void placedFeatures(BootstrapContext<PlacedFeature> context) {

    }

}
