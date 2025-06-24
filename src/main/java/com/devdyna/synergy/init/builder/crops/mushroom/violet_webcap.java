package com.devdyna.synergy.init.builder.crops.mushroom;

import com.devdyna.synergy.api.plants.builder.BaseCropMushroom;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class violet_webcap extends BaseCropMushroom {

    public violet_webcap() {
        super(Properties.of().lightLevel(s->4));
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
