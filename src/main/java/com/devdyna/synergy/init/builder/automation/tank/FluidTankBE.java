package com.devdyna.synergy.init.builder.automation.tank;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.api.beLogic.KeepFluidWhenBroken;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams.Builder;

public class FluidTankBE extends BETank implements KeepFluidWhenBroken {

    public FluidTankBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FluidTankBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.FLUID_TANK.get(), pos, blockState);
    }

    @Override
    public boolean whenSaveContent(BlockEntity be, Block block, BlockState state, Builder builder) {
        return defaultSaveCondition();
    }

}
