package com.devdyna.synergy.init.builder.magic.entity_watcher;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.TickingBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import com.devdyna.synergy.init.types.zEntityTag;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;

@SuppressWarnings("null")
public class EntityWatcherBE extends TickingBE {

    public EntityWatcherBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EntityWatcherBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ENTITY_WATCHER.get(), pos, blockState);
    }

    @Override
    public void tickBoth() {
        var power = getSignal();
        if (power != getBlockState().getValue(BlockStateProperties.POWER))
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
        level.setBlockAndUpdate(
                getBlockPos(),
                getBlockState().setValue(BlockStateProperties.POWER, power));

    }

    public int getSignal() {
        if (level == null)
            return 0;

        for (int i = 1; i <= 15; i++) {
            var entity = getBlockState().getValue(EntityWatcherBlock.PLAYER_FILTER)
                    ? level.getNearestPlayer(
                            getBlockPos().getX() + 0.5,
                            getBlockPos().getY() + 0.5,
                            getBlockPos().getZ() + 0.5,
                            i,
                            true)
                    : level.getNearestEntity(
                            LivingEntity.class,
                            TargetingConditions.forNonCombat()
                                    .selector(e -> !e.getType().is(zEntityTag.ENTITY_WATCHER_IGNORE)),
                            null,
                            getBlockPos().getX() + 0.5,
                            getBlockPos().getY() + 0.5,
                            getBlockPos().getZ() + 0.5,
                            new AABB(getBlockPos()).inflate(i));

            if (entity != null)
                return i;
        }

        return 0;
    }

    public @Nullable LivingEntity getEntity() {
        if (level == null)
            return null;

        for (int i = 1; i <= 15; i++) {
            var entity = getBlockState().getValue(EntityWatcherBlock.PLAYER_FILTER)
                    ? level.getNearestPlayer(
                            getBlockPos().getX() + 0.5,
                            getBlockPos().getY() + 0.5,
                            getBlockPos().getZ() + 0.5,
                            15,
                            true)
                    : level.getNearestEntity(
                            LivingEntity.class,
                            TargetingConditions.forNonCombat()
                                    .selector(e -> !e.getType().is(zEntityTag.ENTITY_WATCHER_IGNORE)),
                            null,
                            getBlockPos().getX() + 0.5,
                            getBlockPos().getY() + 0.5,
                            getBlockPos().getZ() + 0.5,
                            new AABB(getBlockPos()).inflate(i));

            if (entity != null)
                return entity;
        }

        return null;

    }

}
