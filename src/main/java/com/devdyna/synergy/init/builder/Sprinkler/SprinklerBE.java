package com.devdyna.synergy.init.builder.Sprinkler;

import com.devdyna.synergy.init.builder._core.BaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class SprinklerBE extends BaseBE {

    private static int radius = 5;

    public SprinklerBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SPRINKLER.get(), pos, state);
    }

    @Override
    public void tickServer() {
        BlockPos.randomBetweenClosed(level.random, 3,
                getBlockPos().getX() - radius, getBlockPos().getY(), getBlockPos().getZ() - radius,
                getBlockPos().getX() + radius, getBlockPos().getY() + 2, getBlockPos().getZ() + radius)
                .forEach(pos -> {
                    BlockState st = level.getBlockState(pos);
                    if (st.isRandomlyTicking()) {
                        st.randomTick((ServerLevel) level, pos, level.random);
                    }
                });
    }

}
