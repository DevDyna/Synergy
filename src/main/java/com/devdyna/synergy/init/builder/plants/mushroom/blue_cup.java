package com.devdyna.synergy.init.builder.plants.mushroom;

import com.devdyna.synergy.api.plants.builder.BaseCropMushroom;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class blue_cup extends BaseCropMushroom {

    public blue_cup() {
        super(Properties.of().mapColor(MapColor.TERRACOTTA_BLUE));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.BLUE_CUP_SPORE.get();
    }

    @Override
    public TagKey<Block> getSpawnFilter() {
        return zBlockTag.CAN_SUSTAIN_BLUE_CUP;
    }

}
