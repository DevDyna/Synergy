package com.devdyna.synergy.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.devdyna.synergy.init.types.zItemTag;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")

public class DataItemTag extends ItemTagsProvider {

    public DataItemTag(PackOutput o, CompletableFuture<Provider> p, CompletableFuture<TagLookup<Block>> b) {
        super(o, p, b);
    }

    @Override
    protected void addTags(Provider p) {
        tag(zItemTag.STONE_SLABS).add(Items.STONE_SLAB, Items.SMOOTH_STONE_SLAB);
        tag(Tags.Items.TOOLS_WRENCH).add(zItems.PIPE_UPDATER.get());
        
    }

}