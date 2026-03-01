package com.devdyna.synergy.datagen.server;

import static com.devdyna.synergy.Main.ID;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.api.utils.WorldgenUtils;
import com.devdyna.synergy.init.types.zBiomeTags;
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
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
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
                                                c.lookup(Registries.BIOME).getOrThrow(zBiomeTags.IRONWOOD_TREE_SPAWN),
                                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                                .getOrThrow(PlacedFeatures.IRONWOOD)),
                                                GenerationStep.Decoration.VEGETAL_DECORATION));

                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.MUSH_BLUE_CUP,
                                c.lookup(Registries.BIOME).getOrThrow(zBiomeTags.BLUE_CUP_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.MUSH_BLUE_CUP)),
                                Decoration.VEGETAL_DECORATION);
                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.MUSH_VIOLET_WEBCAP,
                                c.lookup(Registries.BIOME).getOrThrow(zBiomeTags.VIOLET_WEBCAP_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.MUSH_VIOLET_WEBCAP)),
                                Decoration.VEGETAL_DECORATION);
                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.WILD_CAVE_WHEAT,
                                c.lookup(Registries.BIOME).getOrThrow(zBiomeTags.WILD_CAVE_WHEAT_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.WILD_CAVE_WHEAT)),
                                Decoration.VEGETAL_DECORATION);
                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.WILD_COTTON,
                                c.lookup(Registries.BIOME).getOrThrow(zBiomeTags.WILD_COTTON_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.WILD_COTTON)),
                                Decoration.VEGETAL_DECORATION);
                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.WILD_RICE,
                                c.lookup(Registries.BIOME).getOrThrow(zBiomeTags.WILD_RICE_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.WILD_RICE)),
                                Decoration.VEGETAL_DECORATION);

        }

        protected static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> c) {

                WorldgenUtils.registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.MUSH_BLUE_CUP,
                                zBlocks.BLUE_CUP_MUSHROOM.get(), 20, 5, 5);
                WorldgenUtils.registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.MUSH_VIOLET_WEBCAP,
                                zBlocks.VIOLET_WEBCAP_MUSHROOM.get(), 10, 7, 3);
                WorldgenUtils.registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.WILD_CAVE_WHEAT,
                                zBlocks.WILD_CAVE_WHEAT.get(), 10, 5, 5);
                WorldgenUtils.registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.WILD_COTTON,
                                zBlocks.WILD_COTTON.get(), 10, 8, 3);
                WorldgenUtils.registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.WILD_RICE,
                                zBlocks.WILD_RICE.get(), 8, 4, 3);

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

                WorldgenUtils.registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.MUSH_BLUE_CUP,
                                zWorldGenFeatures.ConfiguredFeatures.MUSH_BLUE_CUP, 10, -60, 4);
                WorldgenUtils.registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.MUSH_VIOLET_WEBCAP,
                                zWorldGenFeatures.ConfiguredFeatures.MUSH_VIOLET_WEBCAP, 5, -40, 20);
                WorldgenUtils.registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.WILD_CAVE_WHEAT,
                                zWorldGenFeatures.ConfiguredFeatures.WILD_CAVE_WHEAT, 3, -40, 24);
                WorldgenUtils.registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.WILD_COTTON,
                                zWorldGenFeatures.ConfiguredFeatures.WILD_COTTON, 1, 44, 120);
                WorldgenUtils.registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.WILD_RICE,
                                zWorldGenFeatures.ConfiguredFeatures.WILD_RICE, 1, 58, 80);

                c.register(zWorldGenFeatures.PlacedFeatures.IRONWOOD,
                                new PlacedFeature(
                                                c.lookup(Registries.CONFIGURED_FEATURE)
                                                                .getOrThrow(zWorldGenFeatures.ConfiguredFeatures.IRONWOOD),
                                                VegetationPlacements.treePlacement(
                                                                PlacementUtils.countExtra(0, 0.005f, 3),
                                                                zBlocks.IRON_WOOD.getSapling().get())));
        }

}
