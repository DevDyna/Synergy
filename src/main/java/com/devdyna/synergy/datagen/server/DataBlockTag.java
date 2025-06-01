package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zBlocks;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")

public class DataBlockTag extends BlockTagsProvider {

    public DataBlockTag(PackOutput o, CompletableFuture<Provider> l, ExistingFileHelper f) {
        super(o, l, Main.ID, f);
    }

    @Override
    protected void addTags(Provider p) {

        tag(zBlockTag.LEAVES).addTag(BlockTags.LEAVES);

        tag(zBlockTag.CROPS).addTag(BlockTags.CROPS);

        tag(zBlockTag.PIPE_CONNECTORS).add(zBlocks.PIPE.get(), zBlocks.NODE.get());

    }

}