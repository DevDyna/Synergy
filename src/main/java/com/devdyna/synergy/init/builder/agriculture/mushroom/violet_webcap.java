package com.devdyna.synergy.init.builder.agriculture.mushroom;

import com.devdyna.synergy.api.plants.builder.BaseCropMushroom;
import com.devdyna.synergy.init.types.zBlockTag;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class violet_webcap extends BaseCropMushroom {

    public violet_webcap() {
        super(Properties.of().lightLevel(s -> 4).mapColor(MapColor.COLOR_MAGENTA));
    }

    @Override
    public int maxBrightnessSustainable() {
        return getMaxAge() + 1;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.VIOLET_WEBCAP_SPORE.get();
    }

    @Override
    public TagKey<Block> getSpawnFilter() {
        return zBlockTag.CAN_SUSTAIN_VIOLET_WEBCAP;
    }

}
