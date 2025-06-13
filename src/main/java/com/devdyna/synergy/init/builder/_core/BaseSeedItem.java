package com.devdyna.synergy.init.builder._core;

import com.devdyna.synergy.init.Material;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class BaseSeedItem extends ItemNameBlockItem{

    public BaseSeedItem(Block block) {
        super(block, Material.iProp);
    }
    
}
