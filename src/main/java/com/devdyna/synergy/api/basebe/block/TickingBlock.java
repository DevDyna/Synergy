package com.devdyna.synergy.api.basebe.block;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.basebe.be.*;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class TickingBlock extends Block implements EntityBlock {

    public TickingBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s,
            BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof TickingBE be) {

                if(l == null) return;

                be.tickBoth();
                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();
            }
        };
    }

}
