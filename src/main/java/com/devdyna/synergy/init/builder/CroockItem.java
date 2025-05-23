package com.devdyna.synergy.init.builder;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

public class CroockItem extends DiggerItem {

    public CroockItem(TagKey<Block> tag, Tier tier) {
        super(tier, tag,
                new Item.Properties()
                        .attributes(HoeItem.createAttributes(tier, -3.0F, 0.0F)));
    }
}
