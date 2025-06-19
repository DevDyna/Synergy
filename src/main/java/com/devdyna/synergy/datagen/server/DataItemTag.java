package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("null")

public class DataItemTag extends ItemTagsProvider {

    public DataItemTag(PackOutput o, CompletableFuture<Provider> p, CompletableFuture<TagLookup<Block>> b) {
        super(o, p, b);
    }

    @Override
    protected void addTags(Provider p) {
        tag(zItemTag.STONE_SLABS).add(Items.STONE_SLAB, Items.SMOOTH_STONE_SLAB);
        // tag(Tags.Items.TOOLS_WRENCH).add(zItems.SMASHER.get(), zItems.PIPE_REFARCTORIZER.get(),
        //         zItems.PIPE_VISUALIZER.get());

        tag(zItemTag.VISUALIZER).add(zItems.PIPE_VISUALIZER.get());

        tag(zItemTag.SMASHER).add(zItems.SMASHER.get());

    }

}