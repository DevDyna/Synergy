package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zWorldGenFeatures;
import com.devdyna.synergy.init.types.zWorldGenFeatures.PlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DataWorldgen extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, DataWorldgen::biomeModifiers)
            .add(Registries.CONFIGURED_FEATURE, DataWorldgen::configuredFeatures)
            .add(Registries.PLACED_FEATURE, DataWorldgen::placedFeatures);

    public DataWorldgen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ID));
    }

    protected static void biomeModifiers(BootstrapContext<BiomeModifier> c) {
        c.register(zWorldGenFeatures.BiomeModifiers.IRONWOOD,
                new AddFeaturesBiomeModifier(
                        HolderSet.direct(c.lookup(Registries.BIOME).getOrThrow(Biomes.PLAINS)),
                        HolderSet.direct(c.lookup(Registries.PLACED_FEATURE).getOrThrow(PlacedFeatures.IRONWOOD)),
                        GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    protected static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> c) {
        c.register(zWorldGenFeatures.ConfiguredFeatures.IRONWOOD,
                new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(zBlocks.IRON_WOOD.getLog().get()),
                        new StraightTrunkPlacer(5, 8, 0),
                        BlockStateProvider.simple(zBlocks.IRON_WOOD.getLeaves().get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines()
                        .build()));
    }

    protected static void placedFeatures(BootstrapContext<PlacedFeature> c) {
        c.register(zWorldGenFeatures.PlacedFeatures.IRONWOOD,
                new PlacedFeature(
                        c.lookup(Registries.CONFIGURED_FEATURE)
                                .getOrThrow(zWorldGenFeatures.ConfiguredFeatures.IRONWOOD),
                        VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05f, 3),
                                zBlocks.IRON_WOOD.getSapling().get())));
    }

}
