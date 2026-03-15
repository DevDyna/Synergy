package com.devdyna.synergy.init.builder.magic.watchers.player;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.watchers.BaseWatcherBE;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class PlayerWatcherBE extends BaseWatcherBE {

    public PlayerWatcherBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public PlayerWatcherBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.PLAYER_WATCHER.get(), pos, blockState);
    }

    public int getSignal() {
        for (int i = 1; i <= 15; i++) {
            var entity = level.getNearestPlayer(
                    getBlockPos().getX() + 0.5,
                    getBlockPos().getY() + 0.5,
                    getBlockPos().getZ() + 0.5,
                    i,
                    true);

            if (entity != null && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity))
                return i;
        }
        return 0;
    }

    @Override
    public @Nullable LivingEntity getEntity() {
        if (level == null)
            return null;

        return level.getNearestPlayer(
                getBlockPos().getX() + 0.5,
                getBlockPos().getY() + 0.5,
                getBlockPos().getZ() + 0.5,
                15,
                true);
    }

}
