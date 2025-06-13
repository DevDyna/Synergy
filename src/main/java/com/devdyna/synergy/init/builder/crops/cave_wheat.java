package com.devdyna.synergy.init.builder.crops;

import com.devdyna.synergy.init.builder._core.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class cave_wheat extends BaseCropBlock {

    public cave_wheat() {
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.CAVE_WHEAT_SEEDS.get();
    }


}
