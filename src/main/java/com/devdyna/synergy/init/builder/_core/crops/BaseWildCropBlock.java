package com.devdyna.synergy.init.builder._core.crops;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.BushBlock;
import net.neoforged.neoforge.common.IShearable;

public class BaseWildCropBlock extends BushBlock implements IShearable {

    protected BaseWildCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<BaseWildCropBlock> codec() {
        return simpleCodec((p) -> new BaseWildCropBlock(p));
    }

}
