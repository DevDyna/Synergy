package com.devdyna.synergy.init.builder.magic.watchers.entity;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.watchers.BaseWatcherBE;
import com.devdyna.synergy.api.utils.LevelUtil;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zEntityTag;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class EntityWatcherBE extends BaseWatcherBE {

    public EntityWatcherBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EntityWatcherBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ENTITY_WATCHER.get(), pos, blockState);
    }

    public int getSignal() {
        if (level == null)
            return 0;

        return  LevelUtil.trackEntityDistance(level, getBlockPos(),
                        e -> !e.getType().is(zEntityTag.ENTITY_WATCHER_IGNORE));
    }

    @Override
    public @Nullable LivingEntity getEntity() {
        if (level == null)
            return null;

        var entity = LevelUtil.getNearestEntity(level, getBlockPos(), 15);

        if (entity == null || entity.getType().is(zEntityTag.ENTITY_WATCHER_IGNORE))
            return null;

        return entity;
    }

}
