package com.devdyna.synergy.api.blockfactories.watchers;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.TickingBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public abstract class BaseWatcherBE extends TickingBE {

    public BaseWatcherBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public abstract int getSignal();

    public abstract @Nullable LivingEntity getEntity();

    @Override
    public void tickBoth() {
        var power = getSignal();
        if (power != getBlockState().getValue(BlockStateProperties.POWER))
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
        level.setBlockAndUpdate(
                getBlockPos(),
                getBlockState().setValue(BlockStateProperties.POWER, power));

    }

}
