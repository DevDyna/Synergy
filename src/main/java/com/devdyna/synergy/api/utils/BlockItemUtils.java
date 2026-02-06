package com.devdyna.synergy.api.utils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class BlockItemUtils {
    /**
     * check when is a BlockItem and if is inside the blocktag
     */
    public static boolean blockCheck(ItemStack i, TagKey<Block> tag) {
        return i.getItem() instanceof BlockItem bi && bi.getBlock().defaultBlockState().is(tag);
    }
}
