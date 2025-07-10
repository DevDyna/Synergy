package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.api.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.MapColor;

public class cotton extends BaseShortCropBlock {

    public cotton() {
        super(Properties.of().mapColor(MapColor.WOOL));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.COTTON_SEEDS.get();
    }

}
