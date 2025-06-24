package com.devdyna.synergy.api.plants.builder;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class BaseSeedItem extends ItemNameBlockItem {

    public BaseSeedItem(Block block, Properties p) {
        super(block, p);
    }

    public BaseSeedItem(Block block) {
        this(block, new Properties());
    }

}
