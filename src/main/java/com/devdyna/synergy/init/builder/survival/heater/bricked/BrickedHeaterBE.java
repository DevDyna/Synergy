package com.devdyna.synergy.init.builder.survival.heater.bricked;

import com.devdyna.synergy.api.blockfactories.heater.SolidFuelHeaterBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class BrickedHeaterBE extends SolidFuelHeaterBE {

    public BrickedHeaterBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public BrickedHeaterBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.BRICKED_HEATER.get(), pos, blockState);
    }

    @Override
    public int getHeatCap() {
        return 100;
    }

}
