package com.devdyna.synergy.init.builder.magic.watchers.entity;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.watchers.BaseWatcherBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zEntityTag;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

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

        for (int i = 1; i <= 15; i++) {
            var entity = level.getNearestEntity(
                    LivingEntity.class,
                    TargetingConditions.forNonCombat(),
                    null,
                    getBlockPos().getX() + 0.5,
                    getBlockPos().getY() + 0.5,
                    getBlockPos().getZ() + 0.5,
                    new AABB(getBlockPos()).inflate(i));

            if (entity != null && !entity.getType().is(zEntityTag.ENTITY_WATCHER_IGNORE))
                return i;
        }

        return 0;
    }

    @Override
    public @Nullable LivingEntity getEntity() {
        if (level == null)
            return null;

        for (int i = 1; i <= 15; i++) {
            var entity = level.getNearestEntity(
                    LivingEntity.class,
                    TargetingConditions.forNonCombat(),
                    null,
                    getBlockPos().getX() + 0.5,
                    getBlockPos().getY() + 0.5,
                    getBlockPos().getZ() + 0.5,
                    new AABB(getBlockPos()).inflate(i));

            if (entity != null && !entity.getType().is(zEntityTag.ENTITY_WATCHER_IGNORE))
                return entity;
        }

        return null;

    }

}
