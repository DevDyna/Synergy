package com.devdyna.synergy.init.builder.magic.watchers.player;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.watchers.BaseWatcherBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class PlayerWatcherBlock extends BaseWatcherBlock {

    public PlayerWatcherBlock(Properties properties) {
        super(properties);
    }

    public PlayerWatcherBlock() {
        super();
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new PlayerWatcherBE(p, s);
    }

}
