package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.api.plants.builder.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.MapColor;

public class rice extends BaseCropBlock {

    public rice() {
        super(Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.RICE_SEED.get();
    }

}
