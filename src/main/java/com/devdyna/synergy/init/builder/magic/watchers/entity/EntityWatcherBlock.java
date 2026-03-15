package com.devdyna.synergy.init.builder.magic.watchers.entity;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.watchers.BaseWatcherBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class EntityWatcherBlock extends BaseWatcherBlock {

    public EntityWatcherBlock(Properties properties) {
        super(properties);
    }

    public EntityWatcherBlock() {
        super();
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new EntityWatcherBE(p, s);
    }

}
