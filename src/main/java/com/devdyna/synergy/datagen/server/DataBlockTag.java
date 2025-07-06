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

        tag(zBlockTag.MASHABLE).addTag(zBlockTag.CAN_CONNECT).add(zBlocks.SPRINKLER.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(zBlocks.SPRINKLER.get());

        tag(zBlockTag.MUSHROOMS)
                .add(zBlocks.BLUE_CUP_MUSHROOM.get(), zBlocks.VIOLET_WEBCAP_MUSHROOM.get());

        tag(zBlockTag.PIPE).add(zBlocks.PIPE.get());

        tag(zBlockTag.NODE_RETRIEVAL)
                .add(zBlocks.ITEM_RETRIEVAL.get());

        tag(zBlockTag.NODE_TRANSFER)
                .add(zBlocks.ITEM_TRANSFER.get());

        tag(zBlockTag.NODE_PROVIDER)
                .add(zBlocks.ITEM_PROVIDER.get());

        tag(zBlockTag.NODE)
                .addTag(zBlockTag.NODE_TRANSFER)
                .addTag(zBlockTag.NODE_PROVIDER)
                .addTag(zBlockTag.NODE_RETRIEVAL);

        tag(zBlockTag.CAN_CONNECT)
                .addTag(zBlockTag.PIPE)
                .addTag(zBlockTag.NODE);

        // tag(zBlockTag.EXTRACTORS).add(zBlocks.EXTRACTOR.get());

        // zMultiTags.ALL_DEPOSITS.forEach(
        // depo -> tag(depo.block()).add(
        // zBlocks.deposits.get(zMultiTags.ALL_DEPOSITS.indexOf(depo)).get())
        // );

    }

}