package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.api.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class cotton extends BaseShortCropBlock {

    public cotton() {
        super(Properties.of());
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.COTTON_SEEDS.get();
    }

}
