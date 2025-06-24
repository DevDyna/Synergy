package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.api.plants.builder.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class rice extends BaseCropBlock {

    public rice() {
        super(Properties.of());
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.RICE_SEED.get();
    }

}
