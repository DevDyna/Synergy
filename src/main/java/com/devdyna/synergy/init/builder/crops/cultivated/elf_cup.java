package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.crops.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class elf_cup extends BaseShortCropBlock {

    public elf_cup() {
        super(Material.cropProp);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.ELF_CUP_SPORES.get();
    }

}
