package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.dataMaps.RecordMap.ProviderRecipes;
import com.devdyna.synergy.init.dataMaps.zDataMaps;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.data.DataMapProvider;

@SuppressWarnings({ "deprecation" })
public class DataMaps extends DataMapProvider {

    public DataMaps(PackOutput o, CompletableFuture<Provider> p) {
        super(o, p);
    }

    @Override
    protected void gather(Provider p) {

        builder(zDataMaps.PROVIDER_RECIPES).add(Items.COBBLESTONE.builtInRegistryHolder(),
                new ProviderRecipes(Blocks.AIR.defaultBlockState(),
                        new BlockState[] { Blocks.LAVA.defaultBlockState(), Blocks.WATER.defaultBlockState() }),
                false);

        builder(zDataMaps.PROVIDER_RECIPES).add(Items.BASALT.builtInRegistryHolder(),
                new ProviderRecipes(Blocks.SOUL_SOIL.defaultBlockState(),
                        new BlockState[] { Blocks.LAVA.defaultBlockState(), Blocks.BLUE_ICE.defaultBlockState() }),
                false);


    }

}
