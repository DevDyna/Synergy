package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.crops.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class rice extends BaseCropBlock {

    public rice() {
        super(Material.cropProp);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.RICE_SEED.get();
    }

}
