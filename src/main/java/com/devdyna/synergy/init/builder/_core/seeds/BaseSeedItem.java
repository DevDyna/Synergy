package com.devdyna.synergy.init.builder._core.seeds;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class BaseSeedItem extends ItemNameBlockItem {

    public BaseSeedItem(Block block) {
        super(block, new Properties());
    }

    public BaseSeedItem(Block block, Properties p) {
        super(block, p);
    }

}
