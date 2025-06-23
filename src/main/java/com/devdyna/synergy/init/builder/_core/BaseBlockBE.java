package com.devdyna.synergy.init.builder._core;

import javax.annotation.Nullable;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class BaseBlockBE extends Block implements EntityBlock {

    public BaseBlockBE(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s,
            BlockEntityType<T> ty) {

        return (lvl, pos, b, t) -> {
            if (t instanceof BaseBE be) {
                be.tickBoth();

                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();

            }
        };

    }

}
