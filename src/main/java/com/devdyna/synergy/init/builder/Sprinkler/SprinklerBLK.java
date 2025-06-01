package com.devdyna.synergy.init.builder.Sprinkler;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.BaseBlockBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class SprinklerBLK extends BaseBlockBE {

    public SprinklerBLK() {
        super(Material.bProp);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new SprinklerBE(p, s);
    }

}
