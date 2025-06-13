package com.devdyna.synergy.init.builder.crops;

import com.devdyna.synergy.init.builder._core.BaseCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.level.ItemLike;

public class elf_cup extends BaseCropBlock {

    public elf_cup() {
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.ELF_CUP_SPORES.get();
    }

}
