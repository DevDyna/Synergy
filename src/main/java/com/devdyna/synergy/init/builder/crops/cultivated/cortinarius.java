package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.crops.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class cortinarius extends BaseShortCropBlock {

    public cortinarius() {
        super(Material.cropProp);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.CORTINARIUS_SPORES.get();
    }
}
