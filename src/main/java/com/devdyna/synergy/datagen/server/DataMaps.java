package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.dataMaps.RecordMap.ProviderRecipes;
import com.devdyna.synergy.init.types.zItems;

import com.devdyna.synergy.init.dataMaps.zDataMaps;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

@SuppressWarnings({ "deprecation", "null" })
public class DataMaps extends DataMapProvider {

        public DataMaps(PackOutput o, CompletableFuture<Provider> p) {
                super(o, p);
        }

        @Override
        protected void gather(Provider p) {

                builder(zDataMaps.PROVIDER_RECIPES).add(Items.COBBLESTONE.builtInRegistryHolder(),
                                new ProviderRecipes(Blocks.AIR.defaultBlockState(),
                                                new BlockState[] { Blocks.LAVA.defaultBlockState(),
                                                                Blocks.WATER.defaultBlockState() }),
                                false);

                builder(zDataMaps.PROVIDER_RECIPES).add(Items.BASALT.builtInRegistryHolder(),
                                new ProviderRecipes(Blocks.SOUL_SOIL.defaultBlockState(),
                                                new BlockState[] { Blocks.LAVA.defaultBlockState(),
                                                                Blocks.BLUE_ICE.defaultBlockState() }),
                                false);

                builder(NeoForgeDataMaps.COMPOSTABLES)
                                .add(zItems.COTTON_SEEDS.get().builtInRegistryHolder(), new Compostable(0.1F), false)
                                .add(zItems.COTTON.get().builtInRegistryHolder(), new Compostable(0.25F), false)

                                .add(zItems.BLUE_CUP_MUSHROOM.get().builtInRegistryHolder(), new Compostable(0.25F),
                                                false)
                                .add(zItems.BLUE_CUP_SPORE.get().builtInRegistryHolder(), new Compostable(0.15F), false)

                                .add(zItems.VIOLET_WEBCAP_MUSHROOM.get().builtInRegistryHolder(),
                                                new Compostable(0.25F), false)
                                .add(zItems.VIOLET_WEBCAP_SPORE.get().builtInRegistryHolder(), new Compostable(0.155F),
                                                false)

                                .add(zItems.RICE_SEED.get().builtInRegistryHolder(), new Compostable(0.2F), false)

                                .add(zItems.CAVE_WHEAT_SEEDS.get().builtInRegistryHolder(), new Compostable(0.6F),
                                                false)

                                .add(zItems.AZALEA_SEEDS.get().builtInRegistryHolder(), new Compostable(0.1F), false)
                                .add(zItems.SMALL_AZALEA_LEAF.get().builtInRegistryHolder(), new Compostable(0.105F),
                                                false)
                                .add(zItems.SMALL_AZALEA_ROOTS.get().builtInRegistryHolder(), new Compostable(0.15F),
                                                false);

                        builder(NeoForgeDataMaps.FURNACE_FUELS)
                        .add(zItems.INFERNAL_EMBER.get().builtInRegistryHolder(), new FurnaceFuel(3200), false);

        }

}
