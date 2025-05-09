package com.devdyna.modname_id.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.modname_id.Main;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings("null")

public class DataBlockTag extends BlockTagsProvider {

    public DataBlockTag(PackOutput o, CompletableFuture<Provider> l, ExistingFileHelper f) {
        super(o, l, Main.MODID, f);
    }

    @Override
    protected void addTags(Provider p) {
        // tag(BlockTag.LEAVES)
        //         .addTag(BlockTags.LEAVES);

    }

}