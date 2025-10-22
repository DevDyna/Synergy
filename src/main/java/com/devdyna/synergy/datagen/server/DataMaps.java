package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.types.zItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.*;

@SuppressWarnings({ "deprecation", "null" })
public class DataMaps extends DataMapProvider {

        public DataMaps(PackOutput o, CompletableFuture<Provider> p) {
                super(o, p);
        }

        @Override
        protected void gather(Provider p) {

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

                // builder(zDataMaps.ITEM_TO_BRICK)
                //                 .add(Items.CLAY_BALL.builtInRegistryHolder(),
                //                                 new ItemToBrickMap(zBlocks.CLAY_BRICK.get().defaultBlockState(),Items.BRICK), false)
                //                 .add(zItems.PACKED_MUD_BALL.get().builtInRegistryHolder(),
                //                                 new ItemToBrickMap(zBlocks.PACKED_MUD_BRICK.get().defaultBlockState(),zItems.PACKED_MUD_BRICK.get()), false);

        }

}
