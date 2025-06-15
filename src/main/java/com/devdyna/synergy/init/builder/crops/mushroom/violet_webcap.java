package com.devdyna.synergy.init.builder.crops.mushroom;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.crops.BaseCropMushroom;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class violet_webcap extends BaseCropMushroom {

    public violet_webcap() {
        super(Material.cropProp.lightLevel(s->4));
    }

    @Override
    public int maxBrightnessSustainable() {
        return getMaxAge()+1;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.VIOLET_WEBCAP_SPORE.get();
    }

}
