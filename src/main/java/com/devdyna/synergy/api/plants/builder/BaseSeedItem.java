package com.devdyna.synergy.api.plants.builder;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BaseSeedItem extends BlockItem {

    public BaseSeedItem(Block block, Properties p) {
        super(block, p.useItemDescriptionPrefix());
    }

    public BaseSeedItem(Block block) {
        this(block, new Properties());
    }

}
