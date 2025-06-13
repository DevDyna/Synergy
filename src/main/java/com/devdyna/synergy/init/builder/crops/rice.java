package com.devdyna.synergy.init.builder.crops;

import com.devdyna.synergy.init.builder._core.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class rice extends BaseCropBlock {

    public rice() {
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.RICE_SEED.get();
    }

}
